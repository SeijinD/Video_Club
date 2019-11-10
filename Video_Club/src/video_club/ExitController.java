package video_club;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ExitController {
    //Exit Dialog
    @FXML
    private Button noExitButton;
    @FXML
    private Button yesExitButton;
    
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
}
