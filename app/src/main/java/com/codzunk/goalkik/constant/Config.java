package com.codzunk.goalkik.constant;

import android.content.Context;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class Config {
    public static final String DEFAULT_TEAM = "Russia";
    public static final String ROOT_NODE = "data";
    public static final String MY_PREF = "GOAL_PREF";
    public static final String MY_TEAM = "my-team";
    public static final String GROUP_DATA = "group-data";
    public static final String FIX_DATA = "fix-data";
    public static final String FIX_MODEL_DATA = "fix-model-data";
    public static final String SQUAD_DATA = "squad-data";
    public static final String STAND_DATA = "stand-data";
    public static final String RANK = "Rank";
    public static final String PLAYED = "Played";
    public static final int TOTAL_TEAM = 32;

    public static final String API_TOKEN = "af50ecedd7404682b31476c555b92ec9";

    // Team code
    public static final String CODE_RUS = "808";
    public static final String CODE_SAU = "801";
    public static final String CODE_EGY = "825";
    public static final String CODE_URU = "758";
    public static final String CODE_MOR = "815";
    public static final String CODE_IRA = "840";
    public static final String CODE_POR = "765";
    public static final String CODE_SPA = "760";
    public static final String CODE_FRA = "773";
    public static final String CODE_AUS = "779";
    public static final String CODE_PER = "832";
    public static final String CODE_DEN = "782";
    public static final String CODE_ARG = "762";
    public static final String CODE_ICE = "1066";
    public static final String CODE_CRO = "799";
    public static final String CODE_NIG = "776";
    public static final String CODE_COS = "793";
    public static final String CODE_SER = "780";
    public static final String CODE_BRA = "764";
    public static final String CODE_SWI = "788";
    public static final String CODE_GER = "759";
    public static final String CODE_MEX = "769";
    public static final String CODE_SWE = "792";
    public static final String CODE_KOR = "772";
    public static final String CODE_BEL = "805";
    public static final String CODE_PAN = "1836";
    public static final String CODE_TUN = "802";
    public static final String CODE_ENG = "770";
    public static final String CODE_POL = "794";
    public static final String CODE_SEN = "804";
    public static final String CODE_COL = "818";
    public static final String CODE_JAP = "766";

    // Competition code
    private static final String FIFA_CODE = "467";

    //Url
    public static final String FIXTURE_URL = "/v1/competitions/" + FIFA_CODE + "/fixtures";
    public static final String STANDINGS_URL = "/v1/competitions/" + FIFA_CODE + "/leagueTable";

    public static WeakReference<Context> context;

    public static Config init() {
        return new Config();
    }

    public void showToast(String message){
        Toast.makeText(context.get(), "" + message, Toast.LENGTH_SHORT).show();
    }

    public String getSquadUrl(String id){
        return "/v1/teams/" + id + "/players";
    }
}
