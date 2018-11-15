package controller;

import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.AbstractList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import utility.Dados;
import model.Aeronave;
import model.Piloto;
import model.DataDia;
import model.DataMes;
import model.DataSemana;
import model.ModeloAeronave;

public class PrincipalController implements Initializable {

    public static List<Aeronave> lstAeronaves = new ArrayList<Aeronave>();
    public static List<ModeloAeronave> lstModelosAeronaves = new ArrayList<ModeloAeronave>();
    public static List<Piloto> lstPilotos = new ArrayList<Piloto>();

    public static Dados dados = new Dados();

    @FXML
    public AnchorPane rootPane;

    public static void saveAeronaveList(List<Aeronave> lstAeronaves) {
        dados.SaveAeronave(lstAeronaves);
    }

    public void readAeronaveList() {
        lstAeronaves = dados.ReadAeronaveList();
    }

    public static void savePilotoList(List<Piloto> lstPilotos) {
        dados.SavePiloto(lstPilotos);
    }

    public void readPilotoList() {
        lstPilotos = dados.ReadPilotoList();
    }

    public void createModelosList() {
        ModeloAeronave modelo1 = new ModeloAeronave("EUROCOPTER EC120 B", 400);
        ModeloAeronave modelo2 = new ModeloAeronave("ROBINSON R66", 350);
        ModeloAeronave modelo3 = new ModeloAeronave("AGUSTA A109E", 600);
        ModeloAeronave modelo4 = new ModeloAeronave("ESQUILO AS350 B", 950);
        ModeloAeronave modelo5 = new ModeloAeronave("EUROCOPTER EC130 T2", 750);
        lstModelosAeronaves.add(modelo1);
        lstModelosAeronaves.add(modelo2);
        lstModelosAeronaves.add(modelo3);
        lstModelosAeronaves.add(modelo4);
        lstModelosAeronaves.add(modelo5);
        dados.SaveModeloAeronave(lstModelosAeronaves);
        lstModelosAeronaves = dados.ReadModeloAeronaveList();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        createModelosList();

//        DataDia dt = new DataDia("Segunda");
//
//        Map<String, Boolean> hora2 = new LinkedHashMap<String, Boolean>();
//
//        hora2.put("06:00", true);
//        hora2.put("06:30", true);
//        hora2.put("07:00", true);
//        hora2.put("07:30", true);
//        hora2.put("08:00", true);
//        hora2.put("08:30", true);
//        hora2.put("09:00", true);
//        hora2.put("09:30", true);
//
//        dt.setHora(hora2);
//        DataMes mes = new DataMes();
//
//        System.out.println(mes.getSemana1().getDomingo().agendaManutencao.get("06:30"));
//        System.out.println(mes.getSemana1().getDomingo().agendaManutencao.replace("06:30", false));
//        System.out.println(mes.getSemana1().getDomingo().agendaManutencao.get("06:30"));
//        System.out.println(mes.getSemana1().getDomingo().agendaServico.get("06:30"));
//        System.out.println(dt.hora);
//        ArrayList<Aeronave> store = new ArrayList<>();
//        
//        Aeronave aero = new Aeronave(1, "kk", 1, 1, "Disponível");
//        store.add(aero);
//        aero = new Aeronave(2, "qq", 1, 1, "Disponível");
//        store.add(aero);
//        aero = new Aeronave(3, "rr", 1, 1,"Manutenção");
//        store.add(aero);
//        aero = new Aeronave(4, "gg", 1, 1, "Manutenção");
//        store.add(aero);
//
//        dadosAeronave.SaveAeronave(store);
        readAeronaveList();
        readPilotoList();

        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

}
