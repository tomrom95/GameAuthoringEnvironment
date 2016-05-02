package gameauthoring.levels.waves;

import java.util.ResourceBundle;
import engine.definitions.spawnerdef.WaveDefinition;
import gameauthoring.creation.cellviews.NameCellView;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 * Allows waves to be dropped into the target
 * 
 * @author RyanStPierre
 *
 */

public class WaveDropCell extends NameCellView<WaveDefinition> {

    private static final String HOLDER = "holder";
    
    private ResourceBundle myBundle = ResourceBundle.getBundle("defaults/wave_drop_cell");
    private SpawnerView myTarget;

    public WaveDropCell (SpawnerView target) {
        myTarget = target;
        initDrag();
    }

    private void initDrag () {
        this.setOnDragDetected(e -> dragStart());
        getProfilable();
    }

    private void dragStart () {
        Dragboard db = this.startDragAndDrop(TransferMode.COPY);
        ClipboardContent cc = new ClipboardContent();
        cc.putString(HOLDER);
        db.setContent(cc);
        db.setDragView(getDragImage());
        myTarget.draw().setOnDragOver(event -> dragOver(event));
        myTarget.draw().setOnDragDropped(event -> drop(event));
    }

    private Image getDragImage () {
        return new Image(myBundle.getString("ImageURL"));
    }

    private void drop (DragEvent event) {
        myTarget.add(getProfilable());
    }

    private void dragOver (DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY);
    }

}
