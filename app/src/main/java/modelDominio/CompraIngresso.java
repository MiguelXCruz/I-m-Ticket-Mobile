package modelDominio;


import java.io.Serializable;
import java.util.Date;


public class CompraIngresso implements Serializable {

    private static final long serialVersionUID = 123456789L;



    // ATRIBUTOS


    // idcompraingresso
    private Ingresso ingresso;
    private DadosCompra dadoscompra;
    private Date datacompraingresso;


    // CONSTRUTORES


    public CompraIngresso(int idingresso, int iddadoscompra, Date datacompraingresso) {
        Ingresso ingresso = new Ingresso(idingresso);
        this.ingresso = ingresso;
        DadosCompra dadoscompra = new DadosCompra(iddadoscompra);
        this.dadoscompra = dadoscompra;
        this.datacompraingresso = datacompraingresso;
    }

    // GETTERS AND SETTERS

    public Ingresso getIngresso() {
        return ingresso;
    }

    public void setIngresso(Ingresso ingresso) {
        this.ingresso = ingresso;
    }

    public DadosCompra getDadoscompra() {
        return dadoscompra;
    }

    public void setDadoscompra(DadosCompra dadoscompra) {
        this.dadoscompra = dadoscompra;
    }

    public Date getDatacompraingresso() {
        return datacompraingresso;
    }

    public void setDatacompraingresso(Date datacompraingresso) {
        this.datacompraingresso = datacompraingresso;
    }


    // toString

    @Override
    public String toString() {
        return "CompraIngresso{" + "ingresso=" + ingresso + ", dadoscompra=" + dadoscompra + ", datacompraingresso=" + datacompraingresso + '}';
    }







}
