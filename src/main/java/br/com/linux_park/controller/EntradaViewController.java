package br.com.linux_park.controller;

import br.com.linux_park.model.bean.Cor;
import br.com.linux_park.model.bean.TipoVaga;
import br.com.linux_park.model.bean.TipoVeiculo;
import br.com.linux_park.model.dao.CorDAO;
import br.com.linux_park.model.dao.TipoVagaDAO;
import br.com.linux_park.model.dao.TipoVeiculoDAO;
import br.com.linux_park.model.vo.EntradaVO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author n
 */
public class EntradaViewController implements Initializable {

    private PrincipalViewController mainController;

    private final EntradaVO ev = new EntradaVO();

    @FXML
    private TextField inpPlaca;
    @FXML
    private Button psqPlaca;

    @FXML
    private Label lblPlaca;
    @FXML
    private Label lblMarcaModelo;
    @FXML
    private Label lblFabricacaoAnoModelo;
    @FXML
    private Label lblCondutor;
    @FXML
    private Label lblCor;
    @FXML
    private Label lblCidadeUF;

    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        habilitarPlacaMask(this.inpPlaca);
        ev.verificaDadosIniciais();

    }

    public void setMainController(PrincipalViewController mainController) {
        this.mainController = mainController;
    }

    private static void habilitarPlacaMask(final TextField inpPlaca) {
        inpPlaca.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                if (newValue.length() > 0 && newValue.length() < 4) {

                    int tecla = newValue.charAt(newValue.length() - 1);
                    if ((tecla > 64 && tecla < 91) || (tecla > 96 && tecla < 123)) {
                        inpPlaca.setText(newValue.toUpperCase());
                    } else {
                        newValue = oldValue;
                        inpPlaca.setText(oldValue);
                    }
                    if (newValue.length() == 3 && oldValue.length() != 4) {
                        inpPlaca.setText(newValue.toUpperCase() + "-");
                    }
                    if (newValue.length() == 3 && oldValue.length() == 4) {
                        inpPlaca.setText(newValue.substring(0, 2));
                    }
                } else if (newValue.length() > 4 && newValue.length() < 9) {
                    if (!Character.isDigit(newValue.charAt(newValue.length() - 1))) {
                        inpPlaca.setText(oldValue);
                    }
                } else if (newValue.length() > 8) {
                    inpPlaca.setText(oldValue);
                } else if (newValue.length() > 0 && newValue.length() < 9) {
                    inpPlaca.setText(newValue.toUpperCase());
                }
            }
        }
        );
    }

    @FXML
    private void setDadosEntradaView(ActionEvent event) {
        System.out.println("TESTE 1");

    }

    @FXML
    private void confirmar(ActionEvent event) {

        System.out.println("TESTE 2");
//       VeiculoDB veiculoA = new VeiculoDB("XXX9998", 1, 1, 1, 1);
//       VeiculoDAO vd = new VeiculoDAO();
//       vd.inserir(veiculoA);

//        if (ev.vDAO.existente(inpPlaca.getText())) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Recibo de entrada");
//            alert.setHeaderText("Utilizacação ID: " + uvd.inserir(new UtilizacaoVO(vv, new Date())) + "\n"
//                    + "Data de entrada: " + vv.getDtInclusao() + "\n"
//                    + "Carro: " + vv);
//            alert.setContentText("Registro incluído com sucesso.");
//            alert.showAndWait();
//            mainController.chamaSaidaView();
//        }
    }

    @FXML
    private void cancelarLimpar(ActionEvent event) {
        mainController.chamaEntradaView();
    }

}
