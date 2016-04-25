package gameauthoring.util;

import engine.profile.IProfilable;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;


public class DraggableRemoveCell<T extends IProfilable> extends DraggableCell<T> {

    public DraggableRemoveCell (Node target) {
        setTarget(target);
    }
    

    @Override
    public void setOnDragOver (DragEvent e) {
        e.acceptTransferModes(TransferMode.ANY);
    }

    @Override
    public void setOnDragDropped (DragEvent e) {
        getListView().getItems().remove(getIndex());
    }
}
