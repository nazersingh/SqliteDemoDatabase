package com.example.saini.sqlitedemodatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Contacts.db";
    private static final int DATABASE_VER = 3;
    private static final String TABLE_NAME = "Contacts";
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);

        Log.e(TAG, "DatabaseHelper: constructor ");
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,MOBILE_NUMBER TEXT,EMAIL TEXT)");

        Log.e(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.e(TAG, "onUpgrade: ");
        /**
         * if database version upgrade
         * if you dont' want to save previous data in database
         * then use
         */
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(sqLiteDatabase);

        /**
         * if database version upgrade
         * if you want to save previous data in database
         * then use
         * mention the coulumn add in which version
         */
        if (newVersion > oldVersion) {
//            sqLiteDatabase.execSQL("ALTER TABLE "+TABLE_NAME+" ADD COLUMN gender TEXT;");
            sqLiteDatabase.execSQL("ALTER TABLE "+TABLE_NAME+" ADD COLUMN age TEXT;");
        }


    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new SQLiteException("Can't downgrade database from version " +
                oldVersion + " to " + newVersion);
    }

    /**
     * @param name
     * @param mobile_number
     * @param email
     * @return
     */
    public boolean insert(String name, String mobile_number, String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("MOBILE_NUMBER", mobile_number);
        contentValues.put("EMAIL", email);

        long value = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        /**
         * if value is -1 then it's exception
         * if value is 1 the succesfully inserted
         */
        if (value > 0)
            return true;
        else
            return false;
    }

    /**
     * search Data
     *
     * @param mobileNumber
     */
    public Cursor getDataFromTable(String mobileNumber) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE MOBILE_NUMBER = '" + mobileNumber + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Log.e(TAG, "getDataFromTable: "+query);
        Log.e(TAG, "getDataFromTable: "+cursor.toString()+" "+cursor.getColumnName(1));
        return cursor;
    }

    public Cursor getAllDataTable() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME ;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        Log.e(TAG, "getAllDataTable: "+query);
        return cursor;
    }


    public boolean updateData(String name,String mobile_number,String email,int id) {
        Log.e(TAG, "updateData: "+id );
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("MOBILE_NUMBER", mobile_number);
        contentValues.put("EMAIL", email);

        long value = sqLiteDatabase.update(TABLE_NAME,  contentValues,"ID="+id,null);
        /**
         * if value is -1 then it's exception
         * if value is 1 the succesfully inserted
         */
        if (value == 1)
            return true;
        else
            return false;
    }
}
