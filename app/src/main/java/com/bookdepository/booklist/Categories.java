package com.bookdepository.booklist;

public class Categories {

    private int category_id;
    private String name;
    private String nicename;

    public Categories(int c_id, String c_name, String c_nicename){
        this.category_id = c_id;
        this.name = c_name;
        this.nicename = c_nicename;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNicename() { return nicename; }

    public void setNicename(String nicename) { this.nicename = nicename; }
}
