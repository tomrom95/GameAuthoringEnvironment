package engine.definitions;

import java.util.List;
import waves.SpriteWaveData;
import waves.Wave;


/**
 * This class represents a wave definition, which specifies a list of sprite definitions to be
 * spawned in order
 *
 */
public class WaveDefinition implements IDefinition {

    private List<SpriteWaveData> mySprites;

    public WaveDefinition (List<SpriteWaveData> sprites) {
    	setListSprites(sprites);
    }

    public void setListSprites (List<SpriteWaveData> sprites) {
        mySprites = sprites;
    }

    public Wave create(){
    	return new Wave(mySprites);
    }

}
