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

public class EditAeronaveController implements Initializable {

    String[] statusChoices = new String[]{"Disponível", "Manutenção", "Fora de Uso"};

    static Aeronave aeronaveList;

    static int indexList;

    private AeronaveController controller = new AeronaveController();

    @FXML
    public AnchorPane EditAeronavePanel;
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
    public Label lblDuplicatedSerial;

    @FXML
    private void btnCancelEditon(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Aeronave.fxml"));
            EditAeronavePanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    void btnSaveEdition(ActionEvent event) {
        boolean duplicated = false;
        int serial;
        try {
            for (Aeronave each : lstAeronaves) {
                if (each.getSerial() == aeronaveList.getSerial()) {
                    serial = Integer.parseInt(txtFldSerial.getText());
                    for (Aeronave each2 : lstAeronaves) {
                        if (serial == each2.getSerial()) {
                            System.err.println("DUPLICOU");
                            lblDuplicatedSerial.setText("Serial já existente");
                            txtFldSerial.requestFocus();
                            duplicated = true;
                            break;
                        } else {
                            duplicated = false;
                        }
                    }
                    if (!duplicated) {
                        each.setSerial(Integer.parseInt(txtFldSerial.getText()));
                        each.setModelo(txtFldModelo.getText());
                        each.setIdade(Integer.parseInt(txtFldIdade.getText()));
                        each.setHoraVoo(Float.parseFloat(txtFldHoraVoo.getText()));
                        each.setStatusAeronave(cmbBoxStatus.getValue().toString());
                        break;
                    }
                }
            }

            if (!duplicated) {
//            PrincipalController.lstAeronaves.add(controller.aeronave);
                PrincipalController.saveAeronaveList(lstAeronaves);
                btnCancelEditon(event);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void setAllFields() {

        txtFldSerial.setText(Integer.toString(aeronaveList.getSerial()));
        txtFldModelo.setText(aeronaveList.getModelo());
        txtFldIdade.setText(Integer.toString(aeronaveList.getIdade()));
        txtFldHoraVoo.setText(Float.toString(aeronaveList.getHoraVoo()));
        cmbBoxStatus.getItems().addAll(statusChoices);
        cmbBoxStatus.setValue(aeronaveList.getStatusAeronave());
    }

    public static void getAeronaveObj(Aeronave aeronave) {
        for (Aeronave each : lstAeronaves) {
            if (each.getSerial() == aeronave.getSerial()) {
                aeronaveList = each;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setAllFields();
    }

    public EditAeronaveController() {

    }

}
