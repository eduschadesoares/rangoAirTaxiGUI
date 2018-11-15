package model;

public class Heliporto {

    private String codigoHeliporto;
    private int kmHeliporto;
    private String cidade;

    public Heliporto(String codigoHeliporto, int kmHeliporto, String cidade) {
        this.codigoHeliporto = codigoHeliporto;
        this.kmHeliporto = kmHeliporto;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Heliporto{" + "codigoHeliporto=" + codigoHeliporto + ", kmHeliporto=" + kmHeliporto + ", cidade=" + cidade + '}';
    }

}
