package com.quikr.pluto.Backend;

/**
 * Created by kanishth on 14/9/15.
 */
import android.content.Context;
import android.content.SharedPreferences;

public class ReturningClass {

    final static String MyPREFERENCES = "Myprefs";
    static final String defaultToken = "";
    public static SharedPreferences getPrefs(Context context)
    {
        return context.getSharedPreferences(MyPREFERENCES,
                context.MODE_PRIVATE);
    }
    public static String getToken(Context context) {
        return getPrefs(context).getString("token",defaultToken);
    }
    public static String getTokenID(Context context) {
        return getPrefs(context).getString("tokenID",defaultToken);
    }
    public static String getDate(Context context) {
        return getPrefs(context).getString("date",defaultToken);
    }
    public static void setToken(Context context, String value) {
        getPrefs(context).edit().putString("token", value).commit();
    }
    public static void setTokenID(Context context, String value) {
        getPrefs(context).edit().putString("tokenID", value).commit();
    }
    public static void setTokenDate(Context context, String value) {
        getPrefs(context).edit().putString("date", value).commit();
    }
}