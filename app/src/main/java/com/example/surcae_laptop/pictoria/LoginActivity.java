package com.example.surcae_laptop.pictoria;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.auth.api.signin.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends FragmentActivity {
    private static final String TAG = "LoginActivity";
    FirebaseAuth mAuth;
    EditText email;
    EditText password;
    Button button, button2;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        FirebaseApp.initializeApp(this);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        //mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Login())
                {
                    // 로그인 성공 및 다음 엑티비티로 이동, 이동할 때 현재 할당된 권한, 계정 정보를 가지고 처음 초기화 정보와 함께 인텐트하여 넘겨줌
                    //Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    //startActivityForResult(signInIntent, RC_SIGN_IN);
                    Intent mainIntent = new Intent(LoginActivity.this,MainActivity.class);
                    LoginActivity.this.startActivity(mainIntent);
                    LoginActivity.this.finish();
                }
                else{
                    // 로그인 실패 팝업
                    return;
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //계정 생성
            }
        });
    }
    private boolean Login(){
        // 로그인
        // 파이어베이스에 접근해야함.
        return true;
    }
    private void CreateUser(){
        // 빈 공간 검사 (하나라도 비었으면 안 만들어짐)
        if(email.getText().equals(""))
            return;

        if(password.getText().equals(""))
            return;


        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            if (result.isSuccess()) {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = result.getSignInAccount();
//                firebaseAuthWithGoogle(account);
//            } else {
//                // Google Sign In failed, update UI appropriately
//                // ...
//            }
//        }
    }

}
