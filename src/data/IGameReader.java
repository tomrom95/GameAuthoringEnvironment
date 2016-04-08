package data;

import java.io.File;
import java.io.FileNotFoundException;
import engine.Game;

public interface IGameReader {

    Game readFile (File file) throws LoadErrorException;
}
