package com.example.titaniwmbluetoothcontrol;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static com.example.titaniwmbluetoothcontrol.R.id.btnExcluirLayout;
import static com.example.titaniwmbluetoothcontrol.R.id.iVLayoutPersonalizado;

public class TelaEscolhaLayoutsPersonalizados extends AppCompatActivity {

    ArrayList<Telas> telas = new ArrayList<>();



    RecyclerView recyclerView;
    ItemAdapterLayoutsPersonalizados itemAdapterLayoutsPersonalizados;
    CheckBox chkBoxLandscape, chkBoxPortrait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decor = getWindow().getDecorView();

        if(Build.VERSION.SDK_INT < 16)
        {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        }else{
            decor.setSystemUiVisibility(SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN );
        }

        setContentView(R.layout.activity_tela_escolha_layouts_personalizados);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEscolhaLayoutsPersonalizados);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        itemAdapterLayoutsPersonalizados = new ItemAdapterLayoutsPersonalizados(telas, getBaseContext());

        recyclerView.setAdapter(itemAdapterLayoutsPersonalizados);
       recyclerView.addItemDecoration(
               new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        itemAdapterLayoutsPersonalizados.setOnItemClickListener(new ItemAdapterLayoutsPersonalizados.ItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                /*

                   */

                if(v.getId() == btnExcluirLayout){
                   //   Toast.makeText(getBaseContext(), "exlucir layout " + telas.get(position).getNomeArquivo(), Toast.LENGTH_SHORT).show();


                    ManipularArquivos mp1 = new ManipularArquivos(TelaEscolhaLayoutsPersonalizados.this);
                   if( mp1.excluirArquivo("LayoutsPersonalizados", telas.get(position).getNomeArquivo()) == true)
                   {
                       Toast.makeText(getBaseContext(), "Layout Excluido: " + telas.get(position).getNomeArquivo(), Toast.LENGTH_SHORT).show();


                       telas.remove(telas.get(position));
                       itemAdapterLayoutsPersonalizados.notifyItemRemoved(itemAdapterLayoutsPersonalizados.getItemCount());
                       itemAdapterLayoutsPersonalizados.notifyDataSetChanged();
                   }
                   else
                   {
                       Toast.makeText(getBaseContext(), "Erro ao Excluir" + telas.get(position).getNomeArquivo(), Toast.LENGTH_SHORT).show();


                   }



                }
                else if(v.getId() == iVLayoutPersonalizado) {
                    Intent telaPersonalizada = new Intent(getBaseContext(), TelaPersonalizada.class);
                    Bundle parametros = new Bundle();
                    parametros.putString("arquivo", telas.get(position).getNomeArquivo());
                    telaPersonalizada.putExtras(parametros);
                    startActivity(telaPersonalizada);                }

            }




        });
        findViewById(R.id.btnNovaTelaPersonalizada).setOnClickListener(v->{

            final AlertDialog alert;
            final Dialog dialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);


            LayoutInflater li = getLayoutInflater();
            View vi = li.inflate(R.layout.setup_new_orientacao__novo_layout_personalizado, null);


            builder.setTitle("Escolher Orientação");
            builder.setView(vi);
            alert = builder.create();
            alert.show();

            chkBoxLandscape =   vi.findViewById(R.id.chkBoxLandscape);
             chkBoxPortrait = vi.findViewById(R.id.chkBoxPortrait);


            chkBoxPortrait.setChecked(true);
            chkBoxLandscape.setChecked(false);

            Button btnCriarNovaTelaPersonalizada = vi.findViewById(R.id.btnCriarNovaTelaPersonalizada);

            btnCriarNovaTelaPersonalizada.setOnClickListener(v1 -> {
                Bundle bundle = new Bundle();
                if(chkBoxPortrait.isChecked())
                 bundle.putInt("orientacao", 0);
                else
                    bundle.putInt("orientacao", 1);

                Intent novatela = new Intent(this, TelaNovoLayoutsPersonalizados.class);
                novatela.putExtras(bundle);
                startActivity(novatela);

                alert.dismiss();
            });



       });



    }


    public void onResume()
    {
        super.onResume();

        procurarTelas();

    }


    public void procurarTelas()
    {
        telas.clear();
        itemAdapterLayoutsPersonalizados.notifyDataSetChanged();
        ManipularArquivos mp1 = new ManipularArquivos(this);

        File[] arquivosNaPasta = mp1.pegarArquivos("LayoutsPersonalizados");
        if(arquivosNaPasta == null)
        {

        }
        else {

            for (int i = 0; i < arquivosNaPasta.length; i++) {
                String arquivo = mp1.ler("LayoutsPersonalizados", arquivosNaPasta[i].getName());
              if(arquivo != null) {
                  //Toast.makeText(this, "texto desse arquivo: "+ arquivo, Toast.LENGTH_SHORT).show();
                  String[] linhas = arquivo.split("\n");
                 for(int j = 0; j < linhas.length; j++)
                  {

                  }


                  try {
                      Telas tela = new Telas();
                      tela.setNome(linhas[0]);
                      tela.setNomeArquivo(arquivosNaPasta[i].getName());
                      tela.setImg(linhas[linhas.length -1]);

                      telas.add(tela);
                      itemAdapterLayoutsPersonalizados.notifyItemInserted(itemAdapterLayoutsPersonalizados.getItemCount());
                  } catch (Exception e) {
                  }
              }else{

              }


            }

        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus)
            getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

    }

    public void checkOrientacao( View view)
    {

        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
            case R.id.chkBoxLandscape:
            {

                chkBoxPortrait.setChecked(false);
                chkBoxLandscape.setChecked(true);

            }break;
            case R.id.chkBoxPortrait:
            {
                chkBoxPortrait.setChecked(true);
                chkBoxLandscape.setChecked(false);

            }break;

        }

    }

   class Telas
    {

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        private String nome;

        private String nomeArquivo;

        private String pathImg;

        public String getPathImg ()
        {
            return this.pathImg;
        }

        public void setImg(String path){
            this.pathImg = path;
        }

        public String getNomeArquivo() {
            return nomeArquivo;
        }

        public void setNomeArquivo(String nomeArquivo) {
            this.nomeArquivo = nomeArquivo;
        }

        public Telas(){}




    }

}
