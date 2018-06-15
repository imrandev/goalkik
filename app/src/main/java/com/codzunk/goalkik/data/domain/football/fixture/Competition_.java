
package com.codzunk.goalkik.data.domain.football.fixture;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Competition_ {

    @SerializedName("href")
    @Expose
    private String href;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Competition_() {
    }

    /**
     * 
     * @param href
     */
    public Competition_(String href) {
        super();
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
