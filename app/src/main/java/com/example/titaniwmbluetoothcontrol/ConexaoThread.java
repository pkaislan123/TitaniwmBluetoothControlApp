package com.example.titaniwmbluetoothcontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import androidx.lifecycle.ViewModelProviders;

import com.example.titaniwmbluetoothcontrol.interfaces.UserModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.UUID;

public class ConexaoThread extends Thread {

    InputStream input = null;
    OutputStream output = null;
    BluetoothSocket btSocket = null;
    BluetoothServerSocket btServerSocket = null;
    String btDevAddress = null;
    String myUUID;
    boolean server;
    boolean running = false;
    UserModel model;
    PareadosActivity pareados;

    boolean terminal_ativo = false;
    boolean personalizada_ativo = false;
    boolean novaPersonalizada_ativo = false;

    Terminal terminal;
    TelaPersonalizada personalizada;
    TelaNovoLayoutsPersonalizados novaPersonalizada;


    boolean estaRodando = false;
    int position;
    boolean toMain = true;
    String device;
    MainActivity main;


    public ConexaoThread() {
        this.server = true;

    }

    public void setTerminalAtivo(boolean terminal_ativo, Terminal terminal)
    {
        this.terminal = terminal;
        this.terminal_ativo = terminal_ativo;
    }


    public void setNovaPersonalizadaAtivo(boolean novaPersonalizada_ativo, TelaNovoLayoutsPersonalizados novaPersonalizada)
    {
        this.novaPersonalizada = novaPersonalizada;
        this.novaPersonalizada_ativo = novaPersonalizada_ativo;
    }

 public ConexaoThread(String btDevAddress,String MyUUID, int position, String device, boolean toMain, MainActivity main)
 {
     this.server = false;
     this.btDevAddress = btDevAddress;
     this.myUUID = MyUUID;
     this.position = position;
     this.device = device;
     this.toMain = toMain;
     this.main = main;
     this.device = device;



 }

    public ConexaoThread(String btDevAddress, PareadosActivity Pareados, String MyUUID, int position, String device) {
        this.server = false;
        this.btDevAddress = btDevAddress;
        this.myUUID = MyUUID;
        this.position = position;
        this.device = device;


        model = ViewModelProviders.of(Pareados).get(UserModel.class);
        pareados = (PareadosActivity) Pareados;

    }


    public void run() {
        this.running = true;
        BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();

        Log.i("Teste", "MY uudi e :" + myUUID);
        if (this.server) {
            try {
                btServerSocket = bluetooth.listenUsingRfcommWithServiceRecord("Super Bluetooth", UUID.fromString(myUUID));
                btSocket = btServerSocket.accept();

                if (btSocket != null) {
                    btServerSocket.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
               if(toMain)
                toMainActivity("---N".getBytes());
               else
                   toMainActivityPrincipal("---N".getBytes());

            }

        } else {

            try {
                BluetoothDevice btDevice = bluetooth.getRemoteDevice(btDevAddress);
                btSocket = btDevice.createRfcommSocketToServiceRecord(UUID.fromString(myUUID));

                bluetooth.cancelDiscovery();

                if (btSocket != null) {
                    if(toMain) {
                        toMainActivity("---C".getBytes());
                    }
                    else {
                        toMainActivityPrincipal("---C".getBytes());
                    }
                    btSocket.connect();
                    if (btSocket.isConnected())
                        if(toMain)
                            toMainActivity("--CS".getBytes());
                        else
                            toMainActivityPrincipal("--CS".getBytes());

                    else {
                            if(toMain)
                              toMainActivity("--CN".getBytes());
                            else
                                toMainActivityPrincipal("--CN".getBytes());

                        cancel();
                    }
                }


            } catch (IOException e) {

                /*  Caso ocorra alguma exceção, exibe o stack trace para debug.
                    Envia um código para a Activity principal, informando que
                a conexão falhou.
                 */
                e.printStackTrace();
                if(toMain)
                 toMainActivity("---N".getBytes());
                else
                    toMainActivityPrincipal("---N".getBytes());

            }

        }
        if(btSocket != null) {

            /*  Envia um código para a Activity principal informando que a
            a conexão ocorreu com sucesso.
             */
            if(toMain)
                toMainActivity("--CO".getBytes());
            else
                toMainActivityPrincipal("--CO".getBytes());

            this.running = true;
            try {

                /*  Obtem referências para os fluxos de entrada e saída do
                socket Bluetooth.
                 */
                input = btSocket.getInputStream();
                output = btSocket.getOutputStream();

                /*  Cria um byte array para armazenar temporariamente uma
                mensagem recebida.
                    O inteiro bytes representará o número de bytes lidos na
                última mensagem recebida.
                 */

                /*  Permanece em estado de espera até que uma mensagem seja
                recebida.
                    Armazena a mensagem recebida no buffer.
                    Envia a mensagem recebida para a Activity principal, do
                primeiro ao último byte lido.
                    Esta thread permanecerá em estado de escuta até que
                a variável running assuma o valor false.
                 */
                while (running) {

                    try {
                        byte[] buffer = new byte[1024];
                        int bytes = 0;
                        int bytesRead = -1;

                        do{
                            bytes = input.read(buffer, bytesRead+1, 1);
                            bytesRead+=bytes;


                        }while(buffer[bytesRead] != '\n');


                        if (terminal_ativo)

                            toTerminal(Arrays.copyOfRange(buffer, 0, bytesRead -1));

                        if(personalizada_ativo)
                            toPersonalizada(Arrays.copyOfRange(buffer, 0, bytesRead -1));

                        if(novaPersonalizada_ativo)
                            toNovaPersonalizada(Arrays.copyOfRange(buffer, 0, bytesRead -1));

                    } catch (IOException e) {
                        Log.i("Leitura", "erro");
                        e.printStackTrace();
                    }
                    //toMainActivity(Arrays.copyOfRange(buffer, 0, bytes));
                }


                       /*
                        while (running) {

                            try {
                                bytes = input.read(buffer);


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //toMainActivity(Arrays.copyOfRange(buffer, 0, bytes));
                            if (terminal_ativo)

                                toTerminal(Arrays.copyOfRange(buffer, 0, bytes));

                            if(personalizada_ativo)
                                toPersonalizada(Arrays.copyOfRange(buffer, 0, bytes));

                        }*/




            } catch (IOException e) {

                /*  Caso ocorra alguma exceção, exibe o stack trace para debug.
                    Envia um código para a Activity principal, informando que
                a conexão falhou.
                 */
                e.printStackTrace();
                if(toMain)
                  toMainActivity("---N".getBytes());
                else
                    toMainActivityPrincipal("---N".getBytes());

            }
        }

    }



public boolean getestaRodando()
{
    return this.running;
}

public int qualPosicao()
{
    return this.position;
}

public String qualDispositivo()
{
    return this.device;
}



    public void write(byte[] data) {

        if(output != null) {
            try {

                /*  Transmite a mensagem.
                 */
                output.write(data);


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            /*  Envia à Activity principal um código de erro durante a conexão.
             */
            if(toMain)
             toMainActivity("--CH".getBytes());
            else
                toMainActivityPrincipal("--CH".getBytes());

        }
    }



    private void toMainActivity(byte[] data) {

        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putByteArray("data", data);
        message.setData(bundle);
        pareados.handler.sendMessage(message);
    }

    private void toMainActivityPrincipal(byte[] data) {

        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putByteArray("data", data);
        message.setData(bundle);
        main.handler.sendMessage(message);
    }

    private void toTerminal(byte[] data)
    {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putByteArray("data", data);
        message.setData(bundle);
        terminal.handler.sendMessage(message);
    }

    private void toPersonalizada(byte[] data)
    {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putByteArray("data", data);
        message.setData(bundle);
        personalizada.handler.sendMessage(message);
    }

    private void toNovaPersonalizada(byte[] data)
    {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putByteArray("data", data);
        message.setData(bundle);
        novaPersonalizada.handler.sendMessage(message);
    }


    /*  Método utilizado pela Activity principal para encerrar a conexão
     */
    public void cancel() {

        try {
           if(running == false)
           {

           }
           else {

               if (btSocket.isConnected()) {
                   btSocket.close();
               }
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        running = false;
    }
}





