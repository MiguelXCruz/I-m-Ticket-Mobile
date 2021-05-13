package com.example.imticket;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import modelDominio.Ingresso;
import adapter.IngressoAdapter;

public class Carrinho extends AppCompatActivity {
    Button btFinalizarCompra, btLimparCarrinho;
    RecyclerView rvCarrinho;
    TextView tvprecototal;
    LinearLayout llPreco;

    ImageView ivCarrinho, ivHome, ivPerfil;
    IngressoAdapter ingressoAdapter;


    InformacoesApp informacoesApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        btFinalizarCompra = findViewById(R.id.btFinalizarCompra);
        btLimparCarrinho = findViewById(R.id.btLimparCarrinho);
        tvprecototal = findViewById(R.id.tvprecototal);
        ivCarrinho = findViewById(R.id.ivCarrinho);
        ivHome = findViewById(R.id.ivHome);
        ivPerfil = findViewById(R.id.ivperfil);
        llPreco = findViewById(R.id.llPreco);
        rvCarrinho = findViewById(R.id.rvCarrinho);


        informacoesApp = (InformacoesApp) getApplicationContext();

        btFinalizarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Carrinho.this, EfetuarCompra.class);
                startActivity(it);
            }
        });

        btLimparCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                informacoesApp.lstCarrinho.clear();
                Intent it = new Intent(Carrinho.this, home.class);
                startActivity(it);
                Toast.makeText(informacoesApp, "Carrinho está limpo", Toast.LENGTH_SHORT).show();
            }
        });

        ivCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Carrinho.this, Carrinho.class);
                startActivity(it);
            }
        });

        ivPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Carrinho.this, Perfil.class);
                startActivity(it);
            }
        });

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Carrinho.this, home.class);
                startActivity(it);
            }
        });


    }

    private void atualizaCarrinho(){

        if (!informacoesApp.lstCarrinho.isEmpty()) {

            ingressoAdapter = new IngressoAdapter(informacoesApp.lstCarrinho, tratacliqueexcluir);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Carrinho.this);
            rvCarrinho.setLayoutManager(mLayoutManager);
            rvCarrinho.setItemAnimator(new DefaultItemAnimator());
            rvCarrinho.setAdapter(ingressoAdapter);



            float precoTotal = 0;

            for(int x = 0; x < informacoesApp.lstCarrinho.size(); x++){
                Ingresso ingresso = informacoesApp.lstCarrinho.get(x);

                precoTotal += ingresso.getCadeira().getSetor().getPrecosetor();

            }

            tvprecototal.setText(Float.toString(precoTotal));


        } else {
            Toast.makeText(informacoesApp, "Carrinho Vazio", Toast.LENGTH_SHORT).show();
            btLimparCarrinho.setVisibility(View.INVISIBLE);
            btFinalizarCompra.setVisibility(View.INVISIBLE);


            // tv do preço

            llPreco.setVisibility(View.INVISIBLE);
            tvprecototal.setVisibility(View.INVISIBLE);
        }


    }



    IngressoAdapter.ingressoOnClickListener tratacliqueexcluir = new IngressoAdapter.ingressoOnClickListener() {
        @Override
        public void onClickIngresso(View view, int position) {

            informacoesApp.lstCarrinho.remove(position);

            atualizaCarrinho();

            if (informacoesApp.lstCarrinho.isEmpty()) {
                Intent it = new Intent(Carrinho.this, home.class);
                atualizaCarrinho();
                startActivity(it);
            }else{
                //else feito para remover a mensagem REMOVER caso o carrinho fique vazio, para n poluir a tela
                Toast.makeText(informacoesApp, "remover", Toast.LENGTH_SHORT).show();
            }
        }
    };




    @Override
    protected void onResume() {
        super.onResume();
        atualizaCarrinho();
    }



}