package gameauthoring.creation.subforms.events;

import java.util.ResourceBundle;
import engine.Attribute;
import engine.AttributeType;
import engine.IGame;
import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.EventPackageDefinition;
import engine.effects.Effect;
import engine.effects.EffectFactory;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormView;
import javafx.collections.ObservableList;

public class EffectSFC implements ISubFormController<EventPackageDefinition> {

    private IGame myGame;
    private EffectSFV myView;
    private Effect myEffect;
    private TypeFactory myTypeFactory = new TypeFactory();
    private String defaultAttributeType = "length";
    private ResourceBundle myEffects = ResourceBundle.getBundle("defaults/effect_types");
    
    public EffectSFC (IGame game, EffectChoiceSFC sfc, Effect myEffect) {
        myGame = game;
        myView = new EffectSFV(myGame.getAuthorshipData(), getEffects()); 
        this.myEffect = myEffect;
    }

    private ObservableList<ProfileDisplay> getEffects () {
        return myTypeFactory.getEffectTypes(myEffects);
    }

    @Override
    public void updateItem (EventPackageDefinition item) {
        AttributeDefinition attrDef = myView.getAttribute();
        Attribute lengthAttr = new Attribute(myView.getLength(),new AttributeType(defaultAttributeType));
        double val = myView.getValue();
        Effect effect = getEffect(myView.getEffectType(), lengthAttr, attrDef, val);
        if(!item.getMyEffectsList().contains(effect)){
            item.getMyEffectsList().add(effect);
        }
    }

    private Effect getEffect (String effectType,
                              Attribute length,
                              AttributeDefinition def,
                              double val) {
        return new EffectFactory().getEffect(effectType, length, def, val);
    }

    @Override
    public void initializeFields (EventPackageDefinition item) {
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

    @Override
    public void populateViewsWithData (EventPackageDefinition item) {
        String type = myTypeFactory.getEffectType(myEffect);
        AttributeDefinition definition = myEffect.getAttributeDefinition();
        double value = myEffect.getAlteringAttribute().getValueProperty().get();
        double length = myEffect.getEffectLengthAttribute().getValueProperty().get();
        myView.populateWithData(type, definition, value, length);
    }

}
