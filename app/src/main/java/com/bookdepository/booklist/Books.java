package com.bookdepository.booklist;

import java.util.List;

public class Books {

    private int ID;
    private String title;
    private String author;
    private String content;
    private String content_short;
    private String publisher;
    private int publisher_date;
    private int pages;
    private String language;
    private String url_details;
    private String url_downloads;
    private String cover;
    private String thumbnail;
    private List<Categories> categories;
    private List<Tags> tags;

    public Books(){}

    public Books(int ID, String title, String author, String content, String content_short, String publisher, int publisher_date, int pages, String language, String url_details, String url_downloads, String cover, String thumbnail, List<Categories> categories, List<Tags> tags) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.content = content;
        this.content_short = content_short;
        this.publisher = publisher;
        this.publisher_date = publisher_date;
        this.pages = pages;
        this.language = language;
        this.url_details = url_details;
        this.url_downloads = url_downloads;
        this.cover = cover;
        this.thumbnail = thumbnail;
        this.categories = categories;
        this.tags = tags;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_short() {
        return content_short;
    }

    public void setContent_short(String content_short) {
        this.content_short = content_short;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublisher_date() {
        return publisher_date;
    }

    public void setPublisher_date(int publisher_date) {
        this.publisher_date = publisher_date;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUrl_details() {
        return url_details;
    }

    public void setUrl_details(String url_details) {
        this.url_details = url_details;
    }

    public String getUrl_downloads() {
        return url_downloads;
    }

    public void setUrl_downloads(String url_downloads) {
        this.url_downloads = url_downloads;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }
}
