package gameauthoring.levels.waves;

public class SpawnerAuthoringController {

    private SpawnerView mySpawnerView;
    private WaveOptionView myWaveView;
    
    public SpawnerAuthoringController (SpawnerView spawner, WaveOptionView wave) {
        mySpawnerView = spawner;
        myWaveView = wave;
        initAction();
    }

    private void initAction () {
      
        myWaveView.setTarget(mySpawnerView);
        
    }

}
