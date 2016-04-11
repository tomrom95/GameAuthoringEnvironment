package engine.sprite;

import graphics.ImageGraphic;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Stores the display information of a given object that holds this class by composition
 *
 * @author RyanStPierre
 *
 */
public class Profile implements IProfile {

    private static final double DEFAULT_IMAGE_SIZE = 10;;
    private StringProperty myName;
    private StringProperty myDescription;
    private ObjectProperty<ImageGraphic> myImage;

    public Profile () {
        myName = new SimpleStringProperty();
        myDescription = new SimpleStringProperty();
        myImage = new SimpleObjectProperty<>();

    }

    public Profile (String name, String description, String url) {
        myName = new SimpleStringProperty(name);
        myDescription = new SimpleStringProperty(description);
        myImage =
                new SimpleObjectProperty<>(new ImageGraphic(DEFAULT_IMAGE_SIZE, DEFAULT_IMAGE_SIZE,
                                                            url));

    }

    @Override
    public StringProperty getDescription () {
        return myDescription;
    }

    @Override
    public StringProperty getNameProperty () {
        return myName;
    }

    @Override
    public ObjectProperty<ImageGraphic> getImage () {
        return myImage;
    }

}
