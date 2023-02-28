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
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author andreidb
 */
public class DeleteController implements Initializable {

    @FXML
    private TextArea content;
    @FXML
    private TextField title;
    @FXML
    private TextField idField;

    @FXML
    private Button okBtn;
    @FXML
    private Button cancelBtn;

    PreparedStatement prepare;
    ResultSet result;
    Connection connect;

    public void deleteNotesHandleButtons(ActionEvent event) {
        if (event.getSource() == okBtn) {
            deleteNotes();
        } else if (event.getSource() == cancelBtn) {
            canceWindow(GetData.getDeleteNotesWindow());
        }

    }

    public ObservableList<Notes> getNotesFromDatabase() {
        ObservableList<Notes> dataList = FXCollections.observableArrayList();
        String sql = "SELECT title,date,content FROM notes WHERE username=?";
        connect = Database.Connect();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, GetData.getUsername());
            result = prepare.executeQuery();

            while (result.next()) {
                Notes notes = new Notes(
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

    private void deleteNotes() {
        Alert alert;
        if (title.getText().isEmpty() || content.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {
            String sql = "DELETE FROM notes WHERE id=?";
            connect = Database.Connect();
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, String.valueOf(GetData.getCurrentItem().getId()));

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete this item?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted");
                    alert.showAndWait();

                    title.setText("");
                    content.setText("");

                    GetData.getDeleteNotesWindow().close();

                    notesList = getNotesFromDatabase();

                    GetData.getTableView().setItems(notesList);

                }
            } catch (SQLException ex) {
                Logger.getLogger(DeleteController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void canceWindow(Stage stage) {
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(GetData.getCurrentItem().getTitle());
        content.setText(GetData.getCurrentItem().getContent());
        idField.setText(String.valueOf(GetData.getCurrentItem().getId()));

    }

}
