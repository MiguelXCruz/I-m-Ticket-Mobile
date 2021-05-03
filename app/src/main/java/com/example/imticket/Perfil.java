package com.example.imticket;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Perfil extends AppCompatActivity {
    Button btAlterarDados, btMeusIngressos;
    ImageView ivCarrinho, ivHome, ivPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        btAlterarDados = findViewById(R.id.btAlterarDados);
        btMeusIngressos = findViewById(R.id.btMeusIngressos);
        ivCarrinho = findViewById(R.id.ivCarrinho);
        ivHome = findViewById(R.id.ivHome);
        ivPerfil = findViewById(R.id.ivperfil);

        btAlterarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Perfil.this, AlterarDados.class);
                startActivity(it);
            }

        });

        btMeusIngressos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Perfil.this, MeusIngressos.class);
                startActivity(it);
            }

        });


        ivCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Perfil.this, Carrinho.class);
                startActivity(it);
            }
        });

        ivPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Perfil.this, Perfil.class);
                startActivity(it);
            }
        });

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Perfil.this, home.class);
                startActivity(it);
            }
        });

    }
}