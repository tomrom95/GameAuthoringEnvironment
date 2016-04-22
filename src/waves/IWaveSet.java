package waves;

import engine.Updateable;

public interface IWaveSet extends Updateable {
	
	IWave getCurrentWave ();
		
	boolean betweenWaves ();
	
	boolean allWavesCompleted();
}
