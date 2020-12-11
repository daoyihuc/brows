package com.daoyiksw.browsesocial.ui.user.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.pub.holder.GlideRoundTransform;
import com.daoyiksw.browsesocial.ui.index.bean.NewBean;

import java.util.List;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/11
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class CollectAdapter extends BaseQuickAdapter<NewBean, BaseViewHolder> {

    private Context context;

    public CollectAdapter(int layoutResId, @Nullable List<NewBean> data) {
        super(layoutResId, data);
    }

    public CollectAdapter(Context context,@Nullable List<NewBean> data) {
        super(R.layout.item_collect,data);
        this.context=context;
    }

    public CollectAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewBean item) {


        ImageView imageView= helper.getView(R.id.newimage);
        ImageView userImage=helper.getView(R.id.useriamge);
        RequestOptions requestOptions=new RequestOptions();
        requestOptions.transform(new GlideRoundTransform(context,10));
        Glide.with(context).load(item.getImage())
                .apply(requestOptions)
                .into(imageView);

        Glide.with(context).load(item.getUserImg())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(userImage);

        helper.setText(R.id.name,item.getName());
        helper.setText(R.id.username,item.getUsername());
        helper.setText(R.id.readcount,item.getReadcount());
        helper.setText(R.id.label,item.getLable());

        helper.addOnClickListener(R.id.delete);

    }
}
