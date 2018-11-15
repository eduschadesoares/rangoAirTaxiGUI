package CRUDController;

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

public class DeletePilotoController implements Initializable {

    static Piloto pilotoList;

    static int indexList;

    private PilotoController controller = new PilotoController();

    @FXML
    public AnchorPane DeletePilotoPanel;
    @FXML
    public Label lblInfoPiloto;

    @FXML
    private void btnCancelDeletion(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Piloto.fxml"));
            DeletePilotoPanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    void btnSaveDeletion(ActionEvent event) {
        try {
            PrincipalController.lstPilotos.remove(pilotoList);
            PrincipalController.savePilotoList(lstPilotos);

        } catch (Exception e) {
            System.out.println(e);
        }

        btnCancelDeletion(event);
    }

    public void setAllFields() {
        lblInfoPiloto.setText(pilotoList.toString());
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

    public DeletePilotoController() {

    }

}
