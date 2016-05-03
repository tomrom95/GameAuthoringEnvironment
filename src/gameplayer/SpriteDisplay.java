package gameplayer;

import java.util.ResourceBundle;
import engine.Drawable;
import engine.IAttribute;
import engine.rendering.GraphicFactory;
import engine.rendering.ScaleFactory;
import gameauthoring.util.BasicUIFactory;
import gameauthoring.util.UIFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import splash.LocaleManager;


/**
 * Class that displays the selected Sprite on the screen
 * Gives information, such as the Sprite's graphic and attributes.
 * In addition, the upgrade option is given
 *
 * @author RyanStPierre
 *
 */

public class SpriteDisplay extends SizeableGlyph implements ISpriteDisplay {

    private ResourceBundle myLang =
            ResourceBundle.getBundle("languages/labels",
                                     LocaleManager.getInstance().getCurrentLocaleProperty().get());
    private VBox myPane = new VBox();
    private GraphicFactory myGraphicFactory;
    private UIFactory myUIFactory = new BasicUIFactory();
    private Button myUpgradeButton;
    private SpriteDisplayController myController;

    public SpriteDisplay () {
        init();
        myController = new SpriteDisplayController(this);
    }

    private void init () {
        myUpgradeButton = myUIFactory.createButton(myLang.getString("Upgrade"));
        myGraphicFactory = new ScaleFactory(parseString(getString("SpriteImageWidth")),
                                            parseString(getString("SpriteImageHeight")));
        setSize();
    }

    private void setSize () {
        myPane.setPrefWidth(parseString(getString("SpriteDisplayWidth")));
        myPane.setPrefHeight(parseString(getString("SpriteDisplayHeight")));
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    public void populate (Drawable drawn) {
        clear();
        add(drawn.getDrawer().getVisualRepresentation(myGraphicFactory));
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
        return myUIFactory.createLabel(attribute.getType().getType() + " " +
                                       attribute.getValueProperty().get());
    }

    public SpriteDisplayController getController () {
        return myController;
    }

    @Override
    public Button getUpgradeButton () {
        return myUpgradeButton;
    }

}
