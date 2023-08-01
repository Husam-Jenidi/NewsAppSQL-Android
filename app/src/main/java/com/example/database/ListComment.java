package com.example.database;

public class ListComment {
    private String title1;
    private String user;
    private String comment;
    private String img_url;

    public ListComment(String title1, String user, String comment,String img_url){
        this.title1=title1;
        this.user=user;
        this.comment=comment;
        this.img_url=img_url;
    }
    public String getTitle1() {
        return title1;
    }

    public String getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    public String getImg_url() {
        return img_url;
    }
}
