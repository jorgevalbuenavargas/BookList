package com.bookdepository.booklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public SingletonVolley volley;
    public RequestQueue colaPeticiones;
    TextView categoryTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        volley = SingletonVolley.getInstance(getApplicationContext());
        colaPeticiones = volley.getRequestQueue();

        //Obtiene el name y el nicename de la categoría seleccionada en la previa activity
        Intent myIntent = getIntent();
        String cat_nicename = myIntent.getStringExtra("category_nicename");
        String cat_name = myIntent.getStringExtra("category_name");
        categoryTitle = findViewById(R.id.booksTitle);
        categoryTitle.setText(cat_name);
        getBooksByCategory(cat_nicename);
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

    public void getBooksByCategory(String cat_id){
        String url = "http://www.etnassoft.com/api/v1/get/?category="+cat_id+"&criteria=most_viewed";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String stringResult) {

                // Elimina los caracteres inválidos del json
                String replaced_stringResult = stringResult.replace("(", "");
                replaced_stringResult = replaced_stringResult.replace(");", "");

                // Tranforma el json en una lista de objetos Books
                Gson newGson = new Gson();
                Type bookListType = new TypeToken<ArrayList<Books>>(){}.getType();
                List<Books> found_books = newGson.fromJson(replaced_stringResult, bookListType);

                //Llama a la lista books_list

                BooksList adaptador = new BooksList(BooksActivity.this, R.layout.books_list, found_books);

                ListView lstOpciones = (ListView)findViewById(R.id.booksList);

                lstOpciones.setAdapter(adaptador);

                lstOpciones.setOnItemClickListener(BooksActivity.this);

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

        BooksList adaptador = (BooksList) padre.getAdapter();
        Books selected_book = adaptador.getItem(posicion);
        String book_id = Integer.toString(selected_book.getID());
        Intent myIntent = new Intent(this, BookProfile.class);
        myIntent.putExtra("book_id", book_id);
        startActivity(myIntent);

    }
}
