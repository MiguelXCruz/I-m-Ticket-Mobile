package com.example.imticket;

import android.app.Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import modelDominio.Ingresso;
import modelDominio.Usuario;

public class InformacoesApp extends Application {


    public Socket socket;
    public ObjectInputStream in;
    public ObjectOutputStream out;

    public Usuario user;
    public ArrayList<Ingresso> lstCarrinho;

    @Override
    public void onCreate() {

        super.onCreate();
        lstCarrinho = new ArrayList<>();
    }



}
