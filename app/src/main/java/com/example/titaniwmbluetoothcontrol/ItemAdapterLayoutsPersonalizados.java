package com.example.titaniwmbluetoothcontrol;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;


public class ItemAdapterLayoutsPersonalizados extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<TelaEscolhaLayoutsPersonalizados.Telas> telas ;
     private ItemClickListener itemClickListener;
    private Context context;

     public void setOnItemClickListener(ItemClickListener itemClickListener)
     {
         this.itemClickListener =  itemClickListener;
     }

    public ItemAdapterLayoutsPersonalizados(ArrayList<TelaEscolhaLayoutsPersonalizados.Telas> telas, Context context) {
        this.telas = telas;
        this.context = context;
    }



    @Override
    public int getItemCount() {

        return telas != null ? telas.size() : 0;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View visual;
        visual = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.escolhalayoutspersonalizados, viewGroup, false);
        return new ItemViewHolder(visual);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


        ((ItemViewHolder) viewHolder).tvNomeLayoutPersonalizado.setText(telas.get(i).getNome());
        File img = new File(telas.get(i).getPathImg().replaceAll(" ", ""));
        Log.i("Img", "caminho: " + telas.get(i).getPathImg());
        if(img.exists()){
            Toast.makeText(context, "imagem existe: " + telas.get(i).getPathImg(), Toast.LENGTH_SHORT).show();

            Bitmap bitmap = BitmapFactory.decodeFile(img.getAbsolutePath());

            ((ItemViewHolder) viewHolder).iVLayoutPersonalizado.setImageBitmap(bitmap);

        }

    }



    class ItemViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView tvNomeLayoutPersonalizado;
        private ImageButton btnExcluirLayout;

        private ImageView iVLayoutPersonalizado;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNomeLayoutPersonalizado = (TextView) itemView.findViewById(R.id.tvNomeLayoutPersonalizado);
            btnExcluirLayout = (ImageButton) itemView.findViewById(R.id.btnExcluirLayout);
            iVLayoutPersonalizado = (ImageView) itemView.findViewById(R.id.iVLayoutPersonalizado);


            iVLayoutPersonalizado.setOnClickListener(this);
            btnExcluirLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v)
        {

            if(itemClickListener != null)
            {
               itemClickListener.OnItemClick(v, getAdapterPosition());
                //itemClickListener.buttonExcluirOnClick(v, getAdapterPosition());
            }

        }
    }

 public interface ItemClickListener
 {
     void OnItemClick(View v, int position);
    // void buttonExcluirOnClick(View v, int position);

 }


}

