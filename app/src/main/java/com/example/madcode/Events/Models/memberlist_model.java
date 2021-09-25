package com.example.madcode.Events.Models;

public class memberlist_model {
    String uname,url;

    public memberlist_model(String uname, String url) {
        this.uname = uname;
        this.url = url;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
