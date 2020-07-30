package Utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage LoginStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/TelaInicioSistema.fxml"));
        LoginStage.setTitle("Estacionamento");
        LoginStage.setResizable(false);
        LoginStage.setScene(new Scene(root, 600, 346));
        LoginStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
