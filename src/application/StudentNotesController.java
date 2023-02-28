/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andreidb
 */
public class StudentNotesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button newBtn;
    @FXML
    TableView<Notes> table;
    @FXML
    TableColumn<Notes, String> title;
    @FXML
    TableColumn<Notes, Notes> date;
    @FXML
    TableColumn<Notes, String> content;
    @FXML
    TableColumn<Notes, Notes> action;
    @FXML
    TextField searchField;

    Connection connect;
    ResultSet result;
    PreparedStatement prepare;


    public void handleNewButton(ActionEvent event) {
        if (event.getSource() == newBtn) {
            addNotesWindow();
        }

    }
    List<Notes> searchList = new ArrayList<Notes>();

    public void addNotesWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/crud/AddNotes.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            GetData.setAddNotesWindow(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StudentNotesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

     public void updateNotesWindow() {
        try {
           Parent root = FXMLLoader.load(getClass().getResource("/crud/UpdateNotes.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            GetData.setUpdateNotesWindow(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StudentNotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteNotesWindow() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/crud/DeleteNotes.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            GetData.setDeleteNotesWindow(stage);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StudentNotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    ObservableList<Notes> notesList;
    public void showNotesData() {
        notesList=getNotesFromDatabase();
        
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        content.setCellValueFactory(new PropertyValueFactory<>("content"));
        
        
         action.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        action.setCellFactory((tableColumn) -> {
            TableCell<Notes, Notes> tableCell = new TableCell<Notes, Notes>() {
                @Override
                protected void updateItem(Notes item, boolean empty) {
                    super.updateItem(item, empty);

                    this.setText(null);
                    this.setGraphic(null);
                    Button updateBtn = new Button("Update");
                    Button deleteBtn = new Button("Delete");
                    HBox actionBox = new HBox(updateBtn, deleteBtn);

                    updateBtn.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                           
                            updateNotesWindow();
                        }
                    });

                    deleteBtn.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            
                            deleteNotesWindow();
                        }
                    });
                    if (!empty) {
                        this.setGraphic(actionBox);
                    }
                }
            };

            return tableCell;
        });
   
        table.setItems(notesList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showNotesData();
        
       

    }

   

}
