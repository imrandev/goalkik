
package com.codzunk.goalkik.data.domain.football;

import java.util.List;

import com.codzunk.goalkik.data.domain.football.fixture.Fixture;
import com.codzunk.goalkik.data.domain.football.fixture.Links;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FootballOrg {

    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("fixtures")
    @Expose
    private List<Fixture> fixtures = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FootballOrg() {
    }

    /**
     * 
     * @param fixtures
     * @param count
     * @param links
     */
    public FootballOrg(Links links, Integer count, List<Fixture> fixtures) {
        super();
        this.links = links;
        this.count = count;
        this.fixtures = fixtures;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

}
