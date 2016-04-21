package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import java.util.stream.Collectors;
import engine.IGame;
import engine.conditions.ICondition;
import engine.conditions.OnGlobalAttributeCondition;
import javafx.collections.FXCollections;


public class OnGlobalView extends OnGameAttributeView {

    public OnGlobalView (IGame game) {
        super(game, FXCollections
                .observableArrayList(game.getAttributeManager().getAttributes().stream()
                        .map(atty -> atty.getType()).collect(Collectors.toList())));
    }

    @Override
    protected String getLabelKey (String key) {
        return ResourceBundle.getBundle("defaults/on_global_tab").getString(key);
    }

    @Override
    protected ICondition subCreation () {
        return new OnGlobalAttributeCondition(getGame(),
                                              getAttributeType(),
                                              createPredicate(),
                                              getThirdPartyPackage(), 
                                              getGlobalPackage());
    }

}
