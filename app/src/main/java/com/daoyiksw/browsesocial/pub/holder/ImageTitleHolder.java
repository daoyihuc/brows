package com.daoyiksw.browsesocial.pub.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daoyiksw.browsesocial.R;


/**
 * @author 道义（daoyi）
 * @version 1.0
 * @date 2020-03-07
 * @params
 **/
public class ImageTitleHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textView;

    public ImageTitleHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.image);
        textView=itemView.findViewById(R.id.bannerTitle);
    }
}
