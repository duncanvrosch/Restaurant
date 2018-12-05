package com.example.duncan.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intent = getIntent();
        MenuItem selectedItem = (MenuItem) intent.getSerializableExtra("selected_item");

        String title = selectedItem.getName();
        String description = selectedItem.getDescription();
        Long price = selectedItem.getPrice();

        TextView itemTitle = findViewById(R.id.itemTitle);
        TextView itemDescription = findViewById(R.id.itemContent);
        TextView itemPrice = findViewById(R.id.itemPrice);

        itemTitle.setText(title);
        itemDescription.setText(description);
        itemPrice.setText("$ " + String.valueOf(price));

        // picture solving with picasso
        ImageView itemImage = findViewById(R.id.itemImage);
        String url = selectedItem.getImageUrl();
        Picasso.with(this).load(url).fit().into(itemImage);
    }
}