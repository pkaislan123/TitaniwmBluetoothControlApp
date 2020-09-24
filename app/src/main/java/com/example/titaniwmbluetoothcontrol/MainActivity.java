package com.example.titaniwmbluetoothcontrol;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothHeadset;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

public class MainActivity extends AppCompatActivity {
    BluetoothHeadset bluetoothHeadset;
    public static final int telaprincipal = 1;
    // Get the default adapter
    TextView tvDevice;
    TextView status;
    ConexaoThread connect;
    ImageButton imgBtnControl;
   Button btnConexaoRapida;
    private Toolbar mTopoToolbar;
    ImageView iVMainStatusConection;
    ImageButton iBLayPerson;
    private ImageButton btnTerminal;
    AlertDialog alerta = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_FULLSCREEN);

        tvDevice = (TextView) findViewById(R.id.tVDevice);
        status = (TextView) findViewById(R.id.tVStatus);
        btnConexaoRapida = (Button) findViewById(R.id.btnConexaoRapida);
        imgBtnControl = (ImageButton) findViewById(R.id.imgBtnControl);
        iVMainStatusConection = (ImageView) findViewById(R.id.iVMainStatusConection);
        iBLayPerson = (ImageButton) findViewById(R.id.iBLayPerson);
        btnTerminal = (ImageButton) findViewById(R.id.btnTerminal);

        btnTerminal.setOnClickListener(v->
        {
            if(connect.getestaRodando()) {
                Intent terminal = new Intent(this, Terminal.class);
                startActivity(terminal);
            }
        });

        iBLayPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getBaseContext(), TelaEscolhaLayoutsPersonalizados.class);
               //Intent intent = new Intent(getBaseContext(), TelaPersonalizada.class);

                startActivity(intent);

            }
        });




        mTopoToolbar = (Toolbar) findViewById(R.id.inc_topo_toolbar);


      mTopoToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
           @Override
           public boolean onMenuItemClick(MenuItem menuItem) {
              // Intent it = null;

               switch (menuItem.getItemId()){
                   case R.id.action_conection:

                       Intent iten = new Intent(getBaseContext(), SegundaActivity.class);
                       startActivity(iten);

                       break;

                   case R.id.action_configs:



                       break;

                   case R.id.action_sair:

                       if (connect.getestaRodando()) {
                           connect.cancel();
                       }


                       finish();

                       break;

               }
               //startActivity(it);

               return true;
           }
       });

       mTopoToolbar.inflateMenu(R.menu.menu_topo_toolbar);




    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {
            hideui();
        }

    }



    private void hideui()
    {
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(SYSTEM_UI_FLAG_FULLSCREEN |
                SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                SYSTEM_UI_FLAG_IMMERSIVE|
                SYSTEM_UI_FLAG_LAYOUT_STABLE|
                SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
    }

    private void showUi()
    {
        View decor = getWindow().getDecorView().getRootView();
        decor.setSystemUiVisibility(
                SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                        SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                        SYSTEM_UI_FLAG_LAYOUT_STABLE
        );
    }

    public void conexaoRapida(View view)
    {
        if(btnConexaoRapida.getText().equals("Conectar")) {
            ConexaoRapida conectar = new ConexaoRapida(this);
            connect = conectar.procuraConexao();
        }else if(btnConexaoRapida.getText().equals("Desconectar"))
        {
            if(connect.getestaRodando())
            {
                connect.cancel();
                if(!connect.getestaRodando())
                {
                    status.setText("");
                    tvDevice.setText("Sem Conexão");
                    iVMainStatusConection.setImageResource(R.drawable.icondesconectado);
                    btnConexaoRapida.setText("Conectar");
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected  void onResume() {
        super.onResume();
        connect = new ConexaoThread();
        connect = ((BaseAplicacao)this.getApplicationContext()).getConnect();
        Log.i("Ciclo", "O resume foi chamado");
        Log.i("Ciclo", "O estado e : "+connect.getestaRodando());
        if (connect.getestaRodando()) {
            tvDevice.setText(connect.qualDispositivo());
            status.setText(("Conectado"));
            btnConexaoRapida.setText("Desconectar");
            iVMainStatusConection.setImageResource(R.drawable.iconconectado);

        }
        else
        {
            tvDevice.setText("");
            status.setText("Sem Conexão");
            iVMainStatusConection.setImageResource(R.drawable.icondesconectado);
        }

        permissoes();
        ManipularArquivos manipularArquivos = new ManipularArquivos(this);

   //Veririca se os diretorios que o app usara, existem


    }

    @Override
    protected void onPause() {

        super.onPause();
        Log.i("Ciclo", "O pause foi chamado");

    }
    @Override
    protected void onStop() {

        super.onStop();
        Log.i("Ciclo", "O stop foi chamado");

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        Log.i("Ciclo", "O onDestroy foi chamado");

        //connect.cancel();
       // finish();


    }

public void permissoes()
{
    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
    {
        //permissao garantida
    }
    else
    {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},3);
    }

    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
    {
        //permissao garantida
    }
    else
    {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
    }



}




    public void telaControle(View view)
    {
        Log.i("Ciclo", "O metodo telaControle foi chamada");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater li = getLayoutInflater();
        View v = li.inflate(R.layout.escolha_layout_controle, null);

        builder.setTitle("");
        builder.setView(v);
        alerta = builder.create();
        alerta.show();

        v.findViewById(R.id.iBEscolhaBtn).setOnClickListener(view1 -> {
            if(connect.getestaRodando()){

                Intent itent = new Intent(getBaseContext(), TelaControle.class);
                startActivity(itent);

            }
            else{
                Toast.makeText(getBaseContext(), "Não há Conexão ativa", Toast.LENGTH_SHORT).show();;
            }

        });

        v.findViewById(R.id.iBEscolhaJoy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(connect.getestaRodando()){

                    Intent itent = new Intent(getBaseContext(), TelaControleJoy.class);
                    startActivity(itent);

                }
                else{
                    Toast.makeText(getBaseContext(), "Não há Conexão ativa", Toast.LENGTH_SHORT).show();;
                }

            }
        });


       // alerta.getWindow().setLayout(600, 500);



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
                status.setText(("Erro ao Conectar"));
                tvDevice.setText("");
                iVMainStatusConection.setImageResource(R.drawable.icondesconectado);

            }
            //   statusMessage.setText("Ocorreu um erro durante a conexão D:");
            else if(dataString.equals("---S"))
            {
                status.setText(("Conectado"));

            }
            else if(dataString.equals("---C"))
            {
                status.setText(("SocketClienteC"));

            }
            else if(dataString.equals("--CS"))
            {
                status.setText(("SocketClienteCC"));
            }
            else if(dataString.equals("--CN"))
            {
                status.setText(("Falha de Conexao"));
            }
            else if(dataString.equals("--CO"))
            {
                status.setText(("Sucesso na Conexao"));
                       mandaConexao();

                    tvDevice.setText(connect.qualDispositivo());
                    status.setText(("Conectado"));
                    btnConexaoRapida.setText("Desconectar");
                    iVMainStatusConection.setImageResource(R.drawable.iconconectado);


            }
            else if(dataString.equals("--CH"))
            {
                status.setText("Falha ao transmitir dados");
            }


        }
    };

    public void mandaConexao()
    {
        ((BaseAplicacao)this.getApplicationContext()).setConnect(connect);

    }

    public void buscarConexao()
    {
        connect = ((BaseAplicacao)this.getApplicationContext()).getConnect();

    }


}
