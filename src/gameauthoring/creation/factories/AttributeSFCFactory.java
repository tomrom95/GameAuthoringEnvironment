package gameauthoring.creation.factories;

import engine.IGame;
import engine.definitions.concrete.AttributeDefinition;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.LevelSpecificSFC;
import gameauthoring.creation.subforms.MakeAttributeSFC;

public class AttributeSFCFactory extends SubFormControllerFactory<AttributeDefinition> {

    public AttributeSFCFactory (IGame game) {
        super(game);
    }

    @Override
    protected ISubFormController<AttributeDefinition> createSubFormController (String type, Object ... params) {
        if (type.equals("Attribute")) {
            return new MakeAttributeSFC();
        }
        else if (type.equals("LevelSpecific")){
            return new LevelSpecificSFC();
        }
        throw new ReflectionException("Can't create attribute subformcontroller of type " + type);
    }

   

}
