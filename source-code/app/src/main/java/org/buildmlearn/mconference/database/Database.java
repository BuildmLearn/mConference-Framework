package org.buildmlearn.mconference.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.buildmlearn.mconference.constant.Constants;
import org.buildmlearn.mconference.model.SponsorDetails;
import org.buildmlearn.mconference.model.TalkDetails;

import java.util.ArrayList;

/**
 * Created by jai on 19/6/16.
 */
public class Database extends SQLiteOpenHelper implements Constants {

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TALKS_TABLE);
        db.execSQL(CREATE_SPONSORS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TALKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPONSORS);
        onCreate(db);
    }

    public void addSponsor(ArrayList<SponsorDetails> sponsors) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (SponsorDetails sponsor : sponsors) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, sponsor.getName());
            values.put(COLUMN_URL, sponsor.getLogoURL());
            db.insert(TABLE_SPONSORS, null, values);
        }

        db.close();
    }

    public void addTalks(ArrayList<TalkDetails> talks) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (TalkDetails talk : talks) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, talk.getName());
            values.put(COLUMN_URL, talk.getImageURL());
            values.put(COLUMN_START, talk.getStartTime().getTime());
            values.put(COLUMN_END, talk.getEndTime().getTime());
            values.put(COLUMN_LOCATION, talk.getLocation());
            values.put(COLUMN_DESC, talk.getDesc());

            db.insert(TABLE_TALKS, null, values);
        }

        db.close();
    }
}
