package gameplayer;

import engine.IAttribute;
import gameauthoring.Glyph;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

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

    private void addName (HBox container) {
        Text name = new Text(String.format(FORMATTER, myAttribute.getType().getType()));
        container.getChildren().add(name);
    }
    
    private void addValue (HBox container) {
        Text value = new Text();
        System.out.println(myAttribute.getValueProperty().get());
        value.textProperty().bind(Bindings.convert(myAttribute.getValueProperty()));
        container.getChildren().add(value);
    }

    @Override
    public void update () {   
    }


}
