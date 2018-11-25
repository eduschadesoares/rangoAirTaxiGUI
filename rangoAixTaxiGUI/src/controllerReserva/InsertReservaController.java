package controllerReserva;

import static controller.PrincipalController.lstAeronaves;
import static controller.PrincipalController.lstHeliportos;
import static controller.PrincipalController.mes;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import javafx.scene.layout.AnchorPane;
import model.Aeronave;
import model.DataDia;
import model.DataMes;
import model.DataSemana;
import model.Heliporto;
import utility.Dados;

public class InsertReservaController implements Initializable {

    int distOrigem, distDestino = 0;

    @FXML
    public AnchorPane InsertReservaPanel;
    @FXML
    public ComboBox cmbBoxOrigem;
    @FXML
    public ComboBox cmbBoxDestino;
    @FXML
    public ComboBox cmbBoxSemana;
    @FXML
    public ComboBox cmbBoxDia;
    @FXML
    public ComboBox cmbBoxHorario;
    @FXML
    public CheckBox chBoxConfirmarDestinos;
    @FXML
    public CheckBox chBoxConfirmarHorario;

    @FXML
    private void btnCancelInsertion(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxmlReservas/ReservaViagens.fxml"));
            InsertReservaPanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    private void btnConfirmInsertion(ActionEvent event) {

    }

    @FXML
    private void cmbBoxOrigemSelected(Event event) {
        Heliporto origem;

        List<Heliporto> lstHeliportosTemp = new ArrayList<Heliporto>();

        cmbBoxDestino.getItems().clear();

        origem = (Heliporto) cmbBoxOrigem.getSelectionModel().getSelectedItem();
        distOrigem = origem.getKmHeliporto();

        for (Heliporto each : lstHeliportos) {
            if (each != origem) {
                lstHeliportosTemp.add(each);
            }
        }

        cmbBoxDestino.getItems().addAll(lstHeliportosTemp);
        cmbBoxDestino.setValue(lstHeliportosTemp.get(0));
        cmbBoxDestino.setDisable(false);
    }

    @FXML
    private void cmbBoxDestinoSelected(Event event) {
        Heliporto destino;
        destino = (Heliporto) cmbBoxDestino.getSelectionModel().getSelectedItem();
        try {
            distDestino = destino.getKmHeliporto();
        } catch (Exception e) {
//            System.out.println("RS");
        }
        chBoxConfirmarDestinos.setDisable(false);
    }

    @FXML
    private void confirmaDestinos(Event event) {
        if (chBoxConfirmarDestinos.isSelected()) {
            cmbBoxOrigem.setDisable(true);
            cmbBoxDestino.setDisable(true);
            calculaDistanciaCidades(distOrigem, distDestino);
        } else {
            cmbBoxOrigem.setDisable(false);
            cmbBoxDestino.setDisable(false);
        }
    }

    private void calculaDistanciaCidades(int origem, int destino) {
        int distancia;
        distancia = origem - destino;
        if (distancia < 0) {
            distancia *= -1;
        }
        System.out.println(distancia);
    }

    private void setComboBox() {
        cmbBoxOrigem.getItems().addAll(lstHeliportos);

        cmbBoxSemana.getItems().addAll(mes.getSemana1());
        cmbBoxSemana.getItems().addAll(mes.getSemana2());
        cmbBoxSemana.getItems().addAll(mes.getSemana3());
        cmbBoxSemana.getItems().addAll(mes.getSemana4());

//        cmbBoxOrigem.setValue(lstHeliportos.get(0));
//        cmbBoxDestino.getItems().addAll(lstHeliportos);
//        cmbBoxDestino.setValue(lstHeliportos.get(1));
    }

    @FXML
    private void cmbBoxSemanaSelected(Event event) {
        DataSemana semanaSelected;
        semanaSelected = (DataSemana) cmbBoxSemana.getSelectionModel().getSelectedItem();
        System.out.println(semanaSelected);

        cmbBoxDia.setDisable(false);
        cmbBoxDia.getItems().clear();

        cmbBoxDia.getItems().addAll(semanaSelected.getSegunda());
        cmbBoxDia.getItems().addAll(semanaSelected.getTerca());
        cmbBoxDia.getItems().addAll(semanaSelected.getQuarta());
        cmbBoxDia.getItems().addAll(semanaSelected.getQuinta());
        cmbBoxDia.getItems().addAll(semanaSelected.getSexta());
        cmbBoxDia.getItems().addAll(semanaSelected.getSabado());
        cmbBoxDia.getItems().addAll(semanaSelected.getDomingo());

//        cmbBoxDia.setValue(semanaSelected.getSegunda());
    }

    @FXML
    private void cmbBoxDiaSelected(Event event) {
        DataDia diaSelected;
        diaSelected = (DataDia) cmbBoxDia.getSelectionModel().getSelectedItem();
        System.out.println(diaSelected);

        cmbBoxHorario.setDisable(false);
        cmbBoxHorario.getItems().clear();

        cmbBoxHorario.getItems().addAll(diaSelected.agendaServico.keySet());

    }

    @FXML
    private void cmbBoxHorarioSelected(Event event) {
        String horario;

        horario = cmbBoxHorario.getSelectionModel().getSelectedItem().toString();
        System.out.println(horario);

        chBoxConfirmarHorario.setDisable(false);
    }

    @FXML
    private void confirmaHorario(Event event) {
        if (chBoxConfirmarHorario.isSelected()) {
            cmbBoxSemana.setDisable(true);
            cmbBoxDia.setDisable(true);
            cmbBoxHorario.setDisable(true);
        } else {
            cmbBoxSemana.setDisable(false);
            cmbBoxDia.setDisable(false);
            cmbBoxHorario.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBox();
    }

}
