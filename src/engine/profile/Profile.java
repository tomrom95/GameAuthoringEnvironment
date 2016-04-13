package engine.profile;

import graphics.IGraphic;
import graphics.ImageGraphic;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Stores the display information of a given object that holds this class by composition
 *
 * @author RyanStPierre, Jeremy Schreck, Joe Lilien
 *
 */
public class Profile implements IProfile {

    private StringProperty myName;
    private StringProperty myDescription;
    private SimpleObjectProperty<ImageGraphic> myImage;

    public Profile () {
        init("", "", new ImageGraphic(0, 0, "images/Square.png"));
    }
    public Profile (String name, String description, String url) {
        init(name, description, new ImageGraphic(0, 0, url));
    }

    public Profile (String name, String description, ImageGraphic graphic) {
        init(name, description, graphic);

    }

    private void init (String name, String description, ImageGraphic graphic) {
        myName = new SimpleStringProperty(name);
        myDescription = new SimpleStringProperty(description);
        myImage = new SimpleObjectProperty<>(graphic);
    }

    @Override
    public StringProperty getDescription () {
        return myDescription;
    }

    @Override
    public StringProperty getName () {
        return myName;
    }

    @Override
    public SimpleObjectProperty<? extends IGraphic> getImage () {
        return myImage;
    }
    
    @Override
    public String getImageURL () {
        return myImage.get().getUrlProperty().get();
    }
    @Override
    public void setNew (String name, String desc, String url) {
        myImage.set(new ImageGraphic(0,0,url));
        myDescription.set(desc);
        myName.set(name);
    }

}
