package com.example.wlwlxgg.mymusic.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wlwlxgg on 2017/5/4.
 */

@Entity
public class MusicDownloadEntity {
    @Id
    private String songId;
    private String time;
    private String title;
    private String author;
    private String album;
    private String lrcLink;
    private String fileLink;
    private String imageSmall;
    private String imageMid;
    private String imageBig;
    private String savePath;
    private String baseUrl;
    private long contentLength;
    private long readLength;
    private int status;
    @Generated(hash = 546461851)
    public MusicDownloadEntity(String songId, String time, String title,
            String author, String album, String lrcLink, String fileLink,
            String imageSmall, String imageMid, String imageBig, String savePath,
            String baseUrl, long contentLength, long readLength, int status) {
        this.songId = songId;
        this.time = time;
        this.title = title;
        this.author = author;
        this.album = album;
        this.lrcLink = lrcLink;
        this.fileLink = fileLink;
        this.imageSmall = imageSmall;
        this.imageMid = imageMid;
        this.imageBig = imageBig;
        this.savePath = savePath;
        this.baseUrl = baseUrl;
        this.contentLength = contentLength;
        this.readLength = readLength;
        this.status = status;
    }
    @Generated(hash = 1310016221)
    public MusicDownloadEntity() {
    }
    public String getSongId() {
        return this.songId;
    }
    public void setSongId(String songId) {
        this.songId = songId;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAlbum() {
        return this.album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public String getLrcLink() {
        return this.lrcLink;
    }
    public void setLrcLink(String lrcLink) {
        this.lrcLink = lrcLink;
    }
    public String getFileLink() {
        return this.fileLink;
    }
    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }
    public String getImageSmall() {
        return this.imageSmall;
    }
    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }
    public String getImageMid() {
        return this.imageMid;
    }
    public void setImageMid(String imageMid) {
        this.imageMid = imageMid;
    }
    public String getImageBig() {
        return this.imageBig;
    }
    public void setImageBig(String imageBig) {
        this.imageBig = imageBig;
    }
    public String getSavePath() {
        return this.savePath;
    }
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
    public String getBaseUrl() {
        return this.baseUrl;
    }
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    public long getContentLength() {
        return this.contentLength;
    }
    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }
    public long getReadLength() {
        return this.readLength;
    }
    public void setReadLength(long readLength) {
        this.readLength = readLength;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    

}
