package gameauthoring.levels.sprites;

import engine.ILevel;
import engine.rendering.UnscaledFactory;
import engine.sprite.ISprite;
import gameauthoring.Glyph;
import gameauthoring.UIFactory;
import gameauthoring.levels.LevelRenderer;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import util.Coordinate;


public class OnScreenSprite implements Draggable, Glyph {
    private static final String MOVE_STRING = "Moving";

    private LevelRenderer levelView;
    private ISprite mySprite;
    private SpriteController myController;

    public OnScreenSprite (LevelRenderer renderer, ILevel level, ISprite sprite) {
        levelView = renderer;
        mySprite = sprite;
        myController = new SpriteController(level);
    }

    @Override
    public Node draw () {
        Node node = mySprite.getDrawer().get().getVisualRepresentation(new UnscaledFactory());
        Coordinate location = mySprite.getLocation().get();
        node.relocate(location.getX() -
                      mySprite.getDrawer().get().getGraphic().getWidth().get() / 2,
                      location.getY() - mySprite.getDrawer().get().getGraphic().getHeight()
                              .get() / 2);
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
        db.setContent(this.createClipboard(MOVE_STRING));
        db.setDragView(getSpriteImage(source));

        source.setVisible(false);
        levelView.getPane().setOnDragDropped(event -> setOnDragDropped(event));
    }

    private Image getSpriteImage (Node source) {
        return (new UIFactory()).getImageFromNode(source);
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
        SpriteContextMenu menu = new SpriteContextMenu(levelView, myController, mySprite);
        return menu.createActionMenu();
    }

    protected ISprite getSprite () {
        return mySprite;
    }

}
