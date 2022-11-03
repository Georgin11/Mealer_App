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
//        db.execSQL("PRAGMA foreign_keys = ON");

        createTableStatement  = "CREATE TABLE " + ADMIN_TABLE + " ( " +
                COLUMN_ADMIN_USERNAME + " TEXT PRIMARY KEY, " +
                COLUMN_ADMIN_PASSWORD + " TEXT)";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + ADDRESS_TABLE + "( " +
                COLUMN_ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ADDRESS_STREET + " TEXT, " +
                COLUMN_ADDRESS_STREET_NUMBER + " INTEGER, " +
                COLUMN_ADDRESS_POSTAL_CODE + " TEXT, " +
                COLUMN_ADDRESS_CITY + " TEXT)";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + PAYMENT_INFO_TABLE + "(" +
                COLUMN_PAYMENT_INFO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PAYMENT_INFO_CARDHOLDER_NAME + " TEXT, " +
                COLUMN_PAYMENT_INFO_CARD_NUMBER + " BIGINT, " +
                COLUMN_PAYMENT_INFO_CVV + " INTEGER," +
                COLUMN_PAYMENT_INFO_ADDRESS_ID + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_PAYMENT_INFO_ADDRESS_ID + ") " +
                    "REFERENCES " + ADDRESS_TABLE + "( " + COLUMN_ADDRESS_ID + "))";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + CLIENT_TABLE + "(" +
                COLUMN_CLIENT_USERNAME + " TEXT PRIMARY KEY, " +
                COLUMN_CLIENT_PASSWORD + " TEXT, " +
                COLUMN_CLIENT_FIRSTNAME + " TEXT, " +
                COLUMN_CLIENT_LASTNAME + " TEXT, " +
                COLUMN_CLIENT_EMAIL + " TEXT, " +
                COLUMN_CLIENT_ADDRESS_ID + " INTEGER, " +
                COLUMN_CLIENT_PAYMENT_INFO_ID + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_CLIENT_ADDRESS_ID + ") " +
                    "REFERENCES " + ADDRESS_TABLE + " (" + COLUMN_ADDRESS_ID + "), " +
                "FOREIGN KEY ( " + COLUMN_CLIENT_PAYMENT_INFO_ID + " ) " +
                    "REFERENCES " + PAYMENT_INFO_TABLE + " (" + COLUMN_PAYMENT_INFO_ID + "))";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + COOK_TABLE + " (" +
                COLUMN_COOK_USERNAME + " TEXT PRIMARY KEY, " +
                COLUMN_COOK_PASSWORD + " TEXT, " +
                COLUMN_COOK_FIRSTNAME + " TEXT, " +
                COLUMN_COOK_LASTNAME + " TEXT, " +
                COLUMN_COOK_EMAIL + " TEXT, " +
                COLUMN_COOK_BIO + " TEXT, " +
                COLUMN_COOK_CHEQUE + " BOOL, " +
                COLUMN_COOK_SUSPENSION_LENGTH + " INTEGER, " +
                COLUMN_COOK_ADDRESS_ID + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_COOK_ADDRESS_ID + ") " +
                    "REFERENCES " + ADDRESS_TABLE + " (" + COLUMN_ADDRESS_ID + "))";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + COMPLAINT_TABLE + " (" +
                COLUMN_COMPLAINT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COMPLAINT_TEXT + " TEXT, " +
                COLUMN_COMPLAINT_RATING + " INTEGER, " +
                COLUMN_COMPLAINT_DAYS_SUSPENDED + " INTEGER, " +
                COLUMN_COMPLAINT_CLIENT + " TEXT, " +
                COLUMN_COMPLAINT_COOK + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_COMPLAINT_CLIENT + ") " +
                    "REFERENCES " + CLIENT_TABLE + " (" + COLUMN_CLIENT_USERNAME + "), " +
                "FOREIGN KEY (" + COLUMN_COMPLAINT_COOK + ")" +
                    "REFERENCES " + COOK_TABLE + "(" + COLUMN_COOK_USERNAME + "))";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(CreditCardInfo creditCardInfo) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        // getting client address information
        cv.put(COLUMN_ADDRESS_STREET, creditCardInfo.client.getPaymentInfo().getBillingAddress().getStreet());
        cv.put(COLUMN_ADDRESS_STREET_NUMBER, creditCardInfo.client.getPaymentInfo().getBillingAddress().getNumber());
        cv.put(COLUMN_ADDRESS_POSTAL_CODE, creditCardInfo.client.getPaymentInfo().getBillingAddress().getPostal());
        cv.put(COLUMN_ADDRESS_CITY, creditCardInfo.client.getPaymentInfo().getBillingAddress().getCity());

        // inserting into the database
        long insert = db.insert(ADDRESS_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        // clearing content values
        cv.clear();

        //getting payment info address
        cv.put(COLUMN_PAYMENT_INFO_CARDHOLDER_NAME, creditCardInfo.client.getPaymentInfo().getCardHolderName());
        cv.put(COLUMN_PAYMENT_INFO_CARD_NUMBER, creditCardInfo.client.getPaymentInfo().getCardNumber().longValue());
        cv.put(COLUMN_PAYMENT_INFO_CVV, creditCardInfo.client.getPaymentInfo().getCvv());
        cv.put(COLUMN_PAYMENT_INFO_ADDRESS_ID, getAddressCount());

        insert = db.insert(PAYMENT_INFO_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();

        cv.put(COLUMN_ADDRESS_STREET, creditCardInfo.client.getAddress().getStreet());
        cv.put(COLUMN_ADDRESS_STREET_NUMBER, creditCardInfo.client.getAddress().getNumber());
        cv.put(COLUMN_ADDRESS_POSTAL_CODE, creditCardInfo.client.getAddress().getPostal());
        cv.put(COLUMN_ADDRESS_CITY, creditCardInfo.client.getAddress().getCity());

        insert = db.insert(ADDRESS_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();

        cv.put(COLUMN_CLIENT_USERNAME, creditCardInfo.client.getUsername());
        cv.put(COLUMN_CLIENT_PASSWORD, creditCardInfo.client.getPassword());
        cv.put(COLUMN_CLIENT_FIRSTNAME, creditCardInfo.client.getfName());
        cv.put(COLUMN_CLIENT_LASTNAME, creditCardInfo.client.getlName());
        cv.put(COLUMN_CLIENT_EMAIL, creditCardInfo.client.getEmail());
        cv.put(COLUMN_CLIENT_ADDRESS_ID, getAddressCount());
        cv.put(COLUMN_CLIENT_PAYMENT_INFO_ID, getPaymentInfoCount());

        insert = db.insert(CLIENT_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }
        cv.clear();
        return true;
    }

    public boolean addOne(CookRegistration cookRegistration) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ADDRESS_STREET, CookRegistration.cook.getAddress().getStreet());
        cv.put(COLUMN_ADDRESS_STREET_NUMBER, CookRegistration.cook.getAddress().getNumber());
        cv.put(COLUMN_ADDRESS_POSTAL_CODE, CookRegistration.cook.getAddress().getPostal());
        cv.put(COLUMN_ADDRESS_CITY, CookRegistration.cook.getAddress().getCity());

        long insert = db.insert(ADDRESS_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();

        cv.put(COLUMN_COOK_USERNAME, CookRegistration.cook.getUsername());
        cv.put(COLUMN_COOK_PASSWORD, CookRegistration.cook.getPassword());
        cv.put(COLUMN_COOK_FIRSTNAME, CookRegistration.cook.getfName());
        cv.put(COLUMN_COOK_LASTNAME, CookRegistration.cook.getlName());
        cv.put(COLUMN_COOK_EMAIL, CookRegistration.cook.getEmail());
        cv.put(COLUMN_COOK_BIO, CookRegistration.cook.getBio());
        cv.put(COLUMN_COOK_CHEQUE, CookRegistration.cook.hasVoidCheque());
        cv.put(COLUMN_COOK_SUSPENSION_LENGTH, CookRegistration.cook.getSuspensionLength());
        cv.put(COLUMN_COOK_ADDRESS_ID, getAddressCount());

        insert = db.insert(COOK_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();
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

            return false;
        }

        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.getCount() <=0) {
            cursor.close();

            return false;
        }

        cursor.close();
        return true;
    }

    public long getAddressCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, ADDRESS_TABLE);
    }

    public long getPaymentInfoCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, PAYMENT_INFO_TABLE);
    }

    public boolean checkCookExists(String username) {
        if(!(checkUsernameExists(username))) {
            return false;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        String Query = " Select * from " + COOK_TABLE + " where " + COLUMN_COOK_USERNAME + " = " + "'" + username + "'";
        boolean exists;
        Cursor cursor = db.rawQuery(Query, null);

        exists = (cursor.getCount() > 0);

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

        cursor.close();
        return exists;
    }
}
