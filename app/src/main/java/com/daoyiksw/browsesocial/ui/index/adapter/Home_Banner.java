package com.daoyiksw.browsesocial.ui.index.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.pub.holder.GlideRoundTransform;
import com.daoyiksw.browsesocial.pub.holder.ImageTitleHolder;
import com.daoyiksw.browsesocial.ui.index.bean.image_banner_home;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

/**
 * @author 道义（daoyi）
 * @version 1.0
 * @date 2020-03-07
 * @params
 **/
public class Home_Banner extends BannerAdapter<image_banner_home, ImageTitleHolder> {

    private Context context;

    public Home_Banner(Context context, List<image_banner_home> datas) {
        super(datas);
        this.context=context;
    }

    @Override
    public ImageTitleHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ImageTitleHolder(BannerUtils.getView(parent, R.layout.banner_adapter));
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindView(ImageTitleHolder holder, image_banner_home data, int position, int size) {

        ImageView imageView = holder.imageView;
        RequestOptions requestOptions=new RequestOptions();
        requestOptions.transform(new GlideRoundTransform(context,10));
//        requestOptions.placeholder(R.mipmap.jiazaitupian2)
//                .error(R.mipmap.jiazaishibai2)
//                .fallback(R.mipmap.jiazaitupian2);
        Glide.with(context).load(data.getUrl())
                .apply(requestOptions)
                .into(imageView);

//        holder.imageView.setImageResource(data.getRes_id());
//        holder.textView.setText(data.getName());
    }
}
