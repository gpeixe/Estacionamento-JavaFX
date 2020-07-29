package Utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/TelaTicket.fxml"));
        primaryStage.setTitle("Gerar Ticket");
        primaryStage.setResizable(false); //Importante para que o usuário não consiga redimensionar a tela
        primaryStage.setScene(new Scene(root, 350, 391));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
