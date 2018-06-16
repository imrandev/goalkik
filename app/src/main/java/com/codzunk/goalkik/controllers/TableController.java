package com.codzunk.goalkik.controllers;


import com.codzunk.goalkik.data.domain.football.standings.Standings;

public interface TableController {
    void getTables(Standings standings);
    void getTableError(String message);
}
