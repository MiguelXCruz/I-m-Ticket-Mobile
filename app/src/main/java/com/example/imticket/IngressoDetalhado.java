package com.example.imticket;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import modelDominio.Ingresso;
import modelDominio.Partida;

public class IngressoDetalhado extends AppCompatActivity {
    ImageView ivCarrinho, ivHome, ivPerfil;
    Ingresso ingresso;
    TextView tvNomePartidaIngressoDetalhado,tvDataPartidaIngressoDetalhado,tvhorarioPartidaIngressoDetalhado,
            tvSetorPartidaIngressoDetalhado,tvCadeiraPartidaIngressoDetalhado,tvValorPartidaIngressoDetalhado;

    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresso_detalhado);
        ivCarrinho = findViewById(R.id.ivCarrinho);
        ivHome = findViewById(R.id.ivHome);
        ivPerfil = findViewById(R.id.ivperfil);
        tvNomePartidaIngressoDetalhado = findViewById(R.id.tvNomePartidaIngressoDetalhado);
        tvDataPartidaIngressoDetalhado = findViewById(R.id.tvDataPartidaIngressoDetalhado);
        tvhorarioPartidaIngressoDetalhado = findViewById(R.id.tvhorarioPartidaIngressoDetalhado);
        tvSetorPartidaIngressoDetalhado = findViewById(R.id.tvSetorPartidaIngressoDetalhado);
        tvCadeiraPartidaIngressoDetalhado = findViewById(R.id.tvCadeiraPartidaIngressoDetalhado);
        tvValorPartidaIngressoDetalhado = findViewById(R.id.tvValorPartidaIngressoDetalhado);

        informacoesApp = (InformacoesApp) getApplicationContext();

        ivCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(IngressoDetalhado.this, Carrinho.class);
                startActivity(it);
            }
        });

        ivPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(IngressoDetalhado.this, Perfil.class);
                startActivity(it);
            }
        });

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(IngressoDetalhado.this, home.class);
                startActivity(it);
            }
        });


        Intent it = getIntent();
        if (it != null){
            ingresso = (Ingresso) it.getSerializableExtra("Ingresso");
            tvNomePartidaIngressoDetalhado.setText(ingresso.getPartida().getNomepartida());
            tvDataPartidaIngressoDetalhado.setText(ingresso.getPartida().DataPartidaString());
            tvhorarioPartidaIngressoDetalhado.setText(ingresso.getPartida().HorarioPartidaString());
            tvSetorPartidaIngressoDetalhado.setText(String.valueOf(ingresso.getCadeira().getSetor()));
            tvCadeiraPartidaIngressoDetalhado.setText(String.valueOf(ingresso.getCadeira().getNumcadeira()));
            tvValorPartidaIngressoDetalhado.setText(String.valueOf(ingresso.getCadeira().getSetor().getPrecosetor()));
        }

    }
}