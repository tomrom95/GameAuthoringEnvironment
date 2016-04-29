package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import java.util.stream.Collectors;
import engine.AttributeType;
import engine.IGame;
import engine.ILevel;
import engine.conditions.ICondition;
import engine.conditions.OnGlobalAttributeCondition;
import engine.events.EventType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class GlobalEndView extends AttributeEndView {

    public GlobalEndView (IGame game, ILevel level) {
        super(game, level, FXCollections
              .observableArrayList(game.getAuthorshipData().getMyCreatedGlobals().getItems().stream()
                                   .map(atty -> atty.getAttributeType()).collect(Collectors.toList())));
    }
   
    @Override
    protected ICondition subCreation () {
        return new OnGlobalAttributeCondition(getGame(), getAttributeType(), createPredicate(),
                                              createEmpty(), getGlobal());
    }
    
    @Override
    protected String getLabelKey (String key) {
        return ResourceBundle.getBundle("defaults/end_global").getString(key);
    }


}
