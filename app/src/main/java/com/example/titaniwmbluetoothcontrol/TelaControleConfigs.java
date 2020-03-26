package com.example.titaniwmbluetoothcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TelaControleConfigs extends AppCompatActivity {

    EditText edDelay;
    Button btnSalvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_controle_configs);

        edDelay = (EditText) findViewById(R.id.eTDelay);
        btnSalvar = (Button) findViewById(R.id.btnSalvarConfigs);

        Intent intent = getIntent();
        if(intent != null)
        {
            Bundle parametros = intent.getExtras();
            if(parametros != null)
            {
                int delay = parametros.getInt("delay");
                edDelay.setText(Integer.toString(delay));
            }

        }






    }


    public void salvarConfigs(View view)
    {
        try{
            Integer.parseInt(edDelay.getText().toString());
            Intent intent = new Intent();
            Bundle parametros = new Bundle();
            parametros.putInt("delay", Integer.parseInt(edDelay.getText().toString()));

            intent.putExtras(parametros);
            setResult(1, intent);
            finish();

        }catch (NumberFormatException e)
        {
            Toast.makeText(this,"Apenas números no campo Delay", Toast.LENGTH_SHORT).show();
        }

    }



}

