package com.example.titaniwmbluetoothcontrol;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class TelaControleJoy extends AppCompatActivity implements JoyStickViewOfficial.JoystickListener  {

    ConexaoThread connect;
    private boolean clicando = false;
    private Toolbar mTopoToolbar;
    JoyStickViewOfficial joystickX;
    JoyStickViewOfficial joystickY;


    int escopoJoyDir;
    int escopoJoyEsq;


    int escopoJoyDirI;
    int escopoJoyDirF;

    int escopoJoyEsqI;
    int escopoJoyEsqF;



    int delay = 1;
    TextView statusTCJ;
    TextView deviceTCJ;
    int ativo = 1;
    ViewStub stub = null;

    String ScaracterJoyYInicio ;
    String ScaracterJoyYFim ;

    String ScaracterJoyXInicio ;
    String ScaracterJoyXFim ;

    @SuppressLint({"ClickableViewAccessibility", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_tela_controle_joy);

        joystickX = new JoyStickViewOfficial(this);
        joystickY = new JoyStickViewOfficial(this);

        statusTCJ = (TextView) findViewById(R.id.tVTCStatusTelaControleJoy);
        deviceTCJ = (TextView) findViewById(R.id.tVTCDeciveTelaControleJoy);

        mTopoToolbar = (Toolbar) findViewById(R.id.inc_topo_toolbar_com_joys);


        mTopoToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // Intent it = null;

                switch (menuItem.getItemId()) {

                    case R.id.action_configs:
                        Intent intent = new Intent(getBaseContext(), TelaControleJoyConfigs.class);

                        Bundle parametros = new Bundle();
                        parametros.putString("caracterJoyYInicio", ScaracterJoyYInicio);
                        parametros.putString("caracterJoyYFim", ScaracterJoyYFim);
                        parametros.putString("caracterJoyXInicio", ScaracterJoyXInicio);
                        parametros.putString("caracterJoyXFim", ScaracterJoyXFim);
                        parametros.putString("jxii", Integer.toString(escopoJoyDirI));
                        parametros.putString("jxif", Integer.toString(escopoJoyDirF));
                        parametros.putString("jyii", Integer.toString(escopoJoyEsqI));
                        parametros.putString("jyif", Integer.toString(escopoJoyEsqF));

                        intent.putExtras(parametros);

                       // startActivityForResult(intent, ativo);

                       startActivity(intent);

                        break;

                }
                //startActivity(it);


                return true;
            }
        });

        mTopoToolbar.inflateMenu(R.menu.menu_topo_toolbar_controle);
        connect = new ConexaoThread();



        connect = ((BaseAplicacao) this.getApplicationContext()).getConnect();




    }

/*
    protected void onActivityResult(int tela, int result, Intent intent) {

        if (tela == 1) {
            Bundle params = intent.getExtras();
            if (params != null) {
                ScaracterJoyYInicio = params.getString("caracterJoyYInicio");
                ScaracterJoyYFim = params.getString("caracterJoyYFim");
                ScaracterJoyXInicio = params.getString("caracterJoyXInicio");
                ScaracterJoyXFim = params.getString("caracterJoyXFim");
                escopoJoyDir = params.getInt("escopoJoyDir");
                escopoJoyEsq = params.getInt("escopoJoyEsq");




            }
        }
    }
*/
    @Override
    protected void onResume() {

        super.onResume();
        if (connect.getestaRodando()) {
            statusTCJ.setText("Conectado");
            deviceTCJ.setText(connect.qualDispositivo());

            ManipularSharedPreferences sp1 = new ManipularSharedPreferences(this, getBaseContext());

            if(sp1.getDadosBoolean("preferencias_confis_joy", 0, "iniciado_joy") == true)
            {
                //esta tela ja fo inciada uma vez
                ScaracterJoyYInicio = (sp1.getDadosString("preferencias_confis_joy", 0, "jyci"));
                ScaracterJoyYFim = (sp1.getDadosString("preferencias_confis_joy", 0, "jycf"));
                ScaracterJoyXInicio = (sp1.getDadosString("preferencias_confis_joy", 0, "jxci"));
                ScaracterJoyXFim = (sp1.getDadosString("preferencias_confis_joy", 0, "jxcf"));
                escopoJoyEsq = Integer.parseInt(sp1.getDadosString("preferencias_confis_joy", 0, "escopoJoyEsq"));
                escopoJoyDir = Integer.parseInt(sp1.getDadosString("preferencias_confis_joy", 0, "escopoJoyDir"));

                escopoJoyDirI = Integer.parseInt(sp1.getDadosString("preferencias_confis_joy", 0, "jxii"));
                escopoJoyDirF = Integer.parseInt(sp1.getDadosString("preferencias_confis_joy", 0, "jxif"));
                escopoJoyEsqI = Integer.parseInt(sp1.getDadosString("preferencias_confis_joy", 0, "jyii"));
                escopoJoyEsqF = Integer.parseInt(sp1.getDadosString("preferencias_confis_joy", 0, "jyif"));


            }
            else
            {

                ScaracterJoyYInicio  ="@";
                ScaracterJoyYFim = "&";
                ScaracterJoyXInicio = "#";
                ScaracterJoyXFim = "&";


                escopoJoyDir = 180;
                escopoJoyEsq = 180;

                sp1.setDados("preferencias_confis_joy", 0, "jxci", ScaracterJoyXInicio);
                sp1.setDados("preferencias_confis_joy", 0, "jxcf", ScaracterJoyXFim);
                sp1.setDados("preferencias_confis_joy", 0, "jyci", ScaracterJoyYInicio);
                sp1.setDados("preferencias_confis_joy", 0, "jycf", ScaracterJoyYFim);

                sp1.setDados("preferencias_confis_joy", 0, "escopoJoyDir", Integer.toString(escopoJoyDir));
                sp1.setDados("preferencias_confis_joy", 0, "escopoJoyEsq", Integer.toString(escopoJoyEsq));

                sp1.setDados("preferencias_confis_joy", 0, "iniciado_joy", true);

                sp1.setDados("preferencias_confis_joy", 0, "jxii", Integer.toString(0));
                sp1.setDados("preferencias_confis_joy", 0, "jxif", Integer.toString(180));
                sp1.setDados("preferencias_confis_joy", 0, "jyii", Integer.toString(0));
                sp1.setDados("preferencias_confis_joy", 0, "jyif", Integer.toString(180));
                sp1.setDados("preferencias_confis_joy", 0, "iniciado_joy", true);



            }


        }
    }





    @Override
    public void onJoystickMoved(float xPercent, float yPercent, int id) {



        switch (id) {
            case R.id.joystickX:

                xPercent = xPercent * escopoJoyDir/2;
                int x = (int) xPercent + escopoJoyDir/2;
                Log.i("extras", "x : "+x);
                String dados = Integer.toString(x);

              //  Log.i("extras", "X percent: " + xPercent);
              ControleDirecaoX controleX = new ControleDirecaoX();
             String Dados = ScaracterJoyXInicio.concat(dados);
             controleX.setDados(Dados);
             controleX.execute();

                break;
            case R.id.joystickY:
                yPercent = yPercent * escopoJoyEsq/2;
                int y = (int) yPercent + escopoJoyEsq/2;
                Log.i("extras", "x : "+y);
                String dadosY = Integer.toString(y);

                //  Log.i("extras", "X percent: " + xPercent);
                ControleDirecaoY controleY = new ControleDirecaoY();
                String DadosY = ScaracterJoyYInicio.concat(dadosY);
                controleY.setDados(DadosY);
                controleY.execute();

                break;
        }
    }

    public void recarregarTela(View view)
    {/*
        connect.write("P".getBytes());
        connect.write("P".getBytes());


        connect.write("B".getBytes());
        connect.write("B".getBytes());
        */
    }

    private class ControleDirecaoX extends AsyncTask {

        String Dados;

        public void setDados(String dados) {
            this.Dados = dados;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            Log.i("Verificar", "dados antes de enviar e: " + Dados.getBytes());
           String dados = Dados.concat(ScaracterJoyXFim);

            connect.write(dados.getBytes());


            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("CONTROLE", "o botao chammou cancel");
        }
    }


    private class ControleDirecaoY extends AsyncTask {

        String Dados;

        public void setDados(String dados) {
            this.Dados = dados;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            Log.i("Verificar", "dados antes de enviar e: " + Dados.getBytes());
            String dados = Dados.concat(ScaracterJoyYFim);

            connect.write(dados.getBytes());


            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("CONTROLE", "o botao chammou cancel");
        }
    }


}




