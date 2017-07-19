package br.com.linux_park.controller;

import br.com.linux_park.model.bean.Estaciona;
import br.com.linux_park.model.bean.Veiculo;
import br.com.linux_park.model.vo.SaidaVO;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author n
 */
public class SaidaViewController implements Initializable {

    private PrincipalViewController mainController;

    private final SaidaVO sv = new SaidaVO();

    ObservableList<Estaciona> listaView = FXCollections.observableArrayList();

    @FXML
    private Button btnEncerrarUtil;
    @FXML
    private TableView<Estaciona> tblUtilizacao;
    @FXML
    TableColumn<Estaciona, Veiculo> clnVeiculo = new TableColumn<>("Veículo");

    @FXML
    TableColumn<Estaciona, Date> clnEntrada = new TableColumn<>("Entrada");

    @FXML
    TableColumn<Estaciona, Float> clnValorHora = new TableColumn<>("Valor Hora");
    @FXML
    private Label lblPrevisaoPagar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setDadosSaidaView();

        tblUtilizacao.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> preveisaoValorSaida(newValue));
    }

    public void setMainController(PrincipalViewController mainController) {
        this.mainController = mainController;
    }

    private void preveisaoValorSaida(Estaciona newValue) {
        lblPrevisaoPagar.setText(newValue.getValorHora().toString());
    }

    @FXML
    private void actionEncerrarUtil(ActionEvent event) {
        Estaciona e = tblUtilizacao.getSelectionModel().getSelectedItem();
        if (e != null) {
            if (e.getVeiculo() != null) {

                encerrarUtilDialog(e);
            }
        }
        sv.setEstaciona(tblUtilizacao.getSelectionModel().getSelectedItem());
    }

    public void encerrarUtilDialog(Estaciona e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Veículo Estacionado");
        alert.setHeaderText("Confirmar saída?");
        alert.setContentText(e.toString());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            Boolean b = sv.eDAO.excluir(e.getId());
            if (b != null) {

                mainController.chamaSaidaView();
                mainController.chamaHistoricoView();
            }

        } else {

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Saída não inserida");
            alert2.setHeaderText("Inserção cancelada.\n"
                    + "Finalize o procedimento de saída novamente");
            alert2.showAndWait();
        }
    }

    public void setDadosSaidaView() {
        List<Estaciona> lista = sv.eDAO.listarTodos(true);
        for (Estaciona u : lista) {
            listaView.add(u);
            clnVeiculo.setCellValueFactory(new PropertyValueFactory<>("veiculo"));
            clnEntrada.setCellValueFactory(new PropertyValueFactory<>("dataEntrada"));
            clnValorHora.setCellValueFactory(new PropertyValueFactory<>("valorHora"));
        }
        tblUtilizacao.setItems(listaView);
    }
}
