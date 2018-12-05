package com.example.duncan.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback{

    ArrayList<MenuItem> allItems;

    private class SelectItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MenuItem item = (MenuItem) parent.getItemAtPosition(position);

            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("selected_item", item);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ListView lv = findViewById(R.id.menu_lv);
        lv.setOnItemClickListener(new MenuActivity.SelectItemClickListener());

        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        MenuItemsRequest request = new MenuItemsRequest(this);
        request.getMenuItems(this, category);

    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> categories) {
        ListView listview = findViewById(R.id.menu_lv);

        allItems = categories;

        MenuAdapter adapter = new MenuAdapter(this, R.layout.menu_columns, allItems);
        listview.setAdapter(adapter);
    }

    @Override
    public void gotMenuItemsError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

}