package tbd_project;

import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import static tbd_project.InfoConnection.*;

public class DeleteMovieController {
    
    
    
    
    
    
    //GoBack
    @FXML
    private Button goBackButton;
    
    @FXML
    public void GoBack(ActionEvent event)
    {    
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();
    }
    //End GoBack
    
}
