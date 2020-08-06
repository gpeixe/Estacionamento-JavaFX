package Controller;

import View.loaders.WindowEntradaMensalista;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class WindowTicketController {

   @FXML
   JFXButton cancelaTicket;

   public void geraTicket(ActionEvent actionEvent) {
   }

   public void cancelaTicket(ActionEvent actionEvent) {
       Stage stage = (Stage) cancelaTicket.getScene().getWindow();
       stage.close();
   }

    public void geraTicketMensalista(ActionEvent actionEvent) {
    }
}
