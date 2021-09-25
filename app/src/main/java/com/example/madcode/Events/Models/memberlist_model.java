package com.example.madcode.Events.Models;

public class memberlist_model {
    String uname,uri;

    public memberlist_model(String uname, String uri) {
        this.uname = uname;
        this.uri = uri;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
