package com.example.administrator.circlelayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class CircleLayout extends FrameLayout {
    private Paint paint;
    public CircleLayout(Context context) {
        super(context);
        init(context,null);
    }

    public CircleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CircleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
    }


    ArrayList<View> mChilds = new ArrayList<>();

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int rightW = 0;
        int rightH = 0;
        if(MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY){
            rightW = MeasureSpec.getSize(widthMeasureSpec);
        }else{
            rightW = getPaddingRight() + getPaddingLeft() + getMeasuredWidth();
        }

        if(MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY){
            rightH = MeasureSpec.getSize(heightMeasureSpec);
        }else{
            rightH = getPaddingTop() + getPaddingBottom() + getMeasuredHeight();
        }
        setMeasuredDimension(rightW,rightH);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int count = getChildCount();
        mChilds.clear();
        for (int i =0;i<count;i++){
            mChilds.add(getChildAt(i));
        }
        int perDegree  = 360/mChilds.size();
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        int centerx = w/2;
        int centery = h/2;


        for (int i=0;i<mChilds.size();i++) {
            View child = mChilds.get(i);
            double deg = Math.toRadians(i * perDegree - 90);
            int childw = child.getMeasuredWidth();
            int childh = child.getMeasuredHeight();
            int radius = (int) ((float)w / 2f);
            int smallRadius = (int) ((int) radius - Math.sqrt(childh*childh+childw*childw)/2);
            PointF pp_end = new PointF((float) (centerx + smallRadius * Math.cos(deg)),
                    (float) (centery + smallRadius * Math.sin(deg)));
            child.setX(pp_end.x - child.getWidth()/2);
            child.setY(pp_end.y - child.getHeight()/2);
        }
    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        int perDegree  = 360/mChilds.size();
//        int w = getMeasuredWidth();
//        int h = getMeasuredHeight();
//        int centerx = w/2;
//        int centery = h/2;
//        int r2 = (int) ((float)w / 2f);
//        int r3 = (int) ((float)h / 4f);
//
//        for (int i=0;i<mChilds.size();i++) {
//            View child = mChilds.get(i);
//            double deg = Math.toRadians(i * perDegree -90);
//            PointF pp_begin = new PointF((float) (centerx + r3 * Math.cos(deg)),
//                    (float) (centery + r3 * Math.sin(deg)));
//            PointF pp_end = new PointF((float) (centerx + r2 * Math.cos(deg)),
//                    (float) (centery + r2 * Math.sin(deg)));
//
//
//            paint.setStyle(Paint.Style.STROKE);
//            paint.setStrokeWidth(10);
//            canvas.drawLine(pp_begin.x, pp_begin.y, pp_end.x, pp_end.y, paint);
//        }
//    }
}
