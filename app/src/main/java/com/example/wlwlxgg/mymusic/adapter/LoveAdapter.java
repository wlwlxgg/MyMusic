package com.example.wlwlxgg.mymusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.entity.MusicLoveEntity;

import java.util.ArrayList;

/**
 * Created by wlwlxgg on 2017/4/26.
 */

public class LoveAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<MusicLoveEntity> mList;

    public LoveAdapter(Context mContext, ArrayList<MusicLoveEntity> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.listview_love, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.author = (TextView) convertView.findViewById(R.id.author);
            viewHolder.album = (TextView) convertView.findViewById(R.id.album);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(mList.get(position).getTitle());
        viewHolder.author.setText(mList.get(position).getAuthor());
        viewHolder.album.setText(mList.get(position).getAlbum());
        return convertView;
    }
    private class ViewHolder {
        TextView name, author, album;
    }
}
