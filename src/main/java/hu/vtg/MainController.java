package hu.vtg;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    void ExitBtn(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void LoadBtn(ActionEvent event) throws IOException {
        App.setRoot("resrauralasok");   
    }

}
