package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imticket.Carrinho;
import com.example.imticket.R;

import java.util.ArrayList;

import modelDominio.Ingresso;
import modelDominio.Partida;

public class IngressoAdapter extends RecyclerView.Adapter<IngressoAdapter.MyViewHolder>{

    private ArrayList<Ingresso> lstIngressos;
    private ingressoOnClickListener ingressoOnClickListener;


    public IngressoAdapter(ArrayList<Ingresso> lstIngressos, ingressoOnClickListener ingressoOnClickListener) {
        this.lstIngressos = lstIngressos;
        this.ingressoOnClickListener = ingressoOnClickListener;
    }


    @Override
    public IngressoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_carrinho, parent, false);

        return new IngressoAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final IngressoAdapter.MyViewHolder holder, final int position) {


        Ingresso ingresso = lstIngressos.get(position);


        holder.tVCadeira.setText(String.valueOf(ingresso.getCadeira().getNumcadeira()));
        holder.tvSetor.setText(String.valueOf(ingresso.getCadeira().getSetor().getNumsetor()));
        holder.tvNomePartida.setText(ingresso.getPartida().getNomepartida());
        holder.tvPreco.setText(ingresso.getCadeira().getSetor().getValorString());

        // clique no item do cliente
        if (ingressoOnClickListener != null) {
            holder.ivExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ingressoOnClickListener.onClickIngresso(holder.itemView,position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return lstIngressos.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tVCadeira, tvSetor, tvNomePartida, tvPreco;
        ImageView ivExcluir;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvNomePartida = itemView.findViewById(R.id.tvNomePartida);
            tVCadeira = itemView.findViewById(R.id.tVCadeira);
            tvSetor = itemView.findViewById(R.id.tvSetor);
            tvPreco = itemView.findViewById(R.id.tvPreco);


            ivExcluir = itemView.findViewById(R.id.ivExcluir);
        }
    }


    public interface ingressoOnClickListener {
        public void onClickIngresso(View view, int position);
    }



}
