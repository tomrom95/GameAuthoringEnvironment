package gameauthoring.levels;

import engine.ISprite;
import gameauthoring.SpriteCellView;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import util.Draggable;

public class DraggableSpriteCell extends SpriteCellView implements Draggable{
    private static final String DRAG_STRING = "Sprite";

    private Pane myTarget;
    private Node mySource;
    
    public DraggableSpriteCell (ISprite sprite, Pane target) {
        super(sprite);
        myTarget = target;
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
            ImageView img = new ImageView(getDragImage());
            img.setTranslateX(e.getX());
            img.setTranslateY(e.getY());
            createRightClickMenu(img);
            myTarget.getChildren().add(img);
        }
    }

    private void createRightClickMenu (ImageView img) {
        img.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                spriteActionsMenu(myTarget, img).show(img,
                                                      event.getScreenX(),
                                                      event.getScreenY());
            }
        });
    }

    private ContextMenu spriteActionsMenu (Pane container, Node node) {
        ContextMenu menu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(event -> {
            container.getChildren().remove(node);
        });
        menu.getItems().add(delete);
        return menu;
    }

}
