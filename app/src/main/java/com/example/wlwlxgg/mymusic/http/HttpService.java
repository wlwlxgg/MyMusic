package com.example.wlwlxgg.mymusic.http;

import com.example.wlwlxgg.mymusic.http.result.MusicInfo;
import com.example.wlwlxgg.mymusic.http.result.SearchResult;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by wlwlxgg on 2017/4/16.
 */

public interface HttpService {

    String USER_AGENT = "User-Agent";
    String RANGE = "Range";

    /**
     * 搜索方法
     */
    @GET(URL.SEARCH_URL)
    Call<SearchResult> getSearchResult(@Header(USER_AGENT) String userAgent, @QueryMap HashMap<String, String> queryMap);

    /**
     * 获取音乐信息
     */
    @GET(URL.PLAY_URL)
    Call<MusicInfo> getMusic(@Header(USER_AGENT) String userAgent, @Query("songid") String songId);

    /**
     * 下载歌词
     */
    @GET
    Call<ResponseBody> getLyric(@Header(USER_AGENT) String userAgent, @Url String url);

    /**
     * 歌曲下载
     */
    @Streaming
    @GET
    @Headers(URL.USER_AGENT)
    Call<ResponseBody> downMusic(@Header(RANGE) String range, @Url String url);
}
