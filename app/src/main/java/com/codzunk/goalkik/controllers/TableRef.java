package com.codzunk.goalkik.controllers;

import com.codzunk.goalkik.service.TableService;
import com.codzunk.goalkik.service.TableServiceImpl;

public class TableRef {
    private TableService service = new TableServiceImpl();
    public static TableRef init(){
        return new TableRef();
    }

    public void initFixture(TableController controller, String url){
        service.init(controller, url);
    }
}
