package engine.definitions;

import engine.IAdder;
import engine.modules.IModule;
import engine.modules.SpawningModule;

public class SpawnerModuleDefinition extends ModuleDefiniton {

    private WaveDefinition myWave;
    private IAdder myAdder;
    
    public SpawnerModuleDefinition (IAdder adder, WaveDefinition wave) {
        setWave(wave);
        setAdder(adder);
    }
    
    private void setAdder (IAdder adder) {
       myAdder = adder;
        
    }

    public void setWave (WaveDefinition wave) {
        myWave = wave;
    }

    @Override
    public IModule create () {
        return new SpawningModule(myAdder, myWave.create());
    }

}
