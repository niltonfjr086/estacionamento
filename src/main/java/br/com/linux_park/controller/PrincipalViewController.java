package br.com.linux_park.controller;

import br.com.linux_park.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author n
 */
public class PrincipalViewController implements Initializable {

    MainApp mainApp;

    @FXML
    private Tab tabEntrada;
    @FXML
    private BorderPane brdEntrada;
    @FXML
    private Tab tabSaida;
    @FXML
    private BorderPane brdSaida;
    @FXML
    private Tab tabHistorico;
    @FXML
    private BorderPane brdHistorico;
    @FXML
    private Tab tabConfig;
    @FXML
    private BorderPane brdConfig;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void chamaEntradaView(Event event) {
        if (brdEntrada.getCenter() == null) {
            chamaEntradaView();
        }

    }

    public void chamaEntradaView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/EntradaView.fxml"));

            Parent entrada = loader.load();
            brdEntrada.setCenter(entrada);
            EntradaViewController controller = loader.getController();
            controller.setMainController(this);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void chamaSaidaView(Event event) {
        if (brdSaida.getCenter() == null) {
            chamaSaidaView();
        }
    }

    public void chamaSaidaView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/SaidaView.fxml"));

            Parent saida = loader.load();
            brdSaida.setCenter(saida);
            SaidaViewController controller = loader.getController();
            controller.setMainController(this);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void chamaHistoricoView(Event event) {
        if (brdHistorico.getCenter() == null) {
            chamaHistoricoView();
        }
    }

    public void chamaHistoricoView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/HistoricoView.fxml"));

            Parent historico = loader.load();
            brdHistorico.setCenter(historico);
            HistoricoViewController controller = loader.getController();
            controller.setMainController(this);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void chamaConfigView(Event event) {
        if (brdConfig.getCenter() == null) {
            chamaConfigView();
        }
    }

    private void chamaConfigView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/ConfigView.fxml"));

            Parent config = loader.load();
            brdConfig.setCenter(config);
            ConfigViewController controller = loader.getController();
            controller.setMainController(this);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
