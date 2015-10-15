package com.yandex.android_school.sovan.cloudtest01.cloud;

import android.content.ContentValues;
import android.net.Uri;

import com.yandex.android_school.sovan.cloudtest01.BuildConfig;

/**
 * Created by Sovan on 13.10.2015.
 */
public class PictureItem {
    public static final Uri URI = Uri.parse("content://" + BuildConfig.APPLICATION_ID + "/files");

    private String filename;
    private String url;
    private long mUpdateID;

    public String getFilename() {
        return filename;
    }

    public String getUrl() {
        return url;
    }

    public PictureItem(String name, String url) {
        this.filename = name;
        this.url = url;
    }

    public long getUpdateID() {
        return mUpdateID;
    }

    public void setUpdateID(long updateID) {
        mUpdateID = updateID;
    }

    public static interface Columns {
        String FILENAME = "filename";
        String URL = "url";
        String UPDATE_ID = "update_id";
    }

    public ContentValues toValues(){
        final ContentValues values = new ContentValues();
        values.put(Columns.FILENAME, filename);
        values.put(Columns.URL, url);
        values.put(Columns.UPDATE_ID, mUpdateID);
        return values;
    }
}
