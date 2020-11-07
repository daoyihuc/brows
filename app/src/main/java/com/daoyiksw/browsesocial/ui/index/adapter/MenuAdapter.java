package com.daoyiksw.browsesocial.ui.index.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.ui.index.bean.MenuBean;

import java.util.List;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/7
 * @details: '菜单适配器'
 * @mails: '1966287146@qq.com'
 */
public class MenuAdapter extends BaseQuickAdapter<MenuBean, BaseViewHolder> {

    private Context context;

    public MenuAdapter(int layoutResId, @Nullable List<MenuBean> data) {
        super(layoutResId, data);
    }

    public MenuAdapter(Context context, @Nullable List<MenuBean> data) {
        super(R.layout.item_menu,data);
        this.context=context;
    }

    public MenuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuBean item) {

        ImageView view = helper.getView(R.id.image);
        Glide.with(context).load(item.getUrl())
                .into(view);

//        holder.imageView.setImageResource(data.getRes_id());
        helper.setText(R.id.name,item.getName());

    }
}
