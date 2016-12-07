package com.example.estebancastro.proyectofinal.appmodules;

/**
 * Created by ESTEBAN CASTRO on 4/12/2016.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.estebancastro.proyectofinal.R;

public class CitiesLayoutFragment extends Fragment{

    public CitiesLayoutFragment() {
        // Required empty public constructor
    }

    public static CitiesLayoutFragment newInstance() {
        CitiesLayoutFragment fragment = new CitiesLayoutFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
