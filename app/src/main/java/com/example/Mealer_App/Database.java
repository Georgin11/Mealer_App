package com.example.Mealer_App;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {


    public static final String ADDRESS_TABLE = "ADDRESS_TABLE";
    public static final String COLUMN_ADDRESS_ID = "COLUMN_CLIENT_ADDRESS";
    public static final String COLUMN_ADDRESS_STREET = "COLUMN_STREET";
    public static final String COLUMN_ADDRESS_STREET_NUMBER = "COLUMN_ADDRESS_STREET_NUMBER";
    public static final String COLUMN_ADDRESS_POSTAL_CODE = "COLUMN_POSTAL_CODE";
    public static final String COLUMN_ADDRESS_CITY = "COLUMN_ADDRESS_CITY";

    public static final String PAYMENT_INFO_TABLE = "PAYMENTINFO_TABLE";
    public static final String COLUMN_PAYMENT_INFO_ID = "COLUMN_PAYMENTINFO_ID";
    public static final String COLUMN_PAYMENT_INFO_CARDHOLDER_NAME = "COLUMN_PAYMENTINFO_CARDHOLDERNAME";
    public static final String COLUMN_PAYMENT_INFO_CVV = "COLUMN_PAYMENTINFO_CVV";

    public static final String CLIENT_TABLE = "CLIENT_TABLE";
    public static final String COLUMN_CLIENT_FIRSTNAME = "CLIENT_FIRSTNAME";
    public static final String COLUMN_CLIENT_LASTNAME = "CLIENT_LASTNAME";
    public static final String COLUMN_CLIENT_EMAIL = "CLIENT_EMAIL";
    public static final String COLUMN_CLIENT_USERNAME = "CLIENT_USERNAME";
    public static final String COLUMN_CLIENT_PASSWORD = "CLIENT_PASSWORD";

    public static final String COOK_TABLE = "COOK_TABLE";
    public static final String COLUMN_COOK_FIRSTNAME = "COLUMN_COOK_FIRSTNAME";
    public static final String COLUMN_COOK_LASTNAME = "COLUMN_COOK_LASTNAME";
    public static final String COLUMN_COOK_EMAIL = "COLUMN_COOK_EMAIL";
    public static final String COLUMN_COOK_PASSWORD = "COLUMN_COOK_PASSWORD";
    public static final String COLUMN_COOK_BIO = "COLUMN_COOK_BIO";
    public static final String COLUMN_COOK_CHEQUE = "COLUMN_COOK_CHEQUE";
    public static final String COLUMN_COOK_SUSPENSION_LENGTH = "COLUMN_IS_SUSPENDED";
    public static final String COLUMN_COOK_USERNAME = "COLUMN_COOK_USERNAME";

    public static final String COMPLAINT_TABLE = "COMPLAINT_TABLE";
    public static final String COLUMN_COMPLAINT_ID = "COLUMN_COMPLAINT_ID";
    public static final String COLUMN_COMPLAINT_TEXT = "COLUMN_COMPLAINT_TEXT";
    public static final String COLUMN_COMPLAINT_RATING = "COLUMN_COMPLAINT_RATING";
    public static final String COLUMN_COMPLAINT_DAYS_SUSPENDED = "COLUMN_COMPLAINT_DAYS_SUSPENDED";


    public Database(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement;

        createTableStatement = "CREATE TABLE " + ADDRESS_TABLE + "( " +
                COLUMN_ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ADDRESS_STREET + " TEXT NOT NULL, " +
                COLUMN_ADDRESS_STREET_NUMBER + " INTEGER NOT NULL, " +
                COLUMN_ADDRESS_POSTAL_CODE + " TEXT NOT NULL, " +
                COLUMN_ADDRESS_CITY + " TEXT NOT NULL)";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + PAYMENT_INFO_TABLE + " (" +
                COLUMN_PAYMENT_INFO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PAYMENT_INFO_CARDHOLDER_NAME + " TEXT NOT NULL, " +
                COLUMN_PAYMENT_INFO_CVV + " INTEGER NOT NULL," +
                COLUMN_ADDRESS_ID + "INTEGER NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_ADDRESS_ID + ") " +
                    "REFERENCES " + ADDRESS_TABLE + "( " + COLUMN_ADDRESS_ID + "))";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + CLIENT_TABLE + "(" +
                COLUMN_CLIENT_USERNAME + " TEXT PRIMARY KEY NOT NULL, " +
                COLUMN_CLIENT_PASSWORD + " TEXT NOT NULL, " +
                COLUMN_CLIENT_FIRSTNAME + " TEXT NOT NULL, " +
                COLUMN_CLIENT_LASTNAME + " TEXT NOT NULL, " +
                COLUMN_CLIENT_EMAIL + " TEXT NOT NULL, " +
                COLUMN_ADDRESS_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_ADDRESS_ID + ") " +
                    "REFERENCES " + ADDRESS_TABLE + " (" + COLUMN_ADDRESS_ID + "))";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + COOK_TABLE + " (" +
                COLUMN_COOK_USERNAME + " TEXT PRIMARY KEY NOT NULL, " +
                COLUMN_COOK_PASSWORD + " TEXT NOT NULL, " +
                COLUMN_COOK_FIRSTNAME + " TEXT NOT NULL, " +
                COLUMN_COOK_LASTNAME + " TEXT NOT NULL, " +
                COLUMN_COOK_EMAIL + " TEXT NOT NULL, " +
                COLUMN_COOK_BIO + " TEXT NOT NULL, " +
                COLUMN_COOK_CHEQUE + " BOOL NOT NULL, " +
                COLUMN_ADDRESS_ID + " INTEGER NOT NULL, " +
                COLUMN_COOK_SUSPENSION_LENGTH + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_ADDRESS_ID + ") " +
                    "REFERENCES " + ADDRESS_TABLE + " (" + COLUMN_ADDRESS_ID + "))";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + COMPLAINT_TABLE + " (" +
                COLUMN_COMPLAINT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COMPLAINT_TEXT + " TEXT NOT NULL, " +
                COLUMN_COMPLAINT_RATING + " INTEGER NOT NULL, " +
                COLUMN_COMPLAINT_DAYS_SUSPENDED + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_CLIENT_USERNAME + ") " +
                    "REFERENCES " + CLIENT_TABLE + " (" + COLUMN_CLIENT_USERNAME + "), " +
                "FOREIGN KEY (" + COLUMN_COOK_USERNAME + ")" +
                    "REFERENCES " + COOK_TABLE + "(" + COLUMN_COOK_USERNAME + "))";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(ClientRegistration clientRegistration) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        return true;
    }

    public boolean addOne(CookRegistration cookRegistration) {

        return true;
    }
}
