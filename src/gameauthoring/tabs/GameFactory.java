package gameauthoring.tabs;

import engine.Game;


/**
 * Factory class for creating game.
 * TODO loadGame method which loads XML file and creates the game
 * 
 * @author Jin An
 *
 */
public class GameFactory {

    public Game createGame () {
        Game game = new Game();
        return game;
    }

    // public Game loadGame(File xml);
}
