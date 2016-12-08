package com.example.estebancastro.proyectofinal.model;


import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;


/**
 * Created by ESTEBAN CASTRO on 5/12/2016.
 */

public class City extends RealmObject implements Parcelable{

    public static class Fields{
        public static final String ID = "id";
        public static final String NAME = "name";
    }

    private int id;
    private String name;
    private String telephone;
    private String email;

    public City(){}

    public City(int id, String name, String telephone, String email){
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    protected City(Parcel in) {
        id = in.readInt();
        name = in.readString();
        telephone = in.readString();
        email = in.readString();
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(telephone);
        parcel.writeString(email);
    }
}
