package waves;

import java.util.List;

import engine.Attribute;
import engine.AttributeType;
import engine.IAttribute;
import util.TimeDuration;

public class WaveSet implements IWaveSet {

	private List<IWave> myWaves;
	private IWave currentWave;
	private IAttribute myIntervalTime;
	private boolean stopWaves;
	private TimeDuration myTimeSinceLastWave;

	public WaveSet(List<IWave> waves, double interval) {
		myWaves = waves;
		currentWave = myWaves.get(0);
		myWaves.remove(0);
		myIntervalTime = new Attribute(interval, AttributeType.WAVE_INTERVAL);
		myTimeSinceLastWave = new TimeDuration(0);
	}

	@Override
	public void update(TimeDuration duration) {
		
		myTimeSinceLastWave.increase(duration);

		
		if(myTimeSinceLastWave.getSeconds() >= myIntervalTime.getValueProperty().get() & stopWaves){
			
			stopWaves = false;
			myTimeSinceLastWave.setToZero();
		}
		else if (currentWave.waveCompleted() & !stopWaves) {
			stopWaves = true;
			myTimeSinceLastWave.setToZero();
			currentWave = myWaves.get(0);
			myWaves.remove(0);
		}
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


}
