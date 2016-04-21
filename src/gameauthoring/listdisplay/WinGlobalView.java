package gameauthoring.listdisplay;

import java.util.stream.Collectors;
import engine.IGame;
import engine.ILevel;
import javafx.collections.FXCollections;

public class WinGlobalView extends WinView {

    public WinGlobalView (IGame game, ILevel level) {
        super(level, FXCollections
              .observableArrayList(game.getAttributeManager().getAttributes().stream()
                                   .map(atty -> atty.getType()).collect(Collectors.toList())));
        
    }

}
