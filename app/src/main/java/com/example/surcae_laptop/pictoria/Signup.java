package com.example.surcae_laptop.pictoria;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends android.app.Fragment{
    // 싸인업 프레그먼트

    private EditText email;
    private EditText  password, password2;
    private Button signUpButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup, null);
        signUpButton = (Button)view.findViewById(R.id.signup);
        email = (EditText )view.findViewById(R.id.email_signup);
        password = (EditText )view.findViewById(R.id.password_signup);
        password2 = (EditText )view.findViewById(R.id.password2_signup);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void signUp(){
        // 빈칸 검사
        if(email.getText().toString() == "".toString() || password.getText().toString() == "".toString() || password2.getText().toString() == "".toString()){
            Toast.makeText(getActivity().getApplicationContext(), "비어있는 칸이 있습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 패스워드 불일치 검사
        if(!password.getText().equals(password2.getText())){
            Toast.makeText(getActivity().getApplicationContext(), "비밀번호를 똑같이 써주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: 여기서부터 파이어베이스에 권한 생성만 하고 다시 백버튼으로 돌아가게 하거나 처음 부른 액티비티로 바꿈

    }
}
