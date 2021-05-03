package com.example.imticket;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import modelDominio.Partida;

public class VisualizacaoDetalhada extends AppCompatActivity {
    Button btComprar;
    TextView TextoDescricao, TextoNome, TextoHora, TextoData;
    ImageView ivImagemPartida, ivVoltar, ivCarrinho, ivHome, ivPerfil;


    Partida partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao_detalhada);
        btComprar = findViewById(R.id.btComprar);
        ivVoltar = findViewById(R.id.ivVoltar);
        TextoDescricao = findViewById(R.id.TextoDescricao);
        TextoNome = findViewById(R.id.TextoNome);
        TextoHora = findViewById(R.id.TextoHora);
        TextoData = findViewById(R.id.TextoData);
        ivImagemPartida = findViewById(R.id.ivImagemPartida);
        ivCarrinho = findViewById(R.id.ivCarrinho);
        ivHome = findViewById(R.id.ivHome);
        ivPerfil = findViewById(R.id.ivperfil);

        ivVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(VisualizacaoDetalhada.this, home.class);
                startActivity(it);
            }
        });

        btComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), Setores.class);
                it.putExtra("Partida", partida);
                startActivity(it);
            }
        });


        ivCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(VisualizacaoDetalhada.this, Carrinho.class);
                startActivity(it);
            }
        });

        ivPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(VisualizacaoDetalhada.this, Perfil.class);
                startActivity(it);
            }
        });

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(VisualizacaoDetalhada.this, home.class);
                startActivity(it);
            }
        });

        Intent it = getIntent();
        if (it != null){
            partida = (Partida) it.getSerializableExtra("Partida");
            TextoNome.setText(partida.getNomepartida());
            TextoDescricao.setText(partida.getDescricaopartida());
            TextoHora.setText(partida.HorarioPartidaString());
            TextoData.setText(partida.DataPartidaString());
            // carregando imagem
            if (partida.getImagem() != null) {
                Bitmap bmp = BitmapFactory.decodeByteArray(partida.getImagem(), 0, partida.getImagem().length);
                ivImagemPartida.setImageBitmap(bmp);
            }
        }

    }
}