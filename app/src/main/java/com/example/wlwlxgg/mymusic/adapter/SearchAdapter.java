package com.example.wlwlxgg.mymusic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.http.result.SearchResult;

import java.util.ArrayList;

/**
 * Created by wlwlxgg on 2017/4/16.
 */

public class SearchAdapter extends BaseAdapter {
    Context context;
    ArrayList<SearchResult.ResultBean.SongInfoBean.SongListBean> searchResults;

    public SearchAdapter(Context context, ArrayList<SearchResult.ResultBean.SongInfoBean.SongListBean> mList) {
        this.context = context;
        this.searchResults = mList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.listview_search, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.author = (TextView) convertView.findViewById(R.id.author);
            viewHolder.album = (TextView) convertView.findViewById(R.id.album);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.name.setText(searchResults.get(position).getTitle());
        viewHolder.author.setText(searchResults.get(position).getAuthor());
        viewHolder.album.setText(searchResults.get(position).getAlbum_title());
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return searchResults.size();
    }

    @Override
    public Object getItem(int position) {
        return searchResults.get(position);
    }
    private class ViewHolder {
        TextView name, author, album;
    }
}
