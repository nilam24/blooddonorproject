package com.example.admin.myapplication;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;
import java.util.ArrayList;
import java.util.List;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_BLOD_GROUP;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_ID;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_QUANTITY;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.CONTENT_Uri_TAB2;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.TABLE_NAME;

public class PieChartDisplay extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    Uri uri = CONTENT_Uri_TAB2;
    String[] projection = {COLUMN_ID, COLUMN_BLOD_GROUP, COLUMN_QUANTITY};
    String selection = null;
    String[] selectArg = {};
    ContentResolver contentResolver;
    ContentValues values;
    Cursor cursor;
    AnyChartView anyChartView;
    int DONOR_LOADER_ID = 1;
    List<DataEntry> data2;
    List<Donor> donorList;
    Donor donor;
    String sql4 = "select  sum(quantity)  from   " + TABLE_NAME + "  GROUP BY  " + COLUMN_BLOD_GROUP;
    private final String groupby = "GROUP BY  " + COLUMN_BLOD_GROUP;
    private final String[] projection3 = {COLUMN_BLOD_GROUP, COLUMN_QUANTITY, groupby};
    private final String[] selectArg1 = new String[]{"sum(" + COLUMN_QUANTITY + ")"};
    private final String selection1 = " as  quantity";
    Pie pie;
    double qn;
    String bgr;

    private final String select[]=new String[]{COLUMN_BLOD_GROUP, " sum(" + COLUMN_QUANTITY + ")" + "GROUP BY (" +COLUMN_BLOD_GROUP + " )"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie_chart_layout);

        donor = new Donor();
        donorList = new ArrayList<>();
        anyChartView = findViewById(R.id.any_chart_view);

        getSupportLoaderManager().initLoader(DONOR_LOADER_ID, null, this);
        contentResolver = getContentResolver();

        pie = AnyChart.pie();
        data2 = new ArrayList<>();

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {

        Log.e("$$", "uri==" + uri+projection3.toString() +selectArg1);
        return new CursorLoader(getApplicationContext(), uri, projection3, selection1, selectArg1, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        int sum1=0;
        int sum2=0;
        int sum3=0;
        int sum4=0;
        int sum5=0;
        int sum6=0;
        int sum7=0;
        int sum8=0;


        cursor=contentResolver.query(uri,select,null,null,null);

        try{
        for(int i=0;i<cursor.getCount();i++)
        {
            cursor.moveToPosition(i);
            bgr=cursor.getString(cursor.getColumnIndex(COLUMN_BLOD_GROUP));
            if(bgr.equals("A+"))
            {
                qn=cursor.getDouble(cursor.getColumnIndex(COLUMN_QUANTITY));
                 sum1=sum1+(int)qn;

            }
            if(bgr.equals("B+"))
            {
                qn=cursor.getDouble(cursor.getColumnIndex(COLUMN_QUANTITY));
                int qt= (int) qn;
                 sum2=sum2+qt;

            }
            if(bgr.equals("AB+"))
            {
                qn=cursor.getDouble(cursor.getColumnIndex(COLUMN_QUANTITY));
                int qt= (int) qn;
                sum3=sum3+qt;

            }
            if(bgr.equals("O+"))
            {
                qn=cursor.getDouble(cursor.getColumnIndex(COLUMN_QUANTITY));
                int qt= (int) qn;
                sum4=sum4+qt;

            }
            if(bgr.equals("O-"))
            {
                qn=cursor.getDouble(cursor.getColumnIndex(COLUMN_QUANTITY));
                int qt= (int) qn;
                sum5=sum5+qt;

            }
            if(bgr.equals("A-"))
            {
                qn=cursor.getDouble(cursor.getColumnIndex(COLUMN_QUANTITY));
                int qt= (int) qn;
                sum6=sum6+qt;

            }
            if(bgr.equals("B-"))
            {
                qn=cursor.getDouble(cursor.getColumnIndex(COLUMN_QUANTITY));
                int qt= (int) qn;
                sum7=sum7+qt;

            }
            if(bgr.equals("AB-"))
            {
                qn=cursor.getDouble(cursor.getColumnIndex(COLUMN_QUANTITY));
                int qt= (int) qn;
                sum8=sum8+qt;

            }

       }
            int sum11=sum1;
            int sum22=sum2;
            int sum33=sum3;
            int sum44=sum4;
            int sum55=sum5;
            int sum66=sum6;
            int sum77=sum7;
            int sum88=sum8;
            data2.add(new ValueDataEntry("A+",sum11));
            data2.add(new ValueDataEntry("B+",sum22));
            data2.add(new ValueDataEntry("AB+",sum33));
            data2.add(new ValueDataEntry("O+",sum44));
            data2.add(new ValueDataEntry("O-",sum55));
            data2.add(new ValueDataEntry("A-",sum66));
            data2.add(new ValueDataEntry("B-",sum77));
            data2.add(new ValueDataEntry("AB-",sum88));
            donor=new Donor(bgr,qn);
            donorList.add(donor);
            Log.e("bgr="+bgr,"quan" + sum11+","+sum22);
            pie.data(data2);
            pie.setLabels("Donor chart ");
            anyChartView.setChart(pie);

            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
            Log.e("loader callback==", "" + donorList);

        }


       @Override
        public void onLoaderReset (@NonNull Loader < Cursor > loader) {

        }
    }



