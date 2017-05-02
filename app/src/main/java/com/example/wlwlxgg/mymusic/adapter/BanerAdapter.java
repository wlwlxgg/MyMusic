package com.example.wlwlxgg.mymusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.wlwlxgg.mymusic.R;

import java.util.ArrayList;

/**
 * Created by wlwlxgg on 2017/4/15.
 */

public class BanerAdapter extends BaseAdapter {
    private Context mContex;
    private ArrayList<Integer> images;

    public BanerAdapter(Context mContex, ArrayList<Integer> images) {
        this.mContex = mContex;
        this.images = images;
    }

    private ImageView imageView;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(mContex,R.layout.banner_image, null);
        imageView = (ImageView) view.findViewById(R.id.image);
        imageView.setBackgroundResource(images.get(i % images.size()));
        return view;
    }

    @Override
    public int getCount() {
        if (images.size() == 1) {
            return 1;
        } else {
            return Integer.MAX_VALUE; // 返回很大的值使得getView中的position不断增大来实现循环
        }
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
