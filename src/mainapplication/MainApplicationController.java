/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import studentnotes.Notes;

/**
 * FXML Controller class
 *
 * @author andreidb
 */
public class MainApplicationController implements Initializable {

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
    List<Notes> notesList = new ArrayList<Notes>();

    public void handleButtonPress(ActionEvent event) throws IOException {
        URL fxmlUrl = this.getClass().getResource("/addtableitems/addtableitems.fxml");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fxmlUrl);
        AnchorPane root = loader.load();

        Scene scene = new Scene(root, 600, 600);
        Stage stage = new Stage();
        stage.setTitle("Add to table!");
        stage.setScene(scene);
        stage.show();
    }

    private void printList() {
        for (Notes notes : notesList) {
            System.out.println(notes.getTitle());
        }
        System.out.println("11111");
    }

    public void refreshTable() {
        table.getItems().clear();
        table.getItems().addAll(notesList);
    }

    public String generateDate() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
                            openUpdateWindow();
                        }
                    });

                    deleteBtn.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            openDeleteWindow();

                        }
                    });
                    if (!empty) {

                        this.setGraphic(actionBox);

                    }
                }
            };

            return tableCell;
        });

        notesList.add(new Notes("mate",generateDate(), "gfdhhdf"));
        notesList.add(new Notes("romana", generateDate(),"gfdfdhdfhfd"));
        notesList.add(new Notes("engl", generateDate(),"gfdfdhdfhfd"));
        notesList.add(new Notes("ger",generateDate(), "gfdfdhdfhfd"));
        notesList.add(new Notes("muz", generateDate(),"gfdfdhdfhfd"));
        printList();
        table.getItems().addAll(notesList);
        // printList();
    }

    public void openUpdateWindow() {
        try {
            URL fxmlUrl = this.getClass().getResource("/updatetableitems/updatetableitems.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fxmlUrl);
            AnchorPane root = loader.load();

            Scene scene = new Scene(root, 600, 600);

            Stage stage = new Stage();
            stage.setTitle("Update table!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openDeleteWindow() {
        try {
            URL fxmlUrl = this.getClass().getResource("/deletetableitems/deletetableitems.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(fxmlUrl);
            AnchorPane root = loader.load();

            Scene scene = new Scene(root, 600, 600);

            Stage stage = new Stage();
            stage.setTitle("Delete from table!");
            stage.setScene(scene);
            stage.show();
//                            notesList.remove(item);
//                            refreshTable();
//                            printList();
        } catch (IOException ex) {
            Logger.getLogger(MainApplicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
