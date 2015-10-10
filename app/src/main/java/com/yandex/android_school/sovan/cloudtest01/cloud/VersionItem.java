package com.yandex.android_school.sovan.cloudtest01.cloud;

/**
 * Created by Sovan on 10.10.2015.
 */
public class VersionItem {
    private long id;
    private String url;

    public VersionItem(long id, String url) {
        this.id = id;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
