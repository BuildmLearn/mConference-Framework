package org.buildmlearn.mconference.util;

import android.content.Context;
import android.content.SharedPreferences;

import org.buildmlearn.mconference.constant.Constants;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jai on 17/6/16.
 */
public class XMLParser {
    XmlPullParserFactory pullParserFactory;

    public void parse(Context context) throws XmlPullParserException, IOException{

        InputStream inputStream =  context.getAssets().open("sampleXML.xml");
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);

            readXML(context, parser);
        } finally {
            inputStream.close();
        }
    }

    private void readXML(Context context, XmlPullParser parser) throws XmlPullParserException, IOException{
        int event = parser.getEventType();
        String text = null;
        SharedPreferences sharedPreferences = context.getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        while (event != XmlPullParser.END_DOCUMENT) {
            String name = parser.getName();

            switch (event) {
                case XmlPullParser.START_TAG:
                    break;

                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;

                case XmlPullParser.END_TAG:
                    if (name.equals(Constants.NAME_TAG))
                        editor.putString(Constants.NAME_TAG, text);

                    else if (name.equals(Constants.LOGO_TAG))
                        editor.putString(Constants.LOGO_TAG, text);

                    else if (name.equals(Constants.VENUE_TAG))
                        editor.putString(Constants.VENUE_TAG, text);
            }
        }
    }
}
