package gameplayer;

import engine.Drawable;
import engine.IAttribute;
import engine.rendering.GraphicFactory;
import engine.rendering.ScaleFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


/**
 * Class that displays the selected Sprite on the screen
 * Gives information, such as the Sprite's graphic and attributes.
 * In addition, the upgrade option is given
 * 
 * @author RyanStPierre
 *
 */

public class SpriteDisplay extends SizeableGlyph implements ISpriteDisplay {

    private VBox myPane = new VBox();
    private GraphicFactory myFactory = new ScaleFactory(80, 80);
    private Button myUpgradeButton = new Button("Upgrade");
    private SpriteDisplayController myController;

    public SpriteDisplay () {
        setSize();
        myController = new SpriteDisplayController(this);
    }

    private void setSize () {
        myPane.setPrefWidth(parseString(getString("SpriteDisplayWidth")));
        myPane.setPrefHeight(parseString(getString("SpriteDisplayHeight")));

    }

    public Node draw () {
        return myPane;
    }

    public void populate (Drawable drawn) {
        clear();
        add(drawn.getDrawer().getVisualRepresentation(myFactory));
        drawn.getAttributes().forEach(a -> add(generateLabel(a)));
        add(myUpgradeButton);
    }

    @Override
    public void clear () {
        myPane.getChildren().clear();
    }

    private void add (Node node) {
        myPane.getChildren().add(node);
    }

    private Node generateLabel (IAttribute attribute) {
        return new Label(attribute.getType().getType() + " " + attribute.getValueProperty().get());
    }
    
    public SpriteDisplayController getController () {
        return myController;
    }
    
    public Button getUpgradeButton () {
        return myUpgradeButton;
    }

}
