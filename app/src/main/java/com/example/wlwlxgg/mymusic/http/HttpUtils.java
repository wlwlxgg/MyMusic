package com.example.wlwlxgg.mymusic.http;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.wlwlxgg.mymusic.application.MyApplication;
import com.example.wlwlxgg.mymusic.constant.CodeMessage;
import com.example.wlwlxgg.mymusic.constant.PrefsKey;
import com.example.wlwlxgg.mymusic.entity.HistoryEntity;
import com.example.wlwlxgg.mymusic.greendao.DaoSession;
import com.example.wlwlxgg.mymusic.greendao.HistoryEntityDao;
import com.example.wlwlxgg.mymusic.http.result.MusicInfo;
import com.example.wlwlxgg.mymusic.http.result.SearchResult;
import com.example.wlwlxgg.mymusic.utils.CommonUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wlwlxgg on 2017/4/16.
 * 接口实现，返回给view需要的数据
 */

public class HttpUtils {
    //数据库会话层。操作具体的DAO对象（注意：是对象），比如各种getter方法。
    public static DaoSession daoSession = MyApplication.getInstances().getDaoSession();

    /**
     * 搜索，返回结果
     */
    public static void getSearchResult(final ArrayList<SearchResult.ResultBean.SongInfoBean.SongListBean> results, String query, final int page_num, final Handler mHandler) {
        String Page_num = Integer.toString(page_num);
        HashMap<String, String> map = new HashMap<>();
        map.put("query", query);
        map.put("page_no", Page_num);
        map.put("page_size", "10");
        HttpManager.getSearchResult(URL.USER_AGENT, map).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (page_num == 1)
                    results.clear();
                if (response.body().getResult().getSong_info() != null && response.body().getResult().getSong_info().getSong_list() != null) {
                    for (int i = 0; i < response.body().getResult().getSong_info().getSong_list().size(); i++) {
                        results.add(response.body().getResult().getSong_info().getSong_list().get(i));
                    }
                    mHandler.sendEmptyMessage(CodeMessage.NET_SUCCESSFUL);
                } else mHandler.sendEmptyMessage(CodeMessage.NET_FAILURE);
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                mHandler.sendEmptyMessage(CodeMessage.NET_FAILURE);
                t.toString();
            }
        });
    }


    /**
     * 通过songId获取歌曲信息并存入数据库
     */
    public static void getMusic(String songId, final Handler mHandler) {
        HttpManager.getMusic(URL.USER_AGENT, songId).enqueue(new Callback<MusicInfo>() {
            @Override
            public void onResponse(Call<MusicInfo> call, Response<MusicInfo> response) {
                if (response.body() != null) {
                    MusicInfo musicInfo = response.body();
                    /*存入历史记录表*/
                    HistoryEntity historyEntity = CommonUtils.MusicInfo2HistoryEntity(musicInfo);
                    //设置时间戳，以存入的时间为key排序
                    historyEntity.setTime(String.valueOf(System.currentTimeMillis()));
                    HistoryEntityDao historyEntityDao = daoSession.getHistoryEntityDao();
                    historyEntityDao.insertOrReplaceInTx(historyEntity);
                    /*返回数据到MainActivity*/
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(PrefsKey.MUSIC_INFO, musicInfo);
                    Message message = new Message();
                    message.setData(bundle);
                    message.what = CodeMessage.GET_MUSIC;
                    mHandler.sendMessage(message);
                }
            }

            @Override
            public void onFailure(Call<MusicInfo> call, Throwable t) {

            }
        });
    }

    /**
     * 通过songId获取歌曲信息--不存入数据库
     */
    public static void getMusicWithoutSaveData(String songId, final Handler mHandler) {
        HttpManager.getMusic(URL.USER_AGENT, songId).enqueue(new Callback<MusicInfo>() {
            @Override
            public void onResponse(Call<MusicInfo> call, Response<MusicInfo> response) {
                if (response.body() != null) {
                    MusicInfo musicInfo = response.body();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(PrefsKey.MUSIC_INFO, musicInfo);
                    Message message = new Message();
                    message.setData(bundle);
                    message.what = CodeMessage.GET_MUSIC;
                    mHandler.sendMessage(message);
                }
            }

            @Override
            public void onFailure(Call<MusicInfo> call, Throwable t) {

            }
        });
    }

    /**
     * 获得歌词
     */
    public static void getLyric(final MusicInfo musicInfo, final Handler mHandler) {
        String url = musicInfo.getSonginfo().getLrclink();
        String uurl = "";
        for (int i = 0; i < url.length(); i++) {
            if (i > 26) uurl += url.charAt(i);
        }
        HttpManager.getLyric(uurl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    boolean writtenToDisk = writeResponseBodyToDisk(response.body(), musicInfo);
                    mHandler.sendEmptyMessage(CodeMessage.NET_SUCCESSFUL);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    /**
     * 写入歌词
     *
     * @param body
     * @param musicInfo
     * @return
     */

    private static boolean writeResponseBodyToDisk(ResponseBody body, MusicInfo musicInfo) {
        // todo change the file location/name according to your needs
        File file1 = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "MyMusic/" + "Lrc/");//仅创建路径的File对象
        if (!file1.exists()) {
            file1.mkdir();//如果路径不存在就先创建路径
        }
        File futureStudioIconFile = new File(file1, musicInfo.getSonginfo().getSong_id() + ".lrc");
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            byte[] fileReader = new byte[4096];
            long fileSize = body.contentLength();
            long fileSizeDownloaded = 0;
            inputStream = body.byteStream();
            outputStream = new FileOutputStream(futureStudioIconFile);
            while (true) {
                int read = inputStream.read(fileReader);
                if (read == -1) {
                    break;
                }
                outputStream.write(fileReader, 0, read);
                fileSizeDownloaded += read;
                Log.d("", "file download: " + fileSizeDownloaded + " of " + fileSize);
            }
            inputStream.close();
            outputStream.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
