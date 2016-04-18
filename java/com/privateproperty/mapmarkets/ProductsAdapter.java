package com.privateproperty.mapmarkets;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pro-rock on 29.03.2016.
 */
public class ProductsAdapter extends BaseAdapter implements Filterable{
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<ProductItemClass> objects;
    private ArrayList<ProductItemClass> objectsBase;

    ProductsAdapter(Context context, ArrayList<ProductItemClass> products) {
        ctx = context;
        objects = products;
        objectsBase = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public ProductItemClass getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return objects.get(position).getId();
    }

    public ProductItemClass getItem(long id){
        for (int i = 0; i < objectsBase.size(); i++) {
            long d = objectsBase.get(i).getId();
            if (d==id)  {
                return objectsBase.get(i);
            }
        }

        return null;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = lInflater.inflate(R.layout.product_item, parent, false);
            }
        ProductItemClass itemClass = getItem(position);
        TextView txtProduct = (TextView) convertView.findViewById(R.id.txtProduct);
        TextView txtCategory = (TextView) convertView.findViewById(R.id.txtCategory);
        try {
            String name = itemClass.getName();
            txtProduct.setText(name);
            String category = itemClass.getCategory();
            txtCategory.setText(category);

        }
        catch (Exception e){
            Log.e("MM", "Product adapter" + e);

        }
        return convertView;

    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                objects = (ArrayList<ProductItemClass>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<ProductItemClass> FilteredArrayNames = new ArrayList<ProductItemClass>();

                // perform your search here using the searchConstraint String.

                constraint = constraint.toString().toLowerCase();
                for (int i = 0; i < objectsBase.size(); i++) {
                    String dataNames = objectsBase.get(i).getName();
                    if (dataNames.toLowerCase().startsWith(constraint.toString()))  {
                        FilteredArrayNames.add(objectsBase.get(i));
                    }
                }

                results.count = FilteredArrayNames.size();
                results.values = FilteredArrayNames;
//                Log.e("VALUES", results.values.toString());

                return results;
            }
        };

        return filter;
    }
}

