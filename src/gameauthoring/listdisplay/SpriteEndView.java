package gameauthoring.listdisplay;

import java.util.stream.Collectors;
import engine.AttributeType;
import engine.IGame;
import engine.ILevel;
import javafx.collections.FXCollections;


public class SpriteEndView extends EndView {

    public SpriteEndView (IGame game, ILevel level) {
        super(game, level, FXCollections
                .observableArrayList(game.getAuthorshipData().getMyCreatedAttributes().getItems()
                        .stream()
                        .map(atty -> new AttributeType(atty.getType()))
                        .collect(Collectors.toList())));
    }
}
