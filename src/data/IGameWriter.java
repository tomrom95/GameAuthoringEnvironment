package data;

import java.io.File;
import java.io.IOException;
import engine.Game;
import engine.IGame;


/**
 * This interface holds the methods required to convert a game into an xml
 *
 *
 */
public interface IGameWriter {

    void serialize (File file, IGame game) throws IOException;
}
