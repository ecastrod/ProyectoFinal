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
import com.example.estebancastro.proyectofinal.appmodules.citieslist.interactor.GenerateCitiesInteractor;
import com.example.estebancastro.proyectofinal.appmodules.citieslist.interactor.LoadCitiesInteractor;
import com.example.estebancastro.proyectofinal.appmodules.citieslist.presenter.CitiesPresenter;
import com.example.estebancastro.proyectofinal.appmodules.citydetail.view.CityDetailActivity;
import com.example.estebancastro.proyectofinal.model.City;
import com.example.estebancastro.proyectofinal.model.WeatherApiResponse;
import com.example.estebancastro.proyectofinal.networking.CitiesWeatherService;
import com.example.estebancastro.proyectofinal.networking.WeatherApiConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Para WS




public class CitiesActivity extends AppCompatActivity implements CitiesContract.View, Callback<WeatherApiResponse> {

    public static final String CONTACT_ID_KEY = "CONTACT_ID_KEY";
    @BindView(R.id.cities_recycler_view) RecyclerView mCitiesRecyclerView;
    @BindView(R.id.cities_swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLayoutManager;
    private CitiesListAdapter mCitiesListAdapter;
    private CitiesPresenter mCitiesPresenter;
    private LoadCitiesInteractor mLoadCitiesInteractor;
    private GenerateCitiesInteractor mGenerateCitiesInteractor;
    private Realm mRealm;

    //Para WS
    CitiesWeatherService mWeatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);

        setContentView(R.layout.activity_cities_list);
        ButterKnife.bind(this);
        mRealm = Realm.getDefaultInstance();
        initPresenter();

        // Para consultar el WS
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherApiConstants.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mWeatherService = retrofit.create(CitiesWeatherService.class);
        getCitiesWeather();
        // Fin consultar el WS

        initCitiesRecyclerView();
        initSwipeRefreshLayout();




    }

    public void getCitiesWeather() {
        Call<WeatherApiResponse> call = mWeatherService.getCitiesWeather(
                WeatherApiConstants.LAT, WeatherApiConstants.LON,
                WeatherApiConstants.CONT, WeatherApiConstants.APP_ID, WeatherApiConstants.UNIT
        );
        call.enqueue(this);
    }

    private void initPresenter(){
        mLoadCitiesInteractor = new LoadCitiesInteractor(mRealm);
        mGenerateCitiesInteractor = new GenerateCitiesInteractor(mRealm);
        mCitiesPresenter = new CitiesPresenter(getApplicationContext(), this, mLoadCitiesInteractor, mGenerateCitiesInteractor);
        mCitiesPresenter.generateRandomCities();
    }

    private void initCitiesRecyclerView(){
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mCitiesRecyclerView.setLayoutManager(mLayoutManager);
        mCitiesListAdapter = new CitiesListAdapter(new CitiesListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(City city, ImageView contactImageView, TextView cityNameTextView) {
                startCityDetail(city, contactImageView, cityNameTextView);
            }
        });
        mCitiesRecyclerView.setAdapter(mCitiesListAdapter);

        //Para WS

    }

    private void startCityDetail(City city, ImageView contactImageView, TextView cityNameTextView){
        Intent intent = new Intent(CitiesActivity.this, CityDetailActivity.class);
        intent.putExtra(CONTACT_ID_KEY, city);
        Pair<View, String> imageViewContactPair = Pair.create((View)contactImageView, getString(R.string.contact_image_transition));
        Pair<View, String> textViewContactNamePair = Pair.create((View)cityNameTextView, getString(R.string.contact_name_transition));
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(CitiesActivity.this, imageViewContactPair, textViewContactNamePair);
        startActivity(intent, options.toBundle());
    }


    private void initSwipeRefreshLayout(){
        mSwipeRefreshLayout.setColorSchemeColors(ResourcesCompat.getColor(getResources(), android.R.color.holo_orange_light, null));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //mCitiesPresenter.loadCities();
                getCitiesWeather();
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

    //Para WS
    @Override
    public void onResponse(Call<WeatherApiResponse> call, Response<WeatherApiResponse> response) {
        WeatherApiResponse weatherApiResponse = response.body();
        List<City> citiesList = weatherApiResponse.getList();
        //City city = citiesList.get(0);
        //showWeatherInfo(city);
        showCitiesList(new ArrayList<City>(citiesList));

    }

    //Para WS
    @Override
    public void onFailure(Call<WeatherApiResponse> call, Throwable t) {
        Toast.makeText(this, getString(R.string.error_getting_data), Toast.LENGTH_SHORT).show();
    }

    private void showWeatherInfo(City city){
        //mTextViewCityName.setText(city.getName());
        //mTextViewHumidity.setText(String.valueOf(city.getMain().getHumidity()));
        //mTextViewTemperature.setText(String.valueOf(city.getMain().getTemp())+ WeatherApiConstants.UNIT_CHARACTER);
        //mTextViewWind.setText(String.valueOf(city.getWind().getSpeed()));
        showWeatherDescription(city);
    }

    private void showWeatherDescription(City city){
        String description = "";

        if (city.getWeather().size() > 0){
            description += city.getWeather().get(0).getDescription();
        }

        //mTextViewDescription.setText(description);
    }
}

