package com.example.database;

public class listLike {
    private Integer id;
    private String user_id;
    private String article_id;
    private String is_liked;




    public listLike(Integer id,String user_id, String article_id, String is_liked) {
        this.article_id=article_id;
        this.user_id = user_id;
        this.article_id = article_id;
        this.is_liked = is_liked;

    }

    public Integer getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getArticle_id() {
        return article_id;
    }

    public String getIs_liked() {
        return is_liked;
    }
}
