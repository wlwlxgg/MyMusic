package com.example.wlwlxgg.mymusic.http;

import com.example.wlwlxgg.mymusic.http.result.MusicInfo;
import com.example.wlwlxgg.mymusic.http.result.SearchResult;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wlwlxgg on 2017/4/16.
 */

public class HttpManager {

    /**
     * 普通请求
     * @param baseUrl
     * @return
     */
    public static HttpService getService(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(HttpService.class);
    }

    /**
     * 文件下载
     */
    public static HttpService getService() {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL.LYRIC_BASE_URL)
                .build().create(HttpService.class);
    }

    /**
     * 获取搜索结果的方法
     */
    public static Call<SearchResult> getSearchResult(String userAgent, HashMap<String, String> queryMap){
        Call<SearchResult> call = getService(URL.BASE_URL).getSearchResult(userAgent, queryMap);
        return call;
    }

    /**
     * 获取歌曲信息
     */
    public static Call<MusicInfo> getMusic(String userAgent, String songId) {
        Call<MusicInfo> call = getService(URL.BASE_URL).getMusic(userAgent, songId);
        return call;
    }

    /**
     * 歌词下载
     */
    public static Call<ResponseBody> getLyric(String url) {
        Call<ResponseBody> call = getService().getLyric(URL.USER_AGENT, url);
        return call;
    }
}
