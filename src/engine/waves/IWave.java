package engine.waves;

import engine.Updateable;
import engine.definitions.concrete.SpriteDefinition;


/**
 * This interface provides the methods needed to spawn sprites based on a wave, or time duration
 * step
 *
 */
public interface IWave extends Updateable {

    /**
     * @return the next Sprite to be spawned
     */
	boolean waveCompleted ();
	   
	boolean spawnSprite (SpriteDefinition s);
	
}
