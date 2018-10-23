package com.example.admin.myapplication;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;

import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.CONTENT_Uri_TAB;


public class Main4Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner catagories;

    EditText Name;
    EditText Quantity;
    EditText Amount;
    Button button6;
    String[] BloodGroup = {"A+", "B+", "O+", "AB+", "A-", "B-", "O-", "AB-"};
    String selectedBloodGroup = "";
    Uri uri = CONTENT_Uri_TAB;
    String[] projection = {};
    String selection = null;
    String[] selectArg = {};
    List<Donor> donorList;
    ListView listView;
    //The databasemanager object
    DatabaseManager mDatabase;
    ContentResolver contentResolver;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        mDatabase = new DatabaseManager(this);
        contentResolver = getContentResolver();

        catagories = findViewById(R.id.spinner);

        Spinner categories = (Spinner) findViewById(R.id.spinner);

        Name = (EditText) findViewById(R.id.et_Name);
        Quantity = (EditText) findViewById(R.id.et_quantity);
        Amount = (EditText) findViewById(R.id.et_amount);
        donorList = new ArrayList<>();

        cursor = contentResolver.query(uri, projection, selection, selectArg, null);
        Button button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  loadDonorsFromDatabase(cursor);

                if((Name.length()!=0)&&(selectedBloodGroup.length()!=0)&&(Quantity.length()!=0)&&(Amount.length()!=0)) {

                    Intent intent = new Intent(Main4Activity.this, Main7Activity.class);
                    intent.putExtra("bloodGroup",selectedBloodGroup);
                    startActivity(intent);
                }
            }


        });


        //  spinner3= (Spinner) findViewById(R.id.spinner3);

        // ArrayAdapter adapter=ArrayAdapter.createFromResource(this,R.array.Available,android.R.layout.simple_spinner_item);
        //spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

        // ArrayAdapter adapter1=ArrayAdapter.createFromResource(this,R.array.Available,android.R.layout.simple_spinner_item);
        //spinner3.setAdapter(adapter1);
        //spinner3.setOnItemSelectedListener(this);
        String[] BloodGroup = {"A+", "B+", "O+", "AB+", "A-", "B-", "O-", "AB-"};

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, BloodGroup);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedBloodGroup = BloodGroup[position];


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void loadDonorsFromDatabase(Cursor cursor) {
        String name = Name.getText().toString().trim();
        String bloodGroup = "A+";//selectedBloodGroup.trim();
        String quantity = Quantity.getText().toString().trim();


    }
}


        //we are here using the DatabaseManager instance to get all employees
//        Cursor cursor = mDatabase.getDonors(name, bloodGroup, Double.parseDouble(quantity));
//        if (cursor.moveToFirst()) {
//            do {
//                donorList.add(new Donor(
//                        cursor.getInt(0),
//                        cursor.getString(1),
//                        cursor.getString(2),
//                        cursor.getString(3),
//                        cursor.getString(4),
//                        cursor.getDouble(5),
//                        cursor.getDouble(6)
//                ));
//            } while (cursor.moveToNext());
//            //                Intent intent=new Intent(Main4Activity.this,Main7Activity.class);
            //                startActivity(intent);

            //passing the databasemanager instance this time to the adapter
//            DonorAdapter adapter = new DonorAdapter(this, R.layout.list_layout_employees, employeeList, mDatabase);
//            listView.setAdapter(adapter);




    // @Override
    //public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    //  TextView myText= (TextView) view;
    // Toast.makeText(this,"You Selected"+myText.getText(),Toast.LENGTH_SHORT).show();
    //}

    //@Override
    //public void onNothingSelected(AdapterView<?> parent) {

    //}
   /* public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText= (TextView) view;
        Toast.makeText(this,"You Selected"+myText.getText(),Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }*/


    // @Override
    //public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    //  TextView myText= (TextView) view;
    // Toast.makeText(this,"You Selected"+myText.getText(),Toast.LENGTH_SHORT).show();
    //}

    //@Override
    //public void onNothingSelected(AdapterView<?> parent) {

    //}
   /* public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText= (TextView) view;
        Toast.makeText(this,"You Selected"+myText.getText(),Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }*/
//}
//................................................................................
