package com.codzunk.goalkik.advertise;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AdService extends AdListener {
    private AdView mAdView;
    private TextView ads;
    private Context context;

    public AdService(AdView adView, Context context, TextView ads) {
        this.mAdView = adView;
        this.ads = ads;
        this.context = context;
    }

    public void init(){
        MobileAds.initialize(context, "ca-app-pub-9551927371844997~1007897942");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(this);
    }

    @Override
    public void onAdLoaded() {
        mAdView.setVisibility(View.VISIBLE);
        ads.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAdFailedToLoad(int errorCode) {
        mAdView.setVisibility(View.GONE);
        ads.setVisibility(View.GONE);
    }

    @Override
    public void onAdClosed() {
        mAdView.setVisibility(View.GONE);
        ads.setVisibility(View.GONE);
    }
}
