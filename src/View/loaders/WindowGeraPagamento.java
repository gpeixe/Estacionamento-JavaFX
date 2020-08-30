package View.loaders;

import Controller.WindowGeraPagamentoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowGeraPagamento {
    public void startModal(String cpf){
        try {
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResource("/View/fxml/WindowGeraPagamento.fxml").openStream());

            WindowGeraPagamentoController controller = loader.getController();

            if(cpf != null)
                controller.setCpf(cpf);

            Stage stage = new Stage();
            Image icon = new Image("Images/icon.png");
            stage.getIcons().add(icon);
            stage.setTitle("Pagamento");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(pane, 560, 141));
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
