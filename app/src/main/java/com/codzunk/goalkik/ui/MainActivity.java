package com.codzunk.goalkik.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.application.GoalApp;
import com.codzunk.goalkik.constant.Config;
import com.codzunk.goalkik.controllers.FixtureController;
import com.codzunk.goalkik.controllers.FixtureRef;
import com.codzunk.goalkik.controllers.TableController;
import com.codzunk.goalkik.controllers.TableRef;
import com.codzunk.goalkik.controllers.firebase.DatabaseRef;
import com.codzunk.goalkik.controllers.firebase.DatabaseRefController;
import com.codzunk.goalkik.controllers.model.FixtureModel;
import com.codzunk.goalkik.controllers.model.GroupModel;
import com.codzunk.goalkik.data.domain.football.standings.Standings;
import com.codzunk.goalkik.prefs.data.PrefDataManger;
import com.codzunk.goalkik.ui.adapters.DataAdapter;
import com.codzunk.goalkik.ui.adapters.FixAdapter;
import com.codzunk.goalkik.ui.adapters.StandAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements DatabaseRefController, FixtureController, TableController {

    private RecyclerView recyclerView;
    private RecyclerView fixView;
    private RecyclerView standView;
    private PrefDataManger prefManger;
    private LinearLayout scoreBoard;
    private boolean isPlaying;
    private AdView mAdView;

    private TextView home, homeScore, away, awayScore, timeState, ads;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.adView);
        ads = findViewById(R.id.ads);
        prefManger = ((GoalApp) getApplication()).getDataManger();

        MobileAds.initialize(this, "ca-app-pub-9551927371844997~1007897942");
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

        recyclerView = findViewById( R.id.listView);
        fixView = findViewById( R.id.fixView);
        standView = findViewById( R.id.standView);

        home = findViewById(R.id.home);
        homeScore = findViewById(R.id.home_score);
        away = findViewById(R.id.away);
        awayScore = findViewById(R.id.away_score);
        timeState = findViewById(R.id.timing_state);
        scoreBoard = findViewById(R.id.score_board);


        recyclerView.setNestedScrollingEnabled(false);
        fixView.setNestedScrollingEnabled(false);
        standView.setNestedScrollingEnabled(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager fixLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager standLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        fixView.setLayoutManager(fixLayoutManager);
        standView.setLayoutManager(standLayoutManager);

        DatabaseRef.init().initGroupData(this);

        FixtureRef.init().initFixture(this, Config.FIXTURE_URL);

        TableRef.init().initFixture(this, Config.STANDINGS_URL);
    }

    @Override
    public void getList(List<GroupModel> modelList) {
        recyclerView.setAdapter(new DataAdapter(MainActivity.this, modelList));
    }

    @Override
    public void getFixture(List<FixtureModel> fixtureList) {
        fixView.setAdapter(new FixAdapter(fixtureList, this));

        for (FixtureModel model:
             fixtureList) {

            if (model.getStatus().equals("IN_PLAY")){
                home.setText(model.getHomeTeamName());
                homeScore.setText(model.getHome());
                away.setText(model.getAwayTeamName());
                awayScore.setText(model.getAway());

                scoreBoard.setVisibility(View.VISIBLE);
                timeState.setVisibility(View.GONE);

                this.isPlaying = true;
                break;
            }
        }

        if (!isPlaying){
            timeState.setText(R.string.no_match);
            scoreBoard.setVisibility(View.GONE);
            timeState.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getTables(Standings standings) {
        standView.setAdapter(new StandAdapter(standings, this));
    }

    @Override
    public void getError(String message) {

    }
}
