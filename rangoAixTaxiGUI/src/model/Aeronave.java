package model;

import java.io.Serializable;

public class Aeronave implements Serializable {

    private Heliporto heliporto;
    private ModeloAeronave modelo;
    private DataMes mes;

    private String serial;
    private int idadeAeronave;
    private float proxRevisao;
    private int depreciacao;
    //Horas de voo da aeronava, incremente conforme as reservas são feitas
    private float horaTotalVoo;
    private boolean statusAeronave;

    public Aeronave() {
    }

    public Aeronave(ModeloAeronave modelo, DataMes mes, String serial, int idadeAeronave, float proxRevisao, float horaTotalVoo, boolean statusAeronave, Heliporto heliporto) {
        this.modelo = modelo;
        this.mes = mes;
        this.serial = serial;
        this.idadeAeronave = idadeAeronave;
        this.proxRevisao = proxRevisao;
        this.horaTotalVoo = horaTotalVoo;
        this.statusAeronave = statusAeronave;
        this.heliporto = heliporto;
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

    public int getDepreciacao() {
        return depreciacao;
    }

    public void setDepreciacao(int depreciacao) {
        this.depreciacao = depreciacao;
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
        this.proxRevisao = proxRevisao;
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

    public String getInfoGeral() {
        return "Aeronave modelo " + modelo + " está no Heliporto de " + heliporto;
    }

    public String getShowStatus() {
        if (statusAeronave) {
            return "Disponível";
        } else {
            return "Manutenção";
        }
    }
    
    @Override
    public String toString() {
        return modelo + " - " + serial;
    }

}
