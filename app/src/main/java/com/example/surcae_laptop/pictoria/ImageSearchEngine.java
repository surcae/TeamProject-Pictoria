package com.example.surcae_laptop.pictoria;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class ImageSearchEngine extends AsyncTask<URL, Void, Boolean> {

    // Callback from Execute() of MainActivity.
    @Override
    protected void onPreExecute(){

    }

    protected Boolean doInBackground(URL... urls) {
        GridManager.getInstance().isSearchStart = false;
        StringBuffer buffer = null;
        try {
            InputStream is = urls[0].openConnection().getInputStream();
            buffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch ( Exception e) {
            e.printStackTrace();
        }
        String jsonString = buffer.toString();

        try {
            String[] temp = new String[10];
            JSONObject jsonData = new JSONObject(jsonString);
            for(int i = 0; i < jsonData.getJSONArray("items").length(); ++i) {
                JSONObject jsonObject = jsonData.getJSONArray("items").getJSONObject(i);
                System.out.println(jsonObject.optString("link"));
                temp[i] = jsonObject.optString("link");
            }
            GridManager.getInstance().setURLStore(temp);
            GridManager.getInstance().isSearchStart = true;

            //Bitmap[] bitmaps = new Bitmap[10];
        } catch (JSONException jse){
            jse.printStackTrace();
        }
        publishProgress();
        return true;
    }


    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        GridManager.getInstance().getGridAdapter().notifyDataSetChanged();
    }
    private void SetBitMapWithPicaso(){
        //Picasso.get().load()
    }
}

