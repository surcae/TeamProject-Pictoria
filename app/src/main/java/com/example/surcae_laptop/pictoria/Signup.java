package com.example.surcae_laptop.pictoria;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class Signup extends Activity{
    // 싸인업 프레그먼트

    private EditText email;
    private EditText password, password2;
    private Button signUpButton;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        email = (EditText)findViewById(R.id.email_signup);
        password = (EditText)findViewById(R.id.password_signup);
        password2 = (EditText)findViewById(R.id.password2_signup);
        signUpButton = (Button)findViewById(R.id.signup);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    public void signUp(){
        // 빈칸 검사
        if(email.getText().length() == 0 || password.getText().length() == 0 || password2.getText().length() == 0){
            Toast.makeText(this, "비어있는 칸이 있습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 패스워드 불일치 검사
        if(!password.getText().toString().equals(password2.getText().toString())){
            Toast.makeText(this.getApplicationContext(), "비밀번호를 똑같이 써주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: 여기서부터 파이어베이스에 권한 생성만 하고 다시 백버튼으로 돌아가게 하거나 처음 부른 액티비티로 바꿈
        FireBaseClass.getInstance().getmAuth().createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(getApplicationContext(), "계정 생성 완료",
                                    Toast.LENGTH_SHORT).show();
                            //FirebaseUser user = FireBaseClass.getInstance().getmAuth().getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "계정 생성 실패",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }
}
