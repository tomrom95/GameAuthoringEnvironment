package gameauthoring;

import engine.definitions.SpriteDefinition;
import engine.rendering.GraphicFactory;
import engine.rendering.ScaleFactory;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class SpriteCellView extends ListCell<SpriteDefinition> {

    private static final double PIC_SIZE = 30;
    private static final String DEFAULT_NAME = "<No Name>";
    private static final String DEFAULT_DESCRIPTION = "<No Description>";

    private SpriteDefinition mySprite;

    @Override
    protected void updateItem (SpriteDefinition item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            mySprite = item;
            setGraphic(createSpriteCell());
        }
    }

    protected Node createSpriteCell () {
        HBox container = new HBox(10);
        container.setAlignment(Pos.CENTER_LEFT);
        container.getChildren().add(createImageProfile());
        container.getChildren().add(createTextProfile());
        return container;
    }

    private Node createTextProfile () {
        VBox container = new VBox();

        Text name = new Text(getStringOrDefault(getSprite().getName(), DEFAULT_NAME));
        Text description = new Text(getStringOrDefault(getSprite().getDescription(),
                                                       DEFAULT_DESCRIPTION));
        container.getChildren().addAll(name, description);
        return container;
    }

    private String getStringOrDefault (String name, String defaultName) {
        return (name == null) ? defaultName : name;
    }

    private Node createImageProfile () {
        GraphicFactory graphics = new ScaleFactory(PIC_SIZE, PIC_SIZE);
        Node node = mySprite.getGraphic().getVisualRepresentation(graphics);
        return node;
    }

    protected SpriteDefinition getSprite () {
        return mySprite;
    }

}
