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
import engine.effects.IEffect;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;

public class EffectSFC implements ISubFormController<EventPackageDefinition> {
    private static final String NAME = "Effect"; //TODO maybe put in resource file

    private IGame myGame;
    private EffectSFV myView;
    
    public EffectSFC (IGame game) {
        myGame = game;
        myView = new EffectSFV(myGame.getAuthorshipData().getMyCreatedAttributes(),
                                              getEffects());
    }

    private List<String> getEffects () {
        // TODO auto populate
        return new ArrayList<String>(Arrays.asList("Decrease", "Increase", "Proportion"));
    }

    @Override
    public void updateItem (EventPackageDefinition item) {
        // Default profile instead of profileSFC
        item.getProfile().getName().set(myView.getName());
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

    @Override
    public void populateViewsWithData (EventPackageDefinition item) {
        myView.setName(item.getProfile().getName().get());
        
        //TODO problem: we can't set event selection because we don't have ProfileDisplay object, just the string
        myView.setEventSelection(item.getProfile().getDescription().get());  
        
        IEffect effect = item.getMyEffectsList().get(0); //TODO: can we just change eventpackage to have one effect or event?
        
        //TODO: need to change effect to store attribute definition
        //myView.setAttribute(effect.getAttributeType())
        //TODO: need to move some Effect methods to Ieffect
        //myView.getData().getValueProperty(myView.getLengthKey()).set(Double.toString(effect.getEffectLengthAttribute().getValueProperty().get()));
        //myView.getData().getValueProperty(myView.getValueKey()).set(effect.getAlteringValue());
        
        
    }

}
