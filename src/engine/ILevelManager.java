package engine;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;


public interface ILevelManager {

    ILevel getCurrentLevel ();

    ObservableList<SimpleObjectProperty<ILevel>> getLevels ();
}
