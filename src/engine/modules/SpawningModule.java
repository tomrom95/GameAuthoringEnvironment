package engine.modules;

import engine.IAdder;
import engine.effects.DefaultAffectable;
import util.TimeDuration;


public class SpawningModule extends DefaultAffectable implements IModule {

    private IAdder myAdder;
    private IWave myWave;

    public SpawningModule (IAdder adder, IWave wave) {
        myAdder = adder;
    }

    @Override
    public void update (TimeDuration duration) {

        myAdder.add(myWave.getNextSprite());
    }

  

}
