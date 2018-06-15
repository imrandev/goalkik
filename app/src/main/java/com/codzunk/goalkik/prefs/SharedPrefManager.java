package com.codzunk.goalkik.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.codzunk.goalkik.constant.Config;
import com.codzunk.goalkik.controllers.model.FixtureModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPrefManager {

    private SharedPreferences sharedPreferences;

    public SharedPrefManager(Context context){
        sharedPreferences = context.getSharedPreferences(Config.MY_PREF, Context.MODE_PRIVATE);
    }

    public void clear(){
        sharedPreferences.edit().clear().apply();
    }

    public void setMyTeam(String team){
        sharedPreferences.edit().putString(Config.MY_TEAM, team).apply();
    }

    public String getMyTeam(){
        return sharedPreferences.getString(Config.MY_TEAM, Config.DEFAULT_TEAM);
    }

    public void setFixture(List<FixtureModel> modelList){
        Gson gson = new Gson();
        sharedPreferences.edit().putString(Config.FIX_DATA, gson.toJson(modelList)).apply();
    }

    public List<FixtureModel> getFixture(){
        List<FixtureModel> modelList;

        if(sharedPreferences.contains(Config.GROUP_DATA)){
            Gson gson = new Gson();
            String json = sharedPreferences.getString(Config.FIX_DATA,null);
            FixtureModel [] models = gson.fromJson(json,FixtureModel[].class);

            modelList = Arrays.asList(models);
            modelList = new ArrayList<>(modelList);
        } else {
            return null;
        }
        return modelList;
    }

    public boolean isPrefAvailable(String item){
        return sharedPreferences.contains(item);
    }
}
