package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Aeronave;
import model.ModeloAeronave;
import model.Piloto;

public class Dados {

    private final String SOURCES = "/sources/";
    private final String AERONAVE = "aeronave";
    private final String MODELOAERONAVE = "modeloAeronave";
    private final String PILOTO = "piloto";

    public Dados() {

    }

    //AERONAVE STUFF
    public void SaveAeronave(List<Aeronave> aeronave) {
        try {
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + SOURCES + AERONAVE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(aeronave);
            objectOut.close();
            // System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Aeronave> ReadAeronaveList() {
        List<Aeronave> temp = new ArrayList<>();
        try {
            File f = new File(System.getProperty("user.dir") + SOURCES + AERONAVE);
            if (f.exists() && !f.isDirectory()) {
                FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + SOURCES + AERONAVE);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                temp = (List<Aeronave>) objectIn.readObject();
                objectIn.close();
                return temp;
            } else {
                //GAMBIARRA FEROZ
                System.err.println("Creating a " + AERONAVE + " file");
                SaveAeronave(temp);
                return temp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //PILOTO STUFF
    public void SavePiloto(List<Piloto> piloto) {
        try {
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + SOURCES + PILOTO);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(piloto);
            objectOut.close();
            // System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Piloto> ReadPilotoList() {
        List<Piloto> temp = new ArrayList<>();
        try {
            File f = new File(System.getProperty("user.dir") + SOURCES + PILOTO);
            if (f.exists() && !f.isDirectory()) {
                FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + SOURCES + PILOTO);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                temp = (List<Piloto>) objectIn.readObject();
                objectIn.close();
                return temp;
            } else {
                //GAMBIARRA FEROZ
                System.err.println("Creating a " + PILOTO + " file");
                SavePiloto(temp);
                return temp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //MODELO AERONAVE STUFF
    public void SaveModeloAeronave(List<ModeloAeronave> modeloAeronave) {
        try {
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + SOURCES + MODELOAERONAVE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(modeloAeronave);
            objectOut.close();
            // System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<ModeloAeronave> ReadModeloAeronaveList() {
        List<ModeloAeronave> temp = new ArrayList<>();
        try {
            File f = new File(System.getProperty("user.dir") + SOURCES + MODELOAERONAVE);
            if (f.exists() && !f.isDirectory()) {
                FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + SOURCES + MODELOAERONAVE);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                temp = (List<ModeloAeronave>) objectIn.readObject();
                objectIn.close();
                return temp;
            } else {
                //GAMBIARRA FEROZ
                System.err.println("Creating a " + MODELOAERONAVE + " file");
                SaveModeloAeronave(temp);
                return temp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
