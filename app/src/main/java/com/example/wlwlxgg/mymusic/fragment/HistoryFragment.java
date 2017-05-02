package com.example.wlwlxgg.mymusic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.activity.MainActivity;
import com.example.wlwlxgg.mymusic.adapter.HistoryAdapter;
import com.example.wlwlxgg.mymusic.application.MyApplication;
import com.example.wlwlxgg.mymusic.constant.CodeMessage;
import com.example.wlwlxgg.mymusic.entity.HistoryEntity;
import com.example.wlwlxgg.mymusic.greendao.HistoryEntityDao;

import java.util.ArrayList;

/**
 * Created by wlwlxgg on 2017/4/26.
 */

public class HistoryFragment extends BaseFragment implements View.OnClickListener{

    private ListView historyListView;
    private HistoryAdapter adapter;
    private ArrayList<HistoryEntity> historyList;
    private HistoryEntityDao historyDao;
    private Button back;

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_history, null);
        return view;
    }

    @Override
    public void initData(View view, Bundle savedInstanceState) {
        historyListView = (ListView) view.findViewById(R.id.historyList);
        back = (Button) view.findViewById(R.id.back);
        historyDao = MyApplication.getInstances().getDaoSession().getHistoryEntityDao();
        historyList = (ArrayList<HistoryEntity>) historyDao.queryBuilder().orderDesc(HistoryEntityDao.Properties.Time).build().list();
        adapter = new HistoryAdapter(getActivity(), historyList);
        historyListView.setAdapter(adapter);
        back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                MainActivity activity = (MainActivity)getActivity();
                activity.setIndex(CodeMessage.FRAGMENT_HOME);
                HomeFragment.getInstance().setCurrentItem(CodeMessage.FRAGMENT_MY);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        historyDao = MyApplication.getInstances().getDaoSession().getHistoryEntityDao();
        historyList = (ArrayList<HistoryEntity>) historyDao.queryBuilder().orderDesc(HistoryEntityDao.Properties.Time).build().list();
        adapter = new HistoryAdapter(getActivity(), historyList);
        historyListView.setAdapter(adapter);
    }


    public boolean onBackPressed(){
        back.performClick();
        return true;
    }
}
