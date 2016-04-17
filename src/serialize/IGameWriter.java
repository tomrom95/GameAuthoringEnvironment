package serialize;

import java.io.File;
import java.io.IOException;
import engine.IGame;


/**
 * This interface represents a serializer that takes in a Game and serializes it into an XML file
 * for later use
 *
 */
public interface IGameWriter {

    void serialize (File file, IGame game) throws IOException;
}
