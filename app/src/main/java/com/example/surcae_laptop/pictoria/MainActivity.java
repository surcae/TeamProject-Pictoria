package com.example.surcae_laptop.pictoria;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    /*
    여기 들어왔으면 이제 FirebaseUser 데이터에 사용자 데이터가 들어와있고
    이 사용자를 이용해서 들고 볶고 썰고 할 수 있다.
     */

    //Context instance, setter, getter 생성

    private static Context context;
    RecyclerView recyclerView;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MainActivity.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.setContext(this);

        recyclerView =  (RecyclerView) findViewById(R.id.r_main);
        Bitmap[] bitmaps=setBitmaps();
        //어뎁터 설정
        recyclerView.setAdapter(new GridAdapter(bitmaps));
        //우선 col2개로 설정
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);


    }

    //이미지 가져올 메소드 설정
    private Bitmap[] setBitmaps() {
    }

    @Override public void onBackPressed() { // 백버튼 막음
        //super.onBackPressed();
    }


}
