package controller;

import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.AbstractList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import utility.Dados;
import model.Student;
import model.Aeronave;

public class PrincipalController implements Initializable {

    public static List<Aeronave> lstAeronaves = new ArrayList<Aeronave>();

    public static Dados dadosAeronave = new Dados();

    @FXML
    public AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        ArrayList<Aeronave> store = new ArrayList<>();
//        
//        Aeronave aero = new Aeronave(1, "kk", 1, 1, "Disponível");
//        store.add(aero);
//        aero = new Aeronave(2, "qq", 1, 1, "Disponível");
//        store.add(aero);
//        aero = new Aeronave(3, "rr", 1, 1, "Manutenção");
//        store.add(aero);
//        aero = new Aeronave(4, "gg", 1, 1, "Manutenção");
//        store.add(aero);
//
//        dadosAeronave.SaveAeronave(store);

     //   if (dadosAeronave.ReadList() != null) {
            lstAeronaves = dadosAeronave.ReadList();
     //   }

        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

}
