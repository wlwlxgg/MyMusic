package com.example.wlwlxgg.mymusic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.activity.MainActivity;
import com.example.wlwlxgg.mymusic.adapter.LoveAdapter;
import com.example.wlwlxgg.mymusic.application.MyApplication;
import com.example.wlwlxgg.mymusic.constant.CodeMessage;
import com.example.wlwlxgg.mymusic.constant.PlayStatus;
import com.example.wlwlxgg.mymusic.constant.PrefsKey;
import com.example.wlwlxgg.mymusic.entity.MusicLoveEntity;
import com.example.wlwlxgg.mymusic.greendao.MusicLoveEntityDao;
import com.example.wlwlxgg.mymusic.http.HttpUtils;
import com.example.wlwlxgg.mymusic.http.result.MusicInfo;
import com.example.wlwlxgg.mymusic.utils.PrefsUtil;

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
    private MusicInfo musicInfo;
    private HistoryFragment.OnMusicGetListener musicGetListener = null;
    private android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CodeMessage.GET_MUSIC:
                    Bundle bundle = msg.getData();
                    musicInfo = (MusicInfo) bundle.getSerializable(PrefsKey.MUSIC_INFO);
                    PrefsUtil.getInstance().putInt(PrefsKey.PLAY_STATUS, PlayStatus.PLAY);
                    if (musicGetListener != null) {
                        musicGetListener.onMusicGet(musicInfo);
                    }
                    break;
            }
        }
    };

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
        loveListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HttpUtils.getMusicWithoutSaveData(musicLoveEntities.get(position).getSongId(), mHandler);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        musicLoveEntityDao = MyApplication.getInstances().getDaoSession().getMusicLoveEntityDao();
        musicLoveEntities = (ArrayList<MusicLoveEntity>) musicLoveEntityDao.queryBuilder().orderDesc(MusicLoveEntityDao.Properties.Time).build().list();
        adapter = new LoveAdapter(getActivity(), musicLoveEntities);
        loveListView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            musicGetListener = (HistoryFragment.OnMusicGetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Listener");
        }
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

    public boolean onBackPressed() {
        back.performClick();
        return true;
    }
    public interface OnMusicGetListener{
        void onMusicGet(MusicInfo musicInfo);
    }
}
