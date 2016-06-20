package org.buildmlearn.mconference.util;

import android.content.Context;
import android.content.SharedPreferences;

import org.buildmlearn.mconference.constant.Constants;
import org.buildmlearn.mconference.database.Database;
import org.buildmlearn.mconference.model.SponsorDetails;
import org.buildmlearn.mconference.model.TalkDetails;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jai on 17/6/16.
 */
public class XMLParser implements Constants{
    static XmlPullParserFactory pullParserFactory;
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;
    public static Database db;

    public static void parse(Context context) throws XmlPullParserException, IOException{

        InputStream inputStream =  context.getAssets().open("sampleXML.xml");
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);

            db = new Database(context);
            sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();

            readXML(parser);
        } finally {
            inputStream.close();
        }
    }

    private static void readXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        int event = parser.getEventType();
        String text = null;

        while (event != XmlPullParser.END_DOCUMENT) {
            String name = parser.getName();

            switch (event) {
                case XmlPullParser.START_TAG:
                    if (name.equals(TIME_TAG))
                        readTime(parser);

                    else if (name.equals(SPONSORS_TAG))
                        readSponsors(parser);

                    else if (name.equals(TALKS_TAG))
                        readTalks(parser);

                    break;

                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;

                case XmlPullParser.END_TAG:
                    if (name.equals(NAME_TAG))
                        editor.putString(NAME_TAG, text);

                    else if (name.equals(LOGO_TAG))
                        editor.putString(LOGO_TAG, text);

                    else if (name.equals(VENUE_TAG))
                        editor.putString(VENUE_TAG, text);

                    else if (name.equals(SPLASHBG_TAG)) {
                        editor.putString(SPLASHBG_TAG + " " + TYPE_ATTR,
                                parser.getAttributeValue(null, TYPE_ATTR));
                        editor.putString(SPLASHBG_TAG, text);
                    }

                    else if (name.equals(ABOUTBG_TAG))
                        editor.putString(ABOUTBG_TAG, text);
                        
                    else if (name.equals(DETAILS_TAG))
                        editor.putString(DETAILS_TAG, text);

                    else if (name.equals(REGLINK_TAG))
                        editor.putString(REGLINK_TAG, text);

                    break;
            }

            event = parser.next();
        }
        editor.putBoolean(PARSING_COMPLETE, true);
        editor.apply();
    }

    private static void readTime(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, TIME_TAG);

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG)
                continue;

            String name = parser.getName();
            if (name.equals(START_TAG))
                editor.putString(START_TAG, readText(parser));
            else if (name.equals(END_TAG))
                editor.putString(END_TAG, readText(parser));
        }
    }

    private static void readSponsors(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, SPONSORS_TAG);
        String type = parser.getAttributeValue(null, TYPE_ATTR);
        editor.putString(SPONSORS_TAG + " " + TYPE_ATTR, type);

        if (type.equals(TYPE_ATTR_INDIV)){
            ArrayList<SponsorDetails> sponsors = new ArrayList<>();

            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG)
                    continue;

                sponsors.add(readSponsorItem(parser));
            }

            db.addSponsor(sponsors);
        }

        else {
            editor.putString(SPONSORS_TAG, readText(parser));
        }

        parser.require(XmlPullParser.END_TAG, null, SPONSORS_TAG);
    }

    private static void readTalks(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, TALKS_TAG);
        ArrayList<TalkDetails> talks = new ArrayList<>();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG)
                continue;

            talks.add(readTalkItem(parser));
        }

        db.addTalks(talks);
        parser.require(XmlPullParser.END_TAG, null, TALKS_TAG);
    }

    private static SponsorDetails readSponsorItem(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, ITEM_TAG);
        SponsorDetails sponsor = new SponsorDetails();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG)
                continue;

            String name = parser.getName();
            if (name.equals(NAME_TAG))
                sponsor.setName(readText(parser));

            else if (name.equals(IMAGE_TAG))
                sponsor.setLogoURL(readText(parser));
        }

        parser.require(XmlPullParser.END_TAG, null, ITEM_TAG);

        return sponsor;
    }

    private static TalkDetails readTalkItem(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, ITEM_TAG);
        TalkDetails talk = new TalkDetails();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG)
                continue;

            String name = parser.getName();
            if (name.equals(NAME_TAG))
                talk.setName(readText(parser));

            else if (name.equals(DESC_TAG))
                talk.setDesc(readText(parser));

            else if (name.equals(START_TAG))
                talk.setStartTime(new Date(Long.parseLong(readText(parser))));

            else if (name.equals(END_TAG))
                talk.setEndTime(new Date(Long.parseLong(readText(parser))));

            else if (name.equals(VENUE_TAG))
                talk.setLocation(readText(parser));

            else if (name.equals(IMAGE_TAG))
                talk.setImageURL(readText(parser));
        }

        parser.require(XmlPullParser.END_TAG, null, ITEM_TAG);

        return talk;
    }

    private static String readText(XmlPullParser parser) throws XmlPullParserException, IOException {
        String result = "";

        if(parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
}
