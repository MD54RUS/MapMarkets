package com.privateproperty.mapmarkets;

/**
 * Created by Pro-rock on 30.03.2016.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.view.View;
import android.view.GestureDetector;
import android.view.GestureDetector.*;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.widget.Scroller;

import android.widget.ImageView;

import java.io.File;

public class PointImageView extends View {


    private String fileName;


    private ScaleGestureDetector scaleGestureDetector;
    private Scroller scroller;


    private Bitmap image = null;
    private Point point;

    private final GestureDetector gestureDetector;
    private final Paint paint = new Paint();

    private float scaleFactor;

    private int getScaledWidth()
    {
        return (int)(image.getWidth() * scaleFactor);
    }

    private int getScaledHeight()
    {
        return (int)(image.getHeight() * scaleFactor);
    }





    public PointImageView(Context context) {
        super(context);
        gestureDetector = new GestureDetector(context, new MyGestureListener());
        setHorizontalScrollBarEnabled(true);
        setVerticalScrollBarEnabled(true);

//        TypedArray a = context.obtainStyledAttributes(R.styleable.View);
//
//        initializeScrollbars(a);
//        a.recycle();
        scroller = new Scroller(context);

        paint.setFilterBitmap(true);
        paint.setDither(false);

    }
    @Override
    protected int computeHorizontalScrollRange()
    {
        return image.getWidth();
    }

    @Override
    protected int computeVerticalScrollRange()
    {
        return image.getHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event)) return true;
        return true;
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
        {
            scrollBy((int)distanceX, (int)distanceY);
            return true;
        }
    }

    public PointImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, new MyGestureListener());

    }

    public PointImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        gestureDetector = new GestureDetector(context, new MyGestureListener());
    }

    //Вызываем этот метод, передаём туда точку.
    //Если передаём null - точка рисоваться не будет
    public void setPoint(Point point) {
        this.point = point;
        invalidate();
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (point != null) {
            Bitmap map;
            try {
//                File imgFile = new File (fileName);
//                boolean b = imgFile.exists();
//                String s = imgFile.getAbsolutePath();
                map = BitmapFactory.decodeFile(fileName);
            } catch (Exception e)
            {
                Log.e("MM", "Error parse map from flash: " + e.toString());
                map = null;
            }
            image = map;
            Bitmap pt = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_map_marker_circle);
//            Paint paint = new Paint();
//            int width = map.getWidth();
//
//            int height = map.getHeight();
////            paint.set(R.mipmap.ic_map_marker_circle);
////            paint.setColor(Color.BLACK);
////            canvas.drawPoint(point.x, point.y, paint);
//            //canvas.scale(height,width);
//            int cW = canvas.getWidth();
//            int cH = canvas.getWidth();

            canvas.drawBitmap(map,0,0,null);
            canvas.drawBitmap(pt,point.x,point.y, null);

        }
    }
}
