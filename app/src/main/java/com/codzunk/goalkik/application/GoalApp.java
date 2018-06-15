package com.codzunk.goalkik.application;

import android.app.Application;

import com.codzunk.goalkik.R;
import com.codzunk.goalkik.constant.Config;
import com.codzunk.goalkik.prefs.SharedPrefManager;
import com.codzunk.goalkik.prefs.data.PrefDataManger;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.WeakReference;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class GoalApp extends Application {
    private PrefDataManger dataManger;

    @Override
    public void onCreate() {
        super.onCreate();

        Config.context = new WeakReference<>(getApplicationContext());
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        databaseRef.keepSynced(true);

        SharedPrefManager prefManager = new SharedPrefManager(getApplicationContext());
        dataManger = new PrefDataManger(prefManager);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Dusha.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public PrefDataManger getDataManger() {
        return dataManger;
    }
}
