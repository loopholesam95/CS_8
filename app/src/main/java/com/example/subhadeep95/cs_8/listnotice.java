package com.example.subhadeep95.cs_8;

/**
 * Created by Subhadeep95 on 11/30/2016.
 */
public class listnotice {
    private int id;
    private String name;
    private String date;

    //constructor..
    public listnotice(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;

    }
    //Setter, getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

}

