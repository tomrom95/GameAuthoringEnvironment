package gameauthoring.levels.waves;

import engine.definitions.spawnerdef.WaveDefinition;
import gameauthoring.creation.cellviews.NameCellView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;


public class WaveDropCell extends NameCellView<WaveDefinition> {

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
        cc.putString("Ryan");
        db.setContent(cc);
        myTarget.draw().setOnDragOver(event -> dragOver(event));
        myTarget.draw().setOnDragDropped(event -> drop(event));
    }

    private void drop (DragEvent event) {
        myTarget.add(getProfilable());
    }

    private void dragOver (DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY);
    }

}
