package com.example.estebancastro.proyectofinal.appmodules.citieslist.interactor;

import java.util.ArrayList;
import com.example.estebancastro.proyectofinal.model.City;

/**
 * Created by ESTEBAN CASTRO on 6/12/2016.
 */

public class GenerateCitiesInteractor {


    private ArrayList<City> generateRandomContactsList(){
        ArrayList<City> contactsList = new ArrayList<>();

        for(int counter = 0; counter < 10; counter++){
            City contact = new City(counter, "Name " + counter, "234-234", "name" + counter + "@gmail.com");
            contactsList.add(contact);
        }

        return contactsList;
    }

}
