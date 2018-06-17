package com.codzunk.goalkik.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.constant.Config;
import com.codzunk.goalkik.controllers.SquadController;
import com.codzunk.goalkik.controllers.SquadRef;
import com.codzunk.goalkik.controllers.model.PlayerModel;
import com.codzunk.goalkik.ui.adapters.SquadAdapter;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SquadActivity extends AppCompatActivity implements SquadController {

    private RecyclerView squadView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squad);

        //set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String code = "" + getIntent().getStringExtra("code");

        squadView = findViewById(R.id.squadView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        squadView.setLayoutManager(layoutManager);

        SquadRef.init().initSquad(this, Config.init().getSquadUrl(code));
    }

    @Override
    public void getSquad(List<PlayerModel> playerList) {
        if (playerList != null){
            squadView.setAdapter(new SquadAdapter(playerList, this));
        } else {
            Toast.makeText(this, "No data found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getError(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }
}
