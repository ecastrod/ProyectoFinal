package com.example.estebancastro.proyectofinal.appmodules.citieslist.interactor;

import com.example.estebancastro.proyectofinal.appmodules.citieslist.CitiesContract;
import com.example.estebancastro.proyectofinal.dataaccess.CityDAO;
import com.example.estebancastro.proyectofinal.model.City;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by ESTEBAN CASTRO on 6/12/2016.
 */

public class GenerateCitiesInteractor implements CitiesContract.GenerateCitiesInteractor {

    private CityDAO mCityDao;

    public GenerateCitiesInteractor(Realm realm){
        mCityDao = new CityDAO(realm);
    }

    @Override
    public void generateCitiesInDatabase() {
        if(!mCityDao.areCitiesAvailable())
            mCityDao.saveCitiesList(generateRandomCitiesList());
    }

    private ArrayList<City> generateRandomCitiesList(){
        ArrayList<City> citiesList = new ArrayList<>();

        for(int counter = 0; counter < 10; counter++){
            //Por WS
            //City city = new City(counter, "Name City " + counter, "San Jose", "Id " + counter);
            //citiesList.add(city);
        }

        return citiesList;
    }

}
