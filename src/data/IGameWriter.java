package data;

import java.io.File;
import java.io.IOException;
import engine.Game;


/**
 * This interface holds the methods required to convert a game into an xml
 * 
 *
 */
public interface IGameWriter {

    void serialize (File file, Game game) throws IOException;
}
