package com.example.titaniwmbluetoothcontrol;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.TRANSPARENT;
import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
import static android.view.View.SYSTEM_UI_FLAG_LOW_PROFILE;
import static java.lang.Thread.sleep;


public class TelaNovoLayoutsPersonalizados extends AppCompatActivity implements JoyStickViewOfficial.JoystickListener, NavigationView.OnNavigationItemSelectedListener{
    ConstraintLayout meuLayout[] = new ConstraintLayout[50];
    ConstraintLayout layoutPrincipal;
    ConstraintLayout.LayoutParams meuParametros[] = new ConstraintLayout.LayoutParams[50];
    String SnomeButton;

    ArrayList<String> pinsComponentes = new ArrayList<>();

    private LinearLayout exlcuir;
    int corButton;
    int formatoButton;
    String dados[] = new String[50];
    int corText;
    boolean sinal[] = new boolean[50];
    String ScaracterEnvio;
    Spinner comboCorBotao;
    Spinner comboCorTexto;
    int delay = 5;
    Button btnAddNewButton;
    Button btnFinalAddNewButton;
    Context isto;
    ConexaoThread connect;
    boolean clicando = false;

    boolean modoConfigs = false;
    boolean modoPosicionar = false;
    boolean modoTestar = false;

    public ArrayList<EditModel> editModelArrayList;

    private CheckBox checkBoxDividirEixoXNovaTelaPersonalizada[] = new CheckBox[50];
    private CheckBox checkBoxDividirEixoYNovaTelaPersonalizada[] = new CheckBox[50];

    boolean ativo = false;

    private CheckBox checkBoxInverterEixoXNovaTelaPersonalizada[] = new CheckBox[50];
    private CheckBox checkBoxInverterEixoYNovaTelaPersonalizada[] = new CheckBox[50];



//variaveis de escopo para novos joysticks
    //#######################################

    private EditText eTIntervaloInicioEixoXNovaTelaPersonalizada[] = new EditText[50];
    private EditText eTIntervaloFimEixoXNovaTelaPersonalizada[] = new EditText[50];

    private int escopoEixoX[] = new int[50];


    private EditText eTIntervaloInicioEixoYNovaTelaPersonalizada[] = new EditText[50];
    private EditText eTIntervaloFimEixoYNovaTelaPersonalizada[] = new EditText[50];

    private EditText eTIntervaloFimInverterXNovaTelaPersonalizada[] = new EditText[50];
    private EditText eTIntervaloFimInverterYNovaTelaPersonalizada[] = new EditText[50];

    private int escopoEixoY[] = new int[50];


    private int modoOperacaoX[] = new int[50];
    private int modoOperacaoY[] = new int[50];

    int intervaloInicioX[] = new int[50];
    int intervaloFimX[] = new int [50];

    int intervaloInicioY []= new int [50];
    int intervaloFimY []= new int [50];

    //#######################################33

    CheckBox checkBoxBtntp1[] = new CheckBox[50];
    CheckBox checkBoxBtnSeta[] = new CheckBox[50];


    Button novosBotoes[] = new Button[50];
    ImageView novosVolantes[] = new ImageView[50];
    int tipoBotao[] = new int[50];
    int rotacaoBotao[] = new int[50];

    TextView novosTextViewCaracter[] = new TextView[50];
    int contadorBotoes = 1;

    JoyStickViewOfficial novosJoysticks[] = new JoyStickViewOfficial[50];
    EditText novosTextViewCaracterJoyIcicioX[] = new EditText[50];
    EditText novosTextViewCaracterJoyFimX[] = new EditText[50];
    EditText novosTextViewCaracterJoyFimInverterX[] = new EditText[50];
    TextView tVchaveFimInverterX[] = new TextView[50];


    EditText novosTextViewCaracterJoyIcicioY[] = new EditText[50];
    EditText novosTextViewCaracterJoyFimY[] = new EditText[50];
    EditText novosTextViewCaracterJoyFimInverterY[] = new EditText[50];
    TextView tVchaveFimInverterY[] = new TextView[50];


    EditText nome_add_new_joystick[] = new EditText[50];
    TextView  textViewNomeTela[] =  new TextView[50];
    EditText nomeButton;
    EditText caracterEnvio;

    CheckBox checkBoxX[] = new CheckBox[50];
    CheckBox checkBoxY[] = new CheckBox[50];

    CheckBox checkBoxQuadrado;
    CheckBox checkBoxCircular;

    Button btnProximo;

    Button btnFinaladdnewJoy;


    AlertDialog alert_formato_botao;
    AlertDialog alerta_setup_new_seek_bar;
    AlertDialog alerta_setup_botao_pre_definido;


    String ScaracterJoyInicioX[] = new String[50];
    String ScaracterJoyFimX[] = new String[50];
    String ScaracterJoyFimInverterX[] = new String[50];


    String ScaracterJoyInicioY[] = new String[50];
    String ScaracterJoyFimY[] = new String[50];
    String ScaracterJoyFimInverterY[] = new String[50];

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //Variveis new Seek Bar

    private Button btnFinaladdnewSeekBar;
    private EditText SeekBarNome[] = new EditText[50];
    private EditText SeekBarChaveInicio []= new EditText[50];
    private EditText SeekBarChaveFim []= new EditText[50];
    private EditText SeekBarEscopo[]= new EditText[50];

    private  SeekBar novosSeekBars[] = new SeekBar[50];

    private String ScaracterSeekBarInicio[] = new String[50];
    private String ScaracterSeekBarFim[] = new String[50];

    private EditText SeekBarIntervaloInicio[] = new EditText[50];
    private EditText SeekBarIntervaloFim[] = new EditText[50];

    private String SSeekBarNome[] = new String[50];
    private String SSeekBarChaveInicio[] = new String[50];
    private String SSeekBarChaveFim[] = new String[50];
    private String SSeekBarEscopo[] = new String[50];
    private String SSeekBarIntervaloInicio[] = new String[50];
    private String SSeekBarIntervaloFim[] =new String[50];
    private TextView tvNomeSeekBar[] =new TextView[50];
    private int IntSeekBarIntervaloInicio[] = new int[50];
    private int IntSeekBarIntervaloFim[] = new int[50];

    private ArrayList<Componente> componentes = new ArrayList<>();

    private Toolbar mTopoToolbar;

    //

    public static final int background_img = 1234;

    private static final String[] cores = new String[]{"Azul", "Amarelo", "Vermelho", "Verde"};
    private static final String[] formatos = new String[]{"Quadrado","Circular",  "Retangulo"};
    private static final String[] direcoes = new String[]{"Cima", "Baixo", "Direita", "Esquerda"};
    private static final String[] tipoBtnPreDefinidos = new String[]{"On", "Off", "Iniciar", "3"};





    private Button btnOkVisualizar;
    private Button btnVisualizar;

    private TextView modoAtual;

    private    ImageButton btnPreDefinido;

    /* planos de fundo */
    RecyclerView background_itens;
    BackGroundAdapter itemBackGroundAdapter;
    private ArrayList<Integer> enderecoImg = new ArrayList<>();
    private int contadorLayout = 0;

    //caixa de dialogo escolha plano de fundo
    private AlertDialog alertLayout;

    private static Bitmap imagemOriginal, imagemRedimensionada;
    private Matrix matrix;
    private int alturas[] = new int[50];
    private int larguras[] = new int[50];
    private int orientacao = -1;

    //variaveis drag
    private LinearLayout areaDrag[] = new LinearLayout[50];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(getIntent().hasExtra("orientacao"))
        {
            Bundle extras = getIntent().getExtras();
            orientacao = extras.getInt("orientacao");

        }
        Log.i("flag", "" + orientacao);
        if(orientacao == 1)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        View decor = getWindow().getDecorView();
/*
        if(Build.VERSION.SDK_INT < 16)
        {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        }else{
            decor.setSystemUiVisibility(SYSTEM_UI_FLAG_FULLSCREEN|
                    SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | SYSTEM_UI_FLAG_LAYOUT_STABLE|
                    SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | SYSTEM_UI_FLAG_HIDE_NAVIGATION
                     | SYSTEM_UI_FLAG_IMMERSIVE_STICKY

            );
        }
*/


        setContentView(R.layout.activity_tela_layouts_personalizados);



        // layoutPrincipal = new ConstraintLayout(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        //toolbar = (Toolbar) findViewById(R.id.inc_topo_toolbar_novo_layout_personalizado);
        //setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,null, R.string.open_drawer,R.string.close_drawer);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        this.isto = this;
        layoutPrincipal = (ConstraintLayout) findViewById(R.id.tela_layouts_personalizados);

        Integer contador_layouts = new Integer(234);

        TelaNovoLayoutsPersonalizados isto = this;
        ManipularSharedPreferences sp1 = new ManipularSharedPreferences(this, getBaseContext());

        if(sp1.buscarDados("Contador_Layouts", 0, "contador_lay") == true)
        {
            //contador ja foi iniciado
            contador_layouts = sp1.getDadosInt("Contador_Layouts", 0, "contador_lay");
        }
        else
        {
            sp1.setDados("Contador_Layouts", 0, "contador_lay", contador_layouts);

        }

        final Integer contador = new Integer(contador_layouts + 1);




        exlcuir = (LinearLayout) findViewById(R.id.layoutExcluirComponente);
        exlcuir.setOnDragListener(new OuvirDrag());

        mTopoToolbar = (Toolbar) findViewById(R.id.inc_topo_toolbar_nova_tela_personalizada);
        mTopoToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {


                switch (menuItem.getItemId()) {

                    case R.id.action_posicionar_componentes:
                    {
                        if(componentes.size() <= 0)
                            Toast.makeText(getBaseContext(), "Não ha componentes para posicionar", Toast.LENGTH_SHORT).show();
                        else {
                            modoConfigs = false;
                            modoPosicionar = true;
                            modoTestar = false;
                            modoAtual.setText("Posicionar");
                            for(int i = 0; i < contadorBotoes; i++){
                                if(novosTextViewCaracter[i] != null){
                                    novosTextViewCaracter[i].setVisibility(View.INVISIBLE);
                                    novosTextViewCaracter[i].setEnabled(false);
                                }
                            }

                            for(int i = 0; i < contadorBotoes; i++){
                                if(areaDrag[i] != null){
                                    areaDrag[i].setBackgroundResource(R.drawable.fundo_drag);

                                }
                            }

                            Toast.makeText(getBaseContext(), "Toque na area vermelha docomponente e arraste-o para posiciona-lo", Toast.LENGTH_LONG).show();


                        }

                    }break;

                    case R.id.action_configs_componentes:
                    {
                        if(componentes.size() <= 0)
                            Toast.makeText(getBaseContext(), "Não ha componentes para configurar", Toast.LENGTH_SHORT).show();
                        else {
                            modoConfigs = true;
                            modoPosicionar = false;
                            modoTestar = false;
                            modoAtual.setText("Configurar");
                            Toast.makeText(getBaseContext(), "Segure em um Componente para configura-lo", Toast.LENGTH_LONG).show();
                            for(int i = 0; i < contadorBotoes; i++){
                                if(novosTextViewCaracter[i] != null){
                                    novosTextViewCaracter[i].setVisibility(View.VISIBLE);
                                    novosTextViewCaracter[i].setEnabled(true);
                                }
                            }
                            for(int i = 0; i < contadorBotoes; i++){
                                if(areaDrag[i] != null){
                                    areaDrag[i].setBackgroundColor(TRANSPARENT);
                                }
                            }

                        }

                    } break;

                    case R.id.action_teste_componentes:
                    {
                        modoConfigs = false;
                        modoPosicionar = false;
                        modoTestar = true;
                        modoAtual.setText("Testar");
                        Toast.makeText(getBaseContext(), "Toque em um Componente para testa-lo", Toast.LENGTH_LONG).show();
                        for(int i = 0; i < contadorBotoes; i++){
                            if(novosTextViewCaracter[i] != null){
                                novosTextViewCaracter[i].setVisibility(View.INVISIBLE);
                                novosTextViewCaracter[i].setEnabled(false);
                            }

                        }
                        for(int i = 0; i < contadorBotoes; i++){
                            if(areaDrag[i] != null){
                                areaDrag[i].setBackgroundColor(TRANSPARENT);
                            }
                        }

                    } break;

                }

                return true;
            }
        });
        mTopoToolbar.inflateMenu(R.menu.menu_topo_toolbar_nova_tela_personalizada);



        modoAtual = (TextView) findViewById(R.id.tvModoAtual);



    }



/*
    @Override
   public void onWindowFocusChanged(boolean hasFocus)
   {

       super.onWindowFocusChanged(hasFocus);
       View view = getWindow().getDecorView();
       if (hasFocus) {

           view.setSystemUiVisibility(SYSTEM_UI_FLAG_FULLSCREEN|
                   SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                   | SYSTEM_UI_FLAG_LAYOUT_STABLE|
                   SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                   | SYSTEM_UI_FLAG_HIDE_NAVIGATION
                   | SYSTEM_UI_FLAG_IMMERSIVE_STICKY
           );

       }
   }

*/



    @Override
    public void onResume()
    {

        super.onResume();
        connect = ((BaseAplicacao) getBaseContext().getApplicationContext()).getConnect();
        if(connect.getestaRodando())
        {
            ativo = true;
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
            case R.id.nav_item_layout:
            {
                mudarLayout();
                break;
            }

            case R.id.nav_item_one:
            {
                addNewJoy();
                break;
            }
            case R.id.nav_item_two:
            {
                addNewButton();
                break;
            }

            case R.id.nav_item_three:
            {
                addNewSeekBar();
                break;
            }
            case R.id.nav_item_four:
            {
                addNewVolante();
                break;
            }

            case R.id.nav_item_salvar:
            {
                salvar();
            }break;

            case R.id.nav_item_gerar:
            { //gerar scketch
                Intent intent = new Intent(this, GerarScketch.class);

                if(componentes.size() <= 0)
                {
                    Toast.makeText(this, "Não a componentes para gerar uma scketch", Toast.LENGTH_SHORT).show();


                }
                else
                {

                    intent.putParcelableArrayListExtra("componentes", componentes);
                    startActivity(intent);

                }


            }break;



        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



    public void mudarLayout()
    {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View v = li.inflate(R.layout.setup_new_plano_fundo, null);


        builder.setTitle("Mudar Plano de Fundo");
        builder.setView(v);
        alertLayout = builder.create();

        alertLayout.show();


        Button btnEscolherNovoFundo = (Button) v.findViewById(R.id.btnEscolherNovoFundo);

        btnEscolherNovoFundo.setOnClickListener(vi ->
        {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(i, "Selecione Uma Imagem"), background_img);

        });

        background_itens = (RecyclerView) v.findViewById(R.id.recyclerEscolherLayout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        background_itens.setLayoutManager(layoutManager);
        itemBackGroundAdapter = new BackGroundAdapter(enderecoImg);
        enderecoImg.add(R.drawable.fundo_carro);

        background_itens.setAdapter(itemBackGroundAdapter);
        itemBackGroundAdapter.setOnLayoutClickListener(new BackGroundAdapter.LayoutClickListener() {
            @Override
            public void onLayoutClick(View v, int position) {
                layoutPrincipal.setBackgroundResource(enderecoImg.get(position));
                alertLayout.dismiss();


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode != Activity.RESULT_CANCELED)
        {
            if(requestCode == background_img)
            {
                Uri selectedImage = data.getData();
                Drawable bg = null;
                try {
                    InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                    bg = Drawable.createFromStream(inputStream, selectedImage.toString());
                }catch (FileNotFoundException e)
                {
                }
                //pega a resolução da tela
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int wigth = size.x;
                int height = size.y;
                //redimensiona a imagem

                bg.setBounds(0, 0, wigth, height);
                layoutPrincipal.setBackground(bg);

                //salva a imagem nova no shared preferences
                ManipularSharedPreferences sp1 = new ManipularSharedPreferences(this, getBaseContext());
                sp1.setDados("layout_fundo", 0, "layout_fundo" + contadorLayout, selectedImage.toString());


                Toast.makeText(getApplicationContext(), selectedImage.toString(), Toast.LENGTH_SHORT).show();
                contadorLayout++;

                //fecha a caixa de dialogo
                alertLayout.dismiss();

            }
        }
    }


    public void salvar()
    {
        Integer contador_layouts = new Integer(234);

        ManipularSharedPreferences sp1 = new ManipularSharedPreferences(this, getBaseContext());

        if(sp1.buscarDados("Contador_Layouts", 0, "contador_lay") == true)
        {
            //contador ja foi iniciado
            contador_layouts = sp1.getDadosInt("Contador_Layouts", 0, "contador_lay");
        }
        else
        {
            sp1.setDados("Contador_Layouts", 0, "contador_lay", contador_layouts);

        }

        final Integer contador = new Integer(contador_layouts + 1);

        TelaNovoLayoutsPersonalizados isto = this;

        final AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View vi = li.inflate(R.layout.setup_new_layout_personalizado, null);


        builder.setTitle("Salvar Layout");
        builder.setView(vi);
        alert = builder.create();
        alert.show();

        Button btnAceitarNovaTelaPersonalizada = vi.findViewById(R.id.btnAceitarNovaTelaPersonalizada);
        EditText etNomeNovaTelasPersonalizada =  vi.findViewById(R.id.etNomeLayoutPersonalizado);

        btnAceitarNovaTelaPersonalizada.setOnClickListener(vie->
        {
            String nomeLayout = etNomeNovaTelasPersonalizada.getText().toString();

            new Thread() {

                @Override
                public void run() {
                    ManipularArquivos manipularArquivos = new ManipularArquivos(isto);
                    String nomeArquivo = "layout" + Integer.toString(contador) + ".txt";
                    manipularArquivos.escreverArquivo("LayoutsPersonalizados", nomeArquivo, nomeLayout);
                    manipularArquivos.escreverArquivo("LayoutsPersonalizados", nomeArquivo, Integer.toString(orientacao));

                    for (int i = 0; i < componentes.size(); i++) {

                        String escrita;
                        if (componentes.get(i).getTipo().equals("joystick")) {
                            boolean checkX = checkBoxX[componentes.get(i).getIdComponente()].isChecked();
                            boolean checkY = checkBoxY[componentes.get(i).getIdComponente()].isChecked();
                            escrita = componentes.get(i).getIdComponente() + ";" +
                                    componentes.get(i).getNomeComponente() + ";"
                                    + componentes.get(i).getTipo() + ";"
                                    + componentes.get(i).getCaracterEnvio() + ";"
                                    + componentes.get(i).getPositionX() + ";"
                                    + componentes.get(i).getPositionY() + ";"
                                    + componentes.get(i).getChaveInicio() + ";"
                                    + componentes.get(i).getChaveFim() + ";"
                                    + componentes.get(i).getIntervaloInicio() + ";"
                                    + componentes.get(i).getIntervaloFim() + ";"
                                    + checkX + ";"
                                    + componentes.get(i).getChaveInicioEixoX() + ";"
                                    + componentes.get(i).getChaveFimEixoX() + ";"
                                    + componentes.get(i).getChaveFimInverterEixoX() + ";"

                                    + checkY + ";"
                                    + componentes.get(i).getChaveInicioEixoY() + ";"
                                    + componentes.get(i).getChaveFimEixoY() + ";"
                                    + componentes.get(i).getChaveFimInverterEixoY() + ";"

                                    + componentes.get(i).getIntervaloInicioEixoX() + ";"
                                    + componentes.get(i).getIntervaloFimEixoX() + ";"

                                    + componentes.get(i).getEscopoEixoX() + ";"
                                    + componentes.get(i).getModoOperacaoEixoX() + ";"

                                    + componentes.get(i).getIntervaloInicioEixoY() + ";"
                                    + componentes.get(i).getIntervaloFimEixoY() + ";"
                                    + componentes.get(i).getEscopoEixoY() + ";"
                                    + componentes.get(i).getModoOperacaoEixoY() + ";"



                            ;

                        } else {
                            escrita = componentes.get(i).getIdComponente() + ";" +
                                    componentes.get(i).getNomeComponente() + ";"
                                    + componentes.get(i).getTipo() + ";"
                                    + componentes.get(i).getCaracterEnvio() + ";"
                                    + componentes.get(i).getPositionX() + ";"
                                    + componentes.get(i).getPositionY() + ";"
                                    + componentes.get(i).getChaveInicio() + ";"
                                    + componentes.get(i).getChaveFim() + ";"
                                    + componentes.get(i).getIntervaloInicio() + ";"
                                    + componentes.get(i).getIntervaloFim() + ";"
                                    + componentes.get(i).isEixoX() + ";"
                                    + componentes.get(i).getChaveInicioEixoX() + ";"
                                    + componentes.get(i).getChaveFimEixoX() + ";"
                                    + componentes.get(i).isEixoY() + ";"
                                    + componentes.get(i).getChaveInicioEixoY() + ";"
                                    + componentes.get(i).getChaveFimEixoY() + ";"
                                    + componentes.get(i).getTipoBotao() + ";"
                                    + componentes.get(i).getRotacaoBotao() + ";"
                                    + componentes.get(i).getCor() + ";"
                                    + componentes.get(i).getFormato() + ";"

                            ;

                        }
                        manipularArquivos.escreverArquivo("LayoutsPersonalizados", nomeArquivo, escrita);




                    }

                    alert.dismiss();
                    sp1.setDados("Contador_Layouts", 0, "contador_lay", contador);
                    manipularArquivos.criarDiretorio("LayoutsPersonalizados/imgs");

                    //cria a imagem

                    //manipularArquivos.criarArquivo("layout" + Integer.toString(contador) + ".jpg", "LayoutsPersonalizados/imgs");
                   // String path = manipularArquivos.getDiretorioRaiz().concat("/LayoutsPersonalizados/imgs/" + "layout" + Integer.toString(contador) + ".jpg");
                    String path = Environment.getExternalStorageDirectory().toString() + "/TiTaniwmBluetooth/LayoutsPersonalizados/imgs/layout" + Integer.toString(contador) + ".png";

                    View view = getWindow().getDecorView().getRootView();

                    Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    view.draw(canvas);


                    OutputStream fout = null;
                    File file = new File(path);

                    try{
                        fout = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fout);
                        fout.flush();
                        fout.close();
                        MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "img", "img");
                        manipularArquivos.escreverArquivo("LayoutsPersonalizados", nomeArquivo, path);


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finish();
                }


            }.start();



        });



    }






    public void addNewVolante()
    {
        meuLayout[contadorBotoes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_volante, null);
        meuLayout[contadorBotoes].setId(contadorBotoes);

        imagemOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.volante);
        matrix = new Matrix();

        ConstraintSet set = new ConstraintSet();
        novosVolantes[contadorBotoes] = (ImageView) meuLayout[contadorBotoes].findViewById(R.id.add_new_volante);
        novosVolantes[contadorBotoes].setId(contadorBotoes);


        layoutPrincipal.addView(meuLayout[contadorBotoes]);
        set.clone(layoutPrincipal);
        set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, 0);
        // set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, meuLayout[contadorBotoes].getBottom());
        //set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, meuLayout[contadorBotoes].getRight());
        set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, 0);
        set.constrainHeight(meuLayout[contadorBotoes].getId(), meuLayout[contadorBotoes].getMinHeight());

        set.applyTo(layoutPrincipal);


        Log.i("ID", "O id na classe tela e: " + novosVolantes[contadorBotoes].getId());


        Componente volante = new Componente();
        volante.setTipo("volante");

        componentes.add(volante);

        novosVolantes[contadorBotoes].setOnTouchListener(new TouchVolante());

        OnGlobalOuvinte ouvinteGlobal = new OnGlobalOuvinte();
        ouvinteGlobal.setView( novosVolantes[contadorBotoes]);

        novosVolantes[contadorBotoes].getViewTreeObserver().addOnGlobalLayoutListener(ouvinteGlobal);


        //meuLayout[contadorBotoes].setOnDragListener(new OuvirDrag());
        meuLayout[contadorBotoes].setOnLongClickListener(new OuvirCliqueLongo());
        layoutPrincipal.setOnDragListener(new OuvirDrag());

        contadorBotoes++;

    }


    private class EsconderStatusBar implements ViewTreeObserver.OnGlobalLayoutListener {


        @Override
        public void onGlobalLayout() {


                getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_FULLSCREEN|
                        SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | SYSTEM_UI_FLAG_LAYOUT_STABLE|
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | SYSTEM_UI_FLAG_LOW_PROFILE
                        | SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        }
    }



    private class OnGlobalOuvinte implements ViewTreeObserver.OnGlobalLayoutListener {
        ImageView view;
        public void setView(ImageView imagem)
        {
            view = imagem;
        }
        @Override
        public void onGlobalLayout() {
            if (larguras[view.getId()] == 0 || alturas[view.getId()] == 0) {

                larguras[view.getId()] = view.getHeight();
                alturas[view.getId()] = view.getWidth();

                // resize
                Matrix resize = new Matrix();
                resize.postScale((float)Math.min(alturas[view.getId()],  larguras[view.getId()]) / (float)imagemOriginal.getWidth(), (float)Math.min(alturas[view.getId()],  larguras[view.getId()]) / (float)imagemOriginal.getHeight());
                imagemRedimensionada = Bitmap.createBitmap(imagemOriginal, 0, 0, imagemOriginal.getWidth(), imagemOriginal.getHeight(), resize, false);
                float translateX = alturas[view.getId()]/2 - imagemRedimensionada.getWidth() /2;
                float translateY = alturas[view.getId()]/2 - imagemRedimensionada.getHeight() /2;

                view.setImageBitmap(imagemRedimensionada);
                view.setImageMatrix(matrix);

            }
        }

    }

    private class TouchVolante implements View.OnTouchListener{

        private double startAngle;


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch(event.getAction())
            {

                case MotionEvent.ACTION_DOWN:
                    if(modoTestar) {
                        startAngle = getAngle(event.getX(), event.getY(), (ImageView) v);
                        Log.i("Graus", "" + startAngle);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(modoTestar) {

                        double anguloAtual = getAngle(event.getX(), event.getY(), (ImageView) v);
                        rotacionarDialer((float) (startAngle - anguloAtual), (ImageView) v);
                        startAngle = anguloAtual;
                        Log.i("Graus2", "" + anguloAtual);


                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        }
    }

    private double getAngle(double xTouch, double yTouch, View view) {
        double x = xTouch - (alturas[view.getId()] / 2d);
        double y = larguras[view.getId()] - yTouch - (larguras[view.getId()] / 2d);

        switch (getQuadrant(x, y)) {
            case 1:
                return Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
            case 2:
                return 180 - Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
            case 3:
                return 180 + (-1 * Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI);
            case 4:
                return 360 + Math.asin(y / Math.hypot(x, y)) * 180 / Math.PI;
            default:
                return 0;
        }
    }


    private static int getQuadrant(double x, double y) {
        if (x >= 0) {
            return y >= 0 ? 1 : 4;
        } else {
            return y >= 0 ? 2 : 3;
        }
    }


    private void rotacionarDialer(float degrees, ImageView view) {

        matrix.postRotate(degrees, alturas[view.getId()]/2, larguras[view.getId()]/2);
        //imagem.setImageBitmap(Bitmap.createBitmap(imagemRedimensionada, 0, 0, imagemRedimensionada.getWidth(), imagemRedimensionada.getHeight(), matrix, true));
        Log.i("Graus4", "" + degrees);

        view.setImageMatrix(matrix);
    }

    private void impedirRedimensionar(AlertDialog alert){
        alert.getWindow().getDecorView().setSystemUiVisibility(
                TelaNovoLayoutsPersonalizados.this.getWindow().getDecorView().getSystemUiVisibility()
        );

        alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }



    public void addNewJoy() {
        final AlertDialog alert;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View v = li.inflate(R.layout.setup_new_joystick, null);


        builder.setTitle("Adicionar Joystick");
        builder.setView(v);
        alert = builder.create();
        alert.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        alert.show();

        impedirRedimensionar(alert);



        novosTextViewCaracterJoyFimX[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_fim_joyX);
        novosTextViewCaracterJoyIcicioX[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_inicio_joyX);
        novosTextViewCaracterJoyFimX[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyIcicioX[contadorBotoes].setId(contadorBotoes);

        novosTextViewCaracterJoyFimInverterX[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_fim_inverter_joyX);
        novosTextViewCaracterJoyFimInverterX[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyFimInverterX[contadorBotoes].setVisibility(View.INVISIBLE);
        novosTextViewCaracterJoyFimInverterX[contadorBotoes].setEnabled(false);

        tVchaveFimInverterX[contadorBotoes] = v.findViewById(R.id.tVchaveFimInverterX);
        tVchaveFimInverterX[contadorBotoes].setId(contadorBotoes);
        tVchaveFimInverterX[contadorBotoes].setVisibility(View.INVISIBLE);
        tVchaveFimInverterX[contadorBotoes].setEnabled(false);


        novosTextViewCaracterJoyFimY[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_fim_joyY);
        novosTextViewCaracterJoyIcicioY[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_inicio_joyY);
        novosTextViewCaracterJoyFimY[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyIcicioY[contadorBotoes].setId(contadorBotoes);


        novosTextViewCaracterJoyFimInverterY[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_fim_inverter_joyY);
        novosTextViewCaracterJoyFimInverterY[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyFimInverterY[contadorBotoes].setVisibility(View.INVISIBLE);
        novosTextViewCaracterJoyFimInverterY[contadorBotoes].setEnabled(false);

        tVchaveFimInverterY[contadorBotoes] = v.findViewById(R.id.tVchaveFimInverterY);
        tVchaveFimInverterY[contadorBotoes].setId(contadorBotoes);
        tVchaveFimInverterY[contadorBotoes].setVisibility(View.INVISIBLE);
        tVchaveFimInverterY[contadorBotoes].setEnabled(false);

        nome_add_new_joystick[contadorBotoes] = (EditText) v.findViewById(R.id.nome_add_new_joystick);

        checkBoxX[contadorBotoes] = (CheckBox) v.findViewById(R.id.checkBoxX);
        checkBoxX[contadorBotoes].setId(contadorBotoes);
        checkBoxY[contadorBotoes] = (CheckBox) v.findViewById(R.id.checkBoxY);
        checkBoxY[contadorBotoes].setId(contadorBotoes);
        checkBoxX[contadorBotoes].setChecked(true);
        checkBoxY[contadorBotoes].setChecked(false);


        checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes] = (CheckBox) v.findViewById(R.id.checkBoxDividirEixoXNovaTelaPersonalizada);
        checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes]= (CheckBox) v.findViewById(R.id.checkBoxInverterEixoXNovaTelaPersonalizada);
        //checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setId(contadorBotoes);
        //checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setId(contadorBotoes);

        checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(true);
        checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(false);

        checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes] = (CheckBox) v.findViewById(R.id.checkBoxDividirEixoYNovaTelaPersonalizada);
        checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes]= (CheckBox) v.findViewById(R.id.checkBoxInverterEixoYNovaTelaPersonalizada);
        //checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setId(contadorBotoes);
        //checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setId(contadorBotoes);

        checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);
        checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);


        eTIntervaloInicioEixoXNovaTelaPersonalizada[contadorBotoes] = (EditText) v.findViewById(R.id.eTIntervaloInicioJoyXNovaTelaPersonalizada);
        eTIntervaloFimEixoXNovaTelaPersonalizada[contadorBotoes] = (EditText) v.findViewById(R.id.eTIntervaloFimJoyXNovaTelaPersonalizada);

        eTIntervaloInicioEixoYNovaTelaPersonalizada[contadorBotoes] = (EditText) v.findViewById(R.id.eTIntervaloInicioJoyYNovaTelaPersonalizada);
        eTIntervaloFimEixoYNovaTelaPersonalizada[contadorBotoes] = (EditText) v.findViewById(R.id.eTIntervaloFimJoyYNovaTelaPersonalizada);



        btnFinaladdnewJoy = (Button) v.findViewById(R.id.btnFinalAddNewJoy);


        btnFinaladdnewJoy.setOnClickListener(new View.OnClickListener() {
            boolean aceitaCriarJoystick = false;





            @Override
            public void onClick(View view) {
                ScaracterJoyInicioX[contadorBotoes] = novosTextViewCaracterJoyIcicioX[contadorBotoes].getText().toString();
                ScaracterJoyFimX[contadorBotoes] = novosTextViewCaracterJoyFimX[contadorBotoes].getText().toString();
                ScaracterJoyFimInverterX[contadorBotoes] = novosTextViewCaracterJoyFimInverterX[contadorBotoes].getText().toString();

                ScaracterJoyInicioY[contadorBotoes] = novosTextViewCaracterJoyIcicioY[contadorBotoes].getText().toString();
                ScaracterJoyFimY[contadorBotoes] = novosTextViewCaracterJoyFimY[contadorBotoes].getText().toString();
                ScaracterJoyFimInverterY[contadorBotoes] = novosTextViewCaracterJoyFimInverterY[contadorBotoes].getText().toString();


                if(checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].isChecked())
                    modoOperacaoX[contadorBotoes] = 0;
                else
                    modoOperacaoX[contadorBotoes] = 1;

                if(checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].isChecked())
                    modoOperacaoY[contadorBotoes] = 0;
                else
                    modoOperacaoY[contadorBotoes] = 1;


                if(checkBoxX[contadorBotoes].isChecked()) {
                    //chave inciio e fim
                    if (ScaracterJoyInicioX[contadorBotoes].length() != 1 || ScaracterJoyFimX[contadorBotoes].length() != 1 || ScaracterJoyFimInverterX[contadorBotoes].length() != 1 && modoOperacaoX[contadorBotoes] == 1) {
                        Toast.makeText(getBaseContext(), "Chaves do Eixo X devem possuir apenas um  unico caracter", Toast.LENGTH_LONG).show();
                        aceitaCriarJoystick = false;


                    }
                    else
                    {
                        //escopo
                        try {
                            intervaloInicioX[contadorBotoes] = Integer.parseInt(eTIntervaloInicioEixoXNovaTelaPersonalizada[contadorBotoes].getText().toString());
                            try {
                                intervaloFimX[contadorBotoes] = Integer.parseInt(eTIntervaloFimEixoXNovaTelaPersonalizada[contadorBotoes].getText().toString());

                                try{
                                    escopoEixoX[contadorBotoes] = intervaloFimX[contadorBotoes] - intervaloInicioX[contadorBotoes];
                                    if (escopoEixoX[contadorBotoes] < 0)
                                    {
                                        Toast.makeText(getBaseContext(), "Escopo eixo X não pode ser negativo", Toast.LENGTH_SHORT).show();
                                        aceitaCriarJoystick = false;
                                    }
                                    else
                                    {
                                        aceitaCriarJoystick = true;

                                    }

                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getBaseContext(), "Escopo eixo X incorreto, verifique", Toast.LENGTH_SHORT).show();
                                    aceitaCriarJoystick = false;

                                }

                            }
                            catch (Exception e)
                            {
                                Toast.makeText(getBaseContext(), "Valor fim de escopo Eixo X incorreto", Toast.LENGTH_SHORT).show();
                                aceitaCriarJoystick = false;
                            }


                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getBaseContext(), "Valor Inicio de escopo Eixo X incorreto", Toast.LENGTH_SHORT).show();
                            aceitaCriarJoystick = false;
                        }

                    }
                    //escopo

                }

                if(checkBoxY[contadorBotoes].isChecked()) {
                    if (ScaracterJoyInicioY[contadorBotoes].length() != 1 || ScaracterJoyFimY[contadorBotoes].length() != 1  || ScaracterJoyFimInverterY[contadorBotoes].length() != 1 && modoOperacaoY[contadorBotoes] == 1) {
                        Toast.makeText(getBaseContext(), "Chaves Inicio e Fim do Eixo Y devem possuir apenas um  unico caracter", Toast.LENGTH_LONG).show();
                        aceitaCriarJoystick = false;
                    }
                    else
                    {
                        try {
                            intervaloInicioY[contadorBotoes] = Integer.parseInt(eTIntervaloInicioEixoYNovaTelaPersonalizada[contadorBotoes].getText().toString());
                            try {
                                intervaloFimY[contadorBotoes]= Integer.parseInt(eTIntervaloFimEixoYNovaTelaPersonalizada[contadorBotoes].getText().toString());

                                try{
                                    escopoEixoY[contadorBotoes] = intervaloFimY[contadorBotoes] - intervaloInicioY[contadorBotoes];
                                    if (escopoEixoY[contadorBotoes] < 0)
                                    {
                                        Toast.makeText(getBaseContext(), "Escopo Eixo Y não pode ser negativo", Toast.LENGTH_SHORT).show();
                                        aceitaCriarJoystick = false;
                                    }
                                    else
                                    {
                                        aceitaCriarJoystick = true;

                                    }

                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getBaseContext(), "Escopo eixo Y incorreto, verifique", Toast.LENGTH_SHORT).show();
                                    aceitaCriarJoystick = false;

                                }

                            }
                            catch (Exception e)
                            {
                                Toast.makeText(getBaseContext(), "Valor fim de escopo Eixo Y incorreto", Toast.LENGTH_SHORT).show();
                                aceitaCriarJoystick = false;
                            }


                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getBaseContext(), "Valor Inicio de escopo de escopo Eixo Y incorreot", Toast.LENGTH_SHORT).show();
                            aceitaCriarJoystick = false;
                        }

                    }
                }




                if(aceitaCriarJoystick) {

                    meuLayout[contadorBotoes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_joystick, null);
                    meuLayout[contadorBotoes].setId(contadorBotoes);

                    areaDrag[contadorBotoes] = (LinearLayout) meuLayout[contadorBotoes].findViewById(R.id.areaDragJoy);


                    ConstraintSet set = new ConstraintSet();
                    novosJoysticks[contadorBotoes] = (JoyStickViewOfficial) meuLayout[contadorBotoes].findViewById(R.id.add_new_joystick);
                    novosJoysticks[contadorBotoes].setId(contadorBotoes);
                    textViewNomeTela[contadorBotoes] = (TextView) meuLayout[contadorBotoes].findViewById(R.id.nome_joystick_tela);

                    String strNomeJoystick = nome_add_new_joystick[contadorBotoes].getText().toString();
                    textViewNomeTela[contadorBotoes].setText(nome_add_new_joystick[contadorBotoes].getText().toString());
                    textViewNomeTela[contadorBotoes].setId(contadorBotoes);

                    layoutPrincipal.addView(meuLayout[contadorBotoes]);
                    set.clone(layoutPrincipal);
                    set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, 0);
                    // set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, meuLayout[contadorBotoes].getBottom());
                    //set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, meuLayout[contadorBotoes].getRight());
                    set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, 0);
                    set.constrainHeight(meuLayout[contadorBotoes].getId(), meuLayout[contadorBotoes].getMinHeight());

                    set.applyTo(layoutPrincipal);


                    Log.i("ID", "O id na classe tela e: " + novosJoysticks[contadorBotoes].getId());


                  /* meuLayout[contadorBotoes].setOnClickListener(v->{

                       if(modoConfigs)
                       {
                           configurarJoystick(v.getId());
                       }

                   });
                   */


                    Componente joystick = new Componente();
                    joystick.setIdComponente(contadorBotoes);
                    joystick.setTipo("joystick");
                    joystick.setNomeComponente(nome_add_new_joystick[contadorBotoes].getText().toString());
                    joystick.setEixoX(checkBoxX[contadorBotoes].isChecked());
                    joystick.setChaveInicioEixoX(ScaracterJoyInicioX[contadorBotoes]);
                    joystick.setChaveFimEixoX(ScaracterJoyFimX[contadorBotoes]);
                    joystick.setChaveFimInverterEixoX(ScaracterJoyFimInverterX[contadorBotoes]);
                    joystick.setIntervaloInicioEixoX(intervaloInicioX[contadorBotoes]);
                    joystick.setIntervaloFimEixoX(intervaloFimX[contadorBotoes]);
                    joystick.setChaveFimInverterEixoY(ScaracterJoyFimInverterY[contadorBotoes]);
                    joystick.setEscopoEixoX(escopoEixoX[contadorBotoes]);
                    joystick.setModoOperacaoEixoX(modoOperacaoX[contadorBotoes]);
                    joystick.setEixoY(checkBoxY[contadorBotoes].isChecked());
                    joystick.setChaveInicioEixoY(ScaracterJoyInicioY[contadorBotoes]);
                    joystick.setChaveFimEixoY(ScaracterJoyFimY[contadorBotoes]);


                    joystick.setIntervaloInicioEixoY(intervaloInicioY[contadorBotoes]);
                    joystick.setIntervaloFimEixoY(intervaloFimY[contadorBotoes]);
                    joystick.setEscopoEixoY(escopoEixoY[contadorBotoes]);
                    joystick.setModoOperacaoEixoY(modoOperacaoY[contadorBotoes]);


                    componentes.add(joystick);
                    meuLayout[contadorBotoes].setOnLongClickListener(new OuvirCliqueLongo());



                    layoutPrincipal.setOnDragListener(new OuvirDrag());
                    contadorBotoes++;
                    alert.dismiss();
                }
                else
                {

                }
            }
        });

    }


    public void configurarJoystick(int idComponente)
    {


        int posicao = 0;
        for(int i = 0; i < componentes.size();i++)
        {
            if(componentes.get(i).getIdComponente() == idComponente)
            {
                posicao = i;

                break;
            }
        }

        final AlertDialog alert;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View v = li.inflate(R.layout.setup_new_joystick, null);


        builder.setTitle("Configurar Joystick");
        builder.setView(v);
        alert = builder.create();
        alert.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        alert.show();

        impedirRedimensionar(alert);

        novosTextViewCaracterJoyFimX[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_fim_joyX);
        novosTextViewCaracterJoyFimX[contadorBotoes].setText(componentes.get(posicao).getChaveFimEixoX());

        novosTextViewCaracterJoyFimInverterX[contadorBotoes]  = v.findViewById(R.id.caracter_envio_fim_inverter_joyX);
        novosTextViewCaracterJoyFimInverterX[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyFimInverterX[contadorBotoes].setText(componentes.get(posicao).getChaveFimInverterEixoX());

        tVchaveFimInverterX[contadorBotoes] = v.findViewById(R.id.tVchaveFimInverterX);
        tVchaveFimInverterX[contadorBotoes].setId(contadorBotoes);

        if(componentes.get(posicao).getModoOperacaoEixoX() == 1)
        {


            novosTextViewCaracterJoyFimInverterX[contadorBotoes].setVisibility(View.VISIBLE);
            novosTextViewCaracterJoyFimInverterX[contadorBotoes].setEnabled(true);
            tVchaveFimInverterX[contadorBotoes].setVisibility(View.VISIBLE);
            tVchaveFimInverterX[contadorBotoes].setEnabled(true);
        }
        else{
            novosTextViewCaracterJoyFimInverterX[contadorBotoes].setVisibility(View.INVISIBLE);
            novosTextViewCaracterJoyFimInverterX[contadorBotoes].setEnabled(false);
            tVchaveFimInverterX[contadorBotoes].setVisibility(View.INVISIBLE);
            tVchaveFimInverterX[contadorBotoes].setEnabled(false);
        }

        novosTextViewCaracterJoyIcicioX[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_inicio_joyX);
        novosTextViewCaracterJoyIcicioX[contadorBotoes].setText(componentes.get(posicao).getChaveInicioEixoX());

        novosTextViewCaracterJoyFimX[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyIcicioX[contadorBotoes].setId(contadorBotoes);

        novosTextViewCaracterJoyFimY[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_fim_joyY);
        novosTextViewCaracterJoyFimY[contadorBotoes].setText(componentes.get(posicao).getChaveFimEixoY());

        novosTextViewCaracterJoyIcicioY[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_inicio_joyY);
        novosTextViewCaracterJoyIcicioY[contadorBotoes].setText(componentes.get(posicao).getChaveInicioEixoY());

        novosTextViewCaracterJoyFimY[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyIcicioY[contadorBotoes].setId(contadorBotoes);

        novosTextViewCaracterJoyFimInverterY[contadorBotoes]  = v.findViewById(R.id.caracter_envio_fim_inverter_joyY);
        novosTextViewCaracterJoyFimInverterY[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyFimInverterY[contadorBotoes].setText(componentes.get(posicao).getChaveFimInverterEixoY());

        tVchaveFimInverterY[contadorBotoes] = v.findViewById(R.id.tVchaveFimInverterY);
        tVchaveFimInverterY[contadorBotoes].setId(contadorBotoes);

        if(componentes.get(posicao).getModoOperacaoEixoY() == 1)
        {

            novosTextViewCaracterJoyFimInverterY[contadorBotoes].setVisibility(View.VISIBLE);
            novosTextViewCaracterJoyFimInverterY[contadorBotoes].setEnabled(true);
            tVchaveFimInverterY[contadorBotoes].setVisibility(View.VISIBLE);
            tVchaveFimInverterY[contadorBotoes].setEnabled(true);
        }    else{
            novosTextViewCaracterJoyFimInverterY[contadorBotoes].setVisibility(View.INVISIBLE);
            novosTextViewCaracterJoyFimInverterY[contadorBotoes].setEnabled(false);
            tVchaveFimInverterY[contadorBotoes].setVisibility(View.INVISIBLE);
            tVchaveFimInverterY[contadorBotoes].setEnabled(false);
        }


        nome_add_new_joystick[contadorBotoes] = (EditText) v.findViewById(R.id.nome_add_new_joystick);
        nome_add_new_joystick[contadorBotoes].setText(componentes.get(posicao).getNomeComponente());

        checkBoxX[contadorBotoes] = (CheckBox) v.findViewById(R.id.checkBoxX);
        checkBoxY[contadorBotoes] = (CheckBox) v.findViewById(R.id.checkBoxY);

        checkBoxX[contadorBotoes].setChecked(componentes.get(posicao).isEixoX());
        checkBoxY[contadorBotoes].setChecked(componentes.get(posicao).isEixoY());


        checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes] = (CheckBox) v.findViewById(R.id.checkBoxDividirEixoXNovaTelaPersonalizada);
        checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes]= (CheckBox) v.findViewById(R.id.checkBoxInverterEixoXNovaTelaPersonalizada);

        if(componentes.get(posicao).getModoOperacaoEixoX() == 0)
        {
            checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(true);
            checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(false);

        }
        else
        {
            checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(false);
            checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(true);

        }



        checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes] = (CheckBox) v.findViewById(R.id.checkBoxDividirEixoYNovaTelaPersonalizada);
        checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes]= (CheckBox) v.findViewById(R.id.checkBoxInverterEixoYNovaTelaPersonalizada);


        checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);
        checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);

        if(componentes.get(posicao).getModoOperacaoEixoY() == 0)
        {
            checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);
            checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);
        }
        else
        {
            checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);
            checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);
        }


        eTIntervaloInicioEixoXNovaTelaPersonalizada[contadorBotoes] = (EditText) v.findViewById(R.id.eTIntervaloInicioJoyXNovaTelaPersonalizada);
        eTIntervaloInicioEixoXNovaTelaPersonalizada[contadorBotoes].setText(Integer.toString(componentes.get(posicao).getIntervaloInicioEixoX()));

        eTIntervaloFimEixoXNovaTelaPersonalizada[contadorBotoes] = (EditText) v.findViewById(R.id.eTIntervaloFimJoyXNovaTelaPersonalizada);
        eTIntervaloFimEixoXNovaTelaPersonalizada[contadorBotoes].setText(Integer.toString(componentes.get(posicao).getIntervaloFimEixoX()));

        eTIntervaloInicioEixoYNovaTelaPersonalizada[contadorBotoes] = (EditText) v.findViewById(R.id.eTIntervaloInicioJoyYNovaTelaPersonalizada);
        eTIntervaloInicioEixoYNovaTelaPersonalizada[contadorBotoes].setText(Integer.toString(componentes.get(posicao).getIntervaloInicioEixoY()));

        eTIntervaloFimEixoYNovaTelaPersonalizada[contadorBotoes] = (EditText) v.findViewById(R.id.eTIntervaloFimJoyYNovaTelaPersonalizada);
        eTIntervaloFimEixoYNovaTelaPersonalizada[contadorBotoes].setText(Integer.toString(componentes.get(posicao).getIntervaloFimEixoY()));

        btnFinaladdnewJoy = (Button) v.findViewById(R.id.btnFinalAddNewJoy);


        int finalId = contadorBotoes;
        int finalPosicao = posicao;
        int finalIdComponente = idComponente;
        btnFinaladdnewJoy.setOnClickListener(new View.OnClickListener() {
            boolean aceitaCriarJoystick = false;





            @Override
            public void onClick(View view) {
                ScaracterJoyInicioX[contadorBotoes] = novosTextViewCaracterJoyIcicioX[contadorBotoes].getText().toString();
                ScaracterJoyFimX[contadorBotoes] = novosTextViewCaracterJoyFimX[contadorBotoes].getText().toString();
                if( checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].isChecked())
                   ScaracterJoyFimInverterX[contadorBotoes] = novosTextViewCaracterJoyFimInverterX[contadorBotoes].getText().toString();


                ScaracterJoyInicioY[contadorBotoes] = novosTextViewCaracterJoyIcicioY[contadorBotoes].getText().toString();
                ScaracterJoyFimY[contadorBotoes] = novosTextViewCaracterJoyFimY[contadorBotoes].getText().toString();
                if(checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].isChecked())
                    ScaracterJoyFimInverterY[contadorBotoes] = novosTextViewCaracterJoyFimInverterY[contadorBotoes].getText().toString();


                if(checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].isChecked())
                    modoOperacaoX[contadorBotoes] = 0;
                else
                    modoOperacaoX[contadorBotoes] = 1;

                if(checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].isChecked())
                    modoOperacaoY[contadorBotoes] = 0;
                else
                    modoOperacaoY[contadorBotoes] = 1;

                boolean confirma = false;

                try{
                    if(ScaracterJoyFimInverterX[contadorBotoes] == null) {

                        confirma = false;
                    }
                    else {
                        if(ScaracterJoyFimInverterX[contadorBotoes].length() != 1){

                            confirma = false;
                        }else {

                            confirma = true;

                        }

                    }


                }catch (Exception e)
                {
                    confirma = false;

                }

                if(checkBoxX[contadorBotoes].isChecked()) {
                    //chave inciio e fim
                    if (ScaracterJoyInicioX[contadorBotoes].length() != 1 || ScaracterJoyFimX[contadorBotoes].length() != 1 || confirma == false &&  checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].isChecked()) {
                        Toast.makeText(getBaseContext(), "Chaves Eixo X devem possuir apenas um  unico caracter", Toast.LENGTH_LONG).show();
                        aceitaCriarJoystick = false;

                    }
                    else
                    {
                        //escopo
                        try {
                            intervaloInicioX[contadorBotoes] = Integer.parseInt(eTIntervaloInicioEixoXNovaTelaPersonalizada[contadorBotoes].getText().toString());
                            try {
                                intervaloFimX[contadorBotoes] = Integer.parseInt(eTIntervaloFimEixoXNovaTelaPersonalizada[contadorBotoes].getText().toString());

                                try{
                                    escopoEixoX[contadorBotoes] = intervaloFimX[contadorBotoes] - intervaloInicioX[contadorBotoes];
                                    if (escopoEixoX[contadorBotoes] < 0)
                                    {
                                        Toast.makeText(getBaseContext(), "Escopo eixo X não pode ser negativo", Toast.LENGTH_SHORT).show();
                                        aceitaCriarJoystick = false;
                                    }
                                    else
                                    {
                                        aceitaCriarJoystick = true;

                                    }

                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getBaseContext(), "Escopo eixo X incorreto, verifique", Toast.LENGTH_SHORT).show();
                                    aceitaCriarJoystick = false;

                                }

                            }
                            catch (Exception e)
                            {
                                Toast.makeText(getBaseContext(), "Valor fim de escopo Eixo X incorreto", Toast.LENGTH_SHORT).show();
                                aceitaCriarJoystick = false;
                            }


                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getBaseContext(), "Valor Inicio de escopo Eixo X incorreto", Toast.LENGTH_SHORT).show();
                            aceitaCriarJoystick = false;
                        }

                    }
                    //escopo

                }

                boolean confirmaY = false;
                try{
                    if(ScaracterJoyFimInverterY[contadorBotoes] == null) {

                        confirmaY = false;
                    }
                    else {
                        if(ScaracterJoyFimInverterY[contadorBotoes].length() != 1){

                            confirmaY = false;
                        }else {

                            confirmaY = true;

                        }

                    }


                }catch (Exception e)
                {
                    confirmaY = false;

                }


                if(checkBoxY[contadorBotoes].isChecked()) {
                    if (ScaracterJoyInicioY[contadorBotoes].length() != 1 || ScaracterJoyFimY[contadorBotoes].length() != 1 || confirmaY == false &&  checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].isChecked()) {
                        Toast.makeText(getBaseContext(), "Chaves Inicio e Fim do Eixo Y devem possuir apenas um  unico caracter", Toast.LENGTH_LONG).show();
                        aceitaCriarJoystick = false;
                    }
                    else
                    {
                        try {
                            intervaloInicioY[contadorBotoes] = Integer.parseInt(eTIntervaloInicioEixoYNovaTelaPersonalizada[contadorBotoes].getText().toString());
                            try {
                                intervaloFimY[contadorBotoes]= Integer.parseInt(eTIntervaloFimEixoYNovaTelaPersonalizada[contadorBotoes].getText().toString());

                                try{
                                    escopoEixoY[contadorBotoes] = intervaloFimY[contadorBotoes] - intervaloInicioY[contadorBotoes];
                                    if (escopoEixoY[contadorBotoes] < 0)
                                    {
                                        Toast.makeText(getBaseContext(), "Escopo Eixo Y não pode ser negativo", Toast.LENGTH_SHORT).show();
                                        aceitaCriarJoystick = false;
                                    }
                                    else
                                    {
                                        aceitaCriarJoystick = true;

                                    }

                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getBaseContext(), "Escopo eixo Y incorreto, verifique", Toast.LENGTH_SHORT).show();
                                    aceitaCriarJoystick = false;

                                }

                            }
                            catch (Exception e)
                            {
                                Toast.makeText(getBaseContext(), "Valor fim de escopo Eixo Y incorreto", Toast.LENGTH_SHORT).show();
                                aceitaCriarJoystick = false;
                            }


                        }
                        catch (Exception e)
                        {
                            Toast.makeText(getBaseContext(), "Valor Inicio de escopo de escopo Eixo Y incorreot", Toast.LENGTH_SHORT).show();
                            aceitaCriarJoystick = false;
                        }

                    }
                }




                if(aceitaCriarJoystick) {

                    String strNomeJoystick = nome_add_new_joystick[contadorBotoes].getText().toString();
                    textViewNomeTela[finalIdComponente].setText(strNomeJoystick);

                    checkBoxX[finalIdComponente].setChecked(checkBoxX[contadorBotoes].isChecked());
                    modoOperacaoX[finalIdComponente] = modoOperacaoX[contadorBotoes];
                    ScaracterJoyInicioX[finalIdComponente] = ScaracterJoyInicioX[contadorBotoes];
                    ScaracterJoyFimX[finalIdComponente] = ScaracterJoyFimX[contadorBotoes];
                    ScaracterJoyFimInverterX[finalIdComponente] = ScaracterJoyFimInverterX[contadorBotoes];
                    escopoEixoX[finalIdComponente] = escopoEixoX[contadorBotoes];



                    checkBoxY[finalIdComponente].setChecked(checkBoxY[contadorBotoes].isChecked());
                    modoOperacaoY[finalIdComponente] = modoOperacaoY[contadorBotoes];
                    ScaracterJoyInicioY[finalIdComponente] = ScaracterJoyInicioY[contadorBotoes];
                    ScaracterJoyFimY[finalIdComponente] = ScaracterJoyFimY[contadorBotoes];
                    ScaracterJoyFimInverterY[finalIdComponente] = ScaracterJoyFimInverterY[contadorBotoes];
                    escopoEixoY[finalIdComponente] = escopoEixoY[contadorBotoes];






                    componentes.remove(finalPosicao);

                    Componente joystick = new Componente();
                    joystick.setIdComponente(finalIdComponente);
                    joystick.setTipo("joystick");
                    joystick.setNomeComponente(nome_add_new_joystick[contadorBotoes].getText().toString());
                    joystick.setEixoX(checkBoxX[contadorBotoes].isChecked());
                    joystick.setChaveInicioEixoX(ScaracterJoyInicioX[contadorBotoes]);
                    joystick.setChaveFimEixoX(ScaracterJoyFimX[contadorBotoes]);
                    joystick.setChaveFimInverterEixoX(ScaracterJoyFimInverterX[contadorBotoes]);
                    joystick.setIntervaloInicioEixoX(intervaloInicioX[contadorBotoes]);
                    joystick.setIntervaloFimEixoX(intervaloFimX[contadorBotoes]);
                    joystick.setChaveFimInverterEixoY(ScaracterJoyFimInverterY[contadorBotoes]);
                    joystick.setEscopoEixoX(escopoEixoX[contadorBotoes]);
                    joystick.setModoOperacaoEixoX(modoOperacaoX[contadorBotoes]);




                    joystick.setEixoY(checkBoxY[contadorBotoes].isChecked());
                    joystick.setChaveInicioEixoY(ScaracterJoyInicioY[contadorBotoes]);
                    joystick.setChaveFimEixoY(ScaracterJoyFimY[contadorBotoes]);


                    joystick.setIntervaloInicioEixoY(intervaloInicioY[contadorBotoes]);
                    joystick.setIntervaloFimEixoY(intervaloFimY[contadorBotoes]);
                    joystick.setEscopoEixoY(escopoEixoY[contadorBotoes]);
                    joystick.setModoOperacaoEixoY(modoOperacaoY[contadorBotoes]);


                    componentes.add(joystick);

                    layoutPrincipal.setOnDragListener(new OuvirDrag());

                    alert.dismiss();
                }
                else
                {

                }
            }
        });

    }


    public void checkModoOperacaoEixoX( View view)
    {

        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
            case R.id.checkBoxDividirEixoXNovaTelaPersonalizada:
            {

                checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(true);
                tVchaveFimInverterX[contadorBotoes].setVisibility(View.INVISIBLE);
                tVchaveFimInverterX[contadorBotoes].setEnabled(false);
                novosTextViewCaracterJoyFimInverterX[contadorBotoes].setVisibility(View.INVISIBLE);
                novosTextViewCaracterJoyFimInverterX[contadorBotoes].setEnabled(false);

            }break;
            case R.id.checkBoxInverterEixoXNovaTelaPersonalizada:
            {
                checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(true);

                checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                tVchaveFimInverterX[contadorBotoes].setVisibility(View.VISIBLE);
                tVchaveFimInverterX[contadorBotoes].setEnabled(true);
                novosTextViewCaracterJoyFimInverterX[contadorBotoes].setVisibility(View.VISIBLE);
                novosTextViewCaracterJoyFimInverterX[contadorBotoes].setEnabled(true);




            }break;

        }

    }

    public void checkModoOperacaoEixoY( View view)
    {

        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
            case R.id.checkBoxDividirEixoYNovaTelaPersonalizada:
            {

                checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);
                tVchaveFimInverterY[contadorBotoes].setVisibility(View.INVISIBLE);
                tVchaveFimInverterY[contadorBotoes].setEnabled(false);
                novosTextViewCaracterJoyFimInverterY[contadorBotoes].setVisibility(View.INVISIBLE);
                novosTextViewCaracterJoyFimInverterY[contadorBotoes].setEnabled(false);

            }break;
            case R.id.checkBoxInverterEixoYNovaTelaPersonalizada:
            {
                checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);

                checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                tVchaveFimInverterY[contadorBotoes].setVisibility(View.VISIBLE);
                tVchaveFimInverterY[contadorBotoes].setEnabled(true);
                novosTextViewCaracterJoyFimInverterY[contadorBotoes].setVisibility(View.VISIBLE);
                novosTextViewCaracterJoyFimInverterY[contadorBotoes].setEnabled(true);

            }break;

        }

    }



    public void addNewSeekBar()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View v = li.inflate(R.layout.setup_new_seek_bar, null);

        SeekBarNome[contadorBotoes] = (EditText) v.findViewById(R.id.etSetupNewSeekBarNome);
        SeekBarChaveInicio[contadorBotoes] = (EditText) v.findViewById(R.id.etSetupNewSeekBarChaveInicio);
        SeekBarChaveFim[contadorBotoes] = (EditText) v.findViewById(R.id.etSetupNewSeekBarChaveFim);
        SeekBarIntervaloInicio[contadorBotoes] = (EditText) v.findViewById(R.id.etNewSeekBarIntervaloInicio);
        SeekBarIntervaloInicio[contadorBotoes].setText("0");
        SeekBarIntervaloFim[contadorBotoes] = (EditText) v.findViewById(R.id.etNewSeekBarIntervaloFim);


        builder.setTitle("Adicionar Deslizante");
        builder.setView(v);
        alerta_setup_new_seek_bar = builder.create();
        alerta_setup_new_seek_bar.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        alerta_setup_new_seek_bar.show();

        impedirRedimensionar(alerta_setup_new_seek_bar);

        btnFinaladdnewSeekBar = (Button) v.findViewById(R.id.btnFinalAddNewSeekBar);


        btnFinaladdnewSeekBar.setOnClickListener(new View.OnClickListener()
        {
            boolean AceitarCriar = false;

            @Override
            public void onClick(View view) {


                SSeekBarChaveInicio[contadorBotoes] = SeekBarChaveInicio[contadorBotoes].getText().toString();

                if (SSeekBarChaveInicio[contadorBotoes].length() > 1 || SSeekBarChaveInicio.equals(null) || SSeekBarChaveInicio.equals("")) {
                    Toast.makeText(getBaseContext(), "Chave Inicio Irregular, use apenas um caracter", Toast.LENGTH_SHORT).show();
                } else {
                    SSeekBarChaveFim[contadorBotoes] = SeekBarChaveFim[contadorBotoes].getText().toString();
                    if (SSeekBarChaveFim[contadorBotoes].length() > 1 || SSeekBarChaveFim[contadorBotoes].equals(null) || SSeekBarChaveFim[contadorBotoes].equals("")) {
                        Toast.makeText(getBaseContext(), "Chave Fim Irregular, use apenas um caracter", Toast.LENGTH_SHORT).show();

                    }
                    else{

                        try {
                            SSeekBarIntervaloInicio[contadorBotoes] = SeekBarIntervaloInicio[contadorBotoes].getText().toString();
                            SSeekBarIntervaloFim[contadorBotoes] = SeekBarIntervaloFim[contadorBotoes].getText().toString();

                            IntSeekBarIntervaloInicio[contadorBotoes] = Integer.parseInt(SSeekBarIntervaloInicio[contadorBotoes]);
                            IntSeekBarIntervaloFim[contadorBotoes] = Integer.parseInt(SSeekBarIntervaloFim[contadorBotoes]);
                            int escopo = IntSeekBarIntervaloFim[contadorBotoes] - IntSeekBarIntervaloInicio[contadorBotoes];
                            if (escopo < 0)
                            {
                                Toast.makeText(getBaseContext(), "Escopo não pode ser negativo", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                meuLayout[contadorBotoes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_seek_bar, null);
                                SSeekBarNome[contadorBotoes] = SeekBarNome[contadorBotoes].getText().toString();


                                meuLayout[contadorBotoes].setId(contadorBotoes);
                                ConstraintSet set = new ConstraintSet();
                                novosSeekBars[contadorBotoes] = (SeekBar) meuLayout[contadorBotoes].findViewById(R.id.add_new_seek_bar);
                                tvNomeSeekBar[contadorBotoes] = (TextView) meuLayout[contadorBotoes].findViewById(R.id.tvNewSeekBarNome);
                                tvNomeSeekBar[contadorBotoes].setId(contadorBotoes);
                                tvNomeSeekBar[contadorBotoes].setText(SSeekBarNome[contadorBotoes]);
                                novosSeekBars[contadorBotoes].setId(contadorBotoes);


                                //area de drag
                                areaDrag[contadorBotoes] = (LinearLayout) meuLayout[contadorBotoes].findViewById(R.id.areaDragSeekBar);

                                // novosSeekBars[contadorBotoes].setMin(Integer.parseInt(SSeekBarIntervaloInicio));
                                novosSeekBars[contadorBotoes].setMax(Integer.parseInt(SSeekBarIntervaloFim[contadorBotoes]));

                                novosSeekBars[contadorBotoes].setOnClickListener(v->
                                {
                                    configurarSeekBar(v.getId());
                                });

                                novosSeekBars[contadorBotoes].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                                        int id = seekBar.getId();
                                        if(modoConfigs)
                                        {
                                            //  configurarSeekBar(id);

                                        }
                                        else if(modoPosicionar)
                                        {

                                        }
                                        else {
                                            ControleSeekBar controle = new ControleSeekBar();
                                            String Dados = SSeekBarChaveInicio[id].concat(Integer.toString(i).concat(SSeekBarChaveFim[id]));
                                            controle.setDados(Dados);
                                            controle.execute();
                                        }
                                    }

                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {

                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {

                                    }
                                });

                                layoutPrincipal.addView(meuLayout[contadorBotoes]);
                                set.clone(layoutPrincipal);
                                set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, 200);
                                // set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, meuLayout[contadorBotoes].getBottom());
                                //set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, meuLayout[contadorBotoes].getRight());
                                set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, 200);
                                set.constrainHeight(meuLayout[contadorBotoes].getId(), meuLayout[contadorBotoes].getMinHeight());

                                set.applyTo(layoutPrincipal);

//                Log.i("ID", "O id na classe tela e: "+ novosJoysticks[contadorBotoes].getId());

                                meuLayout[contadorBotoes].setOnLongClickListener(new OuvirCliqueLongo());


                                Componente seekBar = new Componente();
                                seekBar.setIdComponente(contadorBotoes);
                                seekBar.setNomeComponente(SSeekBarNome[contadorBotoes]);
                                seekBar.setTipo("seekbar");
                                seekBar.setChaveInicio(SSeekBarChaveInicio[contadorBotoes]);
                                seekBar.setChaveFim(SSeekBarChaveFim[contadorBotoes]);
                                seekBar.setIntervaloInicio(IntSeekBarIntervaloInicio[contadorBotoes]);
                                seekBar.setIntervaloFim(IntSeekBarIntervaloFim[contadorBotoes]);

                                componentes.add(seekBar);


                                layoutPrincipal.setOnDragListener(new OuvirDrag());
                                contadorBotoes++;
                                alerta_setup_new_seek_bar.dismiss();
                            }//fim da verificacao de escopo
                        }//fin do try
                        catch (Exception e)
                        {
                            Toast.makeText(getBaseContext(), "Escopo Incorreto", Toast.LENGTH_SHORT).show();

                        }
                    }//final chavefim
                }//final chaveInicio
            }//final onClick
        });

    }

    public void configurarSeekBar(int idComponente)
    {

        int posicao = 0;
        for(int i = 0; i < componentes.size(); i++)
        {
            if(componentes.get(i).getIdComponente() == idComponente)
            {
                posicao = i;
                break;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);



        LayoutInflater li = getLayoutInflater();
        View v = li.inflate(R.layout.setup_new_seek_bar, null);

        SeekBarNome[contadorBotoes] = (EditText) v.findViewById(R.id.etSetupNewSeekBarNome);
        SeekBarNome[contadorBotoes].setText(componentes.get(posicao).getNomeComponente());

        SeekBarChaveInicio[contadorBotoes] = (EditText) v.findViewById(R.id.etSetupNewSeekBarChaveInicio);
        SeekBarChaveInicio[contadorBotoes].setText(componentes.get(posicao).getChaveInicio());

        SeekBarChaveFim[contadorBotoes] = (EditText) v.findViewById(R.id.etSetupNewSeekBarChaveFim);
        SeekBarChaveFim[contadorBotoes].setText(componentes.get(posicao).getChaveFim());


        SeekBarIntervaloInicio[contadorBotoes] = (EditText) v.findViewById(R.id.etNewSeekBarIntervaloInicio);
        SeekBarIntervaloInicio[contadorBotoes].setText(Integer.toString(componentes.get(posicao).getIntervaloInicio()));

        SeekBarIntervaloFim[contadorBotoes] = (EditText) v.findViewById(R.id.etNewSeekBarIntervaloFim);
        SeekBarIntervaloFim[contadorBotoes].setText(Integer.toString(componentes.get(posicao).getIntervaloFim()));

        builder.setTitle("Configurar Deslizante");
        builder.setView(v);
        alerta_setup_new_seek_bar = builder.create();
        alerta_setup_new_seek_bar.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        alerta_setup_new_seek_bar.show();

        impedirRedimensionar(alerta_setup_new_seek_bar);

        btnFinaladdnewSeekBar = (Button) v.findViewById(R.id.btnFinalAddNewSeekBar);


        int finalPosicao = posicao;
        btnFinaladdnewSeekBar.setOnClickListener(new View.OnClickListener()
        {
            boolean AceitarCriar = false;

            @Override
            public void onClick(View view) {


                SSeekBarChaveInicio[contadorBotoes] = SeekBarChaveInicio[contadorBotoes].getText().toString();

                if (SSeekBarChaveInicio[contadorBotoes].length() > 1 || SSeekBarChaveInicio.equals(null) || SSeekBarChaveInicio.equals("")) {
                    Toast.makeText(getBaseContext(), "Chave Inicio Irregular, use apenas um caracter", Toast.LENGTH_SHORT).show();
                } else {
                    SSeekBarChaveFim[contadorBotoes] = SeekBarChaveFim[contadorBotoes].getText().toString();
                    if (SSeekBarChaveFim[contadorBotoes].length() > 1 || SSeekBarChaveFim[contadorBotoes].equals(null) || SSeekBarChaveFim[contadorBotoes].equals("")) {
                        Toast.makeText(getBaseContext(), "Chave Fim Irregular, use apenas um caracter", Toast.LENGTH_SHORT).show();

                    }
                    else{

                        try
                        {
                            SSeekBarIntervaloInicio[contadorBotoes] = SeekBarIntervaloInicio[contadorBotoes].getText().toString();
                            SSeekBarIntervaloFim[contadorBotoes] = SeekBarIntervaloFim[contadorBotoes].getText().toString();

                            IntSeekBarIntervaloInicio[contadorBotoes] = Integer.parseInt(SSeekBarIntervaloInicio[contadorBotoes]);
                            IntSeekBarIntervaloFim[contadorBotoes] = Integer.parseInt(SSeekBarIntervaloFim[contadorBotoes]);
                            int escopo = IntSeekBarIntervaloFim[contadorBotoes]  - IntSeekBarIntervaloInicio[contadorBotoes];
                            if (escopo < 0)
                            {
                                Toast.makeText(getBaseContext(), "Escopo não pode ser negativo", Toast.LENGTH_SHORT).show();

                            }
                            else {

                                tvNomeSeekBar[idComponente].setText(SeekBarNome[contadorBotoes].getText().toString());
                                SSeekBarChaveInicio[idComponente] = SSeekBarChaveInicio[contadorBotoes];
                                SSeekBarChaveFim[idComponente] = SSeekBarChaveFim[contadorBotoes];
                                IntSeekBarIntervaloInicio[idComponente] = IntSeekBarIntervaloInicio[contadorBotoes];
                                IntSeekBarIntervaloFim[idComponente] = IntSeekBarIntervaloFim[contadorBotoes];
                                novosSeekBars[idComponente].setMax(IntSeekBarIntervaloFim[contadorBotoes]);





                                componentes.remove(finalPosicao);
                                Componente seekBar = new Componente();
                                seekBar.setIdComponente(idComponente);
                                seekBar.setNomeComponente(SeekBarNome[contadorBotoes].getText().toString());
                                seekBar.setTipo("seekbar");
                                seekBar.setChaveInicio(SSeekBarChaveInicio[contadorBotoes]);
                                seekBar.setChaveFim(SSeekBarChaveFim[contadorBotoes]);
                                seekBar.setIntervaloInicio(IntSeekBarIntervaloInicio[contadorBotoes]);
                                seekBar.setIntervaloFim(IntSeekBarIntervaloFim[contadorBotoes]);

                                componentes.add(seekBar);


                                alerta_setup_new_seek_bar.dismiss();
                            }//fim da verificacao de escopo
                        }//fin do try
                        catch (Exception e)
                        {
                            Toast.makeText(getBaseContext(), "Escopo Incorreto", Toast.LENGTH_SHORT).show();

                        }
                    }//final chavefim
                }//final chaveInicio
            }//final onClick
        });

    }



    public void checkTipoBotaoPredefinido( View view)
    {

        switch (view.getId())
        {
            case R.id.checkBoxBtntp1:
            {

                checkBoxBtntp1[contadorBotoes].setChecked(true);
                checkBoxBtnSeta[contadorBotoes].setChecked(false);
                tipoBotao[contadorBotoes] = 1;


            }break;
            case R.id.checkeckBoxBtnSeta:
            {

                checkBoxBtntp1[contadorBotoes].setChecked(false);
                checkBoxBtnSeta[contadorBotoes].setChecked(true);
                tipoBotao[contadorBotoes] = 2;

            }break;

        }

    }

    public void addNewButton() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.setup_make_btn, null);


        caracterEnvio = (EditText) view.findViewById(R.id.eTCaracterNewButton);
        nomeButton = (EditText) view.findViewById(R.id.eTNomeNewButton);




        builder.setTitle("Adicionar Botão");
        builder.setView(view);
        final AlertDialog alerta_setup_new_button = builder.create();

        alerta_setup_new_button.show();




        Button btnMakeBtnPerson = (Button) view.findViewById(R.id.btnMakeBtnPerson);
        btnMakeBtnPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderPerson = new AlertDialog.Builder(getBaseContext());


                LayoutInflater liBtnPerson = getLayoutInflater();
                View vBtnPerson = li.inflate(R.layout.setup_new_button_personalizado, null);

                final ArrayAdapter adapterComboCor = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, cores);
                adapterComboCor.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                comboCorBotao = (Spinner) vBtnPerson.findViewById(R.id.comboCorBotao);
                comboCorTexto = (Spinner) vBtnPerson.findViewById(R.id.comboCorTexto);

                Spinner comboFormatoBtn = (Spinner) vBtnPerson.findViewById(R.id.comboFormatoBotao);
                final ArrayAdapter adapterComboFormato = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, formatos);
                adapterComboFormato.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);


                builder.setTitle("Botão Personalizado");
                builder.setView(vBtnPerson);
                AlertDialog alerta_setup_new_button_person = builder.create();

                alerta_setup_new_button_person.show();

                Button btnPerson = (Button) vBtnPerson.findViewById(R.id.btnPersonalizado);
                btnPerson.setText(nomeButton.getText().toString());
                btnPerson.setTextColor(corText);


                Button btnFinalizarAddnewButtonPersonalizado = (Button) vBtnPerson.findViewById(R.id.btnFinalizarAddnewButtonPersonalizado);
                btnFinalizarAddnewButtonPersonalizado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SnomeButton = nomeButton.getText().toString();
                        ScaracterEnvio = caracterEnvio.getText().toString();
                        dados[contadorBotoes] = ScaracterEnvio;
                        meuLayout[contadorBotoes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_button, null);
                        meuLayout[contadorBotoes].setId(contadorBotoes);


                        novosBotoes[contadorBotoes] = (Button) meuLayout[contadorBotoes].findViewById(R.id.add_new_button);
                        if(tipoBotao[contadorBotoes] == 3) {
                            novosBotoes[contadorBotoes].setBackground(setBackground(corButton, 0));
                            novosBotoes[contadorBotoes].setText(nomeButton.getText().toString());
                            novosBotoes[contadorBotoes].setTextColor(corText);
                        }
                        else if(tipoBotao[contadorBotoes] == 4) {
                            novosBotoes[contadorBotoes].setBackground(setBackground(corButton, 1));
                            novosBotoes[contadorBotoes].setText(nomeButton.getText().toString());
                            novosBotoes[contadorBotoes].setTextColor(corText);                                }
                        else if(tipoBotao[contadorBotoes] == 5) {
                            novosBotoes[contadorBotoes].setBackground(setBackground(corButton, 2));
                            novosBotoes[contadorBotoes].setText(nomeButton.getText().toString());
                            novosBotoes[contadorBotoes].setTextColor(corText);
                        }

                        // novosBotoes[contadorBotoes].setRotation(rotacaoBotao[contadorBotoes]);

                        novosBotoes[contadorBotoes].setId(contadorBotoes);




                        Log.i("Tem", "O botao " + contadorBotoes + " recebeu id " + meuLayout[contadorBotoes].getId());

                        ConstraintSet set = new ConstraintSet();

                        novosTextViewCaracter[contadorBotoes] = (TextView) meuLayout[contadorBotoes].findViewById(R.id.caracter_new_button);
                        novosTextViewCaracter[contadorBotoes].setText(ScaracterEnvio);
                        novosTextViewCaracter[contadorBotoes].setVisibility(View.INVISIBLE);
                        novosTextViewCaracter[contadorBotoes].setEnabled(false);

                         areaDrag[contadorBotoes] = (LinearLayout) meuLayout[contadorBotoes].findViewById(R.id.areaDragBtn);


                        novosBotoes[contadorBotoes].setOnTouchListener(new View.OnTouchListener() {

                            @Override
                            public boolean onTouch(View view, final MotionEvent motionEvent) {

                                Button cliqueBotao = (Button) view;


                                int minhaPosicao = cliqueBotao.getId();
                                Log.i("Tem", "O botao pressionado foi " + minhaPosicao);

                                ControleDeDirecao controle = new ControleDeDirecao();
                                switch (motionEvent.getAction()) {
                                    case MotionEvent.ACTION_DOWN:
                                        if (clicando == false) {
                                            clicando = true;
                                            Log.i("Tem", "E: " + novosTextViewCaracter[view.getId()].getText().toString());
                                            controle.setDados(novosTextViewCaracter[view.getId()].getText().toString());
                                            controle.execute();
                                        }
                                        break;
                                    case MotionEvent.ACTION_UP:
                                        if(ativo) {
                                            connect.write("O".getBytes());
                                            connect.write("O".getBytes());
                                            connect.write("O".getBytes());
                                        }
                                        clicando = false;

                                        controle.cancel(true);


                                        break;
                                }
                                return false;
                            }

                        });


                        layoutPrincipal.addView(meuLayout[contadorBotoes]);
                        set.clone(layoutPrincipal);
                        set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, 90);
                        // set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, meuLayout[contadorBotoes].getBottom());
                        //set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, meuLayout[contadorBotoes].getRight());
                        set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, 5);
                        set.constrainHeight(meuLayout[contadorBotoes].getId(), meuLayout[contadorBotoes].getHeight());
                        set.applyTo(layoutPrincipal);

                        meuLayout[contadorBotoes].setOnLongClickListener(new OuvirCliqueLongo());
                        layoutPrincipal.setOnDragListener(new OuvirDrag());

                        Componente botao = new Componente();
                        botao.setNomeComponente(SnomeButton);
                        botao.setCaracterEnvio(novosTextViewCaracter[contadorBotoes].getText().toString());
                        botao.setTipo("botao");
                        botao.setIdComponente(contadorBotoes);

                        botao.setTipoBotao(tipoBotao[contadorBotoes]);
                        botao.setRotacaoBotao(rotacaoBotao[contadorBotoes]);

                        botao.setCor(corButton);
                        botao.setFormato(formatoButton);

                        componentes.add(botao);

                        alerta_setup_new_button.dismiss();
                        contadorBotoes++;
                        alerta_setup_new_button_person.dismiss();

                        // alerta_setup_botao_pre_definido.dismiss();

                    }
                });

                comboFormatoBtn.setAdapter(adapterComboFormato);
                comboFormatoBtn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Toast.makeText(getBaseContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

                        switch (parent.getItemAtPosition(position).toString()) {

                            case "Quadrado": {
                                btnPerson.setBackground(setBackground(corButton, 0));
                                btnPerson.setText(nomeButton.getText().toString());
                                btnPerson.setTextColor(corText);
                                tipoBotao[contadorBotoes] = 3;
                                formatoButton = 0;

                            }
                            break;

                            case "Circular": {
                                btnPerson.setBackground(setBackground(corButton, 1));
                                btnPerson.setText(nomeButton.getText().toString());
                                btnPerson.setTextColor(corText);
                                tipoBotao[contadorBotoes] = 4;
                                formatoButton = 1;


                            }
                            break;

                            case "Retangulo": {
                                btnPerson.setBackground(setBackground(corButton, 2));
                                btnPerson.setText(nomeButton.getText().toString());
                                btnPerson.setTextColor(corText);
                                tipoBotao[contadorBotoes] = 5;
                                formatoButton = 2;

                            }
                            break;


                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                comboCorTexto.setAdapter(adapterComboCor);
                comboCorBotao.setAdapter(adapterComboCor);
                comboCorBotao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (parent.getItemAtPosition(position).toString()) {
                            case "Azul": {
                                corButton = Color.BLUE;

                            }
                            break;

                            case "Amarelo": {
                                corButton = Color.YELLOW;


                            }
                            break;

                            case "Vermelho": {
                                corButton = Color.RED;

                            }
                            break;

                            case "Verde": {
                                corButton = GREEN;

                            }
                            break;

                        }
                        btnPerson.setBackground(setBackground(corButton, 0));


                    }


                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                comboCorTexto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (parent.getItemAtPosition(position).toString()) {
                            case "Azul": {
                                corText = Color.BLUE;
                            }
                            break;

                            case "Amarelo": {
                                corText = Color.YELLOW;


                            }
                            break;

                            case "Vermelho": {
                                corText = Color.RED;

                            }
                            break;

                            case "Verde": {
                                corText = GREEN;

                            }
                            break;

                        }

                        btnPerson.setTextColor(corText);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }
        });



        Button btnAddNewButtonProximo = (Button) view.findViewById(R.id.btnAddNewButtonProximo);

        btnAddNewButtonProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderPreDefinido = new AlertDialog.Builder(TelaNovoLayoutsPersonalizados.this);

                LayoutInflater liBtnPreDefinido = getLayoutInflater();
                View vBtnPreDefinido = li.inflate(R.layout.list_btns_pre_definidos, null);

                Spinner comboDirecaoSeta = (Spinner) vBtnPreDefinido.findViewById(R.id.spinnerDirecaoSeta);
                final ArrayAdapter adapterDirecao = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, direcoes);
                adapterDirecao.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                Spinner spinnerTipoBtnPreDefinido = (Spinner) vBtnPreDefinido.findViewById(R.id.spinnerTipoBtnPreDefinido);
                final ArrayAdapter adapterTipoBtnPreDefinido = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, tipoBtnPreDefinidos);
                adapterTipoBtnPreDefinido.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                spinnerTipoBtnPreDefinido.setAdapter(adapterTipoBtnPreDefinido);

                spinnerTipoBtnPreDefinido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (parent.getItemAtPosition(position).toString()) {
                            case "On": {
                                tipoBotao[contadorBotoes] = 5;
                                btnPreDefinido.setImageResource(R.drawable.btnon);
                            }
                            break;

                            case "Off": {
                                tipoBotao[contadorBotoes] = 6;
                                btnPreDefinido.setImageResource(R.drawable.btnoff);

                            } break;

                            case "Iniciar":
                            {
                                tipoBotao[contadorBotoes] = 7;
                                btnPreDefinido.setImageResource(R.drawable.btnstart);


                            }break;


                            case "1": {
                                corButton = Color.RED;

                            }
                            break;

                            case "2": {
                                corButton = GREEN;

                            }
                            break;

                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                builderPreDefinido.setTitle("Selecionar Botão Pré-Definido");
                builderPreDefinido.setView(vBtnPreDefinido);
                alerta_setup_botao_pre_definido = builderPreDefinido.create();
                alerta_setup_botao_pre_definido.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

                alerta_setup_botao_pre_definido.show();

                impedirRedimensionar(alerta_setup_botao_pre_definido);


                ImageButton btnSeta = (ImageButton) vBtnPreDefinido.findViewById(R.id.imgBtnSeta);
                btnPreDefinido = (ImageButton) vBtnPreDefinido.findViewById(R.id.imgBtnPreDefinido);

                checkBoxBtnSeta[contadorBotoes] = (CheckBox) vBtnPreDefinido.findViewById(R.id.checkeckBoxBtnSeta);
                checkBoxBtntp1[contadorBotoes] = (CheckBox) vBtnPreDefinido.findViewById(R.id.checkBoxBtntp1);

                checkBoxBtntp1[contadorBotoes].setChecked(true);
                tipoBotao[contadorBotoes] = 5;


                Button btnFinalizarAddNewButton = (Button) vBtnPreDefinido.findViewById(R.id.btnFinalizarAddNewButton);

                btnFinalizarAddNewButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SnomeButton = nomeButton.getText().toString();
                        ScaracterEnvio = caracterEnvio.getText().toString();
                        dados[contadorBotoes] = ScaracterEnvio;
                        meuLayout[contadorBotoes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_button, null);
                        meuLayout[contadorBotoes].setId(contadorBotoes);

                        areaDrag[contadorBotoes] = (LinearLayout) meuLayout[contadorBotoes].findViewById(R.id.areaDragBtn);


                        if(tipoBotao[contadorBotoes] == 1)
                        {
                            novosBotoes[contadorBotoes] = (Button) meuLayout[contadorBotoes].findViewById(R.id.add_new_button);
                            novosBotoes[contadorBotoes].setBackgroundResource(R.drawable.btnon);
                            novosBotoes[contadorBotoes].setText("");
                            novosBotoes[contadorBotoes].setId(contadorBotoes);

                        }

                        if(tipoBotao[contadorBotoes] == 5)
                        {
                            novosBotoes[contadorBotoes] = (Button) meuLayout[contadorBotoes].findViewById(R.id.add_new_button);
                            novosBotoes[contadorBotoes].setBackgroundResource(R.drawable.btnon);
                            novosBotoes[contadorBotoes].setText("");
                            novosBotoes[contadorBotoes].setId(contadorBotoes);
                        }
                        if(tipoBotao[contadorBotoes] == 6)
                        {
                            novosBotoes[contadorBotoes] = (Button) meuLayout[contadorBotoes].findViewById(R.id.add_new_button);
                            novosBotoes[contadorBotoes].setBackgroundResource(R.drawable.btnoff);
                            novosBotoes[contadorBotoes].setText("");
                            novosBotoes[contadorBotoes].setId(contadorBotoes);
                        }
                        if(tipoBotao[contadorBotoes] == 7)
                        {
                            novosBotoes[contadorBotoes] = (Button) meuLayout[contadorBotoes].findViewById(R.id.add_new_button);
                            novosBotoes[contadorBotoes].setBackgroundResource(R.drawable.btnstart);
                            novosBotoes[contadorBotoes].setText("");
                            novosBotoes[contadorBotoes].setId(contadorBotoes);
                        }
                        if(tipoBotao[contadorBotoes] == 2)
                        {
                            novosBotoes[contadorBotoes] = (Button) meuLayout[contadorBotoes].findViewById(R.id.add_new_button);
                            novosBotoes[contadorBotoes].setBackgroundResource(R.drawable.btnseta2);
                            novosBotoes[contadorBotoes].setRotation(rotacaoBotao[contadorBotoes]);
                            novosBotoes[contadorBotoes].setText("");
                            novosBotoes[contadorBotoes].setId(contadorBotoes);
                        }




                        Log.i("Tem", "O botao " + contadorBotoes + " recebeu id " + meuLayout[contadorBotoes].getId());

                        ConstraintSet set = new ConstraintSet();

                        novosTextViewCaracter[contadorBotoes] = (EditText) meuLayout[contadorBotoes].findViewById(R.id.caracter_new_button);
                        novosTextViewCaracter[contadorBotoes].setText(ScaracterEnvio);
                        novosTextViewCaracter[contadorBotoes].setVisibility(View.INVISIBLE);
                        novosTextViewCaracter[contadorBotoes].setEnabled(false);

                        novosBotoes[contadorBotoes].setOnTouchListener(new View.OnTouchListener() {

                            @Override
                            public boolean onTouch(View view, final MotionEvent motionEvent) {

                                Button cliqueBotao = (Button) view;


                                int minhaPosicao = cliqueBotao.getId();
                                Log.i("Tem", "O botao pressionado foi " + minhaPosicao);

                                ControleDeDirecao controle = new ControleDeDirecao();
                                switch (motionEvent.getAction()) {
                                    case MotionEvent.ACTION_DOWN:
                                        if (clicando == false) {
                                            clicando = true;
                                            Log.i("Tem", "E: " +  novosTextViewCaracter[view.getId()].getText().toString());
                                            controle.setDados( novosTextViewCaracter[view.getId()].getText().toString());
                                            controle.execute();
                                        }
                                        break;
                                    case MotionEvent.ACTION_UP:
                                        if(ativo) {
                                            connect.write("O".getBytes());
                                            connect.write("O".getBytes());
                                            connect.write("O".getBytes());
                                        }
                                        clicando = false;

                                        controle.cancel(true);


                                        break;
                                }
                                return false;
                            }

                        });


                        layoutPrincipal.addView(meuLayout[contadorBotoes]);
                        set.clone(layoutPrincipal);
                        set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, 90);
                        // set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, meuLayout[contadorBotoes].getBottom());
                        //set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, meuLayout[contadorBotoes].getRight());
                        set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, 5);
                        set.constrainHeight(meuLayout[contadorBotoes].getId(), meuLayout[contadorBotoes].getHeight());
                        set.applyTo(layoutPrincipal);

                        meuLayout[contadorBotoes].setOnLongClickListener(new OuvirCliqueLongo());

                        layoutPrincipal.setOnDragListener(new OuvirDrag());


                        Componente botao = new Componente();
                        botao.setNomeComponente(SnomeButton);
                        botao.setCaracterEnvio(ScaracterEnvio);
                        botao.setTipo("botao");
                        botao.setIdComponente(contadorBotoes);
                        botao.setTipoBotao(tipoBotao[contadorBotoes]);
                        botao.setRotacaoBotao(rotacaoBotao[contadorBotoes]);
                        componentes.add(botao);

                        contadorBotoes++;
                        alerta_setup_botao_pre_definido.dismiss();

                        alerta_setup_new_button.dismiss();
                    }
                });

                comboDirecaoSeta.setAdapter(adapterDirecao);
                comboDirecaoSeta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (parent.getItemAtPosition(position).toString()) {

                            case "Cima": {
                                btnSeta.setRotation(270);
                                rotacaoBotao[contadorBotoes] = 270;


                            }
                            break;

                            case "Baixo": {
                                btnSeta.setRotation(90);
                                rotacaoBotao[contadorBotoes] = 90;

                            }
                            break;

                            case "Esquerda": {
                                btnSeta.setRotation(180);
                                rotacaoBotao[contadorBotoes] = 180;


                            }
                            break;

                            case "Direita": {

                                btnSeta.setRotation(0);
                                rotacaoBotao[contadorBotoes] = 0;

                            }
                            break;

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                // Button btnMakeBtnPerson = (Button) vBtnPreDefinido.findViewById(R.id.btnMakeBtnPerson);



            }
        });

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

    // alerta.getWindow().setLayout(600, 500);




    public static Point getTouchPositionFromDragEvent (View v, DragEvent event)
    {
        Rect rItem = new Rect();
        v.getGlobalVisibleRect(rItem);
        return new Point(rItem.left + Math.round(event.getX()), rItem.top + Math.round(event.getY()));
    }




    class OuvirDrag implements View.OnDragListener
    {

        @Override
        public boolean onDrag(View v, DragEvent dragEvent) {
            View view = (View) dragEvent.getLocalState();

            int action = dragEvent.getAction();
            Log.i("Tem", "No drg lister o id do view e: " + view.getId() );

            switch (action)
            {
                case DragEvent.ACTION_DRAG_STARTED:
                    if(dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        Log.i("Posicao", "entrou no started");

                        return true;
                    }else {
                        Log.i("Posicao", "não entrou no started");

                        return false;
                    }
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.i("Posicao", "entrou no entered");

                    //  v.setBackgroundColor(Color.YELLOW);
                    if(v == exlcuir)
                    {
                        exlcuir.setBackgroundColor(Color.RED);
                    }



                    break;

                case DragEvent.ACTION_DRAG_LOCATION:
                    Log.i("Posicao", "Y: " + dragEvent.getY() + "X: " + dragEvent.getX());
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.i("Posicao", "entrou");
                    if(v == exlcuir)
                    {
                        exlcuir.setBackgroundColor(Color.TRANSPARENT);
                    }

                    //v.setBackgroundColor(Color.RED);
                    break;
                case DragEvent.ACTION_DROP:

                    if(v == exlcuir)
                    {
                        String removido = new String();
                        for(int i = 0; i < componentes.size(); i++)
                        {
                            if(componentes.get(i).getIdComponente() == view.getId()) {
                                removido = componentes.get(i).getTipo();
                                componentes.remove(i);
                                break;

                            }
                        }
                        ViewGroup dono = (ViewGroup) view.getParent();
                        dono.removeView(view);

                        //Toast.makeText(getBaseContext(), "id da view e:" + view.getId(), Toast.LENGTH_SHORT).show();


                        Toast.makeText(getBaseContext(), removido + " excluido", Toast.LENGTH_SHORT).show();
                        exlcuir.setBackgroundColor(Color.TRANSPARENT);

                    }
                    else {
                        try {
                            Point touchPosition = getTouchPositionFromDragEvent(v, dragEvent);

                            Log.i("Posicao", "touch Y : " + touchPosition.y + " X: " + touchPosition.x);
                            Log.i("Posicao", "dragevent Y : " + dragEvent.getY() + " X: " + dragEvent.getX());

                            ConstraintSet set = new ConstraintSet();
                            ViewGroup dono = (ViewGroup) view.getParent();
                            dono.removeView(view);
                            ConstraintLayout container = (ConstraintLayout) v;
                            container.addView(view);
                            view.setVisibility(View.VISIBLE);


                            set.clone(container);


                            int y = touchPosition.y - (view.getWidth() / 2);
                            int x = (int) dragEvent.getX() - (view.getHeight() / 2);


                            set.connect(view.getId(), ConstraintSet.TOP, container.getId(), ConstraintSet.TOP, y);
                            set.connect(view.getId(), ConstraintSet.LEFT, container.getId(), ConstraintSet.LEFT, x);
                            set.constrainHeight(view.getId(), view.getHeight());
                            set.constrainWidth(view.getId(), view.getWidth());

                            set.applyTo(container);

                            for (int i = 0; i < componentes.size(); i++) {
                                if (componentes.get(i).getIdComponente() == view.getId()) {
                                    Componente componente = new Componente();
                                    componente = componentes.get(i);
                                    componentes.remove(i);
                                    componente.setPositionX(x);
                                    componente.setPositionY(y);

                                    componentes.add(componente);


                                    break;
                                }


                            }


                            Log.i("Olha", "touch - 160 Y : " + (touchPosition.y - 160) + " X: " + (touchPosition.x - 100));
                        } catch (Exception h) {
                              Toast.makeText(getBaseContext(), "Não sobreponha componentes!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.i("Posicao", "entrou no ended");

                    // v.setBackgroundColor(Color.WHITE);
            }

            return true;

        }
    }






    class OuvirCliqueLongo implements View.OnLongClickListener
    {
        @Override
        public boolean onLongClick(View v) {
            String tipo = null;
            for(int i = 0; i < componentes.size(); i++)
            {
                if(componentes.get(i).getIdComponente() == v.getId()) {
                    tipo = componentes.get(i).getTipo();
                    break;

                }
            }
            Log.i("ViewName", "clique longo obtido");

            Log.i("ViewName", Integer.toString(v.getId()));

            if(modoConfigs)
            {
                Log.i("ViewName", "em modo configs");

                if(tipo.equals("botao"))
                {
                }
                else if(tipo.equals("seekbar"))
                {
                    configurarSeekBar(v.getId());
                }
                else if  (tipo.equals("joystick"))
                {
                    configurarJoystick(v.getId());
                }
            }
            else if(modoTestar)
            {

            }
            else if(modoPosicionar)
            {

                ClipData data = ClipData.newPlainText("simple_text", "text");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(v);
                //Sombra shadow = new Sombra(v);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    v.startDragAndDrop(data, shadow, v, 0);

                }

                v.setVisibility(View.INVISIBLE);
            }
            return true;

        }
    }

    @Override
    public void onJoystickMoved(float xPercent, float yPercent, int id) {


        if(modoConfigs)
        {
        }
        else if(modoPosicionar)
        {

        }
        else if(modoTestar){
            Log.i("Eixos", "Eixo x:" + xPercent + " Eixo y: " + yPercent);
            String caracter_final = null;

            if (checkBoxX[id].isChecked()) {
                int x = 0;
                String dados;
                if (modoOperacaoX[id] == 0) {
                   xPercent = xPercent * escopoEixoX[id] / 2;
                    x = (int) xPercent + escopoEixoX[id] / 2;
                    dados = Integer.toString(x);

                    caracter_final = ScaracterJoyFimX[id];


                } else {
                    xPercent = xPercent * escopoEixoX[id];
                    x = (int) xPercent;
                    dados = Integer.toString(x);

                    if(xPercent <= 0)
                        caracter_final = ScaracterJoyFimX[id];
                    else if(xPercent > 0)
                        caracter_final = ScaracterJoyFimInverterX[id];
                    else{}
                }
                //  Log.i("extras", "X percent: " + xPercent);
                ControleDirecaoX controleX = new ControleDirecaoX();
                String Dados = ScaracterJoyInicioX[id].concat(dados).concat(caracter_final);
                controleX.setDados(Dados, id);
                controleX.execute();
            }
            if (checkBoxY[id].isChecked()) {
                int y = 0;
                String dados;
                if (modoOperacaoY[id] == 0) {
                    Log.i("mod_operacao", "Dividir");

                    yPercent = yPercent * escopoEixoY[id] / 2;
                    y = (int) yPercent + escopoEixoY[id] / 2;
                    dados = Integer.toString(y);
                    caracter_final = ScaracterJoyFimY[id];


                } else {
                    Log.i("mod_operacao", "Inverter");



                    Log.i("Valores", "valor antes de aplica: " + yPercent);
                    yPercent = yPercent * escopoEixoY[id];
                    //y = (int) yPercent + escopoEixoY[id] ;
                    y = (int) (yPercent);
                    dados = Integer.toString(y);

                    if(yPercent <= 0)
                        caracter_final = ScaracterJoyFimY[id];
                    else if(yPercent > 0)
                        caracter_final = ScaracterJoyFimInverterY[id];
                    else{}

                    Log.i("Valores", "porcetagem: " + yPercent + "dado: " + dados);
                }

                //  Log.i("extras", "X percent: " + xPercent);
                ControleDirecaoY controleY = new ControleDirecaoY();
                String Dados = ScaracterJoyInicioY[id].concat(dados).concat(caracter_final);
                controleY.setDados(Dados, id);
                controleY.execute();
            }
        }
    }

    private class Sombra extends View.DragShadowBuilder{
        View box;

        public Sombra (View view)
        {
            super(view);
            box = view;
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            box.setBackgroundColor(Color.BLUE);
            box.draw(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point ShadowSize, Point ShadowTouchPoint) {
            View v = getView();
            int height = v.getHeight();
            int widght = v.getWidth();

            ShadowSize.set(widght, height);
            ShadowTouchPoint.set(widght/2, height/2);



        }
    }

    private class ControleDirecaoX extends AsyncTask {

        String Dados;
        int ID;

        public void setDados(String dados, int id) {
            this.Dados = dados;
            this.ID = id;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            //Log.i("Verificar", "dados antes de enviar e: " + Dados.concat(ScaracterJoyFimX[ID]));
            //String dados = Dados.concat(ScaracterJoyFimX[ID]);
            if(ativo && modoTestar)
                connect.write(Dados.getBytes());


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
        int ID;

        public void setDados(String dados, int id) {
            this.Dados = dados;
            this.ID = id;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            //Log.i("Verificar", "dados antes de enviar e: " + Dados.concat(ScaracterJoyFimY[ID]));
            //String dados = Dados.concat(ScaracterJoyFimY[ID]);
            if(ativo && modoTestar)
                connect.write(Dados.getBytes());


            return null;
        }

        @Override
        protected void onCancelled() {
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
            if(ativo && modoTestar)
                connect.write(Dados.getBytes());


            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("CONTROLE", "o botao chammou cancel");
        }
    }

    private class ControleDeDirecao extends AsyncTask {

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
                if(ativo && modoTestar)
                    connect.write(Dados.getBytes());
                try {
                    sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    clicando = false;
                }

            }
            return null;

        }
        @Override
        protected void onCancelled () {
            super.onCancelled();
            clicando = false;
            Log.i("CONTROLE", "o botao chammou cancel");
        }
    }






}



/*
 meuLayout[contadorBotoes].setOnLongClickListener(new View.OnLongClickListener() {


                public boolean onLongClick(View v) {

                    ClipData data = ClipData.newPlainText("simple_text", "text");
                    View.DragShadowBuilder shadow = new View.DragShadowBuilder(v);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        v.startDragAndDrop(data, shadow, v, 0);

                    }
                    v.setVisibility(View.INVISIBLE);
                    return true;
                }
            });


 layoutPrincipal.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent dragEvent) {


                    int action = dragEvent.getAction();
                    switch (action)
                    {
                        case DragEvent.ACTION_DRAG_STARTED:
                            if(dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                                Log.i("Posicao", "entrou no started");

                                return true;
                            }else {
                                Log.i("Posicao", "não entrou no started");

                                return false;
                            }
                        case DragEvent.ACTION_DRAG_ENTERED:
                            Log.i("Posicao", "entrou no entered");

                            v.setBackgroundColor(Color.YELLOW);
                            break;

                        case DragEvent.ACTION_DRAG_LOCATION:
                            Log.i("Posicao", "X: " + dragEvent.getX() + "Y: " + dragEvent.getY());
                            break;
                        case DragEvent.ACTION_DRAG_EXITED:
                            Log.i("Posicao", "entrou");

                            v.setBackgroundColor(Color.RED);
                            break;
                        case DragEvent.ACTION_DROP:
                            Log.i("Posicao", "entrou no drop");
                            ConstraintSet set = new ConstraintSet();
                            View view = (View) dragEvent.getLocalState();
                            ViewGroup dono = (ViewGroup) view.getParent();
                            dono.removeView(view);
                            ConstraintLayout container = (ConstraintLayout) v;
                            container.addView(view);
                            view.setVisibility(View.VISIBLE);

                            set.clone(container);
                            set.connect(view.getId(), ConstraintSet.TOP, container.getId(), ConstraintSet.TOP, 0);
                            // set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, meuLayout[contadorBotoes].getBottom());
                            //set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, meuLayout[contadorBotoes].getRight());
                            set.connect(view.getId(), ConstraintSet.LEFT, container.getId(), ConstraintSet.LEFT, 0);
                            set.constrainHeight(view.getId(), 200);
                            set.applyTo(container);



                          break;
                          case DragEvent.ACTION_DRAG_ENDED:
                              Log.i("Posicao", "entrou no ended");

                              v.setBackgroundColor(Color.BLUE);
                    }
                    return true;

                }
            });

 */