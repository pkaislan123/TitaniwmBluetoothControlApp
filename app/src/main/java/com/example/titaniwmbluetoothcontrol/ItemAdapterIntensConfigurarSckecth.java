package com.example.titaniwmbluetoothcontrol;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapterIntensConfigurarSckecth extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private SpinnerSelectedListener spinnerSelectedListener;
    private PinoListener pinoListener;
    private ArrayList<Componente> componentes = new ArrayList<>() ;
    Context context;

    public void setSpinnerSelectedListener(SpinnerSelectedListener spinnerSelectedListener)
    {
        this.spinnerSelectedListener = spinnerSelectedListener;
    }

    public void setPinoListener(PinoListener pinoListener)
    {
        this.pinoListener = pinoListener;
    }


    public  ItemAdapterIntensConfigurarSckecth(Context context)
    {
        this.context = context;
    }

    public void setArrayList(ArrayList<Componente> componentes)
    {
        this.componentes = componentes;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View visual;
        visual = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itens_configurar_scketch, viewGroup, false);
        return new ItemAdapterIntensConfigurarSckecth.ItemViewHolder(visual);    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ItemAdapterIntensConfigurarSckecth.ItemViewHolder) viewHolder).itens_configurar_scketch_nome_componente.setText(componentes.get(i).getNomeComponente());
        ((ItemAdapterIntensConfigurarSckecth.ItemViewHolder) viewHolder).itens_configurar_scketch_tipo_componente.setText(componentes.get(i).getTipo());

    }

    @Override
    public int getItemCount() {
        return componentes != null ? componentes.size() : 0;
    }

    class ItemViewHolder extends  RecyclerView.ViewHolder implements AdapterView.OnItemSelectedListener, TextWatcher {
        private TextView itens_configurar_scketch_nome_componente;
        private TextView itens_configurar_scketch_tipo_componente;
        private EditText itens_configurar_scketch_pino;
        private Spinner itens_configurar_scketch_spinner_tipo_arduino;

        private String[] tipos_arduino = new String[]{"Servo", "Led", "Motor"};


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itens_configurar_scketch_nome_componente =  itemView.findViewById(R.id.itens_configurar_scketch_nome_componente);
            itens_configurar_scketch_tipo_componente = itemView.findViewById(R.id.itens_configurar_scketch_tipo_componente);
            itens_configurar_scketch_pino = itemView.findViewById(R.id.itens_configurar_scketch_pino);
            itens_configurar_scketch_spinner_tipo_arduino = itemView.findViewById(R.id.itens_configurar_scketch_spinner_tipo_arduino);


            final ArrayAdapter adapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, tipos_arduino);
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            itens_configurar_scketch_spinner_tipo_arduino.setAdapter(adapter);

            itens_configurar_scketch_spinner_tipo_arduino.setOnItemSelectedListener(this);
            itens_configurar_scketch_pino.addTextChangedListener(this);

        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String item = parent.getItemAtPosition(position).toString();
            //Toast.makeText(context, item, Toast.LENGTH_SHORT).show();
            spinnerSelectedListener.onItemClick(item, getAdapterPosition());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String pino = s.toString();
            pinoListener.onItemClick(pino, getAdapterPosition());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public interface SpinnerSelectedListener {

        void onItemClick(String item, int position);
    }

    public interface PinoListener{
        void onItemClick(String pino, int position);
    }

}

