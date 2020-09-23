package com.example.comicsprime.Model;

import java.util.ArrayList;

public class Comic {

    private String title;
    private String volume;
    private String issue;
    private String comicName;
    private boolean isPartOfEvent;
    private String eventName;

    public Comic(String title, String volume, String issue, boolean isPartOfEvent){
        this.title = title;
        this.volume = volume;
        this.issue = issue;
        this.comicName = title + " Volume " + volume + " Issue " + issue;
        this.isPartOfEvent = isPartOfEvent;
    }

    public Comic(String title, String volume, String issue, boolean isPartOfEvent, String eventName){
        this.title = title;
        this.volume = volume;
        this.issue = issue;
        this.comicName = title + " Volume " + volume + " Issue " + issue;
        this.eventName = eventName;
        this.isPartOfEvent = isPartOfEvent;
    }

    public Comic(){

    }

    public boolean isPartOfEvent() {
        return isPartOfEvent;
    }

    public void setPartOfEvent(boolean partOfEvent) {
        isPartOfEvent = partOfEvent;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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
