package com.codzunk.goalkik.controllers;

import com.codzunk.goalkik.controllers.model.FixtureModel;

import java.util.List;

public interface FixtureController {
    void getFixture(List<FixtureModel> fixtureList);
    void getError(String message);
}
