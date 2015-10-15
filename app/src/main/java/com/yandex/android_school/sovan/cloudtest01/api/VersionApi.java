package com.yandex.android_school.sovan.cloudtest01.api;

import com.yandex.android_school.sovan.cloudtest01.cloud.VersionItem;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Sovan on 10.10.2015.
 */
public interface VersionApi {
    //@GET("https://dl.dropboxusercontent.com/s/qj2q5iki25a2bh2/version.txt")
    @GET("https://dl.dropboxusercontent.com/s/qj2q5iki25a2bh2/version.txt")
    Call<VersionItem> getVersion();
}
