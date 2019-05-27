package tbd_project;

import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import static tbd_project.InfoConnection.dbConnection;
import static tbd_project.InfoConnection.*;

public class LogsShowController {       
    
    @FXML
    private ListView logsView;
    
    
    @FXML
    private Button closeButton;
    
    @FXML
    public void Close(ActionEvent event)
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    public void initialize() throws Exception
    {
        String selectString = "SELECT * FROM logs";
        
        try
        {
            dbConnection = DriverManager.getConnection (url, username, passwd);
            statement    = dbConnection.createStatement();

            rs = statement.executeQuery(selectString);
            while(rs.next())
            {
                String i = rs.getString("log_id");
                String a = rs.getString("operation");
                String ts = rs.getString("oper_time");
                String u = rs.getString("username");
                String p=  rs.getString("pass");
                String t = rs.getString("type_user");

                String selectedMovie = "Id: " + i + " |Action: " + a + " |Action Time: " + ts + " |Username: " + u + " |Password: " + p + " |User Type: " + t;
                logsView.getItems().add(selectedMovie);
            }
            statement.close();
            dbConnection.close();
        } catch(SQLException e)
        {
            tbd_project.Handlers.sqlExceptionHandler(e);
        } 
        
    }   
}
