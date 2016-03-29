package engine;

import java.awt.Image;
import javafx.beans.property.SimpleObjectProperty;
import util.Coordinate;


public interface ISpritePlayable {

    SimpleObjectProperty<Image> getImage ();

    void update ();

    SimpleObjectProperty<Coordinate> getLocation ();
}
