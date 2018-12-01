package controllerReserva;

import com.sun.javafx.css.Combinator;
import controller.PrincipalController;
import static controller.PrincipalController.lstAeronaves;
import static controller.PrincipalController.lstHeliportos;
import static controller.PrincipalController.lstModelosAeronaves;
import static controller.PrincipalController.lstPilotos;
import static controller.PrincipalController.lstReservas;
import static controller.PrincipalController.mes;
import controller.ReservaController;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.AbstractList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.UUID;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import javafx.scene.layout.AnchorPane;
import model.Aeronave;
import model.DataDia;
import model.DataMes;
import model.DataSemana;
import model.Heliporto;
import model.ModeloAeronave;
import model.Piloto;
import utility.Dados;

public class InsertReservaController implements Initializable {

    //Variable to sum distance between two heliportos
    int distOrigem, distDestino, distViagem, maiorTempoDeViagem = 0;
    int tempoDeVooIdaVolta = 0;

    DataMes mesSelected;
    DataDia diaSelected;
    DataSemana semanaSelected;
    String horarioSelected;

    Heliporto origem;

    private ReservaController controller = new ReservaController();

    @FXML
    public AnchorPane InsertReservaPanel;
    @FXML
    public Button btnConfirmInsertionID;
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
    public ComboBox cmbBoxPiloto;
    @FXML
    public CheckBox chBoxConfirmarDestinos;
    @FXML
    public CheckBox chBoxConfirmarHorario;
    @FXML
    public TextField txtFldCliente;

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
        Aeronave aeroSelected;
        Piloto piloSelected;

        Aeronave aeroToReserva = null;
        Piloto piloToReserva = null;

        aeroSelected = (Aeronave) cmbBoxAeronave.getSelectionModel().getSelectedItem();
        piloSelected = (Piloto) cmbBoxPiloto.getSelectionModel().getSelectedItem();

        for (Aeronave each : lstAeronaves) {
            if (each.equals(aeroSelected)) {
                salvaAgendaAeronave(each);
                PrincipalController.saveAeronaveList(lstAeronaves);
                aeroToReserva = each;
            }
        }

        for (Piloto each : lstPilotos) {
            if (each.equals(piloSelected)) {
                salvaAgendaPiloto(each);
                PrincipalController.savePilotoList(lstPilotos);
                piloToReserva = each;
            }
        }

        controller.reserva.setCliente(txtFldCliente.getText());
        controller.reserva.setIdReserva(createID());
        controller.reserva.setOrigem((Heliporto) cmbBoxOrigem.getSelectionModel().getSelectedItem());
        controller.reserva.setDestino((Heliporto) cmbBoxDestino.getSelectionModel().getSelectedItem());
        controller.reserva.setSemana(cmbBoxSemana.getSelectionModel().getSelectedItem().toString());
        controller.reserva.setDia(cmbBoxDia.getSelectionModel().getSelectedItem().toString());
        controller.reserva.setHora(cmbBoxHorario.getSelectionModel().getSelectedItem().toString());
        controller.reserva.setAeronave(aeroToReserva);
        controller.reserva.setPiloto(piloToReserva);
        controller.reserva.setProxHorarioDisponivel(diaSelected.tableHours.get(horaValueToKey(cmbBoxHorario.getSelectionModel().getSelectedItem().toString()) + tempoDeVooIdaVolta));
        controller.reserva.setRetornoBase(diaSelected.tableHours.get(horaValueToKey(cmbBoxHorario.getSelectionModel().getSelectedItem().toString()) + (tempoDeVooIdaVolta - 1)));

        PrincipalController.lstReservas.add(controller.reserva);
        PrincipalController.saveReservaList(lstReservas);

        System.out.println(lstReservas);

        btnCancelInsertion(event);

    }

    //--------------------------------------------------------------------------
    //ORIGEM DESTINO STUFF
    @FXML
    private void cmbBoxOrigemSelected(Event event) {

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
            clearComboBoxAeronaves();
            clearComboBoxPilotos();
            btnConfirmInsertionID.setDisable(true);
        }
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
        cmbBoxHorario.setDisable(false);
        cmbBoxHorario.getItems().clear();
        //Get from table of hours
        cmbBoxHorario.getItems().addAll(verificaHorariosDisponiveis().values());
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
            clearComboBoxPilotos();
            btnConfirmInsertionID.setDisable(true);
        }
    }

    //--------------------------------------------------------------------------
    //AERONAVE STUFF
    @FXML
    private void cmbBoxAeronaveSelected(Event event) {
        setComboBoxPilotos();
    }

    private void clearComboBoxAeronaves() {
        cmbBoxAeronave.getItems().clear();
        cmbBoxAeronave.setDisable(true);
    }

    private void setComboBoxAeronaves() {
        List<Aeronave> lstAeronavesAvailable = new ArrayList<Aeronave>();
        lstAeronavesAvailable = getListAeronavesAvailable();

        cmbBoxAeronave.getItems().addAll(lstAeronavesAvailable);
        cmbBoxAeronave.setDisable(false);
    }

    private List<Aeronave> getListAeronavesAvailable() {

        List<Aeronave> lstAeronavesTemp = new ArrayList<>();

        for (Aeronave each : lstAeronaves) {
            if (each.getStatusAeronave() && each.getHeliporto().equals(origem)) {
                if (verificaAgendaAeronave(each)) {
                    lstAeronavesTemp.add(each);
                }
            }
        }
        return lstAeronavesTemp;
    }

    //--------------------------------------------------------------------------
    //PILOTO STUFF
    @FXML
    private void cmbBoxPilotosSelected(Event event) {
        btnConfirmInsertionID.setDisable(false);
    }

    private void setComboBoxPilotos() {
        List<Piloto> lstPilotosAvailable = new ArrayList<>();
        lstPilotosAvailable = getListPilotosAvailable();

        cmbBoxPiloto.getItems().addAll(lstPilotosAvailable);
        cmbBoxPiloto.setDisable(false);
    }

    private void clearComboBoxPilotos() {
        cmbBoxPiloto.getItems().clear();
        cmbBoxPiloto.setDisable(true);
    }

    private List<Piloto> getListPilotosAvailable() {

        List<Piloto> lstPilotosTemp = new ArrayList<>();

        for (Piloto each : lstPilotos) {
            if (each.getStatusPiloto() && each.getHeliporto().equals(origem)) {
                if (verificaAgendaPiloto(each)) {
                    lstPilotosTemp.add(each);
                }
            }
        }
        return lstPilotosTemp;
    }

    //--------------------------------------------------------------------------
    //FUNCOES GLOBAIS
    private void calculaDistanciaCidades(int origem, int destino) {
        distViagem = origem - destino;
        if (distViagem < 0) {
            distViagem *= -1;
        }
//        System.out.println("Viagem de " + distViagem + " km");
    }

    private void calculaTempoDeViagens(String horaSaida) {
        System.out.println("\n\nEstimativa baseada em modelos");
        for (ModeloAeronave each : lstModelosAeronaves) {
            float tempo = (float) distViagem / (float) each.getVelocidadeMedia();
            int hora = (int) tempo;
            int minutos = (int) (60 * (tempo - hora));
            System.out.println("Modelo: " + each.getModelo() + "\nEstimativa tempo de Viagem: " + hora + "h " + minutos + "m ");
            tempoDeVooIdaVolta = ((((int) Math.ceil(tempo) * 2) + 1));
            System.out.println("Hora de Saída: " + horaSaida);
            System.out.println("Hora de volta: " + diaSelected.tableHours.get(horaValueToKey(horaSaida) + tempoDeVooIdaVolta));
            System.out.println("-----------------------------------------------------------");
        }
    }

    private Map verificaHorariosDisponiveis() {
        Map<Integer, String> tableHoursTemp = new LinkedHashMap<>(diaSelected.tableHours.size());
        int tempoViagem;
        for (ModeloAeronave each : lstModelosAeronaves) {
            float tempo = (float) distViagem / (float) each.getVelocidadeMedia();
            tempoViagem = ((((int) Math.ceil(tempo) * 2) + 1));
            if (tempoViagem > maiorTempoDeViagem) {
                maiorTempoDeViagem = tempoViagem;
            }
        }
        //Copia para um map temporário as horas possíveis
        for (int i = 0; i < (diaSelected.tableHours.size() - maiorTempoDeViagem); i++) {
            tableHoursTemp.put(i, diaSelected.tableHours.get(i));
        }
        return tableHoursTemp;
    }

    private String horaKeyToValue(int key) {
        String value;
        return value = diaSelected.tableHours.get(key);
    }

    private int horaValueToKey(String value) {
        int key = 0;
        for (Entry<Integer, String> entry : diaSelected.tableHours.entrySet()) {
            if (entry.getValue().equals(value)) {
                key = entry.getKey();
            }
        }
        return key;
    }

    private boolean verificaAgendaAeronave(Aeronave aeronave) {

        if (semanaSelected.toString().equals("Semana 1")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana1().getSegunda().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana1().getTerca().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana1().getQuarta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana1().getQuinta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana1().getSexta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana1().getSabado().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana1().getDomingo().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            }
        } else if (semanaSelected.toString()
                .equals("Semana 2")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana2().getSegunda().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana2().getTerca().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana2().getQuarta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana2().getQuinta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana2().getSexta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana2().getSabado().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana2().getDomingo().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            }

        } else if (semanaSelected.toString()
                .equals("Semana 3")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana3().getSegunda().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana3().getTerca().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana3().getQuarta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana3().getQuinta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana3().getSexta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana3().getSabado().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana3().getDomingo().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            }
        } else if (semanaSelected.toString()
                .equals("Semana 4")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana4().getSegunda().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana4().getTerca().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana4().getQuarta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana4().getQuinta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana4().getSexta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana4().getSabado().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!aeronave.getMes().getSemana4().getDomingo().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            }
        }

        System.out.println("Aeronave " + aeronave + " disponível para voo nesse horário.");
        return true;
    }

    private boolean verificaAgendaPiloto(Piloto piloto) {

        if (semanaSelected.toString().equals("Semana 1")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana1().getSegunda().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana1().getTerca().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana1().getQuarta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana1().getQuinta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana1().getSexta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana1().getSabado().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana1().getDomingo().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            }
        } else if (semanaSelected.toString()
                .equals("Semana 2")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana2().getSegunda().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana2().getTerca().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana2().getQuarta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana2().getQuinta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana2().getSexta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana2().getSabado().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana2().getDomingo().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            }

        } else if (semanaSelected.toString()
                .equals("Semana 3")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana3().getSegunda().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana3().getTerca().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana3().getQuarta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana3().getQuinta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana3().getSexta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana3().getSabado().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana3().getDomingo().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            }
        } else if (semanaSelected.toString()
                .equals("Semana 4")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana4().getSegunda().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana4().getTerca().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana4().getQuarta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana4().getQuinta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana4().getSexta().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana4().getSabado().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    if (!piloto.getMes().getSemana4().getDomingo().agendaServico.get(i)) {
//                        System.out.println("false");
                        return false;
                    }
                }
            }
        }

        System.out.println("Piloto " + piloto + " disponível para voo nesse horário.");
        return true;
    }

    private boolean salvaAgendaAeronave(Aeronave aeronave) {

        if (semanaSelected.toString().equals("Semana 1")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana1().getSegunda().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana1().getTerca().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana1().getQuarta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana1().getQuinta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana1().getSexta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana1().getSabado().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana1().getDomingo().agendaServico.set(i, false);
                }
            }
        } else if (semanaSelected.toString()
                .equals("Semana 2")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana2().getSegunda().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana2().getTerca().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana2().getQuarta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana2().getQuinta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana2().getSexta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana2().getSabado().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana2().getDomingo().agendaServico.set(i, false);
                }
            }

        } else if (semanaSelected.toString()
                .equals("Semana 3")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana3().getSegunda().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana3().getTerca().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana3().getQuarta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana3().getQuinta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana3().getSexta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana3().getSabado().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana3().getDomingo().agendaServico.set(i, false);
                }
            }
        } else if (semanaSelected.toString()
                .equals("Semana 4")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana4().getSegunda().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana4().getTerca().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana4().getQuarta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana4().getQuinta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana4().getSexta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana4().getSabado().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    aeronave.getMes().getSemana4().getDomingo().agendaServico.set(i, false);
                }
            }
        }

        System.out.println("Agenda da aeronave " + aeronave + " foi alterada.");
        return true;
    }

    private boolean salvaAgendaPiloto(Piloto piloto) {

        if (semanaSelected.toString().equals("Semana 1")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana1().getSegunda().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana1().getTerca().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana1().getQuarta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana1().getQuinta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana1().getSexta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana1().getSabado().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana1().getDomingo().agendaServico.set(i, false);
                }
            }
        } else if (semanaSelected.toString()
                .equals("Semana 2")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana2().getSegunda().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana2().getTerca().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana2().getQuarta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana2().getQuinta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana2().getSexta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana2().getSabado().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana2().getDomingo().agendaServico.set(i, false);
                }
            }

        } else if (semanaSelected.toString()
                .equals("Semana 3")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana3().getSegunda().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana3().getTerca().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana3().getQuarta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana3().getQuinta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana3().getSexta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana3().getSabado().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana3().getDomingo().agendaServico.set(i, false);
                }
            }
        } else if (semanaSelected.toString()
                .equals("Semana 4")) {
            if (diaSelected.toString().equals("Segunda")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana4().getSegunda().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Terça")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana4().getTerca().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quarta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana4().getQuarta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Quinta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana4().getQuinta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sexta")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana4().getSexta().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Sábado")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana4().getSabado().agendaServico.set(i, false);
                }
            } else if (diaSelected.toString().equals("Domingo")) {
                for (int i = horaValueToKey(horarioSelected); i < horaValueToKey(horarioSelected) + tempoDeVooIdaVolta; i++) {
                    piloto.getMes().getSemana4().getDomingo().agendaServico.set(i, false);
                }
            }
        }

        System.out.println("Agenda do piloto " + piloto + " foi alterada.");
        return true;
    }

    private String createID() {
        //Creates a random serial
        UUID uuid = UUID.randomUUID();
        String serialRandom = uuid.toString();
        return serialRandom;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setComboBoxOrigem();
    }

}
