package gameauthoring.levels.waves;

/**
 * Controller helping assist in the functionality of the level spawner view
 * 
 * @author RyanStPierre
 *
 */
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
