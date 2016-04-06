package engine;

import javafx.collections.ObservableList;

public interface ISpriteGroup {
    
    boolean contains (SpriteType spriteType);
    
    ObservableList<SpriteType> getSpriteTypes ();

}
