package gameauthoring.creation.subforms;

import engine.IGame;
import engine.definitions.AttributeDefinition;

public class AttributeSFCFactory extends SubFormControllerFactory<AttributeDefinition> {

    public AttributeSFCFactory (IGame game) {
        super(game);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected ISubFormController<AttributeDefinition> createSubFormController (String type) {
        if (type.equals("Attribute")) {
            return new MakeAttributeSFC();
        }
        else if (type.equals("LevelSpecific")){
            return new LevelSpecificSFC();
        }
        return null;
    }

}
