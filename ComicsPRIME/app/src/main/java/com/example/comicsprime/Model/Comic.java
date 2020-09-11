package com.example.comicsprime.Model;

import java.util.ArrayList;

public class Comic {

    private String title;
    private String volume;
    private String issue;
    private String comicName;

    public Comic(String title, String volume, String issue){
        this.title = title;
        this.volume = volume;
        this.issue = issue;
        this.comicName = title + " Volume " + volume + " Issue " + issue;
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
}
