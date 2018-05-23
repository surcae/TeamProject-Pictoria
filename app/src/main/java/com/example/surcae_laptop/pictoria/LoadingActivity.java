package com.example.surcae_laptop.pictoria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

public class LoadingActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ProgressBar progressBar;
    private boolean handler;
    private CheckInternet checkInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        progressBar = (ProgressBar)findViewById(R.id.progress);

        if(!isOnline()) {
            System.out.println("인터넷 연결 확인 요망");
            return;
        }
        handler = new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(LoadingActivity.this,LoginActivity.class);
                LoadingActivity.this.startActivity(mainIntent);
                LoadingActivity.this.finish();
            }
        }, 5000);
    }

    public static boolean isOnline() {
        CheckInternet cc= new CheckInternet("http://clients3.google.com/generate_204");
        cc.start();
        try{
            cc.join();
            return cc.isSuccess();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
