package com.example.surcae_laptop.pictoria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class LoadingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* 메뉴액티비티를 실행하고 로딩화면을 죽인다.*/
                Intent mainIntent = new Intent(LoadingActivity.this,LoginActivity.class);
                LoadingActivity.this.startActivity(mainIntent);
                LoadingActivity.this.finish();
            }
        }, 1000);
    }
}
