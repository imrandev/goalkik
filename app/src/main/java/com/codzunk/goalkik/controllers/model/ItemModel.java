package com.codzunk.goalkik.controllers.model;

public class ItemModel {
    private String team;
    private int rank;
    private String played;
    private String hLevel;
    private String coach;
    private String starPlayer;
    private String icon;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getPlayed() {
        return played;
    }

    public void setPlayed(String played) {
        this.played = played;
    }

    public String getHLevel() {
        return hLevel;
    }

    public void setHLevel(String hLevel) {
        this.hLevel = hLevel;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getStarPlayer() {
        return starPlayer;
    }

    public void setStarPlayer(String starPlayer) {
        this.starPlayer = starPlayer;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
