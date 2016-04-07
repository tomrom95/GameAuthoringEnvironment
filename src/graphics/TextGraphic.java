package graphics;

import java.util.ResourceBundle;
import gameplayer.IGraphicFactory;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import util.RGBColor;


/**
 * This class removes the JavaFX dependency of Text and stores the properties referencing to the
 * text object
 *
 */
public class TextGraphic implements IGraphic{
	
    private static final String DEFAULT_PATH = "resource/DefaultGraphics";
    private StringProperty myText;
    private StringProperty myFamily;
    private DoubleProperty myFontSize;
    private ObjectProperty<RGBColor> myFill;
    
    public TextGraphic (String text, double fontSize) { 
        myText = new SimpleStringProperty(text);
        myFontSize = new SimpleDoubleProperty(fontSize);
        setDefaults();
    }
    
    private void setDefaults () {
        ResourceBundle defaults = ResourceBundle.getBundle(DEFAULT_PATH);
        myFamily = new SimpleStringProperty(defaults.getString("DefaultFont"));
        myFill = new SimpleObjectProperty<>(RGBColor.BLACK);
    }

    public TextGraphic (String text, double fontSize, String font, RGBColor fill) {
        myText = new SimpleStringProperty(text);
        myFontSize = new SimpleDoubleProperty(fontSize);
        myFamily = new SimpleStringProperty(font);
        myFill = new SimpleObjectProperty<>(fill);
    }
    
    @Override
    public Node getVisualRepresentation (IGraphicFactory factory) {
        return factory.getVisual(this);
    }
    
    public StringProperty getTextProperty () {
        return myText;
    }
    
    public StringProperty getFamilyProperty () {
        return myFamily;
    }
    
    public DoubleProperty getFontSizeProperty () {
        return myFontSize;
    }
    
    public ObjectProperty<RGBColor> getFillProperty () {
        return myFill;
    }

    //TODO extrapolate bounds
    @Override
    public DoubleProperty getWidth () {
        return new SimpleDoubleProperty(0);
    }

    @Override
    public DoubleProperty getHeight () {
        return new SimpleDoubleProperty(0);
    }
}
