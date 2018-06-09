package com.codzunk.goalkik.ui;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.application.GoalApp;
import com.codzunk.goalkik.controllers.firebase.DatabaseRef;
import com.codzunk.goalkik.controllers.firebase.DatabaseRefController;
import com.codzunk.goalkik.controllers.model.GroupModel;
import com.codzunk.goalkik.prefs.data.PrefDataManger;
import com.codzunk.goalkik.ui.adapters.DataAdapter;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements DatabaseRefController {

    private RecyclerView recyclerView;
    private PrefDataManger prefManger;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefManger = ((GoalApp) getApplication()).getDataManger();

        recyclerView = findViewById( R.id.listView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        DatabaseRef.init().initGroupData(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 2000);
    }

    @Override
    public void getList(List<GroupModel> modelList) {
        recyclerView.setAdapter(new DataAdapter(MainActivity.this, modelList));
    }
}
