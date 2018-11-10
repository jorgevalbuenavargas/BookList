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

    }

    /*public void getFavorites(View v){
        String url = "http://www.etnassoft.com/api/v1/get/?id=589";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String stringResult) {
                String replaced_stringResult = stringResult.replace("([", "");
                replaced_stringResult = replaced_stringResult.replace("]);", "");
                replaced_stringResult = replaced_stringResult.replace("<strong>", "");
                replaced_stringResult = replaced_stringResult.replace("</strong>;", "");
                Gson newGson = new Gson();
                Books book_profile = newGson.fromJson(replaced_stringResult, Books.class);
                List<Tags> book_tags = book_profile.getTags();
                String tags = "";
                for (int i = 0; i < book_tags.size(); i++){
                    if (!tags.isEmpty()){
                        tags = tags + " ," + book_tags.get(i).getName();
                    } else {
                        tags = book_tags.get(i).getName();
                    }
                }
                Log.i("Llamado", tags);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //Toast.makeText(getBaseContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                Log.i("ErrorLlamado", volleyError.toString());
            }
        });
        AgregarRequest(request);
    }*/
}
