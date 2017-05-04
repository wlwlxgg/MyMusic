package com.example.wlwlxgg.mymusic.utils;

import com.example.wlwlxgg.mymusic.entity.HistoryEntity;
import com.example.wlwlxgg.mymusic.entity.MusicDownloadEntity;
import com.example.wlwlxgg.mymusic.entity.MusicLoveEntity;
import com.example.wlwlxgg.mymusic.http.result.MusicInfo;

/**
 * Created by wlwlxgg on 2017/4/26.
 * 常用工具类
 */

public class CommonUtils {

    /** MusciInfo和HistoryEntity转换*/
    //M2H
    public static HistoryEntity MusicInfo2HistoryEntity(MusicInfo musicInfo) {
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setSongId(musicInfo.getSonginfo().getSong_id());
        historyEntity.setAlbum(musicInfo.getSonginfo().getAlbum_title());
        historyEntity.setAuthor(musicInfo.getSonginfo().getAuthor());
        historyEntity.setFileLink(musicInfo.getBitrate().getFile_link());
        historyEntity.setTitle(musicInfo.getSonginfo().getTitle());
        historyEntity.setImageSmall(musicInfo.getSonginfo().getPic_small());
        historyEntity.setImageMid(musicInfo.getSonginfo().getPic_big());
        historyEntity.setImageBig(musicInfo.getSonginfo().getPic_huge());
        return historyEntity;
    }
    //H2M
    public static MusicInfo HistoryEntity2MusicInfo(HistoryEntity historyEntity) {
        MusicInfo musicInfo = new MusicInfo();
        musicInfo.getSonginfo().setTitle(historyEntity.getTitle());
        musicInfo.getSonginfo().setAuthor(historyEntity.getAuthor());
        musicInfo.getSonginfo().setAlbum_title(historyEntity.getAlbum());
        musicInfo.getSonginfo().setPic_small(historyEntity.getImageSmall());
        musicInfo.getSonginfo().setPic_big(historyEntity.getImageMid());
        musicInfo.getSonginfo().setPic_huge(historyEntity.getImageBig());
        musicInfo.getSonginfo().setLrclink(historyEntity.getLrcLink());
        musicInfo.getBitrate().setFile_link(historyEntity.getFileLink());
        return musicInfo;
    }

    /** MusciInfo和MusicLoveEntity转换*/
    public static MusicLoveEntity MusicInfo2MusicLoveEntity(MusicInfo musicInfo) {
        MusicLoveEntity musicLoveEntity = new MusicLoveEntity();
        musicLoveEntity.setSongId(musicInfo.getSonginfo().getSong_id());
        musicLoveEntity.setAlbum(musicInfo.getSonginfo().getAlbum_title());
        musicLoveEntity.setAuthor(musicInfo.getSonginfo().getAuthor());
        musicLoveEntity.setFileLink(musicInfo.getBitrate().getFile_link());
        musicLoveEntity.setTitle(musicInfo.getSonginfo().getTitle());
        musicLoveEntity.setImageSmall(musicInfo.getSonginfo().getPic_small());
        musicLoveEntity.setImageMid(musicInfo.getSonginfo().getPic_big());
        musicLoveEntity.setImageBig(musicInfo.getSonginfo().getPic_huge());
        return musicLoveEntity;
    }
    public static MusicInfo MusicLoveEntity2MusicInfo(MusicLoveEntity musicLoveEntity) {
        MusicInfo musicInfo = new MusicInfo();
        musicInfo.getSonginfo().setTitle(musicLoveEntity.getTitle());
        musicInfo.getSonginfo().setAuthor(musicLoveEntity.getAuthor());
        musicInfo.getSonginfo().setAlbum_title(musicLoveEntity.getAlbum());
        musicInfo.getSonginfo().setPic_small(musicLoveEntity.getImageSmall());
        musicInfo.getSonginfo().setPic_big(musicLoveEntity.getImageMid());
        musicInfo.getSonginfo().setPic_huge(musicLoveEntity.getImageBig());
        musicInfo.getSonginfo().setLrclink(musicLoveEntity.getLrcLink());
        musicInfo.getBitrate().setFile_link(musicLoveEntity.getFileLink());
        return musicInfo;
    }

    /** MusicInfo和MusicDownloadEntity转换*/

    public static MusicDownloadEntity MusicInfo2MusicDownloadEntity(MusicInfo musicInfo) {
        MusicDownloadEntity entity = new MusicDownloadEntity();
        entity.setSongId(musicInfo.getSonginfo().getSong_id());
        entity.setAlbum(musicInfo.getSonginfo().getAlbum_title());
        entity.setAuthor(musicInfo.getSonginfo().getAuthor());
        entity.setFileLink(musicInfo.getBitrate().getFile_link());
        entity.setTitle(musicInfo.getSonginfo().getTitle());
        entity.setImageSmall(musicInfo.getSonginfo().getPic_small());
        entity.setImageMid(musicInfo.getSonginfo().getPic_big());
        entity.setImageBig(musicInfo.getSonginfo().getPic_huge());
        return entity;
    }
}
