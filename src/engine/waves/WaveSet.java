package engine.waves;

import java.util.ArrayList;
import java.util.List;

import util.TimeDuration;

public class WaveSet implements IWaveSet {

	private List<IWave> myWaves;
	private IWave currentWave;
	private boolean stopWaves;

	public WaveSet() {
		myWaves = new ArrayList<IWave>();
		
	}
	
	@Override
	public void updateCurrentWave(){
		currentWave = myWaves.get(0);
		myWaves.remove(0);
	}

	@Override
	public void update(TimeDuration duration) {
	
	}

	@Override
	public IWave getCurrentWave() {
		return currentWave;
	}

	@Override
	public boolean betweenWaves() {
		return stopWaves;
	}

	@Override
	public boolean allWavesCompleted() {

		return (myWaves.size() == 0) & currentWave.waveCompleted();

	}

	@Override
	public void setWaveList(List<IWave> waves) {
		myWaves = waves;
	}

	@Override
	public List<IWave> getWaveList() {
		return myWaves;
	}

	public TimeDuration getIntervalTime(){
		return myIntervalTime;
	}
	
	public void setIntervalTime(TimeDuration time){
		myIntervalTime.setToZero();
		myIntervalTime.increase(time);
	}

}
