package com.codzunk.goalkik.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.advert.AdService;
import com.codzunk.goalkik.controllers.model.LeagueModel;
import com.codzunk.goalkik.ui.adapters.TableAdapter;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        //set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        AdView mAdView = findViewById(R.id.adView);
        TextView ads = findViewById(R.id.ads);

        AdService service = new AdService(mAdView, this, ads);
        service.initBanner();

        ArrayList<LeagueModel> arrayList = (ArrayList<LeagueModel>) getIntent().getSerializableExtra("arrayList");

        RecyclerView tableView = findViewById(R.id.tableView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        tableView.setLayoutManager(layoutManager);

        tableView.setAdapter(new TableAdapter(arrayList, this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }
}
