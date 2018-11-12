package model;

import java.io.Serializable;

public class Aeronave implements Serializable {
    private String serial;
    private String modelo;
    private int idade;
    private float horaVoo;
    private String statusAeronave;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public float getHoraVoo() {
        return horaVoo;
    }

    public void setHoraVoo(float horaVoo) {
        this.horaVoo = horaVoo;
    }

    public String getStatusAeronave() {
        return statusAeronave;
    }

    public void setStatusAeronave(String statusAeronave) {
        this.statusAeronave = statusAeronave;
    }

    public Aeronave() {
    }

    public Aeronave(String serial, String modelo, int idade, float horaVoo, String statusAeronave) {
        this.serial = serial;
        this.modelo = modelo;
        this.idade = idade;
        this.horaVoo = horaVoo;
        this.statusAeronave = statusAeronave;
    }

    @Override
    public String toString() {
        return "Aeronave{" + "serial=" + serial + ", modelo=" + modelo + ", idade=" + idade + ", horaVoo=" + horaVoo + ", statusAeronave=" + statusAeronave + '}';
    }

}
