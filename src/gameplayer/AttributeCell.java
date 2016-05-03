package gameplayer;

import engine.IAttribute;
import gameauthoring.util.Glyph;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


/**
 * View class to show an attribute on screen. Will be updated
 * for a better view experience than 2 text fields. Uses binding
 * to automatically update the value
 *
 * @author Tommy
 *
 */
public class AttributeCell implements Glyph {
    private static final String FORMATTER = "%s : ";

    IAttribute myAttribute;

    public AttributeCell (IAttribute attribute) {
        myAttribute = attribute;
    }

    @Override
    public Node draw () {
        HBox container = new HBox(5);
        addName(container);
        addValue(container);
        return container;
    }

    /**
     * Create label for the attribute
     *
     * @param container
     */
    private void addName (HBox container) {
        Text name = new Text(String.format(FORMATTER, myAttribute.getType().getType()));
        container.getChildren().add(name);
    }

    /**
     * Adds binded value
     *
     * @param container
     */
    private void addValue (HBox container) {
        Text value = new Text();
        value.textProperty().bind(Bindings.convert(myAttribute.getValueProperty()));
        container.getChildren().add(value);
    }
}
