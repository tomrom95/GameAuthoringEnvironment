package gameplayer;

import java.util.List;
import gameauthoring.Glyph;

public interface SpritePicker extends Glyph{
    
    public void loadSprites(List<Sprite> availableTowers);
    
    public void clearTowers();
    
}
