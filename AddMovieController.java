package tbd_project;

import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static tbd_project.InfoConnection.*;

public class AddMovieController {
    
    @FXML
    private Label addMovieMessage;
    @FXML
    private TextField titleFieldAdd;
    @FXML
    private TextField directorFieldAdd;
    @FXML
    private TextField starFieldAdd;
    @FXML
    private ComboBox<String> dateComboBoxAdd;
    @FXML
    private ComboBox<String> categoryComboBoxAdd;
        
    //Add Movie in Database
    @FXML
    public void AddMovie(ActionEvent event) throws Exception
    {
        String title = titleFieldAdd.getText();
        String director = directorFieldAdd.getText();
        String star = starFieldAdd.getText();
        String releaseDate = dateComboBoxAdd.getValue();
        String category = categoryComboBoxAdd.getValue();
        try
        {
            dbConnection = DriverManager.getConnection (url, username, passwd);
            statement    = dbConnection.createStatement();
            statement.executeUpdate("insert INTO MOVIES (title,release_date,category,director,star) VALUES ('" + title +
                                                                                                         "','" + releaseDate + 
                                                                                                         "','" + category + 
                                                                                                         "','" + director + 
                                                                                                         "','" + star +
                                                                                                         "')");
            statement.close();
            dbConnection.close();
        } catch(SQLException e)
        {
            tbd_project.Handlers.sqlExceptionHandler(e);
        } 
    }
    //End
    
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
