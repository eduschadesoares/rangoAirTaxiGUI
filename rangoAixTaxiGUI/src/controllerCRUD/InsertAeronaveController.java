package controllerCRUD;

import controller.AeronaveController;
import controller.PrincipalController;
import static controller.PrincipalController.lstAeronaves;
import static controller.PrincipalController.lstHeliportos;
import static controller.PrincipalController.lstModelosAeronaves;
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
import model.Heliporto;
import model.ModeloAeronave;
import utility.Dados;

public class InsertAeronaveController implements Initializable {

    String[] statusChoices = new String[]{"Disponível", "Manutenção"};

    private AeronaveController controller = new AeronaveController();

    @FXML
    public AnchorPane InsertAeronavePanel;
    @FXML
    public TextField txtFldSerial;
    @FXML
    public TextField txtFldIdade;
//    @FXML
//    public TextField txtFldHoraVoo;
    @FXML
    public ComboBox cmbBoxModelo;
    @FXML
    public ComboBox cmbBoxHeliporto;
    @FXML
    public ComboBox cmbBoxStatus;
    @FXML
    public Label lblDuplicatedSerial;

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
        boolean duplicated = false;
        try {
            //CRIAR UM OBJ DATA PARA AERONAVE
            controller.aeronave.setSerial(txtFldSerial.getText());
            controller.aeronave.setModelo((ModeloAeronave) cmbBoxModelo.getSelectionModel().getSelectedItem());
            controller.aeronave.setIdadeAeronave(Integer.parseInt(txtFldIdade.getText()));
//            controller.aeronave.setHoraTotalVoo(Integer.parseInt(txtFldHoraVoo.getText()));
            controller.aeronave.setStatusAeronave(verificaStatus());
            controller.aeronave.setHeliporto((Heliporto) cmbBoxHeliporto.getSelectionModel().getSelectedItem());

            for (Aeronave each : PrincipalController.lstAeronaves) {
                if (controller.aeronave.getSerial().equals(each.getSerial())) {
                    System.err.println("Serial duplicated");
//                    lblDuplicatedSerial.setText("Serial já existente");
                    createSerial();
//                    txtFldSerial.requestFocus();
                    duplicated = true;
                }
            }

            if (!duplicated) {
                PrincipalController.lstAeronaves.add(controller.aeronave);
                PrincipalController.saveAeronaveList(lstAeronaves);
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
        txtFldSerial.setText(serialRandom);
    }

    public void setComboBox() {
        try {
            cmbBoxModelo.getItems().addAll(lstModelosAeronaves);
            cmbBoxModelo.setValue(lstModelosAeronaves.get(0));
            
            cmbBoxStatus.getItems().addAll(statusChoices);
            cmbBoxStatus.setValue(statusChoices[0]);
            
            cmbBoxHeliporto.getItems().addAll(lstHeliportos);
            cmbBoxHeliporto.setValue(lstHeliportos.get(0));
        } catch (Exception e) {
            System.err.println(e);
        }

    }
    
    public boolean verificaStatus(){
        if (cmbBoxStatus.getSelectionModel().getSelectedItem().equals("Disponível")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createSerial();
        setComboBox();
    }

}
