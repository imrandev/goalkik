
package com.codzunk.goalkik.data.domain.firebase;

import java.util.List;

public class Data {

    private List<Group> group;

    private List<Winner> winners;

    public List<Group> getGroup() {
        return group;
    }

    public void setGroup(List<Group> group) {
        this.group = group;
    }

    public List<Winner> getWinners() {
        return winners;
    }

    public void setWinners(List<Winner> winners) {
        this.winners = winners;
    }

}
