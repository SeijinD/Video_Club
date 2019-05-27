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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static tbd_project.InfoConnection.*;
import static tbd_project.LoginController.userType;

public class MainController {
    //Main  
    @FXML
    private Label menuMessage;
    @FXML
    private TextField titleField;
    @FXML
    private TextField directorField;
    @FXML
    private TextField starField;
    @FXML
    private ComboBox<String> dateComboBox;
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private ListView resultView;
    
    //Search
    @FXML
    public void Search(ActionEvent event) throws Exception
    {   
        String title = titleField.getText();
        String director = directorField.getText();
        String star = starField.getText();
        String releaseDate = dateComboBox.getValue();
        String category = categoryComboBox.getValue();
         
        String selectString = "SELECT * FROM movies WHERE 1=1";
        
        if(title != null && !title.equals(""))
            selectString += " AND title='" + title + "'";
        if(director != null && !director.equals(""))
            selectString += " AND director='" + director + "'";
        if(star != null && !star.equals(""))
            selectString += " AND star='" + star + "'";
        if(releaseDate != null && !releaseDate.equals(""))
            selectString += " AND release_date='" + releaseDate + "'";
        if(category != null && !category.equals(""))
            selectString += " AND category='" + category + "'";
        
        try
        {
            dbConnection = DriverManager.getConnection (url, username, passwd);
            statement    = dbConnection.createStatement();

            rs = statement.executeQuery(selectString);
            while(rs.next())
            {
                String t = rs.getString("title");
                String r = rs.getString("release_date");
                String c = rs.getString("category");
                String d=  rs.getString("director");
                String s = rs.getString("star");

                String selectedMovie = "Title: " + t + " |Release Date: " + r + " |Category: " + c + " |Directory: " + d + " |Star: " + s;
                resultView.getItems().add(selectedMovie);
            }
            statement.close();
            dbConnection.close();
        } catch(SQLException e)
        {
            tbd_project.Handlers.sqlExceptionHandler(e);
        } 
    }
    //End Search
    
    //Add Movie Dialog
    @FXML
    public void OpenAddMovie(ActionEvent event) throws Exception
    {
        //create new stage.
        Stage addMovie = new Stage();
        //you can use underdecorated or transparent.
        Parent root = FXMLLoader.load(getClass().getResource("AddMovie.fxml"));       
        //stage.initStyle(StageStyle.UNDERDECORATED);
        addMovie.initStyle(StageStyle.TRANSPARENT);       
        //grab your root here
        root.setOnMousePressed((MouseEvent event1) -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });  
        //move around here
        root.setOnMouseDragged((MouseEvent event1) -> {
            addMovie.setX(event1.getScreenX() - xOffset);
            addMovie.setY(event1.getScreenY() - yOffset);
        });       
        addMovie.setTitle("Add Movie");
        Scene scene = new Scene(root,700,560);
        addMovie.setScene(scene);   
        addMovie.show();  
    } 
    //End Add Movie Dialog
      
    //User Dialog
    @FXML
    public void OpenUser(ActionEvent event) throws Exception
    {
        //create new stage.
        Stage openUser = new Stage();
        //you can use underdecorated or transparent.
        Parent root = FXMLLoader.load(getClass().getResource("User.fxml"));       
        //stage.initStyle(StageStyle.UNDERDECORATED);
        openUser.initStyle(StageStyle.TRANSPARENT);       
        //grab your root here
        root.setOnMousePressed((MouseEvent event1) -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });  
        //move around here
        root.setOnMouseDragged((MouseEvent event1) -> {
            openUser.setX(event1.getScreenX() - xOffset);
            openUser.setY(event1.getScreenY() - yOffset);
        });       
        openUser.setTitle("User");
        Scene scene = new Scene(root,485,375);
        int type_user;
        if (userType == "Admin")
            type_user = 1;
        else
             type_user = 0;
        if(type_user == 1)
        {
            ((Button) scene.lookup("#showLogsButton")).setVisible(true);
        }
        openUser.setScene(scene);   
        openUser.show();  
    } 
    //User Movie Dialog
      
    //Exit Button
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
    
    
}
