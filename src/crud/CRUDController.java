/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import application.GetData;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author andreidb
 */
public class CRUDController implements Initializable{
    
     @FXML
    private Button cancelBtnAddNotesWindow;
      @FXML
    private Button cancelBtnUpdateNotesWindow;

       @FXML
    private Button cancelBtnDeleteNotesWindow;


    @FXML
    private TextArea contentArea;

    @FXML
    private Button okBtnAddNotesWindow;
     @FXML
    private Button okBtnUpdateNotesWindow;
     @FXML
    private Button okBtnDeleteNotesWindow;
    
    public void addNotesHandleButtons(ActionEvent event){
        if(event.getSource()==okBtnAddNotesWindow){
            addNotes();
        }
        else if(event.getSource()==cancelBtnAddNotesWindow){
            canceWindow(GetData.getAddNotesWindow());
        }
    
    }
    public void updateNotesHandleButtons(ActionEvent event){
        if(event.getSource()==okBtnUpdateNotesWindow){
            updateNotes();
        }
        else if(event.getSource()==cancelBtnUpdateNotesWindow){
            canceWindow(GetData.getUpdateNotesWindow());
        }
    
    }

    public void deleteNotesHandleButtons(ActionEvent event){
        if(event.getSource()==okBtnDeleteNotesWindow){
            deleteNotes();
        }
        else if(event.getSource()==cancelBtnDeleteNotesWindow){
            canceWindow(GetData.getDeleteNotesWindow());
        }
    
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    private void addNotes() {
      
    }
    private void updateNotes(){}
    
    private void deleteNotes(){
    
    }

    private void canceWindow(Stage stage) {
        stage.close();
    }
    
}
