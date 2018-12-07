package model;

import java.io.Serializable;

public class Aeronave implements Serializable {

    private Heliporto heliporto;
    private ModeloAeronave modelo;
    private DataMes mes;

    private String serial;
    private int idadeAeronave;
    //Horas de voo da aeronava, incremente conforme as reservas são feitas
    private float proxRevisao;
    private float horaTotalVoo;
    private boolean statusAeronave;

    public Aeronave() {
    }

    public Aeronave(ModeloAeronave modelo, DataMes mes, String serial, int idadeAeronave, float horaTotalVoo, boolean statusAeronave, Heliporto heliporto) {
        this.modelo = modelo;
        this.mes = mes;
        this.serial = serial;
        this.idadeAeronave = idadeAeronave;
        this.horaTotalVoo = horaTotalVoo;
        this.statusAeronave = statusAeronave;
        this.heliporto = heliporto;
        setHorasVooDisponiveis();
    }

    public Heliporto getHeliporto() {
        return heliporto;
    }

    public void setHeliporto(Heliporto heliporto) {
        this.heliporto = heliporto;
    }

    public ModeloAeronave getModelo() {
        return modelo;
    }

    public void setModelo(ModeloAeronave modelo) {
        this.modelo = modelo;
    }

    public DataMes getMes() {
        return mes;
    }

    public void setMes(DataMes mes) {
        this.mes = mes;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getIdadeAeronave() {
        return idadeAeronave;
    }

    public void setIdadeAeronave(int idadeAeronave) {
        this.idadeAeronave = idadeAeronave;
    }

    public float getProxRevisao() {
        return proxRevisao;
    }

    public void setProxRevisao(float proxRevisao) {
        this.proxRevisao = getProxRevisao() - (proxRevisao / 2);
    }

    public float getHoraTotalVoo() {
        return horaTotalVoo;
    }

    public void setHoraTotalVoo(float horaTotalVoo) {
        this.horaTotalVoo = horaTotalVoo + this.horaTotalVoo;
    }

    public boolean getStatusAeronave() {
        return statusAeronave;
    }

    public void setStatusAeronave(boolean statusAeronave) {
        this.statusAeronave = statusAeronave;
    }

    public void incrementaIdade() {
        this.idadeAeronave = getIdadeAeronave() + 1;
    }

    public String getInfoGeral() {
        return "Aeronave modelo " + modelo + " está no Heliporto de " + heliporto;
    }

    public void setHorasVooDisponiveis() {
        System.out.println(getIdadeAeronave() * 0.1);
        this.proxRevisao = (float) (getModelo().getLimiteHorasManutencao() - (getIdadeAeronave() * 0.1));
        System.out.println(this.proxRevisao);
    }

    public String getShowStatus() {
        if (statusAeronave) {
            return "Disponível";
        } else {
            return "Manutenção";
        }
    }

    public boolean podeViajar(int tempoDeVooIdaVolta) {
        System.out.println("Horas necessarias" + " " + tempoDeVooIdaVolta / 2);
        System.out.println("Tempo disponivel" + " " + (getProxRevisao() + (getProxRevisao() * 0.75)));

        return (tempoDeVooIdaVolta / 2) < (getProxRevisao() + (getProxRevisao() * 0.75));
    }

    @Override
    public String toString() {
        return modelo + " - " + serial;
    }

}
