package gameauthoring.characters;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Error message simply prompts the user if some error appears. Other classes 
 * call upon this method with the appropriate error string.
 * 
 * **Note, javafx imports sometimes do not work, must be deleted and imported again to fix issue**
 * 
 * @author JoeLilien
 *
 */
public class ErrorMessage {
    private String errorTitle = "ERROR";
    private Alert errAlert;
    public ErrorMessage(String error){
        initAlert(error);
    }

    private void initAlert (String error) {
        errAlert = new Alert(AlertType.ERROR);
        errAlert.setTitle(errorTitle);
        errAlert.setHeaderText(error);
    }

    public void showError(){
        errAlert.showAndWait();
    }
}
