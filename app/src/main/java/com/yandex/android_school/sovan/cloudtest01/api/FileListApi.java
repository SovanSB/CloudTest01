package com.yandex.android_school.sovan.cloudtest01.api;

import com.yandex.android_school.sovan.cloudtest01.cloud.PictureItem;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Sovan on 13.10.2015.
 */
public interface FileListApi {
    @GET("{url}")
  //  @GET("https://dl.dropboxusercontent.com/s/gc7eluk8lgxodd7/filelist.txt?dl=0")
    Call<List<PictureItem>> loadPictures(@Path("url") String url);
}
