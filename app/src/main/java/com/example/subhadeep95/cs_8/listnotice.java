package com.example.subhadeep95.cs_8;

public class listnotice
{

    private String name,date,notice,url;

    public listnotice() {
    }

    public listnotice(String name, String date, String notice,String url) {
        this.name = name;
        this.date = date;
        this.notice = notice;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getdate() {return date;}

    public void setdate(String date) {this.date = date;}

    public String getnotice() {return notice;}

    public void setnotice(String notice) {this.notice = notice;}

    public String getUrl() {return url;}

    public void setUrl(String url) {
        this.url  = url;
    }

}

