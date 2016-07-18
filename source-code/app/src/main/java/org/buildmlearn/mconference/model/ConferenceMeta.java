package org.buildmlearn.mconference.model;

import java.util.Date;

/**
 * Created by jai on 16/7/16.
 */

public class ConferenceMeta {
    private String name;
    private Date date;
    private String venue;
    private String logoURL;
    private String configURL;
    private boolean isFavroite;

    public ConferenceMeta() {
        this.name = null;
        this.date = null;
        this.venue = null;
        this.configURL = null;
        this.isFavroite = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getConfigURL() {
        return configURL;
    }

    public void setConfigURL(String configURL) {
        this.configURL = configURL;
    }

    public boolean isFavroite() {
        return isFavroite;
    }

    public void setFavroite(boolean favroite) {
        isFavroite = favroite;
    }
}
