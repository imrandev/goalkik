
package com.codzunk.goalkik.data.domain.firebase;

import java.util.List;

public class Winner {

    private String team;
    private int won;
    private List<Timeline> timeline;

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

    public List<Timeline> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<Timeline> timeline) {
        this.timeline = timeline;
    }

}
