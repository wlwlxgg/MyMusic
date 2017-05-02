package com.example.wlwlxgg.mymusic.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;

/**
 * Created by wlwlxgg on 2017/4/17.
 */

public class PrefsUtil {
    private static PrefsUtil prefsUtil;
    public Context context;
    private String prefName = null;
    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;

    public synchronized static PrefsUtil getInstance() {
        return prefsUtil;
    }

    public static void init(Context context, String prefsname, int mode) {
        prefsUtil = new PrefsUtil();
        prefsUtil.context = context;
        prefsUtil.prefName = prefsname;
        prefsUtil.prefs = prefsUtil.context.getSharedPreferences(prefsname,
                mode);
        prefsUtil.editor = prefsUtil.prefs.edit();
    }

    public PrefsUtil putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
        return this;
    }

    public int getInt(String key) {
        return this.prefs.getInt(key, 0);
    }

    public PrefsUtil putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
        return this;
    }

    public String getString(String key) {
        return this.prefs.getString(key, null);
    }

    /**
     * 放入复杂对象
     * @param key
     * @param object
     */
    public void putObject(String key, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }

        if (key.equals("") || key == null) {
            throw new IllegalArgumentException("key is empty or null");
        }
        editor.putString(key, JSON.toJSONString(object));
        editor.commit();
    }

    /**
     * 取出复杂对象
     * @param key
     * @param a
     * @return
     */
    public <T> T getObject(String key, Class<T> a) {

        String str = prefs.getString(key,null);
        if (str == null) {
            return null;
        } else {
            try {

                return JSON.parseObject(str, a);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object storaged with key "
                        + key + " is instanceof other class");
            }
        }
    }
    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }
}
