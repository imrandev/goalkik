
package com.codzunk.goalkik.data.domain.firebase;

import java.util.List;

public class Group {

    private String name;
    private List<Item> item;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

}
