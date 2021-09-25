package com.example.madcode.Events.Models;

public class Event_group {
    public String gkey,member,eid,cid;

    Event_group(){}

    public Event_group(String gkey, String member, String eid,String cid) {
        this.gkey = gkey;
        this.member = member;
        this.eid = eid;
        this.cid = cid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getGkey() {
        return gkey;
    }

    public void setGkey(String gkey) {
        this.gkey = gkey;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }
}
