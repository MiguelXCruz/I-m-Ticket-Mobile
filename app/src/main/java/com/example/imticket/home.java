package com.example.imticket;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import controller.ConexaoController;
import modelDominio.Partida;
import adapter.PartidaAdapter;

public class home extends AppCompatActivity {
    TextView SemPartidas;
    ImageView ivSair, ivCarrinho, ivHome, ivPerfil;

    RecyclerView rvPartidas;
    PartidaAdapter partidaAdapter;
    ArrayList<Partida> lstPartidas;


    InformacoesApp informacoesApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ivSair = findViewById(R.id.ivSair);
        ivCarrinho = findViewById(R.id.ivCarrinho);
        ivHome = findViewById(R.id.ivHome);
        ivPerfil = findViewById(R.id.ivperfil);


        rvPartidas= findViewById(R.id.rvPartidas);


        informacoesApp = (InformacoesApp) getApplicationContext();


        ivSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(home.this, MainActivity.class);
                startActivity(it);
            }
        });

       ivCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(home.this, Carrinho.class);
                startActivity(it);
            }
        });

        ivPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(home.this, Perfil.class);
                startActivity(it);
            }
        });




    }


    // preparando os dados a serem listados


    private void atualizaLista(){

        Thread t = new Thread(){
        @Override
        public void run() {
            ConexaoController ccont = new ConexaoController(informacoesApp);

            lstPartidas = ccont.partidaIngressosDisponiveisLista();

            if (lstPartidas != null){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        partidaAdapter = new PartidaAdapter(lstPartidas, trataCliqueItem);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(home.this);
                        rvPartidas.setLayoutManager(mLayoutManager);
                        rvPartidas.setItemAnimator(new DefaultItemAnimator());
                        rvPartidas.setAdapter(partidaAdapter);
                    }
                });
            }
        }
    };

    t.start();

    }


    PartidaAdapter.PartidaOnClickListener trataCliqueItem = new PartidaAdapter.PartidaOnClickListener() {
        @Override
        public void onClickPartida(View view, int position) {

            Partida partida = lstPartidas.get(position);

            Intent it = new Intent(getApplicationContext(), VisualizacaoDetalhada.class);
            it.putExtra("Partida", partida);
            Log.d("Teste","Partida-data:"+ partida.DataPartidaString() + " Horario: "+ partida.HorarioPartidaString());
            startActivity(it);

        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        atualizaLista();
    }




}