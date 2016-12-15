package com.example.estebancastro.proyectofinal.appmodules.citydetail.presenter;

/**
 * Created by ESTEBAN CASTRO on 12/12/2016.
 */

import com.example.estebancastro.proyectofinal.appmodules.citydetail.CityDetailContract;
import com.example.estebancastro.proyectofinal.model.City;

public class CityDetailPresenter implements CityDetailContract.Presenter{

    private CityDetailContract.View mCityDetailView;

    public CityDetailPresenter(CityDetailContract.View cityDetailView){
        mCityDetailView = cityDetailView;
    }

    @Override
    public void loadCityInfo(City city) {
        mCityDetailView.showCityInfo(city.getName(), city.getCoord().getLat().toString() + " " + city.getCoord().getLon().toString(), city.getMain().getTemp().toString(), city.getMain().getHumidity().toString(), city.getWind().getSpeed().toString());
        //mCityDetailView.showCityInfo(city.getName(), "Coord VALUE", "Temperature VALUE", "Humedity VALUE", "Wind VALUIE");
    }

}
