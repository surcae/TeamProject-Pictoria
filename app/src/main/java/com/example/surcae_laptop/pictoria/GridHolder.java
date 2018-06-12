package com.example.surcae_laptop.pictoria;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GridHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textView;

    public GridHolder(View itemView) {
        super(itemView);

        imageView=itemView.findViewById(R.id.s_image);
        textView=itemView.findViewById(R.id.t_caption);

        imageView.getDrawable();

    }
}
