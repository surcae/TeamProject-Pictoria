package com.example.surcae_laptop.pictoria;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static android.support.v4.content.ContextCompat.startActivity;

public class GridHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    ImageView imageView_pop;
    TextView textView;
    Dialog dialog;
    int curIndex;

    private final Context context;

    public GridHolder(final View itemView, int index) {
        super(itemView);
        curIndex = index;
        context=itemView.getContext();

        imageView=itemView.findViewById(R.id.s_image);
        textView=itemView.findViewById(R.id.t_caption);
        imageView.setClickable(true);

        //recyclerview의 imageview 클릭시 팝업 형태 구현
        imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customdialog(v);
                }
        });
    }

        public void customdialog(View v) {

            imageView_pop=(ImageView) v.findViewById(R.id.image_pop);
            if(GridManager.getInstance().isSearchStart == true) {
                //Picasso.get().load(GridManager.getInstance().getUrlWithPos(curIndex)).into(imageView_pop);
            }
            dialog= new Dialog(MainActivity.getContext());
            dialog.setContentView(R.layout.popup);
            dialog.show();

    }


}
