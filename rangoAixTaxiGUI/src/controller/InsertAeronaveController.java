package controller;

import static controller.PrincipalController.lstAeronaves;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.AbstractList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Aeronave;
import utility.Dados;
import model.Student;

public class InsertAeronaveController implements Initializable {

    String[] statusChoices = new String[]{"Disponível", "Manutenção", "Fora de Uso"};

    private AeronaveController controller = new AeronaveController();

    @FXML
    public AnchorPane InsertAeronavePanel;
    @FXML
    public TextField txtFldSerial;
    @FXML
    public TextField txtFldModelo;
    @FXML
    public TextField txtFldIdade;
    @FXML
    public TextField txtFldHoraVoo;
    @FXML
    public ComboBox cmbBoxStatus;

    @FXML
    private void btnCancelInsertion(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Aeronave.fxml"));
            InsertAeronavePanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    void btnSaveInsertion(ActionEvent event) {
        try {
            controller.aeronave.setSerial(Integer.parseInt(txtFldSerial.getText()));
            controller.aeronave.setModelo(txtFldModelo.getText());
            controller.aeronave.setIdade(Integer.parseInt(txtFldIdade.getText()));
            controller.aeronave.setHoraVoo(Integer.parseInt(txtFldHoraVoo.getText()));
            controller.aeronave.setStatusAeronave(cmbBoxStatus.getValue().toString());
            PrincipalController.lstAeronaves.add(controller.aeronave);
            PrincipalController.saveAeronaveList(lstAeronaves);
            
            btnCancelInsertion(event);
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbBoxStatus.getItems().addAll(statusChoices);
        cmbBoxStatus.setValue(statusChoices[0]);
    }

}
