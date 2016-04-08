package data;

import java.io.File;
import engine.Game;

public interface IGameWriter {

    File serialize(Game game);
}
