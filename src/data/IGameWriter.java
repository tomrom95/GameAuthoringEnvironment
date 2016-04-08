package data;

import java.io.File;
import java.io.IOException;
import engine.Game;

public interface IGameWriter {

    void serialize(File file, Game game) throws IOException;
}
