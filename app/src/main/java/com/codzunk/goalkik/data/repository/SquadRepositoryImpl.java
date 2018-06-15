package com.codzunk.goalkik.data.repository;

import android.support.annotation.NonNull;

import com.codzunk.goalkik.application.GoalApp;
import com.codzunk.goalkik.constant.Config;
import com.codzunk.goalkik.controllers.SquadController;
import com.codzunk.goalkik.controllers.api.RetrofitClient;
import com.codzunk.goalkik.data.domain.football.squad.Player;
import com.codzunk.goalkik.data.domain.football.squad.Squad;
import com.codzunk.goalkik.prefs.data.PrefDataManger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SquadRepositoryImpl implements SquadRepository, Callback<Squad> {
    private SquadController controller;
    private PrefDataManger prefManger;

    @Override
    public void init(SquadController controller, String url) {
        this.controller = controller;
        prefManger = ((GoalApp) Config.context.get()).getDataManger();
        RetrofitClient.getInstance().getSquad(url).enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<Squad> call, @NonNull Response<Squad> response) {
        Squad squad = response.body();

        if (squad != null){
            List<Player> playerList = squad.getPlayers();
            controller.getSquad(playerList);
        }
    }

    @Override
    public void onFailure(@NonNull Call<Squad> call, @NonNull Throwable t) {
        controller.getError(t.getMessage());
    }
}
