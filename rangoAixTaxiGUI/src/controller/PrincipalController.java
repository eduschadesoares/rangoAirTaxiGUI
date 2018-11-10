package controller;

import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.AbstractList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import utility.AeronaveFiler;
import model.Student;

public class PrincipalController implements Initializable {

//    @FXML
//    private Label label;
//
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }
    @FXML
    public AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
//        ArrayList<Student> students = new ArrayList();
//        ArrayList<Student> students_volta = new ArrayList();
//
//        AeronaveFiler objectIO = new AeronaveFiler();
//
//        Student student = new Student("John", "Frost", 22);
//        students.add(student);
//        student = new Student("jose", "fa", 32);
//        students.add(student);
//        student = new Student("rafael", "sfasfasasf", 32);
//        students.add(student);
//        student = new Student("eduardo", "yeyq", 32);
//        students.add(student);
//        objectIO.WriteObjectToFile(students);
//
//        for (Student lista : students) {
//            System.out.println(lista);
//        }
//
//        //Read object from file
//        students_volta = (ArrayList<Student>) objectIO.ReadObjectFromFile();
//        System.out.println(students_volta);
//
//        for (Student lista : students_volta) {
//            System.out.println(lista);
//        }
    }

}
