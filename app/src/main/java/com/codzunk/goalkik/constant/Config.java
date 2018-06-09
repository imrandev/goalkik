package com.codzunk.goalkik.constant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class Config {
    public static final String DEFAULT_TEAM = "Russia";
    public static final String ROOT_NODE = "data";
    public static final String MY_PREF = "GOAL_PREF";
    public static final String MY_TEAM = "my-team";
    public static final String GROUP_DATA = "group-data";
    public static final String RANK = "Rank";
    public static final String PLAYED = "Played";
    public static final int TOTAL_TEAM = 32;

    public static WeakReference<Context> context;

    public static Config init() {
        return new Config();
    }

    public void showToast(String message){
        Toast.makeText(context.get(), "" + message, Toast.LENGTH_SHORT).show();
    }
}
