package testing;

import java.io.File;
import engine.IGame;
import gameplayer.GamePlayer;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import serialize.GameReader;

public class DemoFileChooser extends Application {

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        FileChooser chooser = new FileChooser();
        File f = chooser.showOpenDialog(primaryStage);
        IGame xmlGame = new GameReader().readFile(f);
        GamePlayer gp = new GamePlayer(xmlGame);
    }
}
