package com.democome.flipper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;

public class SharePreferenceUtils {

    private static final String FILE_NAME = "flipper_shared_preference";
    private static SharePreferenceUtils instance;
    private SharedPreferences sharedPreferences;

    public static SharePreferenceUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (SharePreferenceUtils.class) {
                if (instance == null) {
                    instance = new SharePreferenceUtils(context);
                }
            }
        }
        return instance;
    }

    private SharePreferenceUtils(Context context) {
        this.sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public void set(String key, String value) {
        Editor editor = this.sharedPreferences.edit();
        editor.putString(key, value);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public String get(String key, String defaultValue) {
        return this.sharedPreferences.getString(key, defaultValue);
    }

}
