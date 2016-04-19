package gameplayer;

import engine.Drawable;
import engine.IAttribute;
import engine.rendering.GraphicFactory;
import engine.rendering.ScaleFactory;
import gameauthoring.util.Glyph;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class SpriteDisplay implements Glyph {

    private VBox myPane = new VBox();
    private GraphicFactory myFactory = new ScaleFactory(80, 80);
    private Drawable mySprite;

    public Node draw () {
        return myPane;
    }

    public void populate (Drawable drawn) {
        setSprite(drawn);
        render();
    }

    private void render () {
        myPane.getChildren().clear();
        add(mySprite.getDrawer().getVisualRepresentation(myFactory));
        System.out.println(mySprite.getAttributes());
        mySprite.getAttributes().forEach(a -> add(generateLabel(a)));
    }

    private void add (Node node) {

        myPane.getChildren().add(node);

    }

    private Node generateLabel (IAttribute attribute) {

        return new Label(attribute.getType().getType() + " " + attribute.getValueProperty().get());

    }

    private void setSprite (Drawable drawable) {
        mySprite = drawable;
    }

}
