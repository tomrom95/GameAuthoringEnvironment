package waves;

import engine.definitions.SpriteDefinition;
import engine.sprite.ISprite;


/**
 * This interface provides the methods needed to spawn sprites based on a wave, or time duration
 * step
 *
 */
public interface IWave {

    /**
     * @return the next Sprite to be spawned
     */
	boolean waveCompleted ();
	   
	void spawnSprite (SpriteDefinition s);
	
}
