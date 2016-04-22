package engine.definitions.spawnerdef;

import java.util.List;
import java.util.stream.Collectors;
import engine.definitions.concrete.IDefinition;
import engine.waves.SpriteWaveData;
import engine.waves.Wave;


/**
 * This class represents a wave definition, which specifies a list of sprite definitions to be
 * spawned in order
 *
 */
public class WaveDefinition implements IDefinition {

    private List<WaveDataDefinition> myData;

    public WaveDefinition (List<WaveDataDefinition> sprites) {
    	setListSprites(sprites);
    }

    public void setListSprites (List<WaveDataDefinition> sprites) {
        myData = sprites;
    }

    public Wave create(){
        List<SpriteWaveData> spriteData = myData.stream().map(def -> def.create())
                .collect(Collectors.toList());
    	return new Wave(spriteData);
    }

}
