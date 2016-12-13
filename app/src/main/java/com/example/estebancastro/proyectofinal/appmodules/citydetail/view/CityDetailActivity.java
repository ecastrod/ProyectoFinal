package com.example.estebancastro.proyectofinal.appmodules.citydetail.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.estebancastro.proyectofinal.R;
import com.example.estebancastro.proyectofinal.appmodules.citieslist.view.CitiesActivity;
import com.example.estebancastro.proyectofinal.appmodules.citydetail.CityDetailContract;
import com.example.estebancastro.proyectofinal.appmodules.citydetail.presenter.CityDetailPresenter;
import com.example.estebancastro.proyectofinal.model.City;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityDetailActivity extends AppCompatActivity implements CityDetailContract.View {

    @BindView(R.id.text_view_city_name_value)
    TextView mTextViewName;
    @BindView(R.id.text_view_city_email_value) TextView mTextViewEmail;
    @BindView(R.id.text_view_city_telephone_value) TextView mTextViewTelephone;
    private CityDetailPresenter mCityDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        ButterKnife.bind(this);
        initPresenter();
        loadCityData();
    }

    private void initPresenter(){
        mCityDetailPresenter = new CityDetailPresenter(this);
    }

    private void loadCityData(){
        City city = getIntent().getParcelableExtra(CitiesActivity.CONTACT_ID_KEY);
        mCityDetailPresenter.loadCityInfo(city);
    }

    @Override
    public void showCityInfo(String name, String email, String telephone) {
        mTextViewName.setText(name);
        mTextViewEmail.setText(email);
        mTextViewTelephone.setText(telephone);
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}
