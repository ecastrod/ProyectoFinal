package com.example.estebancastro.proyectofinal.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ESTEBAN CASTRO on 11/12/2016.
 */

public class WeatherApiResponse implements Parcelable {

    public static final Creator<WeatherApiResponse> CREATOR = new Creator<WeatherApiResponse>() {
        @Override
        public WeatherApiResponse createFromParcel(Parcel in) {
            return new WeatherApiResponse(in);
        }

        @Override
        public WeatherApiResponse[] newArray(int size) {
            return new WeatherApiResponse[size];
        }
    };

    protected WeatherApiResponse(Parcel in) {
        message = in.readString();
        cod = in.readString();
        count = in.readInt();
        //list = in.readList(<City>);
    }

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("list")
    @Expose
    private List<City> list = null;

    /**
     *
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     *     The cod
     */
    public String getCod() {
        return cod;
    }

    /**
     *
     * @param cod
     *     The cod
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     *
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     *
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     *
     * @return
     *     The list
     */
    public List<City> getList() {
        return list;
    }

    /**
     *
     * @param list
     *     The list
     */
    public void setList(List<City> list) {
        this.list = list;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(message);
        parcel.writeString(cod);
        parcel.writeInt(count);
        parcel.writeList(list);

    }
}
