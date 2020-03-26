package com.example.titaniwmbluetoothcontrol;

//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class BackGroundAdapter extends RecyclerView.Adapter<BackGroundAdapter.BackGroundHolder> {

    private ArrayList<Integer> enderecoImgs;
    private LayoutClickListener layoutClickListener;

    public void setOnLayoutClickListener(LayoutClickListener layoutClickListener)
    {
        this.layoutClickListener =  layoutClickListener;
    }

    public BackGroundAdapter(ArrayList<Integer> enderecoImgs )
    {
        this.enderecoImgs = enderecoImgs;

    }



    @Override
    public BackGroundHolder onCreateViewHolder( ViewGroup viewGroup, int i) {




        return new BackGroundHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.backgrounds, viewGroup, false));
    }



    @Override
    public void onBindViewHolder(final BackGroundHolder itemHolder, final int i) {

       itemHolder.layout_fundo.setBackgroundResource(enderecoImgs.get(i));
    }

    @Override
    public int getItemCount() {
        return enderecoImgs.size();
    }


    public class BackGroundHolder extends RecyclerView.ViewHolder{

        ImageView layout_fundo;


        public BackGroundHolder(View itemView) {
            super(itemView);
            layout_fundo = (ImageView) itemView.findViewById(R.id.imgViewFundo);
            layout_fundo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(layoutClickListener != null)
                    {
                        layoutClickListener.onLayoutClick(v, getAdapterPosition());
                        //itemClickListener.buttonExcluirOnClick(v, getAdapterPosition());
                    }
                }
            });


        }


    }

    public interface LayoutClickListener
    {
        void onLayoutClick(View v, int position);
        // void buttonExcluirOnClick(View v, int position);

    }


    }


