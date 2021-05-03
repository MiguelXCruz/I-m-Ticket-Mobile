package com.example.imticket;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.TimeZone;

import controller.ConexaoController;

public class MainActivity extends AppCompatActivity {
    Button btLogin, btRegistro;

    InformacoesApp informacoesApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        TimeZone.setDefault(TimeZone.getTimeZone("GMT-03:00"));


        setContentView(R.layout.activity_main);



        btLogin = findViewById(R.id.btLogin);
        btRegistro = findViewById(R.id.btRegistro);


        informacoesApp= (InformacoesApp) getApplicationContext();


        Thread t = new Thread(){
            @Override
            public void run() {
                ConexaoController ccont = new ConexaoController(informacoesApp);
                ccont.Conectar();
            }
        };
        t.start();




        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MainActivity.this, Login.class);
                startActivity(it);
            }

        });

        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, Registro.class);
                startActivity(it);
            }

        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

        }






}