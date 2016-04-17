package engine.definitions;

import engine.IAdder;
import engine.Positionable;
import engine.modules.IModule;
import engine.modules.SpawningModule;
import util.TimeDuration;


/**
 * This class represents the definition for a spawning module, or one that pops out sprites from a
 * specified wave at a specified interval
 *
 */
public class SpawnerModuleDefinition extends ModuleDefinition {

    private WaveDefinition myWave;
    private IAdder myAdder;
    private double myDelay;

    public SpawnerModuleDefinition (IAdder adder, WaveDefinition wave, double delay) {
        setWave(wave);
        setAdder(adder);
        myDelay = delay;
    }

    private void setAdder (IAdder adder) {
        myAdder = adder;
    }

    public void setWave (WaveDefinition wave) {
        myWave = wave;
    }

    public void setDelay (double delay) {
        myDelay = delay;
    }

    public double getDelay () {
        return myDelay;
    }

    @Override
    public IModule create (Positionable parent) {
        return new SpawningModule(myAdder, myWave.create(), new TimeDuration(myDelay), parent);
    }

}
