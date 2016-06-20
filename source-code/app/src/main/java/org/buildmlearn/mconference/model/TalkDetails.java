package org.buildmlearn.mconference.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jai on 4/6/16.
 */
public class TalkDetails implements Serializable{
    private String name;
    private String imageURL;
    private Date startTime;
    private Date endTime;
    private String location;
    private String desc;
    private String shortDesc;

    public TalkDetails () {
        this.name = null;
        this.imageURL = null;
        this.startTime = null;
        this.endTime = null;
        this.location = null;
        this.desc = null;
        this.shortDesc = null;
    }

    public TalkDetails(String name, String imageURL, Date startTime, Date endTime, String location, String desc) {
        this.name = name;
        this.imageURL = imageURL;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.desc = desc;
        setShortDesc();
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
        setShortDesc();
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc() {
        String[] splitDesc = desc.split(" ", 7);
        shortDesc = "";

        for (int i=0; i<6 && i<desc.length(); i++)
            shortDesc += splitDesc[i] + " ";

        shortDesc += "...";
    }
}
