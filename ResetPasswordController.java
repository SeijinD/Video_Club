package tbd_project;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static tbd_project.InfoConnection.*;
import static tbd_project.LoginController.userEdit;

public class ResetPasswordController {
    
    @FXML
    private TextField passwordEdit;
    @FXML
    private TextField passwordEdit2;
    @FXML
    private Label messageResetPassword;
    @FXML
    private Label resetUserNameEdit;
    
    @FXML
    public void ResetPassword(ActionEvent event)
    {
        resetUserNameEdit.setText(userEdit);
        if (passwordEdit.getText().equals(passwordEdit2.getText()))
        {
            String pass = passwordEdit.getText();
            try
            {
                dbConnection = DriverManager.getConnection (url, username, passwd);
                statement    = dbConnection.createStatement();
                statement.executeUpdate("UPDATE login SET password='" + pass + "' WHERE username='" + userEdit + "'");
                statement.close();
                dbConnection.close();
            } catch(SQLException e)
            {
                tbd_project.Handlers.sqlExceptionHandler(e);
            }
            messageResetPassword.setText("Password Changed");
        }
        else
        {
            messageResetPassword.setText("Different Passwords");
        }
    }
    
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
        resetUserNameEdit.setText(userEdit);
    }   
}
