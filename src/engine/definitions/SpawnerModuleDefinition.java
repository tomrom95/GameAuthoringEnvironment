package engine.definitions;
import java.util.List;
import com.thoughtworks.xstream.io.path.Path;
import engine.IAdder;
import util.Coordinate;
import engine.IPositionable;
import engine.modules.IModule;
import engine.modules.SpawningModule;


public class SpawnerModuleDefinition extends ModuleDefinition {

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
    public IModule create (IPositionable parent) {
        return new SpawningModule(myAdder, myWave.create(), parent);
    }

  

}
