package engine;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

public interface ILevel extends Updateable {

    void addSprite (ISprite sprite);

    void addCondition (ICondition condition);

    void addGlobalAttribute (IAttribute attribute);

    void addGlobalResource (IResource resource);

    ObservableList<SimpleObjectProperty<ISprite>> getSprites ();
}
