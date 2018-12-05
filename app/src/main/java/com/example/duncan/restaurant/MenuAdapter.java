package com.example.duncan.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    ArrayList<MenuItem> listItems;

    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.listItems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate (R.layout.menu_columns, parent, false);
        }

        TextView rowTitle = convertView.findViewById(R.id.columnTitle);
        TextView rowPrice = convertView.findViewById(R.id.columnPrice);

        String title = listItems.get(position).getName();
        Long price = listItems.get(position).getPrice();

        rowTitle.setText(title);
        rowPrice.setText("$ " + String.valueOf(price));

        // set the image with picasso
        String url = listItems.get(position).getImageUrl();
        ImageView columnImage = convertView.findViewById(R.id.columnImage);
        Picasso.with(getContext()).load(url).fit().into(columnImage);

        return convertView;
    }
}