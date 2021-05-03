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
import android.widget.Toast;

import java.util.Date;

import controller.ConexaoController;
import criptografia.Criptografia;
import modelDominio.Usuario;

public class AlterarDados extends AppCompatActivity {
    EditText etAlteraremail,etAlterarnome,etAlterarSenha;
    Button btAlterarDados;
    ImageView ivCarrinho, ivHome, ivPerfil;

    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_dados);
        ivCarrinho = findViewById(R.id.ivCarrinho);
        ivHome = findViewById(R.id.ivHome);
        ivPerfil = findViewById(R.id.ivperfil);
        btAlterarDados = findViewById(R.id.btAlterarDados);
        etAlteraremail = findViewById(R.id.etAlteraremail);
        etAlterarnome = findViewById(R.id.etAlterarnome);
        etAlterarSenha = findViewById(R.id.etAlterarSenha);

        informacoesApp = (InformacoesApp) getApplicationContext();



        etAlteraremail.setText(informacoesApp.user.getEmailusuario());
        etAlterarnome.setText(informacoesApp.user.getNomeusuario());
        etAlterarSenha.setText(Criptografia.descriptografar(informacoesApp.user.getSenhausuario()));






        btAlterarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!etAlteraremail.getText().toString().equals("")) {
                    if (!etAlterarnome.getText().toString().equals("")) {
                        if (!etAlterarSenha.getText().toString().equals("")) {

                            String emailusuario = etAlteraremail.getText().toString();
                            String nomeusuario = etAlterarnome.getText().toString();
                            String senhausuario = Criptografia.criptografar(etAlterarSenha.getText().toString());


                            Usuario usuario= new Usuario(informacoesApp.user.getIdusuario(), informacoesApp.user.getTipousuario(),
                                    nomeusuario, emailusuario, senhausuario, informacoesApp.user.getDatacadastro());


                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    ConexaoController ccont = new ConexaoController(informacoesApp);

                                    Boolean ok = ccont.usuarioAlterar(usuario);

                                    if(ok){

                                        Intent it = new Intent(getApplicationContext(), home.class);
                                        startActivity(it);

                                        informacoesApp.user = usuario;


                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                                Toast.makeText(AlterarDados.this, "Dados Salvos com Sucesso", Toast.LENGTH_SHORT).show();


                                            }
                                        });



                                    }else{

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                                Toast.makeText(AlterarDados.this, "Erro Inesperado ao Alterar os Dados", Toast.LENGTH_SHORT).show();

                                            }
                                        });


                                    }


                                }
                            };
                            thread.start();




                        } else {
                            etAlterarSenha.setError("Preencha!");
                            etAlterarSenha.requestFocus();
                        }
                    } else {
                        etAlterarnome.setError("Preencha!");
                        etAlterarnome.requestFocus();
                    }
                } else {
                    etAlteraremail.setError("Preencha!");
                    etAlteraremail.requestFocus();
                }



            }
        });




        ivCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AlterarDados.this, Carrinho.class);
                startActivity(it);
            }
        });

        ivPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AlterarDados.this, Perfil.class);
                startActivity(it);
            }
        });

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AlterarDados.this, home.class);
                startActivity(it);
            }
        });

    }
}