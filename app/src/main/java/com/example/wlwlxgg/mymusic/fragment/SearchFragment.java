package com.example.wlwlxgg.mymusic.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.adapter.HistoryAdapter;
import com.example.wlwlxgg.mymusic.adapter.SearchAdapter;
import com.example.wlwlxgg.mymusic.application.MyApplication;
import com.example.wlwlxgg.mymusic.constant.CodeMessage;
import com.example.wlwlxgg.mymusic.constant.PlayStatus;
import com.example.wlwlxgg.mymusic.constant.PrefsKey;
import com.example.wlwlxgg.mymusic.entity.HistoryEntity;
import com.example.wlwlxgg.mymusic.greendao.HistoryEntityDao;
import com.example.wlwlxgg.mymusic.http.HttpUtils;
import com.example.wlwlxgg.mymusic.http.result.MusicInfo;
import com.example.wlwlxgg.mymusic.http.result.SearchResult;
import com.example.wlwlxgg.mymusic.utils.PrefsUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

/**
 * Created by wlwlxgg on 2017/4/15.
 */

public class SearchFragment extends BaseFragment implements View.OnClickListener{

    private Context mContex;
    private EditText edit;
    private TextView cancle, history;
    private int page_num;
    private ArrayList<SearchResult.ResultBean.SongInfoBean.SongListBean> searchResults;
    private ArrayList<HistoryEntity> historyEntities;
    private PullToRefreshListView resultList;
    private ListView historyList;
    private SearchAdapter searchAdapter;
    private HistoryAdapter historyAdapter;
    private OnMusicGetListener musicGetListener;
    private MusicInfo musicInfo;
    private HistoryEntityDao historyEntityDao;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CodeMessage.NET_SUCCESSFUL:
                    searchAdapter.notifyDataSetChanged();
                    break;
                case CodeMessage.NET_FAILURE:
                    Toast.makeText(context, R.string.no_content, Toast.LENGTH_SHORT).show();
                    break;
                case CodeMessage.GET_MUSIC:
                    Bundle bundle = msg.getData();
                    musicInfo = (MusicInfo) bundle.getSerializable(PrefsKey.MUSIC_INFO);
                    PrefsUtil.getInstance().putInt(PrefsKey.PLAY_STATUS, PlayStatus.PLAY);
                    if (musicGetListener != null) {
                        musicGetListener.onMusicGet(musicInfo);
                    }
                    break;

            }
            super.handleMessage(msg);
        }
    };

    @Override
    public View initView(LayoutInflater inflater) {
        View mView = inflater.inflate(R.layout.fragment_search, null);
        edit = (EditText)mView.findViewById(R.id.edit);
        resultList = (PullToRefreshListView)mView.findViewById(R.id.list_view);
        cancle = (TextView) mView.findViewById(R.id.cancle);
        history = (TextView) mView.findViewById(R.id.history);
        historyList = (ListView) mView.findViewById(R.id.historyList);
        return mView;
    }

    @Override
    public void initData(View view, Bundle savedInstanceState) {
        historyEntities = new ArrayList<>();
        historyEntityDao = MyApplication.getInstances().getDaoSession().getHistoryEntityDao();
        historyEntities = (ArrayList<HistoryEntity>) historyEntityDao.queryBuilder().orderDesc(HistoryEntityDao.Properties.Time).build().list();
        mContex = getActivity();
        searchResults = new ArrayList<>();
        edit.addTextChangedListener(textWatch);
        searchAdapter = new SearchAdapter(mContex, searchResults);
        historyAdapter = new HistoryAdapter(mContex, historyEntities);
        historyList.setVisibility(View.VISIBLE);
        resultList.setVisibility(View.GONE);
        historyList.setAdapter(historyAdapter);
        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HttpUtils.getMusic(historyEntities.get(position).getSongId(), mHandler);
            }
        });
        resultList.setAdapter(searchAdapter);
        cancle.setOnClickListener(this);
        resultList.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        resultList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(mContex, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                new GetDataTask().execute();
            }
        });
        resultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HttpUtils.getMusic(searchResults.get(i - 1).getSong_id(), mHandler);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            musicGetListener = (OnMusicGetListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Listener");
        }
    }

    //输入内容后，执行列表隐藏
    private boolean editTag = true;

    /** 监听输入状态*/
    private TextWatcher textWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(s.toString())) {
                if (editTag) {
                    historyList.setVisibility(View.GONE);
                    resultList.setVisibility(View.VISIBLE);
                    editTag = false;
                }
                history.setText(R.string.search_result);
                cancle.setVisibility(View.VISIBLE);
                page_num = 1;
                HttpUtils.getSearchResult(searchResults, edit.getText().toString(), page_num, mHandler);
            }else {
                history.setText(R.string.search_history);
                cancle.setVisibility(View.GONE);
                searchResults.clear();
                searchAdapter.notifyDataSetChanged();
            }
        }
    };

    private class GetDataTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            page_num++;
            HttpUtils.getSearchResult(searchResults, edit.getText().toString(), page_num, mHandler);
            resultList.onRefreshComplete();
        }
    }

    public interface OnMusicGetListener{
        void onMusicGet(MusicInfo musicInfo);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancle:
                edit.setText("");
                editTag = true;
                historyList.setVisibility(View.VISIBLE);
                resultList.setVisibility(View.GONE);
                historyEntities = (ArrayList<HistoryEntity>) historyEntityDao.queryBuilder().orderDesc(HistoryEntityDao.Properties.Time).build().list();
                historyAdapter = new HistoryAdapter(mContex, historyEntities);
                historyList.setAdapter(historyAdapter);
                edit.clearFocus();
                break;
        }
    }
}
