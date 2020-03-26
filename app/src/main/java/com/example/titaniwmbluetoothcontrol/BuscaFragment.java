package com.example.titaniwmbluetoothcontrol;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titaniwmbluetoothcontrol.interfaces.ItemClickListener;
import com.example.titaniwmbluetoothcontrol.interfaces.TrocaDados;
import com.example.titaniwmbluetoothcontrol.interfaces.UserModel;
import com.example.titaniwmbluetoothcontrol.interfaces.TrocaDados;
import com.example.titaniwmbluetoothcontrol.interfaces.UserModel;

import java.lang.reflect.Method;
import java.util.ArrayList;


public class BuscaFragment extends Fragment implements View.OnClickListener, ItemClickListener {
    private static final String TAG = "BuscaBluetooth";
    ConexaoThread connect;
    RecyclerView recyclerVBusc;
    LinearLayoutManager meuLayout;
    private TextView itemClique;
    TrocaDados comunicador;

    UserModel model;

    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    ArrayList<BluetoothDevice> devices = new ArrayList<BluetoothDevice>();
    ArrayList<BluetoothDevice> devicesPareados = new ArrayList<BluetoothDevice>();

    private ImageButton btnBuscar;
    ArrayList<Item> itens = new ArrayList<Item>();
    ItemAdapter adapter = new ItemAdapter();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInsanceState) {
        //paramentos de entrada
        //LayoutInflater: Layout que guardara nosso fragment
        //ViewGropu, e a activy pai que aloca a fragment
        //

        View view = inflater.inflate(R.layout.activity_descoberta, null);

        btnBuscar = (ImageButton) view.findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(this);
        itemClique = (TextView) view.findViewById(R.id.itemClique);

        meuLayout = new LinearLayoutManager(getContext());
        recyclerVBusc = (RecyclerView) view.findViewById(R.id.recyclerVBusc);


        recyclerVBusc.setLayoutManager(meuLayout);

        adapter.setItemCliquado(this);
        adapter.setArrayList(itens);

        recyclerVBusc.setAdapter(adapter);
        recyclerVBusc.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        model = ViewModelProviders.of(getActivity()).get(UserModel.class);


        return (view);


    }


    @Override
    public void onDestroy() {
        super.onDestroy();


    }


    public void updateList(Item item) {
        insertItem(item);
    }

    // Método responsável por inserir um novo usuário na lista
    //e notificar que há novos itens.
    private void insertItem(Item item) {
        itens.add(item);
        adapter.notifyItemInserted(adapter.getItemCount());
    }


    @Override
    public void onClick(View view) {

        connect = ((BaseAplicacao) getContext().getApplicationContext()).getConnect();
        if (connect.getestaRodando()) {
            Toast.makeText(getContext(), "Há uma conexão ativa, não é possivel buscar!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Botão pressionado", Toast.LENGTH_SHORT).show();
            itemClique.setText("");
            itens.clear();
            adapter.notifyDataSetChanged();

            devicesPareados = model.getSelected();

            int j = 0;


            int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);


            final BroadcastReceiver mReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    // When discovery finds a device
                    if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                        Toast.makeText(getContext(), "Busca Iniciada", Toast.LENGTH_SHORT).show();

                    } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                        Toast.makeText(getContext(), "Busca Finalizada", Toast.LENGTH_SHORT).show();

                    } else if (BluetoothAdapter.ACTION_SCAN_MODE_CHANGED.equals(action)) {
                        Toast.makeText(getContext(), "Dispositivo esta detectável", Toast.LENGTH_SHORT).show();

                    } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                        Toast.makeText(getContext(), "Dispositivo Encontrado", Toast.LENGTH_SHORT).show();


                        // Get the bluetoothDevice object from the Intent
                        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                        // Get the "RSSI" to get the signal strength as integer,
                        // but should be displayed in "dBm" units
                        Item dispositivo = new Item();
                        dispositivo.setNome(device.getName());
                        dispositivo.setMac(device.getAddress());
                        BluetoothClass bluetoothClass = device.getBluetoothClass();
                        String tipo = bluetoothClass.toString();
                        dispositivo.setTipo(tipo);
                        dispositivo.setStatusConection("false");


                        //5a020c
                        //1f00


                        if (devicesPareados.contains(device)) {
                            Log.i("Script", "Ja existe um objeto" + device.getName());
                            Log.i("Script", "em " + devicesPareados.get(devicesPareados.indexOf(device)).getName());

                        } else {
                            int i = 0;
                            Log.i("Script", "Tamanho da lista e " + itens.size());
                            if (itens.size() == 0) {


                                updateList(dispositivo);
                                devices.add(device);
                            } else {
                                while (i < itens.size()) {
                                    if (itens.get(i).getNome().equals(dispositivo.getNome())) {
                                        Log.i("Script", "Elemento no primeiro se, " + device.getName() + " ja existe na lista" + " i  e: " + i);
                                        break;
                                    } else {
                                        Log.i("Script", "Elemento no primeiro else, " + device.getName() + " nao existe na lista" + " i e: " + i);
                                        if (i == (itens.size() - 1))//se for o ultimo elemento da lista
                                        {
                                            if (itens.get(i).getNome().equals(device.getName())) {
                                                Log.i("Script", "Ultima comparacao, Elemento " + device.getName() + " ja existe na lista" + " i e :" + i);

                                            } else {
                                                Log.i("Script", "Ultima comparacao, Elemento " + device.getName() + " não existe, sera adicionado" + "i e:" + i);
                                                updateList(dispositivo);
                                                devices.add(device);

                                            }


                                        }

                                    }


                                    i++;
                                }

                            }
                        }

                        Log.i("Script", "Tamnho e " + itens.size());

                    }//fecha tudo
                }

            };


            // Registre o BroadcastReceiver
            IntentFilter filter = new IntentFilter();
            filter.addAction(BluetoothDevice.ACTION_FOUND);
            filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
            filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            filter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
            getContext().registerReceiver(mReceiver, filter);

            mBluetoothAdapter.startDiscovery();


        }
    }//fim do metodo onClick

    public void removeList(Item item, int position)
    {
        itens.remove(item);
        adapter.notifyItemRemoved(devices.indexOf(item));
        devices.remove(position);

    }



    //Intent Filter to discovery the Bluetooth devices
    // Register for broadcasts when a device is discovered.

    public boolean createBond(BluetoothDevice btDevice)
            throws Exception
    {
        Class class1 = Class.forName("android.bluetooth.BluetoothDevice");
        Method createBondMethod = class1.getMethod("createBond");
        Boolean returnValue = (Boolean) createBondMethod.invoke(btDevice);
        return returnValue.booleanValue();
    }

    @Override
    public void onItemClick(View view, final int position) {

        mBluetoothAdapter.cancelDiscovery();

        Toast.makeText(view.getContext(), "Elemento" + position , Toast.LENGTH_SHORT).show();
        itemClique.setText(itens.get(position).getNome());
         devices.get(position).createBond(); //tenta parear

        final BroadcastReceiver mReceiver = new BroadcastReceiver() { //cria um broadcast para ouvir o estado da tentativa de pareamento
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                   final int state = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);
                   final int preState = intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, BluetoothDevice.ERROR);

                    if(state == BluetoothDevice.BOND_BONDED && preState == BluetoothDevice.BOND_BONDING) {
                        Toast.makeText(getContext(), "Dispositivo Pareado", Toast.LENGTH_SHORT).show();
                        PareadosActivity pareados;
                        pareados = (PareadosActivity) model.getFragment();
                        pareados.adicionaPareado(devices.get(position));
                        removeList(itens.get(position), position);
                        //      comunicador.adicionarPareado(devices.get(position));
                    }
                    else if(state == BluetoothDevice.BOND_NONE && preState == BluetoothDevice.BOND_BONDED)
                    {
                        Toast.makeText(getContext(), "Dispositivo Não Pareado", Toast.LENGTH_SHORT).show();

                    }
                    }
            }
        };
        IntentFilter filter = new IntentFilter();

        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        getContext().registerReceiver(mReceiver, filter);
       /*
        try {
            createBond(devices.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
    }


}