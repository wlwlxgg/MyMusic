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
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.application.MyApplication;
import com.example.wlwlxgg.mymusic.constant.CodeMessage;
import com.example.wlwlxgg.mymusic.constant.PlayStatus;
import com.example.wlwlxgg.mymusic.constant.PrefsKey;
import com.example.wlwlxgg.mymusic.entity.MusicDownloadEntity;
import com.example.wlwlxgg.mymusic.entity.MusicLoveEntity;
import com.example.wlwlxgg.mymusic.greendao.DaoSession;
import com.example.wlwlxgg.mymusic.http.HttpUtils;
import com.example.wlwlxgg.mymusic.http.result.MusicInfo;
import com.example.wlwlxgg.mymusic.service.MusicPlayService;
import com.example.wlwlxgg.mymusic.utils.CommonUtils;
import com.example.wlwlxgg.mymusic.utils.PrefsUtil;
import com.example.wlwlxgg.mymusic.utils.StatusBar;

import java.io.File;

import me.zhengken.lyricview.LyricView;

/**
 * Created by wlwlxgg on 2017/4/17.
 */

public class PlayActivity extends Activity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener,
        LyricView.OnPlayerClickListener {

    private LyricView lyricView;
    private TextView title, author, currentTime, totalTime;
    private Button play, last, next, pause, download, love;
    private SeekBar seekBar;
    private MusicInfo musicInfo;
    private File lyricFile;
    private int mProgress;
    private int musicLength;
    private String minTotal, secondTotal;
    private String minCurrent, secondCurrent;
    private boolean isGetLength;
    private PrefsUtil prefsUtil;
    private DaoSession daoSession = MyApplication.getInstances().getDaoSession();

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
            isGetLength = false;
            //注册回调接口来接收下载进度的变化
            myService.setOnProgressListener(new MusicPlayService.OnProgressListener() {

                @Override
                public void onProgress(int progress, int length) {
                    mProgress = progress;
                    if (length != 0 && !isGetLength) {
                        isGetLength = true;
                        musicLength = length;
                    }
                    mHandler.sendEmptyMessage(CodeMessage.LYRIC_PROGRESS);
                }
            });
        }
    };

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
                    if (musicLength != 0) {
                        if (musicLength / 1000 / 60 < 10)
                            minTotal = "0" + musicLength / 1000 / 60;
                        else minTotal = musicLength / 1000 / 60 + "";
                        if (musicLength / 1000 % 60 < 10)
                            secondTotal = "0" + musicLength / 1000 % 60;
                        else secondTotal = musicLength / 1000 % 60 + "";
                        totalTime.setText(minTotal + ":" + secondTotal);
                        if (mProgress / 1000 / 60 < 10)
                            minCurrent = "0" + mProgress / 1000 / 60;
                        else minCurrent = mProgress / 1000 / 60 + "";
                        if (mProgress / 1000 % 60 < 10)
                            secondCurrent = "0" + mProgress / 1000 % 60;
                        else secondCurrent = mProgress / 1000 % 60 + "";
                        currentTime.setText(minCurrent + ":" + secondCurrent);
                        seekBar.setProgress((int) (mProgress / (float) musicLength * 100));
                    }
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
        download = (Button) findViewById(R.id.download);
        currentTime = (TextView) findViewById(R.id.currentTime);
        totalTime = (TextView) findViewById(R.id.totalTime);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
    }

    private void initData() {
        prefsUtil = PrefsUtil.getInstance();
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        love.setOnClickListener(this);
        download.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        lyricView.setOnPlayerClickListener(this);
        Intent intent = getIntent();
        if (intent.getSerializableExtra(PrefsKey.MUSIC_INFO) != null) {
            musicInfo = (MusicInfo) intent.getSerializableExtra(PrefsKey.MUSIC_INFO);
            lyricFile = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "MyMusic/Lrc/" + musicInfo.getSonginfo().getSong_id() + ".lrc");
            if (!lyricFile.exists())
                HttpUtils.getLyric(musicInfo, mHandler);
            else mHandler.sendEmptyMessage(CodeMessage.NET_SUCCESSFUL);
            title.setText(musicInfo.getSonginfo().getTitle());
            author.setText(musicInfo.getSonginfo().getAuthor());
            if (prefsUtil.getInt(PrefsKey.PLAY_STATUS) == PlayStatus.PLAY || prefsUtil.getInt(PrefsKey.PLAY_STATUS) == PlayStatus.CONTINUE) {
                play.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
            } else {
                play.setVisibility(View.VISIBLE);
                pause.setVisibility(View.GONE);
            }
            intent = new Intent(PlayActivity.this, MusicPlayService.class);
            bindService(intent, conn, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (myService != null)
            unbindService(conn);
        super.onDestroy();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (musicLength != 0) {
            int targetProgress = (int) (seekBar.getProgress() / 100.0 * musicLength);
            myService.mediaPlayer.seekTo(targetProgress);
        }
    }

    @Override
    public void onPlayerClicked(long l, String s) {
        myService.mediaPlayer.seekTo((int) l);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.play:
                if (musicInfo != null) {
                    play.setVisibility(View.GONE);
                    pause.setVisibility(View.VISIBLE);
                    if (!prefsUtil.getBoolean(PrefsKey.IS_PAUSED))
                        prefsUtil.putInt(PrefsKey.PLAY_STATUS, PlayStatus.PLAY);
                    else
                        prefsUtil.putInt(PrefsKey.PLAY_STATUS, PlayStatus.CONTINUE);
                    intent.putExtra(PrefsKey.MUSIC_INFO, musicInfo);
                    intent.setClass(PlayActivity.this, MusicPlayService.class);
                    startService(intent);
                }
                break;
            case R.id.pause:
                if (musicInfo != null) {
                    pause.setVisibility(View.GONE);
                    play.setVisibility(View.VISIBLE);
                    prefsUtil.putInt(PrefsKey.PLAY_STATUS, PlayStatus.PAUSE);
                    intent.putExtra(PrefsKey.MUSIC_INFO, musicInfo);
                    intent.setClass(PlayActivity.this, MusicPlayService.class);
                    startService(intent);
                    prefsUtil.putBoolean(PrefsKey.IS_PAUSED, true);
                }
                break;
            case R.id.love:
                if (musicInfo != null) {
                    MusicLoveEntity musicLoveEntity = CommonUtils.MusicInfo2MusicLoveEntity(musicInfo);
                    musicLoveEntity.setTime(String.valueOf(System.currentTimeMillis()));
                    daoSession.getMusicLoveEntityDao().insertOrReplaceInTx(musicLoveEntity);
                    Toast.makeText(this, R.string.add_to_love, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.download:
                if (musicInfo != null) {
                    MusicDownloadEntity downloadEntity = CommonUtils.MusicInfo2MusicDownloadEntity(musicInfo);
                    File file1 = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "MyMusic/" + "Music/");//仅创建路径的File对象
                    if (!file1.exists()) {
                        file1.mkdir();//如果路径不存在就先创建路径
                    }
                    File futureStudioIconFile = new File(file1, musicInfo.getSonginfo().getSong_id() + ".mp3");
                    downloadEntity.setBaseUrl(CommonUtils.getBaseUrl(musicInfo.getBitrate().getFile_link()));
                    downloadEntity.setSavePath(futureStudioIconFile.getAbsolutePath());
                    downloadEntity.setTime(String.valueOf(System.currentTimeMillis()));
                    downloadEntity.setReadLength(0);
                    daoSession.getMusicDownloadEntityDao().insertOrReplaceInTx(downloadEntity);
                    Toast.makeText(this, R.string.add_to_download, Toast.LENGTH_SHORT).show();
                }
        }
    }
}
