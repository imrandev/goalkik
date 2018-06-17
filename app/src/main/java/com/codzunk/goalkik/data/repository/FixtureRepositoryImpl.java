package com.codzunk.goalkik.data.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.codzunk.goalkik.application.GoalApp;
import com.codzunk.goalkik.constant.Config;
import com.codzunk.goalkik.controllers.FixtureController;
import com.codzunk.goalkik.controllers.api.RetrofitClient;
import com.codzunk.goalkik.controllers.model.FixtureModel;
import com.codzunk.goalkik.data.domain.football.fixture.Fixture;
import com.codzunk.goalkik.data.domain.football.FootballOrg;
import com.codzunk.goalkik.prefs.data.PrefDataManger;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FixtureRepositoryImpl implements FixtureRepository, Callback<FootballOrg> {

    private FixtureController controller;
    private PrefDataManger prefManger;

    @Override
    public void init(FixtureController controller, String url) {
        this.controller = controller;
        prefManger = ((GoalApp) Config.context.get()).getDataManger();
        RetrofitClient.getInstance().getResults(url).enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<FootballOrg> call, @NonNull Response<FootballOrg> response) {
        FootballOrg footballOrg = response.body();

        if (footballOrg != null){
            List<Fixture> fixtureList = footballOrg.getFixtures();
            prefManger.setFixture(fixtureList);
            List<FixtureModel> modelList = new ArrayList<>(2);
            int size = fixtureList.size();
            for (int i=0; i<size; i++){
                try {
                    String date = convertUTCFormat(fixtureList.get(i).getDate()).split("\\s+")[0];
                    String time = convertUTCFormat(fixtureList.get(i).getDate()).split("\\s+")[1]
                            + convertUTCFormat(fixtureList.get(i).getDate()).split("\\s+")[2];

                    String status = fixtureList.get(i).getStatus();

                    if (isTodayOrTomorrow(date) == 0){
                        FixtureModel fixture = new FixtureModel();
                        fixture.setCount(i+1);
                        fixture.setHomeTeamName(fixtureList.get(i).getHomeTeamName());
                        fixture.setAwayTeamName(fixtureList.get(i).getAwayTeamName());
                        fixture.setDate(date);
                        fixture.setTime(time);
                        fixture.setStatus(fixtureList.get(i).getStatus());
                        fixture.setToday(1);
                        if (fixtureList.get(i).getResult().getGoalsHomeTeam() != null){
                            fixture.setHome("" + fixtureList.get(i).getResult().getGoalsHomeTeam().toString().charAt(0));
                            fixture.setAway("" + fixtureList.get(i).getResult().getGoalsAwayTeam().toString().charAt(0));
                        }
                        modelList.add(fixture);
                    } else if (isTodayOrTomorrow(date) == -1){
                        FixtureModel fixture = new FixtureModel();
                        fixture.setCount(i+1);
                        fixture.setHomeTeamName(fixtureList.get(i).getHomeTeamName());
                        fixture.setAwayTeamName(fixtureList.get(i).getAwayTeamName());
                        fixture.setDate(date);
                        fixture.setTime(time);
                        fixture.setStatus(fixtureList.get(i).getStatus());
                        fixture.setToday(-1);
                        if (fixtureList.get(i).getResult().getGoalsHomeTeam() != null){
                            fixture.setHome("" + fixtureList.get(i).getResult().getGoalsHomeTeam().toString().charAt(0));
                            fixture.setAway("" + fixtureList.get(i).getResult().getGoalsAwayTeam().toString().charAt(0));
                        }
                        modelList.add(fixture);
                    } else if (isTodayOrTomorrow(date) == 1){
                        FixtureModel fixture = new FixtureModel();
                        fixture.setCount(i+1);
                        fixture.setHomeTeamName(fixtureList.get(i).getHomeTeamName());
                        fixture.setAwayTeamName(fixtureList.get(i).getAwayTeamName());
                        fixture.setDate(date);
                        fixture.setTime(time);
                        fixture.setStatus(fixtureList.get(i).getStatus());
                        fixture.setToday(0);

                        modelList.add(fixture);
                    } else {
                        if (status.equals("IN_PLAY")){
                            FixtureModel fixture = new FixtureModel();
                            fixture.setCount(i+1);
                            fixture.setHomeTeamName(fixtureList.get(i).getHomeTeamName());
                            fixture.setAwayTeamName(fixtureList.get(i).getAwayTeamName());
                            fixture.setDate(date);
                            fixture.setTime(time);
                            fixture.setStatus(fixtureList.get(i).getStatus());
                            fixture.setToday(1);
                            if (fixtureList.get(i).getResult().getGoalsHomeTeam() != null){
                                fixture.setHome("" + fixtureList.get(i).getResult().getGoalsHomeTeam().toString().charAt(0));
                                fixture.setAway("" + fixtureList.get(i).getResult().getGoalsAwayTeam().toString().charAt(0));
                            }
                            modelList.add(fixture);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            prefManger.setFixtureModel(modelList);
            controller.getFixture(modelList);
        }
    }

    @Override
    public void onFailure(@NonNull Call<FootballOrg> call, @NonNull Throwable t) {
        controller.getFixtureError(t.getMessage());
    }

    /*
        This function convert the UTC TZ to regular date time formation
     */
    private static String convertUTCFormat(String dateStr) throws ParseException {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        sourceFormat.setTimeZone(utc);
        Date convertedDate = sourceFormat.parse(dateStr);

        Date date = destFormat.parse(destFormat.format(convertedDate));
        destFormat.setTimeZone(TimeZone.getDefault());

        String[] dateTime = destFormat.format(date).split("\\s+");

        SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm", Locale.getDefault());
        SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        Date _24HourDt = _24HourSDF.parse(dateTime[1]);

        return dateTime[0] + " " + _12HourSDF.format(_24HourDt);
    }

    private int isTodayOrTomorrow(String date){

        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        Calendar calT = Calendar.getInstance();
        calT.setTime(today);
        calT.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = calT.getTime();

        Calendar calY = Calendar.getInstance();
        calY.setTime(today);
        calY.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = calY.getTime();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        String todayAsString = dateFormat.format(today);
        String tomorrowAsString = dateFormat.format(tomorrow);
        String yesterdayAsString = dateFormat.format(yesterday);

        if (date.equals(todayAsString)) {
            return 0;
        } else if (date.equals(tomorrowAsString)) {
            return 1;
        } else if (date.equals(yesterdayAsString)){
            return -1;
        }  else {
            return 2;
        }
    }
}
