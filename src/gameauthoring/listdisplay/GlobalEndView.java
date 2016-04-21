package gameauthoring.listdisplay;

import java.util.stream.Collectors;
import engine.AttributeType;
import engine.IGame;
import engine.ILevel;
import engine.events.EventType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class GlobalEndView extends EndView {

    public GlobalEndView (IGame game, ILevel level) {
        super(game, level, FXCollections
                        .observableArrayList(game.getAttributeManager().getAttributes()
                        .stream()
                        .map(atty -> atty.getType()).collect(Collectors.toList())));
    }
    
    @Override
    protected EventType getEventType () {
        return EventType.LOSE;
    }

}
