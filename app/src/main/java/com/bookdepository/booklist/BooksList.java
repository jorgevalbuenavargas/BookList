package com.bookdepository.booklist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BooksList extends ArrayAdapter<Books> {

    Activity context;
    List<Books> books;

    public BooksList(Activity context, int idListItem, List<Books> books) {
        super(context, idListItem, books);
        this.context = context;
        this.books = books;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.books_list, null);

        TextView tittleText = item.findViewById(R.id.booksTitle);
        TextView authorText = item.findViewById(R.id.authorsName);

        Books book = getItem(position);

        tittleText.setText(book.getTitle());
        authorText.setText(book.getAuthor());

        return item;
    }
}
