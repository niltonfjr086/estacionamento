package br.com.linux_park.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author main
 */
public class ConfigViewController implements Initializable {

    private PrincipalViewController mainController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setMainController(PrincipalViewController mainController) {
        this.mainController = mainController;
    }
}
