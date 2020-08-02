package Controller;

import View.loaders.WindowLogin;


public class WindowStartController {



  
    public void login(javafx.event.ActionEvent actionEvent) {
        WindowLogin w = new WindowLogin();
        w.startModal();
    }
}
