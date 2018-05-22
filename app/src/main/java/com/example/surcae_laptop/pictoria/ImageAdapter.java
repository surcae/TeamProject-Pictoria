package com.example.surcae_laptop.pictoria;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context context;

    public ImageAdapter(Context c) {
        context=c;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    //객체반환
    @Override
    public Object getItem(int position) {
        return null;
    }

    //ID반환
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = null;

        if (convertView==null)
        {
            imageView= new ImageView(context);
            //뷰 객체 높이,너비 설정
            imageView.setLayoutParams(new GridView.LayoutParams(450,450));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(4,4,4,4);
        }else {
            imageView=(ImageView) convertView;
        }
        //imageview에 imgs에서 가져온 이미지를 할당
        imageView.setImageResource(imgs[position]);

        return imageView;
    }

    //sample image 파일들
    private Integer[] imgs={

            R.drawable.fowtail,
            R.drawable.morningglow,
            R.drawable.sun,
            R.drawable.windmill,
            R.drawable.fowtail,
            R.drawable.morningglow,
            R.drawable.sun,
            R.drawable.windmill,
            R.drawable.fowtail,
            R.drawable.morningglow,
            R.drawable.sun,
            R.drawable.windmill,
            R.drawable.fowtail,
            R.drawable.morningglow,
            R.drawable.sun,
            R.drawable.windmill,
            R.drawable.fowtail,
            R.drawable.morningglow,
            R.drawable.sun,
            R.drawable.windmill,
            R.drawable.fowtail,
            R.drawable.morningglow,
            R.drawable.sun,
            R.drawable.windmill
    };

}


