package org.buildmlearn.mconference.constant;

/**
 * Created by jai on 18/6/16.
 */
public interface Constants {

    int SPLASH_TIME_OUT = 3000;

    String PREFERENCES_FILE_NAME = "PREFS";

    String PARSING_COMPLETE = "parsing complete";

    long milliInOneDay = 86400000l;

    String DAY_KEY = "day";

    String NAME_TAG = "name";
    String LOGO_TAG = "logo";
    String VENUE_TAG = "venue";
    String TIME_TAG = "time";
    String START_TAG = "start";
    String END_TAG = "end";
    String SPLASHBG_TAG = "splashbg";
    String TYPE_ATTR = "type";
    String ABOUTBG_TAG = "aboutbg";
    String DETAILS_TAG = "details";
    String REGLINK_TAG = "reglink";
    String SPONSORS_TAG = "sponsors";
    String ITEM_TAG = "item";
    String DESC_TAG = "desc";
    String IMAGE_TAG = "image";
    String TALKS_TAG = "talks";

    String TYPE_ATTR_INDIV = "individual";

    //Constants for local Database

    int DATABASE_VERSION = 1;
    String DATABASE_NAME = "mConference.db";
    String TABLE_SPONSORS = "sponsors";
    String TABLE_TALKS = "talks";

    String COLUMN_NAME = "name";
    String COLUMN_LOCATION = "location";
    String COLUMN_START = "start";
    String COLUMN_END = "end";
    String COLUMN_DESC = "desc";
    String COLUMN_URL = "url";
    String COLUMN_FAVORITE = "favorite";

    String CREATE_TALKS_TABLE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_TALKS + "("
            + COLUMN_NAME + " TEXT, " + COLUMN_URL + " TEXT, "
            + COLUMN_START + " DATETIME, " + COLUMN_END + " DATETIME, "
            + COLUMN_LOCATION + " TEXT, " + COLUMN_FAVORITE + " INTEGER, "
            + COLUMN_DESC + " TEXT)";

    String CREATE_SPONSORS_TABLE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_SPONSORS + "("
            + COLUMN_NAME + " TEXT, " + COLUMN_URL + " TEXT)";

    String GET_SPONSORS_QUERY = "SELECT * FROM " + TABLE_SPONSORS;
    String GET_TALKS_QUERY = "SELECT * FROM " + TABLE_TALKS + " WHERE " + COLUMN_START + " BETWEEN ";

    String LAT = "latitude";
    String LONG = "lomgitude";

}
