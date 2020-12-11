package com.daoyiksw.browsesocial.base.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * @author 道义（daoyi）
 * @version 1.0
 * @date 2020-05-27
 * @params
 **/
public class Adpter_guide extends PagerAdapter {

    private List<View> list;

    public Adpter_guide(List<View> views) {
        this.list=views;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }


}
