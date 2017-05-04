package com.example.wlwlxgg.mymusic.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.adapter.IsDownAdapter;
import com.example.wlwlxgg.mymusic.adapter.SearchAdapter;
import com.example.wlwlxgg.mymusic.application.MyApplication;
import com.example.wlwlxgg.mymusic.entity.MusicDownloadEntity;
import com.example.wlwlxgg.mymusic.greendao.DaoSession;
import com.example.wlwlxgg.mymusic.greendao.HistoryEntityDao;
import com.example.wlwlxgg.mymusic.greendao.MusicDownloadEntityDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wlwlxgg on 2017/4/24.
 */

public class DownloadActivity extends Activity implements View.OnClickListener, ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private List<View> list;
    private ArrayList<MusicDownloadEntity> entities;
    private View isDownView, hasDownView;
    private MyViewPagerAdapter viewPagerAdapter;
    private TextView tx_1, tx_2;
    private ListView isDownListView, hasDownListView;
    private IsDownAdapter isDownAdapter;
    private DaoSession daoSession;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();
        initData();
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
    }
    private void initData() {
        tx_1.setOnClickListener(this);
        tx_2.setOnClickListener(this);
        daoSession = MyApplication.getInstances().getDaoSession();
        entities = new ArrayList<>();
        entities = (ArrayList<MusicDownloadEntity>)daoSession.getMusicDownloadEntityDao().
                queryBuilder().orderDesc(MusicDownloadEntityDao.Properties.Time).build().list();
        isDownAdapter = new IsDownAdapter(entities, this);
        isDownListView.setAdapter(isDownAdapter);
        list = new ArrayList<>();
        list.add(isDownView);
        list.add(hasDownView);
        viewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tx_1:
                viewPager.setCurrentItem(0);
                tx_1.setTextColor(ContextCompat.getColor(this,R.color.color_text_black));
                tx_2.setTextColor(ContextCompat.getColor(this,R.color.color_text_grey));
                break;
            case R.id.tx_2:
                viewPager.setCurrentItem(1);
                tx_2.setTextColor(ContextCompat.getColor(this,R.color.color_text_black));
                tx_1.setTextColor(ContextCompat.getColor(this,R.color.color_text_grey));
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
                tx_1.setTextColor(ContextCompat.getColor(this,R.color.color_text_black));
                tx_2.setTextColor(ContextCompat.getColor(this,R.color.color_text_grey));
                break;
            case 1:
                tx_2.setTextColor(ContextCompat.getColor(this,R.color.color_text_black));
                tx_1.setTextColor(ContextCompat.getColor(this,R.color.color_text_grey));
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
