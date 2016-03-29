package gameplayer;

import java.util.List;
import gameauthoring.Glyph;

public interface TowerPicker extends Glyph{
    
    public void loadTowers(List<Sprite> availableTowers);
    
    public void clearTowers();
    
}
