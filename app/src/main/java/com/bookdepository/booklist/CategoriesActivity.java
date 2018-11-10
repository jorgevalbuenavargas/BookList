package com.bookdepository.booklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public SingletonVolley volley;
    public RequestQueue colaPeticiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_activity);

        volley = SingletonVolley.getInstance(getApplicationContext());
        colaPeticiones = volley.getRequestQueue();

        getCategories();
    }

    public void regresar(View v){
        finish();
    }

    public void AgregarRequest(Request request) {
        if (request != null) {
            request.setTag("REQUEST_LOGIN");
            if (colaPeticiones == null)
                colaPeticiones = volley.getRequestQueue();
            request.setRetryPolicy(new DefaultRetryPolicy(
                    6000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));
            colaPeticiones.add(request);
        }
    }

    public void getCategories(){
        String url = "http://www.etnassoft.com/api/v1/get/?get_categories=all";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String stringResult) {

                // Elimina los caracteres inválidos del json
                String replaced_stringResult = stringResult.replace("(", "");
                replaced_stringResult = replaced_stringResult.replace(");", "");

                // Tranforma el json en una lista de objetos Categories
                Gson newGson = new Gson();
                Type categoryListType = new TypeToken<ArrayList<Categories>>(){}.getType();
                List<Categories> found_categories = newGson.fromJson(replaced_stringResult, categoryListType);

                //Llama a la lista categories_list

                CategoriesList adaptador = new CategoriesList(CategoriesActivity.this, R.layout.categories_list, found_categories);

                ListView lstOpciones = (ListView)findViewById(R.id.categoriesProfileText);

                lstOpciones.setAdapter(adaptador);

                lstOpciones.setOnItemClickListener(CategoriesActivity.this);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //Toast.makeText(getBaseContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                Log.i("ErrorLlamado", volleyError.toString());
            }
        });
        AgregarRequest(request);

    }

    public void onItemClick(AdapterView<?> padre, View view, int posicion, long id) {

        CategoriesList adaptador = (CategoriesList) padre.getAdapter();
        //Crea un objecto Categories al momento de dar clic sobre alguna categoría seleccionada
        Categories selected_category = adaptador.getItem(posicion);
        //Empieza una nueva activity pasando el name y el nicename de la categoría seleccionada
        Intent myIntent = new Intent(this, BooksActivity.class);
        myIntent.putExtra("category_nicename", selected_category.getNicename());
        myIntent.putExtra("category_name", selected_category.getName());
        startActivity(myIntent);
    }
}
