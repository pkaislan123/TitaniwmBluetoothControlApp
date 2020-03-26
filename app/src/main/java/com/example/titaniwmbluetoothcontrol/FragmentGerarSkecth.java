package com.example.titaniwmbluetoothcontrol;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.titaniwmbluetoothcontrol.interfaces.UserModel;
import com.example.titaniwmbluetoothcontrol.interfaces.UserModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGerarSkecth extends Fragment {


    EditText skecth_definida ;
    String texto;

    public FragmentGerarSkecth() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gerar_skecth, container, false);

        String myTag = getTag();
        ((GerarScketch) getActivity()).setTabFragmentB(myTag);

        skecth_definida = view.findViewById(R.id.skecth_definida);

        UserModel model = ViewModelProviders.of(getActivity()).get(UserModel.class);
        model.setContextoGerarSkecth(this);

        skecth_definida = view.findViewById(R.id.skecth_definida);

        return view;
    }


    public void recebeConfiguracoes(String texto)
    {
        skecth_definida.setText(texto);
        Toast.makeText(getContext(), "Funcao chmada", Toast.LENGTH_SHORT).show();
    }

}
