package com.example.wlwlxgg.mymusic.adapter;

import android.content.Context;
import android.os.Bundle;
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
    private Handler mHandler;

    public IsDownAdapter(Context mContext, ArrayList<MusicDownloadEntity> mList, Handler mHandler) {
        this.mList = mList;
        this.mContext = mContext;
        this.mHandler = mHandler;
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
        holder.entity = mList.get(position);
        holder.name.setText(mList.get(position).getTitle());
        holder.author.setText(mList.get(position).getAuthor());
        holder.album.setText(mList.get(position).getAlbum());
        holder.progressBar.setMax((int)mList.get(position).getContentLength());
        holder.progressBar.setProgress((int)mList.get(position).getReadLength());
        holder.setOnclick();

        return convertView;
    }

    public class ViewHolder implements View.OnClickListener{
        TextView name, author, album;
        Button down, pause;
        NumberProgressBar progressBar;
        MusicDownloadEntity entity;

        public void setOnclick() {
            down.setOnClickListener(this);
            pause.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.down:
                    down.setVisibility(View.GONE);
                    pause.setVisibility(View.VISIBLE);
                    manager.startDown(entity,listener);
                    break;
                case R.id.pause:
                    down.setVisibility(View.VISIBLE);
                    pause.setVisibility(View.GONE);
                    manager.pause(entity);
                    break;
            }
        }
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
                if (readLength == countLength) {
                    Message msg = Message.obtain();
                    msg.what = 1;
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("object", entity);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                }
            }
        };
    }
}
