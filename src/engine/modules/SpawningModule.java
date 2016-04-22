package engine.modules;

import java.util.ArrayList;
import java.util.List;

import engine.IAdder;
import engine.IGame;
import engine.Positionable;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.spawnerdef.WaveDefinition;
import engine.effects.DefaultAffectable;
import engine.sprite.ISprite;
import engine.waves.IWave;
import engine.waves.IWaveSet;
import engine.waves.WaveSet;
import util.TimeDuration;


/**
 * This class is responsible for creating sprites and adding them to a wave for tower defense games.
 *
 */
public class SpawningModule extends DefaultAffectable implements IModule {

    private IAdder myAdder;
    private IGame myGame;
    private IWaveSet myWaveSet;
    private Positionable myParent;
 
    private List<SpriteDefinition> mySpritesToSpawn;

    public SpawningModule (IAdder adder, IGame game, Positionable parent, List<WaveDefinition> waves) {
        myParent = parent;
        myAdder = adder;
        myGame = game;
        myWaveSet = new WaveSet();
        List<IWave> myWaves = new ArrayList<IWave>();
        waves.stream().forEachOrdered(p -> myWaves.add(p.create()));
        myWaveSet.setWaveList(myWaves);
        
        
    }

    @Override
    public void update (TimeDuration duration) {
        myWave = myGame.getLevelManager().getCurrentLevel().getWaveSet().getCurrentWave();
        
        myDelay.increase(duration);
        //TODO : change these methods to seconds?
        if (!myWave.waveCompleted() && myDelay.getMillis() >= myThreshold.getMillis() & !myGame.getLevelManager().getCurrentLevel().getWaveSet().betweenWaves()) {
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
