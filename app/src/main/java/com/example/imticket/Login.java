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


import controller.ConexaoController;
import criptografia.Criptografia;
import modelDominio.Usuario;

public class Login extends AppCompatActivity {
    EditText etSenha, etEmail;
    Button btLogar;
    TextView tvRegistrarsse, tvEsqueceuasenha;

    InformacoesApp informacoesApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        
        etSenha = findViewById(R.id.etSenha);
        etEmail = findViewById(R.id.etemail);
        btLogar = findViewById(R.id.btlogar);
        tvRegistrarsse = findViewById(R.id.tvRegistresse);
        tvEsqueceuasenha = findViewById(R.id.tvEsqueceuasenha);

        informacoesApp = (InformacoesApp) getApplicationContext();

        btLogar.setOnClickListener(clickEntrar);
        tvRegistrarsse.setOnClickListener(clickRegistrar);

        etEmail.setText("laura.wermuth@yahoo.com");
        etSenha.setText("1234");

        tvEsqueceuasenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ResetActivity.class);
                startActivity(it);

            }
        });
    }


    View.OnClickListener clickEntrar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if (!etEmail.getText().toString().equals("")) {
                if (!etSenha.getText().toString().equals("")) {

                    String emailusuario = etEmail.getText().toString();
                    String senhausuario = Criptografia.criptografar(etSenha.getText().toString());

                    Usuario usuario= new Usuario(emailusuario, senhausuario);


                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            ConexaoController ccont = new ConexaoController(informacoesApp);

                            Usuario userselecionado = ccont.efetuarLogin(usuario, "Cliente");

                            // se algum usuario foi selecionado então pode abrir a tela principal
                            if (userselecionado != null) {
                                informacoesApp.user = userselecionado;
                                Intent it = new Intent(getApplicationContext(), home.class);
                                startActivity(it);
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Toast.makeText(Login.this, "email ou senha Inválidos", Toast.LENGTH_SHORT).show();

                                        etEmail.setText("");
                                        etSenha.setText("");
                                        etEmail.requestFocus();

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

                etEmail.setError("Preencha!");
                etEmail.requestFocus();
            }


        }

    };


        View.OnClickListener clickRegistrar = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Login.this, Registro.class);
                startActivity(it);
            }

        };




}
