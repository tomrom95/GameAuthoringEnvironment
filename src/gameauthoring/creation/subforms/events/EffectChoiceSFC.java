package gameauthoring.creation.subforms.events;

import java.util.List;
import engine.IGame;
import engine.definitions.concrete.EventPackageDefinition;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.ModuleDefinition;
import engine.effects.IEffect;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.creation.subforms.MultiOptionSFC;
import gameauthoring.creation.subforms.fire.FiringSFCFactory;
import gameauthoring.creation.subforms.fire.FiringSFV;
import gameauthoring.creation.subforms.fire.RemovableSFC;
import util.BundleOperations;

public class EffectChoiceSFC extends MultiOptionSFC<EventPackageDefinition>{
    
    private String effectKey = "EFFECTS";

    public EffectChoiceSFC (IGame game) {
        setMySFCFactory(new EventSFCFactory(game)); //TODO: change this 
        setMyOptions(BundleOperations.getPropertyValueAsList(effectKey, getMyOptionsFile()));
        setMyView(new EffectChoiceSFV(getMyOptions()));
        setActions();       
    }

    @Override
    protected List<IEffect> getList(EventPackageDefinition item){
        return item.getMyEffectsList();
    }

    

}
