package model;

import java.io.Serializable;

public class Heliporto implements Serializable {

    private String codigoHeliporto;
    private int kmHeliporto;
    private String nomeHeliporto;
    private String cidade;

    public Heliporto(String codigoHeliporto, int kmHeliporto, String nomeHeliporto, String cidade) {
        this.codigoHeliporto = codigoHeliporto;
        this.kmHeliporto = kmHeliporto;
        this.nomeHeliporto = nomeHeliporto;
        this.cidade = cidade;
    }

    public Heliporto() {
    }

    public String getCodigoHeliporto() {
        return codigoHeliporto;
    }

    public void setCodigoHeliporto(String codigoHeliporto) {
        this.codigoHeliporto = codigoHeliporto;
    }

    public int getKmHeliporto() {
        return kmHeliporto;
    }

    public void setKmHeliporto(int kmHeliporto) {
        this.kmHeliporto = kmHeliporto;
    }

    public String getNomeHeliporto() {
        return nomeHeliporto;
    }

    public void setNomeHeliporto(String nomeHeliporto) {
        this.nomeHeliporto = nomeHeliporto;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Heliporto{" + "codigoHeliporto=" + codigoHeliporto + ", kmHeliporto=" + kmHeliporto + ", nomeHeliporto=" + nomeHeliporto + ", cidade=" + cidade + '}';
    }

}
