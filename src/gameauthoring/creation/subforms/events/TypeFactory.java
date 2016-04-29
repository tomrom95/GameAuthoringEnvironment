package gameauthoring.creation.subforms.events;

import engine.effects.DecreaseEffect;
import engine.effects.IEffect;
import engine.effects.IncreaseEffect;
import engine.effects.ProportionEffect;
import engine.profile.ProfileDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;
import java.util.stream.Collectors;


public class TypeFactory {

    private ResourceBundle myEffectTypes = ResourceBundle.getBundle("defaults/effect_types");

    public ObservableList<ProfileDisplay> getEffectTypes (ResourceBundle typeFile) {
        List<ProfileDisplay> list = Collections.list(typeFile.getKeys())
                .stream()
                .map(s -> new ProfileDisplay(s))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(list);
    }

    public String getEffectType (IEffect effect) {
        if (effect instanceof DecreaseEffect) {
            return myEffectTypes.getString("Decrease");
        }
        else if (effect instanceof IncreaseEffect) {
            return myEffectTypes.getString("Increase");
        }
        else if (effect instanceof ProportionEffect) {
            return myEffectTypes.getString("Proportion");
        }
        return "";
    }

}
