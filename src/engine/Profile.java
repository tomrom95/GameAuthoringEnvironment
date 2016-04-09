package engine;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Simple implementation of an IProfile
 * 
 * @author Jeremy Schreck
 *
 */
public class Profile implements IProfile {
    private StringProperty myNameProperty;
    private StringProperty myDescriptionProperty;
    private StringProperty myImageFilepathProperty;

    public Profile (String name, String description, String imagePath) {
        myNameProperty = new SimpleStringProperty(name);
        myDescriptionProperty = new SimpleStringProperty(description);
        myImageFilepathProperty = new SimpleStringProperty(imagePath);
    }

    @Override
    public StringProperty getNameProperty () {
        return myNameProperty;
    }

    @Override
    public StringProperty getDescriptionProperty () {
        return myDescriptionProperty;
    }

    @Override
    public StringProperty getImageFilepathProperty () {
        return myImageFilepathProperty;
    }

}
