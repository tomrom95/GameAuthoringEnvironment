package gameauthoring.application;

import gameauthoring.AuthoringView;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start (Stage s) throws Exception {
        AuthoringView aView = new AuthoringView();
        aView.init(s);
        s.show();
    }

    public static void main (String[] args) {
        launch(args);
    }
}
