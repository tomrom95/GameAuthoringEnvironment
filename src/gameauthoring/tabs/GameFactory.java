package gameauthoring.tabs;

import engine.Game;
import engine.rendering.GameGridConfigNonScaling;
import gameplayer.GamePlayer;


/**
 * Factory class for creating game.
 * TODO loadGame method which loads XML file and creates the game
 *
 * @author Jin An
 *
 */
public class GameFactory {

    public Game createGame () {
        Game game =
                new Game(new GameGridConfigNonScaling(GamePlayer.INT_PREF_WIDTH,
                                                      GamePlayer.INT_PREF_HEIGHT));
        return game;
    }

    // public Game loadGame(File xml);
}
