package controller;

import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.AbstractList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
import model.Heliporto;
import model.ModeloAeronave;
import model.Reserva;

public class PrincipalController implements Initializable {

    public static DataMes mes = new DataMes();
    public static List<Aeronave> lstAeronaves = new ArrayList<Aeronave>();
    public static List<ModeloAeronave> lstModelosAeronaves = new ArrayList<ModeloAeronave>();
    public static List<Piloto> lstPilotos = new ArrayList<Piloto>();
    public static List<Heliporto> lstHeliportos = new ArrayList<Heliporto>();
    public static List<Reserva> lstReservas = new ArrayList<Reserva>();

    public static Dados dados = new Dados();

    @FXML
    public AnchorPane rootPane;

    public static void saveMonth(DataMes mes) {
        dados.SaveMes(mes);
    }

    public static void readMes() {
        mes = dados.ReadMes();
    }

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

    public void createModeloList() {
        ModeloAeronave modelo1 = new ModeloAeronave("EUROCOPTER EC120 B", 260, 20, 3);
        ModeloAeronave modelo2 = new ModeloAeronave("ROBINSON R66", 210, 15, 4);
        ModeloAeronave modelo3 = new ModeloAeronave("AGUSTA A109E", 270, 18, 5);
        ModeloAeronave modelo4 = new ModeloAeronave("ESQUILO AS350 B", 250, 18, 2);
        ModeloAeronave modelo5 = new ModeloAeronave("EUROCOPTER EC130 T2", 245, 13, 2);
        lstModelosAeronaves.add(modelo1);
        lstModelosAeronaves.add(modelo2);
        lstModelosAeronaves.add(modelo3);
        lstModelosAeronaves.add(modelo4);
        lstModelosAeronaves.add(modelo5);
        dados.SaveModeloAeronave(lstModelosAeronaves);
    }

    public void readModeloList() {
        lstModelosAeronaves = dados.ReadModeloAeronaveList();
    }

    public void createHeliportoList() {
        Heliporto heliporto1 = new Heliporto("PET", 0, "Heliporto de Pelotas", "Pelotas - RS");
        lstHeliportos.add(heliporto1);
        heliporto1 = new Heliporto("FLN", 570, "Heliporto de Florianópolis", "Florianópolis - SC");
        lstHeliportos.add(heliporto1);
        heliporto1 = new Heliporto("CWB", 750, "Heliporto de Curitiba", "Curitiba - PR");
        lstHeliportos.add(heliporto1);
        heliporto1 = new Heliporto("GRU", 1050, "Heliporto de Guarulhos", "São Paulo - SP");
        lstHeliportos.add(heliporto1);
        heliporto1 = new Heliporto("NAT", 3300, "Heliporto de Natal", "Natal - RN");
        lstHeliportos.add(heliporto1);
        dados.SaveHeliporto(lstHeliportos);
    }

    public void readHeliportoList() {
        lstHeliportos = dados.ReadHeliportoList();
    }

    public static void saveReservaList(List<Reserva> lstReservas) {
        dados.SaveReserva(lstReservas);
    }

    public void readReservaList() {
        lstReservas = dados.ReadReservaList();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createModeloList();
        createHeliportoList();

//        
//        
//        Aeronave aero = new Aeronave(lstModelosAeronaves.get(0), mes, "UAHUAHAUAH", 0, 0, 0, 0, true);
//        lstAeronaves.add(aero);
//        saveAeronaveList(lstAeronaves);

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
//        for (Object each : mes.getSemana1().getDomingo().agendaServico) {
//
//            System.out.println(each);
//        }
//        System.out.println("\n\n" + mes.getSemana1().getSegunda().agendaServico.size());

//        System.out.println(dt.hora);
//        ArrayList<Aeronave> store = new ArrayList<>();
        readMes();
        readAeronaveList();
        readPilotoList();
        readModeloList();
        readHeliportoList();
        readReservaList();

        
//        Aeronave opa = new Aeronave(lstModelosAeronaves.get(0), mes, "affasfasfasuhas", 0, 0, 0, 0, true);
//        try{
//        opa.getMes().getSemana1().getSegunda().agendaServico.set(7, false);
//        } catch (NullPointerException e) {
//            System.out.println(e);
//        }
//        lstAeronaves.add(opa);
//        saveAeronaveList(lstAeronaves);
//        System.out.println(lstAeronaves);

        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }

    }

}
