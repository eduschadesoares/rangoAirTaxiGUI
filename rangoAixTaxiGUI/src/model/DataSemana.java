package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataSemana implements Serializable {

    private String nomeSemana;
    private DataDia segunda;
    private DataDia terca;
    private DataDia quarta;
    private DataDia quinta;
    private DataDia sexta;
    private DataDia sabado;
    private DataDia domingo;

    public DataDia getSegunda() {
        return segunda;
    }

    public DataDia getTerca() {
        return terca;
    }

    public DataDia getQuarta() {
        return quarta;
    }

    public DataDia getQuinta() {
        return quinta;
    }

    public DataDia getSexta() {
        return sexta;
    }

    public DataDia getSabado() {
        return sabado;
    }

    public DataDia getDomingo() {
        return domingo;
    }

    public String getNomeSemana() {
        return nomeSemana;
    }

    public void setNomeSemana(String nomeSemana) {
        this.nomeSemana = nomeSemana;
    }

    public DataSemana(String nomeSemana) {
        this.nomeSemana = nomeSemana;
        this.segunda = new DataDia("Segunda");
        this.terca = new DataDia("Terça");
        this.quarta = new DataDia("Quarta");
        this.quinta = new DataDia("Quinta");
        this.sexta = new DataDia("Sexta");
        this.sabado = new DataDia("Sábado");
        this.domingo = new DataDia("Domingo");
    }

    @Override
    public String toString() {
        return nomeSemana;
    }

}
