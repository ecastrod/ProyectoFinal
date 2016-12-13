package com.example.estebancastro.proyectofinal.appmodules.citydetail;

import com.example.estebancastro.proyectofinal.model.City;

/**
 * Created by ESTEBAN CASTRO on 12/12/2016.
 */

public interface CityDetailContract {

    interface View{
        void showCityInfo(String name, String email, String telephone);
    }

    interface Presenter{
        void loadCityInfo(City contact);
    }

}
