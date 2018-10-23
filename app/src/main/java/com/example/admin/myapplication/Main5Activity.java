package com.example.admin.myapplication;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_AGE;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_BLOD_GROUP;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_GENDER;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_ID;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_MOBILE;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_NAME;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_QUANTITY;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.CONTENT_Uri_TAB;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.CONTENT_Uri_TAB2;


public class Main5Activity extends AppCompatActivity  {

    EditText Name;
    EditText Age;
    EditText Gender;
    EditText BloodGroup;
    EditText MobileNumber;
    EditText Quantity;
    Button ButtonSave;
    Uri uri = CONTENT_Uri_TAB;
    Uri uri2=CONTENT_Uri_TAB2;
    private final String[] projection = {COLUMN_ID,COLUMN_BLOD_GROUP, COLUMN_QUANTITY};
    ContentResolver contentResolver;
    ContentValues contentValues;
    ContentValues values;
    ContentValues valuesUpdate;
    String name;
    String gender;
    String bloodGroup;
    String mobileNumber;
    String age;
    String quantity;
    Cursor cursor;
    String TAG=Main5Activity.class.getName();

    public static final String DATABASE_NAME = "DonorDatabase";
    DatabaseManager mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        //creating a database
        mDatabase = new DatabaseManager(this);
        Name = (EditText) findViewById(R.id.et_Name);
        Age = (EditText) findViewById(R.id.et_Age);
        Gender = (EditText) findViewById(R.id.et_Gender);
        BloodGroup = (EditText) findViewById(R.id.et_Blood);
        MobileNumber = (EditText) findViewById(R.id.et_mobile);
        Quantity = (EditText) findViewById(R.id.et_quantity);
        ButtonSave = (Button) findViewById(R.id.button4);


        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                name = Name.getText().toString().trim();
                gender = Gender.getText().toString().trim();
                bloodGroup = BloodGroup.getText().toString().trim();
                mobileNumber = MobileNumber.getText().toString().trim();
                age = Age.getText().toString().trim();
                quantity = Quantity.getText().toString().trim();

                addDonor(name,gender,bloodGroup,mobileNumber,age,quantity);
                addAvailableBlood(bloodGroup,quantity);

                Toast.makeText(Main5Activity.this, "clicked to add ", Toast.LENGTH_SHORT).show();
            }
        });

        contentResolver = getContentResolver();
        contentValues = new ContentValues();
        values=new ContentValues();
        valuesUpdate=new ContentValues();
    }


    //this method will create the table
    //as we are going to call this method everytime we will launch the application
    //I have added IF NOT EXISTS to the SQL
    //so it will only create the table when the table is not already created
//    private void createEmployeeTable() {

//        mDatabase.execSQL(
//                "CREATE TABLE IF NOT EXISTS donors (\n" +
//                        "    id int NOT NULL CONSTRAINT donors_pk PRIMARY KEY,\n" +
//                        "    name varchar(200) NOT NULL,\n" +
//                        "    gender varchar(200) NOT NULL,\n" +
//                        "    bloodGroup varchar(200) NOT NULL,\n" +
//                        "    mobileNumber varchar(200) NOT NULL,\n" +
//                        "    age double NOT NULL\n" +
//                        ");"
//        );
//    }

    private void addDonor(String name,String gender,String bloodGroup,String mobileNumber,String age,String quantity) {

        Log.e("", " addDonor Main5" + name + gender + bloodGroup + mobileNumber + age + quantity);
        //adding the employee with the DatabaseManager instance
        //adding the employee with the DatabaseManager instance

        if ((name.length()!=0) && (gender.length()!=0) && (bloodGroup.length()!=0) && (mobileNumber.length()!=0) && (age.length()!=0) && (quantity.length()!=0)) {

            int a= Integer.parseInt(age);
            double ag=a;
            int q=Integer.parseInt(quantity);
            double quan=q;
            try {
                contentValues.put(COLUMN_NAME, name);
                contentValues.put(COLUMN_GENDER, gender);
                contentValues.put(COLUMN_BLOD_GROUP, bloodGroup);
                contentValues.put(COLUMN_MOBILE, mobileNumber);
                contentValues.put(COLUMN_AGE, age);
                contentValues.put(COLUMN_QUANTITY, quantity);
                contentValues.putAll(contentValues);

                Log.e("uri1 " + uri, " " + contentValues);
                contentResolver.insert(uri, contentValues);

                Toast.makeText(this, "Donor Added ", Toast.LENGTH_SHORT).show();

            } catch (NullPointerException e) {
                e.printStackTrace();
            }

        }
    }
    private void addAvailableBlood(String bloodGroup,String quantity) {



        if ((name.length() != 0) && (gender.length() != 0) && (bloodGroup.length() != 0) && (mobileNumber.length() != 0) && (age.length() != 0) && (quantity.length() != 0)) {

            int q = Integer.parseInt(quantity);
            double quan = q;


            values.put(COLUMN_BLOD_GROUP, bloodGroup);
            values.put(COLUMN_QUANTITY, quan);
            values.putAll(values);
            Log.e("uri2 " + uri2, " " + values);

            if (values != null) {
                contentResolver.insert(uri2, values);
                Log.e("uri22 " + uri2, " " + values);
            }


        }
    }

//    protected  void queryAvailable(String quantity, int id){
//
//      cursor=contentResolver.query(uri2,projection,null,null,null,null);
//      if(cursor!=null)
//      {
//          cursor.moveToFirst();
//          int id1=cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
//          double q=cursor.getDouble(cursor.getColumnIndex(COLUMN_QUANTITY));
//          int qa=Integer.parseInt(quantity);
//          double q2=qa;
//          double qfinal=q+q2;
//          Uri m = ContentUris.withAppendedId(CONTENT_Uri_TAB2, id1);
//          String selection = COLUMN_ID + "=?";
//          String selectionArg[] = new String[]{String.valueOf(ContentUris.parseId(m))};
//
//          int qt=Integer.parseInt(quantity);
//          double quant=qt;
//
//          valuesUpdate.put(COLUMN_BLOD_GROUP,bloodGroup);
//          valuesUpdate.put(COLUMN_QUANTITY,qt);
//          valuesUpdate.putAll(valuesUpdate);
//          Uri s=contentResolver.insert(uri2,valuesUpdate);
//
//           Log.e(TAG, "sccess "+s);}
//
//    }

}





