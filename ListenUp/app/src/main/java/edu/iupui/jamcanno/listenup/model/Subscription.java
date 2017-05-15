package edu.iupui.jamcanno.listenup.model;

/**
 * Created by Eric Smith on 5/1/2017.
 */

public class Subscription {

    public String title;
    public String website;
    public int subscribers;
    public int subscribers_last_week;
    public String mygpo_link;
    public String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    public int getSubscribers_last_week() {
        return subscribers_last_week;
    }

    public void setSubscribers_last_week(int subscribers_last_week) {
        this.subscribers_last_week = subscribers_last_week;
    }

    public String getMygpo_link() {
        return mygpo_link;
    }

    public void setMygpo_link(String mygpo_link) {
        this.mygpo_link = mygpo_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}



