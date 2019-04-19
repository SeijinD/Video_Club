package tbd_project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLDocumentController implements Initializable {
    
    // Standar info connection
    static String     driverClassName = "org.postgresql.Driver" ;
    static String     url             = "jdbc:postgresql://dblabs.it.teithe.gr:5432/it175058" ;
    static Connection dbConnection    = null;
    static String     username        = "it175058";
    static String     passwd          = "nikopoli";
    static Statement  statement       = null;
    static ResultSet  rs              = null;
    //End
    
    //Login Dialog
    private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    private Label messageLogin;
    @FXML
    private TextField userNameLogin;  
    @FXML
    private PasswordField passwordLogin;
    @FXML
    private Button loginButton;
 
    //Login Dialog
    @FXML
    public void Login(ActionEvent event) throws Exception
    {
        try
        {
            Class.forName (driverClassName);
            dbConnection = DriverManager.getConnection (url, username, passwd);
            statement    = dbConnection.createStatement();
            String user = userNameLogin.getText();
            String pass = passwordLogin.getText();
            String selectString = "SELECT username, password, type_user FROM login WHERE username='" + user + "' and password='" + pass + "'";
            rs = statement.executeQuery(selectString); 
            if(user.equals("") || pass.equals(""))
                messageLogin.setText("Username or Password is empty!");
            else if(rs.next())
            {
                int type_user = Integer.parseInt(rs.getString("type_user"));
                //create new stage.
                Stage main = new Stage();
                //you can use underdecorated or transparent.
                Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
                //stage.initStyle(StageStyle.UNDERDECORATED);
                main.initStyle(StageStyle.TRANSPARENT);       
                //grab your root here
                root.setOnMousePressed((MouseEvent event1) -> {
                    xOffset = event1.getSceneX();
                    yOffset = event1.getSceneY();
                });  
                //move around here
                root.setOnMouseDragged((MouseEvent event1) -> {
                    main.setX(event1.getScreenX() - xOffset);
                    main.setY(event1.getScreenY() - yOffset);
                });       
                main.setTitle("Main");
                Scene scene = new Scene(root,700,700);
                main.setScene(scene);          
                main.show();
                if(type_user == 1)
                    ((Button) scene.lookup("#openAddMovieButton")).setVisible(true);
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close(); 
            }
            else
            {
               messageLogin.setText("User not found!");
            }
            statement.close();
            dbConnection.close();
        } catch(SQLException e)
        {
            sqlExceptionHandler(e);
        }
    }
    //End Login Dialog
    
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
    
    //Exit Dialog
    @FXML
    public void NoExit(ActionEvent event)
    {
        Stage stage = (Stage) noExitButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void YesExit(ActionEvent event)
    {
        System.exit(0);
    }
    //End Exit Dialog
    
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
    
    @FXML
    public void Create(ActionEvent event) throws Exception
    {
        //create new stage.
        Stage signIn = new Stage();
        //you can use underdecorated or transparent.
        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));       
        //stage.initStyle(StageStyle.UNDERDECORATED);
        signIn.initStyle(StageStyle.TRANSPARENT);       
        //grab your root here
        root.setOnMousePressed((MouseEvent event1) -> {
            xOffset = event1.getSceneX();
            yOffset = event1.getSceneY();
        });  
        //move around here
        root.setOnMouseDragged((MouseEvent event1) -> {
            signIn.setX(event1.getScreenX() - xOffset);
            signIn.setY(event1.getScreenY() - yOffset);
        });       
        signIn.setTitle("Create Account");
        Scene scene = new Scene(root,400,315);
        signIn.setScene(scene);   
        signIn.show();  
    }
    //End Create Dialog
    
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
                Class.forName (driverClassName);
                dbConnection = DriverManager.getConnection (url, username, passwd);
                statement = dbConnection.createStatement();
                statement.executeUpdate("insert INTO LOGIN (username, password, type_user) VALUES ('" + user1 + "','" + pass1 + "','" + type_user1 + "')");
                statement.close();
                dbConnection.close(); 
                Stage stage = (Stage) createButton.getScene().getWindow();
                stage.close();  
            } catch(SQLException e)
            {
                sqlExceptionHandler(e);
            } 
        }
    }
    //End Create Account
    
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
            Class.forName (driverClassName);
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
            sqlExceptionHandler(e);
        } 
    }
    //End Search
    
    //Add Movie Dialog
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
        addMovie.setTitle("Create Account");
        Scene scene = new Scene(root,700,560);
        addMovie.setScene(scene);   
        addMovie.show();  
    } 
    //End Add Movie Dialog
    //End Main
    
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
            Class.forName (driverClassName);
            dbConnection = DriverManager.getConnection (url, username, passwd);
            statement    = dbConnection.createStatement();
            statement.executeUpdate("insert INTO MOVIES (title,release_date,category,director,star) VALUES ('" + title + "','" + releaseDate + "','" + category + "','" +  director + "','" + star + "')");
            statement.close();
            dbConnection.close();
        } catch(SQLException e)
        {
            sqlExceptionHandler(e);
        } 
    }
    //End
    
    //Πρεπει να το αλλαξω*
    //Handlers    
    private static void sqlExceptionHandler(SQLException e){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/Logs/SQL_Logs.txt", true));
            writer.write("==============================\n");
            writer.write(LocalDateTime.now() + "\n");
            writer.write("==============================\n");
            writer.write("Message: " + e.getMessage() + "\n");
            writer.write("SQLState: " + e.getSQLState() + "\n");
            writer.write("ErrorCode: " + e.getErrorCode() + "\n");
            writer.write("==============================\n");
            writer.close();
        } catch(IOException io){
            System.out.println(io);
        }
        System.out.println("Message: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("ErrorCode: " + e.getErrorCode());
    }

    private static void fxExceptionHandler(Exception e){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/Logs/JavaFX_Logs.txt", true));
            writer.write("==============================\n");
            writer.write(LocalDateTime.now() + "\n");
            writer.write("==============================\n");
            writer.write("Error Message: " + e + "\n");
            writer.write("==============================\n");
            writer.close();
        } catch(IOException io){
            System.out.println(io);
        }
        System.out.println("Error Message: " + e + "\n");
    }
    //End Handlers
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    }       
}
