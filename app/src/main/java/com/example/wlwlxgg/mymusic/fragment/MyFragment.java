package com.example.wlwlxgg.mymusic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.activity.DownloadActivity;
import com.example.wlwlxgg.mymusic.activity.MainActivity;
import com.example.wlwlxgg.mymusic.constant.CodeMessage;

/**
 * Created by wlwlxgg on 2017/4/15.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener{
    private TextView download;
    private TextView history;
    private TextView love;
    @Override
    public View initView(LayoutInflater inflater) {
        View mView = inflater.inflate(R.layout.fragment_my, null);
        return mView;
    }

    @Override
    public void initData(View view, Bundle savedInstanceState) {
        download = (TextView)view.findViewById(R.id.download);
        history = (TextView) view.findViewById(R.id.history);
        love = (TextView) view.findViewById(R.id.love);
        download.setOnClickListener(this);
        history.setOnClickListener(this);
        love.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MainActivity activity = (MainActivity) getActivity();
        switch (v.getId()) {
            case R.id.download:
                startActivity(new Intent(getActivity(), DownloadActivity.class));
                break;
            case R.id.history:
                activity.setIndex(CodeMessage.FRAGMENT_HISTORY);
                break;
            case R.id.love:
                activity.setIndex(CodeMessage.FRAGMENT_LOVE);
                break;
        }
    }

}
