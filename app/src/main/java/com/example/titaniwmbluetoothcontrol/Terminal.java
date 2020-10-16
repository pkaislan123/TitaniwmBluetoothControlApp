package com.example.titaniwmbluetoothcontrol;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Terminal extends AppCompatActivity {


    private EditText tvTerminalSendText;
    private ImageButton btnTerminalSendText;
    private TextView tvTerminalDevice;
    ConexaoThread connect;
    int contadorTextViews = 0;
    private TextView textViews[] = new TextView[10000];
    private ArrayList<dadosTerminal> dados = new ArrayList<>();

    private ScrollView scrollterminal;

    RecyclerView terminal_itens;
    private String newLine = "\r\n";

    ItemAdapterTerminal itemAdapterTerminal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminal);

        tvTerminalDevice = (TextView) findViewById(R.id.tvTerminalDevice);

        tvTerminalSendText = (EditText) findViewById(R.id.tvTerminalSendText);
        tvTerminalSendText.setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);


        btnTerminalSendText = (ImageButton) findViewById(R.id.btnTerminalSendText);

     //  scrollterminal = findViewById(R.id.scrollterminal);

        terminal_itens = (RecyclerView) findViewById(R.id.terminal_itens);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //layoutManager.setStackFromEnd(true);
        terminal_itens.setLayoutManager(layoutManager);
        itemAdapterTerminal = new ItemAdapterTerminal(dados);

        terminal_itens.setAdapter(itemAdapterTerminal);

        btnTerminalSendText.setOnClickListener(v->
        {
            String para_enviar = tvTerminalSendText.getText().toString();
            if(para_enviar != null && para_enviar.length() > 0 && para_enviar != " " && para_enviar != "") {


                tvTerminalSendText.setText(null);

                dadosTerminal texto_enviar = new dadosTerminal();
                texto_enviar.setTipo(0);
                texto_enviar.setTexto(para_enviar);
                texto_enviar.setDevice(Build.MODEL);
                dados.add(texto_enviar);
                itemAdapterTerminal.notifyItemInserted(itemAdapterTerminal.getItemCount());
                terminal_itens.post(new Runnable() {
                    @Override
                    public void run() {

                       terminal_itens.smoothScrollToPosition(itemAdapterTerminal.getItemCount()-1);

                       // scrollterminal.smoothScrollTo(0, scrollterminal.getBottom());

                    }
                });


                connect.write(para_enviar.getBytes());



            }
        });


    }




    @Override
    public void onResume()
    {
        super.onResume();
        connect = ((BaseAplicacao) this.getApplicationContext()).getConnect();
        if (connect.getestaRodando()) {
            tvTerminalDevice.setText(connect.qualDispositivo()+ "--Conectado");
            connect.setTerminalAtivo(true, this);


        }

    }

    @Override
    public void onStop()
    {
        super.onStop();
        connect.setTerminalAtivo(false, null);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        connect.setTerminalAtivo(false, null);
    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();
        connect.setTerminalAtivo(false, null);

    }


    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            Bundle bundle = msg.getData();
            byte[] data = bundle.getByteArray("data");
            String dataString= new String(data);
          //  Toast.makeText(getBaseContext(), "texto: " + dataString, Toast.LENGTH_SHORT).show();


           if(dataString != null && dataString.length() > 0 && !dataString.equals("") && !dataString.equals(" ")) {
               dadosTerminal texto_recebido = new dadosTerminal();
               texto_recebido.setTexto(dataString);
               texto_recebido.setTipo(1);
               texto_recebido.setDevice(connect.qualDispositivo());
               dados.add(texto_recebido);
               itemAdapterTerminal.notifyItemInserted(itemAdapterTerminal.getItemCount());
               terminal_itens.post(new Runnable() {
                   @Override
                   public void run() {

                       terminal_itens.smoothScrollToPosition(itemAdapterTerminal.getItemCount()-1);

                     //scrollterminal.smoothScrollTo(0, scrollterminal.getBottom());



                   }
               });

           }
        }
    };


class dadosTerminal
    {

      int tipo;
      String texto;
      String device;


        public String getTexto() {
            return texto;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }

        public dadosTerminal() {

        }
        public int getTipo() {
            return tipo;
        }

        public void setTipo(int tipo) {
            this.tipo = tipo;
        }
    }

}

