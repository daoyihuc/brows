package com.daoyiksw.browsesocial.ui.index.fragment;

import android.view.View;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;

/**
 * Administrator: "daoyi(yanwen)"
 *
 * @params: 2020/12/12
 * @details: ''
 * @mails: '1966287146@qq.com'
 */
public class ChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {


    @Override
    protected void setUpView() {
        super.setUpView();
        titleBar.setRightLayoutVisibility(View.GONE);
        titleBar.setBackgroundColor(0x00000000);
        titleBar.setLeftLayoutVisibility(View.GONE);

    }

    @Override
    public void onSetMessageAttributes(EMMessage message) {

    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {

    }

    @Override
    public void onAvatarLongClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return null;
    }
}
