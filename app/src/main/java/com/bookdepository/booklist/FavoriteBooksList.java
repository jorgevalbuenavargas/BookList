package com.bookdepository.booklist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FavoriteBooksList extends ArrayAdapter<FavoriteBooks> {

    Activity context;
    List<FavoriteBooks> favoriteBooks;

    public FavoriteBooksList(Activity context, int idListItem, List<FavoriteBooks> favoriteBooks) {
        super(context, idListItem, favoriteBooks);
        this.context = context;
        this.favoriteBooks = favoriteBooks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.favorite_books_list, null);

        TextView tittleText = item.findViewById(R.id.favoriteBookTitle);
        TextView authorText = item.findViewById(R.id.favoriteBookAuthor);
        FavoriteBooks book = getItem(position);

        tittleText.setText(book.getTitle());
        authorText.setText(book.getAuthor());

        return item;
    }

}
