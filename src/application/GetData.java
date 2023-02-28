/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import application.Notes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 *
 * @author andreidb
 */
public class GetData {

    private static String username;
    private static Stage addNotesWindow;
    private static Stage updateNotesWindow;
    private static Stage deleteNotesWindow;

    public static void setUsername(String user){
        username=user;
    }
    public static String getUsername(){
        return username;
    }
    
    public static void setAddNotesWindow(Stage stage) {
        addNotesWindow=stage;
    }

    public static Stage getAddNotesWindow() {
        return addNotesWindow;
    }
    public static void setUpdateNotesWindow(Stage stage) {
        updateNotesWindow=stage;
    }

    public static Stage getUpdateNotesWindow() {
        return updateNotesWindow;
    }
    public static void setDeleteNotesWindow(Stage stage) {
        deleteNotesWindow=stage;
    }

    public static Stage getDeleteNotesWindow() {
        return deleteNotesWindow;
    }

}
