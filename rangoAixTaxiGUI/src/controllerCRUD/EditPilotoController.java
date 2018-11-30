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

public class EditPilotoController implements Initializable {

    String[] statusChoices = new String[]{"Disponível", "Manutenção", "Descanso"};

    static Piloto pilotoList;

    static int indexList;

    private PilotoController controller = new PilotoController();

    @FXML
    public AnchorPane EditPilotoPanel;
    @FXML
    public TextField txtFldId;
    @FXML
    public TextField txtFldNome;
    @FXML
    public ComboBox cmbBoxStatus;
    @FXML
    public Label lblDuplicatedSerial;

    @FXML
    private void btnCancelEditon(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Piloto.fxml"));
            EditPilotoPanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    void btnSaveEdition(ActionEvent event) {
        boolean duplicated = false;
        String id;
        try {
            for (Piloto each : lstPilotos) {
                if (each.getIdPiloto() == pilotoList.getIdPiloto()) {
                    id = txtFldId.getText();
                    for (Piloto each2 : lstPilotos) {
                        if (id == each2.getIdPiloto()) {
                            System.err.println("DUPLICOU");
                            lblDuplicatedSerial.setText("Id já existente");
                            txtFldId.requestFocus();
                            duplicated = true;
                            break;
                        } else {
                            duplicated = false;
                        }
                    }
                    if (!duplicated) {
                        each.setIdPiloto(txtFldId.getText());
                        each.setNomePiloto(txtFldNome.getText());
//                        each.setStatusPiloto(cmbBoxStatus.getValue().toString());
                        break;
                    }
                }
            }

            if (!duplicated) {
//            PrincipalController.lstAeronaves.add(controller.aeronave);
                PrincipalController.savePilotoList(lstPilotos);
                btnCancelEditon(event);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void setAllFields() {
        //check later
        try {
            txtFldId.setText(pilotoList.getIdPiloto());
            txtFldNome.setText(pilotoList.getNomePiloto());
            cmbBoxStatus.getItems().addAll(statusChoices);
//            cmbBoxStatus.setValue(pilotoList.getStatusPiloto());
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void getPilotoObj(Piloto piloto) {
        for (Piloto each : lstPilotos) {
            if (each.getIdPiloto() == piloto.getIdPiloto()) {
                pilotoList = each;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setAllFields();
    }

    public EditPilotoController() {

    }

}
