package com.example.wlwlxgg.mymusic.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.constant.CodeMessage;
import com.example.wlwlxgg.mymusic.constant.PlayStatus;
import com.example.wlwlxgg.mymusic.constant.PrefsKey;
import com.example.wlwlxgg.mymusic.fragment.BaseFragment;
import com.example.wlwlxgg.mymusic.fragment.HistoryFragment;
import com.example.wlwlxgg.mymusic.fragment.HomeFragment;
import com.example.wlwlxgg.mymusic.fragment.LoveFragment;
import com.example.wlwlxgg.mymusic.fragment.SearchFragment;
import com.example.wlwlxgg.mymusic.http.result.MusicInfo;
import com.example.wlwlxgg.mymusic.service.MusicPlayService;
import com.example.wlwlxgg.mymusic.utils.PrefsUtil;
import com.example.wlwlxgg.mymusic.utils.StatusBar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SearchFragment.OnMusicGetListener {

    private final static String TAG = MainActivity.class.getSimpleName();

    private FrameLayout frameLayout;
    private Button play, pause, next;
    private ImageView image;
    private TextView title, artist;
    private MusicInfo musicInfo = null;
    //默认主fragment为home页
    private int index;
    //Fragment定义
    private HomeFragment mHomeFragment;
    private HistoryFragment mHistoryFragment;
    private LoveFragment mLoveFragment;
    private PrefsUtil prefsUtil;
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.mipmap.album)
            .showImageOnFail(R.mipmap.album)
            .showImageOnLoading(R.mipmap.album)
            .cacheInMemory(false)
            .cacheOnDisk(true)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBar.compat(this, Color.BLACK);
        initView();
        initData();
    }

    private void initView() {
        play = (Button) findViewById(R.id.song_play);
        pause = (Button) findViewById(R.id.song_pause);
        next = (Button) findViewById(R.id.song_next);
        image = (ImageView) findViewById(R.id.image);
        title = (TextView) findViewById(R.id.song_title);
        artist = (TextView) findViewById(R.id.song_artist);
        frameLayout = (FrameLayout) findViewById(R.id.main_content);
    }

    private void initData() {
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        next.setOnClickListener(this);
        image.setOnClickListener(this);
        prefsUtil = PrefsUtil.getInstance();
        TabFragment(CodeMessage.FRAGMENT_HOME);
    }

    /**
     * 切换fragment
     */
    private void TabFragment(int tabTag) {
        FragmentManager fm = getSupportFragmentManager();
        // 开启fragment的事务
        FragmentTransaction transaction = fm.beginTransaction();
        // 创建所有的Fragment并加入事务
        createFragment(fm, transaction);
        switch (tabTag) {
            case CodeMessage.FRAGMENT_HOME:
                transaction.show(mHomeFragment);
                _BaseFragment = mHomeFragment;
                break;
            case CodeMessage.FRAGMENT_HISTORY:
                transaction.show(mHistoryFragment);
                _BaseFragment = mHistoryFragment;
                break;
            case CodeMessage.FRAGMENT_LOVE:
                transaction.show(mLoveFragment);
                _BaseFragment = mLoveFragment;
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (prefsUtil.getInt(PrefsKey.PLAY_STATUS) == PlayStatus.PAUSE) {
            play.setVisibility(View.VISIBLE);
            pause.setVisibility(View.GONE);
        } else {
            play.setVisibility(View.GONE);
            pause.setVisibility(View.VISIBLE);
        }
    }

    private void createFragment(FragmentManager fm, FragmentTransaction transaction) {
        Fragment fragment;

        fragment = fm.findFragmentByTag(CodeMessage.FRAGMENT_HOME + "");
        if (fragment != null) {
            mHomeFragment = (HomeFragment) fragment;
        }
        fragment = fm.findFragmentByTag(CodeMessage.FRAGMENT_HISTORY + "");
        if (fragment != null) {
            mHistoryFragment = (HistoryFragment) fragment;
        }
        fragment = fm.findFragmentByTag(CodeMessage.FRAGMENT_LOVE + "");
        if (fragment != null) {
            mLoveFragment = (LoveFragment) fragment;
        }


        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
            transaction.add(R.id.main_content, mHomeFragment, CodeMessage.FRAGMENT_HOME + "");
        }
        if (mHistoryFragment == null) {
            mHistoryFragment = new HistoryFragment();
            transaction.add(R.id.main_content, mHistoryFragment, CodeMessage.FRAGMENT_HISTORY + "");
        }
        if (mLoveFragment == null) {
            mLoveFragment = new LoveFragment();
            transaction.add(R.id.main_content, mLoveFragment, CodeMessage.FRAGMENT_LOVE + "");

        }
        transaction.hide(mHomeFragment);
        transaction.hide(mHistoryFragment);
        transaction.hide(mLoveFragment);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.song_play:
                if (musicInfo != null) {
                    play.setVisibility(View.GONE);
                    pause.setVisibility(View.VISIBLE);
                    if (!prefsUtil.getBoolean(PrefsKey.IS_PAUSED))
                        prefsUtil.putInt(PrefsKey.PLAY_STATUS, PlayStatus.PLAY);
                    else
                        prefsUtil.putInt(PrefsKey.PLAY_STATUS, PlayStatus.CONTINUE);
                    intent.putExtra(PrefsKey.MUSIC_INFO, musicInfo);
                    intent.setClass(MainActivity.this, MusicPlayService.class);
                    startService(intent);
                }
                break;
            case R.id.song_pause:
                if (musicInfo != null) {
                    pause.setVisibility(View.GONE);
                    play.setVisibility(View.VISIBLE);
                    prefsUtil.putInt(PrefsKey.PLAY_STATUS, PlayStatus.PAUSE);
                    intent.putExtra(PrefsKey.MUSIC_INFO, musicInfo);
                    intent.setClass(MainActivity.this, MusicPlayService.class);
                    startService(intent);
                    prefsUtil.putBoolean(PrefsKey.IS_PAUSED, true);
                }
                break;
            case R.id.song_next:
                break;
            case R.id.image:
                intent.setClass(MainActivity.this, PlayActivity.class);
                if (musicInfo != null)
                    intent.putExtra(PrefsKey.MUSIC_INFO, musicInfo);
                startActivity(intent);
        }
    }

    @Override
    public void onMusicGet(MusicInfo musicInfo) {
        this.musicInfo = musicInfo;
        ImageLoader.getInstance().displayImage(
                musicInfo.getSonginfo().getPic_small(), image, options);
        title.setText(musicInfo.getSonginfo().getTitle());
        artist.setText(musicInfo.getSonginfo().getAuthor());
        if (prefsUtil.getInt(PrefsKey.PLAY_STATUS) == PlayStatus.PLAY) {
            play.setVisibility(View.GONE);
            pause.setVisibility(View.VISIBLE);
            Intent intent = new Intent();
            intent.putExtra(PrefsKey.MUSIC_INFO, musicInfo);
            intent.setClass(MainActivity.this, MusicPlayService.class);
            startService(intent);
            prefsUtil.putBoolean(PrefsKey.IS_PAUSED, false);
        }
    }

    public void setIndex(int fragmentIndex) {
        this.index = fragmentIndex;
        TabFragment(index);
    }


    private BaseFragment _BaseFragment;
    @Override
    public void onBackPressed() {
        if (_BaseFragment instanceof HistoryFragment) {
            if (mHistoryFragment.onBackPressed()) {
                Log.i(TAG, "HistoryFragment返回键拦截");
                return;
            }
        }
        if (_BaseFragment instanceof LoveFragment) {
            if (mLoveFragment.onBackPressed()) {
                Log.i(TAG, "HistoryFragment返回键拦截");
                return;
            }
        }
        super.onBackPressed();
    }

}
