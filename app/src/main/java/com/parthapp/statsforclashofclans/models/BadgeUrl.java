package com.parthapp.statsforclashofclans.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BadgeUrl {
    @SerializedName("small")
    @Expose
    private String small;

    @SerializedName("large")
    @Expose
    private String large;

    @SerializedName("medium")
    @Expose
    private String medium;

    public String getSmall()
    {
        return small;
    }

    public String getLarge()
    {
        return large;
    }

    public String getMedium()
    {
        return medium;
    }
}
