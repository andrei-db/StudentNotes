/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mainapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author andreidb
 */
public class MainApplicationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML Button newBtn;
    @FXML TableView table;
    @FXML TableColumn title;
    @FXML TableColumn date;
     @FXML TableColumn content;
    @FXML TableColumn action;
    public void handleButtonPress(ActionEvent event) throws IOException {
       URL fxmlUrl = this.getClass().getResource("/addtableitems/addtableitems.fxml");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fxmlUrl);
        AnchorPane root = loader.load();

        Scene scene = new Scene(root, 600, 600);
        Stage stage=new Stage();
        stage.setTitle("Add to table!");
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
