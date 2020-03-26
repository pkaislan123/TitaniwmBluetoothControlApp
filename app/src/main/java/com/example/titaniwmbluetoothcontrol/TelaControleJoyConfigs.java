package com.example.titaniwmbluetoothcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class TelaControleJoyConfigs extends AppCompatActivity {
    private Toolbar mTopoToolbar;

    Button salvarConfigsJoys;
    EditText caracterJoyYInicio;
    EditText caracterJoyYFim;

    EditText caracterJoyXInicio;
    EditText caracterJoyXFim;


    EditText eTJoyDirIntervaloInicio;
    EditText eTJoyDirIntervaloFim;


    EditText eTJoyEsqIntervaloInicio;
    EditText eTJoyEsqIntervaloFim;



    String SJoyDirIntervaloInicio;
    String SJoyDirIntervaloFim;


    String SJoyEsqIntervaloInicio;
    String SJoyEsqIntervaloFim;

    String ScaracterJoyYInicio;
    String ScaracterJoyYFim;

    String ScaracterJoyXInicio;
    String ScaracterJoyXFim;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_controle_joy_configs);


        mTopoToolbar = (Toolbar) findViewById(R.id.inc_topo_toolbar_com_joys_configs);

        caracterJoyYInicio = (EditText) findViewById(R.id.eTJoyDireitaInicio);
        caracterJoyYFim = (EditText) findViewById(R.id.eTJoyDireitaFim);
        caracterJoyXInicio = (EditText) findViewById(R.id.eTJoyEsquerdaInicio);
        caracterJoyXFim = (EditText) findViewById(R.id.eTJoyEsquerdaFim);


        eTJoyDirIntervaloInicio = (EditText) findViewById(R.id.eTJoyDirIntervaloInicio);
        eTJoyDirIntervaloFim = (EditText) findViewById(R.id.eTJoyDirIntervaloFim);

        eTJoyEsqIntervaloInicio = (EditText) findViewById(R.id.eTJoyEsqIntervaloInicio);
        eTJoyEsqIntervaloFim = (EditText) findViewById(R.id.eTJoyEsqIntervaloFim);


        Intent intent = getIntent();
        if (intent != null) {
            Bundle params = intent.getExtras();
            if (params != null) {
                 ScaracterJoyYInicio = params.getString("caracterJoyYInicio");
                 ScaracterJoyYFim = params.getString("caracterJoyYFim");
                 ScaracterJoyXInicio = params.getString("caracterJoyXInicio");
                 ScaracterJoyXFim = params.getString("caracterJoyXFim");

                 SJoyDirIntervaloInicio = params.getString("jxii");
                SJoyDirIntervaloFim = params.getString("jxif");
                SJoyEsqIntervaloInicio = params.getString("jyii");
                SJoyEsqIntervaloFim = params.getString("jyif");

            }


        }


        caracterJoyXInicio.setText(ScaracterJoyXInicio);
        caracterJoyXFim.setText(ScaracterJoyXFim);
        caracterJoyYInicio.setText(ScaracterJoyYInicio);
        caracterJoyYFim.setText(ScaracterJoyYFim);

        eTJoyDirIntervaloInicio.setText(SJoyDirIntervaloInicio);
        eTJoyDirIntervaloFim.setText(SJoyDirIntervaloFim);
        eTJoyEsqIntervaloInicio.setText(SJoyEsqIntervaloInicio);
        eTJoyEsqIntervaloFim.setText(SJoyEsqIntervaloFim);

/*

        ManipularSharedPreferences sp1 = new ManipularSharedPreferences(this, getBaseContext());

        if(sp1.getDadosBoolean("preferencias_confis_joy", 0, "iniciado_joy_configs") == true)
        {

            caracterJoyYInicio.setText(sp1.getDadosString("preferencias_confis_joy", 0, "jxci"));
            caracterJoyYFim.setText(sp1.getDadosString("preferencias_confis_joy", 0, "jxcf"));

            caracterJoyXInicio.setText(sp1.getDadosString("preferencias_confis_joy", 0, "jyci"));
            caracterJoyXFim.setText(sp1.getDadosString("preferencias_confis_joy", 0, "jyci"));

            eTJoyDirIntervaloInicio.setText(sp1.getDadosString("preferencias_confis_joy", 0, "jxii"));
            eTJoyDirIntervaloFim.setText(sp1.getDadosString("preferencias_confis_joy", 0, "jxif"));

            eTJoyEsqIntervaloInicio.setText(sp1.getDadosString("preferencias_confis_joy", 0, "jyii"));
            eTJoyEsqIntervaloFim.setText(sp1.getDadosString("preferencias_confis_joy", 0, "jyif"));


        }
       else
        {


        }

*/






    }

    public void salvarConfigsJoys(View view) {
        try{
        String ScaracterJoyYInicio = caracterJoyYInicio.getText().toString();
        String ScaracterJoyYFim = caracterJoyYFim.getText().toString();

        String ScaracterJoyXInicio = caracterJoyXInicio.getText().toString();
        String ScaracterJoyXFim = caracterJoyXFim.getText().toString();

        String IJoyDirIntervaloInicio = eTJoyDirIntervaloInicio.getText().toString();
        String IJoyDirIntervaloFim = eTJoyDirIntervaloFim.getText().toString();


        String IJoyEsqIntervaloInicio = eTJoyEsqIntervaloInicio.getText().toString();
        String IJoyEsqIntervaloFim = eTJoyEsqIntervaloFim.getText().toString();

        int JoyDirIntervaloInicio = Integer.parseInt(IJoyDirIntervaloInicio);
        int JoyDirIntervaloFim = Integer.parseInt(IJoyDirIntervaloFim);


        int JoyEsqIntervaloInicio = Integer.parseInt(IJoyEsqIntervaloInicio);
        int JoyEsqIntervaloFim = Integer.parseInt(IJoyEsqIntervaloFim);




        int escopoJoyDir = JoyDirIntervaloFim - JoyDirIntervaloInicio;
        int escopoJoyEsq = JoyEsqIntervaloFim - JoyEsqIntervaloInicio;




            /*Intent intent = new Intent();
            Bundle parametros = new Bundle();
            parametros.putString("caracterJoyYInicio", ScaracterJoyYInicio);
            parametros.putString("caracterJoyYFim", ScaracterJoyYFim);
            parametros.putString("caracterJoyXInicio", ScaracterJoyXInicio);
            parametros.putString("caracterJoyXFim", ScaracterJoyXFim);

            parametros.putInt("escopoJoyDir", escopoJoyDir);
            parametros.putInt("escopoJoyEsq", escopoJoyEsq);
          */

            ManipularSharedPreferences sp1 = new ManipularSharedPreferences(this, getBaseContext());
            sp1.setDados("preferencias_confis_joy", 0, "jxci", ScaracterJoyXInicio);
            sp1.setDados("preferencias_confis_joy", 0, "jxcf", ScaracterJoyXFim);
            sp1.setDados("preferencias_confis_joy", 0, "jyci", ScaracterJoyYInicio);
            sp1.setDados("preferencias_confis_joy", 0, "jycf", ScaracterJoyYFim);

            sp1.setDados("preferencias_confis_joy", 0, "jxii", Integer.toString(JoyDirIntervaloInicio));
            sp1.setDados("preferencias_confis_joy", 0, "jxif", Integer.toString(JoyDirIntervaloFim));
            sp1.setDados("preferencias_confis_joy", 0, "jyii", Integer.toString(JoyEsqIntervaloInicio));
            sp1.setDados("preferencias_confis_joy", 0, "jyif", Integer.toString(JoyEsqIntervaloFim));
            sp1.setDados("preferencias_confis_joy", 0, "escopoJoyDir", Integer.toString(escopoJoyDir));
            sp1.setDados("preferencias_confis_joy", 0, "escopoJoyEsq", Integer.toString(escopoJoyEsq));



            //intent.putExtras(parametros);
            //setResult(1, intent);
            finish();


        }catch (NumberFormatException e)
        {
            Toast.makeText(this,"Apenas Caracteres especiais aceitos", Toast.LENGTH_SHORT).show();
        }


    }

}