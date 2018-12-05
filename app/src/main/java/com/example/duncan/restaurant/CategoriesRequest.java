package com.example.duncan.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Context context;
    Callback activity;
    ArrayList categoryList = new ArrayList<String>();

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    public void getCategories(Callback activity) {

        this.activity = activity;

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String errorMessage = error.getMessage();
        activity.gotCategoriesError(errorMessage);
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray categories = new JSONArray();
        try {
            categories = response.getJSONArray("categories");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < categories.length(); i++) {
            try {
                categoryList.add(categories.getString(i));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        activity.gotCategories(categoryList);
    }
}