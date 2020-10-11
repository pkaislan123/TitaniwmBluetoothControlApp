package com.example.titaniwmbluetoothcontrol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ItemAdapterTerminal extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<Terminal.dadosTerminal> dados ;


    public ItemAdapterTerminal(ArrayList<Terminal.dadosTerminal> dados) {
        this.dados = dados;
    }



    @Override
    public int getItemCount() {

        return dados != null ? dados.size() : 0;

    }

    @NonNull
    @Override
     public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View visual;
        if(i == 0)
         {
             visual = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.terminal_textview_send, viewGroup, false);
             return new SendViewHolder(visual);
         }
        else
        {
            visual = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.terminal_textview_receiver, viewGroup, false);
            return new ReceiverViewHolder(visual);
        }

    }





    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if(getItemViewType(i) == 0)
        {
            try{
                if (dados.get(i - 1).getTipo() != 0) {

                        ((SendViewHolder) viewHolder).tvRecyclerSendDevice.setText(dados.get(i).getDevice().replaceAll(" ", ""));
                        ((SendViewHolder) viewHolder).tvTerminalTextSend.setText(dados.get(i).getTexto());

                }
                else {

                        ((SendViewHolder) viewHolder).tvTerminalTextSend.setText(dados.get(i).getTexto());

                }

            }catch (Exception e){

                ((SendViewHolder) viewHolder).tvRecyclerSendDevice.setText(dados.get(i).getDevice().replaceAll(" ", ""));
                ((SendViewHolder) viewHolder).tvTerminalTextSend.setText(dados.get(i).getTexto());

            }
        }
        else {

            try{
                if (dados.get(i - 1).getTipo() != 1) {
                        ((ReceiverViewHolder) viewHolder).tvRecyclerReceiverDevice.setText(dados.get(i).getDevice());
                        ((ReceiverViewHolder) viewHolder).tvTerminalTextReceiver.setText(dados.get(i).getTexto());

                }
                else {

                            ((ReceiverViewHolder) viewHolder).tvTerminalTextReceiver.setText(dados.get(i).getTexto());

                }

            }catch (Exception e){
                    ((ReceiverViewHolder) viewHolder).tvRecyclerReceiverDevice.setText(dados.get(i).getDevice());
                    ((ReceiverViewHolder) viewHolder).tvTerminalTextReceiver.setText(dados.get(i).getTexto());


            }

        }

    }

    @Override
    public int getItemViewType(int position) {

        if(dados.get(position).getTipo() == 0)
         return 0;
        else
            return 1;

    }



    class ReceiverViewHolder extends  RecyclerView.ViewHolder
    {
        private TextView tvTerminalTextReceiver;
        private TextView tvRecyclerReceiverDevice;


        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);

         tvTerminalTextReceiver = (TextView) itemView.findViewById(R.id.tvRecycleReceiver);
            tvRecyclerReceiverDevice = (TextView) itemView.findViewById(R.id.tvRecyclerReceiverDevice);

        }
    }



    class SendViewHolder extends  RecyclerView.ViewHolder
    {
        private TextView tvTerminalTextSend;
        private TextView tvRecyclerSendDevice;


        public SendViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTerminalTextSend = (TextView) itemView.findViewById(R.id.tvRecycleSend);
            tvRecyclerSendDevice = (TextView) itemView.findViewById(R.id.tvRecycleSendDevice);

        }
    }




}

