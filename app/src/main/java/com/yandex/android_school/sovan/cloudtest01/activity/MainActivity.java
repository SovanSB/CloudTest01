package com.yandex.android_school.sovan.cloudtest01.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.annotations.JsonAdapter;
import com.yandex.android_school.sovan.cloudtest01.R;
import com.yandex.android_school.sovan.cloudtest01.api.VersionApi;
import com.yandex.android_school.sovan.cloudtest01.cloud.VersionItem;

import java.io.IOException;


import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public class MainActivity extends Activity {

    public String url = "start";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        TextView textViewUrl = (TextView) findViewById(R.id.textViewUrl);
        textViewUrl.setText(url);
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        Retrofit testRetrofit = new Retrofit.Builder()
                                .baseUrl("https://dl.dropboxusercontent.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                .build();

                        VersionApi service = testRetrofit.create(VersionApi.class);
                        Call<VersionItem> testCall = service.getVersion();
                        Response<VersionItem> testResponse;
                        VersionItem testItem = null;
                        try {
                            testResponse = testCall.execute();
                            testItem = testResponse.body();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (testItem != null) {
                            url = testItem.getUrl();
                        }
                    } catch (Error e) {
                        Log.e("Retrofit", e.getMessage(), e);
                    }
                }

            });
            t.start();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
