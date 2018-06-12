package com.example.surcae_laptop.pictoria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.api.services.customsearch.model.Context;

/* Setting 구현 예정 */


public class SettingFragment extends Fragment {

    public static SettingFragment newInstance(){
        return new SettingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final String[] values = new String[]{
                "로그아웃","지원","고객센터","문제 신고","소개","광고","개인정보처리방침","서비스 약관",
        };

        View view = inflater.inflate(R.layout.fragment_setting,container,false) ;
        final ListView listview = (ListView) view.findViewById(R.id.setting_list) ;

        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(
                getActivity(),android.R.layout.simple_list_item_1,values);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    //로그아웃하면 로그인화면으로 돌아간다
                    case 0:
                        Intent intent = new Intent(MainActivity.getContext(), LoginActivity.class);
                        startActivity(intent);
                }

            }
        });

        return view;
    }
}
