package engine;

import java.awt.Image;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import util.Coordinate;


public interface ISpriteEditable {

    SimpleObjectProperty<Coordinate> getLocation ();

    SimpleObjectProperty<Image> getImageProperty ();

    SimpleObjectProperty<IMovementStrategy> getMovementStrategyProperty ();

    ObservableList<IModule> getModulesProperty ();

    ObservableList<IAttribute> getAttributesProperty ();

    ObservableList<IResource> getResourcesProperty ();
}
