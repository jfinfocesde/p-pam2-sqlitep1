package com.example.sqlitep1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "data.db";
    private static final int DATABASE_VERSION = 2;

    // User table
    private static final String TABLE_USER = "User";
    private static final String COLUMN_USER_ID = "_id";
    private static final String COLUMN_USER_NAME = "name";
    private static final String COLUMN_USER_AGE = "age";

    // Product table
    private static final String TABLE_PRODUCT = "Product";
    private static final String COLUMN_PRODUCT_ID = "_id";
    private static final String COLUMN_PRODUCT_NAME = "name";
    private static final String COLUMN_PRODUCT_PRICE = "price";
    private static final String COLUMN_PRODUCT_QUANTITY = "quantity";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_USER_NAME + " TEXT," +
                COLUMN_USER_AGE + " INTEGER)";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "(" +
                COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PRODUCT_NAME + " TEXT," +
                COLUMN_PRODUCT_PRICE + " REAL," +
                COLUMN_PRODUCT_QUANTITY + " INTEGER)";
        db.execSQL(CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        onCreate(db);
    }

    // User CRUD Operations:

    // Create (Insert) User
    public void createUser(String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_AGE, age);
        db.insert(TABLE_USER, null, values);
        Log.d("DatabaseHelper", "User created successfully.");
        db.close();
    }

    // Read (Select) Users
    public List<String> readUsers() {
        List<String> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int nameIndex = cursor.getColumnIndex(COLUMN_USER_NAME);
                int ageIndex = cursor.getColumnIndex(COLUMN_USER_AGE);

                String name = nameIndex != -1 ? cursor.getString(nameIndex) : "N/A";
                int age = ageIndex != -1 ? cursor.getInt(ageIndex) : -1;

                users.add("Name: " + name + ", Age: " + age);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return users;
    }

    // Update User
    public void updateUser(int id, String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_AGE, age);
        db.update(TABLE_USER, values, COLUMN_USER_ID + "=?", new String[]{String.valueOf(id)});
        Log.d("DatabaseHelper", "User updated successfully.");
        db.close();
    }

    // Delete User
    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, COLUMN_USER_ID + "=?", new String[]{String.valueOf(id)});
        Log.d("DatabaseHelper", "User deleted successfully.");
        db.close();
    }

    // Product CRUD Operations:

    // Create (Insert) Product
    public void createProduct(String name, double price, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, name);
        values.put(COLUMN_PRODUCT_PRICE, price);
        values.put(COLUMN_PRODUCT_QUANTITY, quantity);
        db.insert(TABLE_PRODUCT, null, values);
        Log.d("DatabaseHelper", "Product created successfully.");
        db.close();
    }

    // Read (Select) Products
    public List<String> readProducts() {
        List<String> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCT, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int nameIndex = cursor.getColumnIndex(COLUMN_PRODUCT_NAME);
                int priceIndex = cursor.getColumnIndex(COLUMN_PRODUCT_PRICE);
                int quantityIndex = cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY);

                String name = nameIndex != -1 ? cursor.getString(nameIndex) : "N/A";
                double price = priceIndex != -1 ? cursor.getDouble(priceIndex) : 0.0;
                int quantity = quantityIndex != -1 ? cursor.getInt(quantityIndex) : 0;

                products.add("Name: " + name + ", Price: " + price + ", Quantity: " + quantity);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return products;
    }

    // Update Product
    public void updateProduct(int id, String name, double price, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, name);
        values.put(COLUMN_PRODUCT_PRICE, price);
        values.put(COLUMN_PRODUCT_QUANTITY, quantity);
        db.update(TABLE_PRODUCT, values, COLUMN_PRODUCT_ID + "=?", new String[]{String.valueOf(id)});
        Log.d("DatabaseHelper", "Product updated successfully.");
        db.close();
    }

    // Delete Product
    public void deleteProduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCT, COLUMN_PRODUCT_ID + "=?", new String[]{String.valueOf(id)});
        Log.d("DatabaseHelper", "Product deleted successfully.");
        db.close();
    }
}
