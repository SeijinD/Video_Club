package video_club;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static video_club.InfoConnection.*;

public abstract class ExitMethods {
    
    //Exit Button
    @FXML
    private Button noExitButton;
    @FXML
    private Button yesExitButton;
    
    @FXML
    public void Exit(ActionEvent event) throws IOException
    {
        //create new stage.
        Stage exit = new Stage();
        //you can use underdecorated or transparent.
        Parent root = FXMLLoader.load(getClass().getResource("Exit.fxml"));       
        //stage.initStyle(StageStyle.UNDERDECORATED);
        exit.initStyle(StageStyle.TRANSPARENT);       
        //grab your root here
        root.setOnMousePressed((MouseEvent event1) -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });  
        //move around here
        root.setOnMouseDragged((MouseEvent event1) -> {
            exit.setX(event1.getScreenX() - xOffset);
            exit.setY(event1.getScreenY() - yOffset);
        });       
        exit.setTitle("Exit");
        Scene scene = new Scene(root,400,260);
        exit.setScene(scene);   
        exit.show();
    }
    //End Exit Button
    
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
