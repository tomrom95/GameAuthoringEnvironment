package gameauthoring.util;

import engine.profile.IProfilable;
import gameauthoring.creation.cellviews.ProfileCellView;
import gameauthoring.levels.sprites.Draggable;
import javafx.scene.Node;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;


public abstract class DraggableCell<T extends IProfilable> extends ProfileCellView<T>
        implements Draggable {
    private Node myTarget;
    protected static final String DRAG_STRING = "Item";

    @Override
    public void setOnDragDetected (MouseEvent e, Node node) {
        Dragboard db = node.startDragAndDrop(TransferMode.COPY);
        db.setContent(createClipboard(DRAG_STRING));

        db.setDragView(getSpriteImage());

        myTarget.setOnDragOver(event -> setOnDragOver(event));
        myTarget.setOnDragDropped(event -> setOnDragDropped(event));
    }

    @Override
    protected Node createSpriteCell (T profile) {
        Node node = super.createSpriteCell(profile);
        setActions(node);
        return node;
    }

    protected void setTarget (Node target) {
        myTarget = target;
    }
}
