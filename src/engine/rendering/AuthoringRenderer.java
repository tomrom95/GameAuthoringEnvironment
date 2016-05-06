package engine.rendering;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import engine.Drawable;
import engine.ILevel;
import engine.sprite.ISprite;
import gameauthoring.levels.sprites.OnScreenSprite;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import util.ScaleRatio;


public class AuthoringRenderer extends LevelRenderer {

    private ILevel myLevel;
    private Map<ISprite, Node> mySpriteNodeMap;
    private GridRenderer myTileView;

    public AuthoringRenderer (ILevel level, Pane pane, GridPane gridPane, ScaleRatio scale) {
        super(pane, scale);
        myLevel = level;
        mySpriteNodeMap = new HashMap<>();
        myTileView = new GridRenderer(level, gridPane, scale);
    }

    @Override
    public void render () {
        drawBackground(getBackgroundURL());
        drawSprites();
    }

    @Override
    void drawSprites () {
        List<Node> currentEngineConvertedNodeList = getAndUpdateEngineNodeList();
        removeScreenNodesNotInEngine(currentEngineConvertedNodeList);
        updateExistingNodeLocations();
    }

    private Node createOnScreenSprite (ISprite sprite) {
        return (new OnScreenSprite(this, myLevel, sprite, getScale())).draw();
    }

    public void updateNewTiles () {
        myTileView.calculateTileArraySize();
    }

    public void scaleTitles () {
        myTileView.initializeGridLines();
    }

    private void updateExistingNodeLocations () {
        mySpriteNodeMap.keySet().stream().forEach(drawable -> {
            draw(mySpriteNodeMap.get(drawable), drawable);
            mySpriteNodeMap.get(drawable).setVisible(true);
        });

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
            for (Drawable draw : getKeysForNode(mySpriteNodeMap, node)) {
                mySpriteNodeMap.remove(draw);
            }
        }
        return shouldRemove;
    }

    private List<ISprite> getKeysForNode (Map<ISprite, Node> map, Node node) {
        return map.keySet().stream().filter(draw -> map.get(draw) == node)
                .collect(Collectors.toList());
    }

    private List<Node> getCurrentDrawnNodes () {
        return getPane().getChildren();
    }

    private List<Node> getAndUpdateEngineNodeList () {
        return myLevel.getSprites().stream().map(drawable -> getNodeForDrawableAddNew(drawable))
                .collect(Collectors.toList());
    }

    private Node getNodeForDrawableAddNew (ISprite sprite) {
        if (mySpriteNodeMap.containsKey(sprite)) {
            return mySpriteNodeMap.get(sprite);
        }
        else {
            Node node = createOnScreenSprite(sprite);
            node.scaleXProperty().set(getScale().getScale());
            node.scaleYProperty().set(getScale().getScale());
            mySpriteNodeMap.put(sprite, node);
            add(node);
            return node;
        }
    }

    private void add (Node node) {
        getPane().getChildren().add(node);
    }

    @Override
    public void redrawBackground () {
        for (Drawable sprite : mySpriteNodeMap.keySet()) {
            resize(sprite, mySpriteNodeMap.get(sprite));
        }
    }

    private void resize (Drawable sprite, Node node) {
        draw(node, sprite);
    }

    @Override
    protected double scaledHeight () {
        return getScale().scale(myLevel.getBounds().getHeight());
    }

    @Override
    protected double scaledWidth () {
        return getScale().scale(myLevel.getBounds().getWidth());
    }

    @Override
    String getBackgroundURL () {
        return myLevel.getBackgroundImage().getUrlProperty().get();
    }

    public GridRenderer getGrids () {
        return myTileView;
    }

    public ILevel getLevel () {
        return myLevel;
    }

}
