package com.codzunk.goalkik.data.repository;

import android.support.annotation.NonNull;

import com.codzunk.goalkik.application.GoalApp;
import com.codzunk.goalkik.constant.Config;
import com.codzunk.goalkik.controllers.SquadController;
import com.codzunk.goalkik.controllers.api.RetrofitClient;
import com.codzunk.goalkik.controllers.model.PlayerModel;
import com.codzunk.goalkik.data.domain.football.squad.Player;
import com.codzunk.goalkik.data.domain.football.squad.Squad;
import com.codzunk.goalkik.prefs.data.PrefDataManger;

import java.util.ArrayList;
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
        prefManger.setSquad(squad);

        if (squad != null){
            List<Player> playerList = squad.getPlayers();
            List<PlayerModel> modelList = new ArrayList<>(2);
            for (Player player: playerList) {
                PlayerModel model = new PlayerModel();
                model.setName(player.getName());
                String jersey = "" + player.getJerseyNumber();
                model.setJerseyNumber(jersey);
                model.setPosition(player.getPosition());
                model.setDateOfBirth(player.getDateOfBirth());
                modelList.add(model);
            }
            controller.getSquad(modelList);
        }
    }

    @Override
    public void onFailure(@NonNull Call<Squad> call, @NonNull Throwable t) {
        controller.getError(t.getMessage());
    }
}
