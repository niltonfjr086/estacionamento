package br.com.linux_park;

import br.com.linux_park.controller.PrincipalViewController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/PrincipalView.fxml"));
        
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        this.stage = stage;
        this.stage.setResizable(false);
        this.stage.setTitle("Linux Park");
        this.stage.setScene(scene);


//        Parent entrada = loader.load();
//        brdEntrada.setCenter(entrada);
        PrincipalViewController controller = loader.getController();
        controller.setMainApp(this);

        this.stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
