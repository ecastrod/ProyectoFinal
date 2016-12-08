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

    public void saveContactsList(final ArrayList<City> contactsList){
        mRealm.executeTransactionAsync(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
                for(City contact : contactsList){
                    City realmContact = realm.createObject(City.class);
                    realmContact.setId(contact.getId());
                    realmContact.setName(contact.getName());
                    realmContact.setEmail(contact.getEmail());
                    realmContact.setTelephone(contact.getTelephone());
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
        ArrayList<City> citiesList = new ArrayList<>(mRealm.where(City.class).findAll());
        return citiesList;
    }

    public boolean areContactsAvailable(){
        return mRealm.where(City.class).count() > 0;
    }

}