package tbd_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author SeijinD
 */
public class TBD_Project extends Application 
{    
    //define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        //you can use underdecorated or transparent.
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));       
        //stage.initStyle(StageStyle.UNDERDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);       
        //grab your root here
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });  
        //move around here
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });       
        stage.setTitle("Login");
        Scene scene = new Scene(root);
        stage.setScene(scene);   
        stage.show();
    } 
    
    public static void main(String[] args) 
    {
        launch(args);
    }    
}
