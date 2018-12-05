package model;

import java.io.Serializable;

public class DataMes implements Serializable {

    private int numMes = 1;
    private int numAno = 1;
    private DataSemana semana1;
    private DataSemana semana2;
    private DataSemana semana3;
    private DataSemana semana4;

    public int getNumMes() {
        return numMes;
    }

    public void setNumMes(int numMes) {
        this.numMes = numMes;
    }

    public DataSemana getSemana1() {
        return semana1;
    }

    public DataSemana getSemana2() {
        return semana2;
    }

    public DataSemana getSemana3() {
        return semana3;
    }

    public DataSemana getSemana4() {
        return semana4;
    }

    public DataMes() {
        this.semana1 = new DataSemana("Semana 1");
        this.semana2 = new DataSemana("Semana 2");
        this.semana3 = new DataSemana("Semana 3");
        this.semana4 = new DataSemana("Semana 4");
    }

    public int getNumAno() {
        return numAno;
    }

    public void setNumAno(int numAno) {
        this.numAno = numAno;
    }

}
