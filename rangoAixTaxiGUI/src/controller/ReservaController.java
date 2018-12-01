package controller;

import static controller.PrincipalController.lstAeronaves;
import static controller.PrincipalController.lstReservas;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.AbstractList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import model.Reserva;
import utility.Dados;

public class ReservaController implements Initializable {

    public Reserva reserva = new Reserva();

    @FXML
    public AnchorPane reservaPanel, reservaDeViagensPanel, fecharAgendaPanel;

    @FXML
    private void btnCallMenuScene(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
            reservaPanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    private void selectionReservasDeViagens(Event event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxmlReservas/ReservaViagens.fxml"));
            reservaDeViagensPanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    private void selectionFecharAgenda(Event event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxmlReservas/FecharAgenda.fxml"));
            fecharAgendaPanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public ReservaController() {

    }

}
