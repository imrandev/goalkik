package com.codzunk.goalkik.ui;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.application.GoalApp;
import com.codzunk.goalkik.constant.Config;
import com.codzunk.goalkik.controllers.model.FixtureModel;
import com.codzunk.goalkik.data.domain.football.fixture.Fixture;
import com.codzunk.goalkik.prefs.data.PrefDataManger;
import com.codzunk.goalkik.ui.adapters.FixAdapter;
import com.codzunk.goalkik.ui.view.ArrayAdapterSearchView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FullFixtureActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener {

    private List<FixtureModel> modelList;
    private SearchView.SearchAutoComplete searchAutoComplete;
    private RecyclerView fixView;
    private SwipeRefreshLayout swipeLayout;
    private SearchView searchView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_fixture);

        //set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        PrefDataManger prefManger = ((GoalApp) getApplicationContext()).getDataManger();
        List<Fixture> fixtureList = prefManger.getFixture();

        fixView = findViewById(R.id.fixView);
        swipeLayout = findViewById(R.id.swipeLayout);
        swipeLayout.setOnRefreshListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        fixView.setLayoutManager(layoutManager);


        modelList = new ArrayList<>(2);

        if (fixtureList != null){
            int size = fixtureList.size();
            for (int i=0; i<size; i++){
                try {
                    String date = convertUTCFormat(fixtureList.get(i).getDate()).split("\\s+")[0];
                    String time = convertUTCFormat(fixtureList.get(i).getDate()).split("\\s+")[1]
                            + convertUTCFormat(fixtureList.get(i).getDate()).split("\\s+")[2];

                    FixtureModel fixture = new FixtureModel();
                    fixture.setCount(i+1);
                    fixture.setHomeTeamName(fixtureList.get(i).getHomeTeamName());
                    fixture.setAwayTeamName(fixtureList.get(i).getAwayTeamName());
                    fixture.setDate(date);
                    fixture.setTime(time);
                    fixture.setStatus(fixtureList.get(i).getStatus());
                    fixture.setToday(2);
                    if (fixtureList.get(i).getResult().getGoalsHomeTeam() != null){
                        fixture.setHome("" + fixtureList.get(i).getResult().getGoalsHomeTeam().toString().charAt(0));
                        fixture.setAway("" + fixtureList.get(i).getResult().getGoalsAwayTeam().toString().charAt(0));
                    }
                    modelList.add(fixture);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            fixView.setAdapter(new FixAdapter(modelList, this));
        }
    }

    private static String convertUTCFormat(String dateStr) throws ParseException {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        sourceFormat.setTimeZone(utc);
        Date convertedDate = sourceFormat.parse(dateStr);

        Date date = destFormat.parse(destFormat.format(convertedDate));
        destFormat.setTimeZone(TimeZone.getDefault());

        String[] dateTime = destFormat.format(date).split("\\s+");

        SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm", Locale.getDefault());
        SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        Date _24HourDt = _24HourSDF.parse(dateTime[1]);

        return dateTime[0] + " " + _12HourSDF.format(_24HourDt);
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fixture_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView ) menu.findItem(R.id.menu_search).getActionView();

        searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        String[] teamList = getResources().getStringArray(R.array._team);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, teamList);
        searchAutoComplete.setAdapter(adapter);

        if (searchManager != null) {
            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(this);
        }

        searchAutoComplete.setOnItemClickListener(onItemClickListener);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        filterList(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            String searchString = (String) parent.getItemAtPosition(position);
            searchAutoComplete.setText(searchString);
            filterList(searchString);
        }
    };

    private void filterList(String item){
        List<FixtureModel> models = new ArrayList<>(2);
        for (FixtureModel model : modelList){
            if (model.getHomeTeamName().equals(item) || model.getAwayTeamName().equals(item)){
                models.add(model);
            }
        }
        fixView.setAdapter(new FixAdapter(models, this));
    }

    @Override
    public void onRefresh() {
        if (swipeLayout.isRefreshing()){
            fixView.setAdapter(new FixAdapter(modelList, this));
            swipeLayout.setRefreshing(false);
        }
    }

    @Override
    public void onBackPressed() {
        if (searchView.isIconified()){
            fixView.setAdapter(new FixAdapter(modelList, this));
            searchView.setIconified(false);
        } else {
            super.onBackPressed();
        }
    }
}
