package gameauthoring.levels.waves;

import engine.ILevel;
import gameauthoring.util.ErrorMessage;


/**
 * Responsible for settign the action of the spawner view
 *
 * @author RyanStPierre
 *
 */
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
            ErrorMessage message = new ErrorMessage(e.getMessage());
            message.showError();
        }
    }

}
