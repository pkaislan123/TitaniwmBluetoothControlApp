package com.example.titaniwmbluetoothcontrol;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

import static com.example.titaniwmbluetoothcontrol.R.id.btnExcluirLayout;
import static com.example.titaniwmbluetoothcontrol.R.id.iVLayoutPersonalizado;

public class TelaEscolhaLayoutsPersonalizados extends AppCompatActivity {

    ArrayList<Telas> telas = new ArrayList<>();



    RecyclerView recyclerView;
    ItemAdapterLayoutsPersonalizados itemAdapterLayoutsPersonalizados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_escolha_layouts_personalizados);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEscolhaLayoutsPersonalizados);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        itemAdapterLayoutsPersonalizados = new ItemAdapterLayoutsPersonalizados(telas);

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
           Intent novatela = new Intent(this, TelaNovoLayoutsPersonalizados.class);
           startActivity(novatela);
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
               // Toast.makeText(this, arquivosNaPasta[i].getName(), Toast.LENGTH_SHORT).show();
                String arquivo = mp1.ler("LayoutsPersonalizados", arquivosNaPasta[i].getName());

                //Toast.makeText(this, "texto desse arquivo: "+ arquivo, Toast.LENGTH_SHORT).show();
                String [] linhas = arquivo.split("\n");
                /*  for(int j = 0; j < linhas.length; j++)
                  {
                      Toast.makeText(this, "Linha"+j+":"+ linhas[j], Toast.LENGTH_SHORT).show();

                  }

                 */
                Telas tela = new Telas();
                  tela.setNome(linhas[0]);
                  tela.setNomeArquivo(arquivosNaPasta[i].getName());
                  telas.add(tela);
                  itemAdapterLayoutsPersonalizados.notifyItemInserted(itemAdapterLayoutsPersonalizados.getItemCount());



            }

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

        public String getNomeArquivo() {
            return nomeArquivo;
        }

        public void setNomeArquivo(String nomeArquivo) {
            this.nomeArquivo = nomeArquivo;
        }

        public Telas(){}




    }

}
