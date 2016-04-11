package gameauthoring.levels.sprites;

import engine.rendering.AuthoringRenderer;
import engine.rendering.UnscaledFactory;
import gameauthoring.SpriteCellView;
import gameauthoring.UIFactory;
import gameauthoring.levels.SceneController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class DraggableSpriteCell extends SpriteCellView implements Draggable{
    private static final String DRAG_STRING = "Sprite";

    private AuthoringRenderer myTarget;
    private SceneController myController;
    
    public DraggableSpriteCell (AuthoringRenderer target, SceneController controller) {
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
        db.setContent(this.createClipboard(DRAG_STRING));
        
        db.setDragView(getSpriteImage());
        
        myTarget.getPane().setOnDragOver(event -> setOnDragOver(event));
        myTarget.getPane().setOnDragDropped(event -> setOnDragDropped(event));
    }
    
    private Image getSpriteImage(){
        Node spriteNode = this.getSprite().getGraphic().getVisualRepresentation(new UnscaledFactory());
        return (new UIFactory()).getImageFromNode(spriteNode);
    }

    @Override
    public void setOnDragOver (DragEvent e) {
        e.acceptTransferModes(TransferMode.COPY);
    }

    @Override
    public void setOnDragDropped (DragEvent e) {
        Dragboard db = e.getDragboard();
        if (db.hasString()) {
            myController.addSprite(e.getX(), e.getY(), getSprite());
            myTarget.render();
        }
    }

}
