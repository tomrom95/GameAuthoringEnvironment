package engine.waves;

import java.util.List;


public interface IWaveSet {

    IWave getCurrentWave ();

    boolean getStopWaves ();

    boolean allWavesCompleted ();

    void setWaveList (List<IWave> waves);

    List<IWave> getWaveList ();

    void updateCurrentWave ();

    void setStopWaves (boolean b);

}
