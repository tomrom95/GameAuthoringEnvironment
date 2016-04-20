package gameplayer;

import engine.Drawable;
import engine.IAttribute;
import engine.events.EventType;
import engine.events.GameEvent;
import engine.rendering.GraphicFactory;
import engine.rendering.ScaleFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class SpriteDisplay extends SizeableGlyph {

    private VBox myPane = new VBox();
    private GraphicFactory myFactory = new ScaleFactory(80, 80);
    private Drawable mySprite;

    public SpriteDisplay () {
        setSize();
    }

    private void setSize () {
        myPane.setPrefWidth(parseString(getString("SpriteDisplayWidth")));
        myPane.setPrefHeight(parseString(getString("SpriteDisplayHeight")));

    }

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
        mySprite.getAttributes().forEach(a -> add(generateLabel(a)));
        addUpgrade();
    }

    private void addUpgrade () {
        Button upgradeButton = new Button("Upgrade");
        upgradeButton.setDisable(!mySprite.isUgradeable());
        upgradeButton
                .setOnMouseClicked(e -> mySprite.registerEvent(new GameEvent(EventType.UPGRADE)));
        add(upgradeButton);

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
