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

    public SpawningModule (IAdder adder, IGame game, Positionable parent, List<WaveDefinition> waves) {
        myParent = parent;
        myAdder = adder;
        myGame = game;
        myWaveSet = new WaveSet();
        List<IWave> myWaves = new ArrayList<IWave>();
        waves.stream().forEachOrdered(p -> myWaves.add(p.create()));
        myWaveSet.setWaveList(myWaves);
        myWaveSet.updateCurrentWave();
        
    }

    @Override
    public void update (TimeDuration duration) {
    	if(WaveSet.keepSpawning()){
    		 ISprite spawn = myWaveSet.getCurrentWave().spawnSprite()
    			        spawn.setPath(myParent.getPath());	
    			        myAdder.bufferedAdd(spawn, myParent.getLocation());
    	}
       
    }
    
   
    
}
