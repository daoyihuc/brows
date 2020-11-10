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
import com.daoyiksw.browsesocial.ui.user.bean.CommentBean;

import java.util.List;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/10
 * @details: '评论适配及'
 * @mails: '1966287146@qq.com'
 */
public class CommentAdapter extends BaseQuickAdapter<CommentBean, BaseViewHolder> {
    private Context context;
    public CommentAdapter(int layoutResId, @Nullable List<CommentBean> data) {
        super(layoutResId, data);
    }

    public CommentAdapter(Context context,@Nullable List<CommentBean> data) {
        super(R.layout.item_comment,data);
        this.context=context;
    }

    public CommentAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean item) {

        RequestOptions requestOptions=new RequestOptions();
        requestOptions.transform(new GlideRoundTransform(context,10));

        ImageView userUrlView=helper.getView(R.id.useriamge);
        ImageView commentView=helper.getView(R.id.comment_img);


        Glide.with(context).load(item.getUserUrl())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(userUrlView);


        Glide.with(context).load(item.getCommentUrl())
                .apply(requestOptions)
                .into(commentView);

        helper.setText(R.id.username,item.getName())// 用户名
                .setText(R.id.comment_time,item.getTime())// 评论时间
                .setText(R.id.comment_details,item.getDetails()) // 评论内容
                .setText(R.id.comment_title,item.getCommentTitel());




    }
}
