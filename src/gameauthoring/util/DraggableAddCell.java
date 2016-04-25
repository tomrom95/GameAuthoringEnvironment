package gameauthoring.util;

import engine.profile.IProfilable;
import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 * Draggable cell used to allow user to drag and drop certain items, moving them from one listview to another
 * 
 * @author Joe Lilien
 *
 * @param <T>
 */
public class DraggableAddCell<T extends IProfilable> extends DraggableCell<T>{    
    
    ListView<T> myTarget;
    
    public DraggableAddCell(ListView<T> target){
        setTarget(target);
        myTarget = target;
    }
    
    @Override
    public void setOnDragOver (DragEvent e) {
        e.acceptTransferModes(TransferMode.COPY);
    }

    @Override
    public void setOnDragDropped (DragEvent e) {
        Dragboard db = e.getDragboard();
        if (db.hasString()) {
            if(!myTarget.getItems().contains(getProfilable())){
                myTarget.getItems().add(getProfilable());
            }
        }
    }
}
