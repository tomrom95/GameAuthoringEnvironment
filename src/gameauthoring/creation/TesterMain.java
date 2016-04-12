package gameauthoring.creation;

import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.ProfileSubFormView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TesterMain extends Application {

    @Override
    public void start (Stage stage) throws Exception {
        ISubFormView prof = new ProfileSubFormView();
        Group root = new Group(prof.draw());
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main (String[] args){
        launch(args);
    }

}
