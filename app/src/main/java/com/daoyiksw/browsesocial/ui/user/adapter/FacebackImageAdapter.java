package com.daoyiksw.browsesocial.ui.user.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.untils.ScreenUtils;
import com.daoyiksw.browsesocial.views.compant.ShapedImageView;

import java.util.List;

/**
 * AUTHOR : 谢明峰
 * TODO : 意见反馈图片适配器
 * DATE : 2020/3/25
 * VERSION : 1.0
 */
public class FacebackImageAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {

    private Context mContext;
    private List<String> datas;
    private FrameLayout.LayoutParams params;
    private RequestOptions options;

    public FacebackImageAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public FacebackImageAdapter(Context context, @Nullable List<String> data) {
        super(R.layout.adapter_album,data);
        this.mContext=context;
        this.datas =data;

        int width = ScreenUtils.getDisplayWidth();
        int itemWidth =(width - MacUtils.dpto(30) -MacUtils.dpto(21)) /4 -MacUtils.dpto(4);  //减掉两边间距和item间距，删除按钮突出的间距
        params =new FrameLayout.LayoutParams(itemWidth, itemWidth);
        params.topMargin =MacUtils.dpto(3);

        options = new RequestOptions()
                .transform(new RoundedCorners(MacUtils.dpto(10)));
//                .fallback( R.drawable.error_icon) //url为空的时候,显示的图片
//                .error(R.drawable.error_icon);//图片加载失败后，显示的图片
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        ShapedImageView imgIv =helper.getView(R.id.image_iv);
        ImageView deleteIv =helper.getView(R.id.delete_iv);
        imgIv.setLayoutParams(params);
        helper.addOnClickListener(R.id.delete_iv);

        if(TextUtils.equals("#", item)){

            deleteIv.setVisibility(View.GONE);
            Glide.with(mContext)
                    .load(R.drawable.faceback_add_icon)
                    .apply(options).into(imgIv);
        } else{

            deleteIv.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(item)
                    .apply(options)
                    .into(imgIv);
        }
    }
}