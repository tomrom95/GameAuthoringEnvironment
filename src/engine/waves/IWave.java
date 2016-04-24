package engine.waves;

import engine.Updateable;
import engine.definitions.concrete.SpriteDefinition;
import engine.sprite.ISprite;
import util.TimeDuration;


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

    ISprite spawnSprite ();
    
    boolean satisfiedSpawnInterval(TimeDuration t);

}
