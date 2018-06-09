package com.codzunk.goalkik.controllers.model;

import java.util.List;

public class GroupModel {
    private String name;
    private List<ItemModel> itemModels;
    private String success;
    private String error;

    public GroupModel() {
    }

    public GroupModel(String name, List<ItemModel> itemModels, String success, String error) {
        this.name = name;
        this.itemModels = itemModels;
        this.success = success;
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemModel> getItemModels() {
        return itemModels;
    }

    public void setItemModels(List<ItemModel> itemModels) {
        this.itemModels = itemModels;
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
