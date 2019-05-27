package tbd_project;

import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static tbd_project.InfoConnection.*;
import static tbd_project.LoginController.*;

public class UserController {
    
    @FXML
    private Label userMessage;
    @FXML
    private Label typeUserEdit;
    @FXML
    private Label userNameEdit;
          
    //Reset Dialog
    @FXML
    public void GoResetPassword(ActionEvent event) throws Exception
    {
        //create new stage.
        Stage openReset = new Stage();
        //you can use underdecorated or transparent.
        Parent root = FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));       
        //stage.initStyle(StageStyle.UNDERDECORATED);
        openReset.initStyle(StageStyle.TRANSPARENT);       
        //grab your root here
        root.setOnMousePressed((MouseEvent event1) -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });  
        //move around here
        root.setOnMouseDragged((MouseEvent event1) -> {
            openReset.setX(event1.getScreenX() - xOffset);
            openReset.setY(event1.getScreenY() - yOffset);
        });       
        openReset.setTitle("Reset Password");
        Scene scene = new Scene(root,400,315);
        openReset.setScene(scene);   
        openReset.show();  
    } 
    //Reset Dialog
    
    //Logs Dialog
    @FXML
    public void ShowLogs(ActionEvent event) throws Exception
    {
        //create new stage.
        Stage openReset = new Stage();
        //you can use underdecorated or transparent.
        Parent root = FXMLLoader.load(getClass().getResource("LogsShow.fxml"));       
        //stage.initStyle(StageStyle.UNDERDECORATED);
        openReset.initStyle(StageStyle.TRANSPARENT);       
        //grab your root here
        root.setOnMousePressed((MouseEvent event1) -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });  
        //move around here
        root.setOnMouseDragged((MouseEvent event1) -> {
            openReset.setX(event1.getScreenX() - xOffset);
            openReset.setY(event1.getScreenY() - yOffset);
        });       
        openReset.setTitle("Show Logs");
        Scene scene = new Scene(root,700,560);        
        openReset.setScene(scene);   
        openReset.show();  
    } 
    //Logs Dialog
    
    //Delete Account Dialog
    @FXML
    public void DeleteAccount(ActionEvent event) throws Exception
    {        
        //create new stage.
        Stage deleteAccount = new Stage();
        //you can use underdecorated or transparent.
        Parent root = FXMLLoader.load(getClass().getResource("DeleteAccount.fxml"));       
        //stage.initStyle(StageStyle.UNDERDECORATED);
        deleteAccount.initStyle(StageStyle.TRANSPARENT);       
        //grab your root here
        root.setOnMousePressed((MouseEvent event1) -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });  
        //move around here
        root.setOnMouseDragged((MouseEvent event1) -> {
            deleteAccount.setX(event1.getScreenX() - xOffset);
            deleteAccount.setY(event1.getScreenY() - yOffset);
        });       
        deleteAccount.setTitle("Delete Account");
        Scene scene = new Scene(root);
        deleteAccount.setScene(scene);   
        deleteAccount.show();  
        
    }
    //End Delete Account Dialog
    
    @FXML
    private Button closeButton;
    
    @FXML
    public void Close(ActionEvent event)
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    public void initialize() 
    {
        userNameEdit.setText(userEdit);
        typeUserEdit.setText(userType);
    }    
}

