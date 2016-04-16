package engine.definitions;

import java.util.List;
import engine.modules.Wave;


/**
 * This class represents a wave definition, which specifies a list of sprite definitions to be
 * spawned in order
 *
 */
public class WaveDefinition implements IDefinition {

    private List<SpriteDefinition> mySprites;

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
