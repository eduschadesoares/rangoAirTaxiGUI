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

public class Dados {

    private String SOURCES = "/sources/";
    private String AERONAVE = "aeronave";

    public Dados() {

    }

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
}
