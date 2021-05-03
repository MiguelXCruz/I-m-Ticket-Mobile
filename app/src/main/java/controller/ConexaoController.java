package controller;

import com.example.imticket.InformacoesApp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


import modelDominio.CompraIngresso;
import modelDominio.DadosCompra;
import modelDominio.Ingresso;
import modelDominio.Partida;
import modelDominio.Usuario;

public class ConexaoController {


    InformacoesApp informacoesApp;


    public void Conectar(){
        try{
            informacoesApp.socket = new Socket("10.0.2.2", 12345);
            informacoesApp.out = new ObjectOutputStream(informacoesApp.socket.getOutputStream());
            informacoesApp.in = new ObjectInputStream(informacoesApp.socket.getInputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ConexaoController(InformacoesApp informacoesApp) {
        this.informacoesApp = informacoesApp;
    }

    public Usuario efetuarLogin(Usuario usuario, String tipoUser){
        String msg;
        try{
            informacoesApp.out.writeObject("UsuarioLogin");
            msg = (String) informacoesApp.in.readObject(); // lendo ok

            informacoesApp.out.writeObject(usuario); // escrevendo o filtro

            informacoesApp.out.writeObject(tipoUser);

            Usuario usrselecionado = (Usuario) informacoesApp.in.readObject();

            return usrselecionado;
        }catch(Exception ex){

            ex.printStackTrace();
            return null;
        }
    }

    public Boolean usuarioInserir(Usuario usuario){
        String msg = "";
        try{
            informacoesApp.out.writeObject("UsuarioInserir");
            msg = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(usuario);
            msg = (String) informacoesApp.in.readObject();

            if (msg.equals("ok")){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public Boolean usuarioAlterar(Usuario usr){
        String msg = "";
        try{
            informacoesApp.out.writeObject("UsuarioAlterar");
            msg = (String) informacoesApp.in.readObject(); // lendo ok
            informacoesApp.out.writeObject(usr); // escrevendo o usuário
            msg = (String) informacoesApp.in.readObject(); // lendo ok
            if (msg.equals("ok")){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }


    public ArrayList<Partida> partidaIngressosDisponiveisLista() {
        String msg;
        try {
            informacoesApp.out.writeObject("PartidaIngressosDisponiveisLista");
            msg = (String) informacoesApp.in.readObject(); // lendo ok


            ArrayList<Partida> lstPartidas = (ArrayList<Partida>) informacoesApp.in.readObject();
            return lstPartidas;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Ingresso> ingressosDisponiveisSetorLista(int idPartida, int numsetor) {
        String msg;
        try {
            informacoesApp.out.writeObject("IngressosDisponiveisSetorLista");
            msg = (String) informacoesApp.in.readObject(); // lendo ok

            informacoesApp.out.writeObject(idPartida);
            informacoesApp.out.writeObject(numsetor);


            ArrayList<Ingresso> lstIngressos = (ArrayList<Ingresso>) informacoesApp.in.readObject();
            return lstIngressos;


        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public Boolean dadosCompraInserir(DadosCompra dadosCompra) {
        String msg;
        try {
            informacoesApp.out.writeObject("DadosCompraInserir");
            msg = (String) informacoesApp.in.readObject(); // lendo ok

            informacoesApp.out.writeObject(dadosCompra);

            msg = (String) informacoesApp.in.readObject(); // lendo ok

            if (msg.equals("ok")){
                return true;
            }else{
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public DadosCompra dadosCompraCliente(int idCliente) {
        String msg;
        try {
            informacoesApp.out.writeObject("DadosCompraCliente");
            msg = (String) informacoesApp.in.readObject(); // lendo ok

            informacoesApp.out.writeObject(idCliente);

            DadosCompra dadosCompra = (DadosCompra) informacoesApp.in.readObject();

            return dadosCompra;


        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Boolean compraIngressoInserir(CompraIngresso compraingresso) {
        String msg;
        try {
            informacoesApp.out.writeObject("CompraIngressoInserir");
            msg = (String) informacoesApp.in.readObject(); // lendo ok

            informacoesApp.out.writeObject(compraingresso);

            msg = (String) informacoesApp.in.readObject(); // lendo ok

            if (msg.equals("ok")){
                return true;
            }else{
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String usuarioRecuperarSenha(String emailCliente){
        String msg;
        try {
            informacoesApp.out.writeObject("UsuarioRecuperarSenha");
            msg = (String) informacoesApp.in.readObject(); // lendo ok

            informacoesApp.out.writeObject(emailCliente);

            msg = (String) informacoesApp.in.readObject(); // lendo ok

           return msg;

        } catch (Exception ex) {
            ex.printStackTrace();
            return " ";
        }

    }

    public ArrayList<Ingresso> usuarioMeusIngressos(int idCliente){
        String msg;
        try {
            informacoesApp.out.writeObject("IngressosHistoricoClienteLista");
            msg = (String) informacoesApp.in.readObject(); // lendo ok

            informacoesApp.out.writeObject(idCliente);

            ArrayList<Ingresso> lstIngressos = (ArrayList<Ingresso>) informacoesApp.in.readObject();

            return lstIngressos;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }







    public void fim() {
        String msg;
        try {
            informacoesApp.out.writeObject("fim");
            informacoesApp.in.close();
            informacoesApp.out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
