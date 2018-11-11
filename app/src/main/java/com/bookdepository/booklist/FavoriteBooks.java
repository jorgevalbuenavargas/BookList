package com.bookdepository.booklist;

import com.orm.SugarRecord;

public class FavoriteBooks extends SugarRecord<FavoriteBooks> {

    int book_id;
    String title;
    String author;

    public FavoriteBooks(){ }

    public FavoriteBooks(int book_id, String title, String author) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
