package com.codzunk.goalkik.service;

import com.codzunk.goalkik.controllers.firebase.DatabaseRefController;
import com.google.firebase.database.DatabaseReference;

public interface DatabaseService {
    void getGroupData(DatabaseRefController refController, DatabaseReference databaseRef);
    void getWinnerData(DatabaseRefController refController, DatabaseReference databaseRef);
}
