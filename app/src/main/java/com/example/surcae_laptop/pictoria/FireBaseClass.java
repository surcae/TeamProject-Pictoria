package com.example.surcae_laptop.pictoria;

import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class FireBaseClass {
     /*
    파이어베이스 연동 시작과
    파이어베이스에서 가져오는 모든 모듈과 세션 관리, 파이어베이스와 관련된 모든 메소드, 정보들은 이 클래스에서 관리한다.
    파이어베이스는 여러 java 클래스에서 쓸 일이 많기 때문에 싱글톤으로 만든다.
     */

    // 공용
    private FirebaseAuth mAuth;

    // 로그인용
    private GoogleApiClient mGoogleApiClient; // 구글 로그인용
    private LoginActivity loginActivity;
    // 아래는 싱글톤용
    private static final FireBaseClass ourInstance = new FireBaseClass();
    public static FireBaseClass getInstance() {
        return ourInstance;
    }

    private FireBaseClass() { // 생성자
        //Init 메소드에서 따로 초기화함
    }

    public void Initializer() {
        // 기존 로그인 정보가 만약에 있다면 상쇄
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
    }
    public void SetGoogleLoginAPIWithFirebase(){

        // 구글 SignIn 변수
        // IdToken은 API OAuth 2.0 토큰을 넣으면 됨. FireBase에 가서 Key값 가져오길 바람
        // https://console.developers.google.com/apis/credentials?project=pictoria-737f0
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder
                (GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1069317858181-8a149i0vm241u4vrnssa5180g2lhdbtn.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(loginActivity)
                .enableAutoManage(loginActivity /* FragmentActivity */,
                        new GoogleApiClient.OnConnectionFailedListener() {
                            @Override
                            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                            }
                        } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }
    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    public void setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        this.mGoogleApiClient = mGoogleApiClient;
    }

    public void setLoginActivity(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }
}
