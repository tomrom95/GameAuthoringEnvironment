package gameauthoring.levels;

import engine.ISprite;
import gameauthoring.SpriteCellView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import util.Draggable;

public class DraggableSpriteCell extends SpriteCellView implements Draggable{
    private static final String DRAG_STRING = "Sprite";

    private Pane myTarget;
    private Node mySource;
    private SceneController myController;
    
    public DraggableSpriteCell (ISprite sprite, Pane target, SceneController controller) {
        super(sprite);
        myTarget = target;
        myController = controller;
    }
    
    public Node draw() {
        mySource = super.draw();
        this.setActions(mySource, myTarget);
        return mySource;
    }

    @Override
    public Image getDragImage () {
        return this.getImageProfile();
    }

    @Override
    public void setOnDragDetected (MouseEvent e) {
        Dragboard db = mySource.startDragAndDrop(TransferMode.COPY);
        ClipboardContent content = new ClipboardContent();
        content.putString(DRAG_STRING);
        db.setContent(content);
        db.setDragView(getDragImage(), 0, 0);
        
        myTarget.setOnDragOver(event -> setOnDragOver(event));
        myTarget.setOnDragDropped(event -> setOnDragDropped(event));
    }

    @Override
    public void setOnDragOver (DragEvent e) {
        e.acceptTransferModes(TransferMode.COPY);
    }

    @Override
    public void setOnDragDropped (DragEvent e) {
        Dragboard db = e.getDragboard();
        if (db.hasString()) {
            OnScreenSprite sprite = new OnScreenSpawner(getSprite(), myTarget, getDragImage(), myController);
            Node onScreen = sprite.draw();
            onScreen.setTranslateX(e.getX());
            onScreen.setTranslateY(e.getY());
            myTarget.getChildren().add(onScreen);
        }
    }

}
