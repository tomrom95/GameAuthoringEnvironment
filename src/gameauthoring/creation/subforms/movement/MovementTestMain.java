package gameauthoring.creation.subforms.movement;

import engine.Game;
import engine.IGame;
import engine.rendering.GameGridConfigNonScaling;
import gameauthoring.creation.subforms.fire.FiringSFC;
import gameplayer.GamePlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MovementTestMain extends Application {

    @Override
    public void start (Stage s) throws Exception {

        IGame game = new Game(new GameGridConfigNonScaling(GamePlayer.INT_PREF_WIDTH,
                                                           GamePlayer.INT_PREF_HEIGHT));
        MovementSubFormController a = new MovementSubFormController(game);
        FiringSFC fSFC = new FiringSFC(game);
        GridPane root = new GridPane();
        root.add(a.getSubFormView().draw(), 0, 0);
        root.add(fSFC.getSubFormView().draw(), 0, 1);
        s.setScene(new Scene(root, 500, 500));
        s.show();
    }

    public static void main (String[] args) {
        launch(args);
    }

}
