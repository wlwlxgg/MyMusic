package com.example.wlwlxgg.mymusic.http.download;

/**
 * Created by wlwlxgg on 2017/5/3.
 */

public interface DownloadProgressListener {
    /**
     * @param read  已读
     * @param count response.contentLength()
     * @param done  是否下载完成
     */
    void update(long read, long count, boolean done);
}
