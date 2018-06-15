package com.codzunk.goalkik.prefs.data;

import com.codzunk.goalkik.controllers.model.FixtureModel;
import com.codzunk.goalkik.prefs.SharedPrefManager;

import java.util.List;

public class PrefDataManger {
    private SharedPrefManager prefManager;

    public PrefDataManger(SharedPrefManager prefManager){
        this.prefManager = prefManager;
    }

    public void clear(){
        prefManager.clear();
    }

    public void setMyTeam(String team){
        prefManager.setMyTeam(team);
    }

    public String getMyTeam(){
        return prefManager.getMyTeam();
    }

    public List<FixtureModel> getFixture(){
        return prefManager.getFixture();
    }

    public void setFixture(List<FixtureModel> modelList){
        prefManager.setFixture(modelList);
    }

    public boolean isPrefAvailable(String item){
        return prefManager.isPrefAvailable(item);
    }
}
