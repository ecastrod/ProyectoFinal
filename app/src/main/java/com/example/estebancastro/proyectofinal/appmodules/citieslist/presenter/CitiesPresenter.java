package com.example.estebancastro.proyectofinal.appmodules.citieslist.presenter;

import android.content.Context;

import com.example.estebancastro.proyectofinal.R;
import com.example.estebancastro.proyectofinal.appmodules.citieslist.CitiesContract;
import com.example.estebancastro.proyectofinal.appmodules.citieslist.interactor.GenerateCitiesInteractor;
import com.example.estebancastro.proyectofinal.appmodules.citieslist.interactor.LoadCitiesInteractor;
import com.example.estebancastro.proyectofinal.model.City;

import java.util.ArrayList;

/**
 * Created by ESTEBAN CASTRO on 7/12/2016.
 */

public class CitiesPresenter implements CitiesContract.Presenter, CitiesContract.LoadCitiesInteractor.OnCitiesListLoaded {

    private Context mContext;
    private CitiesContract.View mCitiesView;
    private LoadCitiesInteractor mLoadCitiesInteractor;
    private GenerateCitiesInteractor mGenerateCitiesInteractor;



    public CitiesPresenter(Context context, CitiesContract.View citiesView,
                             LoadCitiesInteractor loadCitiesInteractor,
                             GenerateCitiesInteractor generateCitiesInteractor){
        mContext = context;
        mCitiesView = citiesView;
        mLoadCitiesInteractor = loadCitiesInteractor;
        mGenerateCitiesInteractor = generateCitiesInteractor;
    }


    @Override
    public void generateRandomCities() {
        mGenerateCitiesInteractor.generateCitiesInDatabase();
    }

    @Override
    public void loadCities() {
        mLoadCitiesInteractor.loadCitiesFromDatabase(this);
    }

    @Override
    public void onCitiesListLoadedSuccessful(ArrayList<City> citiesList) {
        mCitiesView.hideSwipeRefreshLayout();
        mCitiesView.showCitiesList(citiesList);
    }

    @Override
    public void onErrorLoadingCitiesList() {
        mCitiesView.showErrorLoadingCitiesToast(mContext.getString(R.string.contacts_list_error_loading_contacts_msg));
    }
}