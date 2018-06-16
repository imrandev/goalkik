package com.codzunk.goalkik.prefs.data;

import com.codzunk.goalkik.controllers.model.FixtureModel;
import com.codzunk.goalkik.data.domain.football.fixture.Fixture;
import com.codzunk.goalkik.data.domain.football.squad.Squad;
import com.codzunk.goalkik.data.domain.football.standings.Standings;
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

    public List<Fixture> getFixture(){
        return prefManager.getFixture();
    }

    public void setFixture(List<Fixture> modelList){
        prefManager.setFixture(modelList);
    }

    public List<FixtureModel> getFixtureModel(){
        return prefManager.getFixtureModel();
    }

    public void setFixtureModel(List<FixtureModel> modelList){
        prefManager.setFixtureModel(modelList);
    }

    public Standings getStandings(){
        return prefManager.getStandings();
    }

    public void setStandings(Standings standings){
        prefManager.setStandings(standings);
    }

    public Squad getSquad(){
        return prefManager.getSquad();
    }

    public void setSquad(Squad squad){
        prefManager.setSquad(squad);
    }

    public boolean isPrefAvailable(String item){
        return prefManager.isPrefAvailable(item);
    }
}
