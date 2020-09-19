package com.example.titaniwmbluetoothcontrol;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titaniwmbluetoothcontrol.interfaces.ItemClickListener;
import com.example.titaniwmbluetoothcontrol.interfaces.UserModel;
import com.example.titaniwmbluetoothcontrol.interfaces.ItemClickListener;
import com.example.titaniwmbluetoothcontrol.interfaces.UserModel;

import java.util.ArrayList;
import java.util.Set;

import static android.app.Activity.RESULT_OK;

public class PareadosActivity extends Fragment implements ItemClickListener {
    int ativo = 1;
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    ArrayList<BluetoothDevice> devices = new ArrayList<BluetoothDevice>();
    ArrayList<Item> itens;
    ItemAdapter adapter;
    private UserModel model;
    EditText eTEnviar;
    ImageButton btnEnviar;
    ConexaoThread connect;

    TextView tvSelec;

    private Toolbar mTopoToolbar_configs;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInsanceState) {


        View view = inflater.inflate(R.layout.activity_pareados, null);

        tvSelec = (TextView) view.findViewById(R.id.tVSelect);
        eTEnviar = (EditText) view.findViewById(R.id.eTEnviar);
        btnEnviar = (ImageButton) view.findViewById(R.id.btnEnviar);
         mTopoToolbar_configs = (Toolbar) view.findViewById(R.id.inc_topo_toolbar_configs);

        connect=  ((BaseAplicacao)getContext().getApplicationContext()).getConnect();
        if(connect.getestaRodando())
        {
            tvSelec.setText(connect.qualDispositivo()+" " + " Conectado!");

        }

        eTEnviar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
             @Override
             public void onFocusChange(View view, boolean hasFocus) {
                 if(hasFocus)
                 {
                     eTEnviar.setText("");
                 }
                 else
                 {
                     eTEnviar.setText("escreva aqui");
                 }
             }
         });
        btnEnviar.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) { // do something } }); return view; }

              String messagem = eTEnviar.getText().toString();
              messagem = messagem.concat("%d\n");

              connect = ((BaseAplicacao)getContext().getApplicationContext()).getConnect();
              if(connect.getestaRodando())
              {

                  connect.write(messagem.getBytes());

              }else
              {
                  tvSelec.setText("Não há conexao!");
                  Toast.makeText(getContext(), "Primeiro Inicie uma Conexão", Toast.LENGTH_SHORT).show();

              }

              }
         });

         mTopoToolbar_configs.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // Intent it = null;

                switch (menuItem.getItemId()) {

                }
                //startActivity(it);

                return true;
            }
        });

        // mTopoToolbar_configs.inflateMenu(R.menu.menu_topo_toolbar_configs);
        RecyclerView recyclerV = (RecyclerView) view.findViewById(R.id.recyclerV);
        recyclerV.setLayoutManager(new LinearLayoutManager(getContext()));


        TextView tvDisPared;
        tvDisPared = (TextView) view.findViewById(R.id.tVDisPared);


        if (bluetoothAdapter == null) {
            Toast.makeText(getContext(), "Dispositivo bluetooth não encontrado", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getContext(), "Dispositivo bluetooth encontrado", Toast.LENGTH_SHORT).show();

        }

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, ativo);
        } else {
            Toast.makeText(getContext(), "Dispositivo bluetooth ligado", Toast.LENGTH_SHORT).show();

        }
        /*  Usa o adaptador Bluetooth para obter uma lista de dispositivos pareados.
         */
        UserModel model = ViewModelProviders.of(getActivity()).get(UserModel.class);


       itens = new ArrayList<>();
       adapter = new ItemAdapter();
        adapter.setItemCliquado(this);

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
                if(device.getName().equals(connect.qualDispositivo())) {
                    item.setStatusConection("true");
                }
                else
                {
                    item.setStatusConection("false");
                }




                itens.add(item);
                 devices.add(device);
                //pessoas.add(device.getName() + "\n" + device.getAddress());
            }
        }
        adapter.setArrayList(itens);
        recyclerV.setAdapter(adapter);
        recyclerV.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        model.select(devices);
        model.setFragment(this);


        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ativo) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getContext(), "Dispositivo bluetooth Ligado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Dispositivo bluetooth Não ligado", Toast.LENGTH_SHORT).show();

            }
        }
    }


    public ArrayList<BluetoothDevice> retornaPareados()

    {
        return devices;
    }


    public void updateList(Item item) {
        insertItem(item);
    }

    // Método responsável por inserir um novo usuário na lista
    //e notificar que há novos itens.


    private void insertItem(Item item) {
        itens.add(item);
        adapter.notifyItemInserted(adapter.getItemCount());
        adapter.notifyDataSetChanged();
    }


    public void adicionaPareado(BluetoothDevice bt)
    {
           Item item = new Item();
           item.setMac(bt.getAddress());
           item.setNome(bt.getName());
           item.setStatusConection("false");
        BluetoothClass bluetoothClass = bt.getBluetoothClass();
        String tipo = bluetoothClass.toString();
        item.setTipo(tipo);
          // item.setTipo(bt.getType());
           Log.i("Teste", "novo dispositivo pareado" +bt.getName());
           int i = 0;

        itens.add(item);
        adapter.notifyItemInserted(adapter.getItemCount());
       // adapter.notifyDataSetChanged();
           devices.add(bt);
        while (i < itens.size())
        {
            Log.i("Teste", "Elemento "+itens.get(i).getNome()+"na posicao "+ i+"\n");
            i++;

        }

    }

    @Override
    public void onItemClick(View view, int position) {
        ((BaseAplicacao) getContext().getApplicationContext()).getConnect();
        if (connect.getestaRodando()) {
            tvSelec.setText(connect.qualDispositivo() + "Conectado!");
            Toast.makeText(view.getContext(), "Há uma conexão ativa no momento", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(view.getContext(), "Elemento" + position, Toast.LENGTH_SHORT).show();

            connect = new ConexaoThread(devices.get(position).getAddress(), this, devices.get(position).getUuids()[0].toString(), position, devices.get(position).getName());
            connect.start();

        }
    }

         @SuppressLint("HandlerLeak")
         public Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {

                Bundle bundle = msg.getData();
                byte[] data = bundle.getByteArray("data");
                String dataString= new String(data);

                if(dataString.equals("---N"))
                {
                    tvSelec.setText("Erro ao conectar");
                }
                //   statusMessage.setText("Ocorreu um erro durante a conexão D:");
                else if(dataString.equals("---S"))
                {
                    tvSelec.setText("Conectado");
                }
                else if(dataString.equals("---C"))
                {
                    tvSelec.setText("Socket Cliente Criado");
                }
                else if(dataString.equals("--CS"))
                {
                    tvSelec.setText("Socket Cliente Conectado");
                }
                else if(dataString.equals("--CN"))
                {
                    tvSelec.setText("Socket Cliente Falha");
                }
                else if(dataString.equals("--CO"))
                {
                    tvSelec.setText("Sucesso na Conexão");
                  ((BaseAplicacao)getContext().getApplicationContext()).setConnect(connect);
                    //connect = ((BaseAplicacao)getContext().getApplicationContext()).getConnect();


                    itens.get(connect.qualPosicao()).setStatusConection("true");

                    //adapter.notifyItemInserted(adapter.getItemCount());

                    adapter.notifyItemChanged(connect.qualPosicao());


                }
                else if(dataString.equals("--CH"))
                {
                    tvSelec.setText("Falha ao transmitir Dados");
                }
                else
                Toast.makeText(getContext(), new String(data), Toast.LENGTH_SHORT).show();


            }
        };







}





