package com.example.titaniwmbluetoothcontrol;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

import com.example.titaniwmbluetoothcontrol.interfaces.TratarDados;
import com.github.anastr.speedviewlib.SpeedView;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import io.github.controlwear.virtual.joystick.android.JoystickView;

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


public class TelaNovoLayoutsPersonalizados extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ConstraintLayout meuLayout[] = new ConstraintLayout[50];
    ConstraintLayout layoutPrincipal;
    ConstraintLayout.LayoutParams meuParametros[] = new ConstraintLayout.LayoutParams[50];
    String SnomeButton;


    private double ultimoAngulo = 0;

    ArrayList<String> pinsComponentes = new ArrayList<>();

    private GestureDetector detector;
    private LinearLayout exlcuir;
    int corButton;
    int corButton2;



    int formatoButton;
    String dados[] = new String[50];
    int corText;
    boolean sinal[] = new boolean[50];
    String ScaracterEnvio;
    String ScaracterEnvio2;

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


    private String path_plano_fundo;

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

    CheckBox checkBoxBtnSeta2[] = new CheckBox[50];
    CheckBox checkBoxBtntp2[] = new CheckBox[50];


    Button novosBotoes[] = new Button[50];
    ImageView novosVolantes[] = new ImageView[50];
    int tipoBotao[] = new int[50];
    int tipoBotao2[] = new int[50];
    int rotacaoBotao[] = new int[50];
    int rotacaoBotao2[] = new int[50];

    TextView novosTextViewCaracter[] = new TextView[50];
    EditText novosTextViewCaracter2[] = new EditText[50];
    TextView tvCaracterEnvio2[] = new TextView[50];

    int modoOperacaoBotao[] = new int[50];
    boolean pressionado = false;
    int contadorBotaoPressionado = 0;


    int contadorBotoes = 1;

    JoystickView novosJoysticks[] = new JoystickView[50];
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
    EditText caracterEnvio, caracterEnvio2;

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
    private static final String[] modosOperacao = new String[]{"1 Toque/1 Caracter", "2 Toques/2 Caracteres", "Toque Longe/2 Caracteres"};

    private static final String[] icones = new String[]{"Tensão", "Temperatura", "Velocidade"};
    private static final String[] velocimetros = new String[]{"Opção 1", "Opção 2", "Opção 3"};




    private Button btnOkVisualizar;
    private Button btnVisualizar;

    private TextView modoAtual;

    private    ImageButton btnPreDefinido;
    private    ImageButton btnPreDefinido2;


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


    private int cChamPosicoes = 0, cChamConfigs = 0, cChamTestar = 0;

    //variaveis novo informações
    EditText eTTextoInfo[] = new EditText[50];

    EditText eTRegexInfoInicio[] = new EditText[50];
    EditText eTRegexInfoFim[] = new EditText[50];

    ImageView iconeInfo[] = new ImageView[50];

    TextView tvTextoInfo[] = new TextView[50];
    TextView tvRegexInfo[] = new TextView[50];

    String regexInicio[] = new String[50];
    String regexFim[] = new String[50];
    ImageView iVIconeInfo[] = new ImageView[50];
    int tipoIconeInfo[] = new int[50];


    //variaveis para velocimetros
    private int tipoVelocimetro[] = new int[50];
    private LinearLayout area_velocimetro[] = new LinearLayout[50];


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
                                if (novosTextViewCaracter2[i] != null){
                                    novosTextViewCaracter2[i].setVisibility(View.INVISIBLE);
                                    novosTextViewCaracter2[i].setEnabled(false);
                                }
                            }

                            for(int i = 0; i < contadorBotoes; i++){
                                if(areaDrag[i] != null){
                                    areaDrag[i].setBackgroundResource(R.drawable.fundo_drag);

                                }
                            }


                            if(cChamPosicoes <= 0 )
                            {
                                Toast.makeText(getBaseContext(), "Toque na area vermelha do componente e arraste-o para posiciona-lo", Toast.LENGTH_LONG).show();

                            }

                            cChamPosicoes++;




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
                            if(cChamConfigs <= 0 )
                            {
                                Toast.makeText(getBaseContext(), "Segure em um Componente para configura-lo", Toast.LENGTH_LONG).show();

                            }

                            cChamConfigs++;

                            for(int i = 0; i < contadorBotoes; i++){
                                if(novosTextViewCaracter[i] != null) {
                                    novosTextViewCaracter[i].setVisibility(View.VISIBLE);
                                    novosTextViewCaracter[i].setEnabled(true);
                                }
                                   if (novosTextViewCaracter2[i] != null){
                                        novosTextViewCaracter2[i].setVisibility(View.VISIBLE);
                                        novosTextViewCaracter2[i].setEnabled(true);
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


                        if(cChamTestar <= 0 )
                        {
                            Toast.makeText(getBaseContext(), "Toque em um Componente para testa-lo", Toast.LENGTH_LONG).show();

                        }

                        cChamTestar++;

                        for(int i = 0; i < contadorBotoes; i++){
                            if(novosTextViewCaracter[i] != null){
                                novosTextViewCaracter[i].setVisibility(View.INVISIBLE);
                                novosTextViewCaracter[i].setEnabled(false);
                            }
                            if (novosTextViewCaracter2[i] != null){
                                novosTextViewCaracter2[i].setVisibility(View.INVISIBLE);
                                novosTextViewCaracter2[i].setEnabled(false);
                            }

                        }
                        for(int i = 0; i < contadorBotoes; i++){
                            if(areaDrag[i] != null){
                                areaDrag[i].setBackgroundColor(TRANSPARENT);
                            }
                        }

                    } break;

                    case R.id.action_configs_delay:
                        {
                        configurarDelay();
                    }break;

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
            connect.setNovaPersonalizadaAtivo(true, this);

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
                addNewJoy(0, -1);
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

            case R.id.nav_item_five:
            {
                addNewInfo();
                break;
            }

            case R.id.nav_item_six:
            {
                addNewVelocimetro();
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

    public String getImagePath(Uri contentUri){
        String [] campos = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, campos, null, null, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
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
                String path = getImagePath(selectedImage);

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

                path_plano_fundo = path;

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

                        String escrita = null;
                        if (componentes.get(i).getTipo().equals("joystick")) {
                            boolean checkX = checkBoxX[componentes.get(i).getIdComponente()].isChecked();
                            boolean checkY = checkBoxY[componentes.get(i).getIdComponente()].isChecked();
                            escrita = "id" + componentes.get(i).getIdComponente() + "&;" +
                                   "nome" +  componentes.get(i).getNomeComponente() + "&;" +
                                    "tipoComponente" + componentes.get(i).getTipo() + "&;"  +
                                    "carcaterEnvio" + componentes.get(i).getCaracterEnvio() + "&;" +
                                    "posicaoX" + componentes.get(i).getPositionX() + "&;" +
                                    "posicaoY" + componentes.get(i).getPositionY() + "&;" +
                                    "chaveInicio" + componentes.get(i).getChaveInicio() + "&;" +
                                    "chaveFim" + componentes.get(i).getChaveFim() + "&;" +
                                   "intervaloInicio" + componentes.get(i).getIntervaloInicio() + "&;" +
                                    "intervaloFim" + componentes.get(i).getIntervaloFim() + "&;" +
                                    "checkX" + checkX + "&;" +
                                "chaveInicioEixoX" + componentes.get(i).getChaveInicioEixoX() + "&;" +
                                    "chaveFimEixoX" + componentes.get(i).getChaveFimEixoX() + "&;" +
                                    "chaveInverterEixoX" + componentes.get(i).getChaveFimInverterEixoX() + "&;" +

                                  "checkY"  + checkY + "&;" +
                                    "chaveInicioEixoY" + componentes.get(i).getChaveInicioEixoY() + "&;" +
                                    "chaveFimEixoY" + componentes.get(i).getChaveFimEixoY() + "&;" +
                                    "chaveInverterEixoY" + componentes.get(i).getChaveFimInverterEixoY() + "&;" +

                                   "intervaloInicioEixoX" + componentes.get(i).getIntervaloInicioEixoX() + "&;" +
                                    "intervaloFimEixoX" + componentes.get(i).getIntervaloFimEixoX() + "&;" +

                                    "escopoEixoX" + componentes.get(i).getEscopoEixoX() + "&;" +
                                    "modoOperacaoEixoX" + componentes.get(i).getModoOperacaoEixoX() + "&;" +

                                    "intervaloInicioEixoY" + componentes.get(i).getIntervaloInicioEixoY() + "&;" +
                                    "intervaloFimEixoY" + componentes.get(i).getIntervaloFimEixoY() + "&;" +
                                    "escopoEixoY" + componentes.get(i).getEscopoEixoY() + "&;" +
                                    "modoOperacaoEixoY" + componentes.get(i).getModoOperacaoEixoY() + "&;"



                            ;

                        } else if(componentes.get(i).getTipo().equals("botao")){

                            escrita = "id" + componentes.get(i).getIdComponente() + "&;" +
                                    "nome" +  componentes.get(i).getNomeComponente() + "&;" +
                                    "tipoComponente"  + componentes.get(i).getTipo() + "&;" +
                                    "caracterEnvioBotao1" + novosTextViewCaracter[componentes.get(i).getIdComponente()].getText().toString() + "&;" +

                                    "posicaoX"  + componentes.get(i).getPositionX() + "&;" +
                                    "posicaoY" + componentes.get(i).getPositionY() + "&;" +
                                    "chaveInicio" + componentes.get(i).getChaveInicio() + "&;" +
                                    "chaveFim" + componentes.get(i).getChaveFim() + "&;" +
                                    "intervaloInicio" + componentes.get(i).getIntervaloInicio() + "&;" +
                                    "intervaloFim"   + componentes.get(i).getIntervaloFim() + "&;" +
                                  /* + componentes.get(i).isEixoX() + "&;" +
                                    + componentes.get(i).getChaveInicioEixoX() + "&;" +
                                    + componentes.get(i).getChaveFimEixoX() + "&;" +
                                    + componentes.get(i).isEixoY() + "&;" +
                                    + componentes.get(i).getChaveInicioEixoY() + "&;" +
                                    + componentes.get(i).getChaveFimEixoY() + "&;" +*/
                                    "tipoBotao1" + componentes.get(i).getTipoBotao() + "&;" +
                                    "rotacaoBotao1" + componentes.get(i).getRotacaoBotao() + "&;" +


                                    "caracterEnvioBotao2" + novosTextViewCaracter2[componentes.get(i).getIdComponente()].getText().toString() + "&;" +
                                    "tipoBotao2" + componentes.get(i).getTipoBotao2() + "&;" +
                                    "rotacaoBotao2" + componentes.get(i).getRotacaoBotao2() + "&;" +
                                    "modoOperacaoBotao" + componentes.get(i).getModoOperacaoBotao() + "&;"


                            ;

                        }
                        else if(componentes.get(i).getTipo().equals("seekbar")){
                            escrita = "id" + componentes.get(i).getIdComponente() + "&;" +
                                   "nome" +  componentes.get(i).getNomeComponente() + "&;" +
                                   "tipoComponente"  + componentes.get(i).getTipo() + "&;" +

                                  "posicaoX"  + componentes.get(i).getPositionX() + "&;" +
                                    "posicaoY" + componentes.get(i).getPositionY() + "&;" +
                                    "chaveInicio" + componentes.get(i).getChaveInicio() + "&;" +
                                   "chaveFim" + componentes.get(i).getChaveFim() + "&;" +
                                   "intervaloInicio" + componentes.get(i).getIntervaloInicio() + "&;" +
                                 "intervaloFim"   + componentes.get(i).getIntervaloFim() + "&;"
                                  /* + componentes.get(i).isEixoX() + "&;" +
                                    + componentes.get(i).getChaveInicioEixoX() + "&;" +
                                    + componentes.get(i).getChaveFimEixoX() + "&;" +
                                    + componentes.get(i).isEixoY() + "&;" +
                                    + componentes.get(i).getChaveInicioEixoY() + "&;" +
                                    + componentes.get(i).getChaveFimEixoY() + "&;" +*/



                            ;

                        }else if(componentes.get(i).getTipo().equals("info")){
                            escrita = "id" + componentes.get(i).getIdComponente() + "&;" +
                                    "nome" +  componentes.get(i).getNomeComponente() + "&;" +
                                    "tipoComponente"  + componentes.get(i).getTipo() + "&;" +

                                    "posicaoX"  + componentes.get(i).getPositionX() + "&;" +
                                    "posicaoY" + componentes.get(i).getPositionY() + "&;" +
                                    "regexInicio" + componentes.get(i).getChaveInicio() + "&;" +
                                    "regexFim" + componentes.get(i).getChaveFim() + "&;" +
                                    "fundo" + componentes.get(i).getTipoBotao() + "&;" +
                                    "texto"   + componentes.get(i).getCaracterEnvio() + "&;";

                        }

                        manipularArquivos.escreverArquivo("LayoutsPersonalizados", nomeArquivo, escrita);





                    }

                    manipularArquivos.escreverArquivo("LayoutsPersonalizados", nomeArquivo, "delay" + Integer.toString(delay) + "&");

                    alert.dismiss();
                    sp1.setDados("Contador_Layouts", 0, "contador_lay", contador);
                    manipularArquivos.criarDiretorio("LayoutsPersonalizados/imgs");

                    //cria a imagem

                   // manipularArquivos.criarArquivo("layout" + Integer.toString(contador) + ".jpg", "LayoutsPersonalizados/imgs");
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


                    //salva o plano de fundo
                    if(path_plano_fundo != null && path_plano_fundo.length() > 0 && !path_plano_fundo.equals(""))
                    {
                        manipularArquivos.escreverArquivo("LayoutsPersonalizados", nomeArquivo,"fundo" + path_plano_fundo + "&");

                    }

                    finish();
                }


            }.start();



        });



    }




  public void addNewInfo(){
      final AlertDialog alert;


      AlertDialog.Builder builder = new AlertDialog.Builder(this);


      LayoutInflater li = getLayoutInflater();
      View v = li.inflate(R.layout.setup_new_info, null);


      builder.setTitle("Adicionar Informações");
      builder.setView(v);
      alert = builder.create();
      alert.show();

      ImageView icone_pre_vizu = v.findViewById(R.id.iVIconeInfoV);


      TextView tv_pre_vizu = v.findViewById(R.id.tvTextoInfoV);
      TextView regex_pre_vizu = v.findViewById(R.id.tvRegexV);

      eTRegexInfoInicio[contadorBotoes] = v.findViewById(R.id.eTRegexInfoInicio);
      eTRegexInfoInicio[contadorBotoes].setId(contadorBotoes);
      eTRegexInfoInicio[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);

      eTRegexInfoFim[contadorBotoes] = v.findViewById(R.id.eTRegexInfoFim);
      eTRegexInfoFim[contadorBotoes].setId(contadorBotoes);
      eTRegexInfoFim[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);


      eTTextoInfo[contadorBotoes] = v.findViewById(R.id.eTTextoInfo);
      eTTextoInfo[contadorBotoes].setId(contadorBotoes);
      eTTextoInfo[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);

      eTTextoInfo[contadorBotoes].addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {

          }

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {

          }

          @Override
          public void afterTextChanged(Editable s) {
              if(s != null && s.length() > 0){
                  tv_pre_vizu.setText(s);
              }
          }
      });

      Spinner iconeInfo = (Spinner) v.findViewById(R.id.cBIconeInfo);
      final ArrayAdapter adapterIconeInfo = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, icones);
      adapterIconeInfo.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
       iconeInfo.setAdapter(adapterIconeInfo);
      //instancia o new_info pre vizualizado





      iconeInfo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              switch (parent.getItemAtPosition(position).toString()) {
                  case "Tensão": {
                     icone_pre_vizu.setBackgroundResource(R.drawable.bluetooth);
                      tipoIconeInfo[contadorBotoes] = 2;

                  }
                  break;

                  case "Temperatura": {
                      icone_pre_vizu.setBackgroundResource(R.drawable.borda);
                      tipoIconeInfo[contadorBotoes] = 1;


                  }
                  break;

                  case "Velocidade":{
                      icone_pre_vizu.setBackgroundResource(R.drawable.bluetoothdevice);
                      tipoIconeInfo[contadorBotoes] = 3;

                  }break;
              }

          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });

      icone_pre_vizu.setBackgroundResource(R.drawable.borda);
      tv_pre_vizu.setText("Exemplo:");
      eTTextoInfo[contadorBotoes].setText("Exemplo:");


      Button btnCriarNewInfo = v.findViewById(R.id.btnCriarNewInfo);
      btnCriarNewInfo.setOnClickListener(f->{
          String texto = eTTextoInfo[contadorBotoes].getText().toString();
           regexInicio[contadorBotoes] = eTRegexInfoInicio[contadorBotoes].getText().toString();
           regexFim[contadorBotoes] = eTRegexInfoFim[contadorBotoes].getText().toString();

          meuLayout[contadorBotoes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_info, null);
          meuLayout[contadorBotoes].setId(contadorBotoes);

          areaDrag[contadorBotoes] = (LinearLayout) meuLayout[contadorBotoes].findViewById(R.id.areaDragInfo);

          tvRegexInfo[contadorBotoes] = meuLayout[contadorBotoes].findViewById(R.id.tvRegex);
          tvRegexInfo[contadorBotoes].setId(contadorBotoes);
          tvRegexInfo[contadorBotoes].setText("-----");

          tvTextoInfo[contadorBotoes] = meuLayout[contadorBotoes].findViewById(R.id.tvTextoInfo);
          tvTextoInfo[contadorBotoes].setId(contadorBotoes);
          tvTextoInfo[contadorBotoes].setText(texto);

          iVIconeInfo[contadorBotoes] = (ImageView) meuLayout[contadorBotoes].findViewById(R.id.iVIconeInfo);
          alterarFundoBotaoInfo(iVIconeInfo[contadorBotoes], tipoIconeInfo[contadorBotoes]);



          ConstraintSet set = new ConstraintSet();

          layoutPrincipal.addView(meuLayout[contadorBotoes]);
          set.clone(layoutPrincipal);
          set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, 0);
          // set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, meuLayout[contadorBotoes].getBottom());
          //set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, meuLayout[contadorBotoes].getRight());
          set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, 0);
          set.constrainHeight(meuLayout[contadorBotoes].getId(), meuLayout[contadorBotoes].getMinHeight());

          set.applyTo(layoutPrincipal);
          meuLayout[contadorBotoes].setOnLongClickListener(new OuvirCliqueLongo());

          Componente nova_info = new Componente();
          nova_info.setIdComponente(contadorBotoes);
          nova_info.setChaveInicio(regexInicio[contadorBotoes]);
          nova_info.setChaveFim(regexFim[contadorBotoes]);
          nova_info.setCaracterEnvio(texto);
          nova_info.setTipo("info");
          nova_info.setTipoBotao(tipoIconeInfo[contadorBotoes]);
          componentes.add(nova_info);


          layoutPrincipal.setOnDragListener(new OuvirDrag());
          contadorBotoes++;
          alert.dismiss();

                alert.dismiss();
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

        detector = new GestureDetector(this, new MyGestureDetector(  novosVolantes[contadorBotoes]));

        novosVolantes[contadorBotoes].setOnTouchListener(new TouchVolante());




        OnGlobalOuvinte ouvinteGlobal = new OnGlobalOuvinte();
        ouvinteGlobal.setView( novosVolantes[contadorBotoes]);

        novosVolantes[contadorBotoes].getViewTreeObserver().addOnGlobalLayoutListener(ouvinteGlobal);


        //meuLayout[contadorBotoes].setOnDragListener(new OuvirDrag());
        meuLayout[contadorBotoes].setOnLongClickListener(new OuvirCliqueLongo());
        layoutPrincipal.setOnDragListener(new OuvirDrag());

        contadorBotoes++;

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
                matrix.postTranslate(translateX, translateY);

                view.setImageBitmap(imagemRedimensionada);
                view.setImageMatrix(matrix);

            }
        }

    }

    private class TouchVolante implements View.OnTouchListener{

        private double startAngle;
        private double anguloTocado;


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch(event.getAction())
            {

                case MotionEvent.ACTION_DOWN:
                    if(modoTestar) {

                      anguloTocado =   getAngle(event.getX(), event.getY(), (ImageView) v);

                      startAngle = getAngle(event.getX(), event.getY(), (ImageView) v);
                        Log.i("AnguloInicial", "" + startAngle);
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(modoTestar) {
                       double anguloAtual = getAngle(event.getX(), event.getY(), (ImageView) v);
                           rotacionarDialer((float) (startAngle - anguloAtual), (ImageView) v);

                           startAngle = anguloAtual;
                           ultimoAngulo = anguloAtual;
                        Log.i("Graus2",  "Angulo Atual: " + anguloAtual);

                    }
                    break;
                case MotionEvent.ACTION_UP:{

                }
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
        //Log.i("Graus4", "" + degrees);

        view.setImageMatrix(matrix);
    }

    private void impedirRedimensionar(AlertDialog alert){
        alert.getWindow().getDecorView().setSystemUiVisibility(
                TelaNovoLayoutsPersonalizados.this.getWindow().getDecorView().getSystemUiVisibility()
        );

        alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }



    public void addNewJoy(int flag, int idComponente) {
        final AlertDialog alert;

        int posicao = 0;
       if(flag == 1) {//modo edicao

        for(int i = 0; i < componentes.size();i++)
        {
            if(componentes.get(i).getIdComponente() == idComponente)
            {

                posicao = i;

                break;
            }
        }}


        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View v = li.inflate(R.layout.setup_new_joystick, null);


        builder.setTitle("Adicionar Joystick");
        builder.setView(v);
        alert = builder.create();
        alert.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        alert.show();

        impedirRedimensionar(alert);

        novosTextViewCaracterJoyIcicioX[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_inicio_joyX);
        novosTextViewCaracterJoyIcicioX[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyIcicioX[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);


        novosTextViewCaracterJoyFimX[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_fim_joyX);
        novosTextViewCaracterJoyFimX[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyFimX[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);



        novosTextViewCaracterJoyFimInverterX[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_fim_inverter_joyX);
        novosTextViewCaracterJoyFimInverterX[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyFimInverterX[contadorBotoes].setVisibility(View.INVISIBLE);
        novosTextViewCaracterJoyFimInverterX[contadorBotoes].setEnabled(false);
        novosTextViewCaracterJoyFimInverterX[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);


        tVchaveFimInverterX[contadorBotoes] = v.findViewById(R.id.tVchaveFimInverterX);
        tVchaveFimInverterX[contadorBotoes].setId(contadorBotoes);
        tVchaveFimInverterX[contadorBotoes].setVisibility(View.INVISIBLE);
        tVchaveFimInverterX[contadorBotoes].setEnabled(false);



        novosTextViewCaracterJoyFimY[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_fim_joyY);
        novosTextViewCaracterJoyIcicioY[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_inicio_joyY);
        novosTextViewCaracterJoyFimY[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyIcicioY[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyFimY[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        novosTextViewCaracterJoyIcicioY[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);

        novosTextViewCaracterJoyFimInverterY[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_fim_inverter_joyY);
        novosTextViewCaracterJoyFimInverterY[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyFimInverterY[contadorBotoes].setVisibility(View.INVISIBLE);
        novosTextViewCaracterJoyFimInverterY[contadorBotoes].setEnabled(false);
        novosTextViewCaracterJoyFimInverterY[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);

        tVchaveFimInverterY[contadorBotoes] = v.findViewById(R.id.tVchaveFimInverterY);
        tVchaveFimInverterY[contadorBotoes].setId(contadorBotoes);
        tVchaveFimInverterY[contadorBotoes].setVisibility(View.INVISIBLE);
        tVchaveFimInverterY[contadorBotoes].setEnabled(false);

        nome_add_new_joystick[contadorBotoes] = (EditText) v.findViewById(R.id.nome_add_new_joystick);
        nome_add_new_joystick[contadorBotoes].setId(contadorBotoes);
        nome_add_new_joystick[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);

        checkBoxX[contadorBotoes] = (CheckBox) v.findViewById(R.id.checkBoxX);
        checkBoxX[contadorBotoes].setId(contadorBotoes);
        checkBoxY[contadorBotoes] = (CheckBox) v.findViewById(R.id.checkBoxY);
        checkBoxY[contadorBotoes].setId(contadorBotoes);
        checkBoxX[contadorBotoes].setChecked(true);
        checkBoxY[contadorBotoes].setChecked(false);


        checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes] = (CheckBox) v.findViewById(R.id.checkBoxDividirEixoXNovaTelaPersonalizada);
        checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes]= (CheckBox) v.findViewById(R.id.checkBoxInverterEixoXNovaTelaPersonalizada);
        checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setId(contadorBotoes);
        checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setId(contadorBotoes);

        //listeners checkbox
        checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                    checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(true);
                    tVchaveFimInverterX[contadorBotoes].setVisibility(View.INVISIBLE);
                    tVchaveFimInverterX[contadorBotoes].setEnabled(false);
                    novosTextViewCaracterJoyFimInverterX[contadorBotoes].setVisibility(View.INVISIBLE);
                    novosTextViewCaracterJoyFimInverterX[contadorBotoes].setEnabled(false);
                    modoOperacaoX[contadorBotoes] = 0;
                }
                else
                    {
                        checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(true);

                        checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                        tVchaveFimInverterX[contadorBotoes].setVisibility(View.VISIBLE);
                        tVchaveFimInverterX[contadorBotoes].setEnabled(true);
                        novosTextViewCaracterJoyFimInverterX[contadorBotoes].setVisibility(View.VISIBLE);
                        novosTextViewCaracterJoyFimInverterX[contadorBotoes].setEnabled(true);
                        modoOperacaoX[contadorBotoes] = 1;

                    }

            }
        });

        checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(true);

                    checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                    tVchaveFimInverterX[contadorBotoes].setVisibility(View.VISIBLE);
                    tVchaveFimInverterX[contadorBotoes].setEnabled(true);
                    novosTextViewCaracterJoyFimInverterX[contadorBotoes].setVisibility(View.VISIBLE);
                    novosTextViewCaracterJoyFimInverterX[contadorBotoes].setEnabled(true);
                    modoOperacaoX[contadorBotoes] = 1;

                }
                else
                {
                    checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                    checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(true);
                    tVchaveFimInverterX[contadorBotoes].setVisibility(View.INVISIBLE);
                    tVchaveFimInverterX[contadorBotoes].setEnabled(false);
                    novosTextViewCaracterJoyFimInverterX[contadorBotoes].setVisibility(View.INVISIBLE);
                    novosTextViewCaracterJoyFimInverterX[contadorBotoes].setEnabled(false);
                    modoOperacaoX[contadorBotoes] = 0;


                }

            }
        });


        checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(true);
        checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(false);

        checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes] = (CheckBox) v.findViewById(R.id.checkBoxDividirEixoYNovaTelaPersonalizada);
        checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes]= (CheckBox) v.findViewById(R.id.checkBoxInverterEixoYNovaTelaPersonalizada);
        checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setId(contadorBotoes);
        checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setId(contadorBotoes);


        checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);
        checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);

        checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                    checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);
                    tVchaveFimInverterY[contadorBotoes].setVisibility(View.INVISIBLE);
                    tVchaveFimInverterY[contadorBotoes].setEnabled(false);
                    novosTextViewCaracterJoyFimInverterY[contadorBotoes].setVisibility(View.INVISIBLE);
                    novosTextViewCaracterJoyFimInverterY[contadorBotoes].setEnabled(false);
                    modoOperacaoY[contadorBotoes] = 0;
                }
                else
                {
                    checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);

                    checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                    tVchaveFimInverterY[contadorBotoes].setVisibility(View.VISIBLE);
                    tVchaveFimInverterY[contadorBotoes].setEnabled(true);
                    novosTextViewCaracterJoyFimInverterY[contadorBotoes].setVisibility(View.VISIBLE);
                    novosTextViewCaracterJoyFimInverterY[contadorBotoes].setEnabled(true);
                    modoOperacaoY[contadorBotoes] =  1;
                }

            }
        });

        checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);

                    checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                    tVchaveFimInverterY[contadorBotoes].setVisibility(View.VISIBLE);
                    tVchaveFimInverterY[contadorBotoes].setEnabled(true);
                    novosTextViewCaracterJoyFimInverterY[contadorBotoes].setVisibility(View.VISIBLE);
                    novosTextViewCaracterJoyFimInverterY[contadorBotoes].setEnabled(true);
                    modoOperacaoY[contadorBotoes] = 1;
                }
                else
                {
                    checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                    checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);
                    tVchaveFimInverterY[contadorBotoes].setVisibility(View.INVISIBLE);
                    tVchaveFimInverterY[contadorBotoes].setEnabled(false);
                    novosTextViewCaracterJoyFimInverterY[contadorBotoes].setVisibility(View.INVISIBLE);
                    novosTextViewCaracterJoyFimInverterY[contadorBotoes].setEnabled(false);
                    modoOperacaoY[contadorBotoes] = 0;

                }

            }
        });


        eTIntervaloInicioEixoXNovaTelaPersonalizada[contadorBotoes] = (EditText) v.findViewById(R.id.eTIntervaloInicioJoyXNovaTelaPersonalizada);
        eTIntervaloInicioEixoXNovaTelaPersonalizada[contadorBotoes].setId(contadorBotoes);
        eTIntervaloFimEixoXNovaTelaPersonalizada[contadorBotoes] = (EditText) v.findViewById(R.id.eTIntervaloFimJoyXNovaTelaPersonalizada);
        eTIntervaloFimEixoXNovaTelaPersonalizada[contadorBotoes].setId(contadorBotoes);

        eTIntervaloInicioEixoXNovaTelaPersonalizada[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        eTIntervaloFimEixoXNovaTelaPersonalizada[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);



        eTIntervaloInicioEixoYNovaTelaPersonalizada[contadorBotoes] = (EditText) v.findViewById(R.id.eTIntervaloInicioJoyYNovaTelaPersonalizada);
        eTIntervaloInicioEixoYNovaTelaPersonalizada[contadorBotoes].setId(contadorBotoes);
        eTIntervaloFimEixoYNovaTelaPersonalizada[contadorBotoes] = (EditText) v.findViewById(R.id.eTIntervaloFimJoyYNovaTelaPersonalizada);
        eTIntervaloFimEixoYNovaTelaPersonalizada[contadorBotoes].setId(contadorBotoes);

        eTIntervaloInicioEixoYNovaTelaPersonalizada[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        eTIntervaloFimEixoYNovaTelaPersonalizada[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);


        if(flag == 1){
            nome_add_new_joystick[contadorBotoes].setText(componentes.get(posicao).getNomeComponente());

            if(componentes.get(posicao).isEixoX()){
                checkBoxX[contadorBotoes].setChecked(true);


                novosTextViewCaracterJoyIcicioX[contadorBotoes].setText(componentes.get(posicao).getChaveInicioEixoX());
                novosTextViewCaracterJoyFimX[contadorBotoes].setText(componentes.get(posicao).getChaveFimEixoX());
                eTIntervaloInicioEixoXNovaTelaPersonalizada[contadorBotoes].setText(Integer.toString(componentes.get(posicao).getIntervaloInicioEixoX()));
                eTIntervaloFimEixoXNovaTelaPersonalizada[contadorBotoes].setText(Integer.toString(componentes.get(posicao).getIntervaloFimEixoX()));

                if( componentes.get(posicao).getModoOperacaoEixoX() == 1){

                    checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                    checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(true);
                    tVchaveFimInverterX[contadorBotoes].setVisibility(View.VISIBLE);
                    tVchaveFimInverterX[contadorBotoes].setEnabled(true);
                    novosTextViewCaracterJoyFimInverterX[contadorBotoes].setText(componentes.get(posicao).getChaveFimInverterEixoX());
                    novosTextViewCaracterJoyFimInverterX[contadorBotoes].setVisibility(View.VISIBLE);
                    novosTextViewCaracterJoyFimInverterX[contadorBotoes].setEnabled(true);

                }else{

                    checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(true);
                    checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                    tVchaveFimInverterX[contadorBotoes].setVisibility(View.INVISIBLE);
                    tVchaveFimInverterX[contadorBotoes].setEnabled(false);
                }

            }else{
                checkBoxX[contadorBotoes].setChecked(false);

                novosTextViewCaracterJoyIcicioX[contadorBotoes].setText("");
                novosTextViewCaracterJoyFimX[contadorBotoes].setText("");
                eTIntervaloInicioEixoXNovaTelaPersonalizada[contadorBotoes].setText("");
                eTIntervaloFimEixoXNovaTelaPersonalizada[contadorBotoes].setText("");
                checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(true);
                checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                tVchaveFimInverterX[contadorBotoes].setVisibility(View.INVISIBLE);
                tVchaveFimInverterX[contadorBotoes].setEnabled(false);
            }
            if(componentes.get(posicao).isEixoY() ){
                checkBoxY[contadorBotoes].setChecked(true);

                novosTextViewCaracterJoyIcicioY[contadorBotoes].setText(componentes.get(posicao).getChaveInicioEixoY());
                novosTextViewCaracterJoyFimY[contadorBotoes].setText(componentes.get(posicao).getChaveFimEixoY());
                eTIntervaloInicioEixoYNovaTelaPersonalizada[contadorBotoes].setText(Integer.toString(componentes.get(posicao).getIntervaloInicioEixoY()));
                eTIntervaloFimEixoYNovaTelaPersonalizada[contadorBotoes].setText(Integer.toString(componentes.get(posicao).getIntervaloFimEixoY()));

                if(  componentes.get(posicao).getModoOperacaoEixoY() == 1){

                    checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                    checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);
                    novosTextViewCaracterJoyFimInverterY[contadorBotoes].setText(componentes.get(posicao).getChaveFimInverterEixoY());
                    tVchaveFimInverterY[contadorBotoes].setVisibility(View.VISIBLE);
                    tVchaveFimInverterY[contadorBotoes].setEnabled(true);
                    novosTextViewCaracterJoyFimInverterY[contadorBotoes].setVisibility(View.VISIBLE);
                    novosTextViewCaracterJoyFimInverterY[contadorBotoes].setEnabled(true);

                }else{
                    checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);
                    checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                    tVchaveFimInverterY[contadorBotoes].setVisibility(View.INVISIBLE);
                    tVchaveFimInverterY[contadorBotoes].setEnabled(false);
                }

            }else{
                checkBoxY[contadorBotoes].setChecked(false);

                novosTextViewCaracterJoyIcicioY[contadorBotoes].setText("");
                novosTextViewCaracterJoyFimY[contadorBotoes].setText("");
                eTIntervaloInicioEixoYNovaTelaPersonalizada[contadorBotoes].setText("");
                eTIntervaloFimEixoYNovaTelaPersonalizada[contadorBotoes].setText("");
                checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);
                checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);
                tVchaveFimInverterY[contadorBotoes].setVisibility(View.INVISIBLE);
                tVchaveFimInverterY[contadorBotoes].setEnabled(false);
            }
        }




        btnFinaladdnewJoy = (Button) v.findViewById(R.id.btnFinalAddNewJoy);


        int finalPosicao = posicao;


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


                if(checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].isChecked()) {
                    modoOperacaoX[contadorBotoes] = 0;
                }
                else {
                    modoOperacaoX[contadorBotoes] = 1;

                }

                if(checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].isChecked()) {
                    modoOperacaoY[contadorBotoes] = 0;

                }
                else {
                    modoOperacaoY[contadorBotoes] = 1;

                }


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

                    if(flag == 1){

                        String strNomeJoystick = nome_add_new_joystick[contadorBotoes].getText().toString();
                        modoOperacaoX[idComponente] = modoOperacaoX[contadorBotoes];
                        modoOperacaoY[idComponente] = modoOperacaoX[contadorBotoes];
                        if(checkBoxX[contadorBotoes].isChecked())
                            checkBoxX[idComponente].setChecked(true);
                        else
                            checkBoxX[idComponente].setChecked(false);

                        if(checkBoxY[contadorBotoes].isChecked())
                            checkBoxY[idComponente].setChecked(true);
                        else
                            checkBoxY[idComponente].setChecked(false);
                        //
                         textViewNomeTela[idComponente].setText(strNomeJoystick);
                        if (checkBoxX[contadorBotoes].isChecked() && checkBoxY[contadorBotoes].isChecked()) {
                            novosJoysticks[idComponente].setButtonDirection (0);
                        }else if(checkBoxX[contadorBotoes].isChecked() && !checkBoxY[contadorBotoes].isChecked()){
                            novosJoysticks[idComponente].setButtonDirection (-1);
                        }else{
                            novosJoysticks[idComponente].setButtonDirection (1);

                        }
                    }else {
                        meuLayout[contadorBotoes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_joystick, null);
                        meuLayout[contadorBotoes].setId(contadorBotoes);

                        areaDrag[contadorBotoes] = (LinearLayout) meuLayout[contadorBotoes].findViewById(R.id.areaDragJoy);


                        ConstraintSet set = new ConstraintSet();
                        novosJoysticks[contadorBotoes] = (JoystickView) meuLayout[contadorBotoes].findViewById(R.id.add_new_joystick);
                        novosJoysticks[contadorBotoes].setId(contadorBotoes);
                        if (checkBoxX[contadorBotoes].isChecked() && checkBoxY[contadorBotoes].isChecked()) {
                            novosJoysticks[contadorBotoes].setButtonDirection (0);
                        }else if(checkBoxX[contadorBotoes].isChecked() && !checkBoxY[contadorBotoes].isChecked()){
                            novosJoysticks[contadorBotoes].setButtonDirection (-1);
                        }else{
                            novosJoysticks[contadorBotoes].setButtonDirection (1);

                        }
                        novosJoysticks[contadorBotoes].setOnMoveListener(new OuvirJoystick( novosJoysticks[contadorBotoes]));
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
                    }

                  /* meuLayout[contadorBotoes].setOnClickListener(v->{

                       if(modoConfigs)
                       {
                           configurarJoystick(v.getId());
                       }

                   });
                   */


                 if(flag == 1){ //modo edicao


                     componentes.get(finalPosicao).setIdComponente(idComponente);
                     componentes.get(finalPosicao).setTipo("joystick");
                     componentes.get(finalPosicao).setNomeComponente(nome_add_new_joystick[contadorBotoes].getText().toString());
                     componentes.get(finalPosicao).setEixoX(checkBoxX[contadorBotoes].isChecked());
                     componentes.get(finalPosicao).setChaveInicioEixoX(ScaracterJoyInicioX[contadorBotoes]);
                     componentes.get(finalPosicao).setChaveFimEixoX(ScaracterJoyFimX[contadorBotoes]);
                     componentes.get(finalPosicao).setChaveFimInverterEixoX(ScaracterJoyFimInverterX[contadorBotoes]);
                     componentes.get(finalPosicao).setIntervaloInicioEixoX(intervaloInicioX[contadorBotoes]);
                     componentes.get(finalPosicao).setIntervaloFimEixoX(intervaloFimX[contadorBotoes]);
                     componentes.get(finalPosicao).setChaveFimInverterEixoY(ScaracterJoyFimInverterY[contadorBotoes]);
                     componentes.get(finalPosicao).setEscopoEixoX(escopoEixoX[contadorBotoes]);
                     componentes.get(finalPosicao).setModoOperacaoEixoX(modoOperacaoX[contadorBotoes]);
                     componentes.get(finalPosicao).setEixoY(checkBoxY[contadorBotoes].isChecked());
                     componentes.get(finalPosicao).setChaveInicioEixoY(ScaracterJoyInicioY[contadorBotoes]);
                     componentes.get(finalPosicao).setChaveFimEixoY(ScaracterJoyFimY[contadorBotoes]);


                     componentes.get(finalPosicao).setIntervaloInicioEixoY(intervaloInicioY[contadorBotoes]);
                     componentes.get(finalPosicao).setIntervaloFimEixoY(intervaloFimY[contadorBotoes]);
                     componentes.get(finalPosicao).setEscopoEixoY(escopoEixoY[contadorBotoes]);
                     componentes.get(finalPosicao).setModoOperacaoEixoY(modoOperacaoY[contadorBotoes]);

                 }else { //modo criacao
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
                 }

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


    public void addNewVelocimetro(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View v = li.inflate(R.layout.setup_new_velocimetro, null);


        builder.setTitle("Adicionar Velocimetro");
        builder.setView(v);
        final AlertDialog alerta_setup_new_velocimetro = builder.create();

        alerta_setup_new_velocimetro.show();


        LinearLayout areaVizVelocimetro = v.findViewById(R.id.are_vizualizacao_velocimetro);
        LinearLayout velocimetro1 = (LinearLayout) getLayoutInflater().inflate(R.layout.velocimetro1, null);
        areaVizVelocimetro.addView(velocimetro1);

        Spinner cbVelocimetros = v.findViewById(R.id.cBVelocimetros);
        final ArrayAdapter adapterVelocimetros = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, velocimetros);
        adapterVelocimetros.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cbVelocimetros.setAdapter(adapterVelocimetros);

        cbVelocimetros.setOnItemSelectedListener(new SeletorVelocimetro(areaVizVelocimetro));

        EditText eTregexInicio = v.findViewById(R.id.eTRegexInicioVelocimetro);
        EditText eTregexFim = v.findViewById(R.id.eTRegexFimVelocimetro);




        Button btn_criar_velocimetro = v.findViewById(R.id.btnCriarNewVelocimetro);
        btn_criar_velocimetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                regexInicio[contadorBotoes] = eTregexInicio.getText().toString();
                regexFim[contadorBotoes] = eTregexFim.getText().toString();


                meuLayout[contadorBotoes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_velocimetro, null);
                 area_velocimetro[contadorBotoes] = meuLayout[contadorBotoes].findViewById(R.id.area_velocimetro);
                if(tipoVelocimetro[contadorBotoes] == 1){
                    LinearLayout velocimetro = (LinearLayout) getLayoutInflater().inflate(R.layout.velocimetro1, null);
                    area_velocimetro[contadorBotoes].addView(velocimetro);

                }
                if(tipoVelocimetro[contadorBotoes] == 2){
                    LinearLayout velocimetro = (LinearLayout) getLayoutInflater().inflate(R.layout.velocimetro2, null);
                    area_velocimetro[contadorBotoes].addView(velocimetro);

                }
                if(tipoVelocimetro[contadorBotoes] == 3){
                    LinearLayout velocimetro = (LinearLayout) getLayoutInflater().inflate(R.layout.velocimetro3, null);
                    area_velocimetro[contadorBotoes].addView(velocimetro);

                }


                meuLayout[contadorBotoes].setId(contadorBotoes);

                areaDrag[contadorBotoes] = (LinearLayout) meuLayout[contadorBotoes].findViewById(R.id.areaDragVelocimetro);
                areaDrag[contadorBotoes].setId(contadorBotoes);


                layoutPrincipal.addView(meuLayout[contadorBotoes]);
                ConstraintSet set = new ConstraintSet();

                set.clone(layoutPrincipal);
                set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, 90);
                // set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, meuLayout[contadorBotoes].getBottom());
                //set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, meuLayout[contadorBotoes].getRight());
                set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, 5);
                set.constrainHeight(meuLayout[contadorBotoes].getId(), meuLayout[contadorBotoes].getHeight());
                set.applyTo(layoutPrincipal);

                meuLayout[contadorBotoes].setOnLongClickListener(new OuvirCliqueLongo());

                layoutPrincipal.setOnDragListener(new OuvirDrag());


                Componente velocimetro = new Componente();
                velocimetro.setTipo("velocimetro");
                velocimetro.setIdComponente(contadorBotoes);
                velocimetro.setChaveInicio(regexInicio[contadorBotoes]);
                velocimetro.setChaveFim(regexFim[contadorBotoes]);
                velocimetro.setTipoBotao(tipoVelocimetro[contadorBotoes]);

                componentes.add(velocimetro);
                contadorBotoes++;
                alerta_setup_new_velocimetro.dismiss();
            }
        });

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
        SeekBarIntervaloInicio[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);


        SeekBarIntervaloInicio[contadorBotoes].setText("0");
        SeekBarIntervaloFim[contadorBotoes] = (EditText) v.findViewById(R.id.etNewSeekBarIntervaloFim);

        SeekBarIntervaloInicio[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        SeekBarIntervaloFim[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);



        builder.setTitle("Adicionar Deslizante");
        builder.setView(v);
        alerta_setup_new_seek_bar = builder.create();
        alerta_setup_new_seek_bar.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        alerta_setup_new_seek_bar.show();


        btnFinaladdnewSeekBar = (Button) v.findViewById(R.id.btnFinalAddNewSeekBar);

        SeekBarChaveInicio[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        SeekBarNome[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        SeekBarChaveFim[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);


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

                                meuLayout[contadorBotoes].setOnDragListener(new OuvirDrag());
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

            case R.id.checkBoxBtntp2:
            {

                checkBoxBtntp2[contadorBotoes].setChecked(true);
                checkBoxBtnSeta2[contadorBotoes].setChecked(false);
                tipoBotao2[contadorBotoes] = 1;


            }break;
            case R.id.checkeckBoxBtnSeta:
            {

                checkBoxBtntp1[contadorBotoes].setChecked(false);
                checkBoxBtnSeta[contadorBotoes].setChecked(true);
                tipoBotao[contadorBotoes] = 2;

            }break;

            case R.id.checkeckBoxBtnSeta2:
            {

                checkBoxBtntp2[contadorBotoes].setChecked(false);
                checkBoxBtnSeta2[contadorBotoes].setChecked(true);
                tipoBotao2[contadorBotoes] = 2;

            }break;


        }

    }

    public void addNewButton() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.setup_make_btn, null);


        caracterEnvio = (EditText) view.findViewById(R.id.eTCaracterNewButton);
        nomeButton = (EditText) view.findViewById(R.id.eTNomeNewButton);

        caracterEnvio.setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        nomeButton.setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);





        builder.setTitle("Adicionar Botão");
        builder.setView(view);
        final AlertDialog alerta_setup_new_button = builder.create();

        alerta_setup_new_button.show();




        Button btnMakeBtnPerson = (Button) view.findViewById(R.id.btnMakeBtnPerson);

         tvCaracterEnvio2[contadorBotoes] = view.findViewById(R.id.tvCaracterEnvio2);
        tvCaracterEnvio2[contadorBotoes].setVisibility(View.INVISIBLE);
        tvCaracterEnvio2[contadorBotoes].setEnabled(false);

        caracterEnvio2 = (EditText) view.findViewById(R.id.eTCaracterNewButton2);
        caracterEnvio2.setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);


        Spinner spinnerModoOperacaoBotao = view.findViewById(R.id.spinnerModoOperacaoBotao);
        final ArrayAdapter adapterModoOperacao = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, modosOperacao);
        adapterModoOperacao.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerModoOperacaoBotao.setAdapter(adapterModoOperacao);

        spinnerModoOperacaoBotao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getItemAtPosition(position).toString()) {
                    case "1 Toque/1 Caracter": {
                       modoOperacaoBotao[contadorBotoes] = 1;
                        tvCaracterEnvio2[contadorBotoes].setVisibility(View.INVISIBLE);
                        tvCaracterEnvio2[contadorBotoes].setEnabled(false);

                        caracterEnvio2.setVisibility(View.INVISIBLE);
                        caracterEnvio2.setEnabled(false);
                    }
                    break;

                    case "2 Toques/2 Caracteres": {
                        modoOperacaoBotao[contadorBotoes] = 2;
                        tvCaracterEnvio2[contadorBotoes].setVisibility(View.VISIBLE);
                        tvCaracterEnvio2[contadorBotoes].setEnabled(true);


                        caracterEnvio2.setVisibility(View.VISIBLE);
                        caracterEnvio2.setEnabled(true);


                    }
                    break;

                    case "Toque Longe/2 Caracteres":{
                        modoOperacaoBotao[contadorBotoes] = 3;
                        tvCaracterEnvio2[contadorBotoes].setVisibility(View.VISIBLE);
                        tvCaracterEnvio2[contadorBotoes].setEnabled(true);

                        caracterEnvio2.setVisibility(View.VISIBLE);
                        caracterEnvio2.setEnabled(true);
                    }break;
                }

                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                alerta_setup_botao_pre_definido.show();



                ImageButton btnSeta = (ImageButton) vBtnPreDefinido.findViewById(R.id.imgBtnSeta);
                ImageButton btnSeta2 = (ImageButton) vBtnPreDefinido.findViewById(R.id.imgBtnSeta2);


                btnPreDefinido = (ImageButton) vBtnPreDefinido.findViewById(R.id.imgBtnPreDefinido);
                btnPreDefinido2 = (ImageButton) vBtnPreDefinido.findViewById(R.id.imgBtnPreDefinido2);


                checkBoxBtnSeta[contadorBotoes] = (CheckBox) vBtnPreDefinido.findViewById(R.id.checkeckBoxBtnSeta);
                checkBoxBtntp1[contadorBotoes] = (CheckBox) vBtnPreDefinido.findViewById(R.id.checkBoxBtntp1);
                checkBoxBtntp1[contadorBotoes].setChecked(true);
                tipoBotao[contadorBotoes] = 5;


                if(modoOperacaoBotao[contadorBotoes] == 2){
                    LinearLayout configs_botao2 = vBtnPreDefinido.findViewById(R.id.configs_botao2);
                    configs_botao2.setVisibility(View.VISIBLE);
                    configs_botao2.setEnabled(true);

                    checkBoxBtnSeta2[contadorBotoes] = (CheckBox) vBtnPreDefinido.findViewById(R.id.checkeckBoxBtnSeta2);
                    checkBoxBtntp2[contadorBotoes] = (CheckBox) vBtnPreDefinido.findViewById(R.id.checkBoxBtntp2);
                    checkBoxBtntp2[contadorBotoes].setChecked(true);

                    Spinner comboDirecaoSeta2 = (Spinner) vBtnPreDefinido.findViewById(R.id.spinnerDirecaoSeta2);

                    comboDirecaoSeta2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (parent.getItemAtPosition(position).toString()) {

                                case "Cima": {
                                    btnSeta2.setRotation(270);
                                    rotacaoBotao2[contadorBotoes] = 270;


                                }
                                break;

                                case "Baixo": {
                                    btnSeta2.setRotation(90);
                                    rotacaoBotao2[contadorBotoes] = 90;

                                }
                                break;

                                case "Esquerda": {
                                    btnSeta2.setRotation(180);
                                    rotacaoBotao2[contadorBotoes] = 180;


                                }
                                break;

                                case "Direita": {

                                    btnSeta2.setRotation(0);
                                    rotacaoBotao2[contadorBotoes] = 0;

                                }
                                break;

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    comboDirecaoSeta2.setAdapter(adapterDirecao);

                    Spinner spinnerTipoBtnPreDefinido2 = (Spinner) vBtnPreDefinido.findViewById(R.id.spinnerTipoBtnPreDefinido2);
                    spinnerTipoBtnPreDefinido2.setAdapter(adapterTipoBtnPreDefinido);
                    spinnerTipoBtnPreDefinido2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            switch (parent.getItemAtPosition(position).toString()) {
                                case "On": {
                                    tipoBotao2[contadorBotoes] = 5;
                                    btnPreDefinido2.setImageResource(R.drawable.btnon);
                                }
                                break;

                                case "Off": {
                                    tipoBotao2[contadorBotoes] = 6;
                                    btnPreDefinido2.setImageResource(R.drawable.btnoff);

                                } break;

                                case "Iniciar":
                                {
                                    tipoBotao2[contadorBotoes] = 7;
                                    btnPreDefinido2.setImageResource(R.drawable.btnstart);


                                }break;


                                case "1": {
                                    corButton2 = Color.RED;

                                }
                                break;

                                case "2": {
                                    corButton2 = GREEN;

                                }
                                break;

                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                }else{
                    LinearLayout configs_botao2 = vBtnPreDefinido.findViewById(R.id.configs_botao2);
                    configs_botao2.setVisibility(View.INVISIBLE);
                    configs_botao2.setEnabled(false);
                }





                Button btnFinalizarAddNewButton = (Button) vBtnPreDefinido.findViewById(R.id.btnFinalizarAddNewButton);

                btnFinalizarAddNewButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SnomeButton = nomeButton.getText().toString();
                        ScaracterEnvio = caracterEnvio.getText().toString();
                        ScaracterEnvio2 = caracterEnvio2.getText().toString();

                        dados[contadorBotoes] = ScaracterEnvio;
                        meuLayout[contadorBotoes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_button, null);
                        meuLayout[contadorBotoes].setId(contadorBotoes);

                        areaDrag[contadorBotoes] = (LinearLayout) meuLayout[contadorBotoes].findViewById(R.id.areaDragBtn);

                        novosBotoes[contadorBotoes] = (Button) meuLayout[contadorBotoes].findViewById(R.id.add_new_button);
                        novosBotoes[contadorBotoes].setId(contadorBotoes);
                        //definindo o tipo do botao1
                        alterarFundoBotao(novosBotoes[contadorBotoes], tipoBotao[contadorBotoes]);




                        Log.i("Tem", "O botao " + contadorBotoes + " recebeu id " + meuLayout[contadorBotoes].getId());

                        ConstraintSet set = new ConstraintSet();

                        novosTextViewCaracter[contadorBotoes] = (EditText) meuLayout[contadorBotoes].findViewById(R.id.caracter_new_button);
                        novosTextViewCaracter[contadorBotoes].setText(ScaracterEnvio);
                        novosTextViewCaracter[contadorBotoes].setVisibility(View.INVISIBLE);
                        novosTextViewCaracter[contadorBotoes].setEnabled(false);

                        novosTextViewCaracter[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);


                        novosTextViewCaracter2[contadorBotoes] = (EditText) meuLayout[contadorBotoes].findViewById(R.id.caracter_new_button2);
                        novosTextViewCaracter2[contadorBotoes].setText(ScaracterEnvio2);
                        novosTextViewCaracter2[contadorBotoes].setVisibility(View.INVISIBLE);
                        novosTextViewCaracter2[contadorBotoes].setEnabled(false);

                        novosTextViewCaracter2[contadorBotoes].setImeOptions(EditorInfo.IME_ACTION_DONE | EditorInfo.IME_FLAG_NO_EXTRACT_UI);


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
                                            if(modoOperacaoBotao[view.getId()] ==1 ){
                                                controle.setDados( novosTextViewCaracter[view.getId()].getText().toString());

                                            }else if(modoOperacaoBotao[view.getId()] == 2){
                                                contadorBotaoPressionado++;
                                                Toast.makeText(getBaseContext(), "Botao pressionado: " + contadorBotaoPressionado + pressionado, Toast.LENGTH_SHORT).show();
                                                if(pressionado){
                                                    controle.setDados( novosTextViewCaracter2[view.getId()].getText().toString());
                                                    pressionado = false;
                                                    Toast.makeText(getBaseContext(), "Tipo botao: " + tipoBotao[view.getId()], Toast.LENGTH_SHORT).show();

                                                    alterarFundoBotao(view, tipoBotao[view.getId()]);


                                                }else{
                                                    controle.setDados( novosTextViewCaracter[view.getId()].getText().toString());
                                                    Toast.makeText(getBaseContext(), "Tipo botao: " + tipoBotao2[view.getId()], Toast.LENGTH_SHORT).show();

                                                    alterarFundoBotao(view,tipoBotao2[view.getId()] );
                                                    pressionado = true;

                                                }


                                            }else if(modoOperacaoBotao[view.getId()] ==3){
                                                controle.setDados( novosTextViewCaracter[view.getId()].getText().toString());

                                            }

                                            controle.execute();
                                        }
                                        break;
                                    case MotionEvent.ACTION_UP:
                                        if(ativo) {
                                            if(modoOperacaoBotao[view.getId()] ==1 ){

                                            }else if(modoOperacaoBotao[view.getId()] ==2){

                                            }else if(modoOperacaoBotao[view.getId()] ==3){
                                                connect.write(novosTextViewCaracter2[view.getId()].getText().toString().getBytes());

                                            }

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
                        botao.setCaracterEnvio2(ScaracterEnvio2);

                        botao.setTipo("botao");
                        botao.setIdComponente(contadorBotoes);
                        botao.setTipoBotao(tipoBotao[contadorBotoes]);
                        botao.setRotacaoBotao(rotacaoBotao[contadorBotoes]);
                        botao.setModoOperacaoBotao(modoOperacaoBotao[contadorBotoes]);
                        botao.setTipoBotao2(tipoBotao2[contadorBotoes]);
                        botao.setRotacaoBotao2(rotacaoBotao2[contadorBotoes]);
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


     public void alterarFundoBotao(View view, int tipoBotao){
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
             view.setRotation(rotacaoBotao[contadorBotoes]);
         }
     }


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

                             int x = 0, y = 0;

                            set.clone(container);

                           try{
                               Toast.makeText(getBaseContext(), " " + view.getTag(), Toast.LENGTH_SHORT).show();

                               if(view.getTag().toString().equals("deslizante") && view.getTag() != null) {

                                y = touchPosition.y - (view.getWidth() / 3);
                                x = (int) dragEvent.getX() - (view.getHeight());
                            }else if(view.getTag().toString().equals("botao") && view.getTag() != null ){
                                y = (int) dragEvent.getY() - (view.getWidth() /2);
                                x = (int) dragEvent.getX() - (view.getHeight() /2);
                            }
                            else if(view.getTag().toString().equals("joystick") && view.getTag() != null ){
                                y = touchPosition.y - (view.getWidth() /2);
                                x = touchPosition.x - (view.getHeight() /2);
                            }
                               else if(view.getTag().toString().equals("info") && view.getTag() != null ){
                                   y = touchPosition.y - (view.getWidth() / 4);
                                   x = (int) dragEvent.getX() - (view.getHeight());
                               }
                               else if(view.getTag().toString().equals("velocimetro") && view.getTag() != null ){
                                   y = touchPosition.y - (view.getWidth() /2);
                                   x = touchPosition.x - (view.getHeight() /2);
                               }
                            }catch (Exception f){
                                   y = (int) dragEvent.getY() - (view.getWidth() /2);
                                   x = (int) dragEvent.getX() - (view.getHeight() /2);
                               }







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
                    //configurarJoystick(v.getId());
                    addNewJoy(1, v.getId());
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



            if(modoConfigs)
            {
            }
            else if(modoPosicionar)
            {

            }
            else if(modoTestar){
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
                    controleX.setDados(Dados, id);
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
                    controleY.setDados(Dados, id);
                    controleY.execute();
                }

            }

        }
    }



    /*
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
*/

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

    public void configurarDelay(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.setup_delay, null);


        EditText eTDelay = (EditText) view.findViewById(R.id.eTDelay);
        Button btn_salvar = (Button) view.findViewById(R.id.btnSalvarDelay);



        builder.setTitle("Configurar Delay");
        builder.setView(view);
        final AlertDialog alerta_setup_delay = builder.create();

        alerta_setup_delay.show();


        btn_salvar.setOnClickListener(v -> {
            String Sdelay = eTDelay.getText().toString();

            try {
                int intDelay = Integer.parseInt(Sdelay);
                delay = intDelay;

                alerta_setup_delay.dismiss();
            }catch (Exception t){
                Toast.makeText(getBaseContext(), "Valor de Delay Incorreto", Toast.LENGTH_SHORT).show();
            }


        });

    }


    public Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            Bundle bundle = msg.getData();
            byte[] data = bundle.getByteArray("data");
            String dataString= new String(data);
            //Toast.makeText(getBaseContext(), "texto: " + dataString, Toast.LENGTH_SHORT).show();

            for(int  i = 0; i < contadorBotoes; i++){
                if(meuLayout[i] != null) {
                    if (meuLayout[i].getTag().toString().equals("info") && meuLayout[i].getTag() != null) {

                        TratarDados tratarDados = new TratarDados(dataString);
                        String regex = tratarDados.tratar(regexInicio[i], regexFim[i]);
                       // Toast.makeText(getBaseContext(), "texto: " + regex, Toast.LENGTH_SHORT).show();
                        tvRegexInfo[i].setText(regex);

                    }else if(meuLayout[i].getTag().toString().equals("velocimetro") && meuLayout[i].getTag() != null){
                        TratarDados tratarDados = new TratarDados(dataString);
                        String regex = tratarDados.tratar(regexInicio[i], regexFim[i]);
                        // Toast.makeText(getBaseContext(), "texto: " + regex, Toast.LENGTH_SHORT).show();
                        SpeedView speedometer = null;

                        if(tipoVelocimetro[i] == 1){
                            speedometer = findViewById(R.id.speedView);
                        }
                        if(tipoVelocimetro[i] == 2){
                            speedometer = findViewById(R.id.awesomeSpeedometer);

                        }
                        if(tipoVelocimetro[i] == 3){
                            speedometer = findViewById(R.id.deluxeSpeedView);

                        }


                              speedometer.speedTo(Integer.parseInt(regex));


                    }
                }
            }



        }
    };







    public class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        private ImageView view;

        public MyGestureDetector(ImageView view){
            this.view = view;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            view.post(new FlingRunnable((velocityX + velocityY), view));
            return true;
        }
    }

    /**
     * A {@link Runnable} for animating the the dialer's fling.
     */
    public class FlingRunnable implements Runnable {

        private float velocity;
        private ImageView view;

        public FlingRunnable(float velocity, ImageView view) {
            this.velocity = velocity;
            this.view = view;
        }

        @Override
        public void run() {
            if (Math.abs(velocity) > 5) {
                rotacionarDialer(velocity / 75 , view);
                velocity /= 1.0666F;

                // post this instance again
                view.post(this);
            }
        }
    }

    public class SeletorVelocimetro implements AdapterView.OnItemSelectedListener{

        private LinearLayout areaPreVizualizacao;
        public SeletorVelocimetro(LinearLayout areaPreVizualizacao ){
            this.areaPreVizualizacao  = areaPreVizualizacao;

        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            areaPreVizualizacao.removeAllViews();


            switch (parent.getItemAtPosition(position).toString()) {
                case "Opção 1": {
                    LinearLayout velocimetro = (LinearLayout) getLayoutInflater().inflate(R.layout.velocimetro1, null);
                    tipoVelocimetro[contadorBotoes] = 1;
                    areaPreVizualizacao.addView(velocimetro);
                }
                break;

                case "Opção 2": {
                    LinearLayout velocimetro = (LinearLayout) getLayoutInflater().inflate(R.layout.velocimetro2, null);
                    tipoVelocimetro[contadorBotoes] = 2;

                    areaPreVizualizacao.addView(velocimetro);

                }
                break;

                case "Opção 3":{
                    LinearLayout velocimetro = (LinearLayout) getLayoutInflater().inflate(R.layout.velocimetro3, null);
                    tipoVelocimetro[contadorBotoes] = 3;

                    areaPreVizualizacao.addView(velocimetro);

                }break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }








}

