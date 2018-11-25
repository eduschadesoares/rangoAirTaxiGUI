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
import model.DataMes;
import model.ModeloAeronave;
import model.Piloto;
import model.Heliporto;
import model.Reserva;

public class Dados {

    private final String SOURCES = "/sources/";
    private final String MES = "mes";
    private final String AERONAVE = "aeronave";
    private final String MODELOAERONAVE = "modeloAeronave";
    private final String PILOTO = "piloto";
    private final String HELIPORTO = "heliporto";
    private final String RESERVA = "reserva";

    public Dados() {

    }

    //--------------------------------------------------------------------------    
    //MES STUFF
    public void SaveMes(DataMes mes) {
        try {
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + SOURCES + MES);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(mes);
            objectOut.close();
            // System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public DataMes ReadMes() {
        DataMes temp = new DataMes();
        try {
            File f = new File(System.getProperty("user.dir") + SOURCES + MES);
            if (f.exists() && !f.isDirectory()) {
                FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + SOURCES + MES);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                temp = (DataMes) objectIn.readObject();
                objectIn.close();
                return temp;
            } else {
                //GAMBIARRA FEROZ
                System.err.println("Creating a " + MES + " file");
                SaveMes(temp);
                return temp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //--------------------------------------------------------------------------    
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

    //--------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------
    //HELIPORTO STUFF
    public void SaveHeliporto(List<Heliporto> heliporto) {
        try {
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + SOURCES + HELIPORTO);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(heliporto);
            objectOut.close();
            // System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Heliporto> ReadHeliportoList() {
        List<Heliporto> temp = new ArrayList<>();
        try {
            File f = new File(System.getProperty("user.dir") + SOURCES + HELIPORTO);
            if (f.exists() && !f.isDirectory()) {
                FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + SOURCES + HELIPORTO);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                temp = (List<Heliporto>) objectIn.readObject();
                objectIn.close();
                return temp;
            } else {
                //GAMBIARRA FEROZ
                System.err.println("Creating a " + HELIPORTO + " file");
                SaveHeliporto(temp);
                return temp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //--------------------------------------------------------------------------
    //RESERVA STUFF
    public void SaveReserva(List<Reserva> reserva) {
        try {
            FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + SOURCES + RESERVA);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(reserva);
            objectOut.close();
            // System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Reserva> ReadReservaList() {
        List<Reserva> temp = new ArrayList<>();
        try {
            File f = new File(System.getProperty("user.dir") + SOURCES + RESERVA);
            if (f.exists() && !f.isDirectory()) {
                FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + SOURCES + RESERVA);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                temp = (List<Reserva>) objectIn.readObject();
                objectIn.close();
                return temp;
            } else {
                //GAMBIARRA FEROZ
                System.err.println("Creating a " + RESERVA + " file");
                SaveReserva(temp);
                return temp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
