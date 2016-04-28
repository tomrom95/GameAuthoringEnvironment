package gameauthoring.levels.waves;

import engine.ILevel;

public class SpawnerViewController {

    private SpawnerView mySpawnerView;
    private ILevel myLevel;
    
    public SpawnerViewController (SpawnerView spawnerView, ILevel level) {
        mySpawnerView = spawnerView;
        myLevel = level;
        initAction();
    }

    private void initAction () {
        mySpawnerView.setButtonAction(e -> setGap());
        
    }

    private void setGap () {
        try {
            myLevel.getWaveSetManager().setGap(mySpawnerView.getDelay());
        }
        catch (NumberFormatException e) {
            //do nothing
        }
    }

}
