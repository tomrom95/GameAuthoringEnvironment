package engine.definitions.spawnerdef;

import java.util.List;
import engine.IAdder;
import engine.ILevel;
import engine.Positionable;
import engine.definitions.moduledef.ModuleDefinition;
import engine.modules.IModule;
import engine.modules.SpawningModule;


/**
 * This class represents the definition for a spawning module, or one that pops out sprites from a
 * specified wave at a specified interval
 *
 */
public class SpawnerModuleDefinition extends ModuleDefinition {

    private ILevel myLevel;
    private IAdder myAdder;
    private List<WaveDefinition> myWaves;

    public SpawnerModuleDefinition (IAdder adder,
                                    ILevel level,
                                    List<WaveDefinition> waves) {
        setLevel(level);
        setAdder(adder);
        myWaves = waves;
    }

    private void setLevel (ILevel level) {
        myLevel = level;

    }

    private void setAdder (IAdder adder) {
        myAdder = adder;
    }

    public List<WaveDefinition> getWaveDefinitions () {
        return myWaves;
    }

    public void setWaveDefinitions (List<WaveDefinition> defs) {
        myWaves = defs;
    }

    @Override
    public IModule create (Positionable parent) {
        return new SpawningModule(myAdder, myLevel, parent, myWaves);
    }

}
