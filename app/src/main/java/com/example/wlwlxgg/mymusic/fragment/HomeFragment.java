package com.example.wlwlxgg.mymusic.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.constant.CodeMessage;

import java.util.ArrayList;

/**
 * Created by wlwlxgg on 2017/4/26.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener{
    private ArrayList<Fragment> mFragments;
    private Button my, recommend, search;
    public static ViewPager viewPager;
    public static HomeFragment instance;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private ImageView header;
    private TextView user_name;

    public static HomeFragment getInstance(){
        return instance;
    }

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.title_main, null);
        return view;
    }

    @Override
    public void initData(View view, Bundle savedInstanceState) {
        instance = this;
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        my = (Button) view.findViewById(R.id.my);
        recommend = (Button) view.findViewById(R.id.recommend);
        search = (Button) view.findViewById(R.id.search);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        drawerListView = (ListView) view.findViewById(R.id.left_drawer);

        mFragments = new ArrayList<>();
        mFragments.add(new MyFragment());
        mFragments.add(new RecommendFragment());
        mFragments.add(new SearchFragment());
        viewPager.setAdapter(new MyFragmentAdapter(getFragmentManager(), mFragments));
        viewPager.setCurrentItem(CodeMessage.FRAGMENT_RECOMMEND);
        my.setOnClickListener(this);
        recommend.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    private class MyFragmentAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments;

        public MyFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my:
                setCurrentItem(CodeMessage.FRAGMENT_MY);
                break;
            case R.id.recommend:
                setCurrentItem(CodeMessage.FRAGMENT_RECOMMEND);
                break;
            case R.id.search:
                setCurrentItem(CodeMessage.FRAGMENT_SEARCH);
                break;
        }
    }

    public void setCurrentItem(int index) {
        switch (index) {
            case CodeMessage.FRAGMENT_MY:
                viewPager.setCurrentItem(CodeMessage.FRAGMENT_MY);
                break;
            case CodeMessage.FRAGMENT_RECOMMEND:
                viewPager.setCurrentItem(CodeMessage.FRAGMENT_RECOMMEND);
                break;
            case CodeMessage.FRAGMENT_SEARCH:
                viewPager.setCurrentItem(CodeMessage.FRAGMENT_SEARCH);
                break;
        }
    }
}
