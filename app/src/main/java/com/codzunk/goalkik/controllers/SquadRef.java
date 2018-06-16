package com.codzunk.goalkik.controllers;

import com.codzunk.goalkik.service.SquadService;
import com.codzunk.goalkik.service.SquadServiceImpl;

public class SquadRef {
    private SquadService service = new SquadServiceImpl();
    public static SquadRef init(){
        return new SquadRef();
    }

    public void initSquad(SquadController controller, String url){
        service.init(controller, url);
    }
}
