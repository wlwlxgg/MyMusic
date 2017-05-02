package com.example.wlwlxgg.mymusic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.activity.MainActivity;
import com.example.wlwlxgg.mymusic.adapter.LoveAdapter;
import com.example.wlwlxgg.mymusic.application.MyApplication;
import com.example.wlwlxgg.mymusic.constant.CodeMessage;
import com.example.wlwlxgg.mymusic.entity.MusicLoveEntity;
import com.example.wlwlxgg.mymusic.greendao.MusicLoveEntityDao;

import java.util.ArrayList;

/**
 * Created by wlwlxgg on 2017/4/26.
 */

public class LoveFragment extends BaseFragment implements View.OnClickListener{
    private ListView loveListView;
    private LoveAdapter adapter;
    private ArrayList<MusicLoveEntity> musicLoveEntities;
    private MusicLoveEntityDao musicLoveEntityDao;
    private Button back;

    @Override
    public void onResume() {
        super.onResume();
        musicLoveEntityDao = MyApplication.getInstances().getDaoSession().getMusicLoveEntityDao();
        musicLoveEntities = (ArrayList<MusicLoveEntity>) musicLoveEntityDao.queryBuilder().orderDesc(MusicLoveEntityDao.Properties.Time).build().list();
        adapter = new LoveAdapter(getActivity(), musicLoveEntities);
        loveListView.setAdapter(adapter);
    }


    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_love, null);
        return view;
    }

    @Override
    public void initData(View view, Bundle savedInstanceState) {
        loveListView= (ListView) view.findViewById(R.id.historyList);
        back = (Button) view.findViewById(R.id.back);
        musicLoveEntityDao = MyApplication.getInstances().getDaoSession().getMusicLoveEntityDao();
        musicLoveEntities = (ArrayList<MusicLoveEntity>) musicLoveEntityDao.queryBuilder().orderDesc(MusicLoveEntityDao.Properties.Time).build().list();
        adapter = new LoveAdapter(getActivity(), musicLoveEntities);
        loveListView.setAdapter(adapter);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                MainActivity activity = (MainActivity)getActivity();
                activity.setIndex(CodeMessage.FRAGMENT_HOME);
                HomeFragment.getIstance().setCurrentItem(CodeMessage.FRAGMENT_MY);
                break;
        }
    }

    public boolean onBackPressed() {
        back.performClick();
        return true;
    }

}
