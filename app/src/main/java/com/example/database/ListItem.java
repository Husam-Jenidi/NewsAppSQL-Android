package com.example.database;

public class ListItem {
    private Integer article_id;
    private String title;
    private String category;
    private String date;
    private String content;
    private String src;
    private String image;



    public ListItem(Integer article_id,String title, String category, String date, String content, String src, String image) {
        this.article_id=article_id;
        this.title = title;
        this.category = category;
        this.date = date;
        this.content = content;
        this.src = src;
        this.image = image;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getSrc() {
        return src;
    }

    public String getImage() {
        return image;
    }


}
