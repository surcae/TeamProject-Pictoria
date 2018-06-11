package com.example.surcae_laptop.pictoria;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* Favorite 구현 예정 */

public class ProfileFragment extends Fragment {

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        //ui 만들어서 view 리턴
        return inflater.inflate(R.layout.fragment_profile_expanded,container,false);
    }
}
