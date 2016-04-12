package gameauthoring.characters;

import java.util.ArrayList;
import java.util.List;
import engine.definitions.AttributeDefinition;
import engine.definitions.SpriteDefinition;

public class CreationControllerFactory {

    public CreationControllerFactory(){
        List<ISubFormControllerSprite> sfcs = new ArrayList<ISubFormControllerSprite>();
        createSpriteCreationController(sfcs);
    }
    
    public CreationControllerSprite createSpriteCreationController(List<? extends ISubFormController<SpriteDefinition>> sfcs ){
        return new CreationControllerSprite(sfcs);
    }
    public CreationControllerAttribute createAttributeCreationController(List<? extends ISubFormController<AttributeDefinition>> sfcs ){
        return new CreationControllerAttribute(sfcs);
    }
}
