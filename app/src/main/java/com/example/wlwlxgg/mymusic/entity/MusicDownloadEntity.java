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


    public MusicDownloadEntity() {
        setBaseUrl(getBasUrl(getFileLink()));
    }

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

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getLrcLink() {
        return lrcLink;
    }

    public void setLrcLink(String lrcLink) {
        this.lrcLink = lrcLink;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public String getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }

    public String getImageMid() {
        return imageMid;
    }

    public void setImageMid(String imageMid) {
        this.imageMid = imageMid;
    }

    public String getImageBig() {
        return imageBig;
    }

    public void setImageBig(String imageBig) {
        this.imageBig = imageBig;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public long getReadLength() {
        return readLength;
    }

    public void setReadLength(long readLength) {
        this.readLength = readLength;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    protected String getBasUrl(String url) {
        String head = "";
        int index = url.indexOf("://");
        if (index != -1) {
            head = url.substring(0, index + 3);
            url = url.substring(index + 3);
        }
        index = url.indexOf("/");
        if (index != -1) {
            url = url.substring(0, index + 1);
        }
        return head + url;
    }
}
