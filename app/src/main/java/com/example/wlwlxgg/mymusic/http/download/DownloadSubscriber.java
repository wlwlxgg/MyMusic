package com.example.wlwlxgg.mymusic.http.download;


import com.example.wlwlxgg.mymusic.application.MyApplication;
import com.example.wlwlxgg.mymusic.constant.DownloadStatus;
import com.example.wlwlxgg.mymusic.entity.MusicDownloadEntity;
import com.example.wlwlxgg.mymusic.greendao.MusicDownloadEntityDao;

import java.lang.ref.WeakReference;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by wlwlxgg on 2017/5/4.
 */
public class DownloadSubscriber<T> extends Subscriber<T> implements DownloadProgressListener {

    private WeakReference<DownloadProgressOnNextListener> listener;
    private MusicDownloadEntity downloadEntity;
    private MusicDownloadEntityDao dao = MyApplication.getInstances().getDaoSession().getMusicDownloadEntityDao();

    public DownloadSubscriber(MusicDownloadEntity entity, DownloadProgressOnNextListener listener) {
        this.downloadEntity = entity;
        this.listener = new WeakReference<>(listener);
    }

    /**订阅开始时调用*/
    @Override
    public void onStart() {
        if (listener != null) {
            listener.get().onStart();
        }
        downloadEntity.setStatus(DownloadStatus.START);
        dao.update(downloadEntity);
    }

    /**下载完成时调用*/
    @Override
    public void onCompleted() {
        if (listener != null) {
            listener.get().onComplete();
        }
        downloadEntity.setStatus(DownloadStatus.FINISH);
        dao.update(downloadEntity);
    }


    @Override
    public void onError(Throwable e) {
        DownloadManager.getInstance().stopDown(downloadEntity);
        if (listener != null) {
            listener.get().onError(e);
        }
        downloadEntity.setStatus(DownloadStatus.ERROR);
        dao.update(downloadEntity);
    }

    @Override
    public void onNext(T t) {
        if (listener != null) {
            listener.get().onNext(t);
        }
    }

    @Override
    public void update(long read, long count, boolean done) {
        if (downloadEntity.getContentLength() > count) {
            read = downloadEntity.getContentLength() - count + read;
        }
        else downloadEntity.setContentLength(count);
        downloadEntity.setReadLength(read);
        if (listener.get() != null) {
            rx.Observable.just(read).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            if (downloadEntity.getStatus() == DownloadStatus.PAUSE ||
                                    downloadEntity.getStatus() == DownloadStatus.STOP) return;
                            downloadEntity.setStatus(DownloadStatus.DOWN);
                            listener.get().updateProgress(aLong, downloadEntity.getContentLength());
                        }
                    });
        }
    }
}
