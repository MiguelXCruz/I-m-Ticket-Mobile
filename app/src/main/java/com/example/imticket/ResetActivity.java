package com.example.imticket;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Message;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.net.ConnectivityManager;
import java.net.PasswordAuthentication;
import java.util.Properties;

import adapter.PartidaAdapter;
import controller.ConexaoController;
import criptografia.Criptografia;

public class ResetActivity extends AppCompatActivity {

    InformacoesApp informacoesApp;
    EditText etemailRS;
    Button btRecuperarRS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        etemailRS = findViewById(R.id.etemailRS);
        btRecuperarRS = findViewById(R.id.btRecuperarRS);

        informacoesApp = (InformacoesApp) getApplicationContext();


        btRecuperarRS.setOnClickListener(new View.OnClickListener() {

            String senha = " ";

            @Override
            public void onClick(View v) {
                if(!etemailRS.getText().toString().equals("")){

                    String email = etemailRS.getText().toString();

                    Thread t = new Thread(){
                        @Override
                        public void run() {
                            ConexaoController ccont = new ConexaoController(informacoesApp);

                            String senha = Criptografia.descriptografar(ccont.usuarioRecuperarSenha(email));


                            if(!senha.equals(" ")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        enviaEmail(senha, email);
                                        Toast.makeText(ResetActivity.this, "senha: " + senha, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ResetActivity.this, "se fudeu", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }



                        }
                    };

                    t.start();



                }else{
                    etemailRS.setError("Preencha!!");
                    etemailRS.requestFocus();
                }
            }
        });

    }

    private void enviaEmail(String senha, String email){

    /*    //chamando telas para enviar o email
        Intent it = new Intent(Intent.ACTION_SEND);
        it.putExtra(it.EXTRA_EMAIL, email);
        it.putExtra(it.EXTRA_SUBJECT, senha);

        it.setType("Messege/rfc822");

        startActivity(it); */

        /*
            final String subject = "assunto do email";
            final String body = "corpo do email do " + email;


            new Thread(new Runnable(){
                @Override
                public void run() {
                    Mail m = new Mail();

                    String[] toArr = {email};
                    m.setTo(toArr);

                    //m.setFrom("seunome@seuemail.com.br"); //caso queira enviar em nome de outro
                    m.setSubject(subject);
                    m.setBody(body);

                    try {
                        //m.addAttachment("pathDoAnexo");//anexo opcional
                        m.send();
                    }
                    catch(RuntimeException rex){ }//erro ignorado
                    catch(Exception e) {
                        e.printStackTrace();
                        System.exit(0);
                    }

                    Toast.makeText(getApplicationContext(), "Email enviado!", Toast.LENGTH_SHORT).show();
                }
            }).start();
        }
    */
    }
}