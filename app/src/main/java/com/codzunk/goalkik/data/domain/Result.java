
package com.codzunk.goalkik.data.domain;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    private String score;
    private List<Goal> goal;
    private boolean extraMin;
    private boolean penalty;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<Goal> getGoal() {
        return goal;
    }

    public void setGoal(List<Goal> goal) {
        this.goal = goal;
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

}
