package com.example.surcae_laptop.pictoria;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GridAdapter extends RecyclerView.Adapter<GridHolder> {

    //Bitmap 객체 생성 및 메소드 추가

    Bitmap[] bitmaps;

    public GridAdapter(Bitmap[] bitmaps) {
        this.bitmaps=bitmaps;
    }

    @NonNull
    @Override
    public GridHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(MainActivity.getContext()).inflate(R.layout.activity_main,parent,false);
        return new GridHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GridHolder holder, int position) {
        holder.imageView.requestLayout();
        holder.imageView.setImageBitmap(bitmaps[position]);
        holder.textView.setText("Image : Image details");
    }

    @Override
    public int getItemCount() {
        return bitmaps.length;
    }
}
