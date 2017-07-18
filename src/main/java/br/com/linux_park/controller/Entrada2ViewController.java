/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linux_park.controller;

import br.com.linux_park.model.bean.Cor;
import br.com.linux_park.model.bean.Marca;
import br.com.linux_park.model.bean.Modelo;
import br.com.linux_park.model.bean.TipoVeiculo;
import br.com.linux_park.model.bean.Veiculo;
import br.com.linux_park.model.vo.EntradaVO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author main
 */
public class Entrada2ViewController implements Initializable {

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
    private Button btnPesquisarPlaca;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnLimpar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ev.verificaDadosIniciais();

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
                        inpPlaca.setText(newValue.substring(0,2));
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

//        Veiculo v = new Veiculo();
//        
//        v = ev.veiculoDAO.get("dfd-4040");
//        System.out.println(v);
//        
//        v = ev.veiculoDAO.get("psl-4977");
//        System.out.println(v);
//        v.setId(null);
//        v.set
//        
//        System.out.println(v);      
//        System.out.println(this.ev.veiculoDAO.existente(this.txtPlaca.getText()));
//        System.out.println(this.ev.veiculoDAO.get(this.txtPlaca.getText()));
//        System.out.println(this.ev.veiculoDAO.listarTodos());
//        ev.veiculoDAO.excluir(2L);
//        System.out.println(this.ev.veiculoDAO.listarTodos());
        if (this.ev.veiculoDAO.existente(this.txtPlaca.getText())) {
            this.ev.setVeiculo(this.ev.veiculoDAO.get(this.txtPlaca.getText()));

            this.selectOneMarca.setValue(this.ev.getVeiculo().getMarca());
            this.selectOneTipoVeiculo.setValue(this.ev.getVeiculo().getModelo().getTipoVeiculo());
            this.listaModelos();
            this.selectOneModelo.setValue(this.ev.getVeiculo().getModelo());
            this.selectOneCor.setValue(this.ev.getVeiculo().getCor());

            this.selectOneMarca.setDisable(true);
            this.selectOneModelo.setDisable(true);
            this.selectOneCor.setDisable(true);
            this.selectOneTipoVeiculo.setDisable(true);

        } else {
            if (this.ev.getVeiculo() == null) {
                this.selectOneMarca.setValue(null);
                this.selectOneTipoVeiculo.setValue(null);
                this.selectOneModelo.setValue(null);
                this.selectOneCor.setValue(null);
            }

            this.ev.setVeiculo(new Veiculo());

            actionEditar();
        }

    }

    //LISTENER - ACTION IMPLEMENTANDO ANOTAÇÃO FXML INICIANDO IMPLICITAMENTE
    @FXML
    private void actionPesquisarPlaca(ActionEvent event) {
        actionPesquisarPlaca();
    }

    //LISTENER - ACTION IMPLEMENTANDO ANOTAÇÃO FXML INICIANDO IMPLICITAMENTE
    @FXML
    private void actionRegistrar(ActionEvent event) {

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

    //LISTENER - ACTION IMPLEMENTANDO ANOTAÇÃO FXML INICIANDO IMPLICITAMENTE
    @FXML
    private void actionLimpar(ActionEvent event) {
        this.mainController.chamaEntrada2View();
    }
}
