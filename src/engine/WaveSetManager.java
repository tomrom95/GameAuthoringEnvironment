package engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import engine.waves.IWaveSet;
import util.TimeDuration;


public class WaveSetManager implements IWaveSetManager {

    List<IWaveSet> mySets;
    private TimeDuration myLevelGap;
    private TimeDuration timeSinceWaveCompletion;

    public WaveSetManager () {
        mySets = new ArrayList<IWaveSet>();
        myLevelGap = new TimeDuration(0);
        timeSinceWaveCompletion = new TimeDuration(0);
        
        setGap(20000);
    }
    

    @Override
    public void update (TimeDuration duration) {
        
        if (levelComplete()) {
            System.out.println("LEVEL IS COMPLETE");
            stopSpawners();
        }
        else {
            stopSpawners();
            if (allCurrentWavesComplete()) {
                timeSinceWaveCompletion.increase(duration);
                System.out.println("about to enable spawners, level complete is: "+levelComplete());
                enableSpawners();
            }

        }

    }

    @Override
    public boolean levelComplete () {
        Iterator<IWaveSet> i = mySets.iterator();
        while (i.hasNext()) {
            if (!i.next().allWavesCompleted()) {
                System.out.println("WAVES NOT ALL COMPLETE");
                return false;
            }
        }
        return true;
    }

    private void stopSpawners () {
        mySets.stream().forEach(p -> {
            System.out.println("Current wave is completed: "+ p.getCurrentWave().waveCompleted());
            if (p.getCurrentWave().waveCompleted()) {
                p.setStopWaves(true);
            }
        });
    }

    private void enableSpawners () {
        if (timeSinceWaveCompletion.getMillis() >= myLevelGap.getMillis()) {
            mySets.stream().forEach(p -> p.updateCurrentWave());
            mySets.stream().forEach(p -> p.setStopWaves(false));
            timeSinceWaveCompletion.setToZero();
        }

    }

    private boolean allCurrentWavesComplete () {
        Iterator<IWaveSet> i = mySets.iterator();
        while (i.hasNext()) {
            if (!i.next().getCurrentWave().waveCompleted()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addWaveSet (IWaveSet set) {
        mySets.add(set);
    }

    @Override
    public void setGap (double d) {
        myLevelGap = new TimeDuration(d);
    }

    @Override
    public TimeDuration getGap () {
        return myLevelGap;
    }

}
