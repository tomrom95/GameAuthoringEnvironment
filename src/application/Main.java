package application;

import javafx.application.Application;
import javafx.stage.Stage;
import splash.MainUserInterface;


public class Main extends Application {

    @Override
    public void start (Stage s) throws Exception {
        MainUserInterface mainUI = new MainUserInterface();
        mainUI.init(s);
        s.show();
    }

    public static void main (String[] args) {
        launch(args);
    }
}
