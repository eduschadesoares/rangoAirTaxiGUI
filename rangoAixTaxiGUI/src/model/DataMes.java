package model;

import java.io.Serializable;

public class DataMes implements Serializable {

    private DataSemana semana1;
    private DataSemana semana2;
    private DataSemana semana3;
    private DataSemana semana4;

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
        this.semana1 = new DataSemana();
        this.semana2 = new DataSemana();
        this.semana3 = new DataSemana();
        this.semana4 = new DataSemana();
    }
}
