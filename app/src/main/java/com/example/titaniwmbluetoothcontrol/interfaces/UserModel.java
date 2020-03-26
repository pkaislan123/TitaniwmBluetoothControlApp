package com.example.titaniwmbluetoothcontrol.interfaces;

import android.bluetooth.BluetoothDevice;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.titaniwmbluetoothcontrol.FragmentGerarSkecth;
import com.example.titaniwmbluetoothcontrol.PareadosActivity;
import com.example.titaniwmbluetoothcontrol.FragmentGerarSkecth;
import com.example.titaniwmbluetoothcontrol.PareadosActivity;

import java.util.ArrayList;

public class UserModel extends ViewModel {
    private final MutableLiveData<ArrayList<BluetoothDevice>> selected = new MutableLiveData<>();
    private final MutableLiveData<PareadosActivity> mandarFragment = new MutableLiveData<>();

    private final MutableLiveData<FragmentGerarSkecth> contextoGerarSkecth = new MutableLiveData<>();


    private ArrayList<BluetoothDevice> devices = new ArrayList<>();


    public void select(ArrayList<BluetoothDevice> dispositivosPareados) {

        selected.setValue(dispositivosPareados);
    }

    public void setFragment(PareadosActivity pareados) {
        mandarFragment.setValue(pareados);
    }

    public Fragment getFragment() {
        return mandarFragment.getValue();
    }


    public ArrayList<BluetoothDevice> getSelected() {
        return selected.getValue();
    }



    public void setContextoGerarSkecth(FragmentGerarSkecth fragmentGerarSkecth)
    {
        contextoGerarSkecth.setValue(fragmentGerarSkecth);
    }

    public Fragment getContextoGerarSkecth(){
        return contextoGerarSkecth.getValue();
    }


}






