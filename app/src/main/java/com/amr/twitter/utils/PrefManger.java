package com.amr.twitter.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Amr on 2/3/2018.
 */

public class PrefManger {

    private static PrefManger prefManger;

    private static final String IS_LOGIN = "IS_LOGIN";

    private SharedPreferences preferences;

    private PrefManger(Context context) {
        preferences = context.getSharedPreferences("twitter_api", Context.MODE_PRIVATE);
    }

    public static PrefManger getPrefManger(Context context) {
        if (prefManger == null)
            prefManger = new PrefManger(context);
        return prefManger;
    }

    public void setLoginStatus(boolean loggedIn) {
        preferences.edit().putBoolean(IS_LOGIN, loggedIn).apply();
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(IS_LOGIN, false);
    }
}
