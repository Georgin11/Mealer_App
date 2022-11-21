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
import com.example.Mealer_App.structure.Complaint;
import com.example.Mealer_App.structure.Cook;
import com.example.Mealer_App.structure.Meal;

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

    public static final String COMPLAINT_TABLE = "COMPLAINT_TABLE";
    public static final String COLUMN_COMPLAINT_ID = "COLUMN_COMPLAINT_ID";
    public static final String COLUMN_COMPLAINT_TITLE = "COLUMN_COMPLAINT_TITLE";
    public static final String COLUMN_COMPLAINT_TEXT = "COLUMN_COMPLAINT_TEXT";
    public static final String COLUMN_COMPLAINT_RATING = "COLUMN_COMPLAINT_RATING";
    public static final String COLUMN_COMPLAINT_DAYS_SUSPENDED = "COLUMN_COMPLAINT_DAYS_SUSPENDED";
    public static final String COLUMN_COMPLAINT_CLIENT = "COLUMN_COMPLAINT_CLIENT";
    public static final String COLUMN_COMPLAINT_COOK = "COLUMN_COMPLAINT_COOK";

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

    public Database(@Nullable Context context) {
        super(context, "mealer.db", null, 1);
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

        createTableStatement = "CREATE TABLE " + COMPLAINT_TABLE + " (" +
                COLUMN_COMPLAINT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_COMPLAINT_TITLE + " TEXT, " +
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

    public boolean addOne(Complaint complaint) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_COMPLAINT_TITLE, complaint.getTitle());
        cv.put(COLUMN_COMPLAINT_TEXT, complaint.getMessage());
        cv.put(COLUMN_COMPLAINT_RATING, complaint.getRating());
        cv.put(COLUMN_COMPLAINT_DAYS_SUSPENDED, complaint.getDaysSuspended());
        cv.put(COLUMN_COMPLAINT_CLIENT, complaint.getClientUsername());
        cv.put(COLUMN_COMPLAINT_COOK, complaint.getCookUsername());
        long insert = db.insert(COMPLAINT_TABLE, null, cv);
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
        cv.put(COLUMN_MEAL_INGREDIENTS, meal.getMealIngredients());
        cv.put(COLUMN_MEAL_ALLERGENS, meal.getMealAllergens());
        cv.put(COLUMN_MEAL_PRICE, meal.getMealPrice());
        cv.put(COLUMN_MEAL_DESCRIPTION, meal.getMealDescription());
        cv.put(COLUMN_MEAL_IS_FEATURED, meal.isFeatured());
        cv.put(COLUMN_MEAL_COOK, meal.getAssociatedCook());

        long insert = db.insert(MEAL_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }

        cv.clear();
        return true;
    }

    public List<Meal> getMealsOfCook(String cookUsername) {
        List<Meal> meals = new ArrayList<>();
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

                    Meal meal = new Meal(mealName, mealCourse, mealCuisine, mealIngredients, mealAllergens, mealPrice, mealDescription, cookUsername);

                    meal.setFeatured(isFeatured);
                    meal.setDB_id(mealID);
                    meals.add(meal);
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        return meals;
    }

    public boolean featureMeal(Meal meal, boolean featured) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_MEAL_NAME, meal.getMealName());
        cv.put(COLUMN_MEAL_COURSE, meal.getMealCourse());
        cv.put(COLUMN_MEAL_CUISINE, meal.getMealCuisine());
        cv.put(COLUMN_MEAL_INGREDIENTS, meal.getMealIngredients());
        cv.put(COLUMN_MEAL_ALLERGENS, meal.getMealAllergens());
        cv.put(COLUMN_MEAL_PRICE, meal.getMealPrice());
        cv.put(COLUMN_MEAL_DESCRIPTION, meal.getMealDescription());
        cv.put(COLUMN_MEAL_IS_FEATURED, featured);
        cv.put(COLUMN_MEAL_COOK, meal.getAssociatedCook());

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

        List<Meal> meals = getMealsOfCook(meal.getAssociatedCook());
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

    public long getAddressCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, ADDRESS_TABLE);
    }

    public long getPaymentInfoCount() {
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

        String query = " Select * from " + COMPLAINT_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        return cursor.getCount();
    }

    public List<Complaint> getComplaints() {

        List<Complaint> complaints = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + COMPLAINT_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
             do {
                 int complaintId = cursor.getInt(0);
                 String complaintTitle = cursor.getString(1);
                 String complaintText = cursor.getString(2);
                 int complaintRating = cursor.getInt(3);
                 int complaintSuspension = cursor.getInt(4);
                 String complaintClient = cursor.getString(5);
                 String complaintCook = cursor.getString(6);

                 Complaint complaint = new Complaint(complaintTitle, complaintText, complaintClient, complaintCook, complaintRating);
                 if(complaintSuspension != -1) {
                     complaint.setDaysSuspended(complaintSuspension);
                 }
                 complaint.setDB_id(complaintId);
                 complaints.add(complaint);

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

                Cook cook = new Cook(cookBio, true, cookFname, cookLname, cookEmail, cookAddress, cookUsername, cookPassword);
                cook.setSuspension(cookSuspension);
                cooks.add(cook);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return cooks;
    }

    public boolean updateSuspension(Complaint complaint, int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_COMPLAINT_TITLE, complaint.getTitle());
        cv.put(COLUMN_COMPLAINT_TEXT, complaint.getMessage());
        cv.put(COLUMN_COMPLAINT_RATING, complaint.getRating());
        cv.put(COLUMN_COMPLAINT_DAYS_SUSPENDED, complaint.getDaysSuspended());
        cv.put(COLUMN_COMPLAINT_CLIENT, complaint.getClientUsername());
        cv.put(COLUMN_COMPLAINT_COOK, complaint.getCookUsername());

        long insert = db.update(COMPLAINT_TABLE, cv, COLUMN_COMPLAINT_ID + "=" + id, null);
        if(insert == -1) {
            return false;
        }

        cv.clear();

        List<Cook> cooks = getCooks();
        boolean found = false;
        for(Cook cook : cooks) {
            if(cook.getUsername().equals(complaint.getCookUsername())) {
                found = true;
                cv.put(COLUMN_COOK_USERNAME, cook.getUsername());
                cv.put(COLUMN_COOK_PASSWORD, cook.getPassword());
                cv.put(COLUMN_COOK_FIRSTNAME, cook.getfName());
                cv.put(COLUMN_COOK_LASTNAME, cook.getlName());
                cv.put(COLUMN_COOK_EMAIL, cook.getEmail());
                cv.put(COLUMN_COOK_BIO, cook.getBio());
                cv.put(COLUMN_COOK_CHEQUE, true);
                cv.put(COLUMN_COOK_SUSPENSION_LENGTH, complaint.getDaysSuspended());
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

}
