package com.codzunk.goalkik.data.repository;

import com.codzunk.goalkik.controllers.firebase.DatabaseRefController;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public interface DatabaseRepo extends ValueEventListener {
    DatabaseRepo init(DatabaseRefController refController, DatabaseReference databaseRef);
}
