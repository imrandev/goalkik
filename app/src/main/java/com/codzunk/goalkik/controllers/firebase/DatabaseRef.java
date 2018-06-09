package com.codzunk.goalkik.controllers.firebase;

import com.codzunk.goalkik.service.DatabaseService;
import com.codzunk.goalkik.service.DatabaseServiceImpl;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseRef {
    private static DatabaseReference databaseRef;
    private DatabaseService service = new DatabaseServiceImpl();

    public static DatabaseRef init(){
        DatabaseRef.databaseRef = FirebaseDatabase.getInstance().getReference();
        return new DatabaseRef();
    }

    public void initGroupData(DatabaseRefController refController){
        service.getGroupData(refController, databaseRef);
    }

    public void initWinnerData(DatabaseRefController refController){
        service.getWinnerData(refController, databaseRef);
    }
}
