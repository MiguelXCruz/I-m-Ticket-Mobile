package modelDominio;

import java.io.Serializable;

public class DadosCompra implements Serializable {

    private static final long serialVersionUID = 123456789L;

    // ATRIBUTOS

    private int iddadoscompra;
    private Usuario cliente;
    private String nomecompleto;
    private String cpf;
    private String celular;
    private String nomecartao;
    private String tipocartao;
    private String numerocartao;
    private String cep;
    private String rua;
    private int numcasa;
    private String bairro;
    private String complemento;
    private String uf;


    // CONSTRUTORES

    public DadosCompra(int idusuario, String nomecompleto, String cpf, String celular, String nomecartao, String tipocartao,
                       String numerocartao, String cep, String rua, int numcasa, String bairro, String complemento, String uf) {

        Usuario cliente  = new Usuario(idusuario);
        this.cliente = cliente;
        this.nomecompleto = nomecompleto;
        this.cpf = cpf;
        this.celular = celular;
        this.nomecartao = nomecartao;
        this.tipocartao = tipocartao;
        this.numerocartao = numerocartao;
        this.cep = cep;
        this.rua = rua;
        this.numcasa = numcasa;
        this.bairro = bairro;
        this.complemento = complemento;
        this.uf = uf;
    }

    public DadosCompra(int iddadoscompra) {
        this.iddadoscompra = iddadoscompra;
    }

    // GETTERS AND SETTERS

    public int getIddadoscompra() {
        return iddadoscompra;
    }

    public void setIddadoscompra(int iddadoscompra) {
        this.iddadoscompra = iddadoscompra;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public String getNomecompleto() {
        return nomecompleto;
    }

    public void setNomecompleto(String nomecompleto) {
        this.nomecompleto = nomecompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNomecartao() {
        return nomecartao;
    }

    public void setNomecartao(String nomecartao) {
        this.nomecartao = nomecartao;
    }

    public String getTipocartao() {
        return tipocartao;
    }

    public void setTipocartao(String tipocartao) {
        this.tipocartao = tipocartao;
    }

    public String getNumerocartao() {
        return numerocartao;
    }

    public void setNumerocartao(String numerocartao) {
        this.numerocartao = numerocartao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumcasa() {
        return numcasa;
    }

    public void setNumcasa(int numcasa) {
        this.numcasa = numcasa;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String tipoCartaoLiteral() {
        String retorno;

        if (this.tipocartao.equals("C")) {
            retorno = "Crédito";
        } else {
            retorno = "Débito";
        }

        return retorno;
    }

    @Override
    public String toString() {
        return "DadosCompra{" +
                "iddadoscompra=" + iddadoscompra +
                ", cliente=" + cliente +
                ", nomecompleto='" + nomecompleto + '\'' +
                ", cpf='" + cpf + '\'' +
                ", celular='" + celular + '\'' +
                ", nomecartao='" + nomecartao + '\'' +
                ", tipocartao='" + tipocartao + '\'' +
                ", numerocartao='" + numerocartao + '\'' +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", numcasa=" + numcasa +
                ", bairro='" + bairro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
