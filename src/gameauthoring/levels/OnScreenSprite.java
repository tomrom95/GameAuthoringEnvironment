package gameauthoring.levels;

import engine.ISprite;
import gameauthoring.Glyph;
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

public class OnScreenSprite implements Draggable, Glyph{
    private static final String MOVE_STRING = "Moving";
    
    private Pane myTarget;
    private ISprite mySprite;
    private Image myImage; // TEMPORARY
    private SceneController myController;
    private Node thisNode;
    
    public OnScreenSprite(ISprite sprite, Pane target, Image image, SceneController controller){
        myTarget = target;
        mySprite = sprite;
        myImage = image;
        myController = controller;
    }
    
    @Override
    public Node draw () {
        thisNode = new ImageView(myImage);
        this.setActions(thisNode, myTarget);
        this.createRightClickMenu();
        return thisNode;
    }

    @Override
    public void update () {
    }

    @Override
    public Image getDragImage () {
        return myImage;
        //return mySprite.getDrawer().get().getVisualRepresentation();
    }
    
    protected SceneController getController(){
        return myController;
    }

    @Override
    public void setOnDragDetected (MouseEvent e) {
        Dragboard db = thisNode.startDragAndDrop(TransferMode.COPY);
        ClipboardContent content = new ClipboardContent();
        content.putString(MOVE_STRING);
        db.setContent(content);
        db.setDragView(getDragImage(), 0, 0);
        
        thisNode.setVisible(false);
        myTarget.setOnDragDropped(event -> setOnDragDropped(event));
    }

    @Override
    public void setOnDragOver (DragEvent e) {
    }

    @Override
    public void setOnDragDropped (DragEvent e) {
        thisNode.setTranslateX(e.getX()-myImage.getWidth()/2);
        thisNode.setTranslateY(e.getY()-myImage.getHeight()/2);
        thisNode.setVisible(true);
    }
    
    public ISprite getSprite() {
        return mySprite;
    }
    
    private void createRightClickMenu () {
        thisNode.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                spriteActionsMenu(myTarget, thisNode).show(thisNode,
                                                      event.getScreenX(),
                                                      event.getScreenY());
            }
        });
    }

    protected ContextMenu spriteActionsMenu (Pane container, Node node) {
        ContextMenu menu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(event -> {
            container.getChildren().remove(node);
            this.getController().deleteSprite(mySprite);
        });
        menu.getItems().add(delete);
        return menu;
    }

}
