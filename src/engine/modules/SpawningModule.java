package engine.modules;

import engine.IAdder;
import engine.IPositionable;
import engine.effects.DefaultAffectable;
import engine.sprite.ISprite;
import util.TimeDuration;


/**
 * This class is responsible for creating sprites and adding them to a wave for tower defense games.
 *
 */
public class SpawningModule extends DefaultAffectable implements IModule {

    private IAdder myAdder;
    private IWave myWave;
    private IPositionable myParent;
    private TimeDuration myDelay;
    private TimeDuration myThreshold;

    public SpawningModule (IAdder adder, IWave wave, TimeDuration threshold, IPositionable parent) {
        myParent = parent;
        myAdder = adder;
        myWave = wave;
        myDelay = new TimeDuration();
        myThreshold = threshold;
    }
    
    @Override
    public void update (TimeDuration duration) {
        
        myDelay.increase(duration);
        
        if (myWave.hasNext() && myDelay.getMillis() >= myThreshold.getMillis()) {
            ISprite spawn = myWave.getNextSprite();
            spawn.setPath(myParent.getPath());
            myAdder.bufferedAdd(spawn, myParent.getLocation());
            myDelay.setToZero();
        }
    }

}
