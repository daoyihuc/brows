package com.daoyiksw.browsesocial.ui.index.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import com.daoyiksw.browsesocial.R;
import com.hyphenate.easeui.EaseConstant;
import com.jaeger.library.StatusBarUtil;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/12/14
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class ChatFragmentParent extends Fragment {

    private View view;
    private RelativeLayout chatBox;
    private CoordinatorLayout box;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chatfragmentparent,null);
        initView();
        return view;
    }
    // 初始化界面
    private void initView(){
        box = view.findViewById(R.id.box);
        chatBox = view.findViewById(R.id.chatBox);
        ChatFragment chatFragment=new ChatFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EaseConstant.EXTRA_USER_ID,"134589741858818");
        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE,EaseConstant.CHATTYPE_GROUP);
        chatFragment.setArguments(bundle);
        getChildFragmentManager().beginTransaction().add(R.id.box, chatFragment).commit();
    }
    //状态栏修改
    public void changeStatus(){
        StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(),0,box);
    }
}
