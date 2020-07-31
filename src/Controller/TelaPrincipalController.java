package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaPrincipalController {
    public void openTelaTicket(ActionEvent actionEvent) throws Exception{
        Stage TicketStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/TelaTicket.fxml"));
        TicketStage.setTitle("Gerar Ticket");
        TicketStage.setResizable(false);
        TicketStage.setScene(new Scene(root, 350, 391));
        TicketStage.initModality(Modality.APPLICATION_MODAL);
        TicketStage.showAndWait();
    }

    public void openTelaLogin(ActionEvent actionEvent) throws Exception{
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/TelaLogin.fxml"));
        loginStage.setScene(new Scene(root, 350, 318));
        loginStage.setResizable(false);
        loginStage.setTitle("Login");
        loginStage.initModality(Modality.WINDOW_MODAL);
        loginStage.showAndWait();
    }

    public void openTelaCRUDMensalista(ActionEvent actionEvent) throws Exception{
        Stage mensalistaStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/TelaCRUDMensalistas.fxml"));
        mensalistaStage.setScene(new Scene(root, 1010, 589));
        mensalistaStage.setResizable(false);
        mensalistaStage.setTitle("Mensalista");
        mensalistaStage.initModality(Modality.WINDOW_MODAL);
        mensalistaStage.showAndWait();
    }

    public void deslogarFunc(ActionEvent actionEvent) {
    }
}
