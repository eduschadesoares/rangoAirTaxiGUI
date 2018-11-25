package model;

import java.io.Serializable;

public class ModeloAeronave implements Serializable {

    private String modelo;
    private int limiteHorasManutencao;
    //ADD VALOR HORA VOO AERONAVE 
    
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

    public ModeloAeronave(String modelo, int limiteHorasManutencao) {
        this.modelo = modelo;
        this.limiteHorasManutencao = limiteHorasManutencao;
    }

    public ModeloAeronave() {
    }

    @Override
    public String toString() {
        return modelo;
    }

}
