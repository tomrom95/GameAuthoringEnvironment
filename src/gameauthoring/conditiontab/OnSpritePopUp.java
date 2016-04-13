package gameauthoring.conditiontab;

import java.util.stream.Collectors;
import engine.AttributeType;
import engine.IGame;
import javafx.collections.FXCollections;


public class OnSpritePopUp extends OnAttributePopUp {

    public OnSpritePopUp (IGame game) {
        super(game, FXCollections
                .observableArrayList(game.getAuthorshipData().getMyCreatedAttributes().getItems()
                        .stream()
                        .map(atty -> new AttributeType(atty.getType()))
                        .collect(Collectors.toList())));
    }

}
