
package com.codzunk.goalkik.data.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GoalKik {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
