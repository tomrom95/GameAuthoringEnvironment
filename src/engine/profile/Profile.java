package engine.profile;

import graphics.IGraphic;
import graphics.ImageGraphic;


/**
 * Stores the display information of a given object that holds this class by composition
 *
 * @author RyanStPierre, Jeremy Schreck, Joe Lilien
 *
 */
public class Profile implements IProfile {

    private String myName;
    private String myDescription;
    private ImageGraphic myImage;

    public Profile () {
        init("", "", new ImageGraphic(0, 0, "images/photo.png"));
    }
    public Profile (String name, String description, String url) {
        init(name, description, new ImageGraphic(0, 0, url));
    }

    public Profile (String name, String description, ImageGraphic graphic) {
        init(name, description, graphic);

    }

    private void init (String name, String description, ImageGraphic graphic) {
        myName = name;
        myDescription = description;
        myImage = graphic;
    }

    @Override
    public String getDescription () {
        return myDescription;
    }

    @Override
    public String getName () {
        return myName;
    }

    @Override
    public IGraphic getImage () {
        return myImage;
    }
    
    @Override
    public String getImageURL () {
        return myImage.getUrlProperty().get();
    }

}
