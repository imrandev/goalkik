package com.codzunk.goalkik.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.codzunk.goalkik.constant.Config;
import com.codzunk.goalkik.controllers.model.FixtureModel;
import com.codzunk.goalkik.data.domain.football.fixture.Fixture;
import com.codzunk.goalkik.data.domain.football.squad.Squad;
import com.codzunk.goalkik.data.domain.football.standings.Standings;
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

    public void setFixture(List<Fixture> modelList){
        Gson gson = new Gson();
        sharedPreferences.edit().putString(Config.FIX_DATA, gson.toJson(modelList)).apply();
    }

    public List<Fixture> getFixture(){
        List<Fixture> modelList;

        if(sharedPreferences.contains(Config.FIX_DATA)){
            Gson gson = new Gson();
            String json = sharedPreferences.getString(Config.FIX_DATA,null);
            Fixture [] models = gson.fromJson(json, Fixture[].class);

            modelList = Arrays.asList(models);
            modelList = new ArrayList<>(modelList);
        } else {
            return null;
        }
        return modelList;
    }

    public void setFixtureModel(List<FixtureModel> modelList){
        Gson gson = new Gson();
        sharedPreferences.edit().putString(Config.FIX_MODEL_DATA, gson.toJson(modelList)).apply();
    }

    public List<FixtureModel> getFixtureModel(){
        List<FixtureModel> modelList;

        if(sharedPreferences.contains(Config.FIX_MODEL_DATA)){
            Gson gson = new Gson();
            String json = sharedPreferences.getString(Config.FIX_MODEL_DATA,null);
            FixtureModel [] models = gson.fromJson(json, FixtureModel[].class);

            modelList = Arrays.asList(models);
            modelList = new ArrayList<>(modelList);
        } else {
            return null;
        }
        return modelList;
    }

    public void setStandings(Standings model){
        Gson gson = new Gson();
        sharedPreferences.edit().putString(Config.STAND_DATA, gson.toJson(model)).apply();
    }

    public Standings getStandings(){
        Standings model;

        if(sharedPreferences.contains(Config.STAND_DATA)){
            Gson gson = new Gson();
            String json = sharedPreferences.getString(Config.STAND_DATA,null);
            model = gson.fromJson(json, Standings.class);
        } else {
            return null;
        }
        return model;
    }

    public void setSquad(Squad model){
        Gson gson = new Gson();
        sharedPreferences.edit().putString(Config.SQUAD_DATA, gson.toJson(model)).apply();
    }

    public Squad getSquad(){
        Squad model;

        if(sharedPreferences.contains(Config.SQUAD_DATA)){
            Gson gson = new Gson();
            String json = sharedPreferences.getString(Config.SQUAD_DATA,null);
            model = gson.fromJson(json, Squad.class);
        } else {
            return null;
        }
        return model;
    }

    public boolean isPrefAvailable(String item){
        return sharedPreferences.contains(item);
    }
}
