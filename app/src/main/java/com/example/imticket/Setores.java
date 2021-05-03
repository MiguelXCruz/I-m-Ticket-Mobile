package com.example.imticket;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.CadeiraAdapter;
import controller.ConexaoController;
import modelDominio.Ingresso;
import modelDominio.Partida;

public class Setores extends AppCompatActivity {
    Button btInferiorEsquerdo1, btInferiorDireito2, btSupeiorEsquerdo3, btSuperiorDireito4,  btVipDireito5,btVipEsquerdo6;
    TextView tvNomePartidaSetores;
    Partida partida;
    int numsetor;


    InformacoesApp informacoesApp;

    ArrayList<Ingresso> lstIngressos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setores);

        tvNomePartidaSetores = findViewById(R.id.tvNomePartidaSetores);
        btInferiorEsquerdo1 = findViewById(R.id.btInferiorEsquerdo1);
        btInferiorDireito2 = findViewById(R.id.btInferiordireito2);
        btSupeiorEsquerdo3 = findViewById(R.id.btSuperiorEsquerdo3);
        btSuperiorDireito4 = findViewById(R.id.btSuperiorDireito4);
        btVipDireito5 = findViewById(R.id.btVipDireito5);
        btVipEsquerdo6 = findViewById(R.id.btVipEsquerdo6);

        informacoesApp = (InformacoesApp) getApplicationContext();




        Intent it = getIntent();
        if (it != null){
            partida = (Partida) it.getSerializableExtra("Partida");

            tvNomePartidaSetores.setText(partida.getNomepartida());
        }



        btInferiorEsquerdo1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                numsetor = 1;

                Intent it = new Intent(getApplicationContext(), Setores_Comprar.class);
                it.putExtra("Partida", partida);
                it.putExtra("Setor", numsetor);
                startActivity(it);


            }
        });


        btInferiorDireito2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numsetor = 2;

                Intent it = new Intent(getApplicationContext(), Setores_Comprar.class);
                it.putExtra("Partida", partida);
                it.putExtra("Setor", numsetor);
                startActivity(it);
            }
        });



        btSupeiorEsquerdo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numsetor = 3;

                Intent it = new Intent(getApplicationContext(), Setores_Comprar.class);
                it.putExtra("Partida", partida);
                it.putExtra("Setor", numsetor);
                startActivity(it);
            }
        });


        btSuperiorDireito4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numsetor = 4 ;
                Intent it = new Intent(getApplicationContext(), Setores_Comprar.class);
                it.putExtra("Partida", partida);
                it.putExtra("Setor", numsetor);
                startActivity(it);
            }
        });

        btVipDireito5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numsetor = 5;
                Intent it = new Intent(getApplicationContext(), Setores_Comprar.class);
                it.putExtra("Partida", partida);
                it.putExtra("Setor", numsetor);
                startActivity(it);
            }
        });

        btVipEsquerdo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numsetor = 6;

                Intent it = new Intent(getApplicationContext(), Setores_Comprar.class);
                it.putExtra("Partida", partida);
                it.putExtra("Setor", numsetor);
                startActivity(it);
            }
        });

    }
}