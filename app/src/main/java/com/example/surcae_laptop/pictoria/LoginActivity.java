package com.example.surcae_laptop.pictoria;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.annotations.Nullable;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import static com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Prompt.SIGN_IN;

public class LoginActivity extends FragmentActivity {
    private static final String TAG = "LoginActivity";
    private EditText  email;
    private EditText password;
    private Button loginbutton, createbutton;
    private SignInButton GoogleLoginbutton;

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
    public void onStart() {
        super.onStart();
        // 활동을 초기화할 때 사용자가 현재 로그인되어 있는지 확인합니다.
        try {
            FirebaseUser currentUser = FireBaseClass.getInstance().getmAuth().getCurrentUser();
        } catch (NullPointerException e) {
            Log.e("비로그인 상태", "비로그인 상태");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        FireBaseClass.getInstance().setLoginActivity(this);
        FirebaseApp.initializeApp(this);
        FireBaseClass.getInstance().SetGoogleLoginAPIWithFirebase();

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        loginbutton = (Button) findViewById(R.id.button);     // 로그인 (일반 로그인)
        GoogleLoginbutton = (SignInButton) findViewById(R.id.signin);
        createbutton = (Button) findViewById(R.id.createButton);   // 회원가입 프래그먼트 ㄱㄱ

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 성공 및 다음 엑티비티로 이동, 이동할 때 현재 할당된 권한, 계정 정보를 가지고 처음 초기화 정보와 함께 인텐트하여 넘겨줌
                //Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                //startActivityForResult(signInIntent, RC_SIGN_IN);
                //Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(FireBaseClass.getInstance().getmGoogleApiClient());
                //startActivityForResult(signInIntent, SIGN_IN);
                // 빈 공간 검사 (하나라도 비었으면 안 만들어짐)
                if (email.getText().equals(""))
                    return;

                if (password.getText().equals(""))
                    return;
            }
        });

        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //계정 생성
                CreateUser();
            }
        });

        GoogleLoginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(FireBaseClass.getInstance().getmGoogleApiClient());
                startActivityForResult(signInIntent, SIGN_IN);

            }
        });
    }

    private void CreateUser() {

        // 이 부분은 따로 구현
        // 프래그먼트 변경 (SignUp)
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.loginlayout, new Signup());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public void signOut() {
        FireBaseClass.getInstance().getmGoogleApiClient().connect();
        FireBaseClass.getInstance().getmGoogleApiClient().registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                FireBaseClass.getInstance().getmAuth().signOut();
                if (FireBaseClass.getInstance().getmGoogleApiClient().isConnected()) {
                    Auth.GoogleSignInApi.signOut(FireBaseClass.getInstance().getmGoogleApiClient()).setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            if (status.isSuccess()) {
                                Log.v("알림", "로그아웃 성공");
                                setResult(1);
                            } else {
                                setResult(0);
                            }
                        }
                    });
                }
            }

            @Override
            public void onConnectionSuspended(int i) {
                Log.v("알림", "Google API Client Connection Suspended");
                setResult(-1);
            }
        });
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
                    AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
                    FireBaseClass.getInstance().getmAuth().signInWithCredential(credential)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.v("알림", "ONCOMPLETE");
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Log.v("알림", "!task.isSuccessful()");
                                        Toast.makeText(LoginActivity.this, "인증에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                        return;
                                    } else {
                                        Log.v("알림", "task.isSuccessful()");
                                        FirebaseUser user = FireBaseClass.getInstance().getmAuth().getCurrentUser();
                                        Toast.makeText(LoginActivity.this, "FireBase 아이디 생성이 완료 되었습니다", Toast.LENGTH_SHORT).show();


                                        // 여기 아래에서 결과를 Intent에 넘겨주고
                                        // 메인 엑티비티로 이동한다.
                                        // TODO: 메인 엑티비티로 이동
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });

                    // 아마 성공해서 계정 연동도 인증되면 여기는 안 들어올꺼임
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

                } else {
                    Log.e("GoogleLogin", "login fail cause=" + result.getStatus().getStatusMessage());
                    // 로그인 실패 했을때
                    Toast.makeText(LoginActivity.this, "로그인 실패",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            // 다른 리퀘스트 코드일 때 이리로 온다.

        }
    }
}
