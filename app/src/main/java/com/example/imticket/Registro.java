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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import controller.ConexaoController;
import criptografia.Criptografia;
import modelDominio.Usuario;

public class Registro extends AppCompatActivity {
    EditText etEmail, etSenha, etNome;
    Button btRegistrar;
    TextView tvLogarsse;

    InformacoesApp informacoesApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etEmail = findViewById(R.id.etemail);
        etSenha = findViewById(R.id.etSenha);
        etNome = findViewById(R.id.etnome);
        btRegistrar = findViewById(R.id.btRegistrar);
        tvLogarsse = findViewById(R.id.tvLogarsse);


        informacoesApp = (InformacoesApp) getApplicationContext();



        tvLogarsse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Registro.this, Login.class);
                startActivity(it);
            }

        });

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etEmail.getText().toString().equals("")) {
                    if (!etNome.getText().toString().equals("")) {
                        if (!etSenha.getText().toString().equals("")) {

                            String emailusuario = etEmail.getText().toString();
                            String nomeusuario = etNome.getText().toString();
                            String senhausuario = Criptografia.criptografar(etSenha.getText().toString());
                            Date date = new Date();

                            Usuario usuario= new Usuario("C", nomeusuario, emailusuario, senhausuario, date);


                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    ConexaoController ccont = new ConexaoController(informacoesApp);

                                    Boolean ok = ccont.usuarioInserir(usuario);

                                    if(ok){

                                        Intent it = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(it);

                                    }else{

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                                Toast.makeText(Registro.this, "Erro Inesperado ao se Cadastrar", Toast.LENGTH_SHORT).show();

                                            }
                                        });


                                    }


                                }
                            };
                            thread.start();




                        } else {
                            etSenha.setError("Preencha!");
                            etSenha.requestFocus();
                        }
                    } else {
                        etNome.setError("Preencha!");
                        etNome.requestFocus();
                    }
                } else {
                    etEmail.setError("Preencha!");
                    etEmail.requestFocus();
                }
            }
        });


    }
}