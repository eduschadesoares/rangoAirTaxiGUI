package model;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataDia implements Serializable {

    public String nomeDia;
    public Map<String, Boolean> agendaServico;
    public Map<String, Boolean> agendaManutencao;

    public String getNomeDia() {
        return nomeDia;
    }

    public void setNomeDia(String nomeDia) {
        this.nomeDia = nomeDia;
    }
    

    public DataDia(String nomeDia, Map<String, Boolean> agendaServico, Map<String, Boolean> agendaManutencao) {
        this.nomeDia = nomeDia;
        this.agendaServico = agendaServico;
        this.agendaManutencao = agendaManutencao;
    }

    @Override
    public String toString() {
        return nomeDia;
    }
    
    

}
