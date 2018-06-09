package com.codzunk.goalkik.controllers.firebase;

import com.codzunk.goalkik.controllers.model.GroupModel;

import java.util.List;

public interface DatabaseRefController {
    void getList(List<GroupModel> modelList);
}
