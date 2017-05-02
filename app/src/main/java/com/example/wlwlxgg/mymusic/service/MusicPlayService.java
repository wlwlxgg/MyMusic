package com.example.wlwlxgg.mymusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.example.wlwlxgg.mymusic.constant.PlayStatus;
import com.example.wlwlxgg.mymusic.http.result.MusicInfo;

/**
 * Created by wlwlxgg on 2017/4/16.
 */

public class MusicPlayService extends Service {

    public interface OnProgressListener {
        void onProgress(int progress);
    }
    /**
     * 注册回调接口的方法，供外部调用
     *
     * @param onProgressListener
     */
    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }



    private MediaPlayer mediaPlayer;
    private MusicInfo musicInfo;
    private int msg;
    private int progress = 0;
    /**
     * 更新播放进度回调接口
     */
    private OnProgressListener onProgressListener;

    @Override
    public void onCreate() {
        mediaPlayer = new MediaPlayer();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            musicInfo = (MusicInfo) intent.getSerializableExtra("MusicInfo");
            msg = intent.getIntExtra("Msg", 0);
        }
        switch (msg) {
            case PlayStatus.PLAY:
                play();
                break;
            case PlayStatus.PAUSE:
                pause();
                break;
            case PlayStatus.CONTINUE:
                resume();
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void play() {
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(musicInfo.getBitrate().getFile_link());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pause() {
        mediaPlayer.pause();
    }

    private void resume() {
        mediaPlayer.start();
    }

    public void setProgress() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mediaPlayer.getDuration()!= mediaPlayer.getCurrentPosition()) {
                    progress = mediaPlayer.getCurrentPosition();
                    if (onProgressListener != null) {
                        onProgressListener.onProgress(progress);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    /**
     * 返回一个Binder对象
     */
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder{
        /**
         * 获取当前Service的实例
         * @return
         */
        public MusicPlayService getService(){
            return MusicPlayService.this;
        }
    }

}
