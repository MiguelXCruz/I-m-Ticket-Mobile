package modelDominio;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Ingresso implements Serializable {

    private static final long serialVersionUID = 123456789L;

    // ATRIBUTOS

    private int idingresso;
    private Partida partida;
    private Cadeira cadeira;


    private String precosetores;
    private String idsetores;
    private int idpartidaedit;


    // CONSTRUTORES

    // Inserir
    public Ingresso(int idpartida, int idcadeira) {
        Partida partida = new Partida(idpartida);
        this.partida = partida;
        Cadeira cadeira = new Cadeira(idcadeira);
        this.cadeira = cadeira;
    }

    // Consulta
    public Ingresso(int idingresso, int idpartida, String nomepartida, Date datapartida, Time horariopartida, byte[] imagem, String descricaopartida,
                    int idcadeira, int idsetor, int numsetor, float precosetor, int numcadeira) {

        this.idingresso = idingresso;
        Partida partida = new Partida(idpartida, nomepartida, datapartida, horariopartida, imagem, descricaopartida);
        this.partida = partida;
        Cadeira cadeira = new Cadeira(idcadeira, idsetor, numsetor, precosetor, numcadeira);
        this.cadeira = cadeira;
    }


    // Consulta Avançada
    public Ingresso(int idpartida, String nomepartida, Date datapartida,
                    Time horariopartida, byte[] imagem, String descricaopartida,
                    String precosetores, String idsetores) {

        Partida partida = new Partida(idpartida, nomepartida, datapartida, horariopartida, imagem, descricaopartida);
        this.partida = partida;

        this.precosetores = precosetores;
        this.idsetores = idsetores;
    }

    public Ingresso(int idpartida, int idpartidaEdit, String vazia) {
        Partida partida = new Partida(idpartida);
        this.partida = partida;

        this.idpartidaedit = idpartidaEdit;
    }

    public Ingresso(int idingresso) {
        this.idingresso = idingresso;
    }

    // toString

    @Override
    public String toString() {
        return "Ingresso{" + "idingresso=" + idingresso + ", partida=" + partida + ", cadeira=" + cadeira + '}';
    }


    // GETTERS AND SETTERS

    public int getIdingresso() {
        return idingresso;
    }

    public void setIdingresso(int idingresso) {
        this.idingresso = idingresso;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Cadeira getCadeira() {
        return cadeira;
    }

    public void setCadeira(Cadeira cadeira) {
        this.cadeira = cadeira;
    }

    public String getPrecosetores() {
        return precosetores;
    }

    public void setPrecosetores(String precosetores) {
        this.precosetores = precosetores;
    }

    public String getIdsetores() {
        return idsetores;
    }

    public void setIdsetores(String idsetores) {
        this.idsetores = idsetores;
    }

    public int getIdpartidaedit() {
        return idpartidaedit;
    }

    public void setIdpartidaedit(int idpartidaedit) {
        this.idpartidaedit = idpartidaedit;
    }


}
