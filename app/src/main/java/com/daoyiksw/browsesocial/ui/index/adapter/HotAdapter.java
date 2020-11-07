package com.daoyiksw.browsesocial.ui.index.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.ui.index.bean.HotBean;

import java.util.List;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/7
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class HotAdapter extends BaseQuickAdapter<HotBean, BaseViewHolder> {
    private Context context;
    public HotAdapter(int layoutResId, @Nullable List<HotBean> data) {
        super(layoutResId, data);
    }

    public HotAdapter(Context context, @Nullable List<HotBean> data) {
        super(R.layout.item_hot_list,data);
        this.context=context;
    }

    public HotAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotBean item) {

        helper.setText(R.id.count,""+item.getCount());
        helper.setText(R.id.name,item.getDetails());
    }
}
