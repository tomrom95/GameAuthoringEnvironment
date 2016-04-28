package engine.waves;

import java.util.ArrayList;
import java.util.List;
import util.TimeDuration;


public class WaveSet implements IWaveSet {

    private List<IWave> myWaves;
    private IWave currentWave;
    private boolean stopWaves;

    public WaveSet () {
        myWaves = new ArrayList<IWave>();

    }

    @Override
    public void updateCurrentWave () {
        if(myWaves.isEmpty()) {
            return;
        }
        currentWave = myWaves.get(0);
        myWaves.remove(0);
    }

    @Override
    public IWave getCurrentWave () {
        return currentWave;
    }

    @Override
    public boolean getStopWaves () {
        return stopWaves;
    }

    @Override
    public boolean allWavesCompleted () {
        System.out.println(myWaves + " current waves: "+currentWave);
        return (myWaves.size() == 0) & currentWave.waveCompleted();

    }

    @Override
    public void setWaveList (List<IWave> waves) {
        myWaves = waves;
    }

    @Override
    public List<IWave> getWaveList () {
        return myWaves;
    }

    @Override
    public void setStopWaves (boolean b) {
        stopWaves = b;
    }

}
