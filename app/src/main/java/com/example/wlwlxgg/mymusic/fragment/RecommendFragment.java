package com.example.wlwlxgg.mymusic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.adapter.BanerAdapter;
import com.example.wlwlxgg.mymusic.adapter.RecommedAdapter;
import com.example.wlwlxgg.mymusic.view.CircleFlowIndicator;
import com.example.wlwlxgg.mymusic.view.MyGridView;
import com.example.wlwlxgg.mymusic.view.MyViewFlow;

import java.util.ArrayList;

/**
 * Created by wlwlxgg on 2017/4/15.
 */

public class RecommendFragment extends BaseFragment {

    private MyViewFlow viewFlow;
    private CircleFlowIndicator indicator;
    private Button more;
    private MyGridView gridView;
    private Context mContext;
    private ArrayList<Integer> images, _images;
    private ScrollView scrollView;

    @Override
    public View initView(LayoutInflater inflater) {
        View mView = inflater.inflate(R.layout.fragment_recommend, null);
        viewFlow = (MyViewFlow) mView.findViewById(R.id.viewFlow);
        more = (Button) mView.findViewById(R.id.more);
        gridView = (MyGridView) mView.findViewById(R.id.gridView);
        indicator = (CircleFlowIndicator) mView.findViewById(R.id.viewFlowIndic);
        scrollView = (ScrollView) mView.findViewById(R.id.scrollView);
        return mView;
    }

    @Override
    public void initData(View view, Bundle savedInstanceState) {
        mContext = getActivity();
        images = new ArrayList<>();
        _images = new ArrayList<>();
        makeData();
        viewFlow.setFlowIndicator(indicator);
        viewFlow.setTimeSpan(5000);
        viewFlow.startAutoFlowTimer();
        viewFlow.setmSideBuffer(images.size());
        viewFlow.setAdapter(new BanerAdapter(mContext, images));
        gridView.setColumnWidth(3);
        gridView.setAdapter(new RecommedAdapter(mContext, _images));
        scrollView.post(new Runnable() {
            public void run() {
                scrollView.scrollTo(0, 0);
            }
        });
    }

    private void makeData() {
        images.add(R.mipmap.banner_1);
        images.add(R.mipmap.banner_2);
        images.add(R.mipmap.banner_3);
        images.add(R.mipmap.banner_4);
        for (int i = 0; i < 6; i++) {
            _images.add(R.mipmap.banner_4);
        }
    }

}
