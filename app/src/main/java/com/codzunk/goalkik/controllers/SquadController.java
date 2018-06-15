package com.codzunk.goalkik.controllers;

import com.codzunk.goalkik.data.domain.football.squad.Player;

import java.util.List;

public interface SquadController {
    void getSquad(List<Player> playerList);
    void getError(String message);
}
