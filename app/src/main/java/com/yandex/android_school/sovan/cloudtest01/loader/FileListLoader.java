package com.yandex.android_school.sovan.cloudtest01.loader;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;

import com.yandex.android_school.sovan.cloudtest01.api.FileListApi;
import com.yandex.android_school.sovan.cloudtest01.cloud.PictureItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Sovan on 12.10.2015.
 */
public class FileListLoader extends CursorLoader {
    final String mBaseUri;

    public FileListLoader(Context context, String baseUri) {
        super(context, PictureItem.URI, null, null, null, null);
        mBaseUri = baseUri;
    }

    @Override
    public Cursor loadInBackground() {

        Retrofit testRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUri)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        FileListApi service = testRetrofit.create(FileListApi.class);
        //   VersionItem testItem = null;
        Call<List<PictureItem>> testCall = service.loadPictures(mBaseUri);
        List<PictureItem> list;
        List<ContentValues> values = new ArrayList<ContentValues>();
        try {
            Response<List<PictureItem>> testResponse = testCall.execute();
            list = testResponse.body();
            for (PictureItem pic : list) {
                values.add(pic.toValues());
            }
            final ContentValues[] bulkCategories = values.toArray(new ContentValues[values.size()]);
            ContentResolver db = getContext().getContentResolver();
            // Clearing base before refreshing
            db.delete(PictureItem.URI, null, null);
            db.bulkInsert(PictureItem.URI, bulkCategories);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.loadInBackground();
    }
}
