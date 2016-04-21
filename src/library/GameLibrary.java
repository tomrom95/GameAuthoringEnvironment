package library;

import gameauthoring.tabs.AuthoringView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameLibrary {
    
    private BorderPane myLayout = new BorderPane();

    public void init (Stage s) {
        Scene scene = new Scene(myLayout, AuthoringView.WIDTH, AuthoringView.HEIGHT);
        s.setScene(scene);
    }
}
