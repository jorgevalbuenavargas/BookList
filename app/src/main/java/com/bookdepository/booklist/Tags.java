package com.bookdepository.booklist;

public class Tags {

    private int tag_id;
    private String name;
    private String nicename;

    public Tags(int t_id, String c_name, String c_nicename){
        this.tag_id = t_id;
        this.name = c_name;
        this.nicename = c_nicename;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
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
