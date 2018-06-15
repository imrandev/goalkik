package com.codzunk.goalkik.data.repository;

import com.codzunk.goalkik.controllers.TableController;

public interface TableRepository {
    void init(TableController controller, String url);
}
