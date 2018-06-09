package com.codzunk.goalkik.prefs.data;

import com.codzunk.goalkik.controllers.model.GroupModel;
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

    public List<GroupModel> getGroup(){
        return prefManager.getGroup();
    }

    public void setGroup(List<GroupModel> modelList){
        prefManager.setGroup(modelList);
    }

    public boolean isPrefAvailable(String item){
        return prefManager.isPrefAvailable(item);
    }
}
