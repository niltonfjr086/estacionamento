/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.linux_park.controller;

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
    private Button pesquisaPlaca;
    @FXML
    private Label lblMarca;
    @FXML
    private ComboBox<?> selectOneMarca;
    @FXML
    private Label lblModelo;
    @FXML
    private ComboBox<?> selectOneModelo;
    @FXML
    private Label lblCor;
    @FXML
    private ComboBox<?> selectOneCor;
    @FXML
    private Label lblTipo;
    @FXML
    private ComboBox<?> selectOneTipo;
    @FXML
    private Button registrar;
    @FXML
    private Button editar;
    @FXML
    private Button limpar;
    @FXML
    private Label lblQtdMoto;
    @FXML
    private Label lblVagasMoto;
    @FXML
    private Label lblQtdCarro;
    @FXML
    private Label lblVagasCarro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        habilitarPlacaMask(this.txtPlaca);
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
    private void actionRegistrar(ActionEvent event) {

    }

    @FXML
    private void actionEditar(ActionEvent event) {

    }

    @FXML
    private void actionLimpar(ActionEvent event) {

    }

}
