package engine;

import javafx.beans.property.ObjectProperty;
import util.Coordinate;

public interface IPositionable {

    ObjectProperty<Coordinate> getLocation ();
}
