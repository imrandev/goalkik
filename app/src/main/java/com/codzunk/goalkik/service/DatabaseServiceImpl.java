package com.codzunk.goalkik.service;

import com.codzunk.goalkik.controllers.firebase.DatabaseRefController;
import com.codzunk.goalkik.data.repository.DatabaseRepo;
import com.codzunk.goalkik.data.repository.DatabaseRepoImpl;
import com.google.firebase.database.DatabaseReference;

public class DatabaseServiceImpl implements DatabaseService {

    private DatabaseRepo databaseRepo = new DatabaseRepoImpl();

    @Override
    public void getGroupData(DatabaseRefController refController, DatabaseReference databaseRef) {
        databaseRepo.init(refController, databaseRef);
    }

    @Override
    public void getWinnerData(DatabaseRefController refController, DatabaseReference databaseRef) {
        databaseRepo.init(refController, databaseRef);
    }
}
