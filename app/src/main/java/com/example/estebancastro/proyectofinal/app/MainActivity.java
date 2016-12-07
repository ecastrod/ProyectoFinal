package com.example.estebancastro.proyectofinal.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.estebancastro.proyectofinal.R;
import com.example.estebancastro.proyectofinal.appmodules.AboutLayoutFragment;
import com.example.estebancastro.proyectofinal.appmodules.CitiesLayoutFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private Toolbar mToolbar;
    private TextView mTextViewToolbarTitle;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private CoordinatorLayout mMainCoordinatorLayout;
    private int iCodFragment = 0; // (1)Factorial, (2)Fibonacci
    private Fragment genericFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initViews();
        setupNavigationView();
        //showInitialFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTextViewToolbarTitle = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initViews(){
        mMainCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_coordinator_layout);
    }

    private void setupNavigationView(){
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            public void onDrawerClosed(View view) {
                // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int navigationDrawerSelectedItem = item.getItemId();

        switch (navigationDrawerSelectedItem){
            case R.id.nav_cities_layout:
                showCitiesLayoutFragment();
                iCodFragment = 1;
                break;
            case R.id.nav_about_layout:
                showAboutLayoutFragment();
                iCodFragment = 2;
                break;

        }
        mNavigationView.setCheckedItem(navigationDrawerSelectedItem);
        mDrawer.closeDrawer(GravityCompat.START);
        return false;

    }


    private void showCitiesLayoutFragment() {
        updateToolbarTitle("Cities");
        //Fragment factorialFragment = FactorialLayoutFragment.newInstance();
        //changeFragment(factorialFragment);
        genericFragment = CitiesLayoutFragment.newInstance();
        changeFragment(genericFragment);
    }

    private void showAboutLayoutFragment() {
        updateToolbarTitle("About");
        //Fragment fibonacciFragment = FibonacciLayoutFragment.newInstance();
        //changeFragment(fibonacciFragment);
        genericFragment = AboutLayoutFragment.newInstance();
        changeFragment(genericFragment);
    }


    private void updateToolbarTitle(String toolbarTitle) {
        mTextViewToolbarTitle.setText(toolbarTitle);
    }

    private void changeFragment(Fragment newFragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_main_activity, newFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_share:
                shareInfo();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void shareInfo() {

        /*
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_body));
        shareIntent.setType("text/plain");

        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        }
        */



        String strBodyResult = "";
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);

        if (iCodFragment == 1)
        {
            //strBodyResult = "El resultado del factorial es: ";
            //strBodyResult = "El resultado del factorial es: " + Long.toString(((FactorialLayoutFragment)genericFragment).lResult);
        }
        else {
            //strBodyResult = "El resultado del fibonacci es: ";
            //strBodyResult = "El resultado del fibonacci es: " + Long.toString(((FibonacciLayoutFragment)genericFragment).lResult);
        }

        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"ENVIO RESULTADO" );
        shareIntent.putExtra(Intent.EXTRA_TEXT, strBodyResult.toString());
        shareIntent.setType("text/plain");

        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        }


    }


}
