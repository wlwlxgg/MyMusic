package com.example.wlwlxgg.mymusic.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.adapter.HasDownAdapter;
import com.example.wlwlxgg.mymusic.adapter.IsDownAdapter;
import com.example.wlwlxgg.mymusic.adapter.SearchAdapter;
import com.example.wlwlxgg.mymusic.application.MyApplication;
import com.example.wlwlxgg.mymusic.constant.DownloadStatus;
import com.example.wlwlxgg.mymusic.entity.MusicDownloadEntity;
import com.example.wlwlxgg.mymusic.greendao.DaoSession;
import com.example.wlwlxgg.mymusic.greendao.HistoryEntityDao;
import com.example.wlwlxgg.mymusic.greendao.MusicDownloadEntityDao;
import com.example.wlwlxgg.mymusic.utils.CommonUtils;
import com.example.wlwlxgg.mymusic.utils.StatusBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wlwlxgg on 2017/4/24.
 */

public class DownloadActivity extends Activity implements View.OnClickListener, ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private List<View> list;
    private ArrayList<MusicDownloadEntity> entities;
    private ArrayList<MusicDownloadEntity> isDownEntities;
    private ArrayList<MusicDownloadEntity> hasDownEntities;
    private View isDownView, hasDownView;
    private MyViewPagerAdapter viewPagerAdapter;
    private TextView tx_1, tx_2;
    private Button back;
    private ListView isDownListView, hasDownListView;
    private IsDownAdapter isDownAdapter;
    private HasDownAdapter hasDownAdapter;
    private DaoSession daoSession;
    private ImageView cursor;
    private int lastLeft;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    MusicDownloadEntity entity = msg.getData().getParcelable("object");
                    isDownEntities.remove(entity);
                    hasDownEntities.add(entity);
                    isDownAdapter.notifyDataSetChanged();
                    hasDownAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        StatusBar.compat(this, Color.BLACK);
        initView();
    }
    private void initView() {
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        tx_1 = (TextView) findViewById(R.id.tx_1);
        tx_2 = (TextView) findViewById(R.id.tx_2);
        LayoutInflater inflater = getLayoutInflater();
        isDownView = inflater.inflate(R.layout.listview_isdown, null);
        hasDownView = inflater.inflate(R.layout.listview_hasdown, null);
        isDownListView = (ListView) isDownView.findViewById(R.id.is_down);
        hasDownListView = (ListView) hasDownView.findViewById(R.id.has_down);
        cursor = (ImageView) findViewById(R.id.cursor);
        back = (Button) findViewById(R.id.back);
    }
    private void initData() {
        tx_1.setOnClickListener(this);
        tx_2.setOnClickListener(this);
        back.setOnClickListener(this);
        setCursorPosition(tx_1);
        daoSession = MyApplication.getInstances().getDaoSession();
        entities = new ArrayList<>();
        entities = (ArrayList<MusicDownloadEntity>)daoSession.getMusicDownloadEntityDao().
                queryBuilder().orderDesc(MusicDownloadEntityDao.Properties.Time).build().list();
        isDownEntities = new ArrayList<>();
        hasDownEntities = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getStatus() != DownloadStatus.FINISH)
                isDownEntities.add(entities.get(i));
            else hasDownEntities.add(entities.get(i));
        }
        isDownAdapter = new IsDownAdapter(this, isDownEntities, mHandler);
        isDownListView.setAdapter(isDownAdapter);
        hasDownAdapter = new HasDownAdapter(this, hasDownEntities);
        hasDownListView.setAdapter(hasDownAdapter);
        list = new ArrayList<>();
        list.add(isDownView);
        list.add(hasDownView);
        viewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initData();
    }


    private void setCursorPosition(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(view.getRight() - view.getLeft(), 4);
        ll.setMargins(location[0], 0, 0, 0);
        cursor.setLayoutParams(ll);
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(lastLeft, location[0], 0f, 0f);
        animationSet.addAnimation(translateAnimation);
        animationSet.setFillBefore(true);
        animationSet.setFillAfter(true);
        animationSet.setDuration(300);
        cursor.startAnimation(translateAnimation);
        cursor.clearAnimation();
        lastLeft = view.getLeft();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tx_1:
                viewPager.setCurrentItem(0);
                setCursorPosition(tx_1);
                break;
            case R.id.tx_2:
                viewPager.setCurrentItem(1);
                setCursorPosition(tx_2);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private class MyViewPagerAdapter extends PagerAdapter{
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                setCursorPosition(tx_1);
                break;
            case 1:
                setCursorPosition(tx_2);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
