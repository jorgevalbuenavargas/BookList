package com.bookdepository.booklist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Alumno on 9/11/2018.
 */

public class BookProfile extends AppCompatActivity {
    public SingletonVolley volley;
    public RequestQueue colaPeticiones;
    TextView title;
    TextView author;
    TextView publisher;
    TextView publisherDate;
    TextView pages;
    TextView language;
    TextView stringCategories;
    TextView stringTags;
    TextView bookContent;
    ImageView bookPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_profile);

        volley = SingletonVolley.getInstance(getApplicationContext());
        colaPeticiones = volley.getRequestQueue();

        Intent myIntent = getIntent();
        String book_id = myIntent.getStringExtra("book_id");

        getBook(book_id);

        title = findViewById(R.id.bookProfileTitle);
        author = findViewById(R.id.authorProfileText);
        publisher = findViewById(R.id.publisherProfileText);
        publisherDate = findViewById(R.id.publisherDateProfileText);
        pages = findViewById(R.id.pagesProfileText);
        language = findViewById(R.id.languageProfileText);
        stringCategories = findViewById(R.id.categoriesProfileText);
        stringTags = findViewById(R.id.tagsProfileText);
        bookPicture = findViewById(R.id.imageProfile);
        bookContent = findViewById(R.id.contentProfileText);
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

    public void getBook(final String book_id){
        String url = "http://www.etnassoft.com/api/v1/get/?id=" + book_id;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String stringResult) {
                String replaced_stringResult = stringResult.replace("([", "");
                replaced_stringResult = replaced_stringResult.replace("]);", "");
                /*replaced_stringResult = replaced_stringResult.replace("<strong>", "");
                replaced_stringResult = replaced_stringResult.replace("</strong>;", "");
                replaced_stringResult = replaced_stringResult.replace("<li>", "");
                replaced_stringResult = replaced_stringResult.replace("</li>;", "");
                replaced_stringResult = replaced_stringResult.replace("<ul>", "");
                replaced_stringResult = replaced_stringResult.replace("</ul>;", "");*/
                Gson newGson = new Gson();
                Books book_profile = newGson.fromJson(replaced_stringResult, Books.class);

                List<Tags> book_tags = book_profile.getTags();
                String tags = "";
                for (int i = 0; i < book_tags.size(); i++){
                    if (!tags.isEmpty()){
                        tags = tags + ", " + book_tags.get(i).getName();
                    } else {
                        tags = book_tags.get(i).getName();
                    }
                }

                List<Categories> book_categories = book_profile.getCategories();
                String categories = "";
                for (int i = 0; i < book_categories.size(); i++){
                    if (!categories.isEmpty()){
                        categories = categories + ", " + book_categories.get(i).getName();
                    } else {
                        categories = book_categories.get(i).getName();
                    }
                }


                title.setText(book_profile.getTitle());
                author.setText(book_profile.getAuthor());
                publisher.setText(book_profile.getPublisher());
                publisherDate.setText("Año de publicación: " + Integer.toString(book_profile.getPublisher_date()));
                pages.setText("Cantidad de páginas: " + Integer.toString(book_profile.getPages()));
                language.setText("Idioma: " + book_profile.getLanguage());
                stringCategories.setText("Categorías: " + categories);
                stringTags.setText("Tags: " + tags);
                bookContent.setText(book_profile.getContent_short());
                new DownloadImageTask
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

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
