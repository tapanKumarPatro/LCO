package com.example.tapan.demolco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


/**
 *
 *
 * APP ID:    ca-app-pub-2337868013684078~5908391908
 * unit ID:    ca-app-pub-2337868013684078/8745597792
 *
 * */


    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.btn_start)
    TextView btnStart;


    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        MobileAds.initialize(this, ""+getString(R.string.app_id));

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Util.isNetworkConnected(MainActivity.this)){
                    startActivity(new Intent(MainActivity.this, QuestionListActivity.class));
                }else{
                    Toast.makeText(MainActivity.this, "Please connect to a valid network.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
