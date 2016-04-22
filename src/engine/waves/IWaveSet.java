package engine.waves;

import java.util.List;

import engine.Updateable;

public interface IWaveSet extends Updateable {
	
	IWave getCurrentWave ();
		
	boolean betweenWaves ();
	
	boolean allWavesCompleted();
	
	void setWaveList(List<IWave> waves);
	
	List<IWave> getWaveList();
}
