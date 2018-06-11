package com.example.surcae_laptop.pictoria;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    /*
    여기 들어왔으면 이제 FirebaseUser 데이터에 사용자 데이터가 들어와있고
    이 사용자를 이용해서 들고 볶고 썰고 할 수 있다.
     */
    //Context instance, setter, getter 생성
    // 구글 검색용 엔진 ID
    private final String googleSearchEngineID = "001547157015624447273:cgd3p_evf_o";
    // API 이용하기 위한 키
    private final String APIKey = "AIzaSyDr2FU22oAzmXgljzfpW5wn1khjWPOsjf4";
    private static Context context;
    private ImageSearchEngine imageSearchEngine;
    RecyclerView recyclerView;
    //BottomNavigationview 작업 준비
    BottomNavigationView bottomNavigationView;
    MaterialSearchView materialSearchView;
    Toolbar toolbar;

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

        // 이미지 서치 인스턴스 생성
        imageSearchEngine = new ImageSearchEngine();

        recyclerView =  (RecyclerView) findViewById(R.id.r_main);
        Bitmap[] bitmaps=setBitmaps();

        //우선 col2개로 설정
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        //어뎁터 설정
        recyclerView.setAdapter(new GridAdapter(bitmaps));

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Profile:
                        replaceFragment(ProfileFragment.newInstance());
                        return true;
                    //mainactivty로 돌아간다
                    case R.id.home:
                        Intent intent = new Intent();
                        intent.setClass(getContext(),MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.setting:
                        replaceFragment(SettingFragment.newInstance());
                        return true;
                }
                return false;
            }

            //Fragment instance 받아서 replace
            private void replaceFragment(Fragment fragment) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.container,fragment).commit();
            }

        });

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        materialSearchView=(MaterialSearchView)findViewById(R.id.search_view);
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                final String searchString = query;
                Log.d("Search Engine", "Searching for : " + searchString);

                // 서치 Submit시 키보드 숨김
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                final String searchStringNoSpaces = searchString.replace(" ", "+");
                String urlString = "https://www.googleapis.com/customsearch/v1?q=" + searchStringNoSpaces + "&key=" + APIKey + "&cx=" + googleSearchEngineID + "&alt=json";
                URL url = null;

                try {
                    url = new URL(urlString); // URL 파싱
                } catch (MalformedURLException e) {
                    Log.e("MainActivity", "ERROR converting String to URL " + e.toString());
                }
                Log.d("MainActivity", "Url = "+  urlString);

                imageSearchEngine = new ImageSearchEngine();
                imageSearchEngine.execute(url);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // filter recycler view when text is changed
                return true;
            }
        });

        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });

        materialSearchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        
    }

    //이미지 가져올 메소드 설정
    private Bitmap[] setBitmaps() {
        //sample bitmap 설정
        Bitmap[] bitmaps = new Bitmap[10];
        bitmaps[0] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_00);
        bitmaps[1] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_01);
        bitmaps[2] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_02);
        bitmaps[3] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_03);
        bitmaps[4] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_04);
        bitmaps[5] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_05);
        bitmaps[6] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_06);
        bitmaps[7] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_07);
        bitmaps[8] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_00);
        bitmaps[9] = BitmapFactory.decodeResource(getResources(), R.drawable.sample_01);
        return bitmaps;
    }

    @Override public void onBackPressed(){ // 백버튼 막음
        //super.onBackPressed();
    }

    //만든 menu 가져오기
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        materialSearchView.setMenuItem(item);

        return true;
    }



}
