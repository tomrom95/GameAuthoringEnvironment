package engine;

import javafx.beans.property.SimpleObjectProperty;


public interface Drawable {

    SimpleObjectProperty<IGraphicModule> getDrawer ();
}
