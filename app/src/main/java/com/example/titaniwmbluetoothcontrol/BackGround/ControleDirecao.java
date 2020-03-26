package com.example.titaniwmbluetoothcontrol.BackGround;

import android.os.AsyncTask;
import android.util.Log;

import com.example.titaniwmbluetoothcontrol.ConexaoThread;
import com.example.titaniwmbluetoothcontrol.ConexaoThread;

import static java.lang.Thread.sleep;

public class ControleDirecao extends AsyncTask {

    private ConexaoThread connect;
    String Dados;
    int delay;
    boolean clicando = false;

    public void setDados(String dados,boolean clicando, int delay, ConexaoThread connect) {
        this.Dados = dados;
        this.delay = delay;
        this.connect = connect;
        this.clicando = clicando;
    }

    public void setStop(boolean clicando)
    {
        this.clicando = clicando;
    }



    @Override
    protected Object doInBackground(Object[] objects) {
        while (clicando) {

            connect.write(Dados.getBytes());
            try {
                sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return null;

    }
    @Override
    protected void onCancelled () {
        super.onCancelled();
        Log.i("CONTROLE", "o botao chammou cancel");
    }
}

