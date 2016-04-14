package engine.definitions;
import java.util.List;
import com.thoughtworks.xstream.io.path.Path;
import engine.IAdder;
import util.Coordinate;
import util.TimeDuration;
import engine.IPositionable;
import engine.modules.IModule;
import engine.modules.SpawningModule;


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
    public IModule create (IPositionable parent) {
        return new SpawningModule(myAdder, myWave.create(), new TimeDuration(myDelay), parent);
    }

  

}
