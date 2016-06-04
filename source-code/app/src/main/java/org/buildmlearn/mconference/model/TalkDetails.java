package org.buildmlearn.mconference.model;

/**
 * Created by jai on 4/6/16.
 */
public class TalkDetails {
    private String name;
    private String imageURL;
    private String time;
    private String location;
    private String desc;
    private String shortDesc;

    public TalkDetails(String name, String imageURL, String time, String location, String desc) {
        this.name = name;
        this.imageURL = imageURL;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc() {
        String[] splitDesc = desc.split(" ", 7);
        shortDesc = "";

        for (int i=0; i<6 && i<desc.length(); i++)
            shortDesc += splitDesc[i] + " ";
    }
}
