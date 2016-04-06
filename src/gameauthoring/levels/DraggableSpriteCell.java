package gameauthoring.levels;

import gameauthoring.SpriteCellView;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import util.Draggable;

public class DraggableSpriteCell extends SpriteCellView implements Draggable{
    private static final String DRAG_STRING = "Sprite";

    private LevelRenderer myTarget;
    private SceneController myController;
    
    public DraggableSpriteCell (LevelRenderer target, SceneController controller) {
        myTarget = target;
        myController = controller;
    }
   
    @Override
    protected Node createSpriteCell () {
        Node node = super.createSpriteCell();
        this.setActions(node);
        return node;
    }

    @Override
    public void setOnDragDetected (MouseEvent e, Node node) {
        Dragboard db = node.startDragAndDrop(TransferMode.COPY);
        ClipboardContent content = new ClipboardContent();
        content.putString(DRAG_STRING);
        db.setContent(content);
        
        myTarget.getPane().setOnDragOver(event -> setOnDragOver(event));
        myTarget.getPane().setOnDragDropped(event -> setOnDragDropped(event));
    }

    @Override
    public void setOnDragOver (DragEvent e) {
        e.acceptTransferModes(TransferMode.COPY);
    }

    @Override
    public void setOnDragDropped (DragEvent e) {
        Dragboard db = e.getDragboard();
        if (db.hasString()) {
            myController.addSprite(e.getX(), e.getY(), this.getSprite().get());
            myTarget.render();
        }
    }

}
