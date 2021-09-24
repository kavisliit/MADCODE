package com.example.madcode.Events.Models;

public class Event {

    String name,type,venue,date,time,cid;
    String uri;
    String max = null;

    public Event(){}

    public Event(String name,String type,String venue,String max,String date,String time,String uri,String cid){
        this.name  = name;
        this.type  = type;
        this.venue  = venue;
        this.max  = max;
        this.date  = date;
        this.time  = time;
        this.uri = uri;
        this.cid = cid;
    }


    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getVenue() {
        return venue;
    }

    public String getMax() {
        return max;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setType(String type) {
        this.type = type;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
