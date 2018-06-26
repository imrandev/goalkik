package com.codzunk.goalkik.controllers.model;

public class ScoreModel {
    private String homeTeam;
    private String homeGoal;
    private String awayTeam;
    private String awayGoal;
    private boolean isPlaying;

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getHomeGoal() {
        return homeGoal;
    }

    public void setHomeGoal(String homeGoal) {
        this.homeGoal = homeGoal;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getAwayGoal() {
        return awayGoal;
    }

    public void setAwayGoal(String awayGoal) {
        this.awayGoal = awayGoal;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
