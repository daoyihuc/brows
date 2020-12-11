package com.daoyiksw.browsesocial.ui.user.adapter;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.ui.user.bean.MemberBean;

import java.util.List;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/13
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class MemberAdapter extends BaseQuickAdapter<MemberBean, BaseViewHolder> {

    private Context context;

    public MemberAdapter(int layoutResId, @Nullable List<MemberBean> data) {
        super(layoutResId, data);
    }

    public MemberAdapter(Context context,@Nullable List<MemberBean> data) {
        super(R.layout.item_member,data);
        this.context=context;
    }

    public MemberAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberBean item) {
        ImageView imageView=helper.getView(R.id.image);

        Glide.with(context).load(item.getUrl()).into(imageView);
        helper.setText(R.id.name,item.getName());
        helper.setText(R.id.details,item.getDetails());

    }
}
