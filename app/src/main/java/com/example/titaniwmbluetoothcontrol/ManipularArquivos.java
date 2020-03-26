package com.example.titaniwmbluetoothcontrol;

import android.app.Activity;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManipularArquivos {

/*



        Map<String, ?> dados = sp1.getDados("preferencias_usuarios", 0);
        Toast.makeText(this, "dados: " + dados, Toast.LENGTH_SHORT).show();


        manipularArquivos = new ManipularArquivos(this);
        manipularArquivos.criarArquivo("teste");
       // manipularArquivos.escreverArquivo("teste", "teste");
        String lido = manipularArquivos.ler("teste");
        Toast.makeText(this, lido, Toast.LENGTH_SHORT).show();
        tvLeitura.setText(lido);


    final ManipularBD.Person person = new ManipularBD.Person();
        person.setFirstName("João");
        person.setLastName("Das Neves");
        person.setAge(30);
        person.setEmail("reidonorte@stark.com");

        new Thread(new Runnable() {
        @Override
        public void run() {
            ManipularBD db = ManipularBD.getAppDatabase(getBaseContext());

            db.personDao().insert(person);
            finish();
        }
    }).start();
*/

    private File diretorio;
    private String nomeDiretorio = "Impressora";
    private String diretorioDados;
    private String nomeArquivo;
    private Activity tela;
    private String newline = "\r\n";




    public ManipularArquivos(Activity tela)
    {
        this.tela = tela;

        criarDiretorioRaiz();
        criarDiretorio("LayoutsPersonalizados");
        // criarDiretorio("Impressora/LayoutsPersonalizados");


    }


    public boolean excluirArquivo(String subPasta, String nomeArquivo)
    {
        String diretorioRaiz = getDiretorioRaiz();
        String diretorio = getDiretorioRaiz() + "/" + subPasta + "/";
        File arquivoExcluir = new File(diretorio, nomeArquivo);
        if(arquivoExcluir.isFile())
        {

            if(arquivoExcluir.delete()) {
                return true;
            }
            else
                return false;
        }
        else
        {
           // Toast.makeText(tela.getApplicationContext(), "nao e arquivo", Toast.LENGTH_SHORT).show();

            return false;
        }

    }

    public String getDiretorioRaiz()
    {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/TiTaniwmBluetooth";
    }


    public void criarDiretorioRaiz()
    {

        diretorioDados = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/TiTaniwmBluetooth";

        diretorio = new File(diretorioDados);

        if(diretorio.exists())
        {

        }
        else
        {
            if(diretorio.mkdir()) {

            }
            else
            {

            }

        }


    }


    public void criarDiretorio(String nomeDiretorio)
    {

        diretorioDados = getDiretorioRaiz() + "/"+nomeDiretorio+"/";

        diretorio = new File(diretorioDados);

        if(diretorio.exists())
        {

        }
        else
        {
            if(diretorio.mkdir()) {

            }
            else
                {

                }

        }


    }

    public void criarArquivo(String nomeArquivo, String subPasta)
    {
        this.nomeArquivo = nomeArquivo;

        String diretorioRaiz = getDiretorioRaiz();
        String diretorio = getDiretorioRaiz() + "/" + subPasta + "/";
        File arquivo = new File(diretorio, this.nomeArquivo);


        if(arquivo.exists())
        {

        }
        else
        {
            try {
                arquivo.createNewFile();

            } catch (IOException e) {

                e.printStackTrace();
            }


        }

    }

    public void escreverArquivo(String subPasta, String nomeArquivo, String escrita)
    {

        this.nomeArquivo = nomeArquivo;

        String diretorioRaiz = getDiretorioRaiz();
        String diretorio = getDiretorioRaiz() + "/" + subPasta + "/";
        File arquivo = new File(diretorio, this.nomeArquivo);


            if(arquivo.exists()) {

                try {

                    FileWriter escrever = new FileWriter(arquivo, true);
                    BufferedWriter out = new BufferedWriter(escrever);
                    out.write(escrita);
                    out.newLine();

                    out.close();


                } catch (IOException e) {


                    e.printStackTrace();
                }
            }
                 else
                {

                    criarArquivo(nomeArquivo, subPasta);
                    escreverArquivo(subPasta, nomeArquivo, escrita);
                }




    }


  public File[] pegarArquivos(String subpasta)
  {
      String diretorio = getDiretorioRaiz() + "/" + subpasta ;
      File pasta = new File(diretorio);
      File[] files = pasta.listFiles();

      return files;

  }

  public String ler(String subpasta, String nomeArquivo)
  {
      String retorno = new String();
      String linhas;
      this.nomeArquivo = nomeArquivo ;
      String diretorio = getDiretorioRaiz() + "/" + subpasta + "/";
      File arquivo = new File(diretorio, this.nomeArquivo);
      if(arquivo.isFile())
      {

          if(arquivo.exists()) {

              try {
                  FileReader ler = new FileReader(arquivo);
                  BufferedReader bf_leitura = new BufferedReader(ler);

                  while((linhas = bf_leitura.readLine()) != null)
                  {

                      retorno = retorno + linhas + newline;
                  }
                  bf_leitura.close();




                  return retorno;

              } catch (FileNotFoundException e) {
                  e.printStackTrace();
                  return null;
              } catch (IOException e) {
                  //Toast.makeText(tela.getApplicationContext(), "erro ao ler", Toast.LENGTH_SHORT).show();

                  e.printStackTrace();
                  return null;

              }


          }
          else
          {

              return null;
          }
      }
      else
      {

          return null;
      }


  }


}
