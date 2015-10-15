package com.yandex.android_school.sovan.cloudtest01.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.util.Log;

import com.yandex.android_school.sovan.cloudtest01.api.VersionApi;
import com.yandex.android_school.sovan.cloudtest01.cloud.VersionItem;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Sovan on 11.10.2015.
 */
public class AsyncVersionLoader extends AsyncTaskLoader<VersionItem>{
    String mBaseUri;
    VersionItem mItem;

    public AsyncVersionLoader(Context context, String uri) {
        super(context);
        mBaseUri = uri;
    }




    @Override
    public VersionItem loadInBackground() {
        Retrofit testRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUri)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        VersionApi service = testRetrofit.create(VersionApi.class);
     //   VersionItem testItem = null;
        Call<VersionItem> testCall = service.getVersion();
        try {
            mItem = testCall.execute().body();
//            try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        testCall.enqueue(new Callback<VersionItem>() {
//            @Override
//            public void onResponse(Response<VersionItem> response, Retrofit retrofit) {
//                testItem = response.body();
//                Log.d("Response", "Response successfully received");
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.e("Response", "Failure error!", t);
//            }
//        });
        return mItem;
    }

    @Override
    protected void onStartLoading() {
//        if (mItem != null) {
//            deliverResult(mItem);
//        }
//        else loadInBackground();
      //  super.onStartLoading();
//        if (mItem == null) {
            forceLoad();
//        }
//        else startLoading();
    }
//
//    @Override
//    public void deliverResult(VersionItem data) {
//        super.deliverResult(data);
//    }
}
