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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static tbd_project.InfoConnection.*;

public class LoginController {
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
    static String userEdit = "";
    static String userType = "";
    @FXML
    public void Login(ActionEvent event) throws Exception
    {
        try
        {
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
                userEdit = user;
                if (type_user == 1)
                    userType = "Admin";
                else
                    userType = "User";
                if(type_user == 1)
                {
                    ((Button) scene.lookup("#openAddMovieButton")).setVisible(true);
                    ((Button) scene.lookup("#openEditMovieButton")).setVisible(true);
                    ((Button) scene.lookup("#openDeleteMovieButton")).setVisible(true);
                }
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
            tbd_project.Handlers.sqlExceptionHandler(e);
        }
    }
    //End Login Dialog
    
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
