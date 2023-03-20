package com.example.s_newsprototype2;

public class ViewStructure {

    private String Image;
    private String Title;
    private String Date;
    private  String Info;
    private String ArticleButton;

    ViewStructure(String Image, String Title, String Date, String Info, String ArticleButton)
    {
        this.Image=Image;
        this.Title=Title;
        this.Date=Date;
        this.Info=Info;
        this.ArticleButton=ArticleButton;
    }
    public String getImage() {
        return Image;
    }

    public String getTitle() {
        return Title;
    }

    public String getDate() {
        return Date;
    }

    public String getInfo() {
        return Info;
    }

    public String getArticleButton() {
        return ArticleButton;
    }
}
