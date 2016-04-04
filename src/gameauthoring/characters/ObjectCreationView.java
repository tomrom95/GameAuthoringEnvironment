package gameauthoring.characters;

import gameauthoring.Glyph;
import gameauthoring.SpriteListView;


public interface ObjectCreationView extends Glyph {
    
    SpriteListView getSpriteListView ();

    CreationController<?> getCreationController ();
}
