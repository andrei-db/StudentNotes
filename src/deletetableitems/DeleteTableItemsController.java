/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package deletetableitems;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import studentnotes.Auxiliar;
import studentnotes.Notes;

/**
 * FXML Controller class
 *
 * @author andreidb
 */
public class DeleteTableItemsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField idField;
    @FXML
    TextField titleField;
    @FXML
    TextArea contentArea;
    Notes note = Auxiliar.getNoteItem();
    List<Notes> notesList = Auxiliar.getList();
    TableView table = Auxiliar.getTable();

    public void handleCancelButton(ActionEvent event) {
        closeWindow();
    }
    
    public void handleOKButton(ActionEvent event) {
        
        notesList.remove(note);
        refreshTable();
        closeWindow();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getTheValuesFromTable();
    }    

    public void getTheValuesFromTable() {
        idField.setText("1");
        titleField.setText(note.getTitle());
        contentArea.setText(note.getContent());
    }

    public void closeWindow() {
        Auxiliar.getStage().close();
        
    }

    public void refreshTable() {
        table.getItems().clear();
        table.getItems().addAll(notesList);
        
    }
    
}
