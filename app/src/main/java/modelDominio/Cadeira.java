package modelDominio;

import java.io.Serializable;

public class Cadeira implements Serializable {

    private static final long serialVersionUID = 123456789L;


    // ATRIBUTOS

    private int idcadeira;
    private Setor setor;
    private int numcadeira;



    // CONSTRUTORES

    // Inserir
    public Cadeira(int idsetor, int numcadeira) {
        Setor setor = new Setor(idsetor);
        this.setor = setor;
        this.numcadeira = numcadeira;
    }


    // Consulta Cadeira Gerada
    public Cadeira(int idcadeira, int idsetor, int numcadeira) {
        this.idcadeira = idcadeira;
        Setor setor = new Setor(idsetor);
        this.setor = setor;
        this.numcadeira = numcadeira;
    }


    // Consulta Ingresso
    public Cadeira(int idcadeira, int idsetor, int numsetor, float precosetor, int numcadeira) {
        this.idcadeira = idcadeira;
        Setor setor = new Setor(idsetor, numsetor, precosetor);
        this.setor = setor;
        this.numcadeira = numcadeira;
    }


    public Cadeira(int idcadeira) {
        this.idcadeira = idcadeira;
    }




    // toString
    @Override
    public String toString() {
        return "Cadeira{" + "idcadeira=" + idcadeira + ", setor=" + setor + ", numcadeira=" + numcadeira + '}';
    }


    // GETTERS AND SETTERS

    public int getIdcadeira() {
        return idcadeira;
    }

    public void setIdcadeira(int idcadeira) {
        this.idcadeira = idcadeira;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public int getNumcadeira() {
        return numcadeira;
    }

    public void setNumcadeira(int numcadeira) {
        this.numcadeira = numcadeira;
    }


}
