package com.example.estebancastro.proyectofinal.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ESTEBAN CASTRO on 5/12/2016.
 */

public class City implements Parcelable {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("weather")
    @Expose
    private java.util.List<Weather> weather = null;

    //Variables para clima.
    private String coordStr;
    private String temperatureStr;
    private String humidityStr;
    private String windStr;


    public static class Fields{
        public static final String ID = "id";
        public static final String NAME = "name";

        public static final String COORDSTR = "coordStr";
        public static final String TEMPERATURESTR = "temperatureStr";
        public static final String HUMIDITYSTR = "humidityStr";
        public static final String WINDSTR = "windStr";

    }


    /**
     *
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The coord
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     *
     * @param coord
     *     The coord
     */
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    /**
     *
     * @return
     *     The main
     */
    public Main getMain() {
        return main;
    }

    /**
     *
     * @param main
     *     The main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     *
     * @return
     *     The dt
     */
    public Integer getDt() {
        return dt;
    }

    /**
     *
     * @param dt
     *     The dt
     */
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    /**
     *
     * @return
     *     The wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     *
     * @param wind
     *     The wind
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     *
     * @return
     *     The sys
     */
    public Sys getSys() {
        return sys;
    }

    /**
     *
     * @param sys
     *     The sys
     */
    public void setSys(Sys sys) {
        this.sys = sys;
    }

    /**
     *
     * @return
     *     The clouds
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     *
     * @param clouds
     *     The clouds
     */
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     *
     * @return
     *     The weather
     */
    public java.util.List<Weather> getWeather() {
        return weather;
    }

    /**
     *
     * @param weather
     *     The weather
     */
    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public String getCoordStr() {
        return coord.toString();
    }
    public void setCoordStr(String coordStr) {
        this.coordStr = coordStr;
    }

    public String getTemperatureStr() {
        return temperatureStr.toString();
    }
    public void setTemperatureStr(String temperatureStr) {
        this.temperatureStr = temperatureStr;
    }



    // Para Realm

    //protected City(Parcel in) {
    //    id = in.readInt();
    //    name = in.readString();
    //    coordStr = in.readString();
    //    temperatureStr = in.readString();
    //    humidityStr = in.readString();
    //    windStr = in.readString();
    //}

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    //@Override
    //public int describeContents() {
    //    return 0;
    //}

    //@Override
    //public void writeToParcel(Parcel parcel, int i) {
    //    parcel.writeInt(id);
    //    parcel.writeString(name);

    //    parcel.writeString(coordStr);
    //    parcel.writeString(temperatureStr);
    //    parcel.writeString(humidityStr);
    //    parcel.writeString(windStr);

        //parcel.writeString(telephone);
        //parcel.writeString(email);
    //}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeParcelable(coord, i);
        parcel.writeParcelable(main, i);
        parcel.writeInt(dt);
        parcel.writeParcelable(wind, i);
        parcel.writeParcelable(sys, i);
        parcel.writeParcelable(clouds, i);
    }

    protected City(Parcel in) {
        id = in.readInt();
        name = in.readString();
        coord = in.readParcelable(Coord.class.getClassLoader());
        main = in.readParcelable(Main.class.getClassLoader());
        dt = in.readInt();
        wind = in.readParcelable(Wind.class.getClassLoader());
        sys = in.readParcelable(Sys.class.getClassLoader());
        clouds = in.readParcelable(Clouds.class.getClassLoader());
    }

}
