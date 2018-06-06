package com.example.surcae_laptop.pictoria;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    /*
    여기 들어왔으면 이제 FirebaseUser 데이터에 사용자 데이터가 들어와있고
    이 사용자를 이용해서 들고 볶고 썰고 할 수 있다.
     */

    //main에서 구현할 버튼들 셋팅
    private Button settting;
    private Button home;
    private Button search;
    private Button favorite;
    private Button list;

    //Context instance, setter, getter 생성

    private static Context context;
    RecyclerView recyclerView;


    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MainActivity.context = context;
    }



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.setContext(this);

        settting = (Button) findViewById(R.id.button_setting);
        home = (Button) findViewById(R.id.button_home);
        favorite = (Button) findViewById(R.id.button_favorite);
        search = (Button) findViewById(R.id.button_search);
        list = (Button) findViewById(R.id.button_list);


        recyclerView =  (RecyclerView) findViewById(R.id.r_main);
        Bitmap[] bitmaps=setBitmaps();
        //어뎁터 설정
        recyclerView.setAdapter(new GridAdapter(bitmaps));
        //우선 col2개로 설정
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);


        settting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //settting 버튼 클릭시 실행
                //Navigation activity로 구현예정
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Login activity로 돌아간다
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //구현 or 삭제 예정
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //main 검색 기능 구현할 부분
            }
        });


        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //정렬 기능
                //구현 or 삭제 예정
            }
        });

    }

    //이미지 가져올 메소드 설정
    private Bitmap[] setBitmaps() {
        //sample bitmap 설정
        Bitmap[] bitmaps = new Bitmap[7];
        bitmaps[0]= BitmapFactory.decodeResource(getResources(),R.drawable.night);
        bitmaps[1]= BitmapFactory.decodeResource(getResources(),R.drawable.windmill);
        bitmaps[2]= BitmapFactory.decodeResource(getResources(),R.drawable.sun);
        bitmaps[3]= BitmapFactory.decodeResource(getResources(),R.drawable.night);
        bitmaps[4]= BitmapFactory.decodeResource(getResources(),R.drawable.sun);
        bitmaps[5]= BitmapFactory.decodeResource(getResources(),R.drawable.windmill);
        bitmaps[6]= BitmapFactory.decodeResource(getResources(),R.drawable.morningglow);
        return bitmaps;
    }

    @Override public void onBackPressed() { // 백버튼 막음
        //super.onBackPressed();
    }






}
