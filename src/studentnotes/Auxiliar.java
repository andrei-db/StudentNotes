/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package studentnotes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 *
 * @author andreidb
 */
public class Auxiliar {
    private static List<Notes>notesList;
     private static TableView table;
     private static Stage stage;

     public static String generateDate() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }
     
    public static Notes getNoteItem() {
        return noteItem;
    }

    public static void setNoteItem(Notes noteItem) {
        Auxiliar.noteItem = noteItem;
    }
    private static Notes noteItem;
    
    public static List<Notes> getNotesList() {
        return notesList;
    }

    public static void setNotesList(List<Notes> notesList) {
        Auxiliar.notesList = notesList;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Auxiliar.stage = stage;
    }
     
     
     
      public static void setTable(TableView table){
       Auxiliar.table=table;
    }
    public static TableView getTable(){
       return table;
    }
     
    public static void setList(List<Notes> notesList){
        Auxiliar.notesList=notesList;
    }
    public static List<Notes> getList(){
       return notesList;
    }
}
