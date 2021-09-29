package com.example.madcode.Article.Model;

public class ArticleModel
{
  String Head_line,Small_description,Sub_topic, Description,purl,CurrentUserId;
    ArticleModel()
    {

    }
    public ArticleModel(String Head_line, String Small_description, String Sub_topic, String Description, String purl, String CurrentUserId) {
        this.Head_line = Head_line;
        this.Small_description = Small_description;
        this.Sub_topic = Sub_topic;
        this.Description = Description;
        this.purl = purl;
        this.CurrentUserId=CurrentUserId;
    }

    public String getHead_line() {
        return Head_line;
    }

    public void setHead_line(String head_line) {
        Head_line = head_line;
    }

    public String getSmall_description() {
        return Small_description;
    }

    public void setSmall_description(String small_description) {
        Small_description = small_description;
    }

    public String getSub_topic() {
        return Sub_topic;
    }

    public void setSub_topic(String sub_topic) {
        Sub_topic = sub_topic;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getCurrentUserId() {
        return CurrentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        CurrentUserId = currentUserId;
    }
}
