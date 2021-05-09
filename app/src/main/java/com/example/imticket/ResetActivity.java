package com.example.imticket;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.net.ConnectivityManager;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import adapter.PartidaAdapter;
import controller.ConexaoController;
import criptografia.Criptografia;

import static javax.mail.internet.InternetAddress.*;

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

        //inicializando as propriedades

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");




        //inicializando sessão

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("miguelxavier075@gmail.com","84473351m");

            }
        });



        try {
            //inicializando conteudo do email

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress("miguelxavier075@gmail.com"));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            message.setSubject("Solicitação de senha");

            message.setText("Sua senha é: " + senha);


            //enviando o email

            new SendMail().execute(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private class SendMail extends AsyncTask <Message,String,String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //create and show progress dialog
            progressDialog = ProgressDialog.show(ResetActivity.this, "Por favor Aguarde", "Enviando Email...",
                    true, false);

        }

        @Override
        protected String doInBackground(Message... messages) {

            try {
                // quando sucesso
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                // quando falho
                e.printStackTrace();
                return "Error";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Dimiss progress dialog

            progressDialog.dismiss();
            if (s.equals("Sucess")){
                //quando sucesso

                // iniciando alertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ResetActivity.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324' >Sucesso</font>"));
                builder.setMessage("Email enviado com sucesso!!");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //fechando a tela
                        dialog.dismiss();
                        //limpando os ET's
                        etemailRS.setText("");
                    }
                });
                //mostrando o AlertDialog
                builder.show();
            }else{
                //quando erro
                Toast.makeText(getApplicationContext(),"Algo deu errado",Toast.LENGTH_SHORT);
            }
        }
    }
}