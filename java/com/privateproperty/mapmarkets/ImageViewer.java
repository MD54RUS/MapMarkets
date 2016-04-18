package com.privateproperty.mapmarkets;



        import android.content.Context;
        import android.content.res.TypedArray;
        import android.graphics.*;
        import android.util.DisplayMetrics;
        import android.view.Display;
        import android.view.View;
        import android.view.GestureDetector;
        import android.view.GestureDetector.*;
        import android.view.MotionEvent;
        import android.view.ScaleGestureDetector;
        import android.view.ScaleGestureDetector.OnScaleGestureListener;
        import android.view.WindowManager;
        import android.widget.Scroller;


public class ImageViewer extends View
{
    private Bitmap image = null;
    private Point point = null;
    private int[] loc = null;

    private final GestureDetector gestureDetector;
    private final ScaleGestureDetector scaleGestureDetector;
    private final Scroller scroller;

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

    public ImageViewer(Context context)
    {
        super(context);

        gestureDetector = new GestureDetector(context, new MyGestureListener());
        scaleGestureDetector = new ScaleGestureDetector(context, new MyScaleGestureListener());

        // init scrollbars
        setHorizontalScrollBarEnabled(true);
        setVerticalScrollBarEnabled(true);

//        TypedArray a = context.obtainStyledAttributes(R.styleable.View);
//        initializeScrollbars(a);
//        a.recycle();

        scroller = new Scroller(context);

        paint.setFilterBitmap(true);
        paint.setDither(false);
    }
    public void setPoint(Point point) {
        this.point = point;
        Bitmap pt;
        pt = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_map_marker_circle);
        int[] px = new int[pt.getWidth()*pt.getHeight()];
        pt.getPixels(px, 0, pt.getWidth(), 0, 0, pt.getWidth(), pt.getHeight());
        image.setPixels(px, 0, pt.getWidth(), point.x - (pt.getHeight() / 2), point.y - (pt.getWidth() / 2), pt.getWidth(), pt.getHeight());
        pt.recycle();

        invalidate();

    }
    public void setLoc(int[] loc) {
        this.loc = loc;
        Canvas can = new Canvas(image);
        Path path = new Path();
//        pt = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_map_marker_circle);
        path.moveTo(loc[0],loc[1]);
        int x =0,y=0;
        x+=loc[0];y+=loc[1];
        for (int i = 2; i<loc.length;i=i+2)
        {
            x+=loc[i];y+=loc[i+1];
            path.lineTo(loc[i],loc[i+1]);

        }
        path.close();
        Paint brush = new Paint();
        brush.setColor(Color.RED);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeWidth(3);
        brush.setDither(true);

        can.drawPath(path, brush);
        point = new Point(x*2/loc.length,y*2/loc.length);
        invalidate();

    }







    @Override
    public void onDraw(Canvas canvas)
    {

        Rect dst = new Rect(0, 0, getScaledWidth(), getScaledHeight());

        canvas.drawBitmap(image, null, dst, paint);
        if (point != null){// скорее всего ненужный кусок кода| был нужен для фокуса на пойнт.
            if (point.x-(getWidth()/2)<0)point.set(0,point.y);
            if (point.x+(getWidth()/2)>image.getWidth())point.set(image.getWidth(),point.y);
//            if (!(point.x+(getWidth()/2)>image.getWidth()||point.x-(getWidth()/2)<0))point.set(point.x-(getWidth()/2),point.y);
            if (point.y-(getHeight()/2)<0)point.set(point.x,0);
            if (point.y+(getHeight()/2)>image.getHeight())point.set(point.x,image.getHeight());
//            if (!(point.y+(getHeight()/2)>image.getHeight()||point.y-(getHeight()/2)<0))point.set(point.x,point.y + image.getHeight()/2);
            //Недоделано, проверка выхода за рамки, а если точка за рамки не выходит? Куда скроллит scrollto???
            point.set((int)((float)point.x*scaleFactor),(int)((float)point.y*scaleFactor));
            if (point.x!=0) point.set(point.x-(getWidth()/2),point.y);
            if (point.y!=0) point.set(point.x,point.y-(getHeight()/2));
           scrollTo(point.x,point.y);
            point = null;
        }


    }

    @Override
    protected int computeHorizontalScrollRange()
    {
        return getScaledWidth();
    }

    @Override
    protected int computeVerticalScrollRange()
    {
        return getScaledHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // check for tap and cancel fling
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN)
        {
            if (!scroller.isFinished()) scroller.abortAnimation();
        }
        // handle pinch zoom gesture
        // don't check return value since it is always true
        scaleGestureDetector.onTouchEvent(event);

        // check for scroll gesture


        if (gestureDetector.onTouchEvent(event)) return true;

        // check for pointer release
        if ((event.getPointerCount() == 1) && ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP))
        {
            int newScrollX = getScrollX();
            if (getScrollX() < 0) newScrollX = 0;
            else if (getScrollX() > image.getWidth() - getWidth()) newScrollX = image.getWidth() - getWidth();

            int newScrollY = getScrollY();
            if (getScrollY() < 0) newScrollY = 0;
            else if (getScrollY() > image.getHeight() - getHeight()) newScrollY = image.getHeight() - getHeight();

            if ((newScrollX != getScrollX()) || (newScrollY != getScrollY()))
            {
                scroller.startScroll(getScrollX(), getScrollY(), newScrollX - getScrollX(), newScrollY - getScrollY());
                awakenScrollBars(scroller.getDuration());
            }
        }

        return true;
    }

    @Override
    public void computeScroll()
    {
        if (scroller.computeScrollOffset())
        {
            int oldX = getScrollX();
            int oldY = getScrollY();
            int x = scroller.getCurrX();
            int y = scroller.getCurrY();
            scrollTo(x, y);
            if (oldX != getScrollX() || oldY != getScrollY())
            {
                onScrollChanged(getScrollX(), getScrollY(), oldX, oldY);
            }

            postInvalidate();
        }
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY)
    {
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight)
    {
        int scrollX = (getScaledWidth() < width ? -(width - getScaledWidth()) / 2 : getScaledWidth() / 2);
        int scrollY = (getScaledHeight() < height ? -(height - getScaledHeight()) / 2 : getScaledHeight() / 2);
        scrollTo(scrollX, scrollY);
    }

    public void loadImage(String fileName)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;


        Bitmap image1 = BitmapFactory.decodeFile(fileName, options);
        image = image1.copy(image1.getConfig(),true);
        image1.recycle();
        Canvas canvas1 = new Canvas(image);
        int a1 = canvas1.getWidth();
        int a2 = canvas1.getHeight();
        canvas1 = null;

        if (image == null) {throw new NullPointerException("The image can't be decoded.");}
        else  {
            DisplayMetrics displaymetrics = getResources().getDisplayMetrics();

            if  ((float)displaymetrics.heightPixels/(float)image.getHeight()>(float)displaymetrics.widthPixels/(float)image.getWidth())
            scaleFactor = (float)displaymetrics.widthPixels/(float)image.getWidth();
            else
                scaleFactor = (float)displaymetrics.heightPixels/(float)image.getHeight();
        }

//        scaleFactor = 1;


        // center image on the screen
//        int width = getWidth();
//        int height = getHeight();
//
//
//        if ((width != 0) || (height != 0))
//        {
//
//            int scrollX = (image.getWidth() < width ? -(width - image.getWidth()) / 2 : image.getWidth() / 2);
//            int scrollY = (image.getHeight() < height ? -(height - image.getHeight()) / 2 : image.getHeight() / 2);
//            scrollTo(scrollX, scrollY);
//        }
    }

    private class MyGestureListener extends SimpleOnGestureListener
    {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
        {

            scrollBy((int)distanceX, (int)distanceY);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {
            int fixedScrollX = 0, fixedScrollY = 0;
            int maxScrollX = getScaledWidth(), maxScrollY = getScaledHeight();

            if (getScaledWidth() < getWidth())
            {
                fixedScrollX = -(getWidth() - getScaledWidth()) / 2;
                maxScrollX = fixedScrollX + getScaledWidth();
            }

            if (getScaledHeight() < getHeight())
            {
                fixedScrollY = -(getHeight() - getScaledHeight()) / 2;
                maxScrollY = fixedScrollY + getScaledHeight();
            }

            boolean scrollBeyondImage = (fixedScrollX < 0) || (fixedScrollX > maxScrollX) || (fixedScrollY < 0) || (fixedScrollY > maxScrollY);
            if (scrollBeyondImage) return false;

            scroller.fling(getScrollX(), getScrollY(), -(int)velocityX, -(int)velocityY, 0, getScaledWidth() - getWidth(), 0, getScaledHeight() - getHeight());
            awakenScrollBars(scroller.getDuration());

            return true;
        }
    }

    private class MyScaleGestureListener implements OnScaleGestureListener
    {
        public boolean onScale(ScaleGestureDetector detector)
        {
            scaleFactor *= detector.getScaleFactor();

            int newScrollX = (int)((getScrollX() + detector.getFocusX()) * detector.getScaleFactor() - detector.getFocusX());
            int newScrollY = (int)((getScrollY() + detector.getFocusY()) * detector.getScaleFactor() - detector.getFocusY());
            scrollTo(newScrollX, newScrollY);

            invalidate();

            return true;
        }

        public boolean onScaleBegin(ScaleGestureDetector detector)
        {
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector detector)
        {
        }
    }
}
