package engine.waves;

import java.util.List;
import util.TimeDuration;

public class WaveInfiniteRandom extends Wave implements IWave {

    public WaveInfiniteRandom (List<WaveBlock> sprites) {
        super(sprites);
        // TODO Auto-generated constructor stub
    }
    
    @Override 
    public boolean waveCompleted (){
        return false;
    }
    
    @Override
    public boolean satisfiedSpawnInterval(TimeDuration t){
        
    }
    


}
