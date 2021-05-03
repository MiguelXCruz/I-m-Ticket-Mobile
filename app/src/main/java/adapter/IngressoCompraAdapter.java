package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.imticket.R;

import java.util.ArrayList;

import modelDominio.Ingresso;

public class IngressoCompraAdapter extends RecyclerView.Adapter<IngressoCompraAdapter.MyViewHolder>{
    private ArrayList<Ingresso> lstIngressos;
    IngressoCompraOnClickListener ingressoCompraOnClickListener;


    public IngressoCompraAdapter(ArrayList<Ingresso> lstIngressos) {
        this.lstIngressos = lstIngressos;
    }

    public IngressoCompraAdapter(ArrayList<Ingresso> lstIngressos, IngressoCompraAdapter.IngressoCompraOnClickListener ingressoOnClickListener) {
        this.lstIngressos = lstIngressos;
        this.ingressoCompraOnClickListener = ingressoOnClickListener;
    }

    @Override
    public IngressoCompraAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_finalcompra, parent, false);

        return new MyViewHolder(itemView);
    }




    @Override
    public void onBindViewHolder(final IngressoCompraAdapter.MyViewHolder holder, final int position) {


        Ingresso ingresso = lstIngressos.get(position);


        holder.tVCadeira.setText(String.valueOf(ingresso.getCadeira().getNumcadeira()));
        holder.tvSetor.setText(String.valueOf(ingresso.getCadeira().getSetor().getNumsetor()));
        holder.tvNomePartida.setText(ingresso.getPartida().getNomepartida());
        holder.tvPreco.setText(ingresso.getCadeira().getSetor().getValorString());


        if (ingressoCompraOnClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ingressoCompraOnClickListener.onClickIngressoCompra(holder.itemView,position);
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
        public MyViewHolder(View itemView) {
            super(itemView);
            tvNomePartida = itemView.findViewById(R.id.tvNomePartida);
            tVCadeira = itemView.findViewById(R.id.tVCadeira);
            tvSetor = itemView.findViewById(R.id.tvSetor);
            tvPreco = itemView.findViewById(R.id.tvPreco);


        }
    }

    public interface IngressoCompraOnClickListener {
        public void onClickIngressoCompra(View view, int position);
    }

}
