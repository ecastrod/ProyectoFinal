package com.example.estebancastro.proyectofinal.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ESTEBAN CASTRO on 11/12/2016.
 */

public class Clouds implements Parcelable {

    public static final Creator<Clouds> CREATOR = new Creator<Clouds>() {
        @Override
        public Clouds createFromParcel(Parcel in) {
            return new Clouds(in);
        }

        @Override
        public Clouds[] newArray(int size) {
            return new Clouds[size];
        }
    };

    protected Clouds(Parcel in) {
        all = in.readInt();
    }


    @SerializedName("all")
    @Expose
    private Integer all;


    /**
     *
     * @return
     *     The all
     */
    public Integer getAll() {
        return all;
    }

    /**
     *
     * @param all
     *     The all
     */
    public void setAll(Integer all) {
        this.all = all;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(all);
    }

}
