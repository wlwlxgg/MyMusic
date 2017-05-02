package com.example.wlwlxgg.mymusic.application;

import android.app.Application;
import android.content.Context;

import com.example.wlwlxgg.mymusic.greendao.DaoMaster;
import com.example.wlwlxgg.mymusic.greendao.DaoSession;
import com.example.wlwlxgg.mymusic.utils.PrefsUtil;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import org.greenrobot.greendao.database.Database;

import java.io.File;

/**
 * Created by wlwlxgg on 2017/4/16.
 */

public class MyApplication extends Application{
    private Context mContext;
    private PrefsUtil prefsUtil;
    private DaoSession daoSession;
    //应用实例对象
    private static MyApplication instances;

    @Override
    public void onCreate() {
        this.mContext = getApplicationContext();
        instances = this;
        PrefsUtil.init(getApplicationContext(), "MyMusic", Context.MODE_PRIVATE);
        initData();
        super.onCreate();
    }

    /**
     * 获取应用的实例对象
     *
     * @return 实例对象
     */
    public static MyApplication getInstances() {
        return instances;
    }

    private void initData() {
        initImageLoader();
        //判断应用是否重启。
        prefsUtil = PrefsUtil.getInstance();
        prefsUtil.putInt("isRestart", 1);
        //初始化数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "music-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }
    /**
     * ImageLoader初始化
     */
    public void initImageLoader() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(mContext, "MyMusic/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                .threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCache(new WeakMemoryCache())
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .build();
        ImageLoader.getInstance().init(config);

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
