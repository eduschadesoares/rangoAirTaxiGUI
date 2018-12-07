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
    private float horaTotalVoo;

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

    public void setBonusComissao() {
        if (getHoraTotalVoo() >= 10000 && getHoraTotalVoo() < 20000) {
            this.bonusComissao = 3;
        } else if (getHoraTotalVoo() < 30000) {
            this.bonusComissao = 6;
        } else if (getHoraTotalVoo() < 40000) {
            this.bonusComissao = 9;
        } else if (getHoraTotalVoo() < 50000) {
            this.bonusComissao = 12;
        } else if (getHoraTotalVoo() > 50000) {
            this.bonusComissao = 15;
        } else {
            this.bonusComissao = 0;
        }
        System.out.println("Bonus de comissão de " + this.bonusComissao + "%");
    }

    public float getComissaoMes() {
        return comissaoMes;
    }

    public void setComissao(float valorHoraVoo, int tempoDeViagem) {
        System.out.println("- hora voo" + valorHoraVoo);
        System.out.println("- tempo de viagem" + tempoDeViagem);
        float comissaoTemp = (float) (valorHoraVoo * 0.2);
        float bonusTemp = (comissaoTemp) * ((float) (this.bonusComissao * 0.1));

        this.comissaoMes = this.comissaoMes + ((comissaoTemp + bonusTemp) * tempoDeViagem);
    }

    public float getSalarioMes() {
        return salarioMes;
    }

    public void setSalarioMes(float salarioMes) {
        this.salarioMes = salarioMes;
    }

    public float getHoraTotalVoo() {
        return horaTotalVoo;
    }

    public void setHoraTotalVoo(float horaTotalVoo) {
        this.horaTotalVoo = horaTotalVoo + this.horaTotalVoo;
    }

    public float getRecebimentoMensal() {
        return getSalarioMes() + getComissaoMes();
    }

    public Piloto(DataMes mes, String idPiloto, String nomePiloto, boolean statusPiloto, float salarioMes, Heliporto heliporto, float horaTotalVoo) {
        this.mes = mes;
        this.idPiloto = idPiloto;
        this.nomePiloto = nomePiloto;
        this.statusPiloto = statusPiloto;
        this.salarioMes = salarioMes;
        this.heliporto = heliporto;
        this.horaTotalVoo = horaTotalVoo;
        setBonusComissao();
    }

    public Piloto() {
    }

    @Override
    public String toString() {
        return nomePiloto;
    }

}
