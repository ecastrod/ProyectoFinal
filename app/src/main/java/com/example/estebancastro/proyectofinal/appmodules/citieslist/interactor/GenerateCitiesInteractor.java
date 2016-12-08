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

    private CityDAO mContactDao;

    public GenerateCitiesInteractor(Realm realm){
        mContactDao = new CityDAO(realm);
    }

    @Override
    public void generateCitiesInDatabase() {
        if(!mContactDao.areContactsAvailable())
            mContactDao.saveContactsList(generateRandomCitiesList());
    }

    private ArrayList<City> generateRandomCitiesList(){
        ArrayList<City> citiesList = new ArrayList<>();

        for(int counter = 0; counter < 10; counter++){
            City city = new City(counter, "Name " + counter, "City234-234", "name" + counter + "City@gmail.com");
            citiesList.add(city);
        }

        return citiesList;
    }

}
