package gameauthoring.creation.subforms.movement;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MovementTestMain extends Application{

    @Override
    public void start (Stage s) throws Exception {
        MovementSubFormView a = new MovementSubFormView();
        Group root = new Group();
        root.getChildren().add(a.draw());
        s.setScene(new Scene(root, 500, 500));
        s.show();
    }
    
    public static void main (String[] args){
        launch(args);
    }

}
