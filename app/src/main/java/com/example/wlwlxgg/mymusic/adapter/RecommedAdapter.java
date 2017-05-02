package com.example.wlwlxgg.mymusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wlwlxgg.mymusic.R;

import java.util.ArrayList;

/**
 * Created by wlwlxgg on 2017/4/15.
 */

public class RecommedAdapter extends BaseAdapter {
    private Context mContex;
    private ArrayList<Integer> images;

    public RecommedAdapter(Context mContex, ArrayList<Integer> images) {
        this.mContex = mContex;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i) {
        return images.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(mContex, R.layout.gridview_recommend, null);
            holder.image = (ImageView) view.findViewById(R.id.image);
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.writer = (TextView) view.findViewById(R.id.writer);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.image.setBackgroundResource(images.get(i));
        holder.title.setText("经典金曲100首");
        holder.writer.setText("王磊");
        return view;
    }

    private class ViewHolder {
        private ImageView image;
        private TextView title, writer;
    }
}
