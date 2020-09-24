package com.example.titaniwmbluetoothcontrol;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class TelaPersonalizada extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, JoyStickViewOfficial.JoystickListener{

    ConexaoThread connect;

    int delay = 10;

    private boolean clicando = false;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private int contadorComponentes = 1;


    private TextView tvTelaPersonalizadaNomeLayout;

    private ArrayList<Componente> componentes = new ArrayList<>();

   private ConstraintLayout layoutPrincipal;



    private String nomeArquivo;
    private int orientacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent self = getIntent();
        if(self != null)
        {
            Bundle parametros = self.getExtras();
            if(parametros != null)
            {
                nomeArquivo = parametros.getString("arquivo");
            }
        }

        ManipularArquivos manipularArquivos = new ManipularArquivos(this);
        String lidoArquivo = manipularArquivos.ler("LayoutsPersonalizados", nomeArquivo);
        String[] linhas = lidoArquivo.split("\n");
        for(int i = 0; i < linhas.length; i++)
        {
             Log.i("Conteudo", "linha " + i + "e: "+ linhas[i]);
        }
        orientacao = Integer.parseInt(linhas[1].replaceAll("[^0-9]+", ""));

        if(orientacao == 1)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_tela_personalizada);

        tvTelaPersonalizadaNomeLayout = (TextView) findViewById(R.id.tvTelaPersonalizadaNomeLayout) ;

        tvTelaPersonalizadaNomeLayout.setText(linhas[0]);

        connect = new ConexaoThread();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutTelaPersonalizada);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,null, R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navView_tela_personalizada);
        navigationView.setNavigationItemSelectedListener(this);
        layoutPrincipal = (ConstraintLayout) findViewById(R.id.meuLayoutPersonalizado);






        for(int i = 2; i < linhas.length-1; i++)
        {

            String[] detalhes = linhas[i].split(";");
            Componente componente = new Componente();
            Log.i("Componente", "Detalhes[0] :" + detalhes[0]);
            componente.setIdComponente(Integer.parseInt(detalhes[0]));
            componente.setNomeComponente(detalhes[1]);
            componente.setTipo(detalhes[2]);
            componente.setCaracterEnvio(detalhes[3]);
            componente.setPositionX(Integer.parseInt(detalhes[4]));
            componente.setPositionY(Integer.parseInt(detalhes[5]));

           if(componente.getTipo().equals("botao"))
           {

               componente.setTipoBotao(Integer.parseInt(detalhes[16]));


               componente.setRotacaoBotao(Integer.parseInt(detalhes[17]));
               componente.setCor(Integer.parseInt(detalhes[18]));
               componente.setFormato(Integer.parseInt(detalhes[19]));
           }

            if(componente.getTipo().equals("seekbar"))
            {
                componente.setChaveInicio(detalhes[6]);
                componente.setChaveFim(detalhes[7]);
                componente.setIntervaloInicio(Integer.parseInt(detalhes[8]));
                componente.setIntervaloFim(Integer.parseInt(detalhes[9]));
            }

            if(componente.getTipo().equals("joystick"))
            {

                if(detalhes[10].equals("true"))
                {
                    componente.setEixoX(true);

                }
                else
                {
                    componente.setEixoX(false);

                }

                componente.setChaveInicioEixoX(detalhes[11]);
                componente.setChaveFimEixoX(detalhes[12]);
                componente.setChaveFimInverterEixoX((detalhes[13]));


                if(detalhes[14].equals("true"))
                {
                    componente.setEixoY(true);

                }
                else
                {
                    componente.setEixoY(false);

                }

                componente.setChaveInicioEixoY(detalhes[15]);
                componente.setChaveFimEixoY(detalhes[16]);
                componente.setChaveFimInverterEixoY(detalhes[17]);

                //mais detalhes   + componentes.get(i).getIntervaloInicioEixoX() + ";"
                //                                        + componentes.get(i).getIntervaloFimEixoX() + ";"
                //                                        + componentes.get(i).getEscopoEixoX() + ";"
                //                                        + componentes.get(i).getModoOperacaoEixoX() + ";"
                //
                //                                         + componentes.get(i).getIntervaloInicioEixoY() + ";"
                //                                         + componentes.get(i).getIntervaloFimEixoY() + ";"
                //                                         + componentes.get(i).getEscopoEixoY() + ";"
                //                                         + componentes.get(i).getModoOperacaoEixoY() + ";"


                componente.setIntervaloInicioEixoX(Integer.parseInt(detalhes[18]));
                componente.setIntervaloFimEixoX(Integer.parseInt(detalhes[19]));
                componente.setEscopoEixoX(Integer.parseInt(detalhes[20]));
                componente.setModoOperacaoEixoX(Integer.parseInt(detalhes[21]));

                componente.setIntervaloInicioEixoY(Integer.parseInt(detalhes[22]));
                componente.setIntervaloFimEixoY(Integer.parseInt(detalhes[23]));
                componente.setEscopoEixoY(Integer.parseInt(detalhes[24]));
                componente.setModoOperacaoEixoY(Integer.parseInt(detalhes[25]));




            }


            componentes.add(componente);
        }


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
         }


        }




    }

    @Override
    public void onResume()
    {
        super.onResume();
        connect = ((BaseAplicacao) this.getApplicationContext()).getConnect();

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
        // dados[contadorBotoes]=ScaracterEnvio;
        ConstraintLayout meuLayoutBotao = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_button, null);
        meuLayoutBotao.setId(contadorComponentes);



        ConstraintSet set = new ConstraintSet();
        Button novoBotao = (Button) meuLayoutBotao.findViewById(R.id.add_new_button);

        if(componente.getTipoBotao() == 1)
        {
            novoBotao.setBackgroundResource(R.drawable.btnon);

        }
        else if(componente.getTipoBotao() == 2)
        {
            novoBotao.setBackgroundResource(R.drawable.btnseta2);
            novoBotao.setRotation(componente.getRotacaoBotao());
        }
        else if(componente.getTipoBotao() == 5)
        {
            novoBotao.setBackgroundResource(R.drawable.btnon);
        }
        else if(componente.getTipoBotao() == 6)
        {
            novoBotao.setBackgroundResource(R.drawable.btnoff);
        }
        else if(componente.getTipoBotao() == 7)
        {
            novoBotao.setBackgroundResource(R.drawable.btnstart);
        }
        else {
            novoBotao.setBackground(setBackground(componente.getCor(), componente.getFormato()));
            novoBotao.setText(SnomeButton);
        }

            novoBotao.setId(contadorComponentes);
        TextView tvCaracter = (TextView) meuLayoutBotao.findViewById(R.id.caracter_new_button);
        tvCaracter.setText(ScaracterEnvio);



        novoBotao.setOnTouchListener((view, motionEvent) -> {

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
        JoyStickViewOfficial novoJoystick = (JoyStickViewOfficial) meuLayoutJoystick.findViewById(R.id.add_new_joystick);
        novoJoystick.setId(componente.getIdComponente());
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

    @Override
    public void onJoystickMoved(float xPercent, float yPercent, int id) {

        Componente joy = new Componente();
        String caracter_final = null;

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


                    caracter_final = joy.getChaveFimEixoX();


            }
            else
            {
                xPercent = xPercent * joy.getEscopoEixoX() ;
                x = (int) xPercent;
                dados = Integer.toString(x);


                if(xPercent <= 0)
                    caracter_final = joy.getChaveFimEixoX();
                else if(xPercent > 0)
                    caracter_final = joy.getChaveFimInverterEixoX();
                else{}

            }
            //  Log.i("extras", "X percent: " + xPercent);
            ControleDirecaoX controleX = new ControleDirecaoX();
            controleX.setJoy(joy ,joy.getChaveInicioEixoX().concat(dados).concat(caracter_final));
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
                caracter_final = joy.getChaveFimEixoY();

            }
            else
            {
                Log.i("Valores","valor antes de aplica: "+ yPercent);
                yPercent = yPercent * joy.getEscopoEixoY() ;
                //y = (int) yPercent + escopoEixoY[id] ;
                y = (int) (yPercent );
                dados = Integer.toString(y);

                if(yPercent <= 0)
                    caracter_final = joy.getChaveFimEixoY();
                else if(yPercent > 0)
                    caracter_final = joy.getChaveFimInverterEixoY();
                else{}

                Log.i("Valores","porcetagem: "+ yPercent + "dado: " + dados);
            }

            //  Log.i("extras", "X percent: " + xPercent);
            ControleDirecaoY controleY = new ControleDirecaoY();
            controleY.setJoy(joy ,joy.getChaveInicioEixoY().concat(dados).concat(caracter_final));
            controleY.execute();
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

    public void setPosicao(int posicao)
    {
        this.posicao = posicao;
    }

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

    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            Bundle bundle = msg.getData();
            byte[] data = bundle.getByteArray("data");
            String dataString = new String(data);




        }
    };




}
