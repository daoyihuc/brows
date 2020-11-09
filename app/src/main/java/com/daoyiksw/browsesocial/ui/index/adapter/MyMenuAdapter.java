package com.daoyiksw.browsesocial.ui.index.adapter;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.ui.index.bean.MyMenuBean;

import java.util.List;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/9
 * @details: '我的菜单列表'
 * @mails: '1966287146@qq.com'
 */
public class MyMenuAdapter extends BaseQuickAdapter<MyMenuBean, BaseViewHolder> {

    private Context context;

    public MyMenuAdapter(int layoutResId, @Nullable List<MyMenuBean> data) {
        super(layoutResId, data);
    }

    public MyMenuAdapter(Context context,@Nullable List<MyMenuBean> data) {
        super(R.layout.item_mymenu,data);
        this.context=context;
    }

    public MyMenuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyMenuBean item) {

        ImageView imageView=helper.getView(R.id.menuicon);

        Glide.with(context).load(item.getUrl()).into(imageView);

        helper.setText(R.id.menutext,item.getName())
                .setText(R.id.details,item.getDetails());
    }
}
