package br.com.linux_park.controller;

import br.com.linux_park.model.bean.Cor;
import br.com.linux_park.model.bean.TipoVaga;
import br.com.linux_park.model.dao.CorDAO;
import br.com.linux_park.model.dao.TipoVagaDAO;
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

        CorDAO cd = new CorDAO();
        if (cd.listarTodos().size() <= 0) {
            Cor[] cc = {
                new Cor("BRANCO"), new Cor("PRETO"), new Cor("PRATA"), new Cor("VERMELHO"),
                new Cor("AMARELO"), new Cor("AZUL"), new Cor("VERDE"), new Cor("MARROM")
            };

            for (Cor c : cc) {
                cd.inserir(c);
            }

        }
//        Cor cor = new Cor("MARROM");
//        System.out.println(cd.inserir(cor));
//        Cor c = cd.get("marrom");
//        c.setDescricao("Marrom");
//        cd.alterar(c);
//        System.out.println(cd.listarTodos());

        TipoVagaDAO tvd = new TipoVagaDAO();
        if (tvd.listarTodos().size() <= 0) {
            TipoVaga[] tv = {
                new TipoVaga("2 eixos simples", 3.5f, 50), new TipoVaga("2 eixos", 5.5f, 20), new TipoVaga("3 eixos", 15.5f, 5)
            };

            for (TipoVaga t : tv) {
                tvd.inserir(t);
            }
        }

//        TipoVaga tv = new TipoVaga();
//        tv.setDescricao("AZUL");
//
//        System.out.println("1 " + cd.listarTodos());
//
//        System.out.println(tvd.inserir(cor));
//
//        System.out.println("2 " + cd.listarTodos());
//        if (ev.vDAO.existente(inpPlaca.getText())) {
//            ev.setVeiculo(inpPlaca.getText());
//            lblPlaca.setText(vv.getPlaca());
//            lblMarcaModelo.setText(vv.getMarca().getNome() + " " + vv.getModelo().getNome());
//            lblFabricacaoAnoModelo.setText("" + vv.getFabricacao() + "/" + vv.getModelo().getAno());
//            lblCondutor.setText(vv.getCondutor().getNome());
//            lblCor.setText(vv.getCor().getNome());
//            lblCidadeUF.setText(vv.getCidade().getNome() + "/" + vv.getEstado().getUf());
//            System.out.println(vv.getCondutor().getNome());
//        } else {
//            lblPlaca.setText("Não encontrado");
//            lblMarcaModelo.setText("Não encontrado");
//            lblFabricacaoAnoModelo.setText("Não encontrado");
//            lblCondutor.setText("Não encontrado");
//            lblCor.setText("Não encontrado");
//            lblCidadeUF.setText("Não encontrado");
//            System.out.println("Não encontrado");
//        }
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
