package facebook_util;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * A simple HTML browser.
 * 
 * @author Robert C. Duvall
 */
public class Main extends Application {

    @Override
    public void start (Stage stage) {
        // create program specific components
        BrowserView display = new BrowserView();
        // give the window a title
        // add our user interface components to Frame and show it
        stage.setScene(display.getScene());
        stage.show();
    }


    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
