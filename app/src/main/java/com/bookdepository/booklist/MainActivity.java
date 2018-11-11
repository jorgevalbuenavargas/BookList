package com.bookdepository.booklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button categoriesButton;
    Button favoritesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoriesButton = findViewById(R.id.categoriesButton);
        favoritesButton = findViewById(R.id.favoritesButton);
    }



    public void getCategories(View v){
        Intent myIntent = new Intent(MainActivity.this, CategoriesActivity.class);
        startActivity(myIntent);
    }

    public void getFavorites(View v){
        Intent myIntent = new Intent(MainActivity.this, FavoriteBooksActivity.class);
        startActivity(myIntent);
    }
}
