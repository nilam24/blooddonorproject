package com.example.admin.myapplication;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_AGE;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_BLOD_GROUP;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_GENDER;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_ID;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_MOBILE;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_NAME;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_QUANTITY;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.CONTENT_Uri_TAB;

public class Main7Activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    Uri uri = CONTENT_Uri_TAB;
    String[] projection = {COLUMN_ID,COLUMN_NAME,COLUMN_GENDER,COLUMN_BLOD_GROUP,COLUMN_MOBILE,COLUMN_AGE,COLUMN_QUANTITY};
    String selection = null;
    String[] selectArg = {};
    ContentResolver contentResolver;
    Cursor cursor;
    ListView listView;
    Adapter adapter;
    int flag=0;


    String bloodGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        Intent intent=getIntent();
        if(intent!=null)
        {
            bloodGroup=intent.getStringExtra("bloodGroup");
        }

        listView=(ListView)findViewById(R.id.listView1);

        adapter=new Adapter(Main7Activity.this,cursor,0,bloodGroup);
        listView.setAdapter(adapter);

        contentResolver=getContentResolver();

        getSupportLoaderManager().initLoader(1,null,this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this,uri,projection,selection,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

        Log.e("adapter ","listview ");

       if(data==null) {

          Log.e(" ", "null ");

       }

        if (data != null) {
           cursor=data;
            adapter.swapCursor(cursor);

           Log.e("adapter ","listview "+adapter);
       }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adapter.swapCursor(null);

    }

}
