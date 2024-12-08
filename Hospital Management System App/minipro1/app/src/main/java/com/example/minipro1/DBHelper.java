package com.example.minipro1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mydatabase.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_BIRTHDATE = "birthdate";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_USERNAME + " TEXT NOT NULL UNIQUE, "
                + COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, "
                + COLUMN_PASSWORD + " TEXT NOT NULL, "
                + COLUMN_BIRTHDATE + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public String getUsernameColumnName() {
        return COLUMN_USERNAME;
    }

    public Cursor getData(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

//    public boolean insertData(String username, String email, String password) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COLUMN_USERNAME, username);
//        contentValues.put(COLUMN_EMAIL, email);
//        contentValues.put(COLUMN_PASSWORD, password);
//        long result = db.insert(TABLE_NAME, null, contentValues);
//        return result != -1;
//    }

    public boolean register(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, users.getUserName());
        values.put(COLUMN_EMAIL, users.getEmail());
        values.put(COLUMN_PASSWORD, users.getPassword());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

        return true;
    }

    // code to get all users in a list view
    public List<Users> getAllUsers() {
        List<Users> usersList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Users users = new Users();
                users.setId(Integer.parseInt(cursor.getString(0)));
                users.setUserName(cursor.getString(1));
                users.setEmail(cursor.getString(2));
                users.setPassword(cursor.getString(3));

                // Adding contact to list
                usersList.add(users);
            } while (cursor.moveToNext());
        }

        // return contact list
        return usersList;
    }

    public boolean insertUserData(String name, String username, String password, String email, String birthdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("birthdate", birthdate);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public void updatePassword(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, users.getId());
        values.put(COLUMN_USERNAME, users.getUserName());
        values.put(COLUMN_EMAIL, users.getEmail());
        values.put(COLUMN_PASSWORD, users.getPassword());

        // Inserting Row
        db.update(TABLE_NAME, values, COLUMN_ID+"= ?", new String[]{String.valueOf(users.getId())});
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
}
