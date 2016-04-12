package gameauthoring.creation.forms;

import java.util.ArrayList;
import java.util.List;
import engine.definitions.AttributeDefinition;
import engine.definitions.SpriteDefinition;
import gameauthoring.IDefinitionCollection;
import gameauthoring.creation.subforms.ISubFormController;
import gameauthoring.creation.subforms.ISubFormControllerSprite;


public class CreationControllerFactory {

    public CreationControllerFactory () {
        List<ISubFormControllerSprite> sfcs = new ArrayList<ISubFormControllerSprite>();
        createSpriteCreationController(sfcs);
    }

    public CreationControllerSprite createSpriteCreationController (List<? extends ISubFormController<SpriteDefinition>> sfcs) {
        return new CreationControllerSprite(sfcs);
    }

    public CreationControllerAttribute createAttributeCreationController (List<? extends ISubFormController<AttributeDefinition>> sfcs,
                                                                          IDefinitionCollection<AttributeDefinition> defCol) {
        return new CreationControllerAttribute(sfcs, defCol);
    }
}
