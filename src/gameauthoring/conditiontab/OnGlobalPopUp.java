package gameauthoring.conditiontab;

import java.util.stream.Collectors;
import engine.IGame;
import javafx.collections.FXCollections;


public class OnGlobalPopUp extends OnAttributePopUp {
    public OnGlobalPopUp (IGame game) {
        super(game, FXCollections
                .observableArrayList(game.getAttributeManager().getAttributes().stream()
                        .map(atty -> atty.getType()).collect(Collectors.toList())));
    }
}
