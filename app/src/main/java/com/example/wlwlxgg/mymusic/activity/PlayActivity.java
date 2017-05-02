package com.example.wlwlxgg.mymusic.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.application.MyApplication;
import com.example.wlwlxgg.mymusic.constant.CodeMessage;
import com.example.wlwlxgg.mymusic.constant.PlayStatus;
import com.example.wlwlxgg.mymusic.entity.MusicLoveEntity;
import com.example.wlwlxgg.mymusic.greendao.DaoSession;
import com.example.wlwlxgg.mymusic.http.HttpUtils;
import com.example.wlwlxgg.mymusic.http.result.MusicInfo;
import com.example.wlwlxgg.mymusic.service.MusicPlayService;
import com.example.wlwlxgg.mymusic.utils.CommonUtils;
import com.example.wlwlxgg.mymusic.utils.StatusBar;

import java.io.File;

import me.zhengken.lyricview.LyricView;

/**
 * Created by wlwlxgg on 2017/4/17.
 */

public class PlayActivity extends Activity implements View.OnClickListener {
    private MusicPlayService myService;
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //返回一个MsgService对象
            myService = ((MusicPlayService.MyBinder) service).getService();
            myService.setProgress();
            //注册回调接口来接收下载进度的变化
            myService.setOnProgressListener(new MusicPlayService.OnProgressListener() {

                @Override
                public void onProgress(int progress) {
                    mProgress = progress;
                    mHandler.sendEmptyMessage(CodeMessage.LYRIC_PROGRESS);
                }
            });
        }
    };
    private LyricView lyricView;
    private TextView title, author;
    private Button play, last, next, pause, download, love;
    private MusicInfo musicInfo;
    private File lyricFile;
    private int mProgress;
    private boolean isPaused;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CodeMessage.NET_SUCCESSFUL:
                    if (lyricFile.exists())
                        lyricView.setLyricFile(lyricFile);
                    break;
                case CodeMessage.LYRIC_PROGRESS:
                    lyricView.setCurrentTimeMillis(mProgress);
                    break;

            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        StatusBar.compat(this, Color.BLACK);
        initView();
        initData();
    }

    private void initView() {
        lyricView = (LyricView) findViewById(R.id.custom_lyric_view);
        title = (TextView) findViewById(R.id.title);
        author = (TextView) findViewById(R.id.author);
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        love = (Button) findViewById(R.id.love);
    }

    private void initData() {
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        love.setOnClickListener(this);
        Intent intent = getIntent();
        isPaused = false;
        if (intent.getSerializableExtra("MusicInfo") != null) {
            musicInfo = (MusicInfo) intent.getSerializableExtra("MusicInfo");
            lyricFile = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "MyMusic/Lrc/" + musicInfo.getSonginfo().getSong_id() + ".lrc");
            if (!lyricFile.exists())
                HttpUtils.getLyric(musicInfo, mHandler);
            else mHandler.sendEmptyMessage(CodeMessage.NET_SUCCESSFUL);
            title.setText(musicInfo.getSonginfo().getTitle());
            author.setText(musicInfo.getSonginfo().getAuthor());
            play.setVisibility(View.GONE);
            pause.setVisibility(View.VISIBLE);
        }
        intent = new Intent(PlayActivity.this, MusicPlayService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.play:
                if (musicInfo != null) {
                    play.setVisibility(View.GONE);
                    pause.setVisibility(View.VISIBLE);
                    if (!isPaused)
                        intent.putExtra("Msg", PlayStatus.PLAY);
                    else
                        intent.putExtra("Msg", PlayStatus.CONTINUE);
                    intent.putExtra("MusicInfo", musicInfo);
                    intent.setClass(PlayActivity.this, MusicPlayService.class);
                    startService(intent);
                }
                break;
            case R.id.pause:
                if (musicInfo != null) {
                    pause.setVisibility(View.GONE);
                    play.setVisibility(View.VISIBLE);
                    intent.putExtra("Msg", PlayStatus.PAUSE);
                    intent.putExtra("MusicInfo", musicInfo);
                    intent.setClass(PlayActivity.this, MusicPlayService.class);
                    startService(intent);
                    isPaused = true;
                }
                break;
            case R.id.love:
                if (musicInfo != null) {
                    MusicLoveEntity musicLoveEntity = CommonUtils.MusicInfo2MusicLoveEntity(musicInfo);
                    DaoSession daoSession = MyApplication.getInstances().getDaoSession();
                    daoSession.getMusicLoveEntityDao().insertOrReplaceInTx(musicLoveEntity);
                }
        }
    }
}
