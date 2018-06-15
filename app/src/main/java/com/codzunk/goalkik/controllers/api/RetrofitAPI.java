package com.codzunk.goalkik.controllers.api;

import com.codzunk.goalkik.data.domain.football.FootballOrg;
import com.codzunk.goalkik.data.domain.football.squad.Squad;
import com.codzunk.goalkik.data.domain.football.standings.LeagueTable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface RetrofitAPI {
    @Headers("X-Auth-Token: af50ecedd7404682b31476c555b92ec9")
    @GET
    Call<FootballOrg> getResults(@Url String URL);

    @Headers("X-Auth-Token: af50ecedd7404682b31476c555b92ec9")
    @GET
    Call<LeagueTable> getTables(@Url String URL);

    @Headers("X-Auth-Token: af50ecedd7404682b31476c555b92ec9")
    @GET
    Call<Squad> getSquad(@Url String URL);
}
