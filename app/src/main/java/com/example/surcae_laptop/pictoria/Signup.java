package com.example.surcae_laptop.pictoria;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends FragmentActivity {
    // 싸인업 프레그먼트 액티비티

    private EditText email;
    private EditText password, password2;
    private Button signUpButton;

    @Override
    protected void onPause(){
        super.onPause();
        // 액티비티 도중 멈췄을때...

    }

    @Override
    protected void onResume(){
        super.onResume();
        // 다시 살렸을때...

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        signUpButton = (Button)findViewById(R.id.signup);
        email = (EditText)findViewById(R.id.email_signup);
        password = (EditText)findViewById(R.id.password_signup);
        password2 = (EditText)findViewById(R.id.password2_signup);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    public void signUp(){
        // 빈칸 검사
        if(email.getText().toString() == "".toString() || password.getText().toString() == "".toString() || password2.getText().toString() == "".toString()){
            Toast.makeText(this, "비어있는 칸이 있습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 패스워드 불일치 검사
        if(!password.getText().equals(password2.getText())){
            Toast.makeText(this, "비밀번호를 똑같이 써주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: 여기서부터 파이어베이스에 권한 생성만 하고 다시 백버튼으로 돌아가게 하거나 처음 부른 액티비티로 바꿈
    }
}
