package model;

import java.io.Serializable;

public class ModeloAeronave implements Serializable {

    private String modelo;
    private int velocidadeMedia;
    private int limiteHorasManutencao;
    private int tempoNaRevisao;
    //ADD VALOR HORA VOO AERONAVE 
    //ADD VALOR VELOCIDADE MEDIA AERONAVE 

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(int velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    public int getLimiteHorasManutencao() {
        return limiteHorasManutencao;
    }

    public void setLimiteHorasManutencao(int limiteHorasManutencao) {
        this.limiteHorasManutencao = limiteHorasManutencao;
    }

    public int getTempoNaRevisao() {
        return tempoNaRevisao;
    }

    public void setTempoNaRevisao(int tempoNaRevisao) {
        this.tempoNaRevisao = tempoNaRevisao;
    }

    public ModeloAeronave(String modelo, int velocidadeMedia, int limiteHorasManutencao, int tempoNaRevisao) {
        this.modelo = modelo;
        this.velocidadeMedia = velocidadeMedia;
        this.limiteHorasManutencao = limiteHorasManutencao;
        this.tempoNaRevisao = tempoNaRevisao;
    }

    @Override
    public String toString() {
        return modelo;
    }
    
}
