package com.sekrab.paginationwithsearch;

import com.google.gson.annotations.SerializedName;

public class modelpojo {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public modelpojo(String id, String name, String profile_picture) {
        this.id = id;
        this.name = name;
        this.profile_picture = profile_picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    @SerializedName("profile_picture")
    private String profile_picture;
  @SerializedName("latitude")
    private String latitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @SerializedName("longitude")
    private String longitude;

}
