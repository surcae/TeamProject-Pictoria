package com.example.surcae_laptop.pictoria;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;
/*
이 클래스는 메인 엑티비티에서 사용한다.
백그라운드 실행으로 처음에는 가만히 있다가 사용자가 검색 버튼을 눌렀을때 활성화 되고 백로딩으로 구글 Search API를 이용해서 검색 결과를 받아오고 이미지를 받아온다.
이미지가 여러개 있을텐데 처음에 화질이 낮은걸로 포팅(바꿈)해서 쭉 나열해서 보여준다. Bitmap 어뎁터?랑 연동함.
눌렀을때 저번에 봤던 page? 이미지 왼쪽 오른쪽 스크롤 하는거에 딱 몇 십개만 추가해서 보여준다.

이미지는 아래로 스크롤해서 볼 수 있고
20~30개가 보여진다. 20~30개를 초과하는 스크롤 임계점에 도달하면 더 많은 정보를 불러와서 다시 추가해준다.

이미지 클릭하면 원본 이미지를 보여주는 형식.
이 이미지를 내 사진첩에 보관할 수도 있고 클라우드에 전송할 수도 있다.

클라우드에 전송되는건 이미지가 될지 URL만 될지는 아직 고민중...
이미지 갯수 혹은 구글 클라우드와 연동해서 이미지를 구글 클라우드에 전송하는 기능을 가지고 있다.
 */

public class ImageSearchEngine extends AsyncTask<URL, Integer, String> {
    @Override
    // Callback from Execute() of MainActivity.
    protected String doInBackground(URL... urls) {
        URL url = urls[0];
        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            Log.e(TAG, "Http connection ERROR " + e.toString());
        }
        return null;
    }
    protected void onPreExecute(){

    }
}
