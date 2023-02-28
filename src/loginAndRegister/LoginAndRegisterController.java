/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginAndRegister;

import application.Database;
import application.GetData;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author andreidb
 */
public class LoginAndRegisterController implements Initializable {

    @FXML
    private PasswordField passwordLogin;

    @FXML
    private PasswordField passwordRegister;

    @FXML
    private TextField usernameLogin;

    @FXML
    private TextField usernameRegister;

    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;

    Connection connect;
    PreparedStatement prepare;
    ResultSet result;

    public void close() {
        System.exit(0);
    }

    public void login() {
        String sql = "SELECT username,password FROM account WHERE username=? AND password=?";
        connect = Database.Connect();

        Alert alert;
        if (usernameLogin.getText().isEmpty() || passwordLogin.getText().isEmpty()) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            try {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, usernameLogin.getText());
                prepare.setString(2, passwordLogin.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();
                    
                    GetData.setUsername(usernameLogin.getText());
                            

                    loginBtn.getScene().getWindow().hide();

                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/application/StudentNotes.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginAndRegisterController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginAndRegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void register() {
        String sql = "INSERT INTO account(username,password) VALUES (?,?)";

        connect = Database.Connect();
        Alert alert;
        if (usernameRegister.getText().isEmpty() || passwordRegister.getText().isEmpty()) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            try {
                String checkData = "SELECT username,password FROM account WHERE username=? AND password=?";
                prepare = connect.prepareStatement(checkData);
                prepare.setString(1, usernameRegister.getText());
                prepare.setString(2, passwordRegister.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("An account with the username: " + usernameRegister.getText() + " already exist");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, usernameRegister.getText());
                    prepare.setString(2, passwordRegister.getText());

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Registered!");
                    alert.showAndWait();

                }
                usernameRegister.setText("");
                passwordRegister.setText("");

            } catch (SQLException ex) {
                Logger.getLogger(LoginAndRegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loginAndRegisterButtonsOnAction(ActionEvent event) {
        if (event.getSource() == loginBtn) {
            login();
        } else if (event.getSource() == registerBtn) {
            register();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
