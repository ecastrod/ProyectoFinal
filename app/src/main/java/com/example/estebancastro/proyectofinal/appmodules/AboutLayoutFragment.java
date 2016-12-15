package com.example.estebancastro.proyectofinal.appmodules;

/**
 * Created by ESTEBAN CASTRO on 4/12/2016.
 */


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.estebancastro.proyectofinal.R;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_about_layout, container, false);
        return view;


    }

}
