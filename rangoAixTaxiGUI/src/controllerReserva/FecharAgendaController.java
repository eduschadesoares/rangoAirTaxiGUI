package controllerReserva;

import controller.PrincipalController;
import static controller.PrincipalController.lstAeronaves;
import static controller.PrincipalController.lstPilotos;
import static controller.PrincipalController.lstReservas;
import static controller.PrincipalController.mes;
import controller.ReservaController;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.AbstractList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import javafx.scene.layout.AnchorPane;
import model.Aeronave;
import model.DataMes;
import model.Piloto;
import model.Reserva;
import utility.Dados;

public class FecharAgendaController implements Initializable {

    private ReservaController controller = new ReservaController();

    @FXML
    public AnchorPane fechaAgendaPanel;

    @FXML
    public void confirmaFecharAgenda(ActionEvent event) {
        fechaMesAeronave();
        criaNovoMes();

    }

    @FXML
    public void cancelaFecharAgenda(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxmlReservas/ReservaViagens.fxml"));
            fechaAgendaPanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void fechaMesAeronave() {
        for (Reserva eachReserva : lstReservas) {
            for (Aeronave eachAeronave : lstAeronaves) {
                if (eachReserva.getAeronave().getSerial().equals(eachAeronave.getSerial())) {
                    eachAeronave.setHoraTotalVoo(eachReserva.getTempoDeViagem() / 2);
                    break;
                }
            }
        }
    }

    public void criaNovoMes() {
        int mesAtual;
        int anoAtual;

        mesAtual = PrincipalController.mes.getNumMes();
        anoAtual = PrincipalController.mes.getNumAno();

        PrincipalController.mes = new DataMes();

        if (mesAtual < 12) {
            PrincipalController.mes.setNumAno(anoAtual);
            PrincipalController.mes.setNumMes(mesAtual + 1);
        } else {
            PrincipalController.mes.setNumAno(anoAtual + 1);
            PrincipalController.mes.setNumMes(1);
            incrementaIdadeAeronave();
        }

        for (Aeronave each : lstAeronaves) {
            each.setMes(PrincipalController.mes);
        }

        PrincipalController.saveAeronaveList(lstAeronaves);

        for (Piloto each : lstPilotos) {
            each.setMes(PrincipalController.mes);
        }

        PrincipalController.savePilotoList(lstPilotos);

        PrincipalController.saveMonth(PrincipalController.mes);

        System.out.println(PrincipalController.mes.getNumAno() + " " + PrincipalController.mes.getNumMes());

    }

    public void incrementaIdadeAeronave() {
        for (Aeronave each : lstAeronaves) {
            each.incrementaIdade();
            each.setHorasVooDisponiveis();
        }

        PrincipalController.savePilotoList(lstPilotos);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
