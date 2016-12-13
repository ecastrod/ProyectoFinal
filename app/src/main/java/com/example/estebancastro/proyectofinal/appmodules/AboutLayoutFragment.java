package com.example.estebancastro.proyectofinal.appmodules;

/**
 * Created by ESTEBAN CASTRO on 4/12/2016.
 */


import android.app.Fragment;
import android.os.Bundle;

public class AboutLayoutFragment extends Fragment{


    public AboutLayoutFragment() {
        // Required empty public constructor
    }

    public static AboutLayoutFragment newInstance() {
        AboutLayoutFragment fragment = new AboutLayoutFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
