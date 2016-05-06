package gameauthoring.creation.subforms.events;

import java.util.List;
import engine.IGame;
import engine.definitions.concrete.EventPackageDefinition;
import engine.effects.IEffect;
import gameauthoring.creation.subforms.dynamic.MultiSFController;
import util.BundleOperations;


public class EffectChoiceSFC extends MultiSFController<EventPackageDefinition> {

    private static final String EFFECT_KEY = "Effects";

    public EffectChoiceSFC (IGame game) {
        super(game);
        setMyOptions(BundleOperations.getPropertyValueAsList(EFFECT_KEY, getMyOptionsFile()));
        setMyView(new EffectChoiceSFV(getMyOptions(), EFFECT_KEY));
        setViewActions();
    }

    @Override
    protected List<IEffect> getListofEditables (EventPackageDefinition item) {
        return item.getMyEffectsList();
    }

    @Override
    protected void resetContents (EventPackageDefinition item) {
        item.getMyEffectsList().clear();
    }

}
