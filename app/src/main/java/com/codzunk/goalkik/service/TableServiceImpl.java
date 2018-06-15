package com.codzunk.goalkik.service;

import com.codzunk.goalkik.controllers.TableController;
import com.codzunk.goalkik.data.repository.TableRepository;
import com.codzunk.goalkik.data.repository.TableRepositoryImpl;

public class TableServiceImpl implements TableService {
    private TableRepository repository = new TableRepositoryImpl();
    @Override
    public void init(TableController controller, String url) {
        repository.init(controller, url);
    }
}
