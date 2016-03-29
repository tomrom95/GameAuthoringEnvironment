package engine;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;


public interface ISpriteManager {

    ObservableList<SimpleObjectProperty<ISprite>> getSprites ();
}
