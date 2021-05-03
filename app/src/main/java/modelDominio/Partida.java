package modelDominio;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Partida implements Serializable{


    private static final long serialVersionUID = 123456789L;
    // ATRIBUTOS

    private int idpartida;
    private String nomepartida;
    private Date datapartida;
    private Time horariopartida;
    private byte[] imagem;
    private String descricaopartida;


    // CONSTRUTORES

    // Cadastro
    public Partida(String nomepartida, Date datapartida, Time horariopartida, byte[] imagem, String descricaopartida) {
        this.nomepartida = nomepartida;
        this.datapartida = datapartida;
        this.horariopartida = horariopartida;
        this.imagem = imagem;
        this.descricaopartida = descricaopartida;
    }

    // Consulta, Alteração, Remoção
    public Partida(int idpartida, String nomepartida, Date datapartida, Time horariopartida, byte[] imagem, String descricaopartida) {
        this.idpartida = idpartida;
        this.nomepartida = nomepartida;
        this.datapartida = datapartida;
        this.horariopartida = horariopartida;
        this.imagem = imagem;
        this.descricaopartida = descricaopartida;
    }

    //  Existe pois é necessário no cadastro de Ingresso
    public Partida(int idpartida) {
        this.idpartida = idpartida;
    }


    // toString
    @Override
    public String toString() {
        return "Partida{" + "idpartida=" + idpartida + ", nomepartida=" + nomepartida + ", datapartida=" + datapartida + ", horariopartida=" + horariopartida + ", imagem=" + imagem + ", descricaopartida=" + descricaopartida + '}';
    }


    // GETTERS AND SETTERS

    public int getIdpartida() {
        return idpartida;
    }

    public void setIdpartida(int idpartida) {
        this.idpartida = idpartida;
    }

    public String getNomepartida() {
        return nomepartida;
    }

    public void setNomepartida(String nomepartida) {
        this.nomepartida = nomepartida;
    }

    public Date getDatapartida() {
        return datapartida;
    }

    public void setDatapartida(Date datapartida) {
        this.datapartida = datapartida;
    }

    public Time getHorariopartida() {
        return horariopartida;
    }

    public void setHorariopartida(Time horariopartida) {
        this.horariopartida = horariopartida;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public String getDescricaopartida() {
        return descricaopartida;
    }

    public void setDescricaopartida(String descricaopartida) {
        this.descricaopartida = descricaopartida;
    }


    // FORMATAÇÕES

    // Date --> String
    public String DataPartidaString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(datapartida);
    }

    // Time --> String
    public String HorarioPartidaString(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); // Hora, minuto, segundo.
        return sdf.format(horariopartida);
    }

}
