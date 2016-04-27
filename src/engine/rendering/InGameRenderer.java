package engine.rendering;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import engine.Drawable;
import engine.IGamePlayable;
import gameplayer.SpriteDisplay;
import gameplayer.SpriteDisplayController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import util.Coordinate;
import util.ScaleRatio;


/**
 * Used to render the back-end components into JavaFX responsive objects for the screen
 *
 * @author RyanStPierre
 *
 */
public class InGameRenderer extends LevelRenderer {

    private IGraphicFactory myFactory;
    private IGamePlayable myGame;
    private Map<Drawable, Node> myDrawNodeMap;
    private SpriteDisplayController mySpriteDisplay;
    private boolean myFirstTime;
    private ScaleRatio myScale;

    public InGameRenderer (IGamePlayable game,
                           Pane pane,
                           SpriteDisplayController spriteDisplay,
                           ScaleRatio scale) {
        super(pane);
        myFactory = new UnscaledFactory();
        myGame = game;
        myDrawNodeMap = new HashMap<>();
        myFirstTime = true;
        mySpriteDisplay = spriteDisplay;
        myScale = scale;
    }

    @Override
    void drawSprites () {
        List<Node> currentEngineConvertedNodeList = getAndUpdateEngineNodeList();
        removeScreenNodesNotInEngine(currentEngineConvertedNodeList);
        updateExistingNodeLocations();
    }

    @Override
    public void render () {
        // TODO possibly use invalidation listener in case the background image changes, such as in
        // the case of a level change
        if (myFirstTime) {
            drawBackground(getBackgroundURL());
            myFirstTime = false;
        }
        drawSprites();
    }

    @Override
    String getBackgroundURL () {
        return myGame.getBackroundImage().getUrlProperty().get();
    }

    private void updateExistingNodeLocations () {
        myDrawNodeMap.keySet().stream()
                .forEach(drawable -> this.draw(myDrawNodeMap.get(drawable), drawable));
        // .relocate(drawable.getLocation().getX(), drawable.getLocation().getY()));
    }

    /**
     * This method will remove nodes from JavaFX render tree if the does not report them as listing
     * in its list of drawables
     */
    private void removeScreenNodesNotInEngine (List<Node> engineNodes) {
        getCurrentDrawnNodes()
                .removeIf(node -> checkEngineContainsAndUpdateDrawNodeMapEntry(engineNodes, node));
    }

    private boolean checkEngineContainsAndUpdateDrawNodeMapEntry (List<Node> engineNodes,
                                                                  Node node) {
        boolean shouldRemove = !engineNodes.contains(node);
        if (shouldRemove) {
            for (Drawable draw : getKeysForNode(myDrawNodeMap, node)) {
                myDrawNodeMap.remove(draw);
            }
        }
        return shouldRemove;
    }

    private List<Drawable> getKeysForNode (Map<Drawable, Node> map, Node node) {
        return map.keySet().stream().filter(draw -> map.get(draw) == node)
                .collect(Collectors.toList());
    }

    private List<Node> getCurrentDrawnNodes () {
        return getPane().getChildren();
    }

    /**
     * This method will check the engine drawable lists, add new ones to the
     * internal list of maps and add them to the
     *
     * @return List<Node> of node objects that are currently represented in the engine
     */
    private List<Node> getAndUpdateEngineNodeList () {
        return myGame.getDrawables().stream().map(drawable -> getNodeForDrawableAddNew(drawable))
                .collect(Collectors.toList());
    }

    private Node getNodeForDrawableAddNew (Drawable drawn) {
        if (myDrawNodeMap.containsKey(drawn)) {
            return myDrawNodeMap.get(drawn);
        }
        else {
            Node node = drawn.getDrawer().getVisualRepresentation(myFactory);
            node.scaleXProperty().set(myScale.getScale());
            node.scaleYProperty().set(myScale.getScale());
            node.setOnMouseClicked(e -> mySpriteDisplay.populate(drawn));
            myDrawNodeMap.put(drawn, node);
            add(node);
            return node;
        }
    }

    @Override
    protected void draw (Node node, Drawable sprite) {
        Coordinate location = sprite.getLocation();
        node.relocate((location.getX() - sprite.getDrawer().getGraphic().getWidth().get() / 2) *
                      myScale.getScale(),
                      (location.getY() - sprite.getDrawer().getGraphic().getHeight().get() / 2) *
                                           myScale.getScale());
        node.setVisible(sprite.getDrawer().isVisible());
        node.setRotate(sprite.getOrientation());
    }

    private void add (Node node) {
        getPane().getChildren().add(node);
    }

    @Override
    protected Image getImage (String url) {
        return new Image(url, scaledWidth(), scaledHeight(), true, true);
    }

    private double scaledHeight () {
        return myGame.getLevelBounds().getHeight() * myScale.getScale();
    }

    private double scaledWidth () {
        return myGame.getLevelBounds().getWidth() * myScale.getScale();
    }

    @Override
    public void redrawBackground () {
        myFirstTime = true;
        System.out.println("redraw");
        for (Drawable drawable: myDrawNodeMap.keySet()) {
            System.out.println("Map has something");
            resize(drawable, myDrawNodeMap.get(drawable));
        }
       // myDrawNodeMap.keySet().stream().map(key -> resize(key, myDrawNodeMap.get(key)));
    }

    private Node resize (Drawable draw, Node node) {
        draw(node,draw);
        System.out.println(myScale.getScale());
        node.scaleXProperty().set(myScale.getScale());
        node.scaleYProperty().set(myScale.getScale());
        return node;
    }

}
