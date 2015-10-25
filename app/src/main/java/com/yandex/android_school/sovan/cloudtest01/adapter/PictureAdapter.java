package com.yandex.android_school.sovan.cloudtest01.adapter;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
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
       // TextView fileUrl = (TextView) view.findViewById(R.id.textViewFileUrl);
       // fileUrl.setText(url);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        if (TextUtils.isEmpty(url))
        {
            imageView.setVisibility(View.GONE);
        }
        else {
            imageView.setVisibility(View.VISIBLE);
            Picasso.with(context).load(url).into(imageView);
        }
    }
}
