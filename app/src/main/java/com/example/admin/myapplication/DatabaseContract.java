package com.example.admin.myapplication;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


public class DatabaseContract {


    private static final String BASE_URI="content://";
    static final String CONTENT_ATHORITY = "com.example.admin.myapplication";
    private static final Uri BASE_CONTENT_URI = Uri.parse(BASE_URI + CONTENT_ATHORITY);

    public DatabaseContract(){

    }
    static class DatabaseEntry implements BaseColumns {

        static final String TABLE_NAME = "donors";
        static final String COLUMN_ID = BaseColumns._ID;
        static final String COLUMN_NAME = "name";
        static final String COLUMN_GENDER = "gender";
        static final String COLUMN_BLOD_GROUP = "bloodGroup";
        static final String COLUMN_MOBILE = "mobileNumber";
        static final String COLUMN_AGE = "age";
        static final String COLUMN_QUANTITY = "quantity";
        static final String TABLE2="available_tab";
        static final String TABLE3="group_tab";
        static final String COLUMN_A="APositive";
        static final String COLUMN_B="BPositive";
        static final String COLUMN_AB="ABPositive";
        static final String COLUMN_O="OPositive";
        static final String COLUMN_A1="ANegative";
        static final String COLUMN_B1="BNegative";
        static final String COLUMN_AB1="ABNegative";
        static final String COLUMN_O1="ONegative";




        static final String PATH1 = DatabaseEntry.TABLE_NAME;
        static final String PATH2= DatabaseEntry.TABLE2;
        static final String PATH3=DatabaseEntry.TABLE3;

        static final Uri CONTENT_Uri_TAB = Uri.withAppendedPath(BASE_CONTENT_URI, PATH1);
        static final Uri CONTENT_Uri_TAB2=Uri.withAppendedPath(BASE_CONTENT_URI,PATH2);
        static final Uri CONTENT_Uri_TAB3=Uri.withAppendedPath(BASE_CONTENT_URI,PATH3);

        protected static String CONTENT_LIST_TYPE2 = ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+CONTENT_ATHORITY+"/"+PATH1;
        protected static String CONTENT_LIST_TYPE3=ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+CONTENT_ATHORITY+"/"+PATH2;
        protected static String CONTENT_LIST_TYPE4=ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+CONTENT_ATHORITY+"/"+PATH3;

    }


}

