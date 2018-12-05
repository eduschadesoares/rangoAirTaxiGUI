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

    public ArrayList<Boolean> agendaServico = new ArrayList<>();

    {
        for (int i = 0; i < 48; i++) {
            agendaServico.add(true);
        }
    }

    public ArrayList<Boolean> agendaManutencao = new ArrayList<>();

    {
        for (int i = 0; i < 48; i++) {
            agendaManutencao.add(true);
        }
    }

    public Map<Integer, String> tableHours = new LinkedHashMap<>();

    {
        tableHours.put(0, "06:00");
        tableHours.put(1, "06:30");
        tableHours.put(2, "07:00");
        tableHours.put(3, "07:30");
        tableHours.put(4, "08:00");
        tableHours.put(5, "08:30");
        tableHours.put(6, "09:00");
        tableHours.put(7, "09:30");
        tableHours.put(8, "10:00");
        tableHours.put(9, "10:30");
        tableHours.put(10, "11:00");
        tableHours.put(11, "11:30");
        tableHours.put(12, "12:00");
        tableHours.put(13, "12:30");
        tableHours.put(14, "13:00");
        tableHours.put(15, "13:30");
        tableHours.put(16, "14:00");
        tableHours.put(17, "14:30");
        tableHours.put(18, "15:00");
        tableHours.put(19, "15:30");
        tableHours.put(20, "16:00");
        tableHours.put(21, "16:30");
        tableHours.put(22, "17:00");
        tableHours.put(23, "17:30");
        tableHours.put(24, "18:00");
        tableHours.put(25, "18:30");
        tableHours.put(26, "19:00");
        tableHours.put(27, "19:30");
        tableHours.put(28, "20:00");
        tableHours.put(29, "20:30");
        tableHours.put(30, "21:00");
        tableHours.put(31, "21:30");
        tableHours.put(32, "22:00");
        tableHours.put(33, "22:30");
        tableHours.put(34, "23:00");
        tableHours.put(35, "23:30");
        tableHours.put(36, "00:00");
        tableHours.put(37, "00:30");
        tableHours.put(38, "01:00");
        tableHours.put(39, "01:30");
        tableHours.put(40, "02:00");
        tableHours.put(41, "02:30");
        tableHours.put(42, "03:00");
        tableHours.put(43, "03:30");
        tableHours.put(44, "04:00");
        tableHours.put(45, "04:30");
        tableHours.put(46, "05:00");
        tableHours.put(47, "05:30");
    }

    public String getNomeDia() {
        return nomeDia;
    }

    public void setNomeDia(String nomeDia) {
        this.nomeDia = nomeDia;
    }

    public DataDia(String nomeDia) {
        this.nomeDia = nomeDia;
    }

    @Override
    public String toString() {
        return nomeDia;
    }

}
