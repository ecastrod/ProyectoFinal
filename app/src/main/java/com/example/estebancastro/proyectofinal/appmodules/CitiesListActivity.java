package com.example.estebancastro.proyectofinal.appmodules;

/**
 * Created by ESTEBAN CASTRO on 5/12/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.estebancastro.proyectofinal.model.City;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.estebancastro.proyectofinal.R;



public class CitiesListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ArrayList<City> mCitiesList;

    public static final String CONTACT_ID_KEY = "CONTACT_ID_KEY";
    @BindView(R.id.contacts_recycler_view) RecyclerView mContactsRecyclerView;
    @BindView(R.id.contacts_swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLayoutManager;


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }



    /* METODOS PRESENTER */


    public void generateRandomContacts() {
        mGenerateContactsInteractor.generateContactsInDatabase();
    }

}


