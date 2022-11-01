package com.example.Mealer_App;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
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

    public static final String PAYMENT_INFO_TABLE = "PAYMENT_INFO_TABLE";
    public static final String COLUMN_PAYMENT_INFO_ID = "COLUMN_PAYMENT_INFO_ID";
    public static final String COLUMN_PAYMENT_INFO_CARDHOLDER_NAME = "COLUMN_PAYMENT_INFO_CARDHOLDERNAME";
    public static final String COLUMN_PAYMENT_INFO_CARD_NUMBER = "COLUMN_PAYMENT_INFO_CARD_NUMBER";
    public static final String COLUMN_PAYMENT_INFO_CVV = "COLUMN_PAYMENT_INFO_CVV";
    public static final String COLUMN_PAYMENT_INFO_ADDRESS_ID = "COLUMN_PAYMENT_INFO_ADDRESS";

    public static final String CLIENT_TABLE = "CLIENT_TABLE";
    public static final String COLUMN_CLIENT_FIRSTNAME = "COLUMN_CLIENT_FIRSTNAME";
    public static final String COLUMN_CLIENT_LASTNAME = "COLUMN_CLIENT_LASTNAME";
    public static final String COLUMN_CLIENT_EMAIL = "COLUMN_CLIENT_EMAIL";
    public static final String COLUMN_CLIENT_USERNAME = "COLUMN_CLIENT_USERNAME";
    public static final String COLUMN_CLIENT_PASSWORD = "COLUMN_CLIENT_PASSWORD";
    public static final String COLUMN_CLIENT_ADDRESS_ID = "COLUMN_CLIENT_ADDRESS";
    public static final String COLUMN_CLIENT_PAYMENT_INFO_ID = "COLUMN_CLIENT_PAYMENT_INFO";

    public static final String COOK_TABLE = "COOK_TABLE";
    public static final String COLUMN_COOK_FIRSTNAME = "COLUMN_COOK_FIRSTNAME";
    public static final String COLUMN_COOK_LASTNAME = "COLUMN_COOK_LASTNAME";
    public static final String COLUMN_COOK_EMAIL = "COLUMN_COOK_EMAIL";
    public static final String COLUMN_COOK_PASSWORD = "COLUMN_COOK_PASSWORD";
    public static final String COLUMN_COOK_BIO = "COLUMN_COOK_BIO";
    public static final String COLUMN_COOK_CHEQUE = "COLUMN_COOK_CHEQUE";
    public static final String COLUMN_COOK_SUSPENSION_LENGTH = "COLUMN_IS_SUSPENDED";
    public static final String COLUMN_COOK_USERNAME = "COLUMN_COOK_USERNAME";
    public static final String COLUMN_COOK_ADDRESS_ID = "COLUMN_COOK_ADDRESS";

    public static final String COMPLAINT_TABLE = "COMPLAINT_TABLE";
    public static final String COLUMN_COMPLAINT_ID = "COLUMN_COMPLAINT_ID";
    public static final String COLUMN_COMPLAINT_TEXT = "COLUMN_COMPLAINT_TEXT";
    public static final String COLUMN_COMPLAINT_RATING = "COLUMN_COMPLAINT_RATING";
    public static final String COLUMN_COMPLAINT_DAYS_SUSPENDED = "COLUMN_COMPLAINT_DAYS_SUSPENDED";
    public static final String COLUMN_COMPLAINT_CLIENT = "COLUMN_COMPLAINT_CLIENT";
    public static final String COLUMN_COMPLAINT_COOK = "COLUMN_COMPLAINT_COOK";
    public static final String COLUMN_ADMIN_USERNAME = "COLUMN_ADMIN_USERNAME";
    public static final String ADMIN_TABLE = "ADMIN_TABLE";
    public static final String COLUMN_ADMIN_PASSWORD = "COLUMN_ADMIN_PASSWORD";


    public Database(@Nullable Context context) {
        super(context, "mealer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement;
        db.execSQL("PRAGMA foreign_keys = ON");

        createTableStatement  = "CREATE TABLE " + ADMIN_TABLE + " ( " +
                COLUMN_ADMIN_USERNAME + " TEXT PRIMARY KEY NOT NULL, " +
                COLUMN_ADMIN_PASSWORD + " TEXT NOT NULL)";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + ADDRESS_TABLE + "( " +
                COLUMN_ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ADDRESS_STREET + " TEXT NOT NULL, " +
                COLUMN_ADDRESS_STREET_NUMBER + " INTEGER NOT NULL, " +
                COLUMN_ADDRESS_POSTAL_CODE + " TEXT NOT NULL, " +
                COLUMN_ADDRESS_CITY + " TEXT NOT NULL)";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + PAYMENT_INFO_TABLE + "(" +
                COLUMN_PAYMENT_INFO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PAYMENT_INFO_CARDHOLDER_NAME + " TEXT NOT NULL, " +
                COLUMN_PAYMENT_INFO_CARD_NUMBER + " BIGINT NOT NULL, " +
                COLUMN_PAYMENT_INFO_CVV + " INTEGER NOT NULL," +
                COLUMN_PAYMENT_INFO_ADDRESS_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_PAYMENT_INFO_ADDRESS_ID + ") " +
                    "REFERENCES " + ADDRESS_TABLE + "( " + COLUMN_ADDRESS_ID + "))";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + CLIENT_TABLE + "(" +
                COLUMN_CLIENT_USERNAME + " TEXT PRIMARY KEY NOT NULL, " +
                COLUMN_CLIENT_PASSWORD + " TEXT NOT NULL, " +
                COLUMN_CLIENT_FIRSTNAME + " TEXT NOT NULL, " +
                COLUMN_CLIENT_LASTNAME + " TEXT NOT NULL, " +
                COLUMN_CLIENT_EMAIL + " TEXT NOT NULL, " +
                COLUMN_CLIENT_ADDRESS_ID + " INTEGER NOT NULL, " +
                COLUMN_CLIENT_PAYMENT_INFO_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_CLIENT_ADDRESS_ID + ") " +
                    "REFERENCES " + ADDRESS_TABLE + " (" + COLUMN_ADDRESS_ID + "), " +
                "FOREIGN KEY ( " + COLUMN_CLIENT_PAYMENT_INFO_ID + " ) " +
                    "REFERENCES " + PAYMENT_INFO_TABLE + " (" + COLUMN_PAYMENT_INFO_ID + "))";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + COOK_TABLE + " (" +
                COLUMN_COOK_USERNAME + " TEXT PRIMARY KEY NOT NULL, " +
                COLUMN_COOK_PASSWORD + " TEXT NOT NULL, " +
                COLUMN_COOK_FIRSTNAME + " TEXT NOT NULL, " +
                COLUMN_COOK_LASTNAME + " TEXT NOT NULL, " +
                COLUMN_COOK_EMAIL + " TEXT NOT NULL, " +
                COLUMN_COOK_BIO + " TEXT NOT NULL, " +
                COLUMN_COOK_CHEQUE + " BOOL NOT NULL, " +
                COLUMN_COOK_SUSPENSION_LENGTH + " INTEGER NOT NULL, " +
                COLUMN_COOK_ADDRESS_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_COOK_ADDRESS_ID + ") " +
                    "REFERENCES " + ADDRESS_TABLE + " (" + COLUMN_ADDRESS_ID + "))";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + COMPLAINT_TABLE + " (" +
                COLUMN_COMPLAINT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COMPLAINT_TEXT + " TEXT NOT NULL, " +
                COLUMN_COMPLAINT_RATING + " INTEGER NOT NULL, " +
                COLUMN_COMPLAINT_DAYS_SUSPENDED + " INTEGER NOT NULL, " +
                COLUMN_COMPLAINT_CLIENT + " TEXT NOT NULL, " +
                COLUMN_COMPLAINT_COOK + " TEXT NOT NULL, " +
                "FOREIGN KEY (" + COLUMN_COMPLAINT_CLIENT + ") " +
                    "REFERENCES " + CLIENT_TABLE + " (" + COLUMN_CLIENT_USERNAME + "), " +
                "FOREIGN KEY (" + COLUMN_COMPLAINT_COOK + ")" +
                    "REFERENCES " + COOK_TABLE + "(" + COLUMN_COOK_USERNAME + "))";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(CreditCardInfo clientRegistration) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        // getting client address information
        cv.put(COLUMN_ADDRESS_STREET, clientRegistration.client.getPaymentInfo().getBillingAddress().getStreet());
        cv.put(COLUMN_ADDRESS_STREET_NUMBER, clientRegistration.client.getPaymentInfo().getBillingAddress().getNumber());
        cv.put(COLUMN_ADDRESS_POSTAL_CODE, clientRegistration.client.getPaymentInfo().getBillingAddress().getPostal());
        cv.put(COLUMN_ADDRESS_CITY, clientRegistration.client.getPaymentInfo().getBillingAddress().getCity());

        // inserting into the database
        long insert = db.insert(ADDRESS_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        // clearing content values
        cv.clear();

        //getting payment info address
        cv.put(COLUMN_PAYMENT_INFO_CARDHOLDER_NAME, clientRegistration.client.getPaymentInfo().getCardHolderName());
        cv.put(COLUMN_PAYMENT_INFO_CARD_NUMBER, clientRegistration.client.getPaymentInfo().getCardNumber().longValue());
        cv.put(COLUMN_PAYMENT_INFO_CVV, clientRegistration.client.getPaymentInfo().getCvv());
        cv.put(COLUMN_PAYMENT_INFO_ADDRESS_ID, getAddressCount());

        insert = db.insert(PAYMENT_INFO_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();

        cv.put(COLUMN_ADDRESS_STREET, clientRegistration.client.getAddress().getStreet());
        cv.put(COLUMN_ADDRESS_STREET_NUMBER, clientRegistration.client.getAddress().getNumber());
        cv.put(COLUMN_ADDRESS_POSTAL_CODE, clientRegistration.client.getAddress().getPostal());
        cv.put(COLUMN_ADDRESS_CITY, clientRegistration.client.getAddress().getCity());

        insert = db.insert(ADDRESS_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();

        cv.put(COLUMN_CLIENT_USERNAME, clientRegistration.client.getUsername());
        cv.put(COLUMN_CLIENT_PASSWORD, clientRegistration.client.getPassword());
        cv.put(COLUMN_CLIENT_FIRSTNAME, clientRegistration.client.getfName());
        cv.put(COLUMN_CLIENT_LASTNAME, clientRegistration.client.getlName());
        cv.put(COLUMN_CLIENT_EMAIL, clientRegistration.client.getEmail());
        cv.put(COLUMN_CLIENT_ADDRESS_ID, getAddressCount());
        cv.put(COLUMN_CLIENT_PAYMENT_INFO_ID, getPaymentInfoCount());

        insert = db.insert(CLIENT_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }
        cv.clear();
        db.close();
        return true;
    }

    public boolean addOne(CookRegistration cookRegistration) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ADDRESS_STREET, cookRegistration.cook.getAddress().getStreet());
        cv.put(COLUMN_ADDRESS_STREET_NUMBER, cookRegistration.cook.getAddress().getNumber());
        cv.put(COLUMN_ADDRESS_POSTAL_CODE, cookRegistration.cook.getAddress().getPostal());
        cv.put(COLUMN_ADDRESS_CITY, cookRegistration.cook.getAddress().getCity());

        long insert = db.insert(ADDRESS_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();

        cv.put(COLUMN_COOK_USERNAME, cookRegistration.cook.getUsername());
        cv.put(COLUMN_COOK_FIRSTNAME, cookRegistration.cook.getfName());
        cv.put(COLUMN_COOK_LASTNAME, cookRegistration.cook.getlName());
        cv.put(COLUMN_COOK_EMAIL, cookRegistration.cook.getEmail());
        cv.put(COLUMN_COOK_BIO, cookRegistration.cook.getBio());
        cv.put(COLUMN_COOK_CHEQUE, cookRegistration.cook.hasVoidCheque());
        cv.put(COLUMN_COOK_SUSPENSION_LENGTH, cookRegistration.cook.getSuspensionLength());
        cv.put(COLUMN_COOK_ADDRESS_ID, getAddressCount());

        insert = db.insert(COOK_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();
        db.close();
        return true;
    }

    public boolean addOne() {
        return true;
    }

    public boolean checkUsernameExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = " Select * from " + CLIENT_TABLE + " where " + COLUMN_CLIENT_USERNAME + " = " + "'" + username + "'";
        boolean exists;
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0) {
            Query = " Select * from " + COOK_TABLE + " where " + COLUMN_COOK_USERNAME + " = " + "'" + username + "'";
            cursor = db.rawQuery(Query, null);
        }
        exists = (cursor.getCount() > 0);

        db.close();
        cursor.close();
        return exists;
    }

    public boolean matchPassword(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        String Query;
        if(checkClientExists(username)) {
            Query = " Select * from " + CLIENT_TABLE + " where " + COLUMN_CLIENT_PASSWORD + " = " + "'" + password + "'";
        } else if(checkCookExists(username)) {
            Query = " Select * from " + COOK_TABLE + " where " + COLUMN_COOK_PASSWORD + " = " + "'" + password + "'";
        } else {
            db.close();
            return false;
        }

        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.getCount() <=0) {
            cursor.close();
            db.close();
            return false;
        }

        cursor.close();
        db.close();
        return true;
    }

    public long getAddressCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, ADDRESS_TABLE);
        db.close();
        return count;
    }

    public long getPaymentInfoCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, PAYMENT_INFO_TABLE);
        db.close();
        return count;
    }

    public boolean checkCookExists(String username) {
        if(!(checkUsernameExists(username))) {
            return false;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        String Query = " Select * from " + COOK_TABLE + " where " + COLUMN_CLIENT_USERNAME + " = " + "'" + username + "'";
        boolean exists;
        Cursor cursor = db.rawQuery(Query, null);

        exists = (cursor.getCount() > 0);

        db.close();
        cursor.close();
        return exists;
    }

    public boolean checkClientExists(String username) {
        if(!(checkUsernameExists(username))) {
            return false;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        String Query = " Select * from " + CLIENT_TABLE + " where " + COLUMN_CLIENT_USERNAME + " = " + "'" + username + "'";
        boolean exists;
        Cursor cursor = db.rawQuery(Query, null);

        exists = (cursor.getCount() > 0);

        db.close();
        cursor.close();
        return exists;
    }
}
