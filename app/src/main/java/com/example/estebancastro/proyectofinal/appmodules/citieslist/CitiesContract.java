package com.example.estebancastro.proyectofinal.appmodules.citieslist;

import com.example.estebancastro.proyectofinal.model.City;
import java.util.ArrayList;

/**
 * Created by ESTEBAN CASTRO on 7/12/2016.
 */

public interface CitiesContract {

    interface View{
        void hideSwipeRefreshLayout();
        void showErrorLoadingCitiesToast(String errorMessage);
        void showCitiesList(ArrayList<City> citiesList);
    }

    interface Presenter{
        void generateRandomCities();
        void loadCities();
    }

    interface GenerateCitiesInteractor{
        void generateCitiesInDatabase();
    }

    interface LoadCitiesInteractor{

        interface OnCitiesListLoaded{
            void onCitiesListLoadedSuccessful(ArrayList<City> citiesList);
            void onErrorLoadingCitiesList();
        }

        void loadCitiesFromDatabase(OnCitiesListLoaded onCitiesListLoaded);
    }

}