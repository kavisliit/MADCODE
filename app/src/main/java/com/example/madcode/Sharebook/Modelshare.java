package com.example.madcode.Sharebook;

public class Modelshare{

    String sbookname, sbookcategory, sbookauthor, sbookdes, surl,ShareBookUserId;

    Modelshare(){

    }

    public Modelshare(String sbookname, String sbookcategory, String sbookauthor, String sbookdes, String surl,String ShareBookUserId) {
        this.sbookname = sbookname;
        this.sbookcategory = sbookcategory;
        this.sbookauthor = sbookauthor;
        this.sbookdes = sbookdes;
        this.surl = surl;
        this.ShareBookUserId=ShareBookUserId;
    }

    public String getSbookname() {
        return sbookname;
    }

    public void setSbookname(String sbookname) {
        this.sbookname = sbookname;
    }

    public String getSbookcategory() {
        return sbookcategory;
    }

    public void setSbookcategory(String sbookcategory) {
        this.sbookcategory = sbookcategory;
    }

    public String getSbookauthor() {
        return sbookauthor;
    }

    public void setSbookauthor(String sbookauthor) {
        this.sbookauthor = sbookauthor;
    }

    public String getSbookdes() {
        return sbookdes;
    }

    public void setSbookdes(String sbookdes) {
        this.sbookdes = sbookdes;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }

    public String getShareBookUserId() {
        return ShareBookUserId;
    }

    public void setShareBookUserId(String shareBookUserId) {
        ShareBookUserId = shareBookUserId;
    }
}