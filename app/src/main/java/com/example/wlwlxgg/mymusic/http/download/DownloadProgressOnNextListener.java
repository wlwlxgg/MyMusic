package com.example.wlwlxgg.mymusic.http.download;

/**
 * Created by wlwlxgg on 2017/5/4.
 */

public abstract class DownloadProgressOnNextListener<T> {
    /**
     * 成功后回调方法
     */
    public abstract void onNext(T t);

    /**
     * 开始下载
     */
    public abstract void onStart();

    /**
     * 完成下载
     */
    public abstract void onComplete();

    /**
     * 下载进度
     */
    public abstract void updateProgress(long readLength, long countLength);

    /**
     * 失败或者错误方法
     */
    public void onError(Throwable e) {
    }

    /**
     * 暂停下载
     */
    public void onPuase() {
    }

    /**
     * 停止下载销毁
     */
    public void onStop() {
    }
}
