package engine.definitions.spawnerdef;

import java.util.ArrayList;
import java.util.List;

import engine.IAdder;
import engine.IGame;
import engine.Positionable;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.ModuleDefinition;
import engine.modules.IModule;
import engine.modules.SpawningModule;
import util.TimeDuration;


/**
 * This class represents the definition for a spawning module, or one that pops out sprites from a
 * specified wave at a specified interval
 *
 */
public class SpawnerModuleDefinition extends ModuleDefinition {

    private IGame myGame;
    private IAdder myAdder;
    private List<WaveDefinition> myWaves;

    public SpawnerModuleDefinition (IAdder adder, IGame game, List<WaveDefinition> waves) {
        setGame(game);
        setAdder(adder);
        myWaves = waves;
    }

    private void setGame(IGame game) {
    	myGame = game;
    	
	}

	private void setAdder (IAdder adder) {
        myAdder = adder;
    }


    public List<WaveDefinition> getWaveDefinitions(){
    	return myWaves;
    }
    
    public void setWaveDefinitions(List<WaveDefinition> defs){
    	myWaves = defs;
    }
    
    @Override
    public IModule create (Positionable parent) {
    	return new SpawningModule(myAdder, myGame, parent, myWaves);
    }

}
