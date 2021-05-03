package com.example.imticket;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.IngressoAdapter;
import adapter.IngressoCompraAdapter;
import adapter.PartidaAdapter;
import controller.ConexaoController;
import modelDominio.Ingresso;
import modelDominio.Partida;

public class MeusIngressos extends AppCompatActivity {
    RecyclerView rvmeusIngressos;
    InformacoesApp informacoesApp;
    IngressoCompraAdapter ingressoCompraAdapter;

    ArrayList<Ingresso> lstIngressos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_ingressos);
        rvmeusIngressos = findViewById(R.id.rvmeusIngressos);


        informacoesApp = (InformacoesApp) getApplicationContext();

        Thread t = new Thread(){
            @Override
            public void run() {
                ConexaoController ccont = new ConexaoController(informacoesApp);

                lstIngressos = ccont.usuarioMeusIngressos(informacoesApp.user.getIdusuario());

                if (lstIngressos==null || lstIngressos.isEmpty()) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(informacoesApp, "Você não possui Ingressos ainda", Toast.LENGTH_SHORT).show();

                        }
                    });


                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            ingressoCompraAdapter = new IngressoCompraAdapter(lstIngressos, trataCliqueItem);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MeusIngressos.this);
                            rvmeusIngressos.setLayoutManager(mLayoutManager);
                            rvmeusIngressos.setItemAnimator(new DefaultItemAnimator());
                            rvmeusIngressos.setAdapter(ingressoCompraAdapter);

                        }
                    });

                }
            }
        };

        t.start();

    }


    IngressoCompraAdapter.IngressoCompraOnClickListener trataCliqueItem = new IngressoCompraAdapter.IngressoCompraOnClickListener() {
        @Override
        public void onClickIngressoCompra(View view, int position) {


            Ingresso ingresso = lstIngressos.get(position);

            Intent it = new Intent(getApplicationContext(), IngressoDetalhado.class);
            it.putExtra("Ingresso", ingresso);
            startActivity(it);

        }
    };
}