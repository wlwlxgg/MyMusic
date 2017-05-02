package com.example.wlwlxgg.mymusic.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by wlwlxgg on 2017/4/25.
 * 历史记录表实体类
 */
@Entity
public class HistoryEntity{
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
    @Generated(hash = 1830544899)
    public HistoryEntity(String songId, String time, String title, String author,
            String album, String lrcLink, String fileLink, String imageSmall,
            String imageMid, String imageBig) {
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
    }
    @Generated(hash = 1235354573)
    public HistoryEntity() {
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
}
