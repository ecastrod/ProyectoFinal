package com.example.estebancastro.proyectofinal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ESTEBAN CASTRO on 11/12/2016.
 */

public class Sys {

    @SerializedName("country")
    @Expose
    private String country;

    /**
     *
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

}
