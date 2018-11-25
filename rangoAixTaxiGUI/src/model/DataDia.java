package model;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataDia implements Serializable {

    public Map<String, Boolean> agendaServico;
    public Map<String, Boolean> agendaManutencao;

    public DataDia(Map<String, Boolean> agendaServico, Map<String, Boolean> agendaManutencao) {
        this.agendaServico = agendaServico;
        this.agendaManutencao = agendaManutencao;
    }

}
