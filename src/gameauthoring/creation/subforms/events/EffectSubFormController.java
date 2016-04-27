package gameauthoring.creation.subforms.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.Attribute;
import engine.AttributeType;
import engine.IGame;
import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.EventPackageDefinition;
import engine.effects.Effect;
import engine.effects.EffectFactory;
import engine.profile.IProfile;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;


public class EffectSubFormController implements ISubFormController<EventPackageDefinition> {
    private static final String FORMATTER = "%s %s by %s";

    private IGame myGame;
    private EffectSubFormView myView;

    public EffectSubFormController (IGame game) {
        myGame = game;
        myView = new EffectSubFormView(myGame.getAuthorshipData(),
                                       getEffects());
    }

    private List<String> getEffects () {
        // TODO auto populate
        return new ArrayList<String>(Arrays.asList("Decrease", "Increase", "Proportion"));
    }

    @Override
    public void updateItem (EventPackageDefinition item) {
        // Default profile instead of profileSFC
        updateProfile (item.getProfile());

        AttributeDefinition attrDef = myView.getAttribute();
        Attribute lengthAttr = new Attribute(new AttributeType("length"));
        lengthAttr.setValue(Double
                .valueOf(myView.getData().getValueProperty(myView.getLengthKey()).get()));
        double val = Double.valueOf(myView.getData().getValueProperty(myView.getValueKey()).get());

        Effect effect = getEffect(myView.getEffectType(), lengthAttr, attrDef, val);

        // TODO: need to find and replace instead of adding on each save
        item.getMyEffectsList().add(effect);
    }

    private void updateProfile (IProfile profile) {
        profile.getName().set(myView.getName());
        profile.getDescription()
            .set(String.format(FORMATTER, myView.getEffectType(),
                               myView.getAttribute().getType(),
                               myView.getData().getValueProperty(myView.getValueKey()).get()));
    }

    private Effect getEffect (String effectType,
                              Attribute length,
                              AttributeDefinition def,
                              double val) {
        return new EffectFactory().getEffect(effectType, length, def, val);
    }

    @Override
    public void initializeFields () {
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}
