package com.example.Mealer_App;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.Mealer_App.structure.Address;
import com.example.Mealer_App.structure.Admin;
import com.example.Mealer_App.structure.Client;
import com.example.Mealer_App.structure.PaymentInfo;
import com.example.Mealer_App.structure.Purchase;
import com.example.Mealer_App.structure.Review;
import com.example.Mealer_App.structure.Cook;
import com.example.Mealer_App.structure.Meal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    public static final String ADMIN_TABLE = "ADMIN_TABLE";
    public static final String COLUMN_ADMIN_USERNAME = "COLUMN_ADMIN_USERNAME";
    public static final String COLUMN_ADMIN_PASSWORD = "COLUMN_ADMIN_PASSWORD";

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
    public static final String COLUMN_COOK_SUSPENSION_LENGTH = "COLUMN_COOK_SUSPENSION_LENGTH";
    public static final String COLUMN_COOK_USERNAME = "COLUMN_COOK_USERNAME";
    public static final String COLUMN_COOK_ADDRESS_ID = "COLUMN_COOK_ADDRESS";

    public static final String REVIEW_TABLE = "REVIEW_TABLE";
    public static final String COLUMN_REVIEW_ID = "COLUMN_REVIEW_ID";
    public static final String COLUMN_REVIEW_TITLE = "COLUMN_REVIEW_TITLE";
    public static final String COLUMN_REVIEW_TEXT = "COLUMN_REVIEW_TEXT";
    public static final String COLUMN_REVIEW_RATING = "COLUMN_REVIEW_RATING";
    public static final String COLUMN_REVIEW_DAYS_SUSPENDED = "COLUMN_REVIEW_DAYS_SUSPENDED";
    public static final String COLUMN_REVIEW_CLIENT = "COLUMN_REVIEW_CLIENT";
    public static final String COLUMN_REVIEW_COOK = "COLUMN_REVIEW_COOK";

    public static final String MEAL_TABLE = "MEAL_TABLE";
    public static final String COLUMN_MEAL_ID = "COLUMN_MEAL_ID";
    public static final String COLUMN_MEAL_NAME = "COLUMN_MEAL_NAME";
    public static final String COLUMN_MEAL_COURSE = "COLUMN_MEAL_TYPE";
    public static final String COLUMN_MEAL_CUISINE = "COLUMN_MEAL_CUISINE";
    public static final String COLUMN_MEAL_INGREDIENTS = "COLUMN_MEAL_INGREDIENTS";
    public static final String COLUMN_MEAL_ALLERGENS = "COLUMN_MEAL_ALLERGENS";
    public static final String COLUMN_MEAL_PRICE = "COLUMN_MEAL_PRICE";
    public static final String COLUMN_MEAL_DESCRIPTION = "COLUMN_MEAL_DESCRIPTION";
    public static final String COLUMN_MEAL_IS_FEATURED = "COLUMN_MEAL_IS_FEATURED";
    public static final String COLUMN_MEAL_COOK = "COLUMN_MEAL_COOK";

    public static final String PURCHASE_TABLE = "PURCHASE_TABLE";
    public static final String COLUMN_PURCHASE_ID = "COLUMN_PURCHASE_ID";
    public static final String COLUMN_PURCHASE_COOK = "COLUMN_PURCHASE_COOK";
    public static final String COLUMN_PURCHASE_CLIENT = "COLUMN_PURCHASE_CLIENT";
    public static final String COLUMN_PURCHASE_MEAL = "COLUMN_PURCHASE_MEAL";
    public static final String COLUMN_PURCHASE_STATUS = "COLUMN_PURCHASE_STATUS";
    public static final String COLUMN_PURCHASE_QUANTITY = "COLUMN_PURCHASE_QUANTITY";
    public static final String COLUMN_PURCHASE_SUBTOTAL = "COLUMN_PURCHASE_SUBTOTAL";
    public static final String COLUMN_PURCHASE_UNIT_PRICE = "COLUMN_PURCHASE_UNIT_PRICE";

    public Database(@Nullable Context context) {
        super(context, "mealer.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement;

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

        createTableStatement = "CREATE TABLE " + REVIEW_TABLE + " (" +
                COLUMN_REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_REVIEW_TITLE + " TEXT, " +
                COLUMN_REVIEW_TEXT + " TEXT, " +
                COLUMN_REVIEW_RATING + " INTEGER, " +
                COLUMN_REVIEW_DAYS_SUSPENDED + " INTEGER, " +
                COLUMN_REVIEW_CLIENT + " TEXT, " +
                COLUMN_REVIEW_COOK + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_REVIEW_CLIENT + ") " +
                    "REFERENCES " + CLIENT_TABLE + " (" + COLUMN_CLIENT_USERNAME + "), " +
                "FOREIGN KEY (" + COLUMN_REVIEW_COOK + ")" +
                    "REFERENCES " + COOK_TABLE + "(" + COLUMN_COOK_USERNAME + "))";

        db.execSQL(createTableStatement);

        createTableStatement = "CREATE TABLE " + MEAL_TABLE + " ( " +
                COLUMN_MEAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MEAL_NAME + " TEXT, " +
                COLUMN_MEAL_COURSE + " TEXT, " +
                COLUMN_MEAL_CUISINE + " TEXT, " +
                COLUMN_MEAL_INGREDIENTS + " TEXT, " +
                COLUMN_MEAL_ALLERGENS + " TEXT, " +
                COLUMN_MEAL_PRICE + " FLOAT, " +
                COLUMN_MEAL_DESCRIPTION + " TEXT, " +
                COLUMN_MEAL_IS_FEATURED + " BOOL," +
                COLUMN_MEAL_COOK + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_MEAL_COOK + ") " +
                    "REFERENCES " + COOK_TABLE + " (" + COLUMN_COOK_USERNAME + "))";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int version = oldVersion + 1; version <= newVersion; version++) {
            String alterTableStatement, createTableStatement;
            switch (version) {
                case 2:
                    alterTableStatement = "ALTER TABLE COMPLAINT_TABLE RENAME COLUMN COLUMN_COMPLAINT_ID to " + COLUMN_REVIEW_ID;
                    db.execSQL(alterTableStatement);
                    alterTableStatement = "ALTER TABLE COMPLAINT_TABLE RENAME COLUMN COLUMN_COMPLAINT_TITLE to " + COLUMN_REVIEW_TITLE;
                    db.execSQL(alterTableStatement);
                    alterTableStatement = "ALTER TABLE COMPLAINT_TABLE RENAME COLUMN COLUMN_COMPLAINT_TEXT to " + COLUMN_REVIEW_TEXT;
                    db.execSQL(alterTableStatement);
                    alterTableStatement = "ALTER TABLE COMPLAINT_TABLE RENAME COLUMN COLUMN_COMPLAINT_RATING to " + COLUMN_REVIEW_RATING;
                    db.execSQL(alterTableStatement);
                    alterTableStatement = "ALTER TABLE COMPLAINT_TABLE RENAME COLUMN COLUMN_COMPLAINT_DAYS_SUSPENDED to " + COLUMN_REVIEW_DAYS_SUSPENDED;
                    db.execSQL(alterTableStatement);
                    alterTableStatement = "ALTER TABLE COMPLAINT_TABLE RENAME COLUMN COLUMN_COMPLAINT_CLIENT to " + COLUMN_REVIEW_CLIENT;
                    db.execSQL(alterTableStatement);
                    alterTableStatement = "ALTER TABLE COMPLAINT_TABLE RENAME COLUMN COLUMN_COMPLAINT_COOK to " + COLUMN_REVIEW_COOK;
                    db.execSQL(alterTableStatement);
                    alterTableStatement = "ALTER TABLE COMPLAINT_TABLE RENAME TO " + REVIEW_TABLE;
                    db.execSQL(alterTableStatement);
                case 3:
                    createTableStatement = "CREATE TABLE " + PURCHASE_TABLE + " ( " +
                            COLUMN_PURCHASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            COLUMN_PURCHASE_COOK + " TEXT, " +
                            COLUMN_PURCHASE_CLIENT + " TEXT, " +
                            COLUMN_PURCHASE_MEAL + " TEXT, " +
                            COLUMN_PURCHASE_STATUS + " INTEGER, " +
                            COLUMN_PURCHASE_QUANTITY + " INTEGER, " +
                            COLUMN_PURCHASE_SUBTOTAL + " FLOAT, " +
                            "FOREIGN KEY (" + COLUMN_PURCHASE_COOK + ") " +
                                "REFERENCES " + COOK_TABLE + " (" + COLUMN_COOK_USERNAME + "), " +
                            "FOREIGN KEY (" + COLUMN_PURCHASE_CLIENT + ") " +
                                "REFERENCES " + CLIENT_TABLE + " (" + COLUMN_CLIENT_USERNAME + "))";
                    db.execSQL(createTableStatement);
                case 4:
                    alterTableStatement = "ALTER TABLE " + PURCHASE_TABLE + " ADD COLUMN " +
                            COLUMN_PURCHASE_UNIT_PRICE + " INTEGER";
                    db.execSQL(alterTableStatement);
            }
        }
    }

    public boolean addOne(Admin admin) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ADMIN_USERNAME, admin.getUsername());
        cv.put(COLUMN_ADMIN_PASSWORD, admin.getPassword());

        long insert = db.insert(ADMIN_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();
        return true;
    }

    public boolean addOne(Client client) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        // getting client address information
        cv.put(COLUMN_ADDRESS_STREET, client.getPaymentInfo().getBillingAddress().getStreet());
        cv.put(COLUMN_ADDRESS_STREET_NUMBER, client.getPaymentInfo().getBillingAddress().getNumber());
        cv.put(COLUMN_ADDRESS_POSTAL_CODE, client.getPaymentInfo().getBillingAddress().getPostal());
        cv.put(COLUMN_ADDRESS_CITY, client.getPaymentInfo().getBillingAddress().getCity());

        // inserting into the database
        long insert = db.insert(ADDRESS_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        // clearing content values
        cv.clear();

        //getting payment info address
        cv.put(COLUMN_PAYMENT_INFO_CARDHOLDER_NAME, client.getPaymentInfo().getCardHolderName());
        cv.put(COLUMN_PAYMENT_INFO_CARD_NUMBER, client.getPaymentInfo().getCardNumber().longValue());
        cv.put(COLUMN_PAYMENT_INFO_CVV, client.getPaymentInfo().getCvv());
        cv.put(COLUMN_PAYMENT_INFO_ADDRESS_ID, getAddressCount());

        insert = db.insert(PAYMENT_INFO_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();

        cv.put(COLUMN_ADDRESS_STREET, client.getAddress().getStreet());
        cv.put(COLUMN_ADDRESS_STREET_NUMBER, client.getAddress().getNumber());
        cv.put(COLUMN_ADDRESS_POSTAL_CODE, client.getAddress().getPostal());
        cv.put(COLUMN_ADDRESS_CITY, client.getAddress().getCity());

        insert = db.insert(ADDRESS_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();

        cv.put(COLUMN_CLIENT_USERNAME, client.getUsername());
        cv.put(COLUMN_CLIENT_PASSWORD, client.getPassword());
        cv.put(COLUMN_CLIENT_FIRSTNAME, client.getfName());
        cv.put(COLUMN_CLIENT_LASTNAME, client.getlName());
        cv.put(COLUMN_CLIENT_EMAIL, client.getEmail());
        cv.put(COLUMN_CLIENT_ADDRESS_ID, getAddressCount());
        cv.put(COLUMN_CLIENT_PAYMENT_INFO_ID, getPaymentInfoCount());

        insert = db.insert(CLIENT_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }
        cv.clear();
        return true;
    }

    public boolean addOne(Cook cook) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ADDRESS_STREET, cook.getAddress().getStreet());
        cv.put(COLUMN_ADDRESS_STREET_NUMBER, cook.getAddress().getNumber());
        cv.put(COLUMN_ADDRESS_POSTAL_CODE, cook.getAddress().getPostal());
        cv.put(COLUMN_ADDRESS_CITY, cook.getAddress().getCity());

        long insert = db.insert(ADDRESS_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();

        cv.put(COLUMN_COOK_USERNAME, cook.getUsername());
        cv.put(COLUMN_COOK_PASSWORD, cook.getPassword());
        cv.put(COLUMN_COOK_FIRSTNAME, cook.getfName());
        cv.put(COLUMN_COOK_LASTNAME, cook.getlName());
        cv.put(COLUMN_COOK_EMAIL, cook.getEmail());
        cv.put(COLUMN_COOK_BIO, cook.getBio());
        cv.put(COLUMN_COOK_CHEQUE, cook.hasVoidCheque());
        cv.put(COLUMN_COOK_SUSPENSION_LENGTH, cook.getSuspensionLength());
        cv.put(COLUMN_COOK_ADDRESS_ID, getAddressCount());

        insert = db.insert(COOK_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();
        return true;
    }

    public boolean addOne(Review review) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_REVIEW_TITLE, review.getTitle());
        cv.put(COLUMN_REVIEW_TEXT, review.getMessage());
        cv.put(COLUMN_REVIEW_RATING, review.getRating());
        cv.put(COLUMN_REVIEW_DAYS_SUSPENDED, review.getDaysSuspended());
        cv.put(COLUMN_REVIEW_CLIENT, review.getClientUsername());
        cv.put(COLUMN_REVIEW_COOK, review.getCookUsername());
        long insert = db.insert(REVIEW_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }
        cv.clear();

        return true;
    }

    public boolean addOne(Meal meal) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_MEAL_NAME, meal.getMealName());
        cv.put(COLUMN_MEAL_COURSE, meal.getMealCourse().toString());
        cv.put(COLUMN_MEAL_CUISINE, meal.getMealCuisine().toString());
        cv.put(COLUMN_MEAL_INGREDIENTS, meal.getListOfIngredients());
        cv.put(COLUMN_MEAL_ALLERGENS, meal.getListOfAllergens());
        cv.put(COLUMN_MEAL_PRICE, meal.getMealPrice());
        cv.put(COLUMN_MEAL_DESCRIPTION, meal.getMealDescription());
        cv.put(COLUMN_MEAL_IS_FEATURED, meal.isFeatured());
        cv.put(COLUMN_MEAL_COOK, meal.getAssociatedCook().getUsername());

        long insert = db.insert(MEAL_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();
        return true;
    }

    public boolean addOne(Purchase p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PURCHASE_COOK, p.getChef());
        cv.put(COLUMN_PURCHASE_CLIENT, p.getCustomer());
        cv.put(COLUMN_PURCHASE_MEAL, p.getMealName());
        cv.put(COLUMN_PURCHASE_STATUS, 0);
        cv.put(COLUMN_PURCHASE_QUANTITY, p.getQuantity());
        cv.put(COLUMN_PURCHASE_SUBTOTAL, p.getSubtotal());

        long insert = db.insert(PURCHASE_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();
        return true;
    }

    public List<Meal> getMealsOfCook(String cookUsername) {
        List<Meal> meals = new ArrayList<>();
        List<Cook> cooks = getCooks();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + MEAL_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                if(cursor.getString(9).equals(cookUsername)) {
                    int mealID = cursor.getInt(0);
                    String mealName = cursor.getString(1);
                    String mealCourse = cursor.getString(2);
                    String mealCuisine = cursor.getString(3);
                    String mealIngredients = cursor.getString(4);
                    String mealAllergens = cursor.getString(5);
                    float mealPrice = cursor.getFloat(6);
                    String mealDescription = cursor.getString(7);
                    boolean isFeatured = (cursor.getInt(8) == 1);

                    for(Cook cook: cooks) {
                        if(cook.getUsername().equals(cookUsername)) {
                            Meal meal = new Meal(mealName, mealCourse, mealCuisine, mealIngredients, mealAllergens, mealPrice, mealDescription, cook);

                            meal.setFeatured(isFeatured);
                            meal.setDB_id(mealID);
                            meals.add(meal);
                        }
                    }
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        return meals;
    }

    public List<Meal> getAllMeals() {
        List<Meal> meals = new ArrayList<>();
        List<Cook> cooks = getCooks();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + MEAL_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int mealID = cursor.getInt(0);
                String mealName = cursor.getString(1);
                String mealCourse = cursor.getString(2);
                String mealCuisine = cursor.getString(3);
                String mealIngredients = cursor.getString(4);
                String mealAllergens = cursor.getString(5);
                float mealPrice = cursor.getFloat(6);
                String mealDescription = cursor.getString(7);
                boolean isFeatured = (cursor.getInt(8) == 1);
                String cookUsername = cursor.getString(9);

                for(Cook cook: cooks) {
                    if(cook.getUsername().equals(cookUsername)) {
                        Meal meal = new Meal(mealName, mealCourse, mealCuisine, mealIngredients, mealAllergens, mealPrice, mealDescription, cook);

                        meal.setFeatured(isFeatured);
                        meal.setDB_id(mealID);
                        meals.add(meal);
                    }
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        return meals;
    }

    public List<Review> getPositiveReviews() {
        List<Review> reviews = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + REVIEW_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int reviewRating = cursor.getInt(3);
                String reviewCook = cursor.getString(6);
                if(reviewRating >= 3) {
                    int reviewID = cursor.getInt(0);
                    String reviewTitle = cursor.getString(1);
                    String reviewText = cursor.getString(2);

                    String reviewClient = cursor.getString(5);


                    Review review = new Review(reviewTitle, reviewText, reviewClient, reviewCook, reviewRating);
                    review.setDB_id(reviewID);
                    reviews.add(review);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        return reviews;
    }

    public List<Review> getComplaints() {

        List<Review> complaints = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + REVIEW_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int complaintRating = cursor.getInt(3);
                if(complaintRating < 3) {
                    int complaintId = cursor.getInt(0);
                    String complaintTitle = cursor.getString(1);
                    String complaintText = cursor.getString(2);

                    int complaintSuspension = cursor.getInt(4);
                    String complaintClient = cursor.getString(5);
                    String complaintCook = cursor.getString(6);

                    Review complaint = new Review(complaintTitle, complaintText, complaintClient, complaintCook, complaintRating);
                    if(complaintSuspension != -1) {
                        complaint.setDaysSuspended(complaintSuspension);
                    }
                    complaint.setDB_id(complaintId);
                    complaints.add(complaint);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        return complaints;
    }

    public List<Review> getCookReviews(String cookUsername) {
        List<Review> complaints = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + REVIEW_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                String complaintCook = cursor.getString(6);
                if(cookUsername.equals(complaintCook)) {
                    int complaintId = cursor.getInt(0);
                    String complaintTitle = cursor.getString(1);
                    String complaintText = cursor.getString(2);
                    int complaintRating = cursor.getInt(3);
                    int complaintSuspension = cursor.getInt(4);
                    String complaintClient = cursor.getString(5);


                    Review complaint = new Review(complaintTitle, complaintText, complaintClient, complaintCook, complaintRating);
                    if(complaintSuspension != -1) {
                        complaint.setDaysSuspended(complaintSuspension);
                    }
                    complaint.setDB_id(complaintId);
                    complaints.add(complaint);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        return complaints;
    }

    public List<Address> getAddresses() {
        List<Address> addresses = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + ADDRESS_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                int addressId = cursor.getInt(0);
                String addressStreet = cursor.getString(1);
                int addressNumber = cursor.getInt(2);
                String addressPostal = cursor.getString(3);
                String addressCity = cursor.getString(4);

                Address address = new Address(addressStreet, addressNumber, addressPostal, addressCity);
                address.setDB_id(addressId);
                addresses.add(address);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return addresses;
    }

    public List<PaymentInfo> getAllPaymentInfo() {
        List<PaymentInfo> rows = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + PAYMENT_INFO_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        List<Address> addresses = getAddresses();

        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String cardholderName = cursor.getString(1);
                BigInteger cardNumber = BigInteger.valueOf(cursor.getLong(2));
                int cvv = cursor.getInt(3);
                int addressPosition = cursor.getInt(4);

                Address address = addresses.get(addressPosition);
                PaymentInfo p = new PaymentInfo(cardholderName, cardNumber, cvv, address);
                p.setDB_id(id);

                rows.add(p);
            } while(cursor.moveToNext());
        }

        cursor.close();
        return rows;
    }

    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + CLIENT_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        List<Address> addresses = getAddresses();
        List<PaymentInfo> paymentInfoRows = getAllPaymentInfo();

        if(cursor.moveToFirst()) {
            do {
                String username = cursor.getString(0);
                String password = cursor.getString(1);
                String fname = cursor.getString(2);
                String lname = cursor.getString(3);
                String email = cursor.getString(4);
                int addressPosition = cursor.getInt(5);
                int paymentInfoPosition = cursor.getInt(6);

                Address address = addresses.get(addressPosition-1);
                PaymentInfo paymentInfo = paymentInfoRows.get(paymentInfoPosition-1);

                Client client = new Client(paymentInfo, fname, lname, email, address, username, password);
                clients.add(client);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return clients;
    }

    public List<Cook> getCooks() {

        List<Cook> cooks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + COOK_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        List<Address> addresses = getAddresses();

        if(cursor.moveToFirst()) {
            do {
                String cookUsername = cursor.getString(0);
                String cookPassword = cursor.getString(1);
                String cookFname = cursor.getString(2);
                String cookLname = cursor.getString(3);
                String cookEmail = cursor.getString(4);
                String cookBio = cursor.getString(5);
                int cookSuspension = cursor.getInt(7);
                Address cookAddress = addresses.get(cursor.getInt(8)-1);

                double rating = getCookRating(cookUsername);

                Cook cook = new Cook(cookBio, true, cookFname, cookLname, cookEmail, cookAddress, cookUsername, cookPassword);
                cook.setSuspension(cookSuspension);
                cook.setRating(rating);
                cooks.add(cook);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return cooks;
    }

    public List<Purchase> getSales(String cookUsername) {
        List<Purchase> sales = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + PURCHASE_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {

                String cook = cursor.getString(1);

                if(cook.equals(cookUsername)) {
                    int saleID = cursor.getInt(0);
                    String client = cursor.getString(2);
                    String meal = cursor.getString(3);
                    int status = cursor.getInt(4);
                    int quantity = cursor.getInt(5);
                    double subtotal = cursor.getDouble(6);
                    double unitPrice = cursor.getDouble(7);

                    Purchase sale = new Purchase(cook, client, meal, quantity, unitPrice);
                    sale.setSubtotal(subtotal);
                    sale.setId(saleID);
                    switch(status) {
                        case -1:
                            sale.rejectPurchase();
                        case 1:
                            sale.approvePurchase();
                    }
                    sales.add(sale);
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        return sales;
    }

    public List<Purchase> getPurchases(String clientUsername) {
        List<Purchase> purchases = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + PURCHASE_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {

                String cook = cursor.getString(1);

                if(cook.equals(clientUsername)) {
                    int saleID = cursor.getInt(0);
                    String client = cursor.getString(2);
                    String meal = cursor.getString(3);
                    int status = cursor.getInt(4);
                    int quantity = cursor.getInt(5);
                    double subtotal = cursor.getDouble(6);
                    double unitPrice = cursor.getDouble(7);

                    Purchase purchase = new Purchase(cook, client, meal, quantity, unitPrice);
                    purchase.setSubtotal(subtotal);
                    purchase.setId(saleID);
                    switch(status) {
                        case -1:
                            purchase.rejectPurchase();
                        case 1:
                            purchase.approvePurchase();
                    }
                    purchases.add(purchase);
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        return purchases;
    }

    public boolean approvePurchase(Purchase p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PURCHASE_COOK, p.getChef());
        cv.put(COLUMN_PURCHASE_CLIENT, p.getCustomer());
        cv.put(COLUMN_PURCHASE_MEAL, p.getMealName());
        cv.put(COLUMN_PURCHASE_STATUS, 1);
        cv.put(COLUMN_PURCHASE_QUANTITY, p.getQuantity());
        cv.put(COLUMN_PURCHASE_SUBTOTAL, p.getSubtotal());


        long insert = db.update(PURCHASE_TABLE, cv, COLUMN_PURCHASE_ID + "=" + p.getId(), null);
        if(insert == -1) {
            return false;
        }

        cv.clear();
        return true;
    }

    public boolean rejectPurchase(Purchase p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PURCHASE_COOK, p.getChef());
        cv.put(COLUMN_PURCHASE_CLIENT, p.getCustomer());
        cv.put(COLUMN_PURCHASE_MEAL, p.getMealName());
        cv.put(COLUMN_PURCHASE_STATUS, -1);
        cv.put(COLUMN_PURCHASE_QUANTITY, p.getQuantity());
        cv.put(COLUMN_PURCHASE_SUBTOTAL, p.getSubtotal());


        long insert = db.update(PURCHASE_TABLE, cv, COLUMN_PURCHASE_ID + "=" + p.getId(), null);
        if(insert == -1) {
            return false;
        }

        cv.clear();
        return true;
    }

    public boolean featureMeal(Meal meal, boolean featured) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_MEAL_NAME, meal.getMealName());
        cv.put(COLUMN_MEAL_COURSE, meal.getMealCourse());
        cv.put(COLUMN_MEAL_CUISINE, meal.getMealCuisine());
        cv.put(COLUMN_MEAL_INGREDIENTS, meal.getListOfIngredients());
        cv.put(COLUMN_MEAL_ALLERGENS, meal.getListOfAllergens());
        cv.put(COLUMN_MEAL_PRICE, meal.getMealPrice());
        cv.put(COLUMN_MEAL_DESCRIPTION, meal.getMealDescription());
        cv.put(COLUMN_MEAL_IS_FEATURED, featured);
        cv.put(COLUMN_MEAL_COOK, meal.getAssociatedCook().getUsername());

        long insert = db.update(MEAL_TABLE, cv, COLUMN_MEAL_ID + "=" + meal.getDB_id(), null);
        if(insert == -1) {
            return false;
        }

        cv.clear();
        return true;
    }

    public boolean deleteMeal(Meal meal) {
        if(meal.isFeatured()) {
            return false;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        List<Meal> meals = getMealsOfCook(meal.getAssociatedCook().getUsername());
        int id = -1;
        for(int i = 0; i < meals.toArray().length; i++) {
            if(meal.equals(meals.get(i))) {
                id = i + 1;
            }
        }

        long delete = db.delete(MEAL_TABLE, COLUMN_MEAL_ID + "=" + id, null);
        return delete != -1;
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

        if(cursor.getCount() <= 0) {
            Query = " Select * from " + ADMIN_TABLE + " where " + COLUMN_ADMIN_USERNAME + " = " + "'" + username + "'";
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
        } else if(checkAdminExists(username)) {
            Query = " Select * from " + ADMIN_TABLE + " where " + COLUMN_ADMIN_PASSWORD + " = " + "'" + password + "'";
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

    private long getAddressCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, ADDRESS_TABLE);
    }

    private long getPaymentInfoCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, PAYMENT_INFO_TABLE);
    }

    public boolean checkAdminExists(String username) {
        if(!(checkUsernameExists(username))) {
            return false;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        String Query = " Select * from " + ADMIN_TABLE + " where " + COLUMN_ADMIN_USERNAME + " = " + "'" + username + "'";
        boolean exists;
        Cursor cursor = db.rawQuery(Query, null);

        exists = (cursor.getCount() > 0);

        cursor.close();
        return exists;
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
        String Query = " Select * from " + CLIENT_TABLE + " where " + COLUMN_CLIENT_USERNAME + " = " + "\"" + username + "\"";
        boolean exists;
        Cursor cursor = db.rawQuery(Query, null);

        exists = (cursor.getCount() > 0);

        cursor.close();
        return exists;
    }

    public int getNumComplaints() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = " Select * from " + REVIEW_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        return cursor.getCount();
    }

    public boolean updateSuspension(Review review, int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_REVIEW_TITLE, review.getTitle());
        cv.put(COLUMN_REVIEW_TEXT, review.getMessage());
        cv.put(COLUMN_REVIEW_RATING, review.getRating());
        cv.put(COLUMN_REVIEW_DAYS_SUSPENDED, review.getDaysSuspended());
        cv.put(COLUMN_REVIEW_CLIENT, review.getClientUsername());
        cv.put(COLUMN_REVIEW_COOK, review.getCookUsername());

        long insert = db.update(REVIEW_TABLE, cv, COLUMN_REVIEW_ID + "=" + id, null);
        if(insert == -1) {
            return false;
        }

        cv.clear();

        List<Cook> cooks = getCooks();
        boolean found = false;
        for(Cook cook : cooks) {
            if(cook.getUsername().equals(review.getCookUsername())) {
                found = true;
                cv.put(COLUMN_COOK_USERNAME, cook.getUsername());
                cv.put(COLUMN_COOK_PASSWORD, cook.getPassword());
                cv.put(COLUMN_COOK_FIRSTNAME, cook.getfName());
                cv.put(COLUMN_COOK_LASTNAME, cook.getlName());
                cv.put(COLUMN_COOK_EMAIL, cook.getEmail());
                cv.put(COLUMN_COOK_BIO, cook.getBio());
                cv.put(COLUMN_COOK_CHEQUE, true);
                cv.put(COLUMN_COOK_SUSPENSION_LENGTH, review.getDaysSuspended());
                cv.put(COLUMN_COOK_ADDRESS_ID, cook.getAddress().getDB_id());

                insert = db.update(COOK_TABLE, cv, COLUMN_COOK_USERNAME + "='" + cook.getUsername() + "'", null);
                if(insert == -1) {
                    return false;
                }
                cv.clear();
                break;
            }
        }
        return found;
    }

    public double getCookRating(String cookUsername) {
        double rating = 0.0;

        List<Review> reviews = getCookReviews(cookUsername);
        if(reviews.isEmpty()) {
            return rating;
        }

        for(Review review : reviews) {
            rating += review.getRating();
        }

        rating /= reviews.size();

        BigDecimal bd = BigDecimal.valueOf(rating);
        rating = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();

        return rating;
    }

}
