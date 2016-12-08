package com.example.estebancastro.proyectofinal.appmodules.citieslist.view;

/**
 * Created by ESTEBAN CASTRO on 5/12/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.estebancastro.proyectofinal.R;

import com.example.estebancastro.proyectofinal.appmodules.citieslist.CitiesContract;
import com.example.estebancastro.proyectofinal.appmodules.citieslist.adapter.CitiesListAdapter;
//import com.example.estebancastro.proyectofinal.appmodules.citydetail.view.ContactDetailActivity;
import com.example.estebancastro.proyectofinal.appmodules.citieslist.interactor.GenerateCitiesInteractor;
import com.example.estebancastro.proyectofinal.appmodules.citieslist.interactor.LoadCitiesInteractor;
import com.example.estebancastro.proyectofinal.appmodules.citieslist.presenter.CitiesPresenter;
import com.example.estebancastro.proyectofinal.appmodules.citydetail.CitiesDetailActivity;
import com.example.estebancastro.proyectofinal.model.City;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;



class CitiesActivity extends AppCompatActivity implements CitiesContract.View {

    public static final String CONTACT_ID_KEY = "CONTACT_ID_KEY";
    @BindView(R.id.contacts_recycler_view) RecyclerView mContactsRecyclerView;
    @BindView(R.id.contacts_swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLayoutManager;
    private CitiesListAdapter mCitiesListAdapter;
    private CitiesPresenter mCitiesPresenter;
    private LoadCitiesInteractor mLoadCitiesInteractor;
    private GenerateCitiesInteractor mGenerateCitiesInteractor;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_list);
        ButterKnife.bind(this);
        mRealm = Realm.getDefaultInstance();
        initPresenter();
        initContactsRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initPresenter(){
        mLoadCitiesInteractor = new LoadCitiesInteractor(mRealm);
        mGenerateCitiesInteractor = new GenerateCitiesInteractor(mRealm);
        //mCitiesPresenter = new CitiesPresenter(getApplicationContext(), this, mLoadCitiesInteractor, mGenerateCitiesInteractor);
        mCitiesPresenter.generateRandomCities();
    }

    private void initContactsRecyclerView(){
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mContactsRecyclerView.setLayoutManager(mLayoutManager);
        mCitiesListAdapter = new CitiesListAdapter(new CitiesListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(City city, ImageView contactImageView, TextView contactNameTextView) {
                startContactDetail(city, contactImageView, contactNameTextView);
            }
        });
        mContactsRecyclerView.setAdapter(mCitiesListAdapter);
    }

    private void startContactDetail(City city, ImageView contactImageView, TextView contactNameTextView){
        Intent intent = new Intent(CitiesActivity.this, CitiesDetailActivity.class);
        intent.putExtra(CONTACT_ID_KEY, city);
        Pair<View, String> imageViewContactPair = Pair.create((View)contactImageView, getString(R.string.contact_image_transition));
        Pair<View, String> textViewContactNamePair = Pair.create((View)contactNameTextView, getString(R.string.contact_name_transition));
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(CitiesActivity.this, imageViewContactPair, textViewContactNamePair);
        startActivity(intent, options.toBundle());
    }

    private void initSwipeRefreshLayout(){
        mSwipeRefreshLayout.setColorSchemeColors(ResourcesCompat.getColor(getResources(), android.R.color.holo_orange_light, null));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCitiesPresenter.loadCities();
            }
        });
    }

    @Override
    public void hideSwipeRefreshLayout() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showErrorLoadingCitiesToast(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showCitiesList(ArrayList<City> citiesList) {
        mCitiesListAdapter.setCitiesList(citiesList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}

