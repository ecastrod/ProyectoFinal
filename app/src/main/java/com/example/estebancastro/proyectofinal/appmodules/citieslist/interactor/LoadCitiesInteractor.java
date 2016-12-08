package com.example.estebancastro.proyectofinal.appmodules.citieslist.interactor;

import com.example.estebancastro.proyectofinal.appmodules.citieslist.CitiesContract;
import com.example.estebancastro.proyectofinal.dataaccess.CityDAO;
import com.example.estebancastro.proyectofinal.model.City;

import java.util.ArrayList;

import io.realm.Realm;


/**
 * Created by ESTEBAN CASTRO on 6/12/2016.
 */

public class LoadCitiesInteractor implements CitiesContract.LoadCitiesInteractor {


    private OnCitiesListLoaded mOnCitiesListLoaded;
    private CityDAO mCitiesDao;

    public LoadCitiesInteractor(Realm realm){
        mCitiesDao = new CityDAO(realm);
    }


    @Override
    public void loadCitiesFromDatabase(OnCitiesListLoaded onContactsListLoaded) {
        mOnCitiesListLoaded = onContactsListLoaded;
        ArrayList<City> citiesList = mCitiesDao.getContactsList();

        if(citiesList != null && citiesList.size() > 0)
            mOnCitiesListLoaded.onCitiesListLoadedSuccessful(citiesList);
        else
            mOnCitiesListLoaded.onErrorLoadingCitiesList();
    }
}