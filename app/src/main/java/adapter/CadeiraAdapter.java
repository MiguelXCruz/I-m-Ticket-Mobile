package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imticket.R;
import com.example.imticket.Setores_Comprar;
import com.example.imticket.home;

import java.util.ArrayList;

import modelDominio.Cadeira;
import modelDominio.Ingresso;
import modelDominio.Partida;

public class CadeiraAdapter extends RecyclerView.Adapter<CadeiraAdapter.MyViewHolder> {

    private ArrayList<Ingresso> lstIngressos;
    private CadeiraAdapter.CadeiraOnClickListener cadeiraOnClickListener;



    public CadeiraAdapter(ArrayList<Ingresso> lstIngressos, CadeiraOnClickListener cadeiraOnClickListener) {
        this.lstIngressos = lstIngressos;
        this.cadeiraOnClickListener = cadeiraOnClickListener;
    }


    @Override
    public CadeiraAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_setores, parent, false);

        return new CadeiraAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final CadeiraAdapter.MyViewHolder holder, final int position) {


        Ingresso ingresso = lstIngressos.get(position);

        holder.tVCadeira.setText(String.valueOf(ingresso.getCadeira().getNumcadeira()));         // numcadeira
        holder.tvSetor.setText(String.valueOf(ingresso.getCadeira().getSetor().getNumsetor()));  // numsetor
        holder.btPreco.setText(ingresso.getCadeira().getSetor().getValorString());               // preco


        // clique no item do cliente
        if (cadeiraOnClickListener != null) {
            holder.btPreco.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cadeiraOnClickListener.onClickCadeira(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lstIngressos.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tVCadeira, tvSetor;
        Button btPreco;

        public MyViewHolder(View itemView) {
            super(itemView);
            tVCadeira = itemView.findViewById(R.id.tVCadeira);
            tvSetor = itemView.findViewById(R.id.tvSetor);
            btPreco = itemView.findViewById(R.id.btPreco);
        }
    }


    public interface CadeiraOnClickListener {
        public void onClickCadeira(View view, int position);
    }

}
