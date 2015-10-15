package com.yandex.android_school.sovan.cloudtest01.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.yandex.android_school.sovan.cloudtest01.R;
import com.yandex.android_school.sovan.cloudtest01.adapter.PictureAdapter;
import com.yandex.android_school.sovan.cloudtest01.cloud.PictureItem;
import com.yandex.android_school.sovan.cloudtest01.loader.AsyncVersionLoader;
import com.yandex.android_school.sovan.cloudtest01.cloud.VersionItem;
import com.yandex.android_school.sovan.cloudtest01.loader.FileListLoader;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks {

    public String url = "start";
    public String BASE_URI = "https://dl.dropboxusercontent.com";
    // public final String BASE_URI = "https://dl.dropboxusercontent.com/s/qj2q5iki25a2bh2/version.txt";
    TextView mTextViewUri;
    TextView mTextViewVersion;

    ListView mListView;
    PictureAdapter mPictureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextViewUri = (TextView) findViewById(R.id.textViewUrl);
        mTextViewVersion = (TextView) findViewById(R.id.textViewVersion);
        mListView = (ListView) findViewById(R.id.listView);
        mPictureAdapter = new PictureAdapter(this, null);
        mListView.setAdapter(mPictureAdapter);

        getLoaderManager().initLoader(R.id.version_manager, Bundle.EMPTY, this);
        //      getLoaderManager().initLoader(R.id.files_manager, Bundle.EMPTY, this);


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

//            Retrofit testRetrofit = new Retrofit.Builder()
//                    .baseUrl("https://dl.dropboxusercontent.com")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .build();
//
//            VersionApi service = testRetrofit.create(VersionApi.class);
//            Call<VersionItem> testCall = service.getVersion();
//            testCall.enqueue(new Callback<VersionItem>() {
//                @Override
//                public void onResponse(Response<VersionItem> response, Retrofit retrofit) {
//                    VersionItem testItem = response.body();
//                    url = testItem.getUrl();
//                    Log.d("Response", "Response successfully received");
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    mTextViewUri.setText(url);
//                    // Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT);
//                }
//
//                @Override
//                public void onFailure(Throwable t) {
//                    url = "Error!";
//                    Log.e("Response", "Failure error!", t);
//                }
//            });
//                        Response<VersionItem> testResponse;
//                        VersionItem testItem = null;
//                        try {
//                            testResponse = testCall.execute();
//                            testItem = testResponse.body();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        if (testItem != null) {
//                            url = testItem.getUrl();
//                        }


            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onButtonClick(View view) {
        // Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        getLoaderManager().initLoader(R.id.version_manager, Bundle.EMPTY, this);
        getLoaderManager().initLoader(R.id.files_manager, Bundle.EMPTY, this);

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        if (id == R.id.version_manager) {
            return new AsyncVersionLoader(getApplicationContext(), BASE_URI);
        }
        if (id == R.id.files_manager) {
            return new FileListLoader(getApplicationContext(), BASE_URI);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        if (loader.getId() == R.id.version_manager) {
            if (data != null) {
                mTextViewVersion.setText(Long.toString(((VersionItem) data).getId()));
                mTextViewUri.setText(((VersionItem) data).getUrl());
                BASE_URI = ((VersionItem) data).getUrl();
            }
        }
        if (loader.getId() == R.id.files_manager) {
            mPictureAdapter.swapCursor((Cursor) data);
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        if (loader.getId() == R.id.version_manager) {
            //          mTextViewUri.setText("Loader reset");
        }
        if (loader.getId() == R.id.files_manager) {
            mPictureAdapter.swapCursor(null);
        }
    }
}
