package gameauthoring.characters;

import gameauthoring.Glyph;
import gameauthoring.SpriteListView;


public interface IObjectCreationView extends Glyph {
    
    SpriteListView getSpriteListView ();

    CreationController<?> getCreationController ();
}
