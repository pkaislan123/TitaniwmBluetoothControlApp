package com.example.titaniwmbluetoothcontrol;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.titaniwmbluetoothcontrol.interfaces.TratarDados;
import com.github.anastr.speedviewlib.Speedometer;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;

import io.github.controlwear.virtual.joystick.android.JoystickView;

import static java.lang.Thread.sleep;

public class TelaPersonalizada extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ConexaoThread connect;

    int delay = 10;

    private boolean clicando []= new boolean[50] ;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private int contadorComponentes = 1;
    private TextView tvRegex[] = new TextView[50];

     private String regexInicio[] = new String[50];
    private String regexFim[] = new String[50];

    private TextView tvTelaPersonalizadaNomeLayout;

    private ArrayList<Componente> componentes = new ArrayList<>();

   private ConstraintLayout layoutPrincipal;

    int contadorInfos = 0;
    int contadorBotaoPressionado = 0;
    boolean pressionado[] = new boolean[50];
    private String nomeArquivo;
    private int orientacao;

    private ConstraintLayout layoutInfos[] = new ConstraintLayout[50];

    boolean ativo = false;

    Speedometer conta_giros [] = new Speedometer[50];
    Termometro termometros[] = new Termometro[50];
    Speedometer velocimetros [] = new Speedometer[50];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent self = getIntent();
        if (self != null) {
            Bundle parametros = self.getExtras();
            if (parametros != null) {
                nomeArquivo = parametros.getString("arquivo");
            }
        }

        ManipularArquivos manipularArquivos = new ManipularArquivos(this);
        String lidoArquivo = manipularArquivos.ler("LayoutsPersonalizados", nomeArquivo);
        String[] linhas = lidoArquivo.split("\n");
        for (int i = 0; i < linhas.length; i++) {
            Log.i("Conteudo", "linha " + i + "e: " + linhas[i]);
        }
        orientacao = Integer.parseInt(linhas[1].replaceAll("[^0-9]+", ""));

        if (orientacao == 1)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_tela_personalizada);

        tvTelaPersonalizadaNomeLayout = (TextView) findViewById(R.id.tvTelaPersonalizadaNomeLayout);

        tvTelaPersonalizadaNomeLayout.setText(linhas[0]);

        connect = new ConexaoThread();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutTelaPersonalizada);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, null, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navView_tela_personalizada);
        navigationView.setNavigationItemSelectedListener(this);
        layoutPrincipal = (ConstraintLayout) findViewById(R.id.meuLayoutPersonalizado);

        TratarDados tratarDados = new TratarDados(lidoArquivo);
        delay = Integer.parseInt(tratarDados.tratar("delay", "&"));
       // delay = Integer.parseInt(linhas[linhas.length - 2].replaceAll("[^0-9]+", ""));
        String fundo = tratarDados.tratar("fundo", "&");
        setar_plano_fundo(fundo);

      // try{


           for (int i = 2; i < linhas.length - 2; i++) {


            String[] detalhes = linhas[i].split(";");
            TratarDados separar = new TratarDados(linhas[i]);

            if(linhas[i].contains("identificacao")) {
                Componente componente = new Componente();

                Log.i("Verificar", linhas[i]);
                componente.setIdComponente(Integer.parseInt(separar.tratar("identificacao", "&")));
                componente.setNomeComponente(separar.tratar("nome", "&"));
                componente.setTipo(separar.tratar("tipoComponente", "&"));
                componente.setPositionX(Integer.parseInt(separar.tratar("posicaoX", "&")));
                componente.setPositionY(Integer.parseInt(separar.tratar("posicaoY", "&")));
                Log.i("Detalhes", " " + separar.tratar("tipo", "&"));

                if (componente.getTipo().equals("botao")) {

                    componente.setCaracterEnvio(separar.tratar("caracterEnvioBotao1", "&"));

                    componente.setTipoBotao(Integer.parseInt(separar.tratar("tipoBotao1", "&")));
                    Log.i("TipoBotao1", separar.tratar("tipoBotao1", "&"));

                    componente.setRotacaoBotao(Integer.parseInt(separar.tratar("rotacaoBotao1", "&")));
                    int modoOperacaoBotao = Integer.parseInt(separar.tratar("modoOperacaoBotao", "&"));
                    componente.setModoOperacaoBotao(modoOperacaoBotao);
                    if (modoOperacaoBotao == 1) {

                    } else if (modoOperacaoBotao == 2) {
                        componente.setTipoBotao2(Integer.parseInt(separar.tratar("tipoBotao2", "&")));
                        componente.setRotacaoBotao2(Integer.parseInt(separar.tratar("rotacaoBotao2", "&")));
                        componente.setCaracterEnvio2(separar.tratar("caracterEnvioBotao2", "&"));
                        Log.i("TipoBotao1", separar.tratar("tipoBotao2", "&"));


                    } else if (modoOperacaoBotao == 3) {
                        componente.setCaracterEnvio2(separar.tratar("caracterEnvioBotao2", "&"));
                    }

                } else if (componente.getTipo().equals("seekbar")) {
                    componente.setChaveInicio(separar.tratar("chaveInicio", "&"));
                    componente.setChaveFim(separar.tratar("chaveFim", "&"));
                    componente.setIntervaloInicio(Integer.parseInt(separar.tratar("intervaloInicio", "&")));
                    componente.setIntervaloFim(Integer.parseInt(separar.tratar("intervaloFim", "&")));
                } else if (componente.getTipo().equals("contagiros")) {
                    componente.setChaveInicio(separar.tratar("regexInicio", "&"));
                    componente.setChaveFim(separar.tratar("regexFim", "&"));
                    componente.setTipoBotao(Integer.parseInt(separar.tratar("tipoContaGiros", "&")));
                } else if (componente.getTipo().equals("joystick")) {

                    if (separar.tratar("checkX", "&").equals("true")) {
                        componente.setEixoX(true);

                    } else {
                        componente.setEixoX(false);

                    }

                    componente.setChaveInicioEixoX(separar.tratar("chaveInicioEixoX", "&"));
                    componente.setChaveFimEixoX(separar.tratar("chaveFimEixoX", "&"));
                    componente.setChaveFimInverterEixoX((separar.tratar("chaveInverterEixoX", "&")));


                    if (separar.tratar("checkY", "&").equals("true")) {
                        componente.setEixoY(true);

                    } else {
                        componente.setEixoY(false);

                    }

                    componente.setChaveInicioEixoY(separar.tratar("chaveInicioEixoY", "&"));
                    componente.setChaveFimEixoY(separar.tratar("chaveFimEixoY", "&"));
                    componente.setChaveFimInverterEixoY((separar.tratar("chaveInverterEixoY", "&")));

                    //mais detalhes   + componentes.get(i).getIntervaloInicioEixoX() + ";"
                    //                                        + componentes.get(i).getIntervaloFimEixoX() + ";"
                    //                                        + componentes.get(i).getEscopoEixoX() + ";"
                    //                                        + componentes.get(i).getModoOperacaoEixoX() + ";"
                    //
                    //                                         + componentes.get(i).getIntervaloInicioEixoY() + ";"
                    //                                         + componentes.get(i).getIntervaloFimEixoY() + ";"
                    //                                         + componentes.get(i).getEscopoEixoY() + ";"
                    //                                         + componentes.get(i).getModoOperacaoEixoY() + ";"


                    componente.setIntervaloInicioEixoX(Integer.parseInt(separar.tratar("intervaloInicioEixoX", "&")));
                    componente.setIntervaloFimEixoX(Integer.parseInt(separar.tratar("intervaloFimEixoX", "&")));
                    componente.setEscopoEixoX(Integer.parseInt(separar.tratar("escopoEixoX", "&")));
                    componente.setModoOperacaoEixoX(Integer.parseInt(separar.tratar("modoOperacaoEixoX", "&")));

                    componente.setIntervaloInicioEixoY(Integer.parseInt(separar.tratar("intervaloInicioEixoY", "&")));
                    componente.setIntervaloFimEixoY(Integer.parseInt(separar.tratar("intervaloFimEixoY", "&")));
                    componente.setEscopoEixoY(Integer.parseInt(separar.tratar("escopoEixoY", "&")));
                    componente.setModoOperacaoEixoY(Integer.parseInt(separar.tratar("modoOperacaoEixoY", "&")));


                } else if (componente.getTipo().equals("info")) {
                    componente.setCaracterEnvio(separar.tratar("texto", "&;"));
                    componente.setChaveFim(separar.tratar("regexFim", "&;"));
                    componente.setChaveInicio(separar.tratar("regexInicio", "&;"));
                    componente.setTipoBotao(Integer.parseInt(separar.tratar("fundo", "&;")));
                } else if (componente.getTipo().equals("termometro")) {
                    componente.setChaveFim(separar.tratar("regexFim", "&;"));
                    componente.setChaveInicio(separar.tratar("regexInicio", "&;"));
                    componente.setTipoBotao(Integer.parseInt(separar.tratar("tipoMedir", "&;")));
                } else if (componente.getTipo().equals("velocimetro")) {
                    componente.setChaveFim(separar.tratar("regexFim", "&;"));
                    componente.setChaveInicio(separar.tratar("regexInicio", "&;"));
                    componente.setTipoBotao(Integer.parseInt(separar.tratar("tipoVelocimetro", "&;")));
                }

                componentes.add(componente);
            }

        }
    //}
        /*catch(Exception e){
            Toast.makeText(getBaseContext(), "Erro ao Adicionar Componente", Toast.LENGTH_SHORT).show();
        } */


        for(int i = 0; i < componentes.size(); i++)
        {
         //   Toast.makeText(this, "Id componente: " + componentes.get(i).getIdComponente(), Toast.LENGTH_SHORT).show();
          //  Toast.makeText(this, "Nome componente: " + componentes.get(i).getNomeComponente(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "Tipo componente: " + componentes.get(i).getTipo(), Toast.LENGTH_SHORT).show();

            //Toast.makeText(this, "Caracter envio: " + componentes.get(i).getCaracterEnvio(),  Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "Position X: " + componentes.get(i).getPositionX(), Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "Position Y: " + componentes.get(i).getPositionY(), Toast.LENGTH_SHORT).show();



         if(componentes.get(i).getTipo().equals("botao"))
         {
             addNewButton(componentes.get(i) );
         }
         else if(componentes.get(i).getTipo().equals("seekbar"))
         {
             addNewSeekBar(componentes.get(i));
         }
         else if(componentes.get(i).getTipo().equals("joystick"))
         {
           //  Toast.makeText(this, "X ativo: " + componentes.get(i).isEixoX(), Toast.LENGTH_SHORT).show();
            // Toast.makeText(this, "caracter inicio x: " + componentes.get(i).getChaveInicioEixoX(), Toast.LENGTH_SHORT).show();
             //Toast.makeText(this, "caracter fim x: " + componentes.get(i).getChaveFimEixoX(), Toast.LENGTH_SHORT).show();

             //Toast.makeText(this, "Y ativo: " + componentes.get(i).isEixoY(),  Toast.LENGTH_SHORT).show();
             //Toast.makeText(this, "caracter inicio y: " + componentes.get(i).getChaveInicioEixoY(), Toast.LENGTH_SHORT).show();
             //Toast.makeText(this, "caracter fim y: " + componentes.get(i).getChaveFimEixoY(), Toast.LENGTH_SHORT).show();

             addNewJoystick(componentes.get(i));
         }else if(componentes.get(i).getTipo().equals("info")){
             addNewInfo(componentes.get(i));
         }
         else if(componentes.get(i).getTipo().equals("contagiros")){
             addNewContaGiros(componentes.get(i));
         }
         else if(componentes.get(i).getTipo().equals("termometro")){
             addNewTermometro(componentes.get(i));
         }
         else if(componentes.get(i).getTipo().equals("velocimetro")){
             addNewVelocimetro(componentes.get(i));
         }


        }


        View decorView = getWindow().getDecorView();
// Esconde tanto a barra de navegação e a barra de status .

        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        decorView.setOnSystemUiVisibilityChangeListener
                (new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        // Note that system bars will only be "visible" if none of the
                        // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            decorView.setSystemUiVisibility(
                                    View.SYSTEM_UI_FLAG_IMMERSIVE
                                            // Set the content to appear under the system bars so that the
                                            // content doesn't resize when the system bars hide and show.
                                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                            // Hide the nav bar and status bar
                                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
                        } else {
                            // TODO: The system bars are NOT visible. Make any desired
                            // adjustments to your UI, such as hiding the action bar or
                            // other navigational controls.
                        }
                    }
                });


    }


    public void setar_plano_fundo(String path) {
        File img = new File(path);
        if (img.exists()) {

            Bitmap bitmap = BitmapFactory.decodeFile(img.getAbsolutePath());
            Drawable dr = new BitmapDrawable(bitmap);
            layoutPrincipal.setBackground(dr);

        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        connect = ((BaseAplicacao) this.getApplicationContext()).getConnect();

        super.onResume();
        connect = ((BaseAplicacao) getBaseContext().getApplicationContext()).getConnect();
        if(connect.getestaRodando())
        {
            ativo = true;
            connect.setPersonalizadaAtivo(true, this);
        }
        else
        {
            ativo = false;
        }

    }

    @Override
    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch(menuItem.getItemId())
        {

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public void addNewVelocimetro(Componente componente){
        regexInicio[contadorComponentes] = componente.getChaveInicio();
        regexFim[contadorComponentes] = componente.getChaveFim();


        layoutInfos[contadorComponentes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_velocimetro, null);
        layoutInfos[contadorComponentes].setId(componente.getIdComponente());


        LinearLayout area_velocimetro = layoutInfos[contadorComponentes].findViewById(R.id.area_velocimetro);
        if(componente.getTipoBotao() == 1){
            LinearLayout velocimetro = (LinearLayout) getLayoutInflater().inflate(R.layout.velocimetro1, null);
            area_velocimetro.addView(velocimetro);
            velocimetros[contadorComponentes] = velocimetro.findViewById(R.id.speedView);

        }
       else if(componente.getTipoBotao()== 2){
            LinearLayout velocimetro = (LinearLayout) getLayoutInflater().inflate(R.layout.velocimetro2, null);
            area_velocimetro.addView(velocimetro);
            velocimetros[contadorComponentes] = velocimetro.findViewById(R.id.awesomeSpeedometer);

        }
        else if(componente.getTipoBotao() == 3){
            LinearLayout velocimetro = (LinearLayout) getLayoutInflater().inflate(R.layout.velocimetro3, null);
            area_velocimetro.addView(velocimetro);
            velocimetros[contadorComponentes] = velocimetro.findViewById(R.id.deluxeSpeedView);

        }
        velocimetros[contadorComponentes].setId(componente.getIdComponente());



        ConstraintSet set = new ConstraintSet();

        layoutPrincipal.addView(layoutInfos[contadorComponentes]);
        set.clone(layoutPrincipal);
        set.connect(layoutInfos[contadorComponentes].getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, componente.getPositionY());
        // set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, meuLayout[contadorBotoes].getBottom());
        //set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, meuLayout[contadorBotoes].getRight());
        set.connect(layoutInfos[contadorComponentes].getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, componente.getPositionX());
        //set.constrainHeight(layoutInfos[contadorInfos] .getId(),layoutInfos[contadorInfos] .getMinHeight());

        set.applyTo(layoutPrincipal);

        contadorComponentes++;


    }

    public void addNewTermometro(Componente componente) {
        regexInicio[contadorComponentes] = componente.getChaveInicio();
        regexFim[contadorComponentes] = componente.getChaveFim();


        layoutInfos[contadorComponentes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_termometro, null);
        layoutInfos[contadorComponentes].setId(componente.getIdComponente());


        //TextView tvNomeTermometro = layoutInfos[contadorComponentes].findViewById(R.id.tvNometTermometro);
        //tvNomeTermometro.setText(componente.getNomeComponente());
        termometros[contadorComponentes] = layoutInfos[contadorComponentes].findViewById(R.id.termometro);
        termometros[contadorComponentes].setId(componente.getIdComponente());

        if (componente.getTipoBotao() == 0)
            termometros[contadorComponentes].changeUnit(true);
        else if (componente.getTipoBotao() == 1)
            termometros[contadorComponentes].changeUnit(false);


            ConstraintSet set = new ConstraintSet();

            layoutPrincipal.addView(layoutInfos[contadorComponentes]);
            set.clone(layoutPrincipal);
            set.connect(layoutInfos[contadorComponentes].getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, componente.getPositionY());
            // set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, meuLayout[contadorBotoes].getBottom());
            //set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, meuLayout[contadorBotoes].getRight());
            set.connect(layoutInfos[contadorComponentes].getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, componente.getPositionX());
            //set.constrainHeight(layoutInfos[contadorInfos] .getId(),layoutInfos[contadorInfos] .getMinHeight());

            set.applyTo(layoutPrincipal);

            contadorComponentes++;


    }

    public void addNewContaGiros(Componente componente){

        regexInicio[contadorComponentes] = componente.getChaveInicio();
        regexFim[contadorComponentes] = componente.getChaveFim();


        layoutInfos[contadorComponentes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_contagiros, null);
        layoutInfos[contadorComponentes].setId(componente.getIdComponente());


        //TextView tvNomeContaGiros = layoutInfos[contadorComponentes].findViewById(R.id.tvNomeContaGiros);
        //tvNomeContaGiros.setText(componente.getNomeComponente());

        if(componente.getTipoBotao() == 1){
            LinearLayout area_contagiros = layoutInfos[contadorComponentes].findViewById(R.id.area_contagiros);
            LinearLayout contagiros = (LinearLayout) getLayoutInflater().inflate(R.layout.contagiros1, null);
            area_contagiros.addView(contagiros);
            conta_giros[contadorComponentes] = area_contagiros.findViewById(R.id.id_contagiros1);

        }
        if(componente.getTipoBotao() == 2){

            LinearLayout area_contagiros = layoutInfos[contadorComponentes].findViewById(R.id.area_contagiros);
            LinearLayout contagiros = (LinearLayout) getLayoutInflater().inflate(R.layout.contagiros2, null);
            area_contagiros.addView(contagiros);
            conta_giros[contadorComponentes] = area_contagiros.findViewById(R.id.id_contagiros2);

        }
        else if(componente.getTipoBotao() == 3){
            LinearLayout area_contagiros = layoutInfos[contadorComponentes].findViewById(R.id.area_contagiros);
            LinearLayout contagiros = (LinearLayout) getLayoutInflater().inflate(R.layout.contagiros3, null);
            area_contagiros.addView(contagiros);
            conta_giros[contadorComponentes] = area_contagiros.findViewById(R.id.id_contagiros3);


        }


        ConstraintSet set = new ConstraintSet();

        layoutPrincipal.addView(layoutInfos[contadorComponentes] );
        set.clone(layoutPrincipal);
        set.connect(layoutInfos[contadorComponentes] .getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, componente.getPositionY());
        // set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, meuLayout[contadorBotoes].getBottom());
        //set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, meuLayout[contadorBotoes].getRight());
        set.connect(layoutInfos[contadorComponentes].getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, componente.getPositionX());
        //set.constrainHeight(layoutInfos[contadorInfos] .getId(),layoutInfos[contadorInfos] .getMinHeight());

        set.applyTo(layoutPrincipal);

        contadorComponentes++;

    }


  public void addNewSeekBar(Componente componente)
  {
      String SnomeSeekBar = componente.getNomeComponente();
      String ScaracterEnvio = componente.getCaracterEnvio();
      // dados[contadorBotoes]=ScaracterEnvio;
      ConstraintLayout meuLayoutSeekBar = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_seek_bar, null);
      meuLayoutSeekBar.setId(contadorComponentes);



      ConstraintSet set = new ConstraintSet();
      SeekBar novoSeeBar = (SeekBar) meuLayoutSeekBar.findViewById(R.id.add_new_seek_bar);
      TextView nomeComponente = (TextView) meuLayoutSeekBar.findViewById(R.id.tvNewSeekBarNome);
      nomeComponente.setText(SnomeSeekBar);

      //novoSeeBar.setText(SnomeSeekBar);
      novoSeeBar.setId(contadorComponentes);
      //TextView tvCaracter = (TextView) meuLayoutBotao.findViewById(R.id.caracter_new_button);
      //tvCaracter.setText(ScaracterEnvio);
      novoSeeBar.setMax(componente.getIntervaloFim());
      novoSeeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
          @Override
          public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              ControleSeekBar controle = new ControleSeekBar();
              String Dados = componente.getChaveInicio().concat(Integer.toString(progress).concat(componente.getChaveFim()));
              controle.setDados(Dados);
              controle.execute();
          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {

          }

          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {

          }
      });


      layoutPrincipal.addView(meuLayoutSeekBar);

      set.clone(layoutPrincipal);
      set.connect(meuLayoutSeekBar.getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, componente.getPositionY());
      set.connect(meuLayoutSeekBar.getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, componente.getPositionX());
      //set.constrainHeight(meuLayoutBotao.getId(), meuLayoutBotao.getHeight());
      set.applyTo(layoutPrincipal);

      contadorComponentes++;

  }

    public GradientDrawable setBackground(int cor, int formato)
    {

        GradientDrawable shape = new GradientDrawable();
        shape.setColor(cor);
        shape.setCornerRadius(20);
        shape.setStroke(5, Color.BLACK);
        if(formato == 2) {
            shape.setShape(GradientDrawable.RECTANGLE);
            //shape.setSize(106, 70);

        }

        else {
            shape.setSize(50, 50);
            shape.setShape(formato);

        }

        return shape;
    }

    public void addNewButton(Componente componente) {


        String SnomeButton = componente.getNomeComponente();
        String ScaracterEnvio = componente.getCaracterEnvio();
        String ScaracterEnvio2 = componente.getCaracterEnvio2();
        // dados[contadorBotoes]=ScaracterEnvio;
        ConstraintLayout meuLayoutBotao = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_button, null);
        meuLayoutBotao.setId(contadorComponentes);



        ConstraintSet set = new ConstraintSet();
        Button novoBotao = (Button) meuLayoutBotao.findViewById(R.id.add_new_button);
        novoBotao.setId(componente.getIdComponente());
        alterarFundoBotao(novoBotao, componente.getTipoBotao(), componente.getRotacaoBotao());

        novoBotao.setId(contadorComponentes);
        TextView tvCaracter = (TextView) meuLayoutBotao.findViewById(R.id.caracter_new_button);
        tvCaracter.setText(ScaracterEnvio);
        tvCaracter.setVisibility(View.INVISIBLE);
        tvCaracter.setEnabled(false);

        TextView tvCaracter2 = (TextView) meuLayoutBotao.findViewById(R.id.caracter_new_button2);
        tvCaracter2.setText(ScaracterEnvio2);
        tvCaracter2.setVisibility(View.INVISIBLE);
        tvCaracter2.setEnabled(false);

       /* novoBotao.setOnTouchListener((view, motionEvent) -> {

            Button cliqueBotao = (Button) view;


            int minhaPosicao = cliqueBotao.getId();
            Log.i("Tem", "O botao pressionado foi " + minhaPosicao);

            EnviarDados controle = new EnviarDados();
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (clicando == false) {
                        clicando = true;
                        controle.setDados(ScaracterEnvio);
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
        });
       */
        novoBotao.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, final MotionEvent motionEvent) {

                Button cliqueBotao = (Button) view;
                AnimationDrawable mAnimation = null;


                int minhaPosicao = cliqueBotao.getId();
                Log.i("Tem", "O botao pressionado foi " + minhaPosicao);

                EnviarDados controle = new EnviarDados();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (clicando[componente.getIdComponente()] == false) {
                            clicando[componente.getIdComponente()] = true;
                            Log.i("Tem", "E: " +  componente.getCaracterEnvio());
                            if(componente.getModoOperacaoBotao() == 1 ){
                                controle.setDados( componente.getCaracterEnvio(), componente.getIdComponente());

                            }else if(componente.getModoOperacaoBotao() == 2){
                                if(pressionado[componente.getIdComponente()]){
                                    controle.setDados( componente.getCaracterEnvio2(), componente.getIdComponente());
                                    pressionado[componente.getIdComponente()] = false;

                                    if(componente.getTipoBotao() == 8 || componente.getTipoBotao() == 9 || componente.getTipoBotao() == 10){
                                        cliqueBotao.clearAnimation();
                                    }else
                                        alterarFundoBotao(view, componente.getTipoBotao(), componente.getRotacaoBotao());


                                }else{
                                    controle.setDados( componente.getCaracterEnvio(), componente.getIdComponente());

                                    if(componente.getTipoBotao() == 8  || componente.getTipoBotao()  == 9 || componente.getTipoBotao()  == 10){
                                        cliqueBotao.startAnimation(
                                                AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotation) );

                                    }else
                                    alterarFundoBotao(view,componente.getTipoBotao2(), componente.getRotacaoBotao2() );
                                    pressionado[componente.getIdComponente()] = true;

                                }


                            }else if(componente.getModoOperacaoBotao()  == 3){
                                controle.setDados( componente.getCaracterEnvio(), componente.getIdComponente());
                                if(componente.getTipoBotao() == 8 || componente.getTipoBotao() == 9 || componente.getTipoBotao() == 10){
                                    // cliqueBotao.setBackgroundResource(R.drawable.animation_fan);
                                    //  mAnimation = (AnimationDrawable) cliqueBotao.getBackground();
                                    // mAnimation.start();
                                    cliqueBotao.startAnimation(
                                            AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotation) );
                                }

                            }

                            controle.execute();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(ativo) {
                            if(componente.getModoOperacaoBotao() ==1 ){

                            }else if(componente.getModoOperacaoBotao() ==2){

                            }else if(componente.getModoOperacaoBotao() ==3){
                                connect.write(componente.getCaracterEnvio2().getBytes());
                                if(componente.getTipoBotao() == 8 || componente.getTipoBotao() == 9 || componente.getTipoBotao() == 10) {
                                    cliqueBotao.clearAnimation();
                                }

                            }

                        }
                        clicando[componente.getIdComponente()] = false;

                        controle.cancel(true);


                        break;
                }
                return false;
            }

        });


      /*
        set.clone(layoutPrincipal);
        set.connect(meuLayoutBotao.getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, 90);
        // set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, meuLayout[contadorBotoes].getBottom());
        //set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, meuLayout[contadorBotoes].getRight());
        set.connect(meuLayoutBotao.getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, 5);
        set.constrainHeight(meuLayoutBotao.getId(), meuLayoutBotao.getHeight());
        set.applyTo(layoutPrincipal);


       */
        layoutPrincipal.addView(meuLayoutBotao);

        set.clone(layoutPrincipal);
      set.connect(meuLayoutBotao.getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, componente.getPositionY());
      set.connect(meuLayoutBotao.getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, componente.getPositionX());
      //set.constrainHeight(meuLayoutBotao.getId(), meuLayoutBotao.getHeight());
      set.applyTo(layoutPrincipal);

        contadorComponentes++;



    }


    /*public void alterarFundoBotao(View view, int tipoBotao, int rotacaoBotao){
        if(tipoBotao == 1)
        {

            view.setBackgroundResource(R.drawable.btnon);


        }

        else if(tipoBotao == 5)
        {
            view.setBackgroundResource(R.drawable.btnon);
        }
        else if(tipoBotao == 6)
        {
            view.setBackgroundResource(R.drawable.btnoff);
        }
        else if(tipoBotao == 7)
        {
            view.setBackgroundResource(R.drawable.btnstart);
        }
        else if(tipoBotao == 2)
        {
            view.setBackgroundResource(R.drawable.btnseta2);
            view.setRotation(rotacaoBotao);
        }
    }*/

    public void alterarFundoBotao(View view, int tipoBotao,  int rotacaoBotao){

        Log.i("AlterarFundo", "fundo é: " + tipoBotao);
        if(tipoBotao == 1)
        {

            view.setBackgroundResource(R.drawable.btnon);

        }

        else if(tipoBotao == 5)
        {
            view.setBackgroundResource(R.drawable.btnon);
        }
        else if(tipoBotao == 6)
        {
            view.setBackgroundResource(R.drawable.btnoff);
        }
        else if(tipoBotao == 7)
        {
            view.setBackgroundResource(R.drawable.btnstart);
        }
        else if(tipoBotao == 8)
        {
            view.setBackgroundResource(R.drawable.ventilador);
        }
        else if(tipoBotao == 9)
        {
            view.setBackgroundResource(R.drawable.fan2);
        }
        else if(tipoBotao == 10)
        {
            view.setBackgroundResource(R.drawable.fan3);
        }
        else if(tipoBotao == 11)
        {
            view.setBackgroundResource(R.drawable.btn_play);
        }
        else if(tipoBotao == 12)
        {
            view.setBackgroundResource(R.drawable.btn_pause);
        }

        else if(tipoBotao == 2)
        {
            view.setBackgroundResource(R.drawable.btnseta2);
            view.setRotation(rotacaoBotao);
        }
    }

    public void vibrar() {
        Vibrator rr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long milliseconds = 30;//'30' é o tempo em milissegundos, é basicamente o tempo de duração da vibração. portanto, quanto maior este numero, mais tempo de vibração você irá ter
        rr.vibrate(milliseconds);
    }


    public void addNewJoystick(Componente componente)
    {

        String SnomeJoystick = componente.getNomeComponente();

        String chaveInicioX = componente.getChaveInicioEixoX();
        String chaveFimX = componente.getChaveFimEixoX();

        String chaveInicioY = componente.getChaveInicioEixoY();
        String chaveFimY = componente.getChaveFimEixoY();


        // dados[contadorBotoes]=ScaracterEnvio;
        ConstraintLayout meuLayoutJoystick = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_joystick, null);
        meuLayoutJoystick.setId(componente.getIdComponente());




        ConstraintSet set = new ConstraintSet();
        JoystickView novoJoystick = (JoystickView) meuLayoutJoystick.findViewById(R.id.add_new_joystick);
        novoJoystick.setId(componente.getIdComponente());
        if (componente.isEixoX() && componente.isEixoY()) {
            novoJoystick.setButtonDirection (0);
        }else if(componente.isEixoX() && !componente.isEixoY()){
            novoJoystick.setButtonDirection (-1);
        }else{
            novoJoystick.setButtonDirection (1);

        }

        novoJoystick.setOnMoveListener(new OuvirJoystick( novoJoystick));

        TextView nomeJoystick = (TextView) meuLayoutJoystick.findViewById(R.id.nome_joystick_tela);

        nomeJoystick.setText(SnomeJoystick);


        layoutPrincipal.addView(meuLayoutJoystick);

        set.clone(layoutPrincipal);
        set.connect(meuLayoutJoystick.getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, componente.getPositionY());
        set.connect(meuLayoutJoystick.getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, componente.getPositionX());
        //set.constrainHeight(meuLayoutBotao.getId(), meuLayoutBotao.getHeight());
        set.applyTo(layoutPrincipal);

        contadorComponentes++;



    }
    public  class OuvirJoystick implements JoystickView.OnMoveListener
    {
        private View view;

        public OuvirJoystick(View view){
            this.view = view;

        }


        @Override
        public void onMove(int angle, int strength) {
            int id = view.getId();
            Componente componente = null;
            int posicao = -1;
            for(int i = 0 ; i < componentes.size(); i++){
                if(componentes.get(i).getIdComponente() == id){
                    posicao = i;
                    componente = componentes.get(i);
                    break;
                }
            }

            float xPercent = -1, yPercent = -1;

            if( angle > 0 && angle < 180){
                //y positivo
                yPercent = (float ) strength / 100;

            }else if (angle > 180 && angle < 360){
                //y negativo
                yPercent =  (float )  strength / 100 * -1;
            }else{}

            if(angle >= 0 && angle < 90 || angle > 270 && angle < 360){
                // x positivo
                xPercent =  (float )  strength / 100;

            }else if(angle > 90 && angle < 270) {
                //x negativo
                xPercent =  (float )  strength / 100 * -1;

            }else{}

            String caracter_final = null;

            if (componentes.get(posicao).isEixoX()) {
                int x = 0;
                String dados;
                if (componente.getModoOperacaoEixoX() == 0) {

                    if(strength == 0) {
                        dados = Integer.toString((int) componente.getEscopoEixoX() / 2);

                    }else {

                        float calc = xPercent * componente.getEscopoEixoX() / 2;
                        x = (int) calc + componente.getEscopoEixoX() / 2;
                        dados = Integer.toString(x);
                    }
                    caracter_final = componente.getChaveFimEixoX();

                    Log.i("Eixos",  "Eixo: X Modo: Dividir" + "Angulo: " + angle + "Strength: " + strength + " Eixo X:" + xPercent + " Enviar: " + dados );


                } else {
                    xPercent = xPercent * componente.getEscopoEixoX();
                    x = (int) xPercent;
                    if(x < 0)
                        x = x* -1;

                    if(strength == 0)
                        x = 0;
                    dados = Integer.toString(x);

                    if(xPercent <= 0)
                        caracter_final = componente.getChaveFimEixoX();
                    else if(xPercent > 0)
                        caracter_final = componente.getChaveFimInverterEixoX();
                    else{}

                    Log.i("Eixos",  "Eixo: X Modo: Inverter" + "Angulo: " + angle + "Strength: " + strength + " Eixo X:" + xPercent + " Enviar: " + dados );

                }
                //  Log.i("extras", "X percent: " + xPercent);
                ControleDirecaoX controleX = new ControleDirecaoX();
                String Dados = componente.getChaveInicioEixoX().concat(dados).concat(caracter_final);
                Log.i("String",   "Enviar: " +  Dados );

                controleX.setJoy(componentes.get(posicao), Dados);
                controleX.execute();
            }
            if (componentes.get(posicao).isEixoY()) {
                int y = 0;
                String dados;
                if (componente.getModoOperacaoEixoY() == 0) {

                    if(strength == 0) {
                        dados = Integer.toString((int) componente.getEscopoEixoY() / 2);

                    }else {
                        yPercent = yPercent *componente.getEscopoEixoY() / 2;
                        y = (int) xPercent + componente.getEscopoEixoY() / 2;
                        dados = Integer.toString(y);
                    }
                    caracter_final = componente.getChaveFimEixoY();

                    Log.i("Eixos",  "Eixo: Y Modo: Dividir" + "Angulo: " + angle + "Strength: " + strength + " Eixo Y:" + yPercent + " Enviar: " + dados );


                } else {
                    yPercent = yPercent * componente.getEscopoEixoY();
                    y = (int) yPercent;
                    if(y < 0)
                        y = y* -1;
                    if(strength == 0)
                        y = 0;
                    dados = Integer.toString(y);

                    if(yPercent <= 0)
                        caracter_final = componente.getChaveFimEixoY();
                    else if(yPercent > 0)
                        caracter_final = componente.getChaveFimInverterEixoY();
                    else{}

                    Log.i("Eixos",  "Eixo: Y Modo: Inverter" + "Angulo: " + angle + "Strength: " + strength + " Eixo Y:" + yPercent + " Enviar: " + dados );

                }
                //  Log.i("extras", "X percent: " + xPercent);
                ControleDirecaoY controleY = new ControleDirecaoY();
                String Dados = componente.getChaveInicioEixoY().concat(dados).concat(caracter_final);
                controleY.setJoy(componentes.get(posicao), Dados);
                controleY.execute();
            }



        }
    }


   /* @Override
    public void onJoystickMoved(float xPercent, float yPercent, int id) {

        Componente joy = new Componente();

        for(int i = 0; i < componentes.size(); i++)
        {
            if(id == componentes.get(i).getIdComponente())
            {
                joy = componentes.get(i);
                break;
            }
        }


        if(joy.isEixoX()) {
            int x = 0;
            String dados;
            if(joy.getModoOperacaoEixoX() == 0) {
                xPercent = xPercent * joy.getEscopoEixoX() / 2;
                x = (int) xPercent + joy.getEscopoEixoX() / 2;
                dados = Integer.toString(x);
            }
            else
            {
                xPercent = xPercent * joy.getEscopoEixoX() ;
                x = (int) xPercent;
                dados = Integer.toString(x);
            }
            //  Log.i("extras", "X percent: " + xPercent);
            ControleDirecaoX controleX = new ControleDirecaoX();
            controleX.setJoy(joy ,dados);
            controleX.execute();
        }
        if(joy.isEixoY())
        {
            int y = 0;
            String dados;
            if(joy.modoOperacaoEixoY == 0) {
                yPercent = yPercent * joy.getEscopoEixoY() / 2;
                y = (int) yPercent + joy.getEscopoEixoY() / 2;
                dados = Integer.toString(y);
            }
            else
            {
                Log.i("Valores","valor antes de aplica: "+ yPercent);
                yPercent = yPercent * joy.getEscopoEixoY() ;
                //y = (int) yPercent + escopoEixoY[id] ;
                y = (int) (yPercent );
                dados = Integer.toString(y);

                Log.i("Valores","porcetagem: "+ yPercent + "dado: " + dados);
            }

            //  Log.i("extras", "X percent: " + xPercent);
            ControleDirecaoY controleY = new ControleDirecaoY();
            controleY.setJoy(joy, dados);
            controleY.execute();
        }

    }
    */

/*

    @Override
    public void onJoystickMoved(float xPercent, float yPercent, int id) {
        Componente joy = new Componente();

        for(int i = 0; i < componentes.size(); i++)
        {
           if(id == componentes.get(i).getIdComponente())
           {
               joy = componentes.get(i);
               break;
           }
        }

        if(joy.isEixoX()) {

            xPercent = xPercent * 90;
            int x = (int) xPercent + 90;
            Log.i("extras", "x : " + x);
            String dados = Integer.toString(x);

            //  Log.i("extras", "X percent: " + xPercent);
            ControleDirecaoX controleX = new ControleDirecaoX();
            controleX.setJoy(joy ,dados);
            controleX.execute();
        }
        if(joy.isEixoY())
        {
            yPercent = yPercent * 90;
            int y = (int) yPercent + 90;
            Log.i("extras", "x : " + y);
            String dados = Integer.toString(y);

            //  Log.i("extras", "X percent: " + xPercent);
            ControleDirecaoY controleY = new ControleDirecaoY();
            controleY.setJoy(joy, dados);
            controleY.execute();
        }

    }
*/


    private class EnviarDados extends AsyncTask {

    String Dados;
    int posicao;

    public void setDados(String dados, int posicao) {
        this.Dados = dados;
        this.posicao = posicao;
    }



    @Override
    protected Object doInBackground(Object[] objects) {
        while (clicando[posicao]) {

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




    private class ControleSeekBar extends AsyncTask {

        String Dados;


        public void setDados(String dados) {
            this.Dados = dados;

        }

        @Override
        protected Object doInBackground(Object[] objects) {

            connect.write(Dados.getBytes());


            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("CONTROLE", "o botao chammou cancel");
        }
    }




    private class ControleDirecaoX extends AsyncTask {

        Componente joy;
        String dados;

        public void setJoy(Componente componente, String dados)
        {
            this.joy = componente;
            this.dados = dados;
        }


        @Override
        protected Object doInBackground(Object[] objects) {
            //String envio = joy.getChaveInicioEixoX().concat(dados).concat(joy.getChaveFimEixoX());


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


        Componente joy;
        String dados;

        public void setJoy(Componente componente, String dados)
        {
            this.joy = componente;
            this.dados = dados;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
           // String envio = joy.getChaveInicioEixoY().concat(dados).concat(joy.getChaveFimEixoY());

            connect.write(dados.getBytes());


            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("CONTROLE", "o botao chammou cancel");
        }
    }

    public void addNewInfo(Componente componente){


        layoutInfos[contadorComponentes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_info, null);
        layoutInfos[contadorComponentes] .setId(componente.getIdComponente());


         tvRegex[contadorComponentes] = layoutInfos[contadorComponentes] .findViewById(R.id.tvRegex);
        tvRegex[contadorComponentes].setId(componente.getIdComponente());
        tvRegex[contadorComponentes].setText("-----");

        TextView texto = layoutInfos[contadorComponentes].findViewById(R.id.tvTextoInfo);
        texto.setId(componente.getIdComponente());
        texto.setText(componente.getCaracterEnvio());

        regexInicio[contadorComponentes] = componente.getChaveInicio();
        regexFim[contadorComponentes] = componente.getChaveFim();

        ImageView fundo = (ImageView) layoutInfos[contadorComponentes] .findViewById(R.id.iVIconeInfo);
        alterarFundoBotaoInfo(fundo, componente.getTipoBotao());



        ConstraintSet set = new ConstraintSet();

        layoutPrincipal.addView(layoutInfos[contadorComponentes] );
        set.clone(layoutPrincipal);
        set.connect(layoutInfos[contadorComponentes] .getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, componente.getPositionY());
        // set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, meuLayout[contadorBotoes].getBottom());
        //set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, meuLayout[contadorBotoes].getRight());
        set.connect(layoutInfos[contadorComponentes].getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, componente.getPositionX());
        //set.constrainHeight(layoutInfos[contadorInfos] .getId(),layoutInfos[contadorInfos] .getMinHeight());

        set.applyTo(layoutPrincipal);


        contadorComponentes++;

    }


    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            Bundle bundle = msg.getData();
            byte[] data = bundle.getByteArray("data");
            String dataString = new String(data);

            for (int i = 0; i < contadorComponentes; i++) {
                if (layoutInfos[i] != null) {
                    if (layoutInfos[i].getTag().toString().equals("info") && layoutInfos[i].getTag() != null) {

                        TratarDados tratarDados = new TratarDados(dataString);
                        String regex = tratarDados.tratar(regexInicio[i], regexFim[i]);
                        // Toast.makeText(getBaseContext(), "texto: " + regex, Toast.LENGTH_SHORT).show();
                        tvRegex[i].setText(regex);

                    } else if (layoutInfos[i].getTag().toString().equals("contagiros") && layoutInfos[i].getTag() != null) {
                        Log.i("ContaGiros", "contagiros encontrado");

                        if (regexInicio[i] != null && regexFim[i] != null) {
                            TratarDados tratarDados = new TratarDados(dataString);
                            String regex = tratarDados.tratar(regexInicio[i], regexFim[i]);


                            if (conta_giros[i] != null) {
                                try {
                                    conta_giros[i].speedTo(Integer.parseInt(regex));
                                } catch (Exception f) {

                                }
                            }
                        }

                    }//fim conta giros
                    else if (layoutInfos[i].getTag().toString().equals("termometro") && layoutInfos[i].getTag() != null) {
                        {
                            Log.i("Termometro", "termometro encontrado");

                            if (regexInicio[i] != null && regexFim[i] != null) {
                                TratarDados tratarDados = new TratarDados(dataString);
                                String regex = tratarDados.tratar(regexInicio[i], regexFim[i]);


                                if (termometros[i] != null) {
                                    try {
                                        termometros[i].setTempAtual(Float.parseFloat(regex));
                                    } catch (Exception f) {

                                    }
                                }
                            }
                        }
                    }//fim do termometro
                    else if (layoutInfos[i].getTag().toString().equals("velocimetro") && layoutInfos[i].getTag() != null) {
                        Log.i("Velocimetro", "velocimetros encontrado");

                        if (regexInicio[i] != null && regexFim[i] != null) {
                            TratarDados tratarDados = new TratarDados(dataString);
                            String regex = tratarDados.tratar(regexInicio[i], regexFim[i]);


                            if (velocimetros[i] != null) {
                                try {
                                    velocimetros[i].speedTo(Integer.parseInt(regex));
                                } catch (Exception f) {

                                }
                            }
                        }
                    }//fim do velocimetro
                }


            }
        }
    };



    public void alterarFundoBotaoInfo(View view, int tipoFundo){
        if(tipoFundo == 1)
        {
            view.setBackgroundResource(R.drawable.borda);
        }
        else if(tipoFundo == 2)
        {
            view.setBackgroundResource(R.drawable.bluetooth);
        }
        else if(tipoFundo == 3)
        {
            view.setBackgroundResource(R.drawable.bluetoothdevice);
        }

    }



}
