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
    private static TableView tableView;
    private static Notes currentItem;

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

    public static TableView getTableView() {
        return tableView;
    }

    public static void setTableView(TableView tableView) {
        GetData.tableView = tableView;
    }

    public static void setCurrentItem(Notes item) {
        currentItem=item;
    }
    public static Notes getCurrentItem() {
        return currentItem;
    }

}
