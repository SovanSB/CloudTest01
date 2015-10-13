package com.yandex.android_school.sovan.cloudtest01.sqlite;

import android.net.Uri;

import java.util.List;

/**
 * Created by Sovan on 12.10.2015.
 */
public class SQLiteUriMatcher {
    static final int NO_MATCH = -1;
    static final int MATCH_ALL = 1;
    static int match(Uri uri) {
        final List<String> pathSegments = uri.getPathSegments();
        switch(pathSegments.size()) {
            case 1:
                return MATCH_ALL;
            default:
                return NO_MATCH;
        }
    }
}
