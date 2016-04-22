package engine.modules;

import java.util.List;

import engine.IAdder;
import engine.IGame;
import engine.Positionable;
import engine.definitions.SpriteDefinition;
import engine.effects.DefaultAffectable;
import engine.sprite.ISprite;
import util.TimeDuration;
import waves.IWave;


/**
 * This class is responsible for creating sprites and adding them to a wave for tower defense games.
 *
 */
public class SpawningModule extends DefaultAffectable implements IModule {

    private IAdder myAdder;
    private IGame myGame;
    private IWave myWave;
    private Positionable myParent;
    private TimeDuration myDelay;
    private TimeDuration myThreshold;
    private List<SpriteDefinition> mySpritesToSpawn;

    public SpawningModule (IAdder adder, IGame game, TimeDuration threshold, Positionable parent, List<SpriteDefinition> sprites) {
        myParent = parent;
        myAdder = adder;
        myGame = game;
        myDelay = new TimeDuration();
        myThreshold = threshold;
        mySpritesToSpawn = sprites;
        myWave = myGame.getLevelManager().getCurrentLevel().getWaveSet().getCurrentWave();
    }

    @Override
    public void update (TimeDuration duration) {
        myWave = myGame.getLevelManager().getCurrentLevel().getWaveSet().getCurrentWave();
        
        myDelay.increase(duration);
        //TODO : change these methods to seconds?
        if (!myWave.waveCompleted() && myDelay.getMillis() >= myThreshold.getMillis()) {
            mySpritesToSpawn.stream().forEach(p -> spawnSprite(p));
        }
    }
    
    private void spawnSprite(SpriteDefinition s){
    	if(myWave.spawnSprite(s)){
    		ISprite spawn = s.create();
            spawn.setPath(myParent.getPath());
            myAdder.bufferedAdd(spawn, myParent.getLocation());
            myDelay.setToZero();
    	} 	
    }

}
