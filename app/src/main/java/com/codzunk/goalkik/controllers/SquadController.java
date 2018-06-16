package com.codzunk.goalkik.controllers;

import com.codzunk.goalkik.controllers.model.PlayerModel;

import java.util.List;

public interface SquadController {
    void getSquad(List<PlayerModel> playerList);
    void getError(String message);
}
