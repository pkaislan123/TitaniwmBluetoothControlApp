package com.example.titaniwmbluetoothcontrol;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class ConexaoRapida {
    ConexaoThread connect;
    String status;
    Activity tela;
    MainActivity main;


    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    ArrayList<BluetoothDevice> devices = new ArrayList<BluetoothDevice>();
    ArrayList<Item> itens = new ArrayList<>();
    int position;


    public ConexaoRapida(MainActivity main) {
        this.main = main;
    }

    public ConexaoThread procuraConexao() {

        connect = ((BaseAplicacao) main.getApplicationContext()).getConnect();
        if (connect.getestaRodando()) {
            Toast.makeText(main.getApplicationContext(), "Há uma conexão ativa no momento", Toast.LENGTH_SHORT).show();
        }
        else {


            if (!bluetoothAdapter.isEnabled()) {
                Toast.makeText(main.getApplicationContext(), "Dispositivo Desligado, configure", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(main.getApplicationContext(), "Dispositivo bluetooth ligado", Toast.LENGTH_SHORT).show();


                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                if (pairedDevices.size() > 0) {
                    // There are paired devices. Get the name and address of each paired device.
                    for (BluetoothDevice device : pairedDevices) {
                        Item item = new Item();
                        item.setNome(device.getName());
                        item.setMac(device.getAddress());
                        BluetoothClass bluetoothClass = device.getBluetoothClass();
                        String tipo = bluetoothClass.toString();
                        item.setTipo(tipo);
                        itens.add(item);
                        devices.add(device);
                        //pessoas.add(device.getName() + "\n" + device.getAddress());
                    }

                    int i = 0;
                    while (i < itens.size())
                    {
                        if(itens.get(i).getTipo().equals("1f00")) {
                            position = i;
                            break;
                        }
                        i++;
                    }


                    if (position != -1) {

                        connect = new ConexaoThread(itens.get(position).getMac(), devices.get(position).getUuids()[0].toString(), position, itens.get(position).getNome(), false, main);
                        connect.start();
                    } else {
                        Toast.makeText(main.getApplicationContext(), "Não há dispositivos disponiveis", Toast.LENGTH_SHORT).show();


                    }

                }
                else
                {
                    Toast.makeText(main.getApplicationContext(), "Não há dispositivos disponiveis", Toast.LENGTH_SHORT).show();

                }

            }


    }
 return connect;

}


}