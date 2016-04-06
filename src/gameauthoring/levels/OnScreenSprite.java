package gameauthoring.levels;

import engine.ILevel;
import engine.ISprite;
import gameauthoring.Glyph;
import graphics.GraphicFactory;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import util.Coordinate;
import util.Draggable;


public class OnScreenSprite implements Draggable, Glyph {
    private static final String MOVE_STRING = "Moving";

    private LevelRenderer levelView;
    private ObjectProperty<ISprite> mySprite;
    private SpriteController myController;
    private GraphicFactory myFactory;

    public OnScreenSprite (LevelRenderer renderer, ILevel level, ObjectProperty<ISprite> sprite) {
        levelView = renderer;
        myController = new SpriteController(level);
        mySprite = sprite;
        myFactory = new GraphicFactory();
    }

    @Override
    public Node draw () {
        //thisNode = myLevel.getNode(mySprite);
        Node node = mySprite.get().getDrawer().get().getVisualRepresentation(myFactory);
        Coordinate location = mySprite.get().getLocation().get();
        node.relocate(location.getX() - mySprite.get().getDrawer().get().getGraphic().getWidth().get()/2,
                      location.getY() - mySprite.get().getDrawer().get().getGraphic().getHeight().get()/2);
        this.setActions(node);
        this.createRightClickMenu(node);
        return node;
    }

    @Override
    public void update () {
        
    }

    @Override
    public void setOnDragDetected (MouseEvent e, Node source) {
        Dragboard db = source.startDragAndDrop(TransferMode.COPY);
        ClipboardContent content = new ClipboardContent();
        content.putString(MOVE_STRING);
        db.setContent(content);

        source.setVisible(false);
        levelView.getPane().setOnDragDropped(event -> setOnDragDropped(event));
    }

    @Override
    public void setOnDragOver (DragEvent e) {
    }

    @Override
    public void setOnDragDropped (DragEvent e) {
        myController.moveSprite(mySprite, e.getX(), e.getY());
        levelView.render();
    }

    private void createRightClickMenu (Node node) {
        node.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                spriteActionsMenu(levelView.getPane()).show(node,
                                                                  event.getScreenX(),
                                                                  event.getScreenY());
            }
        });
    }

    protected ContextMenu spriteActionsMenu (Pane container) {
        ContextMenu menu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(event -> {
            myController.deleteSprite(mySprite);
            levelView.render();
        });
        menu.getItems().add(delete);
        return menu;
    }
    
    protected ObjectProperty<ISprite> getSprite(){
        return mySprite;
    }

}
