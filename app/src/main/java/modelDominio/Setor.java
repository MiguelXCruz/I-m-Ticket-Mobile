package modelDominio;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Setor implements Serializable {

    private static final long serialVersionUID = 123456789L;


    // ATRIBUTOS

    private int idsetor;
    private int numsetor;
    private float precosetor;


    // CONSTRUTORES

    // Inserir
    public Setor(int numsetor, float precosetor) {
        this.numsetor = numsetor;
        this.precosetor = precosetor;
    }

    // Consulta
    public Setor(int idsetor, int numsetor, float precosetor) {
        this.idsetor = idsetor;
        this.numsetor = numsetor;
        this.precosetor = precosetor;
    }

    public Setor(int idsetor, float precosetor, String vazia) {
        this.idsetor = idsetor;
        this.precosetor = precosetor;
    }





    // Class --> CADEIRA
    public Setor(int idsetor) {
        this.idsetor = idsetor;
    }




    // toString
    @Override
    public String toString() {
        return "Setor{" + "idsetor=" + idsetor + ", numsetor=" + numsetor + ", precosetor=" + precosetor + '}';
    }


    // GETTERS AND SETTERS

    public int getIdsetor() {
        return idsetor;
    }

    public void setIdsetor(int idsetor) {
        this.idsetor = idsetor;
    }

    public int getNumsetor() {
        return numsetor;
    }

    public void setNumsetor(int numsetor) {
        this.numsetor = numsetor;
    }

    public float getPrecosetor() {
        return precosetor;
    }

    public void setPrecosetor(float precosetor) {
        this.precosetor = precosetor;
    }

    // FORMATAÇÕES

    // Float --> String
    public String getValorString(){
        String pattern = "###,##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(precosetor);
    }

}
