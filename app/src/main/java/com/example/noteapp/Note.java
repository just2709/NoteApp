package com.example.noteapp;

import java.io.Serializable;

public class Note implements Serializable {

    private int Id;
    private String Image;
    private String Title;
    private String subTitle;
    private String Content;
    private String DateTime;
    private String Color;
    private String WebLink;
    public Note(int id, String image, String title, String subTitle, String content, String dateTime, String color, String webLink) {
        Id = id;
        Image = image;
        Title = title;
        this.subTitle = subTitle;
        Content = content;
        DateTime = dateTime;
        Color = color;
        WebLink = webLink;
    }

    public Note(String image, String title, String subTitle, String content, String dateTime, String color, String webLink) {
//        Id = id;
        Image = image;
        Title = title;
        this.subTitle = subTitle;
        Content = content;
        DateTime = dateTime;
        Color = color;
        WebLink = webLink;
    }



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getWebLink() {
        return WebLink;
    }

    public void setWebLink(String webLink) {
        WebLink = webLink;
    }

}
