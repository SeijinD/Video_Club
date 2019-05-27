package tbd_project;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import static tbd_project.InfoConnection.*;
import static tbd_project.LoginController.userEdit;

public class DeleteAccountController {
        
    //Delete Account
    @FXML
    private Button noDeleteAccountButton;
    @FXML
    private Button yesDeleteAccountButton;
    
    @FXML
    public void NoDeleteAccount(ActionEvent event)
    {
        Stage stage = (Stage) noDeleteAccountButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void YesDeleteAccount(ActionEvent event) throws Exception
    {
        try
        {
            dbConnection = DriverManager.getConnection (url, username, passwd);
            statement    = dbConnection.createStatement();
            statement.executeUpdate("DELETE FROM login WHERE username='" + userEdit + "'");
            statement.close();
            dbConnection.close();
        } 
        catch(SQLException e)
        {
            tbd_project.Handlers.sqlExceptionHandler(e);
        }
        
        Platform.exit();
        
//        Stage stage = (Stage) noDeleteAccountButton.getScene().getWindow();
//        stage.close();
//        
//        Platform.runLater( () -> {
//            try {
//                new TBD_Project().start( new Stage() );
//            } catch (Exception ex) {
//                Logger.getLogger(DeleteAccountController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } );
              
    }
    //End Delete Account
}
