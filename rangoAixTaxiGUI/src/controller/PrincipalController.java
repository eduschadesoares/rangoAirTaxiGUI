package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import utility.AeronaveFiler;
import model.Student;

public class PrincipalController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AeronaveFiler objectIO = new AeronaveFiler();

        Student student = new Student("John", "Frost", 22);
        objectIO.WriteObjectToFile(student);

        //Read object from file
        Student st = (Student) objectIO.ReadObjectFromFile();
        System.out.println(st);

    }

}
