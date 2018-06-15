package com.codzunk.goalkik.data.repository;

import com.codzunk.goalkik.controllers.FixtureController;

public interface FixtureRepository {
    void init(FixtureController controller, String url);
}
