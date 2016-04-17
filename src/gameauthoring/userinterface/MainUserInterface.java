package gameauthoring.userinterface;

import gameauthoring.tabs.AuthoringView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MainUserInterface {

    private static final String TITLE = "Welcome to GitDepends Game Salad";
    private GridPane myLayout;
    public void init (Stage s) {
        Button createGameBtn = new Button("Create Game");
        Button loadGameBtn = new Button("Load Existing Game");
        myLayout = new GridPane();
        myLayout.add(createGameBtn, 0, 0);
        myLayout.add(loadGameBtn, 0, 1);
        createGameBtn.setOnAction(e->createGame(s));
        loadGameBtn.setOnAction(e->loadGame());
        Scene scene = new Scene(myLayout,AuthoringView.WIDTH, AuthoringView.HEIGHT);
        s.setTitle(TITLE);
        s.setScene(scene);
        s.show();
    }
    
    private void createGame (Stage s){
        AuthoringView aView = new AuthoringView();
        aView.init(s);
    }
    
    private void loadGame () {
        
    }
}
