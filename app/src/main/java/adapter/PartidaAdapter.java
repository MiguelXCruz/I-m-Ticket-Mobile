package adapter;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.imticket.R;

import java.util.ArrayList;

import modelDominio.Partida;

public class PartidaAdapter extends RecyclerView.Adapter<PartidaAdapter.MyViewHolder>{


    private ArrayList<Partida> lstPartidas;
    private PartidaOnClickListener partidaOnClickListener;
    ImageView imgView;


    public PartidaAdapter(ArrayList<Partida> lstPartidas, PartidaOnClickListener partidaOnClickListener) {
        this.lstPartidas = lstPartidas;
        this.partidaOnClickListener = partidaOnClickListener;
    }


    @Override
    public PartidaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exemple_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final PartidaAdapter.MyViewHolder holder, final int position) {


        Partida partida = lstPartidas.get(position);


        holder.tvConfronto.setText(partida.getNomepartida());
        holder.tvData.setText(partida.DataPartidaString());
        holder.tvHora.setText(partida.HorarioPartidaString());
        // carregando imagem
        if (partida.getImagem() != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(partida.getImagem(), 0, partida.getImagem().length);
            imgView.setImageBitmap(bmp);
        }

        // clique no item do cliente
        if (partidaOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    partidaOnClickListener.onClickPartida(holder.itemView,position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return lstPartidas.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvConfronto, tvData, tvHora;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvConfronto = itemView.findViewById(R.id.tVConfronto);
            tvData = itemView.findViewById(R.id.tVData);
            tvHora = itemView.findViewById(R.id.tVHora);
            imgView = itemView.findViewById(R.id.imgView);
        }
    }


    public interface PartidaOnClickListener {
        public void onClickPartida(View view, int position);
    }

}
