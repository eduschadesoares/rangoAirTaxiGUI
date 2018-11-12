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

    Map<String, Boolean> agendaServicoTemp = new LinkedHashMap<String, Boolean>();

    {
        agendaServicoTemp.put("06:00", true);
        agendaServicoTemp.put("06:30", true);
        agendaServicoTemp.put("07:00", true);
        agendaServicoTemp.put("07:30", true);
        agendaServicoTemp.put("08:00", true);
        agendaServicoTemp.put("08:30", true);
        agendaServicoTemp.put("09:00", true);
        agendaServicoTemp.put("09:30", true);
        agendaServicoTemp.put("06:30", true);
        agendaServicoTemp.put("07:00", true);
        agendaServicoTemp.put("07:30", true);
        agendaServicoTemp.put("08:00", true);
        agendaServicoTemp.put("08:30", true);
        agendaServicoTemp.put("09:00", true);
        agendaServicoTemp.put("09:30", true);
        agendaServicoTemp.put("10:00", true);
        agendaServicoTemp.put("10:30", true);
        agendaServicoTemp.put("11:00", true);
        agendaServicoTemp.put("11:30", true);
        agendaServicoTemp.put("12:00", true);
        agendaServicoTemp.put("12:30", true);
        agendaServicoTemp.put("13:00", true);
        agendaServicoTemp.put("13:30", true);
        agendaServicoTemp.put("14:00", true);
        agendaServicoTemp.put("14:30", true);
    }

    Map<String, Boolean> agendaManutencaoTemp = new LinkedHashMap<String, Boolean>();

    {
        agendaManutencaoTemp.put("06:00", true);
        agendaManutencaoTemp.put("06:30", true);
        agendaManutencaoTemp.put("07:00", true);
        agendaManutencaoTemp.put("07:30", true);
        agendaManutencaoTemp.put("08:00", true);
        agendaManutencaoTemp.put("08:30", true);
        agendaManutencaoTemp.put("09:00", true);
        agendaManutencaoTemp.put("09:30", true);
        agendaManutencaoTemp.put("06:30", true);
        agendaManutencaoTemp.put("07:00", true);
        agendaManutencaoTemp.put("07:30", true);
        agendaManutencaoTemp.put("08:00", true);
        agendaManutencaoTemp.put("08:30", true);
        agendaManutencaoTemp.put("09:00", true);
        agendaManutencaoTemp.put("09:30", true);
        agendaManutencaoTemp.put("10:00", true);
        agendaManutencaoTemp.put("10:30", true);
        agendaManutencaoTemp.put("11:00", true);
        agendaManutencaoTemp.put("11:30", true);
        agendaManutencaoTemp.put("12:00", true);
        agendaManutencaoTemp.put("12:30", true);
        agendaManutencaoTemp.put("13:00", true);
        agendaManutencaoTemp.put("13:30", true);
        agendaManutencaoTemp.put("14:00", true);
        agendaManutencaoTemp.put("14:30", true);
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
        this.segunda = new DataDia(agendaServicoTemp, agendaManutencaoTemp);
        this.terca = new DataDia(agendaServicoTemp, agendaManutencaoTemp);
        this.quarta = new DataDia(agendaServicoTemp, agendaManutencaoTemp);
        this.quinta = new DataDia(agendaServicoTemp, agendaManutencaoTemp);
        this.sexta = new DataDia(agendaServicoTemp, agendaManutencaoTemp);
        this.sabado = new DataDia(agendaServicoTemp, agendaManutencaoTemp);
        this.domingo = new DataDia(agendaServicoTemp, agendaManutencaoTemp);
    }

}
