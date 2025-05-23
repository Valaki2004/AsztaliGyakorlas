package hu.vtg;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class LoadController {
    
    @FXML
    private TableView<Create> Table;
    
    @FXML
    private TableColumn<Create, Integer> Col1;

    @FXML
    private TableColumn<Create, Integer> Col2;

    @FXML
    private TableColumn<Create, LocalDate> Col3;

    @FXML
    private TableColumn<Create, String> Col4;

    @FXML
    private TableColumn<Create, String> Col5;


    @FXML
    void initialize() throws IOException{
        Reader reader = new Reader();
        ArrayList<Create> list = reader.readFile();
        ObservableList<Create> creates = FXCollections.observableArrayList(list);

        this.Col1.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.Col2.setCellValueFactory(new PropertyValueFactory<>("paintingid"));
        this.Col3.setCellValueFactory(new PropertyValueFactory<>("datum"));
        this.Col4.setCellValueFactory(new PropertyValueFactory<>("muvelet"));
        this.Col5.setCellValueFactory(new PropertyValueFactory<>("megjegyzes"));
        this.Table.getItems().addAll(creates);
    }
    @FXML
    void AddBtn(ActionEvent event) throws IOException {
        try{
            Reader reader = new Reader();
            ArrayList<Create> list = reader.readFile();
            Create creates = new Create();
            int plusid = list.size() + 1;
            creates.setId(plusid);
            creates.setPaintingid(Integer.parseInt(paintingidinput.getText()));
            creates.setDatum(dateinput.getValue());
            creates.setMuvelet(actioninput.getText());
            creates.setMegjegyzes(descriptioninput.getText());
            if (!asd(actioninput.getText()) || !asd(descriptioninput.getText())) {
            showError("A művelet vagy megjegyzés mező érvénytelen karaktereket tartalmaz!");
            return;
            }
            list.add(creates);
            Table.getItems().add(creates);
            reader.FileWiriter(list);
            clear();
        }
        catch(Exception e) {
            if (
            paintingidinput.getText().isEmpty() ||
            dateinput.getValue() == null ||
            actioninput.getText().isEmpty() || 
            descriptioninput.getText().isEmpty())
            {
                showError("Helyesen add meg az adatokat és/vagy minden mezőt tölts ki!");
            }

        }
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Hiba");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clear(){
        dateinput.setValue(null);
        descriptioninput.setText("");
        actioninput.setText("");
        paintingidinput.setText("");
    }

    private boolean asd(String text) {
        return text != null && text.matches("[a-zA-ZáéíóöőúüűÁÉÍÓÖŐÚÜŰ\\s]+");
    }


    @FXML
    private TextField actioninput;

    @FXML
    private DatePicker dateinput;

    @FXML
    private TextField descriptioninput;

    @FXML
    private TextField paintingidinput;

    @FXML
    void BackBtn(ActionEvent event) throws IOException {
        App.setRoot("MainFrame");
    }


}
