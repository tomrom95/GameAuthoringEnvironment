package engine.profile;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import graphics.IGraphic;
import graphics.ImageGraphic;
import javafx.beans.property.DoubleProperty;
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

    private String DEFAULT_IMAGE_NAME = "images/Square.png";
    
    private StringProperty myName;
    private StringProperty myDescription;
    private ImageGraphic myImage;
    private DoubleProperty myImageWidth;
    private DoubleProperty myImageHeight;
    private static final double DEFAULT_SIZE = 100;


    public Profile () {
        init("<NAME>", "<DESCRIPTION>", new ImageGraphic(DEFAULT_SIZE, DEFAULT_SIZE, DEFAULT_IMAGE_NAME));
    }

    public Profile (String name) {
        init(name, "", new ImageGraphic(DEFAULT_SIZE, DEFAULT_SIZE, DEFAULT_IMAGE_NAME));
    }

    public Profile (String name, String description) {
        init(name, description, new ImageGraphic(DEFAULT_SIZE, DEFAULT_SIZE, DEFAULT_IMAGE_NAME));
    }

    public Profile (String name, String description, String url) {
        init(name, description, new ImageGraphic(DEFAULT_SIZE, DEFAULT_SIZE, url));
    }

    public Profile (String name, String description, String url, double width, double height){
        init(name, description, new ImageGraphic(width, height, url));
    }
    
    public Profile (String name, String description, ImageGraphic graphic) {
        init(name, description, graphic);

    }

    private void init (String name, String description, ImageGraphic graphic) {
        myName = new SimpleStringProperty(name);
        myDescription = new SimpleStringProperty(description);
        myImage = graphic;
        myImageWidth = graphic.getWidth();
        myImageHeight = graphic.getHeight();
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
    public IGraphic getImage () {
        return myImage;
    }

    @Override
    public String getImageURL () {
        return myImage.getUrlProperty().get();
    }

    @Override
    public DoubleProperty getImageWidth () {
        return myImageWidth;
    }

    @Override
    public DoubleProperty getImageHeight () {
        return myImageHeight;
    }

    @Override
    public void setNew (String name, String desc, String url, double width, double height) {
        myImage = new ImageGraphic(width, height, url);
        myDescription.set(desc);
        myName.set(name);
        myImageWidth.set(width);
        myImageHeight.set(height);
    }

}
