package com.codzunk.goalkik.service;

import com.codzunk.goalkik.controllers.FixtureController;
import com.codzunk.goalkik.data.repository.FixtureRepository;
import com.codzunk.goalkik.data.repository.FixtureRepositoryImpl;

public class FixtureServiceImpl implements FixtureService {

    private FixtureRepository repository = new FixtureRepositoryImpl();
    @Override
    public void init(FixtureController controller, String url) {
        repository.init(controller, url);
    }
}
