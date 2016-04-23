package engine;

import engine.waves.IWaveSet;
import util.TimeDuration;

public interface IWaveSetManager extends Updateable {
    
    void addWaveSet (IWaveSet set);
    
    void setGap(double d);
    
    TimeDuration getGap();
    
    boolean levelComplete();

}
