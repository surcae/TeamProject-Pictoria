package com.example.surcae_laptop.pictoria;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* Setting 구현 예정 */


public class SettingFragment extends Fragment {

    public static SettingFragment newInstance(){
        return new SettingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //ui 만들어서 view 리턴
        return inflater.inflate(R.layout.fragment_setting,container,false);
    }
}
