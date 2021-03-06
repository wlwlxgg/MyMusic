package com.example.wlwlxgg.mymusic.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.wlwlxgg.mymusic.entity.HistoryEntity;
import com.example.wlwlxgg.mymusic.entity.MusicDownloadEntity;
import com.example.wlwlxgg.mymusic.entity.MusicLoveEntity;

import com.example.wlwlxgg.mymusic.greendao.HistoryEntityDao;
import com.example.wlwlxgg.mymusic.greendao.MusicDownloadEntityDao;
import com.example.wlwlxgg.mymusic.greendao.MusicLoveEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig historyEntityDaoConfig;
    private final DaoConfig musicDownloadEntityDaoConfig;
    private final DaoConfig musicLoveEntityDaoConfig;

    private final HistoryEntityDao historyEntityDao;
    private final MusicDownloadEntityDao musicDownloadEntityDao;
    private final MusicLoveEntityDao musicLoveEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        historyEntityDaoConfig = daoConfigMap.get(HistoryEntityDao.class).clone();
        historyEntityDaoConfig.initIdentityScope(type);

        musicDownloadEntityDaoConfig = daoConfigMap.get(MusicDownloadEntityDao.class).clone();
        musicDownloadEntityDaoConfig.initIdentityScope(type);

        musicLoveEntityDaoConfig = daoConfigMap.get(MusicLoveEntityDao.class).clone();
        musicLoveEntityDaoConfig.initIdentityScope(type);

        historyEntityDao = new HistoryEntityDao(historyEntityDaoConfig, this);
        musicDownloadEntityDao = new MusicDownloadEntityDao(musicDownloadEntityDaoConfig, this);
        musicLoveEntityDao = new MusicLoveEntityDao(musicLoveEntityDaoConfig, this);

        registerDao(HistoryEntity.class, historyEntityDao);
        registerDao(MusicDownloadEntity.class, musicDownloadEntityDao);
        registerDao(MusicLoveEntity.class, musicLoveEntityDao);
    }
    
    public void clear() {
        historyEntityDaoConfig.clearIdentityScope();
        musicDownloadEntityDaoConfig.clearIdentityScope();
        musicLoveEntityDaoConfig.clearIdentityScope();
    }

    public HistoryEntityDao getHistoryEntityDao() {
        return historyEntityDao;
    }

    public MusicDownloadEntityDao getMusicDownloadEntityDao() {
        return musicDownloadEntityDao;
    }

    public MusicLoveEntityDao getMusicLoveEntityDao() {
        return musicLoveEntityDao;
    }

}
