package library;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import engine.IGame;
import gameauthoring.util.ErrorMessage;
import gameplayer.GamePlayer;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import serialize.GameReader;
import serialize.LoadErrorException;


public class GameLibraryController {

    private GameLibraryView myLibView;

    public GameLibraryController () {
        myLibView = new GameLibraryView();
        populateGames();
    }

    private void populateGames () {
        try {
            Files.walk(Paths.get("resources/saved_games/"))
                    .filter(path -> !path.getFileName().toString().startsWith("."))
                    .filter(path -> Files.isRegularFile(path))
                    .map(path -> path.toFile())
                    .forEach(file -> {
                        try {
                            myLibView.addGameToDisplay(createGameDisp(file));
                        }
                        catch (LoadErrorException e) {
                            new ErrorMessage(e.getMessage()).showError();
                        }
                    });
        }
        catch (IOException e) {
            new ErrorMessage(e.getMessage()).showError();
        }
    }

    private Node createGameDisp (File file) throws LoadErrorException {
        IGame game = new GameReader().readFile(file);
        Node poster = new GamePosterFactory().createGamePoster(game);
        poster.setOnMouseClicked(event -> playOnDoubleClick(event, game));
        return poster;
    }

    private void playOnDoubleClick (MouseEvent mouseEvent, IGame game) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                new GamePlayer(game);
            }
        }
    }

    public void init (Stage s) {
        myLibView.init(s);
    }
}
