package com.example.estebancastro.proyectofinal.dataaccess;

import com.example.estebancastro.proyectofinal.model.City;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by ESTEBAN CASTRO on 7/12/2016.
 */

public class CityDAO {

    private Realm mRealm;

    public CityDAO(Realm realm){
        mRealm = realm;
    }

    public void saveCitiesList(final ArrayList<City> citiesList){
        mRealm.executeTransactionAsync(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
                for(City city : citiesList){
                    //Por WS
                    //City realmCity = realm.createObject(City.class);
                    //realmCity.setId(city.getId());
                    //realmCity.setName(city.getName());
                    //realmCity.setEmail(city.getEmail());
                    //realmCity.setTelephone(city.getTelephone());

                    City realmCity = realm.createObject(null);
                    realmCity.setId(city.getId());
                    realmCity.setName(city.getName());
                    //realmCity.setEmail("EC");
                    //realmCity.setTelephone(city.getTelephone());

                }
            }
        }, new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess() {

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

            }
        });
    }

    public ArrayList<City> getContactsList(){
        //Por WS
        //ArrayList<City> citiesList = new ArrayList<>(mRealm.where(City.class).findAll());
        ArrayList<City> citiesList = new ArrayList<>();
        return citiesList;
    }

    public boolean areCitiesAvailable(){
        //return mRealm.where(City.class).count() > 0;
        return true;
    }

}