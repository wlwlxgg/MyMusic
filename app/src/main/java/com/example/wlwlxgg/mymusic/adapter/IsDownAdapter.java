package com.example.wlwlxgg.mymusic.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.wlwlxgg.mymusic.R;
import com.example.wlwlxgg.mymusic.entity.MusicDownloadEntity;
import com.example.wlwlxgg.mymusic.http.download.DownloadManager;
import com.example.wlwlxgg.mymusic.http.download.DownloadProgressOnNextListener;

import java.util.ArrayList;

/**
 * Created by wlwlxgg on 2017/5/4.
 */

public class IsDownAdapter extends BaseAdapter{
    private ArrayList<MusicDownloadEntity> mList;
    private Context mContext;
    private DownloadManager manager;

    public IsDownAdapter(ArrayList<MusicDownloadEntity> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
        manager = DownloadManager.getInstance();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext,R.layout.listview_isdown_item, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.album = (TextView) convertView.findViewById(R.id.album);
            holder.author = (TextView) convertView.findViewById(R.id.author);
            holder.down = (Button) convertView.findViewById(R.id.down);
            holder.pause = (Button) convertView.findViewById(R.id.pause);
            holder.progressBar = (NumberProgressBar) convertView.findViewById(R.id.number_progress_bar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(mList.get(position).getTitle());
        holder.author.setText(mList.get(position).getAuthor());
        holder.album.setText(mList.get(position).getAlbum());
        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.name.setVisibility(View.GONE);
                holder.pause.setVisibility(View.VISIBLE);
                manager.startDown(mList.get(position), holder.listener);
            }
        });
        holder.pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.name.setVisibility(View.VISIBLE);
                holder.pause.setVisibility(View.GONE);
                manager.pause(mList.get(position), holder.listener);
            }
        });
        return convertView;
    }

    public class ViewHolder{
        TextView name, author, album;
        Button down, pause;
        NumberProgressBar progressBar;
        DownloadProgressOnNextListener<MusicDownloadEntity> listener = new DownloadProgressOnNextListener<MusicDownloadEntity>() {
            @Override
            public void onNext(MusicDownloadEntity entity) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void updateProgress(long readLength, long countLength) {
                progressBar.setMax((int) countLength);
                progressBar.setProgress((int) readLength);
            }
        };
    }
}
