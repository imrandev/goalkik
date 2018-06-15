package com.codzunk.goalkik.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.controllers.model.LeagueModel;
import com.codzunk.goalkik.ui.adapters.TableAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TableActivity extends AppCompatActivity {

    private AdView mAdView;
    private TextView ads;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        mAdView = findViewById(R.id.adView);
        ads = findViewById(R.id.ads);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                mAdView.setVisibility(View.GONE);
                ads.setVisibility(View.GONE);
            }
        });

        ArrayList<LeagueModel> arrayList = (ArrayList<LeagueModel>) getIntent().getSerializableExtra("arrayList");

        RecyclerView tableView = findViewById(R.id.tableView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        tableView.setLayoutManager(layoutManager);

        tableView.setAdapter(new TableAdapter(arrayList, this));
    }
}
