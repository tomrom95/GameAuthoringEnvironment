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
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;

public class EffectSubFormController implements ISubFormController<EventPackageDefinition> {
    private static final String NAME = "Effect"; //TODO maybe put in resource file

    private IGame myGame;
    private EffectSubFormView myView;
    
    public EffectSubFormController (IGame game) {
        myGame = game;
        myView = new EffectSubFormView(myGame.getAuthorshipData().getMyCreatedAttributes(),
                                              getEffects());
    }

    private List<String> getEffects () {
        // TODO auto populate
        return new ArrayList<String>(Arrays.asList("Decrease", "Incresase", "Proportion"));
    }

    @Override
    public void updateItem (EventPackageDefinition item) {
        // Default profile instead of profileSFC
        item.getProfile().getName().set(NAME);
        item.getProfile().getDescription().set(myView.getEffectType());
        
        
        AttributeDefinition attrDef = myView.getAttribute();
        Attribute lengthAttr = new Attribute(new AttributeType("length"));
        lengthAttr.setValue(Double.valueOf(myView.getData().getValueProperty(myView.getLengthKey()).get()));
        double val = Double.valueOf(myView.getData().getValueProperty(myView.getValueKey()).get());
        Effect effect = getEffect(myView.getEffectType(), lengthAttr, attrDef, val);
        
        //TODO: need to find and replace instead of adding on each save
        item.getMyEffectsList().add(effect);
    }

    private Effect getEffect (String effectType, Attribute length, AttributeDefinition def, double val) {
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
