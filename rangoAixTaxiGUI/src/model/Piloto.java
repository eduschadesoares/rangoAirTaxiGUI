package model;

import java.io.Serializable;

public class Piloto implements Serializable {

    private String idPiloto;
    private String nomePiloto;
    private String statusPiloto;

    public String getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(String idPiloto) {
        this.idPiloto = idPiloto;
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

    public Piloto(String idPiloto, String nomePiloto, String statusPiloto) {
        this.idPiloto = idPiloto;
        this.nomePiloto = nomePiloto;
        this.statusPiloto = statusPiloto;
    }

    public Piloto() {
    }

       
    @Override
    public String toString() {
        return "Piloto{" + "id=" + idPiloto + ", nomePiloto=" + nomePiloto + ", statusPiloto=" + statusPiloto + '}';
    }

}
