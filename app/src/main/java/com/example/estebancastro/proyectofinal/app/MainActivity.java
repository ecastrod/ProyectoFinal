package com.example.estebancastro.proyectofinal.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.estebancastro.proyectofinal.R;
import com.example.estebancastro.proyectofinal.appmodules.AboutLayoutFragment;
import com.example.estebancastro.proyectofinal.appmodules.citieslist.view.CitiesActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;


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

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);

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
        //genericFragment = CitiesLayoutFragment.newInstance();
        //changeFragment(genericFragment);

        // Aqui vamos a instanciar otro activity.


        Intent intent = new Intent(MainActivity.this, CitiesActivity.class);
        //intent.putExtra(CONTACT_ID_KEY, contact);
        //Pair<View, String> imageViewContactPair = Pair.create((View)contactImageView, getString(R.string.contact_image_transition));
        //Pair<View, String> textViewContactNamePair = Pair.create((View)contactNameTextView, getString(R.string.contact_name_transition));
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(MainActivity.this);
        startActivity(intent, options.toBundle());


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

    //Cambiar este metodo por algo para instanciar otro activity.
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
            case R.id.action_settings:
                 Toast.makeText(this, "TOAST: SETTINGS. Create by Esteban Castro.", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    private void shareInfo() {

        String strBodyResult = "";
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);

        strBodyResult = "Esto es una prueba de compartir informacion.";

        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"ENVIO RESULTADO" );
        shareIntent.putExtra(Intent.EXTRA_TEXT, strBodyResult.toString());
        shareIntent.setType("text/plain");

        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        }


    }


}
