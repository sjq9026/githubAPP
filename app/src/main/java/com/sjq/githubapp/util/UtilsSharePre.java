package com.sjq.githubapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 偏好设置文件工具类,key要在这里设置
 * 使用{@link #getInstance()} 调用
 */
public class UtilsSharePre {

    private final String FILE_NAME = "Github";//

    public static final String USER_NAME = "User_Name";//
    public static final String TOKEN = "Token";//




    private static UtilsSharePre instance = null;

    /**
     * UtilsSharePre提供公共静态方法调用
     *
     * @return UtilsSharePre的实例
     */
    public static UtilsSharePre getInstance() {

        if (instance == null) {
            instance = new UtilsSharePre();
        }
        return instance;

    }

    /**
     * 私有的构造方法
     */
    private UtilsSharePre() {
    }



    /**
     * 偏好存储,存储字符串
     *
     * @param context
     * @param key
     * @param value
     */
    public void setPreferenceString(Context context, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        pref.edit().putString(key, value).apply();
    }

    public String getPreferenceString(Context context, String key,
                                      String defaultvvalue) {
        SharedPreferences pref = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return pref.getString(key, defaultvvalue);
    }

    /**
     * 偏好存储,存储boolean
     *
     * @param context
     * @param key
     * @param defaultvalue
     */
    public void setPreferenceBoolean(Context context, String key, boolean defaultvalue) {
        SharedPreferences pref = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        pref.edit().putBoolean(key, defaultvalue).apply();
    }

    public boolean getPreferenceBoolean(Context context, String key,
                                        boolean defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return pref.getBoolean(key, defaultValue);
    }

    /**
     * 偏好存储,存储long
     *
     * @param context
     * @param key
     * @param value
     */
    public void setPreferenceLong(Context context, String key, long value) {
        SharedPreferences pref = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        pref.edit().putLong(key, value).apply();
    }

    public long getPreferenceLong(Context context, String key,
                                  long defaultvvalue) {
        SharedPreferences pref = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return pref.getLong(key, 0);
    }

    /**
     * 偏好存储,存储int
     *
     * @param context
     * @param key
     * @param value
     */
    public void setPreferenceInt(Context context, String key, int value) {
        SharedPreferences pref = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        pref.edit().putInt(key, value).apply();
    }

    /**
     * 偏好存储,存储int
     *
     * @param context
     * @param key
     * @param defaultvalue
     * @return
     */
    public int getPreferenceInt(Context context, String key, int defaultvalue) {
        SharedPreferences pref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        int value = pref.getInt(key, defaultvalue);
        return value;
    }

    /**
     * 偏好存储,存储float
     *
     * @param context
     * @param key
     * @param value
     */
    public void setPreferenceFloat(Context context, String key, float value) {
        SharedPreferences pref = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        pref.edit().putFloat(key, value).apply();
    }

    /**
     * 偏好存储,存储float
     *
     * @param context
     * @param key
     * @param defaultvalue
     * @return
     */
    public float getPreferenceFloat(Context context, String key, float defaultvalue) {
        SharedPreferences pref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        float value = pref.getFloat(key, defaultvalue);
        return value;
    }
}
