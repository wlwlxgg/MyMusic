package com.example.wlwlxgg.mymusic.http.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by wlwlxgg on 2017/5/3.
 */

public class DownloadInterceptor implements Interceptor{

    private DownloadProgressListener downloadProgressListener;

    public DownloadInterceptor(DownloadProgressListener downloadProgressListener) {
        this.downloadProgressListener = downloadProgressListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder()
                .body(new DownloadResponseBody(response.body(), downloadProgressListener)).build();
    }
}
