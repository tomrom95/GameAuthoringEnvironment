package gameplayer;

import java.io.File;
import gameauthoring.Glyph;

public interface GameChooserView extends Glyph{
    
    public void addNewGame(File xmlFile);
    
}
