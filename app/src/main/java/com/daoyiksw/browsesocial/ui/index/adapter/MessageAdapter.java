package com.daoyiksw.browsesocial.ui.index.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.ui.index.bean.MessageListBean;

import java.util.List;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/7
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class MessageAdapter extends BaseQuickAdapter<MessageListBean, BaseViewHolder> {

    private Context context;
    public MessageAdapter(int layoutResId, @Nullable List<MessageListBean> data) {
        super(layoutResId, data);
    }

    public MessageAdapter(Context context, @Nullable List<MessageListBean> data) {
        super(R.layout.item_message,data);
        this.context=context;
    }

    public MessageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageListBean item) {

        ImageView imageView=helper.getView(R.id.img);
        Glide.with(context).load(item.getUrl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
        helper.setText(R.id.title,item.getName())
                .setText(R.id.details,item.getDetails())
                .setText(R.id.count,"有"+item.getCount()+"个人在聊");
    }
}
