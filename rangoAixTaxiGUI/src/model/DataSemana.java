package model;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataSemana {

    private DataDia segunda;
    private DataDia terca;
    private DataDia quarta;
    private DataDia quinta;
    private DataDia sexta;
    private DataDia sabado;
    private DataDia domingo;

    Map<String, Boolean> horasDiaTemp = new LinkedHashMap<String, Boolean>();

    {
        horasDiaTemp.put("06:00", true);
        horasDiaTemp.put("06:30", true);
        horasDiaTemp.put("07:00", true);
        horasDiaTemp.put("07:30", true);
        horasDiaTemp.put("08:00", true);
        horasDiaTemp.put("08:30", true);
        horasDiaTemp.put("09:00", true);
        horasDiaTemp.put("09:30", true);
        horasDiaTemp.put("06:30", true);
        horasDiaTemp.put("07:00", true);
        horasDiaTemp.put("07:30", true);
        horasDiaTemp.put("08:00", true);
        horasDiaTemp.put("08:30", true);
        horasDiaTemp.put("09:00", true);
        horasDiaTemp.put("09:30", true);
        horasDiaTemp.put("10:00", true);
        horasDiaTemp.put("10:30", true);
        horasDiaTemp.put("11:00", true);
        horasDiaTemp.put("11:30", true);
        horasDiaTemp.put("12:00", true);
        horasDiaTemp.put("12:30", true);
        horasDiaTemp.put("13:00", true);
        horasDiaTemp.put("13:30", true);
        horasDiaTemp.put("14:00", true);
        horasDiaTemp.put("14:30", true);
    }

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

    public DataSemana() {
        this.segunda = new DataDia(horasDiaTemp);
        this.terca = new DataDia(horasDiaTemp);
        this.quarta = new DataDia(horasDiaTemp);
        this.quinta = new DataDia(horasDiaTemp);
        this.sexta = new DataDia(horasDiaTemp);
        this.sabado = new DataDia(horasDiaTemp);
        this.domingo = new DataDia(horasDiaTemp);
    }

}
