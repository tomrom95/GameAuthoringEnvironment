package gameauthoring.characters;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TesterMain extends Application {

    @Override
    public void start (Stage arg0) throws Exception {
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void main(String[] args){
        launch(args);
    }

}
