package com.example.surcae_laptop.pictoria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView)findViewById(R.id.Grid_view);

        gridView.setAdapter(new ImageAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //나중에 구현할 부분
                //클릭시 이미지 팝업?
                /*
                Top, BottomBar를 통해서 프래그먼트를 나누고 각 프래그먼트당 작동할 .java 파일이 필요함
                 */

            }
        });

    }





}
