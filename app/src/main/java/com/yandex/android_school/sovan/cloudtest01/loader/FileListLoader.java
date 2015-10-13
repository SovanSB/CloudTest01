package com.yandex.android_school.sovan.cloudtest01.loader;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Sovan on 12.10.2015.
 */
public class FileListLoader extends CursorLoader {
    public FileListLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    public Cursor loadInBackground() {
        return super.loadInBackground();
    }
}
