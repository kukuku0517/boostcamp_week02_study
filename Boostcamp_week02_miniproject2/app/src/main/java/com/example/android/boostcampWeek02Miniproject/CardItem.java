package com.example.android.boostcampWeek02Miniproject;

import java.util.Date;

/**
 * Created by samsung on 2017-07-13.
 */


class CardItem {
    public CardItem(){

    }
    public CardItem(String image, String title, String content, boolean isClicked, int distance, int likes, Date date) {
        this.image = image;
        this.title = title;
        this.content = content;
        this.isClicked = isClicked;
        this.distance = distance;
        this.likes = likes;
        this.date = date;
    }

    private String image, title, content;
    private int distance, likes;
    private boolean isClicked;
    private Date date;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getIsClicked() {
        return isClicked;
    }

    public void setIsClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}