package com.daoyiksw.browsesocial.ui.user.adapter;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.ui.user.bean.AccountRecodesBean;

import java.util.List;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/10
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class AccountRecodesAdpter extends BaseQuickAdapter<AccountRecodesBean, BaseViewHolder> {

    private Context context;

    public AccountRecodesAdpter(int layoutResId, @Nullable List<AccountRecodesBean> data) {
        super(layoutResId, data);
    }

    public AccountRecodesAdpter(Context context,@Nullable List<AccountRecodesBean> data) {
        super(R.layout.item_accont_records,data);
        this.context=context;
    }

    public AccountRecodesAdpter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccountRecodesBean item) {

        helper.setText(R.id.type,item.getName())
                .setText(R.id.time,item.getTime())
                .setText(R.id.count,item.getCount());

    }
}
