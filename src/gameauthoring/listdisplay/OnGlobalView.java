package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import java.util.stream.Collectors;
import engine.IGame;
import javafx.collections.FXCollections;


public class OnGlobalView extends OnAttributeView {
    
    public OnGlobalView (IGame game) {
        super(game, FXCollections
                .observableArrayList(game.getAttributeManager().getAttributes().stream()
                        .map(atty -> atty.getType()).collect(Collectors.toList())));
    }
}
