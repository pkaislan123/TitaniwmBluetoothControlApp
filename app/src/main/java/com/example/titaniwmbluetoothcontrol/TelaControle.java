package com.example.titaniwmbluetoothcontrol;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import static java.lang.Thread.sleep;

public class TelaControle extends AppCompatActivity {

    ConexaoThread connect;
    Button btnDireita;
    Button btnEsquerda;
    private boolean clicando = false;
    private Toolbar mTopoToolbar;
    TextView tvDevice;
    TextView tVStatus;
    int delay = 10;
    int ativo = 1;
    ViewStub stub = null;
    Button btnAcelerar;
    Button btnFreiar;
    Button btnConfigsBtns;
    Button btnSalvarConfigsBtns;
    ConstraintLayout meuLayout;
    EditText eTBtnEsquerda;
    EditText eTBtnDireita;

    EditText eTBtnAcelerar;

    EditText eTBtnFreiar;

    String acelerar;
    String freiar;
    String esquerda;
    String direita;



    @SuppressLint({"ClickableViewAccessibility", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_controle);
       // stub = (ViewStub) findViewById(R.id.frameLayout);
        //View inflate = stub.inflate();
        meuLayout = new ConstraintLayout(getApplicationContext());

        mTopoToolbar = (Toolbar) findViewById(R.id.inc_topo_toolbar_com_botoes);


        mTopoToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // Intent it = null;

                switch (menuItem.getItemId()) {

                    case R.id.action_configs:
                        Intent intent = new Intent(getBaseContext(), TelaControleConfigs.class);
                        Bundle parametros = new Bundle();
                        parametros.putInt("delay", delay);
                        intent.putExtras(parametros);
                        startActivityForResult(intent, ativo);


                        break;

                }
                //startActivity(it);

                return true;
            }
        });

        mTopoToolbar.inflateMenu(R.menu.menu_topo_toolbar_controle);
        connect = new ConexaoThread();
        btnDireita = (Button) findViewById(R.id.btnDireita);
        btnEsquerda = (Button) findViewById(R.id.btnEsquerda);
        btnAcelerar = (Button) findViewById(R.id.btnAcelerar);
        btnFreiar = (Button) findViewById(R.id.btnFreiar);
        tVStatus = (TextView) findViewById(R.id.tVTCStatus);
        tvDevice = (TextView) findViewById(R.id.tVTCDecive);
        btnSalvarConfigsBtns = (Button) findViewById(R.id.btnSalvarConfigsBtns);

        btnConfigsBtns = (Button) findViewById(R.id.btnConfigsBTNs);

        eTBtnDireita = (EditText) findViewById(R.id.eTBtnDireita);
        eTBtnEsquerda = (EditText) findViewById(R.id.eTBtnEsquerda);
        eTBtnAcelerar = (EditText) findViewById(R.id.eTBTNAcelerar);
        eTBtnFreiar = (EditText) findViewById(R.id.eTBtnFreiar);


        btnConfigsBtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getBaseContext(), "Botao de configuracoes pressionado", Toast.LENGTH_SHORT).show();



                eTBtnFreiar.setEnabled(true);
                eTBtnAcelerar.setEnabled(true);
                eTBtnEsquerda.setEnabled(true);
                eTBtnDireita.setEnabled(true);

                btnSalvarConfigsBtns.setEnabled((true));
                btnSalvarConfigsBtns.setVisibility(View.VISIBLE);


                eTBtnAcelerar.setVisibility(View.VISIBLE);
                eTBtnDireita.setVisibility(View.VISIBLE);
                eTBtnEsquerda.setVisibility(View.VISIBLE);
                eTBtnFreiar.setVisibility(View.VISIBLE);


            }
        });

        btnSalvarConfigsBtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                direita = eTBtnDireita.getText().toString();
                esquerda = eTBtnEsquerda.getText().toString();
                acelerar = eTBtnAcelerar.getText().toString();
                freiar = eTBtnFreiar.getText().toString();

                eTBtnFreiar.setEnabled(false);
                eTBtnAcelerar.setEnabled(false);
                eTBtnEsquerda.setEnabled(false);
                eTBtnDireita.setEnabled(false);


                eTBtnAcelerar.setVisibility(View.INVISIBLE);
                eTBtnDireita.setVisibility(View.INVISIBLE);
                eTBtnEsquerda.setVisibility(View.INVISIBLE);
                eTBtnFreiar.setVisibility(View.INVISIBLE);

                btnSalvarConfigsBtns.setVisibility(View.INVISIBLE);
                btnSalvarConfigsBtns.setEnabled(false);
            }
        });

        btnFreiar.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, final MotionEvent motionEvent) {

                ControleDeDirecao controle = new ControleDeDirecao();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (clicando == false) {
                            clicando = true;
                            controle.setDados(freiar);
                            controle.execute();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        clicando = false;
                        controle.cancel(true);
                        break;
                }
                return false;
            }

        });

        btnAcelerar.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, final MotionEvent motionEvent) {

                ControleDeDirecao controle = new ControleDeDirecao();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (clicando == false) {
                            clicando = true;
                            controle.setDados(acelerar);
                            controle.execute();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        clicando = false;
                        controle.cancel(true);
                        break;
                }
                return false;
            }

        });


        btnDireita.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, final MotionEvent motionEvent) {

                ControleDeDirecao controle = new ControleDeDirecao();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (clicando == false) {
                            clicando = true;
                            controle.setDados(direita);
                            controle.execute();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        connect.write("O".getBytes());
                        connect.write("O".getBytes());
                        connect.write("O".getBytes());

                        clicando = false;

                        controle.cancel(true);



                        break;
                }
                return false;
            }

        });

        btnEsquerda.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View view, final MotionEvent motionEvent) {
                ControleDeDirecao controle = new ControleDeDirecao();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (clicando == false) {
                            clicando = true;
                            controle.setDados(esquerda);
                            controle.execute();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        clicando = false;
                        connect.write("O".getBytes());
                        connect.write("O".getBytes());
                        connect.write("O".getBytes());

                        controle.cancel(true);



                        break;
                }
                return false;
            }

        });
        connect = ((BaseAplicacao) this.getApplicationContext()).getConnect();

    }


    protected void onActivityResult(int tela, int result, Intent intent) {
        if (tela == 1) {
            Bundle params = intent.getExtras();
            if (params != null) {
                int delay = params.getInt("delay");
                this.delay = delay;
            }
        }
        connect = ((BaseAplicacao) this.getApplicationContext()).getConnect();
       // connect.write("P".getBytes());

    //    connect.write("A".getBytes());
    }

    @Override
    protected void onResume() {

        super.onResume();
        connect = ((BaseAplicacao) this.getApplicationContext()).getConnect();
        if (connect.getestaRodando()) {
            tvDevice.setText(connect.qualDispositivo());
            tVStatus.setText("Conectado");

        }

        direita = eTBtnDireita.getText().toString();
        esquerda = eTBtnEsquerda.getText().toString();
        acelerar = eTBtnAcelerar.getText().toString();
        freiar = eTBtnFreiar.getText().toString();

        eTBtnFreiar.setEnabled(false);
        eTBtnAcelerar.setEnabled(false);
        eTBtnEsquerda.setEnabled(false);
        eTBtnDireita.setEnabled(false);


        eTBtnAcelerar.setVisibility(View.INVISIBLE);
        eTBtnDireita.setVisibility(View.INVISIBLE);
        eTBtnEsquerda.setVisibility(View.INVISIBLE);
        eTBtnFreiar.setVisibility(View.INVISIBLE);

        btnSalvarConfigsBtns.setVisibility(View.INVISIBLE);
        btnSalvarConfigsBtns.setEnabled(false);

    }

    public void recarregarTelaC(View view)
    {


    }

/*

    @Override
    public void onJoystickMoved(float xPercent, float yPercent, int id) {
        xPercent = xPercent * 90;
        int x = (int) xPercent + 90;
        Log.i("extras", "x : "+x);
        String dados = Integer.toString(x);


        switch (id) {
            case R.id.joystickX:
              //  Log.i("extras", "X percent: " + xPercent);
             ControleDirecao controle = new ControleDirecao();
             controle.setDados(dados);
             controle.execute();

                break;
        }
    }
    */

    private class ControleDeDirecao extends AsyncTask {

        String Dados;

        public void setDados(String dados) {
            this.Dados = dados;
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







}




