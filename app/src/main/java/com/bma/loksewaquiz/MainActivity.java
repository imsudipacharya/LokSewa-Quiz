package com.bma.loksewaquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {
    AppCompatButton gk1btn,gk2btn,gk3btn,gk4btn,gk5btn,gk6btn,gk7btn,gk8btn,gk9btn,gk10btn;
    private InterstitialAd mInterstitialAd, mInterstitialAd1, mInterstitialAd2, mInterstitialAd3, mInterstitialAd4,mInterstitialAd5;
     private String interstitalback = "755537311976362_755537525309674";
    final private String interstital1 = "755537311976362_755537838642976";
    final private String interstital2 = "755537311976362_755537911976302";
    final private String interstital3 = "755537311976362_755538011976292";
    final private String interstital4 = "755537311976362_755538095309617";
    final private String interstital5 = "755537311976362_755538188642941";
    final private String interstital6 = "755537311976362_755538281976265";
    final private String interstital7 = "755537311976362_755538321976261";
    final private String interstital8 = "755537311976362_755538321976261";
    final private String interstital9 = "755537311976362_755538458642914";
    final private String interstital10 = "755537311976362_755538525309574";
    final private String interstital11 = "755537311976362_755538605309566";
    final private String interstital12 = "755537311976362_755538678642892";
    final private String interstital13 = "755537311976362_755538755309551";
    final private String interstital14 = "755537311976362_755538891976204";
    final private String interstital15 = "755537311976362_755538971976196";
    final private String interstital16 = "755537311976362_755539051976188";
    final private String interstital17 = "755537311976362_755539118642848";
    final private String interstital18 = "755537311976362_755539268642833";
    final private String interstital19 = "755537311976362_755539455309481";
    final private String interstital20 = "755537311976362_755539555309471";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        gk1btn = findViewById(R.id.gk1btn);
        gk2btn = findViewById(R.id.gk2btn);
        gk3btn = findViewById(R.id.gk3btn);
        gk4btn = findViewById(R.id.gk4btn);
        gk5btn = findViewById(R.id.gk5btn);
        gk6btn = findViewById(R.id.gk6btn);
        gk7btn = findViewById(R.id.gk7btn);
        gk8btn = findViewById(R.id.gk8btn);
        gk9btn = findViewById(R.id.gk9btn);
        gk10btn = findViewById(R.id.gk10btn);

        String name1 = gk1btn.getText().toString();
        String name2 = gk2btn.getText().toString();
        String name3 = gk3btn.getText().toString();
        String name4 = gk4btn.getText().toString();
        String name5 = gk5btn.getText().toString();
        String name6 = gk6btn.getText().toString();
        String name7 = gk7btn.getText().toString();
        String name8 = gk8btn.getText().toString();
        String name9 = gk9btn.getText().toString();
        String name10 = gk10btn.getText().toString();

        AudienceNetworkAds.initialize(this);
        mInterstitialAd = new InterstitialAd(this, interstitalback);
        mInterstitialAd.loadAd();

        AudienceNetworkAds.initialize(this);
        mInterstitialAd1 = new InterstitialAd(this, interstital1);
        mInterstitialAd1.loadAd();


        AudienceNetworkAds.initialize(this);
        mInterstitialAd2 = new InterstitialAd(this, interstital2);
        mInterstitialAd2.loadAd();


        AudienceNetworkAds.initialize(this);
        mInterstitialAd3 = new InterstitialAd(this, interstital3);
        mInterstitialAd3.loadAd();


        AudienceNetworkAds.initialize(this);
        mInterstitialAd4 = new InterstitialAd(this, interstital4);
        mInterstitialAd4.loadAd();

        AudienceNetworkAds.initialize(this);
        mInterstitialAd5 = new InterstitialAd(this, interstital5);
        mInterstitialAd5.loadAd();


        gk1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd1.isAdLoaded()) {

                    mInterstitialAd1.show();
                }
                Intent intent = new Intent(MainActivity.this, SetActivity.class);
                intent.putExtra("fromname", name1);
                intent.putExtra("ads1", interstital11);
                intent.putExtra("ads2", interstital12);
                intent.putExtra("ads3", interstital13);
                intent.putExtra("ads4", interstital14);
                intent.putExtra("ads5", interstital15);
                startActivity(intent);
                finish();

            }
        });

        gk2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd2.isAdLoaded()) {

                    mInterstitialAd2.show();
                }
                Intent intent = new Intent(MainActivity.this, SetActivity.class);
                intent.putExtra("fromname", name2);
                intent.putExtra("ads1", interstital6);
                intent.putExtra("ads2", interstital7);
                intent.putExtra("ads3", interstital8);
                intent.putExtra("ads4", interstital9);
                intent.putExtra("ads5", interstital10);
                startActivity(intent);
finish();
            }
        });

        gk3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd3.isAdLoaded()) {

                    mInterstitialAd3.show();
                }
                Intent intent = new Intent(MainActivity.this, SetActivity.class);
                intent.putExtra("fromname", name3);
                intent.putExtra("ads1", interstital10);
                intent.putExtra("ads2", interstital15);
                intent.putExtra("ads3", interstital19);
                intent.putExtra("ads4", interstital14);
                intent.putExtra("ads5", interstital11);
                startActivity(intent);
finish();
            }
        });

        gk4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd4.isAdLoaded()) {

                    mInterstitialAd4.show();
                }
                Intent intent = new Intent(MainActivity.this, SetActivity.class);
                intent.putExtra("fromname", name4);
                intent.putExtra("ads1", interstital18);
                intent.putExtra("ads2", interstital17);
                intent.putExtra("ads3", interstital16);
                intent.putExtra("ads4", interstital13);
                intent.putExtra("ads5", interstital12);
                startActivity(intent);
finish();
            }
        });

        gk5btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd5.isAdLoaded()) {

                    mInterstitialAd5.show();
                }
                Intent intent = new Intent(MainActivity.this, SetActivity.class);
                intent.putExtra("fromname", name5);
                intent.putExtra("ads1", interstital15);
                intent.putExtra("ads2", interstital16);
                intent.putExtra("ads3", interstital17);
                intent.putExtra("ads4", interstital18);
                intent.putExtra("ads5", interstital19);
                startActivity(intent);
finish();
            }
        });

        gk6btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd1.isAdLoaded()) {

                    mInterstitialAd1.show();
                }
                Intent intent = new Intent(MainActivity.this, SetActivity.class);
                intent.putExtra("fromname", name6);
                intent.putExtra("ads1", interstital20);
                intent.putExtra("ads2", interstital2);
                intent.putExtra("ads3", interstital3);
                intent.putExtra("ads4", interstital4);
                intent.putExtra("ads5", interstital5);
                startActivity(intent);
                finish();
            }
        });

        gk7btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd2.isAdLoaded()) {

                    mInterstitialAd2.show();
                }
                Intent intent = new Intent(MainActivity.this, SetActivity.class);
                intent.putExtra("fromname", name7);
                intent.putExtra("ads1", interstital6);
                intent.putExtra("ads2", interstital16);
                intent.putExtra("ads3", interstital1);
                intent.putExtra("ads4", interstital11);
                intent.putExtra("ads5", interstital20);
                startActivity(intent);
                finish();
            }
        });

        gk8btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd3.isAdLoaded()) {

                    mInterstitialAd3.show();
                }
                Intent intent = new Intent(MainActivity.this, SetActivity.class);
                intent.putExtra("fromname", name8);
                intent.putExtra("ads1", interstital6);
                intent.putExtra("ads2", interstital11);
                intent.putExtra("ads3", interstital16);
                intent.putExtra("ads4", interstital7);
                intent.putExtra("ads5", interstital12);
                startActivity(intent);
                finish();
            }
        });

        gk9btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd4.isAdLoaded()) {

                    mInterstitialAd4.show();
                }
                Intent intent = new Intent(MainActivity.this, SetActivity.class);
                intent.putExtra("fromname", name9);
                intent.putExtra("ads1", interstital17);
                intent.putExtra("ads2", interstital8);
                intent.putExtra("ads3", interstital13);
                intent.putExtra("ads4", interstital18);
                intent.putExtra("ads5", interstital9);
                startActivity(intent);
                finish();
            }
        });

        gk10btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd5.isAdLoaded()) {

                    mInterstitialAd5.show();
                }
                Intent intent = new Intent(MainActivity.this, SetActivity.class);
                intent.putExtra("fromname", name10);
                intent.putExtra("ads1", interstital14);
                intent.putExtra("ads2", interstital19);
                intent.putExtra("ads3", interstital10);
                intent.putExtra("ads4", interstital15);
                intent.putExtra("ads5", interstital20);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isAdLoaded()) {

            mInterstitialAd.show();
        } else {

            AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
            ab.setTitle("DO YOU WANT TO CLOSE LOKSEWA Quiz ?");
            ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //if you want to kill app . from other then your main avtivity.(Launcher)
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);

                 finishAffinity();
                }
            });
            ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            ab.show();

        }
    }
}