package engine;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Simple implementation of an IProfile
 * 
 * @author Jeremy Schreck
 *
 */
public class Profile implements IProfile {
    private ObjectProperty<SpriteType> mySpriteTypeProperty;
    private StringProperty myDescriptionProperty;
    private StringProperty myImageFilepathProperty;
    
    
    public Profile(SpriteType type, String description, String imagePath) {
        init (type, description, imagePath);
    }
    public Profile(String name, String description, String imagePath){
        SpriteType spriteType = new SpriteType(name);
        init (spriteType, description, imagePath);
    }
    
    private void init(SpriteType type, String description, String imagePath){
        mySpriteTypeProperty = new SimpleObjectProperty<SpriteType>(type);
        myDescriptionProperty = new SimpleStringProperty(description);
        myImageFilepathProperty = new SimpleStringProperty(imagePath);

    }

    @Override
    public ObjectProperty<SpriteType> getSpriteTypeProperty () {
        return mySpriteTypeProperty;
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
