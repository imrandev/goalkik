package com.codzunk.goalkik.data.repository;

import android.support.annotation.NonNull;

import com.codzunk.goalkik.application.GoalApp;
import com.codzunk.goalkik.constant.Config;
import com.codzunk.goalkik.controllers.firebase.DatabaseRefController;
import com.codzunk.goalkik.controllers.model.GroupModel;
import com.codzunk.goalkik.controllers.model.ItemModel;
import com.codzunk.goalkik.controllers.model.WinnerModel;
import com.codzunk.goalkik.data.domain.firebase.Data;
import com.codzunk.goalkik.data.domain.firebase.Group;
import com.codzunk.goalkik.data.domain.firebase.Item;
import com.codzunk.goalkik.prefs.data.PrefDataManger;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRepoImpl implements DatabaseRepo {

    private List<GroupModel> groupList = new ArrayList<>(2);
    private List<WinnerModel> winnerList = new ArrayList<>(2);
    private PrefDataManger prefManager;
    private DatabaseRefController refController;

    @Override
    public DatabaseRepo init(DatabaseRefController refController, DatabaseReference databaseRef) {
        this.refController = refController;
        prefManager = ((GoalApp) Config.context.get()).getDataManger();
        databaseRef.child(Config.ROOT_NODE).addValueEventListener(this);
        return new DatabaseRepoImpl();
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Data data = dataSnapshot.getValue(Data.class);

        if (data != null){
            for (Group group:
                 data.getGroup()) {
                GroupModel model = new GroupModel();
                model.setName(group.getName());
                List<ItemModel> itemModels = new ArrayList<>(2);
                for (Item item:
                        group.getItem()) {
                    ItemModel itemModel = new ItemModel();
                    itemModel.setCoach(item.getCoach());
                    itemModel.setRank(item.getRank());
                    itemModel.setHLevel(item.getHLevel());
                    itemModel.setPlayed(item.getPlayed());
                    itemModel.setStarPlayer(item.getStarPlayer());
                    itemModel.setTeam(item.getTeam());
                    itemModel.setIcon(item.getIcon());
                    itemModel.setCode(item.getCode());

                    itemModels.add(itemModel);
                }
                model.setItemModels(itemModels);
                groupList.add(model);
            }
            refController.getList(groupList);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        refController.getFirebaseError(databaseError.getMessage());
    }
}
