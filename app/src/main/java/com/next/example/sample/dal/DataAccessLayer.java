package com.next.example.sample.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by next on 29/9/16.
 */
public class DataAccessLayer {
    protected static final String TAG = "Sample_DataAccessLayer";
    protected static final String CREATETABLE = "CREATE TABLE IF NOT EXISTS ";
    protected static final String DATATYPE_TEXT = "TEXT";
    protected static final String DROPTABLE = "DROP TABLE IF EXISTS";
    protected static SQLiteDatabase mDb;
    protected static DatabaseHelper mDbHelper;
    protected Context mCtx;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "employee.db";

    public static final String TRN_TABLE_EMPLOYEE = "trn_employee";
    public static final String TRN_EMPLOYEE_ID = "employee_id";
    public static final String TRN_EMPLOYEE_FIRST_NAME = "first_name";
    public static final String TRN_EMPLOYEE_LAST_NAME = "last_name";
    public static final String TRN_EMPLOYEE_USER_NAME = "username";
    public static final String TRN_EMPLOYEE_PASSWORD = "password";
    public static final String TRN_EMPLOYEE_TEAM = "team";
    public static final String TRN_EMPLOYEE_SYNC_STATUS = "sync_status";


    protected static final String TABLE_EMPLOYEE = CREATETABLE + " "
            + TRN_TABLE_EMPLOYEE + "("
            + TRN_EMPLOYEE_ID + " " + DATATYPE_TEXT + ","
            + TRN_EMPLOYEE_FIRST_NAME + " " + DATATYPE_TEXT + ","
            + TRN_EMPLOYEE_LAST_NAME + " " + DATATYPE_TEXT + ","
            + TRN_EMPLOYEE_TEAM + " " + DATATYPE_TEXT + ","
            + TRN_EMPLOYEE_USER_NAME + " " + DATATYPE_TEXT + ","
            + TRN_EMPLOYEE_PASSWORD + " " + DATATYPE_TEXT + ","
            + TRN_EMPLOYEE_SYNC_STATUS + " " + DATATYPE_TEXT + ");";

    public DataAccessLayer(Context ctx) {
        this.mCtx = ctx;
    }

    public DataAccessLayer open() throws SQLException {
        //System.out.println("Inside DAL");
        mDbHelper= new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public static void close() {
        mDbHelper.close();
    }

    public long insertEmployee(int id,String fName, String lName, String team, String uName, String pword,String syncStatus) {

        ContentValues args = new ContentValues();
        args.put(TRN_EMPLOYEE_ID, id);
        args.put(TRN_EMPLOYEE_FIRST_NAME, fName);
        args.put(TRN_EMPLOYEE_LAST_NAME, lName);
        args.put(TRN_EMPLOYEE_TEAM, team);
        args.put(TRN_EMPLOYEE_USER_NAME, uName);
        args.put(TRN_EMPLOYEE_PASSWORD, pword);
        args.put(TRN_EMPLOYEE_SYNC_STATUS, syncStatus);
        return mDb.insert(TRN_TABLE_EMPLOYEE, null, args);

    }

    public Cursor fetch(String tablename) throws SQLException {
        Cursor mCursorFetch = mDb.rawQuery("SELECT * FROM " + tablename, null);
        if (mCursorFetch != null) {
            mCursorFetch.moveToFirst();
        }
        return mCursorFetch;
    }
    public Cursor columnIdFetch(String tablename,String columnname,String columnvalue) throws SQLException {
        Cursor mCursorFetch = mDb.rawQuery("SELECT * FROM " + tablename+" "+"WHERE "+columnname+"='"+columnvalue+"'", null);
        if (mCursorFetch != null) {
            mCursorFetch.moveToFirst();
        }
        return mCursorFetch;
    }
    public Cursor loginFetch(String tablename,String username,String usernamevalue,String password,String passwordvalue) throws SQLException {
        Cursor mCursorFetch = mDb.rawQuery("SELECT * FROM "+tablename+" WHERE "+username+"='"+usernamevalue+"' AND "+password+"='"+passwordvalue+"'", null);
        if (mCursorFetch != null) {
            mCursorFetch.moveToFirst();
        }
        return mCursorFetch;
    }
    public void updateSyncStatus( String mTableName,String mTableId,String mTableValue ) throws SQLException {
        Cursor mCursor;
        String syncStatus_query = "UPDATE " + mTableName + " SET "
                + TRN_EMPLOYEE_SYNC_STATUS + " = '1' where " + mTableId + " = '" + mTableValue
                + "';";

        mCursor = mDb.rawQuery( syncStatus_query, null );
        if ( mCursor != null ) {
            mCursor.moveToFirst();
        }
        mCursor.close();


    }

    protected static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_EMPLOYEE);

            System.out.println("Successfully Table Created !!!");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
