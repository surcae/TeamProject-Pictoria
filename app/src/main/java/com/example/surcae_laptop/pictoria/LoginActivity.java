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

import static com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Prompt.SIGN_IN;

public class LoginActivity extends FragmentActivity {
    private static final String TAG = "LoginActivity";
    private EditText email;
    private EditText password;
    private Button loginbutton, createbutton;
    private SignInButton GoogleLoginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        FireBaseClass.getInstance().setLoginActivity(this);
        FirebaseApp.initializeApp(this);
        FireBaseClass.getInstance().SetGoogleLoginAPIWithFirebase();

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        loginbutton = (Button)findViewById(R.id.button);     // 로그인 (일반 로그인)
        GoogleLoginbutton = (SignInButton)findViewById(R.id.signin);
        createbutton = (Button)findViewById(R.id.button2);   // 회원가입 프래그먼트 ㄱㄱ

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 성공 및 다음 엑티비티로 이동, 이동할 때 현재 할당된 권한, 계정 정보를 가지고 처음 초기화 정보와 함께 인텐트하여 넘겨줌
                //Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                //startActivityForResult(signInIntent, RC_SIGN_IN);
                //Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(FireBaseClass.getInstance().getmGoogleApiClient());
                //startActivityForResult(signInIntent, SIGN_IN);
            }
        });

        createbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //계정 생성
                CreateUser();
            }
        });

        GoogleLoginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(FireBaseClass.getInstance().getmGoogleApiClient());
                startActivityForResult(signInIntent, SIGN_IN);
            }
        });
    }

    private void CreateUser(){
        // 빈 공간 검사 (하나라도 비었으면 안 만들어짐)
        if(email.getText().equals(""))
            return;

        if(password.getText().equals(""))
            return;

        // 이 부분은 따로 구현
        // 프래그먼트 변경

    }

    // 위의 로그인 버튼을 누르면 결과가 이리로 들어온다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result != null) {
                if (result.isSuccess()) {

                    // 로그인 성공 했을때
                    GoogleSignInAccount acct = result.getSignInAccount();

                    String personName = acct.getDisplayName();
                    String personEmail = acct.getEmail();
                    String personId = acct.getId();
                    String tokenKey = acct.getServerAuthCode();

                    // 연결용 API 연결 해제
                    FireBaseClass.getInstance().getmGoogleApiClient().disconnect();

                    Log.e("GoogleLogin", "personName=" + personName);
                    Log.e("GoogleLogin", "personEmail=" + personEmail);
                    Log.e("GoogleLogin", "personId=" + personId);
                    Log.e("GoogleLogin", "tokenKey=" + tokenKey);

                    // 여기 아래에서 결과를 Intent에 넘겨주고
                    // 메인 엑티비티로 이동한다.
                    // TODO: 메인 엑티비티로 이동 (Intent로 데이터와 함께)

                } else {
                    Log.e("GoogleLogin", "login fail cause=" + result.getStatus().getStatusMessage());
                    // 로그인 실패 했을때
                    Toast.makeText(LoginActivity.this, "로그인 실패",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
        else {
            // 다른 리퀘스트 코드일 때 이리로 온다.

        }
    }
}
