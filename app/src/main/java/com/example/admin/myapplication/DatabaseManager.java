package com.example.admin.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_A;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_A1;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_AB;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_AB1;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_AGE;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_B;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_B1;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_BLOD_GROUP;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_GENDER;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_ID;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_MOBILE;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_NAME;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_O;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_O1;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_QUANTITY;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.TABLE2;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.TABLE3;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.TABLE_NAME;


//The class is extending SQLiteOpenHelper
public class DatabaseManager extends SQLiteOpenHelper {

    /*
     * This time we will not be using the hardcoded string values
     * Instead here we are defining all the Strings that is required for our database
     * for example databasename, table name and column names.
     * */
    private static final String DATABASE_NAME = "DonorDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = DatabaseManager.class.getName();
    private final String[] selectAr = {COLUMN_ID, COLUMN_NAME, COLUMN_GENDER, COLUMN_BLOD_GROUP, COLUMN_MOBILE, COLUMN_AGE, COLUMN_QUANTITY};
    String sql4 = "select  " + COLUMN_BLOD_GROUP + " ,sum( " + COLUMN_QUANTITY + "  )  from   " + TABLE_NAME + "  GROUP BY  " + COLUMN_BLOD_GROUP;
    String selectArg2[] = {COLUMN_QUANTITY};

    /*
     * We need to call the super i.e. parent class constructur
     * And we need to pass 4 parameters
     * 1. Context context -> It is the context object we will get it from the activity while creating the instance
     * 2. String databasename -> It is the name of the database and here we are passing the constant that we already define
     * 3. CursorFactory cursorFactory -> If we want a cursor to be initialized on the creation we can use cursor factory, it is doptionall and that is why we passed null here
     * 4. int version -> It is an int defining our database version
     * */
    DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*
         * The query to create our table
         * It is same as we had in the previous post
         * The only difference here is we have changed the
         * hardcoded string values with String Variables
         * */

        String sql = "CREATE TABLE " + TABLE_NAME + " (\n" + "    " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" + "    " + COLUMN_NAME + " varchar(200) NOT NULL,\n" + "    " + COLUMN_GENDER + " varchar(20) NOT NULL,\n" + "    " + COLUMN_BLOD_GROUP + " varchar(10) NOT NULL,\n" + "    " + COLUMN_MOBILE + " varchar(35) NOT NULL,\n" + "    " + COLUMN_AGE + " double NOT NULL,\n" + "    " + COLUMN_QUANTITY + "  double NOT NULL\n" + ")";
        Log.e("create table", "" + sql);
        String sql2 = "CREATE TABLE " + TABLE2 + " (\n" + "    " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" + "    " + COLUMN_BLOD_GROUP + " varchar(100) NOT NULL,\n" + COLUMN_QUANTITY + "  double NOT NULL\n" + ")";
//        String sql3 = "CREATE TABLE " + TABLE3 + " (\n" + "    " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" + "    " + COLUMN_A + " varchar(100) NOT NULL,\n" + "   " + COLUMN_B + " varchar(100) NOT NULL,\n" + "   " + COLUMN_AB + " varchar(100) NOT NULL,\n" + "  " + COLUMN_A1 + " varchar(100) NOT NULL,\n" + "  " + COLUMN_B1 + " varchar(100) NOT NULL,\n" + "  " + COLUMN_AB1 + " varchar(100) NOT NULL,\n" + "  " + COLUMN_O + " varchar(100) NOT NULL,\n" + "  " + COLUMN_O1 + " varchar(100) NOT NULL,\n" + COLUMN_QUANTITY + "  double NOT NULL\n" + ")";
        Log.e("create table2", "" + sql2 + sql4);
        /*
         * Executing the string to create the table
         * */
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /*
         * We are doing nothing here
         * Just dropping and creating the table
         * */
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + " ";
        sqLiteDatabase.execSQL(sql);

        onCreate(sqLiteDatabase);
        String sql2 = "DROP TABLE IF EXISTS " + TABLE2 + " ";
        sqLiteDatabase.execSQL(sql2);

    }

    /*
     * CREATE OPERATION
     * ====================
     * This is the first operation of the CRUD.
     * This method will create a new employee in the table
     * Method is taking all the parameters required
     *
     * Operation is very simple, we just need a content value objects
     * Inside this object we will put everything that we want to insert.
     * So each value will take the column name and the value that is to inserted
     * for the column name we are using the String variables that we defined already
     * And that is why we converted the hardcoded string to variables
     *
     * Once we have the contentValues object with all the values required
     * We will call the method getWritableDatabase() and it will return us the SQLiteDatabase object and we can write on the database using it.
     *
     * With this object we will call the insert method it takes 3 parameters
     * 1. String -> The table name where the value is to be inserted
     * 2. String -> The default values of null columns, it is null here as we don't have any default values
     * 3. ContentValues -> The values that is to be inserted
     *
     * insert() will return the inserted row id, if there is some error inserting the row
     * it will return -1
     *
     * So here we are returning != -1, it will be true of record is inserted and false if not inserted
     * */

    boolean addEmployee(String name, String gender, String bloodGroup, String mobileNumber, double age, String Quantity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_GENDER, gender);
        contentValues.put(COLUMN_BLOD_GROUP, bloodGroup);
        contentValues.put(COLUMN_MOBILE, mobileNumber);
        contentValues.put(COLUMN_AGE, age);
        contentValues.put(COLUMN_QUANTITY, Quantity);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME, null, contentValues) != -1;
    }

    public void selectQuery(SQLiteDatabase db) {


        String SELECT_SQL = " SELECT DISTINCT  " + COLUMN_ID + COLUMN_NAME + COLUMN_GENDER + COLUMN_BLOD_GROUP + COLUMN_MOBILE + COLUMN_AGE + COLUMN_QUANTITY + " FROM " + TABLE_NAME;
        Cursor c = db.rawQuery(SELECT_SQL, selectAr);
        c.moveToNext();
        int id = c.getInt(c.getColumnIndex(COLUMN_ID));
        String name = c.getString(c.getColumnIndex(COLUMN_NAME));
        String gender = c.getString(c.getColumnIndex(COLUMN_GENDER));

        String mobile = c.getString(c.getColumnIndex(COLUMN_MOBILE));
        Double age = c.getDouble(c.getColumnIndex(COLUMN_AGE));

        Double q = c.getDouble(c.getColumnIndex(COLUMN_QUANTITY));

        c.close();
    }

    public List<Donor> selectQuery2(SQLiteDatabase db) {

        Donor donor = new Donor();
        List<Donor> donorList = new ArrayList<>();
        Cursor c1 = db.rawQuery(sql4, selectArg2);
        c1.moveToNext();
        String bgrp = c1.getString(c1.getColumnIndex(COLUMN_BLOD_GROUP));
        double quantity = c1.getDouble(c1.getColumnIndex(COLUMN_QUANTITY));

        donor = new Donor(bgrp, quantity);
        donorList.add(donor);

        Log.e("  ", "  " + donorList);
        c1.close();

        return donorList;
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {

        // selectQuery2(getReadableDatabase());

        return super.getReadableDatabase();
    }

    /*
     * READ OPERATION
     * =================
     * Here we are reading values from the database
     * First we called the getReadableDatabase() method it will return us the SQLiteDatabase instance
     * but using it we can only perform the read operations.
     *
     * We are running rawQuery() method by passing the select query.
     * rawQuery takes two parameters
     * 1. The query
     * 2. String[] -> Arguments that is to be binded -> We use it when we have a where clause in our query to bind the where value
     *
     * rawQuery returns a Cursor object having all the data fetched from database
     * */
    Cursor getAllEmployees() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    Cursor getDonors(String name, String bloodGroup, double quantity) {

        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = '" + name + "' OR " + COLUMN_BLOD_GROUP + " = '" + bloodGroup + "' OR " + COLUMN_QUANTITY + " <= " + quantity, null);
    }


    /*
     * DELETE OPERATION
     * ======================
     *
     * This is the last delete operation.  To delete again we need a writable database using getWritableDatabase()
     * Then we will call the delete method. It takes 3 parameters
     * 1. String -> Table name
     * 2. String -> The where clause passed as columnname = ?
     * 3. String[] -> The values to be binded on the where clause
     * */
    boolean deleteEmployee(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) == 1;
    }

    public boolean addDonor(String name, String gender, String bloodGroup, String mobileNumber, double v, double v1) {
        return true;
    }

}

