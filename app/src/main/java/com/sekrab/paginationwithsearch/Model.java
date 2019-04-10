package com.sekrab.paginationwithsearch;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Model {
    private float status;
    private String message;

   @SerializedName("activities")
   ArrayList<modelpojo> modelpojos;

    public ArrayList<modelpojo> getModelpojos() {
        return modelpojos;
    }

    public void setModelpojos(ArrayList<modelpojo> modelpojos) {
        this.modelpojos = modelpojos;
    }
// Getter Methods

    public float getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    // Setter Methods

    public void setStatus( float status ) {
        this.status = status;
    }

    public void setMessage( String message ) {
        this.message = message;
    }
}