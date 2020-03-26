package com.example.titaniwmbluetoothcontrol;


import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titaniwmbluetoothcontrol.interfaces.UserModel;

import java.util.ArrayList;

public class FragmentConfigurarSkecth extends Fragment {

    RecyclerView recycleritens;
    ArrayList<Componente> componentes = new ArrayList<Componente>();
    ItemAdapterIntensConfigurarSckecth adapter ;
    private UserModel model;
    LinearLayoutManager meuLayout;
    ConfigurarCodigo configurarCodigo;

    FragmentGerarSkecth fragmentGerarSkecth;


    Button gerar_skecth;
    public FragmentConfigurarSkecth() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        componentes = getArguments().getParcelableArrayList("componentes");
        Log.i("ComponentesFra", componentes.get(0).getNomeComponente());

        View view = inflater.inflate(R.layout.fragment_configurar_skecth, null);

        String myTag = getTag();
        ((GerarScketch) getActivity()).setTabFragmentA(myTag);


        adapter = new ItemAdapterIntensConfigurarSckecth(getContext());
        meuLayout = new LinearLayoutManager(getContext());
        recycleritens = (RecyclerView) view.findViewById(R.id.recyclerVItensConfigurarScketch);

        recycleritens.setLayoutManager(meuLayout);

        adapter.setArrayList(componentes);

        recycleritens.setAdapter(adapter);
        recycleritens.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


        adapter.setSpinnerSelectedListener(new ItemAdapterIntensConfigurarSckecth.SpinnerSelectedListener() {
            @Override
            public void onItemClick(String item, int position) {
                Toast.makeText(getContext(), item + " posicao: " + position, Toast.LENGTH_SHORT).show();
                //componentes.get(position).setTipoArduino();
            }
        });

        adapter.setPinoListener(new ItemAdapterIntensConfigurarSckecth.PinoListener() {
            @Override
            public void onItemClick(String pino, int position) {
                Toast.makeText(getContext(), pino + "posicao: " + position, Toast.LENGTH_SHORT).show();
                componentes.get(position).setPin(pino);
            }
        });


        //fragmentGerarSkecth.recebeConfiguracoes("teste");

        gerar_skecth = view.findViewById(R.id.gerar_skecth);
        gerar_skecth.setOnClickListener(v->
        {
            configurarCodigo = new ConfigurarCodigo(componentes);
            fragmentGerarSkecth.recebeConfiguracoes("teste");
            ((GerarScketch) getActivity()).trocarPagina(1);
        });

        return view;



    }

    @Override
    public void onResume()
    {
        super.onResume();
        model = ViewModelProviders.of(getActivity()).get(UserModel.class);
        fragmentGerarSkecth = (FragmentGerarSkecth) model.getContextoGerarSkecth();
    }

}
