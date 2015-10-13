package com.yandex.android_school.sovan.cloudtest01.sqlite;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Sovan on 12.10.2015.
 */
public class SQLiteProvider extends ContentProvider {

    private static final String DATABASE_NAME = "files.db";
    private static final int DATABASE_VERSION = 0;
    private MySQLHelper mHelper;

    @Override
    public boolean onCreate() {
        mHelper = new MySQLHelper(getContext(), DATABASE_NAME, DATABASE_VERSION);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] columns, String where, String[] whereArgs, String orderBy) {
        int match = SQLiteUriMatcher.match(uri);
        switch (match) {
            case SQLiteUriMatcher.MATCH_ALL:
                return select(uri,columns, where, whereArgs, orderBy);
            default:
                throw new SQLiteException("Uri not found " + uri.toString());

        }
    }

    @Override
    public String getType(Uri uri) {
        if (SQLiteUriMatcher.match(uri) == SQLiteUriMatcher.MATCH_ALL) {
            return "vnd.android.cursor.dir/" + uri.getPathSegments().get(0);
        }
        else {
            throw new SQLiteException("Uri not found " + uri.toString());
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (SQLiteUriMatcher.match(uri) == SQLiteUriMatcher.NO_MATCH) {
            throw new SQLiteException("Uri not found " + uri.toString());
        }
        return insert(uri, values, true);
    }

    public Uri insert(Uri uri, ContentValues values, boolean notify) {
        final long lastInsertRowid = mHelper.getWritableDatabase().insert(
                uri.getPathSegments().get(0),
                BaseColumns._ID,
                values
        );
        final Uri result = new Uri.Builder()
                .scheme(uri.getScheme())
                .authority(uri.getAuthority())
                .path(uri.getPathSegments().get(0))
                .path(Long.toString(lastInsertRowid))
                .build();
        if (notify) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return result;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private Cursor select(Uri uri, String[] columns, String where, String[] whereArgs, String orderBy) {
        final Cursor cursor = mHelper.getReadableDatabase().query(
                uri.getPathSegments().get(0),
                columns, where, whereArgs, null, null, orderBy
        );
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    private final static class MySQLHelper extends SQLiteOpenHelper {

        public MySQLHelper(Context context, String name, int version) {
            super(context, name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS files(_id INTEGER, update_id LONG," +
                    "filename TEXT, url TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS files");
            onCreate(db);
        }
    }
}
