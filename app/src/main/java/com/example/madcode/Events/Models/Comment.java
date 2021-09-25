package com.example.madcode.Events.Models;

public class Comment {
    String oid,comment,comid,oname,ouri;

    Comment(){}

    public Comment(String oid, String comment, String comid, String oname, String ouri) {
        this.oid = oid;
        this.comment = comment;
        this.comid = comid;
        this.oname = oname;
        this.ouri = ouri;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getOuri() {
        return ouri;
    }

    public void setOuri(String ouri) {
        this.ouri = ouri;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComid() {
        return comid;
    }

    public void setComid(String comid) {
        this.comid = comid;
    }
}
