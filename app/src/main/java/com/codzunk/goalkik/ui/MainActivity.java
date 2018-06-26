package com.codzunk.goalkik.ui;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.advert.AdService;
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
import com.codzunk.goalkik.controllers.model.ScoreModel;
import com.codzunk.goalkik.data.domain.football.standings.Standings;
import com.codzunk.goalkik.prefs.data.PrefDataManger;
import com.codzunk.goalkik.ui.adapters.DataAdapter;
import com.codzunk.goalkik.ui.adapters.FixAdapter;
import com.codzunk.goalkik.ui.adapters.ScoreAdapter;
import com.codzunk.goalkik.ui.adapters.StandAdapter;
import com.google.android.gms.ads.AdView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements DatabaseRefController, FixtureController, TableController {

    private RecyclerView recyclerView;
    private RecyclerView fixView;
    private RecyclerView standView;
    private RecyclerView scoreView;
    private PrefDataManger prefManger;
    private boolean isPlaying;
    private TextView info;

    private LinearLayout errorBoard;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Config.context = new WeakReference<>(getApplicationContext());

        //set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AdView mAdView = findViewById(R.id.adView);
        TextView ads = findViewById(R.id.ads);
        info = findViewById(R.id.internet);
        prefManger = ((GoalApp) getApplication()).getDataManger();

        AdService service = new AdService(mAdView, this, ads);
        service.initBanner();

        recyclerView = findViewById( R.id.listView);
        fixView = findViewById( R.id.fixView);
        standView = findViewById( R.id.standView);
        scoreView = findViewById( R.id.scoreView);
        errorBoard = findViewById( R.id.error_board);

        recyclerView.setNestedScrollingEnabled(false);
        fixView.setNestedScrollingEnabled(false);
        standView.setNestedScrollingEnabled(false);
        scoreView.setNestedScrollingEnabled(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager fixLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager scoreLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager standLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        fixView.setLayoutManager(fixLayoutManager);
        standView.setLayoutManager(standLayoutManager);
        scoreView.setLayoutManager(scoreLayoutManager);

        DatabaseRef.init().initGroupData(this);
        FixtureRef.init().initFixture(this, Config.FIXTURE_URL);
        TableRef.init().initFixture(this, Config.STANDINGS_URL);
    }

    @Override
    public void getList(List<GroupModel> modelList) {
        recyclerView.setAdapter(new DataAdapter(MainActivity.this, modelList));
    }

    @Override
    public void getFirebaseError(String message) {
        info.setText(message);
        info.setVisibility(View.VISIBLE);
    }

    @Override
    public void getFixture(List<FixtureModel> fixtureList) {
        fixView.setAdapter(new FixAdapter(fixtureList, this));
        setView(fixtureList);
    }

    @Override
    public void getFixtureError(String message) {
        if (prefManger.isPrefAvailable(Config.FIX_MODEL_DATA)){
            List<FixtureModel> fixtureList = prefManger.getFixtureModel();
            setView(fixtureList);
        }
        info.setText(message);
        errorBoard.setVisibility(View.VISIBLE);
    }

    @Override
    public void getTables(Standings standings) {
        standView.setAdapter(new StandAdapter(standings, this));
    }

    @Override
    public void getTableError(String message) {
        if (prefManger.isPrefAvailable(Config.STAND_DATA)){
            Standings standings = prefManger.getStandings();
            standView.setAdapter(new StandAdapter(standings, this));
        }
        info.setText(message);
        errorBoard.setVisibility(View.VISIBLE);
    }

    public void onFullFixtureClickEvent(View view) {
        startActivity(new Intent(this, FullFixtureActivity.class));
    }

    private void setView(List<FixtureModel> fixtureList){
        fixView.setAdapter(new FixAdapter(fixtureList, this));

        List<ScoreModel> modelList = new ArrayList<>(2);

        for (FixtureModel model:
                fixtureList) {

            if (model.getStatus().equals("IN_PLAY")){
                ScoreModel scoreModel = new ScoreModel();
                scoreModel.setHomeTeam(model.getHomeTeamName());
                scoreModel.setHomeGoal(model.getHome());
                scoreModel.setAwayTeam(model.getAwayTeamName());
                scoreModel.setAwayGoal(model.getAway());
                scoreModel.setPlaying(true);

                modelList.add(scoreModel);

                this.isPlaying = true;
            }
        }

        if (!isPlaying){
            ScoreModel scoreModel = new ScoreModel();
            scoreModel.setPlaying(false);
            modelList.add(scoreModel);
        }

        scoreView.setAdapter(new ScoreAdapter(modelList));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dash_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.rating:
                onUserRatingEvent();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onUserRatingEvent() {
        Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
        }
    }

    public void onReloadEventListener(View view) {
        finish();
        startActivity(getIntent());
    }
}
