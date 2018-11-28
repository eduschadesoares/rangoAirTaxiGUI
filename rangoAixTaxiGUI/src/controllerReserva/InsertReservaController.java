package controllerReserva;

import static controller.PrincipalController.lstAeronaves;
import static controller.PrincipalController.lstHeliportos;
import static controller.PrincipalController.lstModelosAeronaves;
import static controller.PrincipalController.mes;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.AbstractList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import javafx.scene.layout.AnchorPane;
import model.Aeronave;
import model.DataDia;
import model.DataMes;
import model.DataSemana;
import model.Heliporto;
import model.ModeloAeronave;
import utility.Dados;

public class InsertReservaController implements Initializable {

    //Variable to sum distance between two heliportos
    int distOrigem, distDestino, distViagem, maiorTempoDeViagem = 0;

    DataMes mesSelected;
    DataDia diaSelected;
    DataSemana semanaSelected;
    String horarioSelected;

    @FXML
    public AnchorPane InsertReservaPanel;
    @FXML
    public ComboBox cmbBoxOrigem;
    @FXML
    public ComboBox cmbBoxDestino;
    @FXML
    public ComboBox cmbBoxSemana;
    @FXML
    public ComboBox cmbBoxDia;
    @FXML
    public ComboBox cmbBoxHorario;
    @FXML
    public ComboBox cmbBoxAeronave;
    @FXML
    public CheckBox chBoxConfirmarDestinos;
    @FXML
    public CheckBox chBoxConfirmarHorario;

    @FXML
    private void btnCancelInsertion(ActionEvent event) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxmlReservas/ReservaViagens.fxml"));
            InsertReservaPanel.getChildren().setAll(pane);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @FXML
    private void btnConfirmInsertion(ActionEvent event) {

    }

    @FXML
    private void cmbBoxOrigemSelected(Event event) {
        Heliporto origem;

        List<Heliporto> lstHeliportosTemp = new ArrayList<Heliporto>();

        cmbBoxDestino.getItems().clear();

        origem = (Heliporto) cmbBoxOrigem.getSelectionModel().getSelectedItem();
        distOrigem = origem.getKmHeliporto();

        for (Heliporto each : lstHeliportos) {
            if (each != origem) {
                lstHeliportosTemp.add(each);
            }
        }

        cmbBoxDestino.getItems().addAll(lstHeliportosTemp);
        cmbBoxDestino.setValue(lstHeliportosTemp.get(0));
        cmbBoxDestino.setDisable(false);
    }

    @FXML
    private void cmbBoxDestinoSelected(Event event) {
        Heliporto destino;
        destino = (Heliporto) cmbBoxDestino.getSelectionModel().getSelectedItem();
        try {
            distDestino = destino.getKmHeliporto();
        } catch (Exception e) {
//            System.out.println("RS");
        }
        chBoxConfirmarDestinos.setDisable(false);
    }

    @FXML
    private void confirmaDestinos(Event event) {
        if (chBoxConfirmarDestinos.isSelected()) {
            cmbBoxOrigem.setDisable(true);
            cmbBoxDestino.setDisable(true);
            calculaDistanciaCidades(distOrigem, distDestino);
            setComboBoxSemanas();
        } else {
            cmbBoxOrigem.setDisable(false);
            cmbBoxDestino.setDisable(false);
            clearComboBoxSemanas();
        }
    }

    private void calculaDistanciaCidades(int origem, int destino) {
        distViagem = origem - destino;
        if (distViagem < 0) {
            distViagem *= -1;
        }
        System.out.println(distViagem);
    }

    private void calculaTempoDeViagens(String horaSaida) {
        int tempoDeVoo;
        for (ModeloAeronave each : lstModelosAeronaves) {
            float tempo = (float) distViagem / (float) each.getVelocidadeMedia();
            int hora = (int) tempo;
            int minutos = (int) (60 * (tempo - hora));
            System.out.println("\nModelo: " + each.getModelo() + "\nEstimativa tempo de Viagem: " + hora + "h " + minutos + "m ");

            tempoDeVoo = ((((int) Math.ceil(tempo) * 2) + 1));

            System.out.println("Hora de Saída: " + horaSaida);
            int keySaida = 0;
            for (Entry<Integer, String> entry : diaSelected.tableHours.entrySet()) {
                if (entry.getValue().equals(horaSaida)) {
                    keySaida = entry.getKey();
                }
            }
            System.out.println("Hora de volta: " + diaSelected.tableHours.get(keySaida + tempoDeVoo));
            System.out.println("-----------------------------------------------------------");
        }
    }

    private Map verificaHorariosDisponiveis() {
        Map<Integer, String> tableHoursTemp = new LinkedHashMap<>(diaSelected.tableHours.size());
        int tempoViagem;
        for (ModeloAeronave each : lstModelosAeronaves) {
            float tempo = (float) distViagem / (float) each.getVelocidadeMedia();

            tempoViagem = ((((int) Math.ceil(tempo) * 2) + 1));
            if(tempoViagem > maiorTempoDeViagem) {
                maiorTempoDeViagem = tempoViagem;
            }   
        }

        for(int i = 0; i < (diaSelected.tableHours.size() - maiorTempoDeViagem); i++) {
            tableHoursTemp.put(i, diaSelected.tableHours.get(i));
        }
        
        System.out.println(tableHoursTemp);
        
        return tableHoursTemp;
    }

    private void setComboBoxOrigem() {
        cmbBoxOrigem.getItems().addAll(lstHeliportos);
    }

    //--------------------------------------------------------------------------
    //DATA STUFF
    private void setComboBoxSemanas() {
        cmbBoxSemana.setDisable(false);
        cmbBoxSemana.getItems().addAll(mes.getSemana1());
        cmbBoxSemana.getItems().addAll(mes.getSemana2());
        cmbBoxSemana.getItems().addAll(mes.getSemana3());
        cmbBoxSemana.getItems().addAll(mes.getSemana4());

    }

    private void clearComboBoxSemanas() {
        cmbBoxSemana.getItems().clear();
        cmbBoxDia.getItems().clear();
        cmbBoxHorario.getItems().clear();

        cmbBoxSemana.setDisable(true);
        cmbBoxDia.setDisable(true);
        cmbBoxHorario.setDisable(true);
        chBoxConfirmarHorario.setSelected(false);
        chBoxConfirmarHorario.setDisable(true);
    }

    @FXML
    private void cmbBoxSemanaSelected(Event event) {
        semanaSelected = (DataSemana) cmbBoxSemana.getSelectionModel().getSelectedItem();

//        System.out.println(semanaSelected);
        cmbBoxDia.setDisable(false);
        cmbBoxDia.getItems().clear();

        cmbBoxDia.getItems().addAll(semanaSelected.getSegunda());
        cmbBoxDia.getItems().addAll(semanaSelected.getTerca());
        cmbBoxDia.getItems().addAll(semanaSelected.getQuarta());
        cmbBoxDia.getItems().addAll(semanaSelected.getQuinta());
        cmbBoxDia.getItems().addAll(semanaSelected.getSexta());
        cmbBoxDia.getItems().addAll(semanaSelected.getSabado());
        cmbBoxDia.getItems().addAll(semanaSelected.getDomingo());

//        cmbBoxDia.setValue(semanaSelected.getSegunda());
    }

    @FXML
    private void cmbBoxDiaSelected(Event event) {
        diaSelected = (DataDia) cmbBoxDia.getSelectionModel().getSelectedItem();
//        System.out.println(diaSelected);

        cmbBoxHorario.setDisable(false);
        cmbBoxHorario.getItems().clear();
        //Get from table of hours

        cmbBoxHorario.getItems().addAll(verificaHorariosDisponiveis().values());
//        cmbBoxHorario.getItems().addAll(diaSelected.tableHours.values());

    }

    @FXML
    private void cmbBoxHorarioSelected(Event event) {
        horarioSelected = cmbBoxHorario.getSelectionModel().getSelectedItem().toString();

        calculaTempoDeViagens(horarioSelected);

        chBoxConfirmarHorario.setDisable(false);
    }

    @FXML
    private void confirmaHorario(Event event) {
        if (chBoxConfirmarHorario.isSelected()) {
            cmbBoxSemana.setDisable(true);
            cmbBoxDia.setDisable(true);
            cmbBoxHorario.setDisable(true);
            setComboBoxAeronaves();
        } else {
            cmbBoxSemana.setDisable(false);
            cmbBoxDia.setDisable(false);
            cmbBoxHorario.setDisable(false);
            clearComboBoxAeronaves();
        }
    }

    //--------------------------------------------------------------------------
    //AERONAVE STUFF
    @FXML
    private void cmbBoxAeronaveSelected(Event event) {

    }

    private void clearComboBoxAeronaves() {
        cmbBoxAeronave.getItems().clear();
        cmbBoxAeronave.setDisable(true);
    }

    private void setComboBoxAeronaves() {
        List<Aeronave> lstAeronavesAvailable = new ArrayList<Aeronave>();
        lstAeronavesAvailable = checkAeronavesAvailable();

        cmbBoxAeronave.getItems().addAll(lstAeronavesAvailable);
        cmbBoxAeronave.setDisable(false);
    }

    private List<Aeronave> checkAeronavesAvailable() {

        List<Aeronave> lstAeronavesTemp = new ArrayList<Aeronave>();

        for (Aeronave each : lstAeronaves) {
            if (semanaSelected.toString().equals("Semana 1")) {
                if (diaSelected.toString().equals("Segunda")) {
//                    lstAeronaves.get(0).getMes().getSemana1().getSegunda().agendaServico.replace(horarioSelected, false);

                } else if (diaSelected.toString().equals("Terça")) {

                } else if (diaSelected.toString().equals("Quarta")) {

                } else if (diaSelected.toString().equals("Quinta")) {

                } else if (diaSelected.toString().equals("Sexta")) {

                } else if (diaSelected.toString().equals("Sábado")) {

                } else if (diaSelected.toString().equals("Domingo")) {

                }
            } else if (semanaSelected.toString()
                    .equals("Semana 2")) {
                if (diaSelected.toString().equals("Segunda")) {

                } else if (diaSelected.toString().equals("Terça")) {

                } else if (diaSelected.toString().equals("Quarta")) {

                } else if (diaSelected.toString().equals("Quinta")) {

                } else if (diaSelected.toString().equals("Sexta")) {

                } else if (diaSelected.toString().equals("Sábado")) {

                } else if (diaSelected.toString().equals("Domingo")) {

                }

            } else if (semanaSelected.toString()
                    .equals("Semana 3")) {
                if (diaSelected.toString().equals("Segunda")) {

                } else if (diaSelected.toString().equals("Terça")) {

                } else if (diaSelected.toString().equals("Quarta")) {

                } else if (diaSelected.toString().equals("Quinta")) {

                } else if (diaSelected.toString().equals("Sexta")) {

                } else if (diaSelected.toString().equals("Sábado")) {

                } else if (diaSelected.toString().equals("Domingo")) {

                }
            } else if (semanaSelected.toString()
                    .equals("Semana 4")) {
                if (diaSelected.toString().equals("Segunda")) {

                } else if (diaSelected.toString().equals("Terça")) {

                } else if (diaSelected.toString().equals("Quarta")) {

                } else if (diaSelected.toString().equals("Quinta")) {

                } else if (diaSelected.toString().equals("Sexta")) {

                } else if (diaSelected.toString().equals("Sábado")) {

                } else if (diaSelected.toString().equals("Domingo")) {

                }
            }
        }

//        System.out.println(
//                "FIRST " + lstAeronaves.get(0).getMes().getSemana1().getSegunda().agendaServico.get(horarioSelected));
//        mes2.getSemana1().getSegunda().agendaServico.replace(horarioSelected, false);
//        System.out.println(
//                "DEU " + lstAeronaves.get(0).getMes().getSemana1().getSegunda().agendaServico.get(horarioSelected));
        return lstAeronavesTemp;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBoxOrigem();
    }

}
