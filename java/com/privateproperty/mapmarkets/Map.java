package com.privateproperty.mapmarkets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Pro-rock on 25.03.2016.
 */
public class Map extends AppCompatActivity {
    Drawable mapPic;
    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = (RelativeLayout) findViewById(R.id.mapLayout);

//        setContentView(R.layout.map_layout);
        String map = getIntent().getStringExtra("MarketMAP");

//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
//
//                .build();
//        ImageLoader.getInstance().init(config);

//        ImageLoader imageLoader = ImageLoader.getInstance();
        ImageManager man = new ImageManager();
//        man.fetchImage(this,604800,map,null);
        PointImageView imgView;
        setContentView(imgView = new PointImageView(this));
        imgView.setFileName(this.getExternalCacheDir() + "/"+man.md5(map)+ ".png");
        imgView.setPoint(new Point(0,0));
//        if (!map.isEmpty()){
//
//
//            try {
//                InputStream is = (InputStream) new URL(map).getContent();
//
//                mapPic =  Drawable.createFromStream(is, "mapPicLoader");
//                layout.setBackground(mapPic);
//
//            } catch (Exception e) {
//                //((ImageView) view.findViewById(R.id.marketImg)).setImageResource(R.mipmap.ic_launcher);
//                //mapPic =  android.R.mipmap.sym_def_app_icon;
////                getResources().getDrawable();
//                Log.e("MM", "Error parse img from url" + e);
//
//            }}
//        setContentView(new DrawView(this));
//    }
//
//    class DrawView extends View {
//
//        public DrawView(Context context) {
//            super(context);
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//
//            canvas.drawColor(Color.GREEN);
//            mapPic.draw(canvas);
        }



//        setContentView(R.layout.map_layout);
//        String map = getIntent().getStringExtra("MarketMAP");
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
//
//                .build();
//        ImageLoader.getInstance().init(config);
//
//        ImageLoader imageLoader = ImageLoader.getInstance();
//        if (!map.isEmpty()){
//
//            imageLoader.displayImage(map, (WebView) findViewById(R.id.imgMap));
//            try {
//                InputStream is = (InputStream) new URL(map).getContent();
//
//                ((ImageView) findViewById(R.id.imgMap)).setImageDrawable(Drawable.createFromStream(is, "marketLogo"));
//
//            } catch (Exception e) {
//                //((ImageView) view.findViewById(R.id.marketImg)).setImageResource(R.mipmap.ic_launcher);
//                Log.e("MM", "Error parse img from url");
//
//            }}
////        MapView map = (MapView) findViewById(R.id.map);
////        map.setTileSource(TileSourceFactory.MAPNIK);
    }



