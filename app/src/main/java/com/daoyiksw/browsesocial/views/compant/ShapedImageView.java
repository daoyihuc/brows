package com.daoyiksw.browsesocial.views.compant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.daoyiksw.browsesocial.R;

import java.util.Arrays;

/**
 * @author 道义（daoyi）
 * @version 1.0
 * @date 2020-03-07
 * @params
 **/
@SuppressLint("AppCompatCustomView")
public class ShapedImageView extends ImageView {

    private static final int SHAPE_MODE_ROUND_RECT = 1;
    private static final int SHAPE_MODE_CIRCLE = 2;

    private int mShapeMode = 0;
    private float mRadius = 0;
    private float mMarginWidth = 0;
    private int mMarginColor =0;
    private Shape mShape;
    private Paint mPaint;
    //图片类型
    int type=0;

    //组件宽高
    int width,height;

    //圆角半径
    float rx,ry;
    float left_top,left_bootom,right_top,right_bottom;

    public ShapedImageView(Context context) {
        super(context);
        init(null);
    }

    public ShapedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ShapedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(LAYER_TYPE_HARDWARE, null);
        }

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ShapedImageView);
            mShapeMode = a.getInt(R.styleable.ShapedImageView_shape_mode, 0);
            if(mShapeMode==2){
                type=0;
            }if(mShapeMode==1){
                type=1;
            }
            mRadius = a.getDimension(R.styleable.ShapedImageView_round_radius, 0);
            mMarginWidth = a.getDimension(R.styleable.ShapedImageView_margin_width, 0);
            mMarginColor =a.getColor(R.styleable.ShapedImageView_margin_color, getResources().getColor(R.color.whilte));
            rx=mRadius;
            ry=mRadius;
            a.recycle();
        }
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width=getMysize(widthMeasureSpec);
        height=getMysize(heightMeasureSpec);

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            switch (mShapeMode) {
                case SHAPE_MODE_ROUND_RECT:
                    break;
                case SHAPE_MODE_CIRCLE:
                    int min = Math.min(getWidth(), getHeight());
                    mRadius = (float) min / 2;
                    break;
            }
            if (mShape == null) {
                float[] radius = new float[8];
                Arrays.fill(radius, mRadius);
                mShape = new RoundRectShape(radius, null, null);
            }
            mShape.resize(getWidth(), getHeight());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int saveCount = canvas.getSaveCount();
        canvas.save();
        super.onDraw(canvas);
        switch (mShapeMode) {
            case SHAPE_MODE_ROUND_RECT:
            case SHAPE_MODE_CIRCLE:
                if (mShape != null) {
                    mShape.draw(canvas, mPaint);
                }
                break;
        }
        canvas.restoreToCount(saveCount);
    }
    @Override
    public void draw(Canvas canvas) {

        //设置绘制路径
        Paint paint=new Paint();
        Path path=new Path();
        RectF rect=new RectF(0,0,width,height);
        paint.setAntiAlias(true);

        canvas.save();
        if(type==0){
            int Circle_x=width/2;
            int Circle_y=height/2;
            int radius=0;

            if(width<=height){
                radius=width/2;
            }else if (height<=width){
                radius=height/2;
            }

            if(mMarginWidth >0) {
                paint.setColor(mMarginColor);
                canvas.drawCircle(Circle_x, Circle_y, radius, paint);
                path.addCircle(Circle_x,Circle_y,radius-mMarginWidth, Path.Direction.CW);
            } else{
                path.addCircle(Circle_x,Circle_y,radius, Path.Direction.CW);
            }
        }else if(type==1){
            path.addRoundRect(rect,rx,ry, Path.Direction.CW);
        }else if(type==2){
            path.addRoundRect(rect,new float[]{left_top,
                            left_top, left_bootom,
                            left_bootom, right_top,
                            right_top,right_bottom,
                            right_bottom},
                    Path.Direction.CW);
        }

        canvas.clipPath(path);
        super.draw(canvas);
        canvas.restore();
    }

    //设置图片类型
    public void setType(int t){
        type=t;
    }

    //宽高测量
    public int getMysize(int measure){
        int result=0;

        int spaceMode= MeasureSpec.getMode(measure);
        int spaceSize= MeasureSpec.getSize(measure);

        if(spaceMode== MeasureSpec.EXACTLY){
            result=spaceSize;
        }else if(spaceMode== MeasureSpec.AT_MOST){
            result=spaceSize;
        }

        result=spaceSize;

        return result;
    }

}

