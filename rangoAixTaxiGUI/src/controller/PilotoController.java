package controller;

import static controller.PrincipalController.lstAeronaves;
import static controller.PrincipalController.lstPilotos;
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
import model.Piloto;
import utility.Dados;

public class PilotoController implements Initializable {

    public Piloto piloto = new Piloto();

    @FXML
    public AnchorPane pilotoPanel;
    @FXML
    public TableView<Piloto> tblView;
    @FXML
    public Button btnDeletePilotoHide;
    @FXML
    public Button btnEditPilotoHide;

    @FXML
    private void btnCallMenuScene(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
            pilotoPanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    private void btnInsertPiloto(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxmlCRUDPiloto/InsertPiloto.fxml"));
            pilotoPanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    private void btnEditPiloto(ActionEvent event) {
        piloto = tblView.getSelectionModel().getSelectedItem();
        controllerCRUD.EditPilotoController.getPilotoObj(piloto);

        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxmlCRUDPiloto/EditPiloto.fxml"));
            pilotoPanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    private void tblViewEditPilotoClick(Event event) {
        MouseEvent me = null;
        if (event.getEventType() == MOUSE_CLICKED) {
            me = (MouseEvent) event;
            if (me.getClickCount() == 2) {
                piloto = tblView.getSelectionModel().getSelectedItem();
                if (piloto != null) {
                    ActionEvent callFuncEvent = new ActionEvent();
                    btnEditPiloto(callFuncEvent);
                }
            }
        }
    }

    @FXML
    private void btnDeletePiloto(ActionEvent event) {
        piloto = tblView.getSelectionModel().getSelectedItem();
        controllerCRUD.DeletePilotoController.getPilotoObj(piloto);

        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxmlCRUDPiloto/DeletePiloto.fxml"));
            pilotoPanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            tblView.setItems(FXCollections.observableList(lstPilotos));
            tblView.requestFocus();

            // Select last added item
            if (lstPilotos.size() > 1) {
                tblView.getSelectionModel().select(lstPilotos.get(lstPilotos.size() - 1));
            } else {
                tblView.getSelectionModel().selectFirst();
            }
            tblView.refresh();
        });

        if (lstPilotos.isEmpty()) {
            btnDeletePilotoHide.setDisable(true);
            btnEditPilotoHide.setDisable(true);
        }
    }

    public PilotoController() {

    }

}
