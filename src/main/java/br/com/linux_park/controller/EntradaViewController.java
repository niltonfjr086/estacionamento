/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linux_park.controller;

import br.com.linux_park.model.bean.Cor;
import br.com.linux_park.model.bean.Estaciona;
import br.com.linux_park.model.bean.Marca;
import br.com.linux_park.model.bean.Modelo;
import br.com.linux_park.model.bean.TipoVeiculo;
import br.com.linux_park.model.bean.Veiculo;
import br.com.linux_park.model.vo.EntradaVO;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Nilton
 */
public class EntradaViewController implements Initializable {

    private PrincipalViewController mainController;

    private final EntradaVO ev = new EntradaVO();

    @FXML
    private Label lblPlaca;
    @FXML
    private TextField txtPlaca;
    @FXML
    private Label lblMarca;
    @FXML
    private ComboBox<Marca> selectOneMarca;
    @FXML
    private Label lblModelo;
    @FXML
    private ComboBox<Modelo> selectOneModelo;
    @FXML
    private Label lblCor;
    @FXML
    private ComboBox<Cor> selectOneCor;
    @FXML
    private Label lblTipo;
    @FXML
    private ComboBox<TipoVeiculo> selectOneTipoVeiculo;
    @FXML
    private Label lblQtdMoto;
    @FXML
    private Label lblVagasMoto;
    @FXML
    private Label lblQtdCarro;
    @FXML
    private Label lblVagasCarro;
    @FXML
    private Label lblQtdCaminhonete;
    @FXML
    private Label lblVagasCaminhonete;
    @FXML
    private Label lblQtdCaminhao;
    @FXML
    private Label lblVagasCaminhao;
    @FXML
    private Button btnPesquisarPlaca;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnLimpar;
    @FXML
    private Label lblValorTolerancia;
    @FXML
    private Label lblMinutosTolerancia;

    private StringProperty bindTolerancia;
    @FXML
    private Label lblUnidadeMedida;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ev.verificaDadosIniciais();

        defineVagasDisponiveis();
        defineConfiguracoes();

        //INICIANDO CHANGE LISTENERS EXPLICITAMENTE NO CONTROLLER
        habilitarPlacaMask(this.txtPlaca);
        viculaModelosMarca(this.selectOneMarca);
        viculaTipoAoModelo(this.selectOneModelo);
        viculaModelosAoTipo(this.selectOneTipoVeiculo);

        listas();
    }

    public void setMainController(PrincipalViewController mainController) {
        this.mainController = mainController;
    }

    void defineVagasDisponiveis() {

        List<Estaciona> e = ev.estacionaDAO.listarTodos(true);

        defineQtdMotos(e);
        defineQtdCarros(e);
        defineQtdCaminhonetes(e);
        defineQtdeCaminhoes(e);

    }

    private Integer calculaNumeroDisponivel(String tipo, List<Estaciona> e) {
        int estacionados = 0;
        for (Estaciona ee : e) {
            if (ee.getVeiculo().getModelo().getTipoVeiculo().getDescricao().equals(tipo)) {
                estacionados++;
            }
        }
        Integer disponivel = ev.tipoVagaDAO.getPorId(ev.tipoVeiculoDAO.get(tipo).getTipoVaga().getId()).getQuantidade()
                - estacionados;

        return disponivel;
    }

    private void defineQtdMotos(List<Estaciona> e) {
        lblVagasMoto.setText(calculaNumeroDisponivel("Moto", e).toString());
    }

    private void defineQtdCarros(List<Estaciona> e) {
        lblVagasCarro.setText(calculaNumeroDisponivel("Carro", e).toString());
    }

    private void defineQtdCaminhonetes(List<Estaciona> e) {
        lblVagasCaminhonete.setText(calculaNumeroDisponivel("Caminhonete", e).toString());
    }

    private void defineQtdeCaminhoes(List<Estaciona> e) {
        lblVagasCaminhao.setText(calculaNumeroDisponivel("Caminhao", e).toString());
    }

    private void defineConfiguracoes() {
        defineToleranciaDefault();
    }

    private void defineToleranciaDefault() {
        bindTolerancia = new SimpleStringProperty(ev.estacionaDAO.getToleranciaDefault().toString());//.addListener();
        lblMinutosTolerancia.setText(bindTolerancia.getValue());
    }

    private void habilitarPlacaMask(final TextField inpPlaca) {
        inpPlaca.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {

                if (newValue.length() == 8) {
                    actionPesquisarPlaca();
                }

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

    private void listas() {
        this.listaMarcas();
        this.listaModelos();
        this.listaCores();
        this.listaTiposVeiculo();
    }

    private void listaMarcas() {
        this.selectOneMarca.setDisable(true);
        this.selectOneMarca.setOpacity(1);
        this.selectOneMarca.getItems().addAll(this.ev.marcaDAO.listarTodos());
    }

    //LISTENER - CHANGE IMPLEMENTANDO CLASSE ANÔNIMA SEM ANOTAÇÃO FXML INICIANDO EXPLICITAMENTE NO CONTROLLER
    private void viculaModelosMarca(final ComboBox<Marca> newMarca) {
        newMarca.valueProperty().addListener(new ChangeListener<Marca>() {
            @Override
            public void changed(ObservableValue<? extends Marca> observable, Marca oldValue, Marca newValue) {
                listaModelos();
            }
        });
    }

    private void listaModelos() {

        if (this.selectOneCor.getItems().size() <= 0 || this.selectOneCor == null) {
            this.selectOneModelo.setDisable(true);
            this.selectOneModelo.setOpacity(1);
        }

        if (this.selectOneMarca.getValue() != null) {
            this.selectOneModelo.getItems().clear();
            if (this.selectOneTipoVeiculo.getValue() == null) {
                this.selectOneModelo.getItems().addAll(
                        this.ev.modeloDAO.listarTodos(this.selectOneMarca.getValue().getId())
                );
            } else {
                this.selectOneModelo.getItems().addAll(
                        this.ev.modeloDAO.listarTodos(this.selectOneMarca.getValue().getId(), this.selectOneTipoVeiculo.getValue().getId())
                );
            }
        }
    }

    //LISTENER - CHANGE IMPLEMENTANDO CLASSE ANÔNIMA SEM ANOTAÇÃO FXML INICIANDO EXPLICITAMENTE 
    private void viculaTipoAoModelo(final ComboBox<Modelo> newModelo) {
        newModelo.valueProperty().addListener(new ChangeListener<Modelo>() {
            @Override
            public void changed(ObservableValue<? extends Modelo> observable, Modelo oldValue, Modelo newValue) {
                if (selectOneModelo.getValue() != null) {
                    selectOneTipoVeiculo.setDisable(true);
                    selectOneTipoVeiculo.setValue(selectOneModelo.getValue().getTipoVeiculo());
                }

            }
        });
    }

    private void listaCores() {
        this.selectOneCor.setDisable(true);
        this.selectOneCor.setOpacity(1);
        this.selectOneCor.getItems().addAll(this.ev.corDAO.listarTodos());
    }

    private void listaTiposVeiculo() {
        this.selectOneTipoVeiculo.setDisable(true);
        this.selectOneTipoVeiculo.setOpacity(1);
        this.selectOneTipoVeiculo.getItems().addAll(this.ev.tipoVeiculoDAO.listarTodos());
    }

    //LISTENER - CHANGE IMPLEMENTANDO CLASSE ANÔNIMA SEM ANOTAÇÃO FXML INICIANDO EXPLICITAMENTE 
    private void viculaModelosAoTipo(final ComboBox<TipoVeiculo> newTipoVeiculo) {
        newTipoVeiculo.valueProperty().addListener(new ChangeListener<TipoVeiculo>() {
            @Override
            public void changed(ObservableValue<? extends TipoVeiculo> observable, TipoVeiculo oldValue, TipoVeiculo newValue) {
                if (selectOneModelo.getValue() == null) {
                    listaModelos();
                }
            }
        });
    }

    private void actionPesquisarPlaca() {

        if (this.ev.veiculoDAO.existente(this.txtPlaca.getText())) {

            if (this.ev.veiculoDAO.utilizando(this.txtPlaca.getText())) {

                this.ev.setVeiculo(this.ev.veiculoDAO.get(this.txtPlaca.getText()));

                if (!ev.estacionaDAO.utilizando(String.valueOf(ev.getVeiculo().getId()))) {

                    this.selectOneMarca.setValue(this.ev.getVeiculo().getMarca());
                    this.selectOneTipoVeiculo.setValue(this.ev.getVeiculo().getModelo().getTipoVeiculo());
                    this.listaModelos();
                    this.selectOneModelo.setValue(this.ev.getVeiculo().getModelo());
                    this.selectOneCor.setValue(this.ev.getVeiculo().getCor());

                    this.selectOneMarca.setDisable(true);
                    this.selectOneModelo.setDisable(true);
                    this.selectOneCor.setDisable(true);
                    this.selectOneTipoVeiculo.setDisable(true);

                    insereEstacionaDialog();

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Veículo estacionado");
                    alert.setHeaderText(ev.getVeiculo().toString());
                    alert.setContentText("Tentativa de dupla utilização.");
                    alert.showAndWait();
                }
            }
        } else {
            if (validaCampos(true)) {

                insereVeiculoDialog();

            } else {
                actionEditar();
            }
        }
    }

    //LISTENER - ACTION IMPLEMENTANDO ANOTAÇÃO FXML INICIANDO IMPLICITAMENTE
    @FXML
    private void actionPesquisarPlaca(ActionEvent event) {
        actionPesquisarPlaca();
    }

    private Boolean validaCampos(Boolean b) {
        Boolean temPlaca = this.txtPlaca.getText().length() == 8;
        Boolean temMarca = this.selectOneMarca.getValue() != null;
        Boolean temTipoVeiculo = this.selectOneTipoVeiculo.getValue() != null;
        Boolean temModelo = this.selectOneModelo.getValue() != null;
        Boolean temCor = this.selectOneCor.getValue() != null;

        Boolean todosValidos = true;
        String mensagem = "";
        if (!temPlaca) {
            mensagem += "Favor completar a placa\n";
            todosValidos = false;
        }
        if (!temMarca || !temTipoVeiculo || !temModelo || !temCor) {
            todosValidos = false;
            mensagem += "Favor selecionar: \n";
            if (!temMarca) {
                mensagem += "Sua marca\n";
            }
            if (!temTipoVeiculo) {
                mensagem += "O tipo do veiculo\n";
            }
            if (!temModelo) {
                mensagem += "Seu modelo\n";
            }
            if (!temCor) {
                mensagem += "Sua cor\n";
            }
        }
        if (!todosValidos && b) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Veiculo não cadastrado");
            alert.setHeaderText("Finalize o preenchimento");
            alert.setContentText(mensagem);
            alert.showAndWait();
        }
        return todosValidos;
    }

    private void insereVeiculoDialog() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Incluir Veiculo");
        alert.setHeaderText("Confirmar inserção");
        alert.setContentText("Deseja adicionar?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            this.ev.setVeiculo(new Veiculo());
            ev.getVeiculo().setPlaca(this.txtPlaca.getText());
            ev.getVeiculo().setMarca(this.selectOneMarca.getValue());
            ev.getVeiculo().setModelo(this.selectOneModelo.getValue());
            ev.getVeiculo().setCor(this.selectOneCor.getValue());

            Long id = ev.veiculoDAO.inserir(ev.getVeiculo());
            if (id != null) {
                ev.getVeiculo().setId(id);

                insereEstacionaDialog();
            }

        } else {

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Veiculo não inserido");
            alert2.setHeaderText("Inserção cancelada.\n"
                    + "Finalize o procedimento de entrada");
            alert2.showAndWait();
        }

    }

    private void alteraVeiculoDialog() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alterar Veiculo");
        alert.setHeaderText("MUNDANÇA REGISTRO");
        alert.setContentText("Deseja ALTERAR?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            if (ev.veiculoDAO.alterar(ev.getVeiculo())) {

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Veiculo ALTERADO");
                alert2.setHeaderText("Operação realizada.\n"
                        + "Finalize o procedimento de entrada");
                alert2.showAndWait();
            }

        } else {

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Veiculo não alterado");
            alert2.setHeaderText("Operação cancelada.\n"
                    + "Finalize o procedimento de entrada");
            alert2.showAndWait();
        }

    }

    private void insereEstacionaDialog() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Veiculo está inserido");
        alert.setHeaderText("Inserir o procedimento de entrada?");
        alert.setContentText(ev.getVeiculo().toString());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            ev.estacionaDAO.inserir(
                    new Estaciona(
                            ev.getVeiculo(),
                            Integer.parseInt(lblMinutosTolerancia.getText())
                    )
            );
            actionLimpar();
            mainController.chamaSaidaView();
        }
    }

    private void actionRegistrar() {

        if (validaCampos(false) && ev.getVeiculo() != null) {
            if (ev.getVeiculo().getId() != null) {
                Veiculo v = ev.getVeiculo();
                v.setPlaca(txtPlaca.getText());
                v.setMarca(selectOneMarca.getValue());
                v.setModelo(selectOneModelo.getValue());
                v.setCor(selectOneCor.getValue());

                if (!v.equals(ev.veiculoDAO.getPorId(v.getId()))) {

                    alteraVeiculoDialog();
                }
            }
        }
        actionPesquisarPlaca();

    }

    //LISTENER - ACTION IMPLEMENTANDO ANOTAÇÃO FXML INICIANDO IMPLICITAMENTE
    @FXML
    private void actionRegistrar(ActionEvent event) {
        actionRegistrar();
    }

    private void actionEditar() {
        this.selectOneMarca.setDisable(false);
        this.selectOneModelo.setDisable(false);
        this.selectOneCor.setDisable(false);
        this.selectOneTipoVeiculo.setDisable(false);
    }

    //LISTENER - ACTION IMPLEMENTANDO ANOTAÇÃO FXML INICIANDO IMPLICITAMENTE
    @FXML
    private void actionEditar(ActionEvent event) {
        actionEditar();
    }

    private void actionLimpar() {
        this.mainController.chamaEntrada2View();
    }

    //LISTENER - ACTION IMPLEMENTANDO ANOTAÇÃO FXML INICIANDO IMPLICITAMENTE
    @FXML
    private void actionLimpar(ActionEvent event) {
        actionLimpar();
    }
}
