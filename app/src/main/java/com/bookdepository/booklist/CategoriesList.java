package com.bookdepository.booklist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.Response;

import java.util.List;

public class CategoriesList extends ArrayAdapter<Categories> {

    Activity context;
    List<Categories> categories;

    public CategoriesList(Activity context, int idListItem, List<Categories> categories) {
        super(context, idListItem, categories);
        this.context = context;
        this.categories = categories;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.categories_list, null);

        TextView nameText = item.findViewById(R.id.categoryName);

        Categories category = getItem(position);

        nameText.setText(category.getName());

        return item;
    }
}
