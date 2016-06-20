package org.buildmlearn.mconference.model;

/**
 * Created by jai on 2/6/16.
 */
public class SponsorDetails {
    private String name;
    private String logoURL;

    public SponsorDetails() {
        this.name = null;
        this.logoURL = null;
    }

    public SponsorDetails(String name, String logoURL) {
        this.name = name;
        this.logoURL = logoURL;
    }

    public String getName() {
        return name;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }
}
