package engine.rendering;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import engine.Drawable;
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

public abstract class LevelRenderer<T extends Drawable> implements IRenderer {

    private static final boolean PRESERVE_RATIO = true;
    private static final boolean SMOOTH = true;
    private static final double HALF = .5;
    
    private Map<T, Node> myNodeMap = new HashMap<>();
    private Pane myPane;
    private ScaleRatio myScale;

    public LevelRenderer (Pane pane, ScaleRatio scale) {
        myScale = scale;
        myPane = pane;
    }

    @Override
    public void render () {
        drawBackground();
        drawSprites();
    }
    
    protected void drawBackground () {
        Image image = getImage(getBackgroundURL());
        BackgroundImage background = getBackgroundImage(image);
        myPane.setBackground(new Background(background));
        resizePane(image.getWidth(), image.getHeight());
    }
    
    private void resizePane (double width, double height) {
        myPane.setMinWidth(width);
        myPane.setMinHeight(height);
    }

    private BackgroundImage getBackgroundImage (Image image) {
        return new BackgroundImage(image,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.DEFAULT,
                            BackgroundSize.DEFAULT);
    }

    protected abstract String getBackgroundURL ();

    protected void drawSprites () {
        List<Node> currentEngineNodeList = getCurrentNodeList();
        removeScreenNodesNotInEngine(currentEngineNodeList);
    }

    protected List<Node> getCurrentNodeList () {
        return getList().stream().map(drawable -> addToMap(drawable))
                .collect(Collectors.toList());
    }

    private Node addToMap (T drawable) {
        if (getNodeMap().containsKey(drawable)) {
            relocateAndScale(drawable, getNodeMap().get(drawable));
            return getNodeMap().get(drawable);
        }
        else {
            Node node = getNode(drawable);
            relocateAndScale(drawable, node);
            add(drawable, node);
            return node;
        }
    }

    private void add (T drawable, Node node) {
        getNodeMap().put(drawable, node);
        myPane.getChildren().add(node);
    }

    protected abstract Node getNode (T drawable);

    protected abstract Collection<? extends T> getList ();

    protected void removeScreenNodesNotInEngine (List<Node> engineNodes) {
        getCurrentDrawnNodes()
                .removeIf(node -> checkEngineContainsAndUpdateDrawNodeMapEntry(engineNodes, node));
    }

    private Collection<Node> getCurrentDrawnNodes () {
        return getPane().getChildren();
    }
    
    private boolean checkEngineContainsAndUpdateDrawNodeMapEntry (List<Node> engineNodes,
                                                                  Node node) {
        if (!engineNodes.contains(node)) {
            for (T draw : getKeysForNode(node)) {
                getNodeMap().remove(draw);
            }
        }
        return !engineNodes.contains(node);
    }
    
    private List<T> getKeysForNode (Node node) {
        return getNodeMap().keySet().stream().filter(draw -> getNodeMap().get(draw) == node)
                .collect(Collectors.toList());
    }

    protected void relocateAndScale (Drawable drawable, Node node) {
        relocate(node, drawable.getLocation());
        scale(node);
        node.setVisible(drawable.getDrawer().isVisible());
        node.setRotate(drawable.getOrientation());
    }    

    private void relocate (Node node, Coordinate location) {
        node.relocate(scale(location.getX()) -
                      half(node.getBoundsInLocal().getWidth()),
                      scale(location.getY()) - 
                      half(node.getBoundsInLocal().getHeight()));
    }
    
    private double half (double input) {
        return input * HALF;
    }

    private void scale (Node node) {
        node.setScaleX(getScale().get());
        node.setScaleY(getScale().get());
    }
    
    private double scale (double input) {
        return getScale().scale(input);
    }

    protected ScaleRatio getScale () {
        return myScale;
    }

    protected Image getImage (String url) {
        return new Image(url, scale(boundWidth()), scale(boundHeight()), PRESERVE_RATIO, SMOOTH);
    }

    protected abstract double boundHeight ();

    protected abstract double boundWidth ();

    private Map<T, Node> getNodeMap () {
        return myNodeMap;
    }
    
    public Pane getPane () {
        return myPane;
    }

}
