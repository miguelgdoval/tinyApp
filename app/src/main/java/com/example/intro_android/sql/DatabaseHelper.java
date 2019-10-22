package com.example.intro_android.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.intro_android.model.University;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Example.db";

    // University table name
    private static final String TABLE_UNIVERSITY= "university";

    // University Table Columns names
    private static final String COLUMN_UNIVERSITY_ID = "id";
    private static final String COLUMN_UNIVERSITY_NAME = "name";
    private static final String COLUMN_UNIVERSITY_APLHA_TWO_CODE = "alpha_two_code";
    private static final String COLUMN_UNIVERSITY_DOMAIN = "domain";
    private static final String COLUMN_UNIVERSITY_COUNTRY= "country";
    private static final String COLUMN_UNIVERSITY_WEB_PAGE= "web_page";
    private static final String COLUMN_UNIVERSITY_STATE_PROVINCE= "state_province";

    // create table sql query
    private String CREATE_UNIVERSITY_TABLE = "CREATE TABLE " + TABLE_UNIVERSITY + "("
            + COLUMN_UNIVERSITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_UNIVERSITY_NAME + " TEXT,"
            + COLUMN_UNIVERSITY_APLHA_TWO_CODE + " TEXT," + COLUMN_UNIVERSITY_DOMAIN + " TEXT,"
            + COLUMN_UNIVERSITY_COUNTRY + " TEXT," + COLUMN_UNIVERSITY_WEB_PAGE + " TEXT,"
            + COLUMN_UNIVERSITY_STATE_PROVINCE + " TEXT" + ")";

    // drop table sql query
    private String DROP_UNIVERSITY_TABLE = "DROP TABLE IF EXISTS " + TABLE_UNIVERSITY;

    /**
     * Constructor
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_UNIVERSITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_UNIVERSITY_TABLE);
        // Create tables again
        onCreate(db);
    }

    /**
     * This method is to create university record
     * @param university
     */
    public void addUniversity(University university) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_UNIVERSITY_NAME, university.getName());
        values.put(COLUMN_UNIVERSITY_APLHA_TWO_CODE, university.getAlphaTwoCode());
        values.put(COLUMN_UNIVERSITY_DOMAIN, university.getDomains().get(0));
        values.put(COLUMN_UNIVERSITY_COUNTRY, university.getCountry());
        values.put(COLUMN_UNIVERSITY_WEB_PAGE, university.getWebPages().get(0));
        values.put(COLUMN_UNIVERSITY_STATE_PROVINCE, university.getStateProvince());
        // Inserting Row
        db.insert(TABLE_UNIVERSITY, null, values);
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<University> getAllUniversities() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_UNIVERSITY_ID,
                COLUMN_UNIVERSITY_NAME,
                COLUMN_UNIVERSITY_APLHA_TWO_CODE,
                COLUMN_UNIVERSITY_DOMAIN,
                COLUMN_UNIVERSITY_COUNTRY,
                COLUMN_UNIVERSITY_WEB_PAGE,
                COLUMN_UNIVERSITY_STATE_PROVINCE
        };
        // sorting orders
        String sortOrder =
                COLUMN_UNIVERSITY_NAME + " ASC";
        List<University> universitiesList = new ArrayList<University>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the university table
        /**
         * Here query function is used to fetch records from university table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT * FROM university ORDER BY name;
         */
        Cursor cursor = db.query(TABLE_UNIVERSITY, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                University university = new University();
                university.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_UNIVERSITY_ID))));
                university.setName(cursor.getString(cursor.getColumnIndex(COLUMN_UNIVERSITY_NAME)));
                university.setAlphaTwoCode(cursor.getString(cursor.getColumnIndex(COLUMN_UNIVERSITY_APLHA_TWO_CODE)));
                List<String> domains = new ArrayList<>();
                domains.add(cursor.getString(cursor.getColumnIndex(COLUMN_UNIVERSITY_DOMAIN)));
                university.setDomains(domains);
                university.setCountry(cursor.getString(cursor.getColumnIndex(COLUMN_UNIVERSITY_COUNTRY)));
                List<String> webPages = new ArrayList<>();
                domains.add(cursor.getString(cursor.getColumnIndex(COLUMN_UNIVERSITY_WEB_PAGE)));
                university.setWebPages(webPages);
                university.setStateProvince(cursor.getString(cursor.getColumnIndex(COLUMN_UNIVERSITY_STATE_PROVINCE)));
                universitiesList.add(university);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return universitiesList;
    }

    public void deleteUniversity(University university) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_UNIVERSITY, COLUMN_UNIVERSITY_ID + " = ?",
                new String[]{String.valueOf(university.getId())});
        db.close();
    }

}
