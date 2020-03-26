package com.example.titaniwmbluetoothcontrol;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class TelaNovoLayoutsPersonalizados extends AppCompatActivity implements JoyStickViewOfficial.JoystickListener, NavigationView.OnNavigationItemSelectedListener{
    ConstraintLayout meuLayout[] = new ConstraintLayout[10];
    ConstraintLayout layoutPrincipal;
    ConstraintLayout.LayoutParams meuParametros[] = new ConstraintLayout.LayoutParams[10];
    String SnomeButton;

    ArrayList<String> pinsComponentes = new ArrayList<>();

    private LinearLayout exlcuir;
    int corButton;
    int formatoButton;
    String dados[] = new String[10];
    int corText;
    boolean sinal[] = new boolean[10];
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

    private CheckBox checkBoxDividirEixoXNovaTelaPersonalizada[] = new CheckBox[10];
    private CheckBox checkBoxDividirEixoYNovaTelaPersonalizada[] = new CheckBox[10];

    boolean ativo = false;

    private CheckBox checkBoxInverterEixoXNovaTelaPersonalizada[] = new CheckBox[10];
    private CheckBox checkBoxInverterEixoYNovaTelaPersonalizada[] = new CheckBox[10];



//variaveis de escopo para novos joysticks
    //#######################################

    private EditText eTIntervaloInicioEixoXNovaTelaPersonalizada[] = new EditText[10];
    private EditText eTIntervaloFimEixoXNovaTelaPersonalizada[] = new EditText[10];

    private int escopoEixoX[] = new int[10];


    private EditText eTIntervaloInicioEixoYNovaTelaPersonalizada[] = new EditText[10];
    private EditText eTIntervaloFimEixoYNovaTelaPersonalizada[] = new EditText[10];

    private int escopoEixoY[] = new int[10];


    private int modoOperacaoX[] = new int[10];
    private int modoOperacaoY[] = new int[10];

    int intervaloInicioX[] = new int[10];
    int intervaloFimX[] = new int [10];

    int intervaloInicioY []= new int [10];
    int intervaloFimY []= new int [10];

    //#######################################33

    CheckBox checkBoxBtntp1[] = new CheckBox[10];
    CheckBox checkBoxBtnSeta[] = new CheckBox[10];


    Button novosBotoes[] = new Button[10];
    int tipoBotao[] = new int[10];
    int rotacaoBotao[] = new int[10];

    TextView novosTextViewCaracter[] = new TextView[10];
    int contadorBotoes = 1;

    JoyStickViewOfficial novosJoysticks[] = new JoyStickViewOfficial[10];
    EditText novosTextViewCaracterJoyIcicioX[] = new EditText[10];
    EditText novosTextViewCaracterJoyFimX[] = new EditText[10];
    EditText novosTextViewCaracterJoyIcicioY[] = new EditText[10];
    EditText novosTextViewCaracterJoyFimY[] = new EditText[10];
    EditText nome_add_new_joystick[] = new EditText[10];
    TextView  textViewNomeTela[] =  new TextView[10];
    EditText nomeButton;
    EditText caracterEnvio;

    CheckBox checkBoxX[] = new CheckBox[10];
    CheckBox checkBoxY[] = new CheckBox[10];

    CheckBox checkBoxQuadrado;
    CheckBox checkBoxCircular;

    Button btnProximo;

    Button btnFinaladdnewJoy;


    AlertDialog alerta_setup_new_button;
    AlertDialog alert_formato_botao;
    AlertDialog alerta_setup_new_seek_bar;
    AlertDialog alerta_setup_botao_pre_definido;


    String ScaracterJoyInicioX[] = new String[10];
    String ScaracterJoyFimX[] = new String[10];


    String ScaracterJoyInicioY[] = new String[10];
    String ScaracterJoyFimY[] = new String[10];
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //Variveis new Seek Bar

    private Button btnFinaladdnewSeekBar;
    private EditText SeekBarNome[] = new EditText[10];
    private EditText SeekBarChaveInicio []= new EditText[10];
    private EditText SeekBarChaveFim []= new EditText[10];
    private EditText SeekBarEscopo[]= new EditText[10];

    private  SeekBar novosSeekBars[] = new SeekBar[10];

    private String ScaracterSeekBarInicio[] = new String[10];
    private String ScaracterSeekBarFim[] = new String[10];

    private EditText SeekBarIntervaloInicio[] = new EditText[10];
    private EditText SeekBarIntervaloFim[] = new EditText[10];

    private String SSeekBarNome[] = new String[10];
    private String SSeekBarChaveInicio[] = new String[10];
    private String SSeekBarChaveFim[] = new String[10];
    private String SSeekBarEscopo[] = new String[10];
    private String SSeekBarIntervaloInicio[] = new String[10];
    private String SSeekBarIntervaloFim[] =new String[10];
    private TextView tvNomeSeekBar[] =new TextView[10];
    private int IntSeekBarIntervaloInicio[] = new int[10];
    private int IntSeekBarIntervaloFim[] = new int[10];

    private ArrayList<Componente> componentes = new ArrayList<>();

    private Toolbar mTopoToolbar;

    //

    public static final int background_img = 1234;

    private static final String[] cores = new String[]{"Azul", "Amarelo", "Vermelho", "Verde"};
    private static final String[] formatos = new String[]{"Circular", "Quadrado", "Retangulo"};
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        /*
        findViewById(R.id.btnSalvarTelaPersonalizada).setOnClickListener(visual->
        {
            final AlertDialog alert;
            final Dialog dialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);


            LayoutInflater li = getLayoutInflater();
            View vi = li.inflate(R.layout.setup_new_layout_personalizado, null);


            builder.setTitle("Adicionar Layout");
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
                                        + checkY + ";"
                                        + componentes.get(i).getChaveInicioEixoY() + ";"
                                        + componentes.get(i).getChaveFimEixoY() + ";"

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

                            sp1.setDados("Contador_Layouts", 0, "contador_lay", contador);

                        }

                        alert.dismiss();
                        finish();
                    }


                }.start();



            });





        });
        */


        exlcuir = (LinearLayout) findViewById(R.id.layoutExcluirComponente);
        exlcuir.setOnDragListener(new OuvirDrag());
        // meuLayout[contadorBotoes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_joystick, null);

        // btnOkVisualizar = (Button) findViewById(R.id.btnOkVisualizar);
        // btnVisualizar = (Button) findViewById(R.id.btnVisualizar);
        // btnVisualizar.setText("Visualizar");

        /*
        btnOkVisualizar.setVisibility(View.INVISIBLE);

        btnVisualizar.setOnClickListener(v->
        {
            btnVisualizar.setVisibility(View.INVISIBLE);
            btnOkVisualizar.setVisibility(View.VISIBLE);
            for(int i = 1; i < contadorBotoes; i++)
                {
                    if(novosTextViewCaracter[i] != null)
                    {
                        novosTextViewCaracter[i].setVisibility(View.INVISIBLE);
                    }
                }



        });

        btnOkVisualizar.setOnClickListener(v->
        {
            btnVisualizar.setVisibility(View.VISIBLE);
            btnVisualizar.setText("Visualizar");
            btnOkVisualizar.setVisibility(View.INVISIBLE);
            for(int i = 1; i < contadorBotoes; i++)
            {
                if(novosTextViewCaracter[i] != null)
                {
                    novosTextViewCaracter[i].setVisibility(View.VISIBLE);
                }
            }
        });

*/
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
                            Toast.makeText(getBaseContext(), "Segure um componente e arraste-o para posiciona-lo", Toast.LENGTH_LONG).show();


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


                        }

                    } break;

                    case R.id.action_teste_componentes:
                    {
                        modoConfigs = false;
                        modoPosicionar = false;
                        modoTestar = true;
                        modoAtual.setText("Testar");
                        Toast.makeText(getBaseContext(), "Toque em um Componente para testa-lo", Toast.LENGTH_LONG).show();


                    } break;

                }

                return true;
            }
        });
        mTopoToolbar.inflateMenu(R.menu.menu_topo_toolbar_nova_tela_personalizada);

        modoAtual = (TextView) findViewById(R.id.tvModoAtual);




    }

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


        final Dialog dialog;
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
        final Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View vi = li.inflate(R.layout.setup_new_layout_personalizado, null);


        builder.setTitle("Adicionar Layout");
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
                                    + checkY + ";"
                                    + componentes.get(i).getChaveInicioEixoY() + ";"
                                    + componentes.get(i).getChaveFimEixoY() + ";"

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

                        sp1.setDados("Contador_Layouts", 0, "contador_lay", contador);

                    }

                    alert.dismiss();
                    finish();
                }


            }.start();



        });




    }








    public void addNewJoy() {
        final AlertDialog alert;
        final Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View v = li.inflate(R.layout.setup_new_joystick, null);


        builder.setTitle("Adicionar Joystick");
        builder.setView(v);
        alert = builder.create();
        alert.show();
        novosTextViewCaracterJoyFimX[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_fim_joyX);
        novosTextViewCaracterJoyIcicioX[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_inicio_joyX);
        novosTextViewCaracterJoyFimX[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyIcicioX[contadorBotoes].setId(contadorBotoes);

        novosTextViewCaracterJoyFimY[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_fim_joyY);
        novosTextViewCaracterJoyIcicioY[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_inicio_joyY);
        novosTextViewCaracterJoyFimY[contadorBotoes].setId(contadorBotoes);
        novosTextViewCaracterJoyIcicioY[contadorBotoes].setId(contadorBotoes);

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

                ScaracterJoyInicioY[contadorBotoes] = novosTextViewCaracterJoyIcicioY[contadorBotoes].getText().toString();
                ScaracterJoyFimY[contadorBotoes] = novosTextViewCaracterJoyFimY[contadorBotoes].getText().toString();


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
                    if (ScaracterJoyInicioX[contadorBotoes].length() != 1 || ScaracterJoyFimX[contadorBotoes].length() != 1) {
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

                if(checkBoxY[contadorBotoes].isChecked()) {
                    if (ScaracterJoyInicioY[contadorBotoes].length() != 1 || ScaracterJoyFimY[contadorBotoes].length() != 1) {
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

                    meuLayout[contadorBotoes].setOnLongClickListener(new OuvirCliqueLongo());

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
                    joystick.setIntervaloInicioEixoX(intervaloInicioX[contadorBotoes]);
                    joystick.setIntervaloFimEixoX(intervaloFimX[contadorBotoes]);
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
        final Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View v = li.inflate(R.layout.setup_new_joystick, null);


        builder.setTitle("Configurar Joystick");
        builder.setView(v);
        alert = builder.create();
        alert.show();
        novosTextViewCaracterJoyFimX[contadorBotoes] = (EditText) v.findViewById(R.id.caracter_envio_fim_joyX);
        novosTextViewCaracterJoyFimX[contadorBotoes].setText(componentes.get(posicao).getChaveFimEixoX());

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

                ScaracterJoyInicioY[contadorBotoes] = novosTextViewCaracterJoyIcicioY[contadorBotoes].getText().toString();
                ScaracterJoyFimY[contadorBotoes] = novosTextViewCaracterJoyFimY[contadorBotoes].getText().toString();


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
                    if (ScaracterJoyInicioX[contadorBotoes].length() != 1 || ScaracterJoyFimX[contadorBotoes].length() != 1) {
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

                if(checkBoxY[contadorBotoes].isChecked()) {
                    if (ScaracterJoyInicioY[contadorBotoes].length() != 1 || ScaracterJoyFimY[contadorBotoes].length() != 1) {
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
                    escopoEixoX[finalIdComponente] = escopoEixoX[contadorBotoes];



                    checkBoxY[finalIdComponente].setChecked(checkBoxY[contadorBotoes].isChecked());
                    modoOperacaoY[finalIdComponente] = modoOperacaoY[contadorBotoes];
                    ScaracterJoyInicioY[finalIdComponente] = ScaracterJoyInicioY[contadorBotoes];
                    ScaracterJoyFimY[finalIdComponente] = ScaracterJoyFimY[contadorBotoes];
                    escopoEixoY[finalIdComponente] = escopoEixoY[contadorBotoes];






                    componentes.remove(finalPosicao);

                    Componente joystick = new Componente();
                    joystick.setIdComponente(finalIdComponente);
                    joystick.setTipo("joystick");
                    joystick.setNomeComponente(nome_add_new_joystick[contadorBotoes].getText().toString());
                    joystick.setEixoX(checkBoxX[contadorBotoes].isChecked());
                    joystick.setChaveInicioEixoX(ScaracterJoyInicioX[contadorBotoes]);
                    joystick.setChaveFimEixoX(ScaracterJoyFimX[contadorBotoes]);
                    joystick.setIntervaloInicioEixoX(intervaloInicioX[contadorBotoes]);
                    joystick.setIntervaloFimEixoX(intervaloFimX[contadorBotoes]);
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

            }break;
            case R.id.checkBoxInverterEixoXNovaTelaPersonalizada:
            {
                checkBoxInverterEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(true);

                checkBoxDividirEixoXNovaTelaPersonalizada[contadorBotoes].setChecked(false);

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

            }break;
            case R.id.checkBoxInverterEixoYNovaTelaPersonalizada:
            {
                checkBoxInverterEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(true);

                checkBoxDividirEixoYNovaTelaPersonalizada[contadorBotoes].setChecked(false);

            }break;

        }

    }



    public void addNewSeekBar()
    {
        final Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View v = li.inflate(R.layout.setup_new_seek_bar, null);

        SeekBarNome[contadorBotoes] = (EditText) v.findViewById(R.id.etSetupNewSeekBarNome);
        SeekBarChaveInicio[contadorBotoes] = (EditText) v.findViewById(R.id.etSetupNewSeekBarChaveInicio);;
        SeekBarChaveFim[contadorBotoes] = (EditText) v.findViewById(R.id.etSetupNewSeekBarChaveFim);;
        SeekBarIntervaloInicio[contadorBotoes] = (EditText) v.findViewById(R.id.etNewSeekBarIntervaloInicio);
        SeekBarIntervaloFim[contadorBotoes] = (EditText) v.findViewById(R.id.etNewSeekBarIntervaloFim);


        builder.setTitle("Adicionar Deslizante");
        builder.setView(v);
        alerta_setup_new_seek_bar = builder.create();
        alerta_setup_new_seek_bar.show();

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
                                set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.TOP, layoutPrincipal.getId(), ConstraintSet.TOP, 0);
                                // set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, meuLayout[contadorBotoes].getBottom());
                                //set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, meuLayout[contadorBotoes].getRight());
                                set.connect(meuLayout[contadorBotoes].getId(), ConstraintSet.LEFT, layoutPrincipal.getId(), ConstraintSet.LEFT, 0);
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

        final Dialog dialog;
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
        alerta_setup_new_seek_bar.show();

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
        final Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.setup_new_button, null);

        caracterEnvio = (EditText) view.findViewById(R.id.eTCaracterNewButton);
        nomeButton = (EditText) view.findViewById(R.id.eTNomeNewButton);


        comboCorBotao = (Spinner) view.findViewById(R.id.comboCorBotao);
        comboCorTexto = (Spinner) view.findViewById(R.id.comboCorTexto);
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, cores);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        builder.setTitle("Adicionar Botão");
        builder.setView(view);
        alerta_setup_new_button = builder.create();
        alerta_setup_new_button.show();


        Button btnMakeBtnPerson = (Button) view.findViewById(R.id.btnMakeBtnPerson);
        btnMakeBtnPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogBtnPerson;
                AlertDialog.Builder builderPerson = new AlertDialog.Builder(getBaseContext());


                LayoutInflater liBtnPerson = getLayoutInflater();
                View vBtnPerson = li.inflate(R.layout.setup_new_button_personalizado, null);

                Spinner comboFormatoBtn = (Spinner) vBtnPerson.findViewById(R.id.comboFormatoBotao);
                final ArrayAdapter adapterComboFormato = new ArrayAdapter<String>(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, formatos);
                adapterComboFormato.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                Button btnPerson = (Button) vBtnPerson.findViewById(R.id.btnPersonalizado);

                builder.setTitle("Botão Personalizado");
                builder.setView(vBtnPerson);
                AlertDialog alerta_setup_new_button_person = builder.create();
                alerta_setup_new_button_person.show();

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
                                            Log.i("Tem", "E: " + dados[minhaPosicao]);
                                            controle.setDados(dados[minhaPosicao]);
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

                        botao.setCor(corButton);
                        botao.setFormato(formatoButton);

                        componentes.add(botao);

                        contadorBotoes++;
                        alerta_setup_new_button_person.dismiss();

                        // alerta_setup_botao_pre_definido.dismiss();

                        alerta_setup_new_button.dismiss();
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


            }
        });

        comboCorTexto.setAdapter(adapter);
        comboCorBotao.setAdapter(adapter);
//    private static final String[] cores = new String[]{"Azul", "Amarelo", "Vermelho", "Verde"};
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
                        corButton = Color.GREEN;

                    }
                    break;

                }


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
                        corText = Color.GREEN;

                    }
                    break;

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
                final Dialog dialogBtnPreDefinido;
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
                                corButton = Color.GREEN;

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
                btnPreDefinido = (ImageButton) vBtnPreDefinido.findViewById(R.id.imgBtnPreDefinido);

                checkBoxBtnSeta[contadorBotoes] = (CheckBox) vBtnPreDefinido.findViewById(R.id.checkeckBoxBtnSeta);
                checkBoxBtntp1[contadorBotoes] = (CheckBox) vBtnPreDefinido.findViewById(R.id.checkBoxBtntp1);

                checkBoxBtntp1[contadorBotoes].setChecked(true);
                tipoBotao[contadorBotoes] = 5;
                /*

                checkBoxBtntp1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        checkBoxBtnSeta.setChecked(false);
                        tipoBotao[contadorBotoes] = 1;

                    }
                });

                checkBoxBtnSeta.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        checkBoxBtntp1.setChecked(false);
                        tipoBotao[contadorBotoes] = 2;

                    }
                });
*/
                Button btnFinalizarAddNewButton = (Button) vBtnPreDefinido.findViewById(R.id.btnFinalizarAddNewButton);

                btnFinalizarAddNewButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SnomeButton = nomeButton.getText().toString();
                        ScaracterEnvio = caracterEnvio.getText().toString();
                        dados[contadorBotoes] = ScaracterEnvio;
                        meuLayout[contadorBotoes] = (ConstraintLayout) getLayoutInflater().inflate(R.layout.new_button, null);
                        meuLayout[contadorBotoes].setId(contadorBotoes);


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

                        novosTextViewCaracter[contadorBotoes] = (TextView) meuLayout[contadorBotoes].findViewById(R.id.caracter_new_button);
                        novosTextViewCaracter[contadorBotoes].setText(ScaracterEnvio);

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
                                            Log.i("Tem", "E: " + dados[minhaPosicao]);
                                            controle.setDados(dados[minhaPosicao]);
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
                    Log.i("Posicao", "X: " + dragEvent.getX() + "Y: " + dragEvent.getY());
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
                        Point touchPosition = getTouchPositionFromDragEvent(v, dragEvent);

                        Log.i("Olha", "touch Y : " + touchPosition.y + " X: " + touchPosition.x);
                        Log.i("Olha", "dragevent Y : " + dragEvent.getY() + " X: " + dragEvent.getX());

                        ConstraintSet set = new ConstraintSet();
                        ViewGroup dono = (ViewGroup) view.getParent();
                        dono.removeView(view);
                        ConstraintLayout container = (ConstraintLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);

                        set.clone(container);

                        int y = touchPosition.y - 160;
                        int x = touchPosition.x - 100;

                        set.connect(view.getId(), ConstraintSet.TOP, container.getId(), ConstraintSet.TOP, y);
                        set.connect(view.getId(), ConstraintSet.LEFT, container.getId(), ConstraintSet.LEFT, (int) x);
                        set.constrainHeight(view.getId(), view.getHeight());
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
            if (checkBoxX[id].isChecked()) {
                int x = 0;
                String dados;
                if (modoOperacaoX[id] == 0) {
                    xPercent = xPercent * escopoEixoX[id] / 2;
                    x = (int) xPercent + escopoEixoX[id] / 2;
                    dados = Integer.toString(x);
                } else {
                    xPercent = xPercent * escopoEixoX[id];
                    x = (int) xPercent;
                    dados = Integer.toString(x);
                }
                //  Log.i("extras", "X percent: " + xPercent);
                ControleDirecaoX controleX = new ControleDirecaoX();
                String Dados = ScaracterJoyInicioX[id].concat(dados);
                controleX.setDados(Dados, id);
                controleX.execute();
            }
            if (checkBoxY[id].isChecked()) {
                int y = 0;
                String dados;
                if (modoOperacaoY[id] == 0) {
                    yPercent = yPercent * escopoEixoY[id] / 2;
                    y = (int) yPercent + escopoEixoY[id] / 2;
                    dados = Integer.toString(y);
                } else {
                    Log.i("Valores", "valor antes de aplica: " + yPercent);
                    yPercent = yPercent * escopoEixoY[id];
                    //y = (int) yPercent + escopoEixoY[id] ;
                    y = (int) (yPercent);
                    dados = Integer.toString(y);

                    Log.i("Valores", "porcetagem: " + yPercent + "dado: " + dados);
                }

                //  Log.i("extras", "X percent: " + xPercent);
                ControleDirecaoY controleY = new ControleDirecaoY();
                String Dados = ScaracterJoyInicioY[id].concat(dados);
                controleY.setDados(Dados, id);
                controleY.execute();
            }
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
            Log.i("Verificar", "dados antes de enviar e: " + Dados.concat(ScaracterJoyFimX[ID]));
            String dados = Dados.concat(ScaracterJoyFimX[ID]);
            if(ativo)
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
        int ID;

        public void setDados(String dados, int id) {
            this.Dados = dados;
            this.ID = id;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            Log.i("Verificar", "dados antes de enviar e: " + Dados.concat(ScaracterJoyFimY[ID]));
            String dados = Dados.concat(ScaracterJoyFimY[ID]);
            if(ativo)
                connect.write(dados.getBytes());


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
            if(ativo)
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
                if(ativo)
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