package com.codzunk.goalkik.controllers.model;

public class WinnerModel {
    private String team;
    private int won;
    private int year;
    private String opponent;
    private String score;
    private boolean extraMin;
    private boolean penalty;
    private String brazil;
    private String germany;
    private String icon;
    private String italy;
    private String czechoslovakia;
    private String sweden;
    private String argentina;
    private String netherlands;
    private String hungary;
    private String france;
    private String uruguay;
    private String spain;
    private String england;
    private String success;
    private String error;

    public WinnerModel() {
    }

    public WinnerModel(String team, int won, int year, String opponent, String score,
                       boolean extraMin, boolean penalty, String brazil, String germany,
                       String icon, String italy, String czechoslovakia, String sweden,
                       String argentina, String netherlands, String hungary, String france,
                       String uruguay, String spain, String england, String success, String error) {
        this.team = team;
        this.won = won;
        this.year = year;
        this.opponent = opponent;
        this.score = score;
        this.extraMin = extraMin;
        this.penalty = penalty;
        this.brazil = brazil;
        this.germany = germany;
        this.icon = icon;
        this.italy = italy;
        this.czechoslovakia = czechoslovakia;
        this.sweden = sweden;
        this.argentina = argentina;
        this.netherlands = netherlands;
        this.hungary = hungary;
        this.france = france;
        this.uruguay = uruguay;
        this.spain = spain;
        this.england = england;
        this.success = success;
        this.error = error;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public boolean isExtraMin() {
        return extraMin;
    }

    public void setExtraMin(boolean extraMin) {
        this.extraMin = extraMin;
    }

    public boolean isPenalty() {
        return penalty;
    }

    public void setPenalty(boolean penalty) {
        this.penalty = penalty;
    }

    public String getBrazil() {
        return brazil;
    }

    public void setBrazil(String brazil) {
        this.brazil = brazil;
    }

    public String getGermany() {
        return germany;
    }

    public void setGermany(String germany) {
        this.germany = germany;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getItaly() {
        return italy;
    }

    public void setItaly(String italy) {
        this.italy = italy;
    }

    public String getCzechoslovakia() {
        return czechoslovakia;
    }

    public void setCzechoslovakia(String czechoslovakia) {
        this.czechoslovakia = czechoslovakia;
    }

    public String getSweden() {
        return sweden;
    }

    public void setSweden(String sweden) {
        this.sweden = sweden;
    }

    public String getArgentina() {
        return argentina;
    }

    public void setArgentina(String argentina) {
        this.argentina = argentina;
    }

    public String getNetherlands() {
        return netherlands;
    }

    public void setNetherlands(String netherlands) {
        this.netherlands = netherlands;
    }

    public String getHungary() {
        return hungary;
    }

    public void setHungary(String hungary) {
        this.hungary = hungary;
    }

    public String getFrance() {
        return france;
    }

    public void setFrance(String france) {
        this.france = france;
    }

    public String getUruguay() {
        return uruguay;
    }

    public void setUruguay(String uruguay) {
        this.uruguay = uruguay;
    }

    public String getSpain() {
        return spain;
    }

    public void setSpain(String spain) {
        this.spain = spain;
    }

    public String getEngland() {
        return england;
    }

    public void setEngland(String england) {
        this.england = england;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
