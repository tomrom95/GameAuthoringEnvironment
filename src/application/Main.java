package application;

import gameauthoring.tabs.AuthoringView;
import gameauthoring.userinterface.MainUserInterface;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start (Stage s) throws Exception {
        MainUserInterface mainUI = new MainUserInterface();
        mainUI.init(s);
        s.show();
//        AuthoringView aView = new AuthoringView();
//        aView.init(s);
//        s.show();
    }

    public static void main (String[] args) {
        launch(args);
    }
}
