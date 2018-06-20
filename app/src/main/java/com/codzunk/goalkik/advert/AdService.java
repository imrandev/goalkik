package com.codzunk.goalkik.advert;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.codzunk.goalkik.constant.Config;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AdService extends AdListener {
    private AdView mAdView;
    private TextView ads;
    private Context context;
    private InterstitialAd interstitialAd;
    private boolean isAdView;

    public AdService(AdView adView, Context context, TextView ads) {
        this.mAdView = adView;
        this.ads = ads;
        this.context = context;
        this.isAdView = true;
    }

    public AdService(InterstitialAd interstitialAd, Context context) {
        this.interstitialAd = interstitialAd;
        this.context = context;
        this.isAdView = false;
    }

    public void initBanner(){
        MobileAds.initialize(context, Config.ADMOB_APP_ID);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(this);
    }

    public void initInterstitialAd(){
        MobileAds.initialize(context, Config.ADMOB_APP_ID);
        interstitialAd.setAdUnitId(Config.ADMOB_INTERESTITIAL_ID);
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(this);
    }

    @Override
    public void onAdLoaded() {
        if (isAdView){
            mAdView.setVisibility(View.VISIBLE);
            ads.setVisibility(View.VISIBLE);
        } else {
            if (interstitialAd.isLoaded()) {
                interstitialAd.show();
            }
        }
    }

    @Override
    public void onAdFailedToLoad(int errorCode) {
        if (isAdView){
            mAdView.setVisibility(View.GONE);
            ads.setVisibility(View.GONE);
        }
        Log.d("AdsError", getErrorReason(errorCode));
    }

    @Override
    public void onAdClosed() {
        if (isAdView){
            mAdView.setVisibility(View.GONE);
            ads.setVisibility(View.GONE);
        }
    }

    private String getErrorReason(int errorCode) {

        String errorReason = "";
        switch (errorCode) {
            case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                errorReason = "Internal Error";
                break;
            case AdRequest.ERROR_CODE_INVALID_REQUEST:
                errorReason = "Invalid Request";
                break;
            case AdRequest.ERROR_CODE_NETWORK_ERROR:
                errorReason = "Network Error";
                break;
            case AdRequest.ERROR_CODE_NO_FILL:
                errorReason = "No Fill";
                break;
        }
        return errorReason;
    }
}
