package com.example.titaniwmbluetoothcontrol;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titaniwmbluetoothcontrol.interfaces.ItemClickListener;
import com.example.titaniwmbluetoothcontrol.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private ItemClickListener itemCliquado;
     private List<Item> listIntens;
    ConexaoThread connect;
    Context context;
    /* public ItemAdapter(ArrayList itens) {
        listIntens = itens;
    }
*/
   public ItemAdapter(Context context)
   {
       this.context = context;
   }


    public void setArrayList(ArrayList itens)
    {
        listIntens = itens;
    }
    @Override
    public ItemHolder onCreateViewHolder( ViewGroup viewGroup, int i) {




        return new ItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false));
    }

    //5a020c
    //1f00

    @Override
    public void onBindViewHolder(final ItemHolder itemHolder, final int i) {
             itemHolder.nome.setText((String.format(Locale.getDefault(), "%s", listIntens.get(i).getNome())));
             itemHolder.mac.setText((String.format(Locale.getDefault(), "%s", listIntens.get(i).getMac())));
             Log.i("Testando", "Tipo do elemento e: "+ listIntens.get(i).getTipo());
             if(listIntens.get(i).getTipo().equals("5a020c")) {
                 itemHolder.iVcv.setImageResource(R.drawable.iconecelular);
                 Log.i("Testando", "No adapter, elemento e" + listIntens.get(i).getNome() +"e e um smartphone");
             }else if(listIntens.get(i).getTipo().equals("1f00")) {
                 itemHolder.iVcv.setImageResource(R.drawable.bluetoothdevice);
                 Log.i("Testando", "No adapter, elemento e" + listIntens.get(i).getNome() +"e e um adaptador bluetooth");


             }
        connect=  ((BaseAplicacao)context.getApplicationContext()).getConnect();

        if(listIntens.get(i).getStatusConection().equals("true"))
             {
                 if(connect.getestaRodando())
                   itemHolder.iVStatusConection.setImageResource(R.drawable.iconconectado);
                 else{
                     itemHolder.iVStatusConection.setImageResource(R.drawable.icondesconectado);

                 }


             }else if(listIntens.get(i).getStatusConection().equals("false"))
             {

                     itemHolder.iVStatusConection.setImageResource(R.drawable.icondesconectado);
             }

    }




    @Override
    public int getItemCount() {
        return listIntens != null ? listIntens.size() :0;
    }

    public void setItemCliquado(ItemClickListener r)
    {
        this.itemCliquado = r;
    }



    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cv;
        public TextView nome;
        public TextView mac;
        private int posicao;
        private ImageView iVcv;
        private ImageView iVStatusConection;

        public ItemHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            nome = (TextView) itemView.findViewById(R.id.tVNome);
            mac = (TextView) itemView.findViewById(R.id.tVMac);
            iVcv = (ImageView) itemView.findViewById(R.id.iVcv);
            iVStatusConection = (ImageView) itemView.findViewById(R.id.iVStatusConection);

            itemView.setOnClickListener(this);

        }

        public int getPosicao()
        {
            return  this.posicao;
        }

        public void setPosicao(int posicao)
        {
            this.posicao = posicao;
        }



        @Override
        public void onClick(View view) {
           if(itemCliquado != null)
           {
               itemCliquado.onItemClick(view,getAdapterPosition() );
           }
            //Toast.makeText(view.getContext(), "Elemento" + getAdapterPosition() , Toast.LENGTH_SHORT).show();
            //setPosicao(getAdapterPosition());


        }












    }
}

