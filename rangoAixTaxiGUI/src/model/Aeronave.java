package model;

import java.io.Serializable;

public class Aeronave implements Serializable {

    private ModeloAeronave modelo;
    private DataMes mes;

    private String serial;
    private int anoAeronave;
    private float valorHoraVoo;
    private float proxRevisao;
    private float horaTotalVoo;
    private boolean statusAeronave;

    public Aeronave() {
    }

    public Aeronave(ModeloAeronave modelo, DataMes mes, String serial, int anoAeronave, float valorHoraVoo, float proxRevisao, float horaTotalVoo, boolean statusAeronave) {
        this.modelo = modelo;
        this.mes = mes;
        this.serial = serial;
        this.anoAeronave = anoAeronave;
        this.valorHoraVoo = valorHoraVoo;
        this.proxRevisao = proxRevisao;
        this.horaTotalVoo = horaTotalVoo;
        this.statusAeronave = statusAeronave;
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

    public int getAnoAeronave() {
        return anoAeronave;
    }

    public void setAnoAeronave(int anoAeronave) {
        this.anoAeronave = anoAeronave;
    }

    public float getValorHoraVoo() {
        return valorHoraVoo;
    }

    public void setValorHoraVoo(float valorHoraVoo) {
        this.valorHoraVoo = valorHoraVoo;
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
        this.horaTotalVoo = horaTotalVoo;
    }

    public boolean getStatusAeronave() {
        return statusAeronave;
    }

    public void setStatusAeronave(boolean statusAeronave) {
        this.statusAeronave = statusAeronave;
    }

    @Override
    public String toString() {
        return "Aeronave{" + "modelo=" + modelo + ", serial=" + serial + ", anoAeronave=" + anoAeronave + ", valorHoraVoo=" + valorHoraVoo + ", proxRevisao=" + proxRevisao + ", horaTotalVoo=" + horaTotalVoo + ", statusAeronave=" + statusAeronave + '}';
    }

}
