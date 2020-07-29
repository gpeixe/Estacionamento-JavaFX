package Utils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    /*
    @Override
    public void start(Stage TicketStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/TelaTicket.fxml"));
        TicketStage.setTitle("Gerar Ticket");
        TicketStage.setResizable(false); //Importante para que o usuário não consiga redimensionar a tela
        TicketStage.setScene(new Scene(root, 350, 391));
        TicketStage.initModality(Modality.APPLICATION_MODAL);
        TicketStage.showAndWait();
    }
    */

    @Override
    public void start(Stage LoginStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/TelaLogin.fxml"));
        LoginStage.setTitle("Login");
        LoginStage.setResizable(false); //Importante para que o usuário não consiga redimensionar a tela
        LoginStage.setScene(new Scene(root, 350, 318));
        //LoginStage.initModality(Modality.APPLICATION_MODAL);
        //LoginStage.showAndWait();
        LoginStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
