package org.buildmlearn.mconference.secondapproach;

import android.util.Log;

import org.buildmlearn.mconference.constant.Constants;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jai on 20/7/16.
 */

public class IndexParser implements Constants{
    static XmlPullParserFactory pullParserFactory;
    static ArrayList<ConferenceMeta> conferences;

    public static ArrayList<ConferenceMeta> parseIndex() throws XmlPullParserException, IOException {

        conferences = new ArrayList<>();

        pullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = pullParserFactory.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);

        Log.d("Parse", "parse");
        URL url = new URL(INDEX_FILE_URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = null;

        try {
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            parser.setInput(inputStream, null);
            readIndex(parser);
        } finally {
            urlConnection.disconnect();
            if(inputStream!=null)
                inputStream.close();
        }

        return conferences;
    }

    private static void readIndex(XmlPullParser parser) throws XmlPullParserException, IOException {
        int event = parser.getEventType();

        while (event != XmlPullParser.END_DOCUMENT) {
            String name = parser.getName();

            switch (event) {
                case XmlPullParser.START_TAG:
                    if (name.equals(CONFERENCE_TAG))
                        conferences.add(readConferenceItem(parser));
                    break;
            }
            event = parser.next();
        }
    }

    private static ConferenceMeta readConferenceItem(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, null, CONFERENCE_TAG);
        ConferenceMeta conference = new ConferenceMeta();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG)
                continue;

            String name = parser.getName();

            if (name.equals(NAME_TAG))
                conference.setName(readText(parser));
            else if (name.equals(START_TAG))
                conference.setDate(new Date(Long.parseLong(readText(parser))));
            else if (name.equals(VENUE_TAG))
                conference.setVenue(readText(parser));
            else if (name.equals(IMAGE_TAG))
                conference.setLogoURL(readText(parser));
            else if (name.equals(CONFIG_URL_TAG))
                conference.setConfigURL(readText(parser));
        }

        parser.require(XmlPullParser.END_TAG, null, CONFERENCE_TAG);

        return conference;
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
