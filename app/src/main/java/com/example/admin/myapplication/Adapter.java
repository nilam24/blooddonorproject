package com.example.admin.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_AGE;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_BLOD_GROUP;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_GENDER;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_ID;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_MOBILE;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_NAME;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.COLUMN_QUANTITY;

public class Adapter extends CursorAdapter {

    Context context;
    Cursor c;
    int flags;
    List<Donor> donorList;
    Donor donor;
    String bloodGroup;
    private static String TAG = Adapter.class.getName();

    public Adapter(Context context, Cursor c, int flags, String bloodGroup) {
        super(context, c, flags);

        this.context = context;
        this.c = c;
        this.flags = flags;
        this.bloodGroup = bloodGroup;
        Log.e("adapter ", " " + c + bloodGroup);
        Log.e("intent value1", "" + bloodGroup);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        context = viewGroup.getContext();

        return LayoutInflater.from(context).inflate(R.layout.item_list_layout, viewGroup, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView textName, textG, textB, textM, textA, textQ;
        int age2 = 0;
        int qun = 0;

        textName = (TextView) view.findViewById(R.id.textviewName);
        textG = (TextView) view.findViewById(R.id.textviewGender);
        textB = (TextView) view.findViewById(R.id.textviewBlod);
        textM = (TextView) view.findViewById(R.id.textviewMobile);
        textA = (TextView) view.findViewById(R.id.textviewAge);
        textQ = (TextView) view.findViewById(R.id.textviewQuantity);

        donorList = new ArrayList<Donor>();
        donor = new Donor();

        int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        String blodgrp = cursor.getString(cursor.getColumnIndex(COLUMN_BLOD_GROUP));

        if (blodgrp.equals(bloodGroup)) {
            textName.setVisibility(View.VISIBLE);
            textG.setVisibility(View.VISIBLE);
            textB.setVisibility(View.VISIBLE);
            textM.setVisibility(View.VISIBLE);
            textA.setVisibility(View.VISIBLE);
            textQ.setVisibility(View.VISIBLE);

            String nm = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            if (nm != null) {
                textName.setText(nm);

            }

            String gender1 = cursor.getString(cursor.getColumnIndex(COLUMN_GENDER));

            if (gender1 != null) {
                textG.setText(gender1);
            }

            if (blodgrp != null) {
                textB.setText(blodgrp);
            }

            String mobile = cursor.getString(cursor.getColumnIndex(COLUMN_MOBILE));
            if (mobile != null) {
                textM.setText(mobile);
            }

            double age = cursor.getDouble(cursor.getColumnIndex(COLUMN_AGE));
            String ag = String.valueOf(age);
            if (ag != null) {
                textA.setText(ag);

            }

            double q = cursor.getDouble(cursor.getColumnIndex(COLUMN_QUANTITY));
            int qunt = (int) q;
            String quant = String.valueOf(q);
            if (q != 0) {
                textQ.setText(quant);
            }

        }

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

}














