package model;

import java.io.Serializable;

public class Piloto implements Serializable {

    private String id;
    private String nomePiloto;
    private String statusPiloto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomePiloto() {
        return nomePiloto;
    }

    public void setNomePiloto(String nomePiloto) {
        this.nomePiloto = nomePiloto;
    }

    public String getStatusPiloto() {
        return statusPiloto;
    }

    public void setStatusPiloto(String statusPiloto) {
        this.statusPiloto = statusPiloto;
    }

    public Piloto(String id, String nomePiloto, String statusPiloto) {
        this.id = id;
        this.nomePiloto = nomePiloto;
        this.statusPiloto = statusPiloto;
    }

    @Override
    public String toString() {
        return "Piloto{" + "id=" + id + ", nomePiloto=" + nomePiloto + ", statusPiloto=" + statusPiloto + '}';
    }

}
