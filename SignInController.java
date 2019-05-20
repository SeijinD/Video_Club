package tbd_project;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static tbd_project.InfoConnection.*;

public class SignInController {
    //Create Dialog
    @FXML
    private TextField userNameCreate;
    @FXML
    private  PasswordField passwordCreate;
    @FXML 
    private CheckBox sureBox;
    @FXML
    private RadioButton userRadioButton;
    @FXML
    private RadioButton adminRadioButton;  
    @FXML
    private Label messageSignIn;
    
    //Create Account
    @FXML
    private Button createButton;
    
    @FXML
    public void CreateAndGoLogin(ActionEvent event) throws Exception
    {
        String user1 = userNameCreate.getText();
        String pass1 = passwordCreate.getText();
        int type_user1;
        if(userRadioButton.isSelected())
            type_user1 = 0;
        else
            type_user1 = 1;
        if(user1.equals("") || pass1.equals(""))
            messageSignIn.setText("Username or Password is empty!");
        else if (sureBox.selectedProperty().getValue() == false)
            messageSignIn.setText("You don't check the checkbox!");
        else            
        {
            try
            {
                dbConnection = DriverManager.getConnection (url, username, passwd);
                statement = dbConnection.createStatement();
                statement.executeUpdate("insert INTO LOGIN (username, password, type_user) VALUES ('" + user1 + "','" + pass1 + "','" + type_user1 + "')");
                statement.close();
                dbConnection.close(); 
                Stage stage = (Stage) createButton.getScene().getWindow();
                stage.close();  
            } catch(SQLException e)
            {
                tbd_project.Handlers.sqlExceptionHandler(e);
            } 
        }
    }
    //End Create Account
    
    @FXML
    private Button closeButton;
    
    @FXML
    public void Close(ActionEvent event)
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
