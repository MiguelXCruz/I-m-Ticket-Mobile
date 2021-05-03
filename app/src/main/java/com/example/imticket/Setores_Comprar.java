package com.example.imticket;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.PartidaAdapter;
import controller.ConexaoController;
import modelDominio.Cadeira;
import adapter.CadeiraAdapter;
import modelDominio.Ingresso;
import modelDominio.Partida;

public class Setores_Comprar extends AppCompatActivity {
    ImageView ivCarrinho, ivHome, ivPerfil;


    RecyclerView rvComprarIngresso;
    CadeiraAdapter cadeiraAdapter;
    ArrayList<Ingresso> lstIngressos;

    InformacoesApp informacoesApp;

    Partida partida;
    int numsetor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setores__comprar);
        ivCarrinho = findViewById(R.id.ivCarrinho);
        ivHome = findViewById(R.id.ivHome);
        ivPerfil = findViewById(R.id.ivperfil);

        rvComprarIngresso = findViewById(R.id.rvComprarIngresso);

        informacoesApp = (InformacoesApp) getApplicationContext();

        Intent it = getIntent();
        if (it != null){
            partida = (Partida) it.getSerializableExtra("Partida");
            numsetor = (int) it.getSerializableExtra("Setor");
        }


        //Toast.makeText(informacoesApp, "number: " + numsetor, Toast.LENGTH_SHORT).show();

        ivCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Setores_Comprar.this, Carrinho.class);
                startActivity(it);
            }
        });

        ivPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Setores_Comprar.this, Perfil.class);
                startActivity(it);
            }
        });

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Setores_Comprar.this, home.class);
                startActivity(it);
            }
        });


    }


    private void atualizaLista() {
        Thread t = new Thread() {
            @Override
            public void run() {
                ConexaoController ccont = new ConexaoController(informacoesApp);

                lstIngressos = ccont.ingressosDisponiveisSetorLista(partida.getIdpartida(), numsetor);

                if (lstIngressos != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cadeiraAdapter = new CadeiraAdapter(lstIngressos, trataCliqueItem);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Setores_Comprar.this);
                            rvComprarIngresso.setLayoutManager(mLayoutManager);
                            rvComprarIngresso.setItemAnimator(new DefaultItemAnimator());
                            rvComprarIngresso.setAdapter(cadeiraAdapter);
                        }
                    });
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(informacoesApp, "ta nuloooooooo", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        };

        t.start();

    }

    CadeiraAdapter.CadeiraOnClickListener trataCliqueItem = new CadeiraAdapter.CadeiraOnClickListener() {
        @Override
        public void onClickCadeira(View view, int position) {


            Ingresso ingresso = lstIngressos.get(position);

           //------------------------------------------------

            informacoesApp.lstCarrinho.add(ingresso);


            //------------------------------------------



            Intent it = new Intent(getApplicationContext(), home.class);




            startActivity(it);
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        atualizaLista();
    }


}
