package com.example.wlwlxgg.mymusic.http.download;

import com.example.wlwlxgg.mymusic.application.MyApplication;
import com.example.wlwlxgg.mymusic.constant.DownloadStatus;
import com.example.wlwlxgg.mymusic.entity.MusicDownloadEntity;
import com.example.wlwlxgg.mymusic.greendao.DaoSession;
import com.example.wlwlxgg.mymusic.greendao.MusicDownloadEntityDao;
import com.example.wlwlxgg.mymusic.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wlwlxgg on 2017/5/4.
 */

public class DownloadManager {
    private Set<MusicDownloadEntity> downloadEntities;
    private HashMap<String, DownloadSubscriber> subscriberHashMap;
    private volatile static DownloadManager INSTANCE;
    private WeakReference<DownloadProgressOnNextListener> listener;

    private DaoSession daoSession = MyApplication.getInstances().getDaoSession();
    private MusicDownloadEntityDao downDao = daoSession.getMusicDownloadEntityDao();

    public DownloadManager() {
        downloadEntities = new HashSet<>();
        subscriberHashMap = new HashMap<>();
    }

    public static DownloadManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DownloadManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DownloadManager();
                }
            }
        }
        return INSTANCE;
    }

    public void startDown(final MusicDownloadEntity entity, WeakReference<DownloadProgressOnNextListener> listener) {
        this.listener = listener;
        if (entity == null || subscriberHashMap == null || listener == null) {
            return;
        }
        DownloadSubscriber subscriber = new DownloadSubscriber(entity, listener);
        subscriberHashMap.put(entity.getSongId(), subscriber);

        DownloadInterceptor interceptor = new DownloadInterceptor(subscriber);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(entity.getBaseUrl())
                .build();
        HttpService service = retrofit.create(HttpService.class);
        String url = entity.getFileLink().substring(entity.getBaseUrl().length());
        service.downMusic("bytes=" + entity.getReadLength() + "-", url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Func1<ResponseBody, MusicDownloadEntity>() {
                    @Override
                    public MusicDownloadEntity call(ResponseBody responseBody) {
                        try {
                            writeCache(responseBody, new File(entity.getSavePath()), entity);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return entity;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        downloadEntities.add(entity);
    }

    public void stopDown(MusicDownloadEntity entity) {
        if (entity == null) return;
        entity.setStatus(DownloadStatus.STOP);
        listener.get().onStop();
        if (subscriberHashMap.containsKey(entity.getSongId())) {
            DownloadSubscriber subscriber = subscriberHashMap.get(entity.getSongId());
            subscriber.unsubscribe();
            subscriberHashMap.remove(entity.getSongId());
        }
        downDao.update(entity);
    }

    public void deleteDown(MusicDownloadEntity entity) {
        stopDown(entity);
        downDao.delete(entity);
    }

    public void pause(MusicDownloadEntity entity) {
        if (entity == null) return;
        entity.setStatus(DownloadStatus.PAUSE);
        listener.get().onPuase();
        if (subscriberHashMap.containsKey(entity.getSongId())) {
            DownloadSubscriber subscriber = subscriberHashMap.get(entity.getSongId());
            subscriber.unsubscribe();
            subscriberHashMap.remove(entity.getSongId());
        }
        downDao.update(entity);
    }

    public void stopAll() {
        for (MusicDownloadEntity downloadEntity : downloadEntities) {
            stopDown(downloadEntity);
        }
        subscriberHashMap.clear();
        downloadEntities.clear();
    }

    public void pauseAll() {
        for (MusicDownloadEntity downloadEntity : downloadEntities) {
            pause(downloadEntity);
        }
        subscriberHashMap.clear();
        downloadEntities.clear();
    }

    /**
     * 写入文件
     */
    public void writeCache(ResponseBody responseBody, File file, MusicDownloadEntity entity) throws IOException {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        long allLength;
        if (entity.getContentLength() == 0) {
            allLength = responseBody.contentLength();
        } else {
            allLength = entity.getContentLength();
        }
        FileChannel channelOut = null;
        RandomAccessFile randomAccessFile = null;
        randomAccessFile = new RandomAccessFile(file, "rwd");
        channelOut = randomAccessFile.getChannel();
        MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE,
                entity.getReadLength(), allLength - entity.getReadLength());
        byte[] buffer = new byte[1024 * 8];
        int len;
        int record = 0;
        while ((len = responseBody.byteStream().read(buffer)) != -1) {
            mappedBuffer.put(buffer, 0, len);
            record += len;
        }
        responseBody.byteStream().close();
        if (channelOut != null) {
            channelOut.close();
        }
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
    }
}