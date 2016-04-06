package engine;

import engine.sprite.SpriteType;
import javafx.collections.ObservableList;

public interface ISpriteGroup {
    
    boolean contains (SpriteType spriteType);
    
    ObservableList<SpriteType> getSpriteTypes ();

}
