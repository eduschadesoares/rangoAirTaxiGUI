package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataDia implements Serializable {

    public String nomeDia;
    public ArrayList<Boolean> agendaServico;
    public ArrayList<Boolean> agendaManutencao;

    public Map<String, Integer> tableHours = new LinkedHashMap<>();

    {
        tableHours.put("06:00", 0);
        tableHours.put("06:30", 1);
        tableHours.put("07:00", 2);
        tableHours.put("07:30", 3);
        tableHours.put("08:00", 4);
        tableHours.put("08:30", 5);
        tableHours.put("09:00", 6);
        tableHours.put("09:30", 7);
        tableHours.put("10:00", 8);
        tableHours.put("10:30", 9);
        tableHours.put("11:00", 10);
    }

    public String getNomeDia() {
        return nomeDia;
    }

    public void setNomeDia(String nomeDia) {
        this.nomeDia = nomeDia;
    }

    public DataDia(String nomeDia, ArrayList<Boolean> agendaServico, ArrayList<Boolean> agendaManutencao) {
        this.nomeDia = nomeDia;
        this.agendaServico = agendaServico;
        this.agendaManutencao = agendaManutencao;
    }

    @Override
    public String toString() {
        return nomeDia;
    }

}
