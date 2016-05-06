package engine.rendering;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import engine.Drawable;
import engine.IGamePlayable;
import gameplayer.SpriteDisplayController;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import util.ScaleRatio;


/**
 * Used to render the back-end components into JavaFX responsive objects for the screen
 *
 * @author RyanStPierre
 *
 */
public class InGameRenderer extends LevelRenderer<Drawable> {

    private IGraphicFactory myFactory;
    private IGamePlayable myGame;
    private SpriteDisplayController mySpriteDisplay;
    private boolean myFirstTime;

    public InGameRenderer (IGamePlayable game,
                           Pane pane,
                           SpriteDisplayController spriteDisplay,
                           ScaleRatio scale) {
        super(pane, scale);
        myFactory = new UnscaledFactory();
        myGame = game;
        myFirstTime = true;
        mySpriteDisplay = spriteDisplay;
    }

    @Override
    protected void drawSprites () {
        List<Node> currentEngineConvertedNodeList = getAndUpdateEngineNodeList();
        removeScreenNodesNotInEngine(currentEngineConvertedNodeList);
        updateExistingNodeLocations();
    }

    @Override
    public void render () {
        // TODO possibly use invalidation listener in case the background image changes, such as in
        // the case of a level change
        if (myGame.getSwitched()) {
            myFirstTime = true;
        }
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

    @Override
    protected void updateExistingNodeLocations () {
        getNodeMap().keySet().stream()
                .forEach(drawable -> draw(getNodeMap().get(drawable), drawable));
        // .relocate(drawable.getLocation().getX(), drawable.getLocation().getY()));
    }

    /**
     * This method will remove nodes from JavaFX render tree if the does not report them as listing
     * in its list of drawables
     */
    @Override
    protected void removeScreenNodesNotInEngine (List<Node> engineNodes) {
        getCurrentDrawnNodes()
                .removeIf(node -> checkEngineContainsAndUpdateDrawNodeMapEntry(engineNodes, node));
    }

    private boolean checkEngineContainsAndUpdateDrawNodeMapEntry (List<Node> engineNodes,
                                                                  Node node) {
        boolean shouldRemove = !engineNodes.contains(node);
        if (shouldRemove) {
            for (Drawable draw : getKeysForNode(getNodeMap(), node)) {
                getNodeMap().remove(draw);
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
    @Override 
    protected List<Node> getAndUpdateEngineNodeList () {
        return myGame.getDrawables().stream().map(drawable -> getNodeForDrawableAddNew(drawable))
                .collect(Collectors.toList());
    }

    private Node getNodeForDrawableAddNew (Drawable drawn) {
        if (getNodeMap().containsKey(drawn)) {
            return getNodeMap().get(drawn);
        }
        else {
            Node node = drawn.getDrawer().getVisualRepresentation(myFactory);
            node.setOnMouseClicked(e -> mySpriteDisplay.populate(drawn));
            getNodeMap().put(drawn, node);
            add(node);
            return node;
        }
    }

    private void add (Node node) {
        getPane().getChildren().add(node);
    }

    @Override
    public void redrawBackground () {
        myFirstTime = true;
        for (Drawable drawable : getNodeMap().keySet()) {
            resize(drawable, getNodeMap().get(drawable));
        }
    }

    private Node resize (Drawable draw, Node node) {
        draw(node, draw);
        return node;
    }

    @Override
    protected double scaledHeight () {
        return getScale().scale(myGame.getLevelBounds().getHeight());
    }

    @Override
    protected double scaledWidth () {
        return getScale().scale(myGame.getLevelBounds().getWidth());
    }

    @Override
    protected Collection<? extends Drawable> getList () {
        return myGame.getDrawables();
    }

    @Override
    protected Node getNode (Drawable item) {
        return item.getDrawer().getVisualRepresentation(myFactory);
    }

}
