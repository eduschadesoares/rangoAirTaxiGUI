package model;

import java.util.Hashtable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataDia {

    public Map<String, Boolean> agendaServico;
    public Map<String, Boolean> agendaManutencao;

    public DataDia(Map<String, Boolean> agendaServico, Map<String, Boolean> agendaManutencao) {
        this.agendaServico = agendaServico;
        this.agendaManutencao = agendaManutencao;
    }

}
