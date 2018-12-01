package model;

import java.io.Serializable;

public class Piloto implements Serializable {

    private DataMes mes;
    
    private Heliporto heliporto;

    private String idPiloto;
    private String nomePiloto;
    private boolean statusPiloto;
    private int bonusComissao;
    private float comissaoMes;
    private float salarioMes;

    public Heliporto getHeliporto() {
        return heliporto;
    }

    public void setHeliporto(Heliporto heliporto) {
        this.heliporto = heliporto;
    }

    public DataMes getMes() {
        return mes;
    }

    public void setMes(DataMes mes) {
        this.mes = mes;
    }

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

    public boolean getStatusPiloto() {
        return statusPiloto;
    }

    public void setStatusPiloto(boolean statusPiloto) {
        this.statusPiloto = statusPiloto;
    }

    public int getBonusComissao() {
        return bonusComissao;
    }

    public void setBonusComissao(int bonusComissao) {
        this.bonusComissao = bonusComissao;
    }

    public float getComissaoMes() {
        return comissaoMes;
    }

    public void setComissaoMes(float comissaoMes) {
        this.comissaoMes = comissaoMes;
    }

    public float getSalarioMes() {
        return salarioMes;
    }

    public void setSalarioMes(float salarioMes) {
        this.salarioMes = salarioMes;
    }

    public Piloto(DataMes mes, String idPiloto, String nomePiloto, boolean statusPiloto, int bonusComissao, float comissaoMes, float salarioMes, Heliporto heliporto) {
        this.mes = mes;
        this.idPiloto = idPiloto;
        this.nomePiloto = nomePiloto;
        this.statusPiloto = statusPiloto;
        this.bonusComissao = bonusComissao;
        this.comissaoMes = comissaoMes;
        this.salarioMes = salarioMes;
        this.heliporto = heliporto;
    }

    public Piloto() {
    }

    @Override
    public String toString() {
        return  nomePiloto;
    }

}
