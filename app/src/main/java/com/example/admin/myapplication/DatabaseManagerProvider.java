package com.example.admin.myapplication;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import static com.example.admin.myapplication.DatabaseContract.CONTENT_ATHORITY;
import static com.example.admin.myapplication.DatabaseContract.DatabaseEntry.*;


public class DatabaseManagerProvider extends ContentProvider {

    private SQLiteDatabase sqLiteDatabaseRead;
    private SQLiteDatabase sqLiteDatabaseWrite;
    private static final int Db_match_1 = 100;
    private static final int Db_match_2 = 101;
    private static final int DB_match_3 = 102;
    private static final int Db_match_4 = 103;

    private final String[] projection = {COLUMN_ID, COLUMN_NAME, COLUMN_GENDER, COLUMN_BLOD_GROUP, COLUMN_MOBILE, COLUMN_AGE, COLUMN_QUANTITY};
    private final String[] projection2 = {COLUMN_ID, COLUMN_BLOD_GROUP, COLUMN_QUANTITY};
    private final String[] projection3 = {COLUMN_BLOD_GROUP, COLUMN_QUANTITY};
    private final String[] selectArg = new String[]{"sum(" + COLUMN_QUANTITY + ")"};
    private final String selection = " as  quantity";
    private final String groupby = COLUMN_BLOD_GROUP;


    private final String TAG = DatabaseManagerProvider.class.getName();
    private Cursor cursor = null;
    private ContentResolver contentResolver;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(CONTENT_ATHORITY, PATH1, Db_match_1);
        uriMatcher.addURI(CONTENT_ATHORITY, "donors/#", Db_match_2);
        uriMatcher.addURI(CONTENT_ATHORITY, PATH2, DB_match_3);
        uriMatcher.addURI(CONTENT_ATHORITY, "available_tab/#", DB_match_3);


    }

    @Override
    public boolean onCreate() {
        DatabaseManager helper = new DatabaseManager(getContext());
        sqLiteDatabaseRead = helper.getReadableDatabase();
        sqLiteDatabaseWrite = helper.getWritableDatabase();
        contentResolver = getContext().getContentResolver();
        return true;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        int match = uriMatcher.match(uri);
        switch (match) {
            case Db_match_1:
                cursor = sqLiteDatabaseRead.query(TABLE_NAME, projection, null, null, null, null, null);
                Log.e(TAG, "queryquery" + cursor);
                break;

            case DB_match_3:

                cursor = sqLiteDatabaseRead.query(TABLE2, projection2, null, null, null, null, null);

                Log.e("", "QueryAvailableBlood");
                break;

            case Db_match_4:


                cursor = sqLiteDatabaseRead.query(TABLE_NAME, projection3, selection, selectArg, groupby, null, null);
                Log.e("", "QueryGroupby blood");

            default:
                throw new IllegalArgumentException("no match for uri " + uri);
        }
        cursor.setNotificationUri(contentResolver, uri);
        return cursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        int match = uriMatcher.match(uri);
        switch (match) {
            case Db_match_1:
                return CONTENT_LIST_TYPE2;

            case DB_match_3:
                return CONTENT_LIST_TYPE3;
            default:
                throw new IllegalArgumentException("Exception @@@@@");
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        int match = uriMatcher.match(uri);
        Log.e(TAG, TAG + match);
        switch (match) {
            case Db_match_1:
                Log.e(TAG, "insert0000000 " + uri);

                InsertDonorData(uri, contentValues);
                return uri;

            case DB_match_3:

                InsertDonorBloodData(uri, contentValues);
                return uri;
            default:
                throw new IllegalArgumentException("exception ....**" + uri);
        }
    }

    private Uri InsertDonorData(Uri uri, ContentValues Values) {

        long _id = sqLiteDatabaseWrite.insert(TABLE_NAME, null, Values);
        Log.e(TAG, "" + Values + " , " + _id);

        if (_id == -1) {
            return null;
        }
        if (_id != -1) {
            return uri;
        }
        Log.e(TAG, _id + " uriiiiii " + uri);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, _id);
    }

    private Uri InsertDonorBloodData(Uri uri, ContentValues values) {

        long id = sqLiteDatabaseWrite.insert(TABLE2, null, values);
        Log.e(TAG, values + " id==" + id);

        if (id == -1) {
            return null;
        }
        if (id != -1) {
            return uri;
        }

        Log.e(TAG, "uri::" + uri);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {


        int mat = uriMatcher.match(uri);
        int success;

        selection = COLUMN_ID + "=?";
        selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
        switch (mat) {
            case Db_match_2:
                success = sqLiteDatabaseRead.delete(TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("uri is invalid");
        }
        return success;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectinArg) {

        int match = uriMatcher.match(uri);
        int i = 0;
        switch (match) {
            case DB_match_3:
                i = UpdateAvailableBlood(uri, contentValues, selection, selectinArg);
                Log.e("update", "query i=" + i);
                return i;

            default:
                throw new IllegalArgumentException("invalid query for updation");
        }

    }

    protected int UpdateAvailableBlood(Uri uri, ContentValues contentValues, String selection, String[] selectArg) {

        int updt = sqLiteDatabaseWrite.update(TABLE2, contentValues, selection, selectArg);
        if (updt == 1) {
            return updt;
        }
        return updt;

    }
}

