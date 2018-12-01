package controllerReserva;

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

public class ReservaViagensController implements Initializable {

    @FXML
    public AnchorPane reservaViagensPanel;

    @FXML
    public TableView<Reserva> tblView;

    @FXML
    private void btnAgendarVoo(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxmlReservas/InsertReserva.fxml"));
            reservaViagensPanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            tblView.setItems(FXCollections.observableList(lstReservas));
            tblView.requestFocus();

            // Select last added item
            if (lstReservas.size() > 1) {
                tblView.getSelectionModel().select(lstReservas.get(lstReservas.size() - 1));
            } else {
                tblView.getSelectionModel().selectFirst();
            }
            tblView.refresh();
        });

//        if (lstAeronaves.isEmpty()) {
//            btnDeleteAeronaveHide.setDisable(true);
//            btnEditAeronaveHide.setDisable(true);
//        }
    }

}
