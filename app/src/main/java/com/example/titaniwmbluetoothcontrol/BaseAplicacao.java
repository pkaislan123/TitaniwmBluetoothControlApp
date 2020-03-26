package com.example.titaniwmbluetoothcontrol;

import android.app.Application;

import java.util.ArrayList;

public class BaseAplicacao extends Application {
    public ConexaoThread connect;
    public ArrayList<Componente> componentes = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        connect = new ConexaoThread();
    }

    public void setComponentes(ArrayList<Componente> componentes) {
        this.componentes = componentes;
    }

    public ArrayList<Componente> getComponentes() {
        return this.componentes;
    }

    public void setConnect(ConexaoThread connect) {
        this.connect = connect;

    }

    public ConexaoThread getConnect() {
        return this.connect;
    }

}

