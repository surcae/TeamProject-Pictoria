package com.example.surcae_laptop.pictoria;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

public class GridAdapter extends RecyclerView.Adapter<GridHolder> {

    //Bitmap 객체 생성 및 메소드 추가
    Bitmap[] bitmaps;

    public GridAdapter(Bitmap[] bitmaps) {
        this.bitmaps=bitmaps;
    }

    public void setBitmaps(Bitmap[] _params){
        bitmaps = _params;
    }
    @NonNull
    @Override
    public GridHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(MainActivity.getContext()).inflate(R.layout.grid_view,parent,false);
        return new GridHolder(v, 0);
    }

    @Override
    public void onBindViewHolder(@NonNull GridHolder holder, int position) {
            holder.curIndex = position;
            holder.imageView.requestLayout();
            if(GridManager.getInstance().isSearchStart == true && GridManager.getInstance().CheckAllLoaded()) {
                    Picasso.get().load(GridManager.getInstance().getUrlWithPos(position)).into(holder.imageView);
                holder.imageView.invalidate();
            }
            else
                holder.imageView.setImageBitmap(bitmaps[position]);
            holder.textView.setText("");
    }

    @Override
    public int getItemCount() {
        return bitmaps.length;
    }
}
