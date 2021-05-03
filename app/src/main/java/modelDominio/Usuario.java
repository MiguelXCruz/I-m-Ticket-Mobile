package modelDominio;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Usuario implements Serializable{

    private static final long serialVersionUID = 123456789L;


    // ATRIBUTOS

    private int idusuario;
    private String tipousuario; // A- Administrador, F- Funcionário, C- Cliente
    private String nomeusuario;
    private String emailusuario;
    private String senhausuario;
    private Date datacadastro;

    // CONSTRUTORES


    // Cadastro
    public Usuario(String tipousuario, String nomeusuario, String emailusuario, String senhausuario, Date datacadastro) {
        this.tipousuario = tipousuario;
        this.nomeusuario = nomeusuario;
        this.emailusuario = emailusuario;
        this.senhausuario = senhausuario;
        this.datacadastro = datacadastro;
    }

    // Login
    public Usuario(String emailusuario, String senhausuario) {
        this.emailusuario = emailusuario;
        this.senhausuario = senhausuario;
    }



    // Consulta
    public Usuario(int idusuario, String tipousuario, String nomeusuario, String emailusuario, String senhausuario, Date datacadastro) {
        this.idusuario = idusuario;
        this.tipousuario = tipousuario;
        this.nomeusuario = nomeusuario;
        this.emailusuario = emailusuario;
        this.senhausuario = senhausuario;
        this.datacadastro = datacadastro;
    }


    // criar o DadosCompra
    public Usuario(int idusuario) {
        this.idusuario = idusuario;
    }


    // toString

    @Override
    public String toString() {
        return "Usuario{" + "idusuario=" + idusuario + ", tipousuario=" + tipousuario + ", nomeusuario=" + nomeusuario + ", emailusuario=" + emailusuario + ", senhausuario=" + senhausuario + ", datacadastro=" + datacadastro + '}';
    }


    // GETTERS AND SETTERS

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }

    public String getNomeusuario() {
        return nomeusuario;
    }

    public void setNomeusuario(String nomeusuario) {
        this.nomeusuario = nomeusuario;
    }

    public String getEmailusuario() {
        return emailusuario;
    }

    public void setEmailusuario(String emailusuario) {
        this.emailusuario = emailusuario;
    }

    public String getSenhausuario() {
        return senhausuario;
    }

    public void setSenhausuario(String senhausuario) {
        this.senhausuario = senhausuario;
    }

    public Date getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(Date datacadastro) {
        this.datacadastro = datacadastro;
    }


    // FORMATAÇÕES

    // Date --> String
    public String DataCadastroString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(datacadastro);
    }


}
