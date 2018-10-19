package com.privateproperty.mapmarkets;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pro-rock on 25.03.2016.
 */
public class MarketAdaptor extends BaseAdapter{
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<MarketClass> objects;

    MarketAdaptor(Context context, ArrayList<MarketClass> markets) {
        ctx = context;
        objects = markets;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public MarketClass getItem(int position) {
        return objects.get(position);
    }



    @Override
    public long getItemId(int position) {
        return objects.get(position).getId();
    }



    @Override
    public View getView(int position, View view, ViewGroup parent) {


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(ctx)

        .build();
        ImageLoader.getInstance().init(config);



        ImageLoader imageLoader = ImageLoader.getInstance();

        if (view == null) {
            view = lInflater.inflate(R.layout.market_item, parent, false);
        }

        MarketClass m = getItem(position);
        ((TextView) view.findViewById(R.id.marketName)).setText(m.getName());
        ((TextView) view.findViewById(R.id.marketAdress)).setText(m.getAddress());


        if (!m.getUrlLogo().isEmpty()){

        imageLoader.displayImage(m.getUrlLogo(), (ImageView) view.findViewById(R.id.marketImg));
        try {
            InputStream is = (InputStream) new URL(m.getUrlLogo()).getContent();

            ((ImageView) view.findViewById(R.id.marketImg)).setImageDrawable(Drawable.createFromStream(is, "marketLogo"));

        } catch (Exception e) {
            //((ImageView) view.findViewById(R.id.marketImg)).setImageResource(R.mipmap.ic_launcher);
            Log.e("MM", "Error parse img from url. Error: " + e.toString());
            //imageLoader.destroy();
            return view;
        }}
//        view.setOnClickListener(new View.OnClickListener() {
//            public void onClick(final View v) {
////                if (isSamsung) {
//                    Intent intent = new Intent(ctx, SearchActivity.class);
//                    intent.putExtra("MarketID", v.getId());
//                    ctx.startActivity(intent);
//                }
////                else if (...) {
////                    ...
////                }
////            }
//        });

        //else {((ImageView) view.findViewById(R.id.marketImg)).setImageResource(R.mipmap.ic_launcher);}
        imageLoader.destroy();
        return view;
    }

//    public CustomList(Activity context, String[] web, Integer[] imageId) {
//        super(context, R.layout.list_single, web);
//        this.context = context;
//        this.web = web;
//        this.imageId = imageId;




}
