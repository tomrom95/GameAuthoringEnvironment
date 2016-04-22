package engine.definitions.spawnerdef;

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
    private double myDelay;
    private List<SpriteDefinition> mySprites;

    public SpawnerModuleDefinition (IAdder adder, IGame game, double delay, List<SpriteDefinition> sprites) {
        setGame(game);
        setAdder(adder);
        myDelay = delay;
        mySprites = sprites;
    }

    private void setGame(IGame game) {
    	myGame = game;
    	
	}

	private void setAdder (IAdder adder) {
        myAdder = adder;
    }

    public void setDelay (double delay) {
        myDelay = delay;
    }

    public double getDelay () {
        return myDelay;
    }

    public List<SpriteDefinition> getSpriteDefinitions(){
    	return mySprites;
    }
    
    public void setSpriteDefinitions(List<SpriteDefinition> defs){
    	mySprites = defs;
    }
    
    @Override
    public IModule create (Positionable parent) {
        return new SpawningModule(myAdder, myGame, new TimeDuration(myDelay), parent, mySprites);
    }

}
