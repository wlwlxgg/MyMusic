package com.example.wlwlxgg.mymusic.http.download;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by wlwlxgg on 2017/5/3.
 */

public class DownloadResponseBody extends ResponseBody {

    private ResponseBody responseBody;
    private DownloadProgressListener downloadProgressListener;
    private BufferedSource bufferedSource;

    public DownloadResponseBody(ResponseBody responseBody, DownloadProgressListener downloadProgressListener) {
        this.responseBody = responseBody;
        this.downloadProgressListener = downloadProgressListener;
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (null != downloadProgressListener) {
                    downloadProgressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                }
                return bytesRead;
            }
        };
    }

    @Override
    public MediaType contentType() {
        return null;
    }
}
