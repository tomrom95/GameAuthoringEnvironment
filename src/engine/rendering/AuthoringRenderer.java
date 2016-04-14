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
import javafx.scene.layout.Pane;

public class AuthoringRenderer extends LevelRenderer{
    
    private ILevel myLevel;
    private Map<ISprite, Node> mySpriteNodeMap;

    public AuthoringRenderer (ILevel level, Pane pane) {
        super(pane);
        myLevel = level;
        mySpriteNodeMap = new HashMap<>();
    }
    
    private Node createOnScreenSprite (ISprite sprite) {
        return (new OnScreenSprite(this, myLevel, sprite)).draw();
        //this.draw(spriteNode, sprite);
    }

    @Override
    void drawSprites () {        
        List<Node> currentEngineConvertedNodeList = getAndUpdateEngineNodeList();
        removeScreenNodesNotInEngine(currentEngineConvertedNodeList);
        updateExistingNodeLocations();
    }

    @Override
    String getBackgroundURL () {
        return myLevel.getBackgroundImage().getUrlProperty().get();
    }



    private void updateExistingNodeLocations () {
        mySpriteNodeMap.keySet().stream().forEach(drawable -> mySpriteNodeMap.get(drawable)
                .relocate(drawable.getLocation().getX(), drawable.getLocation().getY()));
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
        return this.getPane().getChildren();
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
            mySpriteNodeMap.put(sprite, node);
            add(node);
            return node;
        }
    }



    private void add (Node node) {
        this.getPane().getChildren().add(node);
    }

}
