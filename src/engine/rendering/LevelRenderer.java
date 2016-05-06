package engine.rendering;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import engine.Drawable;
import engine.sprite.ISprite;
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
 *
 * TODO fix scaling of window sizes
 */
public abstract class LevelRenderer<T extends Drawable> implements IRenderer {

    private Map<T, Node> myNodeMap = new HashMap<>();
    private Pane myPane;
    private ScaleRatio myScale;

    public LevelRenderer (Pane pane, ScaleRatio scale) {
        myScale = scale;
        myPane = pane;
    }

    @Override
    public void render () {
        drawBackground(getBackgroundURL());
        drawSprites();
    }

    public Pane getPane () {
        return myPane;
    }

    protected List<Node> getAndUpdateEngineNodeList () {
        return getList().stream().map(drawable -> getNodeForDrawableAddNew(drawable))
                .collect(Collectors.toList());
    }

    private Node getNodeForDrawableAddNew (T sprite) {
        if (getNodeMap().containsKey(sprite)) {
            return getNodeMap().get(sprite);
        }
        else {
            Node node = getNode(sprite);
            node.scaleXProperty().set(getScale().getScale());
            node.scaleYProperty().set(getScale().getScale());
            getNodeMap().put(sprite, node);
            add(node);
            return node;
        }
    }

    private void add (Node node) {
        myPane.getChildren().add(node);
    }

    protected abstract Node getNode (T item);

    protected abstract Collection<? extends T> getList ();

    protected void drawSprites () {
        List<Node> currentEngineConvertedNodeList = getAndUpdateEngineNodeList();
        removeScreenNodesNotInEngine(currentEngineConvertedNodeList);
        updateExistingNodeLocations();
    }

    protected void removeScreenNodesNotInEngine (List<Node> engineNodes) {
        getCurrentDrawnNodes()
                .removeIf(node -> checkEngineContainsAndUpdateDrawNodeMapEntry(engineNodes, node));
    }

    private Collection<Node> getCurrentDrawnNodes () {
        return myPane.getChildren();
    }
    
    private boolean checkEngineContainsAndUpdateDrawNodeMapEntry (List<Node> engineNodes,
                                                                  Node node) {
        boolean shouldRemove = !engineNodes.contains(node);
        if (shouldRemove) {
            for (T draw : getKeysForNode(getNodeMap(), node)) {
                myNodeMap.remove(draw);
            }
        }
        return shouldRemove;
    }
    
    private List<T> getKeysForNode (Map<T, Node> map, Node node) {
        return map.keySet().stream().filter(draw -> map.get(draw) == node)
                .collect(Collectors.toList());
    }
    
    protected void updateExistingNodeLocations () {
        getNodeMap().keySet().stream().forEach(drawable -> {
            draw(getNodeMap().get(drawable), drawable);
            getNodeMap().get(drawable).setVisible(true);
        });

    }

    abstract String getBackgroundURL ();

    protected void draw (Node node, Drawable sprite) {
        Coordinate location = sprite.getLocation();
        node.relocate(getScale().scale(location.getX()) -
                      sprite.getDrawer().getGraphic().getHeight().get() / 2,
                      getScale().scale(location.getY()) - sprite.getDrawer().getGraphic()
                              .getHeight().get() / 2);
        node.setScaleX(getScale().getScale());
        node.setScaleY(getScale().getScale());
        node.setVisible(sprite.getDrawer().isVisible());
        node.setRotate(sprite.getOrientation());
    }

    protected void drawBackground (String url) {
        if (url == null) {
            return;
        }
        Image img = getImage(url);
        BackgroundImage background = new BackgroundImage(img,
                                                         BackgroundRepeat.NO_REPEAT,
                                                         BackgroundRepeat.NO_REPEAT,
                                                         BackgroundPosition.DEFAULT,
                                                         BackgroundSize.DEFAULT);
        myPane.setBackground(new Background(background));
        myPane.setMinWidth(img.getWidth());
        myPane.setMinHeight(img.getHeight());
    }

    public abstract void redrawBackground ();

    protected ScaleRatio getScale () {
        return myScale;
    }

    protected Image getImage (String url) {
        return new Image(url, scaledWidth(), scaledHeight(), true, true);
    }

    protected abstract double scaledHeight ();

    protected abstract double scaledWidth ();

    protected Map<T, Node> getNodeMap () {
        return myNodeMap;
    }
}
