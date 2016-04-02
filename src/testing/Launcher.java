package testing;

import engine.IOInterpeter;
import gameplayer.GamePlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Launcher extends Application {

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        
        Stage myStage = new Stage();
        Pane myPane = new Pane();
        
        myStage.setScene(new Scene(myPane));
        myStage.show();
        
        IOInterpeter io = new IOInterpeter (myPane);
        myStage.show();
    }
    

}
