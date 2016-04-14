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

    public SpawningModule (IAdder adder, IWave wave, IPositionable parent) {
        myParent = parent;
        myAdder = adder;
        myWave = wave;
    }
    
    @Override
    public void update (TimeDuration duration) {
        if (myWave.hasNext()) {
            ISprite spawn = myWave.getNextSprite();
            spawn.setPath(myParent.getPath());
            myAdder.bufferedAdd(spawn, myParent.getLocation());
        }
    }

}
