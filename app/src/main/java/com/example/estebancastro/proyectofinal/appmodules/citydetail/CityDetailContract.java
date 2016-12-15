package com.example.estebancastro.proyectofinal.appmodules.citydetail;

import com.example.estebancastro.proyectofinal.model.City;

/**
 * Created by ESTEBAN CASTRO on 12/12/2016.
 */

public interface CityDetailContract {

    interface View{
        void showCityInfo(String name, String description, String temperature, String humedity, String wind);
    }

    interface Presenter{
        void loadCityInfo(City contact);
    }

}
