package com.codzunk.goalkik.controllers;

import com.codzunk.goalkik.service.FixtureService;
import com.codzunk.goalkik.service.FixtureServiceImpl;

public class FixtureRef {
    private FixtureService service = new FixtureServiceImpl();
    public static FixtureRef init(){
        return new FixtureRef();
    }

    public void initFixture(FixtureController controller, String url){
        service.init(controller, url);
    }
}
