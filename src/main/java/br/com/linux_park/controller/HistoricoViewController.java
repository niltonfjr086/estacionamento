package br.com.linux_park.controller;

import br.com.linux_park.model.bean.Estaciona;
import br.com.linux_park.model.vo.HistoricoVO;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author n
 */
public class HistoricoViewController implements Initializable {

    private PrincipalViewController mainController;

    private final HistoricoVO hv = new HistoricoVO();

    ObservableList<Estaciona> listaView = FXCollections.observableArrayList();

    @FXML
    TableView<Estaciona> tblHistorico;

    @FXML
    TableColumn<Estaciona, String> clnVeiculo = new TableColumn<>("Veículo");

    @FXML
    TableColumn<Estaciona, Date> clnEntrada = new TableColumn<>("Entrada");

    @FXML
    TableColumn<Estaciona, Float> clnValorHora = new TableColumn<>("Valor Hora");

    @FXML
    TableColumn<Estaciona, Date> clnSaida = new TableColumn<>("Saída");

    @FXML
    TableColumn<Estaciona, Float> clnValor = new TableColumn<>("Valor");

    @FXML
    private ComboBox<String> selectOnePeriodo;
    
    private String[] periodos = {"Diário", "Semanal" , "Mensal"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setDadosHistoricoView();

    }

    public void setMainController(PrincipalViewController mainController) {
        this.mainController = mainController;
    }

    public ObservableList<Estaciona> getVeiculoVOData() {
        return listaView;
    }

//    private void setDadosHistoricoView(ActionEvent event) {
//        setDadosHistoricoView();
//    }
    
    
    
        //LISTENER - CHANGE IMPLEMENTANDO CLASSE ANÔNIMA SEM ANOTAÇÃO FXML INICIANDO EXPLICITAMENTE NO CONTROLLER
    private void viculaPeriodoAoHistorico(final ComboBox<String> newPeriodo) {
        newPeriodo.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                listaPeriodo();
            }

        });
        
    }

    private void listaPeriodo() {
        
//        if (this.selectOneCor.getItems().size() <= 0 || this.selectOneCor == null) {
//            this.selectOneModelo.setDisable(true);
//            this.selectOneModelo.setOpacity(1);
//        }
//
//        if (this.selectOneMarca.getValue() != null) {
//            this.selectOneModelo.getItems().clear();
//            if (this.selectOneTipoVeiculo.getValue() == null) {
//                this.selectOneModelo.getItems().addAll(
//                        this.ev.modeloDAO.listarTodos(this.selectOneMarca.getValue().getId())
//                );
//            } else {
//                this.selectOneModelo.getItems().addAll(
//                        this.ev.modeloDAO.listarTodos(this.selectOneMarca.getValue().getId(), this.selectOneTipoVeiculo.getValue().getId())
//                );
//            }
//        }
    }

    public void setDadosHistoricoView() {
        List<Estaciona> lista = hv.eDAO.listarTodos(false);
        for (Estaciona u : lista) {
            listaView.add(u);
            clnVeiculo.setCellValueFactory(new PropertyValueFactory<>("veiculo"));
            clnEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
            clnValorHora.setCellValueFactory(new PropertyValueFactory<>("valorHora"));
            clnSaida.setCellValueFactory(new PropertyValueFactory<>("dataSaida"));
            clnValor.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        }
        tblHistorico.setItems(listaView);
    }

    private void showDetalhes(ActionEvent event) {
        hv.setEstaciona(tblHistorico.getSelectionModel().getSelectedItem());
//            vv = uv.getVeiculo();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Detalhes da utilização");
//            alert.setHeaderText("Utilizacação ID: " + vv.getId() + "\n"
//                    + "Data de entrada: " + vv.getDtInclusao() + "\n"
//                    + "Data de saída: " + uv.getDtSaida() + "\n"
//                    + "Valor hora: " + uv.getValorHora() + "\n"
//                    + "Valor total: " + uv.getValor() + "\n"
//                    + "Carro: " + vv);
        //alert.setContentText("contentText");
//            alert.showAndWait();
    }
}
