package com.codzunk.goalkik.data.repository;

import android.support.annotation.NonNull;

import com.codzunk.goalkik.application.GoalApp;
import com.codzunk.goalkik.constant.Config;
import com.codzunk.goalkik.controllers.TableController;
import com.codzunk.goalkik.controllers.api.RetrofitClient;
import com.codzunk.goalkik.data.domain.football.standings.LeagueTable;
import com.codzunk.goalkik.data.domain.football.standings.Standings;
import com.codzunk.goalkik.prefs.data.PrefDataManger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableRepositoryImpl implements TableRepository, Callback<LeagueTable> {
    private TableController controller;
    private PrefDataManger prefManger;

    @Override
    public void init(TableController controller, String url) {
        this.controller = controller;
        prefManger = ((GoalApp) Config.context.get()).getDataManger();
        RetrofitClient.getInstance().getTables(url).enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<LeagueTable> call, @NonNull Response<LeagueTable> response) {
        LeagueTable leagueTable = response.body();

        if (leagueTable != null){
            Standings standings = leagueTable.getStandings();
            prefManger.setStandings(standings);
            controller.getTables(standings);
        }
    }

    @Override
    public void onFailure(@NonNull Call<LeagueTable> call, @NonNull Throwable t) {
        controller.getTableError(t.getMessage());
    }
}
