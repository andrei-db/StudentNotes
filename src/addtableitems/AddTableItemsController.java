/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package addtableitems;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainapplication.MainApplicationController;
import studentnotes.Auxiliar;
import studentnotes.Notes;

/**
 * FXML Controller class
 *
 * @author andreidb
 */
public class AddTableItemsController implements Initializable {

    @FXML
    TextField titleField;
    @FXML
    TextArea contentArea;

    List<Notes> notesList = Auxiliar.getList();
    TableView table = Auxiliar.getTable();

    /**
     * Initializes the controller class.
     */
    public void handleCancelButton(ActionEvent event) {
        closeWindow();
    }

    public void handleOKButton(ActionEvent event) {
        if (titleField.getText().isEmpty() || contentArea.getText().isEmpty()) {

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Fill the missing fields");
            alert.setHeaderText("Empty fields:");
            alert.setContentText("Please make sure all fields are filled in.!");
            alert.showAndWait();
        } else {
            notesList.add(new Notes(titleField.getText(), "fdsg", contentArea.getText()));
            refreshTable();
            closeWindow();
        }

    }

    public void refreshTable() {
        table.getItems().clear();
        table.getItems().addAll(notesList);

    }

    public void closeWindow() {
        Auxiliar.getStage().close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
