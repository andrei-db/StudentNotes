/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import application.Database;
import application.GetData;
import application.Notes;
import application.StudentNotesController;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author andreidb
 */
public class CRUDController implements Initializable {

    @FXML
    private Button cancelBtnAddNotesWindow;
    @FXML
    private Button cancelBtnUpdateNotesWindow;

    @FXML
    private Button cancelBtnDeleteNotesWindow;

    @FXML
    private TextArea contentAddNotesWindow;
    @FXML
    private TextField titleAddNotesWindow;

    @FXML
    private Button okBtnAddNotesWindow;
    @FXML
    private Button okBtnUpdateNotesWindow;
    @FXML
    private Button okBtnDeleteNotesWindow;
    
    
    PreparedStatement prepare;
    ResultSet result;
    Connection connect;

    public void addNotesHandleButtons(ActionEvent event) {
        if (event.getSource() == okBtnAddNotesWindow) {
            addNotes();
        } else if (event.getSource() == cancelBtnAddNotesWindow) {
            canceWindow(GetData.getAddNotesWindow());
        }

    }

    public void updateNotesHandleButtons(ActionEvent event) {
        if (event.getSource() == okBtnUpdateNotesWindow) {
            updateNotes();
        } else if (event.getSource() == cancelBtnUpdateNotesWindow) {
            canceWindow(GetData.getUpdateNotesWindow());
        }

    }

    public void deleteNotesHandleButtons(ActionEvent event) {
        if (event.getSource() == okBtnDeleteNotesWindow) {
            deleteNotes();
        } else if (event.getSource() == cancelBtnDeleteNotesWindow) {
            canceWindow(GetData.getDeleteNotesWindow());
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    
    public ObservableList<Notes> getNotesFromDatabase() {
        ObservableList<Notes>dataList=FXCollections.observableArrayList();
        String sql = "SELECT title,date,content FROM notes WHERE username=?";
        connect = Database.Connect();

        try {
            prepare=connect.prepareStatement(sql);
            prepare.setString(1, GetData.getUsername());
            result = prepare.executeQuery();

            while (result.next()) {
                Notes notes=new Notes(
                        result.getString("title"),
                        result.getDate("date"),
                        result.getString("content")
                );
                dataList.add(notes);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentNotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataList;
    }
    ObservableList<Notes>notesList;
    private void addNotes() {
        Alert alert;
        if(titleAddNotesWindow.getText().isEmpty() || contentAddNotesWindow.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        
        }else{
            String sql="INSERT INTO notes(username,title,date,content) VALUES(?,?,?,?)";
            connect=Database.Connect();
            try {
                prepare=connect.prepareStatement(sql);
                prepare.setString(1, GetData.getUsername());
                prepare.setString(2, titleAddNotesWindow.getText());
                
                
                Date date=new Date();
                java.sql.Date sqlDate=new java.sql.Date(date.getTime());
                
                prepare.setString(3, sqlDate.toString());
                prepare.setString(4, contentAddNotesWindow.getText());
                
                prepare.executeUpdate();
                
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Note was successfully added!");
                alert.showAndWait();
                
                titleAddNotesWindow.setText("");
                contentAddNotesWindow.setText("");
                
                
                GetData.getAddNotesWindow().close();
                
                notesList=getNotesFromDatabase();
                
                GetData.getTableView().setItems(notesList);
                
                
            } catch (SQLException ex) {
                Logger.getLogger(CRUDController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        
        }

    }

    private void updateNotes() {
    }

    private void deleteNotes() {

    }

    private void canceWindow(Stage stage) {
        stage.close();
    }

}
