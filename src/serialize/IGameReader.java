package serialize;

import java.io.File;
import engine.IGame;


/**
 * This interface represents class takes in a file, and deserializes it to an IGame if it is a valid
 * XML file
 *
 */
public interface IGameReader {

    /**
     * Reads in an XML file describing a fully serialized game.
     */
    IGame readFile (File file) throws LoadErrorException;
}
