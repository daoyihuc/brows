package com.daoyiksw.browsesocial.ui.index.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daoyiksw.browsesocial.R;
import com.daoyiksw.browsesocial.consts.BaseFragment;
import com.daoyiksw.browsesocial.pub.holder.WrapContentLinearLayoutManager;
import com.daoyiksw.browsesocial.ui.index.adapter.MessageAdapter;
import com.daoyiksw.browsesocial.ui.index.bean.MessageListBean;
import com.daoyiksw.browsesocial.untils.MacUtils;
import com.daoyiksw.browsesocial.untils.StatusBarUtils_d;
import com.daoyiksw.browsesocial.views.compant.Titlabar;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/11/7
 * @details: '聊天'
 * @mails: '1966287146@qq.com'
 */
public class MessageFragment extends BaseFragment  {


    private View view;
    private Titlabar titlabar;

    private CoordinatorLayout box;
    private RecyclerView messageList;
    private MessageAdapter messageAdapter;
    private List<MessageListBean> list_message;

    {
        list_message=new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_message,null,false);
        initData();
        initUI();

        return view;
    }

    @Override
    protected void initUI() {
        box=view.findViewById(R.id.box);
        int statusBarHeight = StatusBarUtils_d.getStatusBarHeight(getActivity());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1,-1);
        layoutParams.setMargins(0,statusBarHeight,0,0);
        box.setLayoutParams(layoutParams);

        // 导航栏
        titlabar=view.findViewById(R.id.titlebar);
        titlabar.setCenterTitle("聊天室");
        titlabar.setCenterColor(0xffffffff);
        titlabar.setLeftMargin(MacUtils.dpto(10),0,0,0);
//        titlabar.setLeftDrawable(R.drawable.ic_baseline_arrow_back_ios_24,0x90000000);
        titlabar.addviews();
        titlabar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressed();
            }
        });

        setMessageList();



    }

    @Override
    protected void initData() {
        for(int i=0;i<5;i++){
            MessageListBean listBean=new MessageListBean();
            listBean.setUrl(R.mipmap.tupian);
            listBean.setName("聊天室的名字");
            listBean.setDetails("聊天室描述聊天室描述");
            listBean.setCount("150");
            list_message.add(listBean);
        }

    }

    @Override
    protected <T> T Https() {
        return null;
    }

    //状态栏修改
    public void changeStatus(){
        StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(),0,box);
    }
    // recyclerview
    private void setMessageList(){
        messageList=view.findViewById(R.id.messageList);
        WrapContentLinearLayoutManager linearLayoutManager=new WrapContentLinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        messageList.setLayoutManager(linearLayoutManager);
        messageAdapter=new MessageAdapter(getContext(),list_message);
        messageList.setAdapter(messageAdapter);

    }
}
