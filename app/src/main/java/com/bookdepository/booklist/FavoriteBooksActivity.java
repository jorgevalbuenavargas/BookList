package com.bookdepository.booklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavoriteBooksActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<FavoriteBooks> allBooks;
    TextView categoryTitle;
    String book_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_books_activity);

        allBooks = FavoriteBooks.listAll(FavoriteBooks.class);

        categoryTitle = findViewById(R.id.favoriteBooksTitle);
        categoryTitle.setText("Mis favoritos");

        listBooks();
    }


    protected void onResume() {
        super.onResume();
        allBooks = FavoriteBooks.listAll(FavoriteBooks.class);
        listBooks();
    }

    public void regresar(View v) {
        finish();
    }


    public void listBooks() {

        //Llama a la lista favorite_books_list

        FavoriteBooksList adaptador = new FavoriteBooksList(FavoriteBooksActivity.this, R.layout.favorite_books_list, allBooks);

        ListView lstOpciones = (ListView) findViewById(R.id.favoriteBooksList);

        lstOpciones.setAdapter(adaptador);

        lstOpciones.setOnItemClickListener(FavoriteBooksActivity.this);

    }

    public void onItemClick(AdapterView<?> padre, View view, int posicion, long id) {

        FavoriteBooksList adaptador = (FavoriteBooksList) padre.getAdapter();
        FavoriteBooks selected_book = adaptador.getItem(posicion);
        book_id = Integer.toString(selected_book.getBook_id());

        Intent myIntent = new Intent(this, BookProfile.class);
        myIntent.putExtra("book_id", book_id);
        startActivity(myIntent);
    }

    public void deleteBook(View v){
        View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        FavoriteBooks book = allBooks.get(position);
        book.delete();
        allBooks = FavoriteBooks.listAll(FavoriteBooks.class);
        listBooks();
    }

}
