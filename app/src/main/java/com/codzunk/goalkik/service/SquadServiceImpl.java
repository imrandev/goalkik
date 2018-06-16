package com.codzunk.goalkik.service;

import com.codzunk.goalkik.controllers.SquadController;
import com.codzunk.goalkik.data.repository.SquadRepository;
import com.codzunk.goalkik.data.repository.SquadRepositoryImpl;

public class SquadServiceImpl implements SquadService {
    private SquadRepository repository = new SquadRepositoryImpl();

    @Override
    public void init(SquadController controller, String url) {
        repository.init(controller, url);
    }
}
