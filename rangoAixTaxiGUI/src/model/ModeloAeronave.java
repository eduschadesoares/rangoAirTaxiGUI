package model;

import java.io.Serializable;

public class ModeloAeronave implements Serializable {

    private String modelo;
    private int limiteHorasManutencao;
    private int anoModelo;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getLimiteHorasManutencao() {
        return limiteHorasManutencao;
    }

    public void setLimiteHorasManutencao(int limiteHorasManutencao) {
        this.limiteHorasManutencao = limiteHorasManutencao;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public ModeloAeronave(String modelo, int limiteHorasManutencao, int anoModelo) {
        this.modelo = modelo;
        this.limiteHorasManutencao = limiteHorasManutencao;
        this.anoModelo = anoModelo;
    }

    public ModeloAeronave() {
    }

    @Override
    public String toString() {
        return modelo;
    }

}
