package com.yandex.android_school.sovan.cloudtest01.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.yandex.android_school.sovan.cloudtest01.R;
import com.yandex.android_school.sovan.cloudtest01.cloud.PictureItem;

import retrofit.Response;

/**
 * Created by Sovan on 14.10.2015.
 */
public class PictureAdapter extends ResourceCursorAdapter {
    public PictureAdapter(Context context, Cursor c) {
        super(context, R.layout.li_item, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final String name = cursor.getString(cursor.getColumnIndex(PictureItem.Columns.FILENAME));
        TextView fileName = (TextView) view.findViewById(R.id.textViewFileName);
        fileName.setText(name);
        final String url = cursor.getString(cursor.getColumnIndex(PictureItem.Columns.URL));
        TextView fileUrl = (TextView) view.findViewById(R.id.textViewFileUrl);
        fileUrl.setText(url);
    }
}
