package controller;

import static controller.PrincipalController.lstAeronaves;
import java.io.IOException;
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
import javafx.fxml.LoadException;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import javafx.scene.layout.AnchorPane;
import model.Aeronave;
import utility.Dados;

public class AeronaveController implements Initializable {

    public Aeronave aeronave = new Aeronave();

    @FXML
    public AnchorPane aeronavePanel;
    @FXML
    public TableView<Aeronave> tblView;
    @FXML
    public Button btnDeleteAeronaveHide;
    @FXML
    public Button btnEditAeronaveHide;
    @FXML
    public Label lblAeronaveInfo;

    @FXML
    private void btnCallMenuScene(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
            aeronavePanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    private void btnInsertAeronave(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxmlCRUDAeronave/InsertAeronave.fxml"));
            aeronavePanel.getChildren().setAll(pane);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @FXML
    private void btnEditAeronave(ActionEvent event) {
//        aeronave = tblView.getSelectionModel().getSelectedItem();
//        controllerCRUD.EditAeronaveController.getAeronaveObj(aeronave);
//
//        try {
//            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxmlCRUDAeronave/EditAeronave.fxml"));
//            aeronavePanel.getChildren().setAll(pane);
//        } catch (Exception e) {
//            System.err.println(e);
//        }
    }

    @FXML
    private void tblViewEditAeronaveClick(Event event) {
        MouseEvent me = null;
        if (event.getEventType() == MOUSE_CLICKED) {
            me = (MouseEvent) event;
            if (me.getClickCount() == 1) {
                aeronave = tblView.getSelectionModel().getSelectedItem();
                if (aeronave != null) {
                    lblAeronaveInfo.setText(aeronave.getInfoGeral());
                }
            }
            if (me.getClickCount() == 2) {
                aeronave = tblView.getSelectionModel().getSelectedItem();
                if (aeronave != null) {
                    ActionEvent callFuncEvent = new ActionEvent();
                    btnEditAeronave(callFuncEvent);
                }
            }
        }
    }

    @FXML
    private void btnDeleteAeronave(ActionEvent event) {
        aeronave = tblView.getSelectionModel().getSelectedItem();
        controllerCRUD.DeleteAeronaveController.getAeronaveObj(aeronave);

        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxmlCRUDAeronave/DeleteAeronave.fxml"));
            aeronavePanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            tblView.setItems(FXCollections.observableList(lstAeronaves));
            tblView.requestFocus();

            // Select last added item
            if (lstAeronaves.size() > 1) {
                tblView.getSelectionModel().select(lstAeronaves.get(lstAeronaves.size() - 1));
            } else {
                tblView.getSelectionModel().selectFirst();

            }

            tblView.refresh();

        });

        if (lstAeronaves.isEmpty()) {
            btnDeleteAeronaveHide.setDisable(true);
            btnEditAeronaveHide.setDisable(true);
        }
    }

    public AeronaveController() {

    }

}
