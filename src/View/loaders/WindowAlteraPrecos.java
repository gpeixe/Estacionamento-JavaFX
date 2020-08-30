package View.loaders;

import Controller.MensalistaController;
import Controller.PrecoController;
import Model.Entities.Mensalista.Mensalista;
import Model.Entities.Precos.Precos;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class WindowAlteraPrecos {
    PrecoController pc = new PrecoController();
    public void startModal(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(getClass().getResource("/View/fxml/WindowAlteraPrecos.fxml").openStream());
            Scene scene = new Scene(pane, 452, 296);
            pc = loader.getController();
            Precos precos = pc.readPrecos();

            if(precos != null){
                pc.editaPrecos(precos);
                pc.setPreco(precos);
            }
            Image icon = new Image("Images/icon.png");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.setTitle("Mensalista");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
