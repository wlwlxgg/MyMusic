package com.example.wlwlxgg.mymusic.http;

/**
 * Created by wlwlxgg on 2017/4/16.
 */

public class URL {

    public static final String BASE_URL = "http://tingapi.ting.baidu.com/v1/";
    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
    public static final String LYRIC_BASE_URL = "http://musicdata.baidu.com/";

    /**
     * 搜索歌曲
     */
    public static final String SEARCH_URL = "restserver/ting?format=json&calback=&from=android&version=5.6.5.0&method=baidu.ting.search.merge&type=-1&data_source=0&use_cluster=1";

    /**
     * 音乐播放
     */
    public static final String PLAY_URL = "restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.song.play";
}
