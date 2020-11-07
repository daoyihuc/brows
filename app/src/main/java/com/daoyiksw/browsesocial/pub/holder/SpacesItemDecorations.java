package com.daoyiksw.browsesocial.pub.holder;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 道翼(yanwen)
 * @description:
 * @date : 2019/9/21 0021 8:19
 */
public class SpacesItemDecorations extends RecyclerView.ItemDecoration {
    private int space;
    private int top;

    public SpacesItemDecorations(int space) {
        this.space = space;
    }
    public SpacesItemDecorations(int space,int top) {
        this.space = space;
        this.top=top;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        if(top!=0){
            outRect.bottom = top;
        }else{
            outRect.bottom = space;
        }


        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space;
        } else {
            outRect.top = 0;
        }
    }
}

//设置网格布局



//均匀布局

