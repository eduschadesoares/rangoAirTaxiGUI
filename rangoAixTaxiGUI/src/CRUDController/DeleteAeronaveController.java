package CRUDController;

import controller.AeronaveController;
import controller.PrincipalController;
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

public class DeleteAeronaveController implements Initializable {

    String[] statusChoices = new String[]{"Disponível", "Manutenção", "Fora de Uso"};

    static Aeronave aeronaveList;

    static int indexList;

    private AeronaveController controller = new AeronaveController();

    @FXML
    public AnchorPane DeleteAeronavePanel;
    @FXML
    public Label lblInfoAeronave;

    @FXML
    private void btnCancelDeletion(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Aeronave.fxml"));
            DeleteAeronavePanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    void btnSaveDeletion(ActionEvent event) {
        try {
            PrincipalController.lstAeronaves.remove(aeronaveList);
            PrincipalController.saveAeronaveList(lstAeronaves);

        } catch (Exception e) {
            System.out.println(e);
        }

        btnCancelDeletion(event);
    }

    public void setAllFields() {
        lblInfoAeronave.setText(aeronaveList.toString());
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

    public DeleteAeronaveController() {

    }

}
