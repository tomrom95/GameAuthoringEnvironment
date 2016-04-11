package engine.definitions;

import java.util.List;
import engine.modules.Wave;

public class WaveDefinition {

    List<SpriteDefinition> mySprites;
    
    public WaveDefinition (List<SpriteDefinition> sprites) {
        setListSprites(sprites);
    }
    
    public void setListSprites (List<SpriteDefinition> sprites) {
        mySprites = sprites;
    }
    
    public Wave create () {
        return new Wave(mySprites);
    }
}
