package gameauthoring.levels.sprites;

import engine.definitions.SpriteDefinition;
import engine.rendering.LevelRenderer;
import engine.rendering.UnscaledFactory;
import gameauthoring.creation.cellviews.ProfileCellView;
import gameauthoring.levels.SceneController;
import gameauthoring.util.UIFactory;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

/**
 * ListView ListCell that allows for dragging of sprites to the screen. Is used by both
 * the authoring environment and the engine for dragging new sprites onto the screen. Eventually
 * this will be extended for the engine to incorporate costs
 * @author Tommy
 *
 */
public class DraggableSpriteCell extends ProfileCellView<SpriteDefinition> implements Draggable{
    private static final String DRAG_STRING = "Sprite";

    private LevelRenderer myTarget;
    private SceneController myController;
    
    public DraggableSpriteCell (LevelRenderer target, SceneController controller) {
        myTarget = target;
        myController = controller;
    }
   
    @Override
    protected Node createSpriteCell (SpriteDefinition profile) {
        Node node = super.createSpriteCell(profile);
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
    
    /**
     * Helper to get a the correct image from the sprite
     * @return
     */
    private Image getSpriteImage(){
        Node spriteNode = getProfilable().getProfile().getImage().get().getVisualRepresentation(new UnscaledFactory());
        return new UIFactory().getImageFromNode(spriteNode);
    }

    @Override
    public void setOnDragOver (DragEvent e) {
        e.acceptTransferModes(TransferMode.COPY);
    }

    @Override
    public void setOnDragDropped (DragEvent e) {
        Dragboard db = e.getDragboard();
        if (db.hasString()) {
            myController.addSprite(e.getX(), e.getY(), getProfilable());
            myTarget.render();
        }
    }

}
