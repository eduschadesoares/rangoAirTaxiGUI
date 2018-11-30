package controllerCRUD;

import controller.PilotoController;
import controller.PrincipalController;
import static controller.PrincipalController.lstAeronaves;
import static controller.PrincipalController.lstPilotos;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.AbstractList;
import java.util.ResourceBundle;
import java.util.UUID;
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
import model.Piloto;
import utility.Dados;

public class InsertPilotoController implements Initializable {

    String[] statusChoices = new String[]{"Disponível", "Manutenção", "Descanso"};

    private PilotoController controller = new PilotoController();

    @FXML
    public AnchorPane InsertPilotoPanel;
    @FXML
    public TextField txtFldId;
    @FXML
    public TextField txtFldNome;
    @FXML
    public ComboBox cmbBoxStatus;
    @FXML
    public Label lblDuplicatedSerial;

    @FXML
    private void btnCancelInsertion(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Piloto.fxml"));
            InsertPilotoPanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    void btnSaveInsertion(ActionEvent event) {
        boolean duplicated = false;
        try {
            controller.piloto.setIdPiloto(txtFldId.getText());
            controller.piloto.setNomePiloto(txtFldNome.getText());
//            controller.piloto.setStatusPiloto(cmbBoxStatus.getValue().toString());

            for (Piloto each : PrincipalController.lstPilotos) {
                if (controller.piloto.getIdPiloto().equals(each.getIdPiloto())) {
                    System.err.println("Serial duplicated");
//                    lblDuplicatedSerial.setText("Serial já existente");
                    createSerial();
//                    txtFldSerial.requestFocus();
                    duplicated = true;
                }
            }

            if (!duplicated) {
                PrincipalController.lstPilotos.add(controller.piloto);
                PrincipalController.savePilotoList(lstPilotos);
                btnCancelInsertion(event);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createSerial() {
        //Creates a random serial
        UUID uuid = UUID.randomUUID();
        String serialRandom = uuid.toString();
        txtFldId.setText(serialRandom);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createSerial();
        cmbBoxStatus.getItems().addAll(statusChoices);
        cmbBoxStatus.setValue(statusChoices[0]);
    }

}
