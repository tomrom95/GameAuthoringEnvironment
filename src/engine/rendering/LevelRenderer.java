// This entire file is part of my masterpiece
// Ryan St.Pierre
/**
 * My masterpiece includes the reworking of 3 classes the Level Renderer and its two subclasses:
 * AuthorshipRenderer and InGameRenderer. The LevelRenderer class is used to capture the common
 * functionality of rendering drawable back-end objects, whether it be in authorship or game play.
 * Prior to this refractor the InGameRenderer and AuthorshipRenderer were quickly written. I believe
 * they were done hastily, using a lot of copy and paste, with the idea of coming back to fix it.
 * 
 * The rendering of levels in Authorship and in game play are slightly different. In game play the
 * Sprites just need to be visually represented. However, in authorship more access is needed. The
 * user should be able to alter the Sprites, adding paths, relocating, and deleting. Given this,
 * game rendering only needs to deal with Drawables. Drawable is an interface that just gives enough
 * information to draw a Sprite and set its location. However, dealing with Drawables is not suffice
 * for the Authorship renderer, it needs full access to the ISprite (as it wants to give the author
 * full access). I wanted to capture the functionality of rendering without having to always deal
 * with ISprites (shows to much). Thus, as a solution I made the LevelRender deal with generics. The
 * LevelRender now renders anything that extends Drawable. The InGameRender defines this to be the
 * general Drawable interface while the Authorship render defines this to be the more specific
 * ISprite. Using generics allowed me to define abstract ways of rendering while keeping all ISprite
 * accesses restricted to the Authorship renderer class.
 * 
 * I believe that the scaling component of this renderer signifies proper design. This renderer
 * takes in a ScaleRatio. This ScaleRatio defines the scale ratio of the current presented window
 * with respect to the default set size. This ScaleRatio is passed down by reference by the
 * GamePlayer and its value is managed there (where there is direct access to the stage width and
 * height properties). This scale factor is necessary because virtual and visual pixels are
 * completely independent. In other words, the backend engine has no concept of being displayed. Its
 * coordinates are independent of any scaling. This means the view (the renderer) truly represents
 * the model directly, while having the back-end coordinate system be completely independent of the
 * view.
 * 
 * It is important that the GameEngine has reference to a generic IRender interface, not this class.
 * Originally, our rendering algorithm did not cache node creation, recreating all Sprite nodes
 * every render. This was not fast enough to be workable. Thus, we had to reconstruct the
 * LevelRenderer class. We changed the IRenderer in the GameEngine class from a class that did not
 * cache to this. Since the GameEngine used the interface it was easy to swap out the old renderer
 * for this one, using the Strategy pattern.
 * 
 * This class has short methods that have a distinct purpose. In addition, I went through and
 * changed a lot of names to be more concise and clear. These two components contribute to the
 * readibility of the class.
 * 
 * Here I take advantage of the Template design pattern to define the skeleton of methods, but call
 * on abstract methods to be concretely implemented by the subclasses. For example. the
 * getBackgroundImage() method is fully fleshed out but calls the abstract method, getBackgroundURL
 * The reason this method is abstract is because the URL comes from different places based on the 
 * subclass, from the game's current level in the in game case of the direct reference of the level
 * in the authorship case.
 * 
 */
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


/**
 * A class created to capture the common functionality of level rendering in multiple environments
 * This includes both game play rendering and in the authoring environment
 * 
 * Drawables are cached with their Node representation to improve performance
 * 
 * @author RyanStPierre
 * @param <T> The backend object type to be rendered
 */
public abstract class LevelRenderer<T extends Drawable> implements IRenderer {

    private static final boolean PRESERVE_RATIO = true;
    private static final boolean SMOOTH = true;
    private static final double HALF = .5;

    /**
     * A Map of Drawable to Node is used for performance reasons
     * Recreating a new Node each time, rather than just moving those already rendered is too
     * expensive
     */
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
        List<Node> currentEngineNodes = getCurrentNodeList();
        removeScreenNodesNotInEngine(currentEngineNodes);
    }

    protected List<Node> getCurrentNodeList () {
        return getList().stream()
                .map(drawable -> updateMap(drawable))
                .collect(Collectors.toList());
    }

    private Node updateMap (T drawable) {
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

    /**
     * Removes node from pane (visual representation) and node map
     * 
     * @param node
     */
    private void remove (Node node) {
        getCurrentDrawnNodes().remove(node);
        for (T draw : getKeysForNode(node)) {
            getNodeMap().remove(draw);
        }
    }

    protected abstract Node getNode (T drawable);

    protected abstract Collection<? extends T> getList ();

    protected void removeScreenNodesNotInEngine (List<Node> engineNodes) {
        getCurrentDrawnNodes().stream()
                .filter(node -> !engineNodes.contains(node))
                .forEach(node -> remove(node));
    }

    private Collection<Node> getCurrentDrawnNodes () {
        return getPane().getChildren();
    }

    private List<T> getKeysForNode (Node node) {
        // == is desired (want by address)
        return getNodeMap().keySet().stream()
                .filter(draw -> getNodeMap().get(draw) == node)
                .collect(Collectors.toList());
    }

    protected void relocateAndScale (Drawable drawable, Node node) {
        relocate(node, drawable.getLocation());
        scale(node);
        node.setVisible(drawable.getDrawer().isVisible());
        node.setRotate(drawable.getOrientation());
    }

    private void relocate (Node node, Coordinate location) {
        node.relocate(scale(location.getX()) - half(node.getBoundsInLocal().getWidth()),
                      scale(location.getY()) - half(node.getBoundsInLocal().getHeight()));
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
