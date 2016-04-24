package com.privateproperty.mapmarkets;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Pro-rock on 30.03.2016.
 */
public class Map2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.map_layout);
        ImageViewer imageViewer = new ImageViewer(this);
//        PointImageView imgView;
//        setContentView(imgView = new PointImageView(this));
        String map = getIntent().getStringExtra("MarketMAP");

        try
        {   ImageManager man = new ImageManager();
            imageViewer.loadImage(this.getExternalCacheDir() + "/"+man.md5(map)+ ".png");
        }
        catch (Exception e)
        {


        }
//        imageViewer.setPoint(new Point(100,100));
        imageViewer.setLoc(getIntent().getIntArrayExtra("loc"));
        setContentView(imageViewer);

}

}
